# 이력서 양식

개발자 자유양식 이력서에 사용하는 1~2페이지 단일 열 형식이다. 회사가 자사 양식을 제공하면 동일한 내용을 자사 양식의 해당 항목으로 옮긴다.

## 백엔드 지원용

### 기본 정보

```text
유환희
Java/Spring Boot 기반 주니어 백엔드 개발자

전화번호: [입력]
이메일: olgksgml@gmail.com
GitHub: https://github.com/hwandroid921
Portfolio: https://hwandroid921.github.io/
Blog: https://velog.io/@hwandroid921

거주지: 인천광역시
근무 가능 지역: 서울·인천·경기 / 타지역 이전 협의 가능
```

### 개발자 소개

```text
Java와 Spring Boot를 중심으로 REST API, 인증·인가, 데이터베이스 기반
웹 서비스를 개발했습니다.

LogMile 프로젝트에서 팀장과 백엔드 개발을 담당하여 JWT 인증,
운행 기록, 피로도 계산 및 FastAPI 기반 OCR 연동을 구현했습니다.

Vue·React 프로젝트 경험을 바탕으로 프론트엔드의 요구사항을 이해하며
API와 데이터 구조를 설계할 수 있습니다.
```

### 기술

```text
Backend
- Java 21, Spring Boot 3
- Spring Security, JWT
- Spring Data JPA
- FastAPI

Database
- PostgreSQL
- 엔티티 관계 설계, JOIN, 트랜잭션, 페이지네이션
- [완료 후 추가] 인덱스 및 실행 계획 분석

Frontend
- Vue 3, Pinia, Vue Router, Axios
- React, TypeScript, Electron

Infrastructure
- Docker, Docker Compose
- Nginx, AWS
- GitHub Actions

Collaboration
- GitHub, Notion, Figma
```

### 프로젝트 1 — LogMile

```text
LogMile | 운행 기록과 운전자 피로도를 관리하는 B2B 웹 서비스
기간: 2026.04.27~2026.06.01
구성: 2인 팀
담당: 팀장, 백엔드, AI 서버 연동

- Spring Boot 기반 운행 기록 및 피로도 관리 REST API 설계
- Spring Security와 JWT 기반 인증·인가 구현
- PostgreSQL 엔티티 및 연관관계 설계
- FastAPI 기반 번호판 OCR 서버 연동
- 피로도 계산 및 휴식 권고 비즈니스 로직 구현
- Docker 기반 애플리케이션 실행 환경 구성

문제 해결
- [GPS 중복·역순 데이터 문제와 처리 방법]
- [OCR 실패·지연 상황과 예외 처리 방법]

검증 및 결과
- [JUnit 테스트 대상 및 테스트 수]
- [성능 개선 전후 측정값]
- [배포 주소 또는 데모 영상]

기술: Java 21, Spring Boot, Spring Security, JPA, PostgreSQL,
FastAPI, Vue 3, Docker
GitHub: https://github.com/hwandroid921/logmile
```

### 프로젝트 2 — TokenMonitor

```text
TokenMonitor | AI 서비스 사용량을 로컬에서 확인하는 Windows 애플리케이션
구성: 개인 프로젝트
담당: 기획, 설계, 개발, 패키징

- Electron과 React, TypeScript 기반 데스크톱 UI 구현
- ChatGPT·Claude·Gemini 사용량 데이터 통합
- 서비스마다 다른 응답을 공통 데이터 구조로 변환
- 사용자 세션과 데이터를 로컬 환경에서 처리
- Windows 배포 패키지 구성

문제 해결
- [서로 다른 응답 형식을 정규화한 방식]
- [민감정보 저장·노출을 줄인 방식]

GitHub: https://github.com/hwandroid921/TokenMonitor
```

### 프로젝트 3 — ReadMe

```text
ReadMe | 상품 탐색부터 주문·결제로 이어지는 쇼핑몰 서비스
기간: 2026.03.13~2026.04.12
구성: 3인 팀
담당: Vue 프론트엔드 전체

- Vue 3 기반 사용자·관리자 화면 구현
- Pinia를 이용한 장바구니 및 사용자 상태관리
- Router Guard 기반 페이지 접근 제어
- Axios 기반 백엔드 REST API 연동
- 주문·결제 상태에 따른 화면 흐름 구현

GitHub: https://github.com/hwandroid921/readme
```

### 교육·학력·자격

```text
교육
MBC아카데미 스마트 모빌리티 DX 개발자 과정
2025.12~2026.07
- Java, Spring Boot, Vue, PostgreSQL 기반 풀스택 교육
- 팀 프로젝트 및 Docker·AWS 배포 경험

학력
한국공학대학교 게임공학과 중퇴
2018.03~2023.05

학사학위 취득 준비 중
- 학점은행제 또는 사이버대학교 과정 검토 중

자격증
[정확한 자격증명] | [발급기관] | [취득일]
```

## 풀스택·SaaS 지원용

기본 정보와 교육·학력은 백엔드 지원용과 동일하게 사용한다.

### 제목

```text
유환희
Java/Spring Boot·Vue 기반 주니어 풀스택 개발자
```

### 개발자 소개

```text
Spring Boot 기반 REST API와 PostgreSQL 데이터베이스를 구현하고,
Vue·React로 사용자 화면까지 연결해 본 주니어 풀스택 개발자입니다.

쇼핑몰 프로젝트에서는 Vue 프론트엔드 전체를 담당했고,
LogMile 프로젝트에서는 팀장과 백엔드 개발을 담당했습니다.

프론트엔드와 백엔드 양쪽의 요구사항을 이해하며
서비스 전체 흐름을 구현하는 것이 강점입니다.
```

### 프로젝트 순서

1. ReadMe
2. LogMile
3. TokenMonitor

## 작성 시 제외할 항목

- 성장 과정 장문
- 주민등록번호, 가족관계, 전체 집 주소
- 신장, 체중, 혈액형
- 기술별 임의의 숙련도 퍼센트
- 실제로 사용하지 않은 기술
- 팀 전체 구현을 본인의 구현으로 표현한 문장
- 근거가 없는 사용자 수와 성능 수치
