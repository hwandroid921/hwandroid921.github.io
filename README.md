# Hwandroid Portfolio

신입 풀스택 웹 개발자 유환희의 포트폴리오 웹사이트입니다.

현재 사이트는 **Portfolio v2**를 기준으로 관리합니다. Java와 Spring Boot 기반의 백엔드, Vue 3 기반의 프론트엔드, PostgreSQL과 Docker를 활용한 프로젝트 경험을 소개합니다.

## 주요 구성

- 소개 및 이력
- 기술 스택과 성장 과정
- 제주 문화 관광 서비스
- 온라인 서점 ReadMe
- 모빌리티 모니터링 플랫폼 LogMile
- AI 도구 사용량 대시보드 TokenMonitor
- 이메일, GitHub, 기술 블로그 연락처

## 기술 구성

- HTML5
- Tailwind CSS CDN
- Google Fonts
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
    ├── Hwan.png
    └── TokenMonitor.png
```

## 로컬 확인

정적 파일이므로 `index.html`을 직접 열거나 로컬 HTTP 서버로 실행할 수 있습니다.

```bash
python -m http.server 8000
```

실행 후 브라우저에서 `http://localhost:8000`으로 접속합니다.

## 브랜치 운영 기준

- `main`: Portfolio v2 기반의 배포 기준 브랜치
- 기능 수정: 별도 작업 브랜치에서 진행 후 `main`에 반영
- v1: 과거 버전 보관용이며 신규 작업 기준으로 사용하지 않음

## 링크

- Website: https://hwandroid921.github.io/
- GitHub: https://github.com/hwandroid921
- Blog: https://velog.io/@hwandroid921
- Email: olgksgml@gmail.com
