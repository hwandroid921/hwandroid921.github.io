from pathlib import Path
from docx import Document
from docx.shared import Inches, Pt, RGBColor
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
from docx.opc.constants import RELATIONSHIP_TYPE as RT

out = Path('C:/siyeon/hwandroid/docs/공고')
out.mkdir(parents=True, exist_ok=True)
jobs = [
('오픈웍스', '신입 백엔드 개발자', '경기 남양주시 다산순환로 20, B동 425호', '학력·전공 무관', '신입', '2026년 12월 6일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?rec_idx=55501831&referNonce=56e4fdd6f60f9c9d9539&t_category=job_recruit&t_content=SRI_M_050_2LV-JOB_MIX_RCT_NONMEM'),
('주식회사 애드스퀘어', '풀스택 개발자 채용 (신입/경력 · AI 실무 교육 지원)', '경기 화성시 동탄구 동탄대로 635, 1422호', '학력·전공 무관', '신입·경력 무관', '2026년 12월 19일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?cn=job-industry&cns=recently-list&rec_idx=55615207&referNonce=&t_category=job_recruit&t_content=generic'),
('와이그라운드', 'JAVA 백엔드 개발자', '서울 송파구 중대로9길 35', '학력무관', '경력무관 (신입 지원 가능)', '2026년 12월 17일 23:59', '사람인', 'https://www.saramin.co.kr/zf_user/jobs/view?rec_idx=55597382'),
('(주)아랑소프트', 'SI개발·웹개발·백엔드/서버개발', '서울 구로구 디지털로 242, 한화비즈메트로 1차 1005호', '학력무관', '신입', '2026년 11월 30일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?cn=job-industry&cns=recently-list&rec_idx=55374469&referNonce=&t_category=job_recruit&t_content=generic'),
('주식회사 서우컴퍼니', 'SI/SM 신입 웹개발자 (JAVA, JSP)', '서울 구로구 디지털로33길 11, 1005호', '고등학교 졸업 이상', '신입·경력 (년수무관)', '2026년 12월 20일 23:59', '사람인', 'https://m.saramin.co.kr/job-search/view?cn=job-industry&cns=recently-list&rec_idx=55629125&referNonce=&t_category=job_recruit&t_content=generic'),
('㈜제이시큐어플랫폼', 'Java 백엔드 신입 개발자 (보안솔루션)', '경기 성남시 수정구 금토로80번길 37, 인피니티타워 W동 6층', '학력무관', '신입 (동일 공고 내 경력직 포지션은 별도)', '2026년 12월 25일 (채용 시 조기마감)', '잡코리아', 'https://m.jobkorea.co.kr/Recruit/GI_Read/50428880?sc=640'),
('㈜위니언', '풀스택 개발자', '서울 강남구 봉은사로16길 10 (역삼동)', '학력무관', '경력무관 (신입 포함)', '2026년 12월 3일', '잡코리아', 'https://www.jobkorea.co.kr/Recruit/GI_Read/50296518?Oem_Code=C1&listno=370&logpath=1&sc=630&stext=%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C'),
]

doc=Document()
for border in list(doc.styles.element.iter(qn('w:pBdr'))):
    border.getparent().remove(border)
sec=doc.sections[0]
sec.page_width=Inches(8.5); sec.page_height=Inches(11)
sec.top_margin=Inches(.85); sec.bottom_margin=Inches(.85)
sec.left_margin=sec.right_margin=Inches(.9)
for name in ['Normal','Title','Heading 1','Heading 2']:
    st=doc.styles[name]
    st.font.name='맑은 고딕'
    st._element.get_or_add_rPr().rFonts.set(qn('w:eastAsia'),'맑은 고딕')
    st.font.color.rgb=RGBColor(0,0,0)
doc.styles['Normal'].font.size=Pt(12)
doc.styles['Normal'].paragraph_format.line_spacing=1.25
doc.styles['Normal'].paragraph_format.space_after=Pt(7)
doc.styles['Title'].font.size=Pt(24)

for i, (company,role,location,education,experience,deadline,site,url) in enumerate(jobs):
    if i: doc.add_page_break()
    doc.add_paragraph('개발 직무 채용 공고', 'Title')
    p=doc.add_paragraph(f'{i+1:02d} / 07    ·    정보 확인일 2026년 11월 27일')
    p.runs[0].font.size=Pt(10)
    p.paragraph_format.space_after=Pt(24)
    fields=[('기업명',company),('직무',role),('근무지',location),('요구학력, 경력',f'학력: {education}\n경력: {experience}'),('마감일',deadline)]
    for label,value in fields:
        p=doc.add_paragraph()
        p.paragraph_format.space_before=Pt(12)
        p.paragraph_format.space_after=Pt(4)
        p.paragraph_format.keep_with_next=True
        r=p.add_run(label);r.bold=True;r.font.size=Pt(11)
        doc.add_paragraph(value)
    p=doc.add_paragraph(); p.paragraph_format.space_before=Pt(12)
    p.paragraph_format.keep_with_next=True
    p.add_run('해당 공고 링크').bold=True
    p=doc.add_paragraph()
    link=OxmlElement('w:hyperlink')
    link.set(qn('r:id'),p.part.relate_to(url,RT.HYPERLINK,is_external=True))
    r=OxmlElement('w:r'); rp=OxmlElement('w:rPr')
    color=OxmlElement('w:color');color.set(qn('w:val'),'0563C1');rp.append(color)
    u=OxmlElement('w:u');u.set(qn('w:val'),'single');rp.append(u)
    r.append(rp);t=OxmlElement('w:t');t.text=f'{site}에서 공고 열기';r.append(t);link.append(r);p._p.append(link)
    p=doc.add_paragraph('마감일은 기업 사정에 따라 변경되거나 조기 마감될 수 있습니다.')
    p.paragraph_format.space_before=Pt(22)
    p.runs[0].font.size=Pt(10)
doc.core_properties.title='개발 직무 채용 공고 7건'
doc.core_properties.subject='신입 및 경력무관 개발 직무 채용 공고'
path=out/'개발직무_채용공고_7건_2026-11-27.docx'
doc.save(path)
print(path)
