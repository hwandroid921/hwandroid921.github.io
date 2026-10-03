# Hwandroid Portfolio

현재 메인 페이지는 v7입니다.

## 메인 페이지

- `index.html`, `index.css`, `index.js`: 배포용 페이지
- `assets/`: 폰트 및 라이선스
- `images/projects/`: 메인에서 사용하는 프로젝트 이미지 4개
- `images/profiles/`, `images/avatars/`: 포트폴리오에 사용할 프로필 및 아바타 이미지
- `docs/`: 백업에서 복원한 이력서·자기소개서·지원서 자료

## 프로토타입 작업

메인에 적용하기 전의 모든 작업은 `prototype/`에서 진행합니다.
프로토타입은 독립된 CSS, JS, 폰트와 이미지를 사용하므로 메인에 영향을 주지 않습니다.

- `prototype/index.html`: 현재 v7 작업본 및 유일한 HTML 진입점
- `prototype/index.css`, `prototype/index.js`: v7 스타일 및 기능
- `prototype/images/`: 작업본에서 사용하는 프로젝트 이미지와 프로필 사진
- `prototype/assets/`: 폰트, 아이콘 및 라이선스
- `prototype/tools/preview-v7.cjs`: 로컬 미리보기 서버

`node prototype/tools/preview-v7.cjs` 실행 후 http://127.0.0.1:8767/ 에서 작업본을 확인합니다.
이후 작업본 수정은 `prototype/index.html`에서 진행합니다.

메인 반영 요청을 받으면 확정된 HTML과 필요한 자산을 루트에 적용하고 경로를 확인합니다.
