from pathlib import Path
from copy import deepcopy
from docx import Document
from docx.oxml import OxmlElement
from docx.oxml.ns import qn
import shutil

root = Path(__file__).resolve().parents[2]
base = root / 'docs/국취제'
qa = root / 'prototype/review/company-resumes/split'
qa.mkdir(parents=True, exist_ok=True)

for company in ['리덕트', '디비엔텍']:
    source = base / f'유환희_이력서_및_자기소개서_{company}.docx'
    shutil.copy2(source, qa / source.name)
    doc = Document(source)
    changes = []
    # Change text runs in place so tables, pictures, and paragraph formatting survive.
    for paragraph in list(doc.paragraphs) + [p for table in doc.tables for row in table.rows for cell in row.cells for p in cell.paragraphs]:
        for old, new in [('26세', '27세'), ('2016. 07. 02.', '2026. 07. 02.')]:
            if old in paragraph.text:
                for run in paragraph.runs:
                    if old in run.text:
                        run.text = run.text.replace(old, new)
                        changes.append(old)
                if old in paragraph.text:
                    # Old date/age may be split between runs; retain first run formatting.
                    text = paragraph.text.replace(old, new)
                    paragraph.runs[0].text = text
                    for run in paragraph.runs[1:]:
                        run.text = ''
                    changes.append(old)
        if paragraph.text.strip() == 'OA':
            paragraph.runs[0].text = '보유 기술'
            for run in paragraph.runs[1:]:
                run.text = ''
    text = '\n'.join(p.text for t in doc.tables for row in t.rows for c in row.cells for p in c.paragraphs)
    assert '27세' in text and '2026. 07. 02.' in text
    assert '2016. 07. 02.' not in text
    doc.save(source)
    body = doc.element.body
    children = list(body)
    marker = next(i for i, el in enumerate(children) if el.tag == qn('w:p') and ''.join(el.itertext()).strip().startswith('자기소개서'))
    # Keep the declaration with each standalone document.
    declaration = next(i for i, el in enumerate(children) if el.tag == qn('w:p') and '위에 기재한 사항은' in ''.join(el.itertext()))
    for kind in ['이력서', '자기소개서']:
        output = Document(source)
        elements = list(output.element.body)
        keep = set(range(marker)) | set(range(declaration, len(elements))) if kind == '이력서' else set(range(marker, len(elements)))
        for i, element in enumerate(elements):
            if element.tag != qn('w:sectPr') and i not in keep:
                output.element.body.remove(element)
        if kind == '이력서':
            # Remove empty page-break paragraphs after the last project table.
            nodes = list(output.element.body)
            last_table = max(i for i, el in enumerate(nodes) if el.tag == qn('w:tbl'))
            for el in nodes[last_table + 1:]:
                if el.tag == qn('w:p') and not ''.join(el.itertext()).strip():
                    output.element.body.remove(el)
        else:
            title = output.paragraphs[0]
            title.text = f'{company} 지원 자기소개서'
            title.paragraph_format.page_break_before = False
            for br in list(title._p.iter(qn('w:br'))):
                br.getparent().remove(br)
            identity = OxmlElement('w:p')
            run = OxmlElement('w:r')
            text_el = OxmlElement('w:t')
            text_el.text = f'지원자 유환희 | 지원기업 {company}'
            run.append(text_el)
            identity.append(run)
            title._p.addnext(identity)
        dest = base / f'유환희_{company}_{kind}.docx'
        output.save(dest)
        print(dest.name)
    print(company, 'corrected age/date and skill heading')
