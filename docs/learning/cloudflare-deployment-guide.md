# React 포트폴리오 Cloudflare 배포 실습

[React 전환 학습](react-portfolio-guide.md)의 1~6단계를 마친 뒤 진행하는 안내서입니다. 첫 목표는 `prototype/`의 React 화면을 별도 학습 주소에 배포하고, 수정한 화면을 다시 배포할 수 있게 되는 것입니다.

2026-10-04 기준으로 공식 문서를 확인했습니다. 이 문서의 명령과 설정은 실습 예제이며, 현재 저장소에는 React 앱이나 배포 설정이 아직 없습니다. 문서를 보완하는 과정에서 실제 배포는 진행하지 않았습니다.

## 1. 배포 대상과 방식 이해

이번 실습에서는 **Cloudflare Workers Static Assets**를 사용합니다. Vite가 만든 HTML·CSS·JavaScript와 이미지·폰트를 Workers에 올리는 방식입니다. 이후 Workers API와 D1을 배우는 흐름을 고려해 선택했습니다. 이 단계에서는 서버 코드 없이 정적 화면만 배포합니다.

GitHub 저장소에 코드를 올리는 일과 Cloudflare에 빌드 결과를 배포하는 일은 별개입니다. 로컬에서 실행하는 아래 배포 명령은 Git push 없이도 해당 Cloudflare Worker를 갱신합니다. 기존 GitHub Pages 주소에 React 작업본을 반영하려면 별도의 메인 반영 작업이 필요합니다.

| 위치 | 역할 |
| --- | --- |
| 루트 `index.html`, `index.css`, `index.js` | 기존 배포용 포트폴리오 |
| `prototype/src/` | React 전환 후의 소스 코드 |
| `prototype/public/` | 그대로 공개할 이미지·폰트·아이콘 |
| `prototype/dist/` | Vite가 생성하는 배포 결과 |
| `prototype/wrangler.jsonc` | 학습용 Worker 이름과 배포 경로 |
| `docs/learning/` | 학습 안내와 기록 |

아래 실습은 Cloudflare Vite 플러그인을 추가하지 않은 일반 Vite React 앱을 기준으로 합니다. 플러그인을 쓰는 프로젝트는 빌드 출력과 배포 구성이 달라질 수 있으므로 두 방식을 섞지 않습니다. [Workers 정적 자산 개요](https://developers.cloudflare.com/workers/static-assets/), [Cloudflare Vite 플러그인](https://developers.cloudflare.com/workers/vite-plugin/)

## 2. 시작 전 확인

React 전환을 마쳤다면 `prototype/package.json`, `prototype/src/main.tsx`, `prototype/src/App.tsx`가 있어야 합니다. 현재 HTML 작업본만 있는 상태에서는 아래 명령을 바로 실행하지 말고 먼저 React 전환 단계를 진행합니다.

PowerShell에서 프로젝트 위치와 환경을 확인합니다.

```powershell
Set-Location C:\project\hwandroid
git status --short --branch
node --version
npm --version
Test-Path prototype/package.json
Set-Location prototype
npm install
npm run build
```

각 명령의 결과를 확인하고 다음 명령으로 넘어갑니다. 오류가 났는데도 배포까지 계속 실행하지 않습니다. Node.js 버전은 실습 시점의 [Vite 요구 버전](https://vite.dev/guide/)과 [Wrangler 설치 안내](https://developers.cloudflare.com/workers/wrangler/install-and-update/)를 모두 충족해야 합니다.

React 전환 과정의 파일 변경을 확인하고 작업 브랜치에 커밋해 두면 배포한 소스를 다시 찾기 쉽습니다. 빌드 명령이 TypeScript 검사까지 실행하는지도 `package.json`에서 확인합니다. Vite 빌드만 하는 스크립트라면 별도의 타입 검사 명령도 실행합니다.

## 3. 빌드 결과와 경로 확인

이후 명령은 모두 `C:\project\hwandroid\prototype`에서 실행합니다.

```powershell
npm run build
Test-Path dist/index.html
npm run preview
```

터미널에 표시된 로컬 주소를 브라우저에서 엽니다. 확인이 끝나면 `Ctrl+C`로 미리보기를 종료합니다. Vite의 기본 빌드 출력은 `dist/`이며 `preview`는 빌드 결과를 로컬에서 확인하는 용도입니다. [Vite 정적 배포 안내](https://vite.dev/guide/static-deploy.html)

`public/images/profiles/Hwan2.png`는 빌드 후 `dist/images/profiles/Hwan2.png`로 있어야 합니다. JSX에서는 `/images/profiles/Hwan2.png`로 참조합니다. 폰트와 아이콘도 같은 방식으로 공개 경로를 확인합니다. 이번 실습은 사이트 루트에 배포하므로 Vite의 `base`는 기본값 `/`을 사용합니다. `/prototype/`을 배포 경로로 설정하지 않습니다.

`public/`에는 브라우저에서 공개해도 되는 자산만 둡니다. 취업 문서, 윤문 기록, 원본 백업을 통째로 복사하지 않습니다. 배포 대상은 `prototype/` 전체가 아니라 `dist/`입니다.

## 4. Wrangler 설치와 계정 확인

Cloudflare 계정을 준비한 뒤 배포 도구를 프로젝트의 개발 의존성으로 설치합니다.

```powershell
npm install --save-dev wrangler
npx wrangler --version
npx wrangler login
npx wrangler whoami
```

`login`에서 열린 브라우저로 로그인하고 권한을 승인합니다. `whoami`에서 실제 배포할 계정인지 확인합니다. 여러 계정에 접근할 수 있다면 사용할 계정 ID를 확인해 다음 설정의 `account_id`에 추가합니다. 계정 ID는 API 토큰과 다릅니다. 인증 토큰을 문서나 소스 코드에 적지 않습니다. [Wrangler 인증 명령](https://developers.cloudflare.com/workers/wrangler/commands/general/)

`package.json`과 잠금 파일의 변경은 소스 관리에 포함합니다. 향후 실습 환경을 다시 만들 때 잠금 파일이 있으면 `npm ci`로 같은 의존성을 설치할 수 있습니다.

## 5. 정적 자산 배포 설정 작성

실습 시 `prototype/wrangler.jsonc`를 만들고 다음 내용을 넣습니다.

```jsonc
{
  "$schema": "node_modules/wrangler/config-schema.json",
  "name": "hwandroid-portfolio-learning",
  "compatibility_date": "2026-10-04",
  "workers_dev": true,
  "assets": {
    "directory": "./dist"
  }
}
```

`name`은 학습용 Worker 이름입니다. 배포할 계정에 같은 이름의 Worker가 있다면 먼저 용도를 확인하거나 다른 이름을 정합니다. 같은 이름으로 배포하면 그 Worker의 배포가 갱신됩니다. 여러 계정 중 하나를 지정하려면 최상위에 `account_id`를 추가합니다.

`compatibility_date`는 Workers 런타임의 동작 기준 날짜입니다. 예제에서는 문서 작성일을 사용했고, 실제 실습에서는 지원되는 날짜로 고정해 기록합니다. `assets.directory`는 설정 파일 위치를 기준으로 `dist/`를 가리킵니다. 정적 자산만 배포하므로 이 설정에는 서버 코드 진입점인 `main`이 없습니다. [정적 자산 설정](https://developers.cloudflare.com/workers/static-assets/binding/)

현재 포트폴리오는 `#projects`처럼 페이지 내부 앵커로 이동하므로 SPA 경로 대체 설정이 필요하지 않습니다. 나중에 React Router로 `/projects` 같은 경로를 만들면 `assets.not_found_handling`을 `single-page-application`으로 설정하는 방식을 검토합니다. API를 추가할 때는 `/api/*` 요청이 HTML로 대체되지 않도록 라우팅도 함께 설계합니다. [SPA 라우팅](https://developers.cloudflare.com/workers/static-assets/routing/single-page-application/)

실습 시 `prototype/.gitignore`에는 다음 생성 파일과 로컬 인증 정보를 제외합니다. 기존 내용이 있다면 필요한 항목만 추가합니다.

```gitignore
node_modules/
dist/
.wrangler/
.dev.vars*
.env*
!.env.example
```

## 6. 최초 배포

최신 빌드를 만든 뒤 배포할 파일 구성을 확인합니다.

```powershell
npm run build
npx wrangler deploy --dry-run
```

`--dry-run`은 실제 업로드 없이 배포 준비를 확인합니다. 성공해도 로그인 권한, 배포 주소와 실제 화면이 검증된 것은 아닙니다. 결과가 맞으면 아래 명령으로 Cloudflare에 공개합니다.

```powershell
npx wrangler deploy
```

배포 성공 출력에 나온 실제 주소를 사용합니다. 일반적으로 학습용 Worker는 `workers.dev` 주소에서 확인할 수 있지만 계정별 주소를 추측해서 기록하지 않습니다. [정적 사이트 배포](https://developers.cloudflare.com/workers/static-assets/get-started/), [Wrangler 배포 명령](https://developers.cloudflare.com/workers/wrangler/commands/workers/)

## 7. 배포 후 확인과 기록

배포 주소를 새 탭에서 열고 개발자 도구의 Console과 Network를 확인합니다.

- 프로필·프로젝트 이미지, 아이콘과 폰트가 정상적으로 로드되는가?
- CSS와 JavaScript 요청에 404가 없는가? 응답 내용도 예상한 파일인가?
- 좁은 화면과 낮은 화면에서 목차와 하단 버튼을 사용할 수 있는가?
- 내부 앵커와 GitHub·블로그·이메일·전화 링크가 맞는가?
- 인쇄 버튼과 PDF 저장 화면이 기존 배치를 유지하는가?
- 새로고침해도 같은 화면이 열리는가? Router를 추가했다면 하위 경로에서도 확인했는가?

배포 내역과 소스 커밋은 다음 명령으로 확인합니다.

```powershell
npx wrangler deployments list
git rev-parse --short HEAD
git status --short
```

실습 기록에는 배포 시각, 실제 URL, Worker 이름, 소스 커밋, 배포된 버전 ID, 검증 결과를 함께 남깁니다. 미커밋 변경을 배포했다면 커밋 ID만으로 해당 코드를 재현할 수 없으므로 변경 파일도 적습니다. 배포 ID와 버전 ID는 다르며, 복원에는 **버전 ID**를 사용합니다.

## 8. 수정 후 재배포와 복원

수정은 `prototype/src/`와 필요한 `public/` 자산에서 진행합니다. `dist/` 파일을 직접 편집하면 다음 빌드 때 덮어써집니다. 변경 내용을 확인하고 빌드·미리보기를 거친 뒤 같은 Worker에 재배포합니다.

```powershell
npm run build
npm run preview
```

미리보기를 종료한 다음 배포합니다.

```powershell
npx wrangler deploy
npx wrangler deployments list
```

배포 후에는 7단계 확인을 반복합니다. 문제가 생기면 Cloudflare 대시보드의 해당 Worker 배포 내역에서 정상 동작했던 버전을 확인합니다. CLI로 복원할 때는 아래 문자열을 실제 **버전 ID**로 바꿉니다.

```powershell
npx wrangler rollback "정상-동작했던-버전-ID"
```

복원은 즉시 서비스에 반영되므로 Worker 이름과 계정을 확인한 뒤 실행합니다. 로컬 소스는 이 명령으로 되돌아가지 않습니다. 원인을 수정하지 않고 같은 코드를 다시 배포하면 문제가 재발할 수 있습니다. D1을 연결한 이후에는 Worker 복원만으로 데이터나 스키마 변경이 되돌아가지 않으므로 별도의 복구 절차가 필요합니다. [Workers 복원 안내](https://developers.cloudflare.com/workers/versions-and-deployments/rollbacks/)

## 9. 자주 막히는 지점

| 증상 | 먼저 확인할 부분 |
| --- | --- |
| `package.json`을 찾지 못함 | React 전환 완료 여부와 현재 터미널 위치 |
| 빌드가 실패함 | 첫 오류 메시지, Node.js 요구 버전, TypeScript 검사 결과 |
| `dist/`를 찾지 못함 | 빌드 성공 여부, Vite 출력 경로와 `assets.directory` 일치 여부 |
| 로그인·배포 권한 오류 | `whoami` 결과와 선택한 계정 |
| 이미지·폰트가 안 보임 | `dist/`에 파일이 있는지, 경로 철자와 대소문자가 일치하는지 |
| 배포했는데 예전 화면이 보임 | 빌드 시각, Worker 이름, 배포 내역, 브라우저 캐시 |
| 하위 경로 새로고침이 실패함 | Router 사용 여부와 SPA 라우팅 설정 |

## 10. 다음 학습으로 넘어가는 기준

배포 주소에서 현재 화면을 사용할 수 있고, 작은 문구 수정부터 재배포와 검증까지 직접 할 수 있으면 첫 배포 학습을 마친 것입니다. 그다음 [React와 Workers API 공식 가이드](https://developers.cloudflare.com/workers/framework-guides/web-apps/react/)로 서버 코드와 개발 환경을 배우고, 프로젝트 목록 조회 API를 추가합니다.

D1은 조회 API가 동작한 뒤 연결합니다. 데이터베이스 바인딩, 로컬·원격 데이터 구분, 마이그레이션을 익힌 다음 관리자 편집 기능으로 넘어갑니다. 프론트엔드의 `VITE_*` 환경 변수는 공개 번들에 포함되므로 인증 비밀값을 넣지 않습니다. 관리자 권한은 서버에서 검사해야 합니다. [Vite 환경 변수](https://vite.dev/guide/env-and-mode), [D1 시작하기](https://developers.cloudflare.com/d1/get-started/)
