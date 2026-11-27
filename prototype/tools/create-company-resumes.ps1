$ErrorActionPreference = 'Stop'
$taskRoot = 'C:\siyeon\hwandroid'
$taskSource = Join-Path $taskRoot 'docs\국취제\유환희 이력서 및 자기소개서.doc'
$taskQa = Join-Path $taskRoot 'prototype\review\company-resumes'
New-Item -ItemType Directory -Path $taskQa -Force | Out-Null
$taskHashBefore = (Get-FileHash -LiteralPath $taskSource).Hash
$taskApplications = @(
    @{
        Company = '리덕트'
        Role = '웹·앱 및 AI 서비스 개발자'
        Summary = @('Spring Boot와 Vue를 사용해 화면, API, 데이터베이스를 연결하는 웹 서비스를 개발했습니다.', '팀 프로젝트에서 백엔드 설계와 Docker 기반 실행 환경 구성을 맡았고, FastAPI 기반 번호판 인식 기능을 연동했습니다.', '팀 리더로 기획과 역할 분담을 조율하고, 구현 내용을 기록하며 협업했습니다.')
        Motivation = @'
리덕트의 웹·앱 및 AI 서비스 개발 공고에서 화면과 백엔드, 데이터베이스부터 AI 기능 연동까지 서비스 전반을 다루는 업무에 관심을 갖고 지원했습니다. 저는 Spring Boot, Vue, PostgreSQL을 사용한 팀 프로젝트를 통해 사용자 화면의 요청이 API와 데이터 저장으로 이어지는 과정을 구현했습니다. Docker와 Nginx로 실행 환경을 구성하고 AWS에 배포한 경험도 있습니다.
화물차 운전자 피로도 모니터링 프로젝트에서는 팀장을 맡아 기획과 설계를 진행하고 백엔드와 AI 기반 번호판 인식 기능을 담당했습니다. FastAPI, YOLO11, easyOCR을 사용한 기능을 서비스 흐름에 연결하면서 분석 기능 자체뿐 아니라 백엔드와 주고받는 데이터도 함께 살펴봤습니다. 제주 문화관광 프로젝트에서는 팀 리더로 백엔드 설계와 인프라 관리, 협업을 맡았습니다. 이런 경험을 바탕으로 여러 영역의 연결을 이해하고 맡은 기능을 구현하는 데 기여하고 싶습니다.
입사 후에는 먼저 기존 서비스와 개발 환경을 익히고, 기능을 작은 단위로 나누어 구현 결과를 확인하겠습니다. 제가 경험한 Vue와 Spring Boot를 토대로 업무에 필요한 프레임워크를 학습하고, 웹 개발 경험을 넓히겠습니다. LLM과 모바일 앱, IoT 연동은 기존 번호판 인식 프로젝트와 다른 학습이 필요한 영역으로 이해하고 있습니다. 익숙하지 않은 기술은 확인한 내용과 모르는 부분을 구분해 공유하며 팀과 함께 해결하겠습니다.
'@
    },
    @{
        Company = '디비엔텍'
        Role = 'AI 활용 기반 웹개발자 (JAVA/Spring)'
        Summary = @('Java와 Spring Boot를 중심으로 REST API와 PostgreSQL 기반 웹 서비스를 개발했습니다.', '인증 기능과 데이터 처리, Vue 화면 연동을 구현하고 Docker·Nginx·AWS를 활용한 배포를 경험했습니다.', 'GitHub로 코드를 관리하고 팀 리더로 기획과 역할 분담, 진행 상황 공유를 맡았습니다.')
        Motivation = @'
디비엔텍의 Java/Spring Boot 기반 제품 웹개발과 공공·SI 시스템 개발 업무에 관심을 갖고 지원했습니다. 교육과 팀 프로젝트에서 Spring Boot로 백엔드 기능을 구현하고 Vue 화면, PostgreSQL 데이터베이스와 연결했습니다. 온라인 서점 프로젝트에서는 로그인과 회원가입, 마이페이지 등 사용자 인증 기능을 맡았고, Docker로 실행 환경을 구성해 배포와 발표를 진행했습니다.
화물차 운전자 피로도 모니터링 프로젝트에서는 팀장을 맡아 기획과 설계를 진행하고 백엔드와 AI 기반 번호판 인식 기능을 담당했습니다. 요구사항을 기능으로 나누고 화면과 API, 데이터의 흐름을 함께 살피면서 구현했습니다. GitHub의 브랜치와 버전 관리, README 작성, Notion과 Figma를 통한 기록도 경험했습니다. 맡은 기능을 설명하고 진행 상황을 공유하는 습관을 바탕으로 팀의 개발 방식에 적응하겠습니다.
공고에 명시된 AI 도구 활용과 생성 코드 검토 역량도 더 익히고 싶습니다. AI가 제안하는 코드는 요구사항과 기존 코드에 맞는지 확인하고, 실행 결과와 오류를 검토한 뒤 적용하는 원칙으로 업무에 임하겠습니다. 입사 후에는 회사의 제품 구조와 개발 규칙을 익혀 작은 기능 개선과 버그 수정부터 맡겠습니다. 전자정부 프레임워크와 업무에 필요한 데이터베이스·배포 도구를 학습하고, 고객사의 요구사항과 문제 상황을 정확하게 기록해 팀과 공유하겠습니다.
'@
    }
)
$taskWord = New-Object -ComObject Word.Application
$taskWord.Visible = $false
$taskWord.DisplayAlerts = 0
try {
    foreach ($taskApplication in $taskApplications) {
        $taskDocument = $taskWord.Documents.Open($taskSource, $false, $true)
        try {
            $taskChanged = 0
            foreach ($taskTable in $taskDocument.Tables) {
                foreach ($taskCell in $taskTable.Range.Cells) {
                    $taskText = $taskCell.Range.Text.Trim([char]13, [char]7, [char]32)
                    $taskReplacement = $null
                    if ($taskText -eq '웹 개발자') { $taskReplacement = $taskApplication.Role }
                    elseif ($taskText -eq '풀스택 기반 웹 개발자를 지향하고 있습니다.') { $taskReplacement = $taskApplication.Summary[0] }
                    elseif ($taskText.StartsWith('Spring Boot를 중심으로 REST API')) { $taskReplacement = $taskApplication.Summary[1] }
                    elseif ($taskText.StartsWith('사람과의 소통과 협력을 우선')) { $taskReplacement = $taskApplication.Summary[2] }
                    elseif ($taskText.StartsWith('저는 웹 서비스가 하나의 화면')) { $taskReplacement = $taskApplication.Motivation.Trim().Replace("`r`n", "`r").Replace("`n", "`r") }
                    if ($null -ne $taskReplacement) {
                        $taskRange = $taskCell.Range.Duplicate
                        $taskRange.End -= 1
                        $taskRange.Text = $taskReplacement + "`r"
                        $taskChanged++
                    }
                }
            }
            if ($taskChanged -ne 5) { throw "Unexpected edited cell count: $taskChanged" }
            # Date of this company-specific draft. Keep education dates as written pending verification.
            $taskFind = $taskDocument.Content.Find
            $taskFind.ClearFormatting()
            $taskFind.Replacement.ClearFormatting()
            [void]$taskFind.Execute('2024년    10월    20일', $false, $false, $false, $false, $false, $true, 1, $false, '2026년    11월    12일', 2)
            $taskOutput = Join-Path $taskRoot ('docs\국취제\유환희_이력서_및_자기소개서_' + $taskApplication.Company + '.docx')
            if (Test-Path -LiteralPath $taskOutput) { throw "Output exists: $taskOutput" }
            $taskDocument.SaveAs2($taskOutput, 16)
            $taskDocument.Repaginate()
            $taskDocument.ExportAsFixedFormat((Join-Path $taskQa ($taskApplication.Company + '.pdf')), 17)
            Write-Output "$($taskApplication.Company): $($taskDocument.ComputeStatistics(2)) pages, $taskChanged cells edited"
        } finally { $taskDocument.Close(0) }
    }
} finally { $taskWord.Quit() }
if ((Get-FileHash -LiteralPath $taskSource).Hash -ne $taskHashBefore) { throw 'Source modified' }
Write-Output 'PASS: Original preserved.'
