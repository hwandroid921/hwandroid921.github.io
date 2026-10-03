# Hwandroid Portfolio

신입 풀스택 웹 개발자 유환희의 포트폴리오 웹사이트입니다.

현재 배포 기준은 **Portfolio v6**입니다. Java와 Spring Boot 기반의 백엔드 개발 경험과 Vue 3 프론트엔드 프로젝트, AI를 활용한 제품 구현 및 개발 워크플로 경험을 소개합니다.

## 주요 구성

- 소개 및 이력
- 기술 스택과 성장 과정
- 제주 문화 관광 서비스
- 온라인 서점 ReadMe
- 모빌리티 모니터링 플랫폼 LogMile
- AI 도구 사용량 대시보드 TokenMonitor
- 프로젝트 구현과 개발 과정에서의 AI 활용 사례
- 이메일, GitHub, 기술 블로그 연락처

## AI 활용 경험

- **LogMile:** 기획·설계 내용을 검토하고 산출물을 작성하는 데 AI를 활용했습니다. 코드 작성과 디버깅에도 활용했으며, FastAPI 기반 번호판 OCR 서버를 백엔드 흐름에 연동했습니다.
- **ReadMe:** 결제·인증·인가 관련 코드를 구현할 때 AI를 보조 도구로 활용했습니다. 제 프론트엔드 담당 범위에서는 Vue 화면, Pinia 상태 관리, 라우터 가드와 결제 흐름에 맞춰 제안을 검토하고 적용했습니다.
- **TokenMonitor:** 업로드 작업 자동화와 코드 검토에 AI를 활용했습니다. 백엔드·프론트엔드 리뷰 서브에이전트를 구성하고, 디자인·코드·사용자 흐름 검토에 플러그인과 스킬을 사용했습니다.
- **검토 원칙:** AI가 제안한 내용은 프로젝트 맥락과 실행 결과를 확인한 뒤 반영합니다.

## 기술 구성

- HTML5
- CSS3 (페이지 내 스타일)
- 별도 빌드 과정이 없는 정적 웹사이트
- GitHub Pages 배포

## 프로젝트 구조

```text
.
├── index.html
├── jeju-project.png
├── logmile-project.png
├── readme-project.png
└── png/
    ├── Hwan2.png
    └── TokenMonitor.png
```

## 로컬 확인

정적 파일이므로 `index.html`을 직접 열거나 로컬 HTTP 서버로 실행할 수 있습니다.

```bash
python -m http.server 8000
```

실행 후 브라우저에서 `http://localhost:8000`으로 접속합니다.

## 브랜치 운영 기준

- `main`: Portfolio v6 메인 페이지의 배포 기준 브랜치
- 기능 수정: 별도 작업 브랜치에서 진행 후 `main`에 반영
- 과거 포트폴리오 시안은 참고용이며, 신규 배포 기준은 루트의 `index.html`입니다.

## 링크

- Website: https://hwandroid921.github.io/
- GitHub: https://github.com/hwandroid921
- Blog: https://velog.io/@hwandroid921
- Email: olgksgml@gmail.com

## 취업 준비 문서

- [이력서·자기소개서 문서 모음](docs/job-application/README.md)
