from pathlib import Path
from docx import Document
from docx.shared import Mm, Pt, RGBColor
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.opc.constants import RELATIONSHIP_TYPE as RT

OUT = Path('C:/siyeon/hwandroid/docs/공고/개발직_채용공고_2026-11-27.docx')
jobs = [
('오픈웍스', '신입 백엔드 개발자', '경기 남양주시 다산순환로 20, B동 425호', '학력·전공 무관 / 신입', '2026년 12월 6일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?rec_idx=55501831&referNonce=56e4fdd6f60f9c9d9539&t_category=job_recruit&t_content=SRI_M_050_2LV-JOB_MIX_RCT_NONMEM'),
('애드스퀘어', '풀스택 개발자 채용 신입 및 경력', '경기 화성시 동탄구 동탄대로 635, 1422호', '학력·전공 무관 / 신입·경력 연차무관', '2026년 12월 19일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?cn=job-industry&cns=recently-list&rec_idx=55615207&referNonce=&t_category=job_recruit&t_content=generic'),
('와이그라운드', 'JAVA 백엔드 개발자', '서울 송파구 중대로9길 35', '학력무관 / 경력무관 신입 지원 가능', '2026년 12월 17일 23:59', '사람인', 'https://www.saramin.co.kr/zf_user/jobs/view?rec_idx=55597382'),
('아랑소프트', 'SI 개발 및 웹 개발 및 백엔드 서버 개발', '서울 구로구 디지털로 242, 한화비즈메트로 1차 1005호', '학력무관 / 신입', '2026년 11월 30일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?cn=job-industry&cns=recently-list&rec_idx=55374469&referNonce=&t_category=job_recruit&t_content=generic'),
('서우컴퍼니', 'SI 및 SM 신입 웹개발자 JAVA JSP', '서울 구로구 디지털로33길 11, 1005호', '고등학교 졸업 이상 / 신입·경력 연차무관', '2026년 12월 20일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?cn=job-industry&cns=recently-list&rec_idx=55629125&referNonce=&t_category=job_recruit&t_content=generic'),
('제이시큐어플랫폼', 'Java 백엔드 신입 개발자 보안솔루션', '경기 성남시 수정구 금토로80번길 37, 인피니티타워 W동 6층', '학력무관 / 신입 포지션', '2026년 12월 25일\n채용 시 조기마감', '잡코리아', 'https://m.jobkorea.co.kr/Recruit/GI_Read/50428880?sc=640'),
('위니언', '풀스택 개발자', '서울 강남구 봉은사로16길 10', '학력무관 / 경력무관 신입 포함', '2026년 12월 3일', '잡코리아', 'https://www.jobkorea.co.kr/Recruit/GI_Read/50296518?Oem_Code=C1&listno=370&logpath=1&sc=630&stext=%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C'),
]

d = Document()
s = d.sections[0]
s.page_width, s.page_height = Mm(210), Mm(297)
s.top_margin = s.bottom_margin = Mm(23)
s.left_margin = s.right_margin = Mm(25)
for name in ['Normal','Title','Heading 1','Heading 2']:
    st = d.styles[name]
    st.font.name = '맑은 고딕'
    st.font.color.rgb = RGBColor(0,0,0)
    st.element.get_or_add_rPr().rFonts.set(qn('w:eastAsia'), '맑은 고딕')
d.styles['Normal'].font.size = Pt(12)
d.styles['Normal'].paragraph_format.line_spacing = 1.25
d.styles['Normal'].paragraph_format.space_after = Pt(9)
d.styles['Title'].font.size = Pt(24)
d.styles['Title'].paragraph_format.space_after = Pt(8)

def field(label, value):
    p = d.add_paragraph()
    p.paragraph_format.space_before = Pt(14)
    p.paragraph_format.space_after = Pt(4)
    p.paragraph_format.keep_with_next = True
    r = p.add_run(label)
    r.bold = True
    r.font.size = Pt(10)
    p = d.add_paragraph(value)
    p.paragraph_format.keep_together = True
    return p

for i, (company, role, address, req, deadline, site, url) in enumerate(jobs):
    if i:
        d.add_page_break()
    p = d.add_paragraph(f'개발직 채용공고 {i+1:02d}', 'Title')
    p = d.add_paragraph('확인 기준 2026년 11월 27일')
    p.runs[0].font.size = Pt(10)
    p.runs[0].font.color.rgb = RGBColor.from_string('666666')
    field('기업명', company)
    field('직무', role)
    field('근무지', address)
    field('요구학력 및 경력', req)
    field('마감일', deadline)
    p = field('해당 공고 링크', '')
    link = OxmlElement('w:hyperlink')
    link.set(qn('r:id'), p.part.relate_to(url, RT.HYPERLINK, is_external=True))
    r = OxmlElement('w:r')
    pr = OxmlElement('w:rPr')
    color = OxmlElement('w:color'); color.set(qn('w:val'), '0563C1'); pr.append(color)
    underline = OxmlElement('w:u'); underline.set(qn('w:val'), 'single'); pr.append(underline)
    r.append(pr)
    t = OxmlElement('w:t'); t.text = f'{site} 공고 바로가기'; r.append(t)
    link.append(r); p._p.append(link)
    p = d.add_paragraph('마감일은 기업 사정에 따라 변경되거나 조기 종료될 수 있습니다.')
    p.paragraph_format.space_before = Pt(18)
    p.runs[0].font.size = Pt(9)
    p.runs[0].font.color.rgb = RGBColor.from_string('666666')

d.core_properties.title = '개발직 채용공고 2026년 11월 27일'
d.core_properties.subject = '신입 및 경력무관 개발직 채용공고 7건'
d.core_properties.author = ''
d.save(OUT)
print(OUT)
print(f'공고 {len(jobs)}개 / 하이퍼링크 {len(jobs)}개')
