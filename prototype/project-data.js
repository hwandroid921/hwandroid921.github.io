// Copy is drawn from the existing portfolio. No unverified metrics added.
window.portfolioProjects = [
  {
    "id": "jeju",
    "title": "제주 문화관광 페이지",
    "summary": "관광지 탐색부터 회원 인증, 리뷰와 문의까지 이어지는 문화관광 안내 서비스입니다.",
    "meta": [
      {
        "label": "담당 작업",
        "value": "인증, 게시글, 리뷰 흐름 구현과 사용자 동작에 따른 데이터 저장 과정 정리"
      },
      {
        "label": "구현 영역",
        "value": "웹 화면, API, 회원 인증, 게시글 및 리뷰 데이터 처리"
      }
    ],
    "requirement": "관광 정보를 탐색한 사용자가 회원 인증을 거쳐 리뷰를 작성하거나 게시판으로 문의할 수 있어야 합니다.",
    "features": [
      "관광지 정보 탐색",
      "회원 인증과 리뷰 작성",
      "게시판을 통한 문의"
    ],
    "implementation": "인증, 게시글, 리뷰 기능을 각각 구현하되 사용자가 이동하는 순서를 기준으로 API와 화면을 연결했습니다. 사용자 동작이 데이터 저장으로 이어지는 과정을 함께 정리했습니다.",
    "flow": [
      "관광지 탐색",
      "회원 인증",
      "리뷰 작성",
      "게시판 문의"
    ],
    "challenge": "기능별로 흩어진 사용자 흐름 연결",
    "resolution": [
      {
        "label": "문제",
        "value": "관광 정보, 인증, 리뷰와 문의를 개별 기능으로만 구성하면 서비스 전체의 화면 이동과 데이터 흐름을 파악하기 어렵습니다."
      },
      {
        "label": "접근",
        "value": "사용자 시나리오를 기준으로 API와 화면 이동을 정리하고, 인증·게시글·리뷰의 저장 흐름을 연결했습니다."
      },
      {
        "label": "구현 내용",
        "value": "관광지 탐색과 사용자 참여 기능을 하나의 서비스 흐름으로 구성했습니다."
      }
    ],
    "repo": "https://github.com/hwandroid921/Jeju",
    "image": "images/projects/jeju-project.png",
    "focus": "인증과 사용자 참여 흐름"
  },
  {
    "id": "bookstore",
    "title": "온라인 서점 ReadMe",
    "summary": "도서 조회, 장바구니, 주문과 주문 내역으로 이어지는 구매 과정을 구현한 온라인 서점입니다.",
    "meta": [
      {
        "label": "담당 작업",
        "value": "Vue 3 화면 구현과 Pinia 상태 관리를 중심으로 구매 과정 구성"
      },
      {
        "label": "확인된 기술",
        "value": "Vue 3, Pinia"
      }
    ],
    "requirement": "사용자가 도서를 조회하고 상세 정보를 확인한 뒤, 장바구니와 주문 과정을 거쳐 주문 내역을 확인할 수 있어야 합니다.",
    "features": [
      "도서 목록 및 상세 조회",
      "장바구니와 주문·결제 흐름",
      "주문 내역 확인"
    ],
    "implementation": "Vue 3로 구매 단계별 화면을 구성하고 Pinia로 상태를 관리했습니다. 화면 단위 상태와 요청 흐름을 분리해, 장바구니에서 주문으로 이어지는 과정을 정리했습니다.",
    "flow": [
      "도서 조회",
      "상세 확인",
      "장바구니",
      "주문",
      "주문 내역"
    ],
    "challenge": "장바구니와 주문 단계의 상태 분리",
    "resolution": [
      {
        "label": "문제",
        "value": "구매 과정에서는 화면 상태와 요청 흐름이 섞이지 않도록 관리해야 했습니다. 특히 장바구니에서 주문으로 이동하는 단계가 주요 고려 사항이었습니다."
      },
      {
        "label": "접근",
        "value": "Vue 3 화면과 Pinia 상태 관리를 바탕으로 화면 단위 상태와 요청 흐름을 분리했습니다."
      },
      {
        "label": "구현 내용",
        "value": "도서 탐색, 장바구니, 주문 및 내역 확인을 구매 순서에 맞게 구성했습니다."
      }
    ],
    "repo": "https://github.com/hwandroid921/readme",
    "image": "images/projects/readme-project.png",
    "focus": "Vue 화면과 구매 상태 관리"
  },
  {
    "id": "logmile",
    "title": "LogMile",
    "summary": "주행 상태를 수집하고 이상 상태를 판단해, 화물차 운전자 피로도를 모니터링하는 플랫폼입니다.",
    "meta": [
      {
        "label": "담당 작업",
        "value": "상태 데이터 흐름과 모니터링 화면 연결, 백엔드와 AI 분석 API 연동 구조 정리"
      },
      {
        "label": "확인된 기술",
        "value": "FastAPI 연동"
      }
    ],
    "requirement": "수집한 주행 상태를 분석하고, 판단 결과를 모니터링 화면에서 이해할 수 있는 정보로 제공해야 합니다.",
    "features": [
      "주행 상태 데이터 수집",
      "이상 상태 판단과 AI 분석 API 연동",
      "모니터링 화면을 통한 결과 확인"
    ],
    "implementation": "데이터 수집, 분석과 화면 표시를 나누어 흐름을 구성했습니다. FastAPI 연동을 포함한 백엔드·AI 분석 API 구조를 정리하고, 상태 데이터와 모니터링 화면을 연결했습니다.",
    "flow": [
      "상태 수집",
      "이상 판단 및 분석",
      "모니터링",
      "결과 확인"
    ],
    "challenge": "분석 결과를 모니터링 정보로 전달",
    "resolution": [
      {
        "label": "문제",
        "value": "상태 데이터를 수집하는 것만으로는 사용자가 판단 결과를 이해하기 어렵습니다. 분석 과정과 화면 표시를 연결해야 했습니다."
      },
      {
        "label": "접근",
        "value": "수집, 분석, 표시 단계를 구분하고 각 단계의 데이터 흐름을 정리했습니다."
      },
      {
        "label": "구현 내용",
        "value": "주행 상태 수집부터 이상 상태 판단과 결과 확인까지 모니터링 흐름을 구성했습니다."
      }
    ],
    "repo": "https://github.com/hwandroid921/logmile",
    "image": "images/projects/logmile-project.png",
    "focus": "분석 API와 모니터링 연결"
  },
  {
    "id": "tokenmonitor",
    "title": "TokenMonitor",
    "summary": "여러 AI 개발 도구의 플랜, 잔여 사용량과 초기화 시간을 한 화면에서 확인하는 개인용 대시보드입니다.",
    "meta": [
      {
        "label": "담당 작업",
        "value": "개인 사용 흐름 정의, 도구별 상태를 비교할 수 있는 UI 구성"
      },
      {
        "label": "실행 환경",
        "value": "Windows 데스크톱"
      },
      {
        "label": "표시 대상",
        "value": "ChatGPT, Claude, Antigravity"
      }
    ],
    "requirement": "여러 도구의 사용량 확인 화면을 각각 방문하지 않고도 주요 상태를 비교할 수 있어야 합니다.",
    "features": [
      "도구별 플랜과 잔여 사용량 표시",
      "초기화 시간과 상태 갱신",
      "사용량 확인 링크 연결과 Windows 실행"
    ],
    "implementation": "개인적으로 필요한 사용량 확인 흐름에서 출발해 도구별 상태를 대시보드에 모았습니다. Windows 화면에서 플랜, 잔여 사용량과 초기화 시간을 비교하고 갱신하거나 관련 링크로 이동하도록 구성했습니다.",
    "flow": [
      "도구별 사용량 확인",
      "상태 비교",
      "갱신 및 외부 링크 이동"
    ],
    "challenge": "흩어진 AI 도구 사용량 정보 통합",
    "resolution": [
      {
        "label": "문제",
        "value": "AI 도구마다 사용량 확인 위치가 달라 잔여 사용량과 초기화 시간을 한 번에 비교하기 어려웠습니다."
      },
      {
        "label": "접근",
        "value": "도구별 주요 상태를 같은 대시보드에서 확인할 수 있도록 UI를 구성했습니다."
      },
      {
        "label": "구현 내용",
        "value": "사용량, 초기화 시간과 확인 링크를 한 화면에서 다루는 개인용 Windows 도구를 구현했습니다."
      }
    ],
    "repo": "https://github.com/hwandroid921/TokenMonitor",
    "image": "images/projects/token-monitor-project.png",
    "focus": "개인 사용 흐름과 데스크톱 UI"
  }
];
