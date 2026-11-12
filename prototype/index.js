document.getElementById('print-portfolio')?.addEventListener('click', () => {
  window.print();
});

// Query-based pages keep index.html as the prototype's only HTML entry point.
// React migration: replace this renderer with ProjectDetailPage and route params.
(() => {
  const id = new URLSearchParams(location.search).get('project');
  if (id === null) return;
  const projects = window.portfolioProjects || [];
  const project = projects.find((item) => item.id === id);
  const escape = (value) => String(value).replace(/[&<>"']/g, (char) => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[char]));
  const main = document.getElementById('main-content');
  document.body.classList.add('case-mode');

  const content = main.querySelector('.hybrid-main');
  main.querySelectorAll('.profile-aside nav a').forEach((link) => {
    link.setAttribute('href', 'index.html' + link.getAttribute('href'));
  });
  if (!project) {
    document.title = '프로젝트를 찾을 수 없습니다 | 유환희';
    content.innerHTML = '<div class="case-error"><p class="case-kicker">PROJECT NOT FOUND</p><h1>프로젝트를 찾을 수 없습니다.</h1><p>프로젝트 목록에서 다시 선택해 주세요.</p><a class="case-button" href="index.html#projects">프로젝트 목록으로</a></div>';
    return;
  }
  const p = project;
  const next = projects[(projects.indexOf(p) + 1) % projects.length];
  document.title = `${p.title} | 유환희 프로젝트 포트폴리오`;
  document.querySelector('meta[name="description"]').content = p.summary;
  const section = (id, number, title, content) => `<section class="case-section" id="${id}" aria-labelledby="${id}-heading"><h2 id="${id}-heading">${title}</h2>${content}</section>`;
  content.innerHTML = `
    <nav class="case-breadcrumb" aria-label="현재 위치"><a href="index.html">포트폴리오</a><span aria-hidden="true">/</span><a href="index.html#projects">프로젝트</a><span aria-hidden="true">/</span><span aria-current="page">${escape(p.title)}</span></nav>
    <header class="hybrid-hero case-hero"><span class="eyebrow">PROJECT</span><h2>${escape(p.title)}</h2><p class="muted">${escape(p.summary)}</p><div class="case-header-links"><a href="${escape(p.repo)}" target="_blank" rel="noopener noreferrer">GitHub 저장소 ↗</a><a href="index.html#projects">프로젝트 목록으로</a></div></header>
    <figure class="case-cover"><img src="${escape(p.image)}" alt="${escape(p.title)} 서비스 화면" width="1600" height="900" fetchpriority="high"><figcaption>${escape(p.title)} · 기존 포트폴리오에 수록한 대표 화면</figcaption></figure>
    <div class="case-layout"><nav class="case-nav" aria-label="프로젝트 상세 목차"><a href="#overview">담당 역할</a><a href="#requirements">요구사항</a><a href="#implementation">구현 흐름</a><a href="#decisions">문제와 접근</a><a href="#resources">관련 자료</a></nav><div>
    ${section('overview','01 / OVERVIEW','프로젝트에서 맡은 일',`<p>${escape(p.meta[0].value)}</p><dl class="case-meta">${p.meta.slice(1).map(m => `<div><dt>${escape(m.label)}</dt><dd>${escape(m.value)}</dd></div>`).join('')}<div><dt>핵심 구현 주제</dt><dd>${escape(p.focus)}</dd></div></dl>`)}
    ${section('requirements','02 / CONTEXT','사용자에게 필요한 기능',`<p>${escape(p.requirement)}</p><ul class="case-features">${p.features.map(f => `<li>${escape(f)}</li>`).join('')}</ul>`)}
    ${section('implementation','03 / IMPLEMENTATION','화면과 데이터를 연결한 과정',`<p>${escape(p.implementation)}</p><ol class="case-flow" aria-label="서비스 이용 흐름">${p.flow.map((f,i) => `<li><span>${i + 1}</span>${escape(f)}</li>`).join('')}</ol>`)}
    ${section('decisions','04 / PROBLEM SOLVING',escape(p.challenge),`<dl class="case-resolution">${p.resolution.map(r => `<div><dt>${escape(r.label)}</dt><dd>${escape(r.value)}</dd></div>`).join('')}</dl><aside class="case-takeaway"><strong>이 프로젝트의 구현 초점</strong><p>${escape(p.focus)}</p></aside>`)}
    ${section('resources','05 / RESOURCES','코드와 구현 자료',`<div class="case-evidence"><a class="case-button" href="${escape(p.repo)}" target="_blank" rel="noopener noreferrer">GitHub에서 코드 보기 ↗</a><a href="${escape(p.image)}" target="_blank" rel="noopener noreferrer">대표 화면 원본 보기 ↗</a></div><p class="case-caption">담당 범위와 구현 내용은 현재 포트폴리오에 정리된 설명을 기준으로 작성했습니다.</p>`)}
    <nav class="case-next" aria-label="다른 프로젝트"><div><small>다음 프로젝트</small><a href="?project=${next.id}">${escape(next.title)} ↗</a></div><a class="case-button" href="index.html#projects">전체 프로젝트</a></nav></div></div>
    <footer class="footer case-footer"><span>유환희 / Full-stack Web Developer</span><a href="#main-content">맨 위로 ↑</a></footer>`;
  if ('IntersectionObserver' in window) {
    const observer = new IntersectionObserver((entries) => {
      const current = entries.filter(e => e.isIntersecting).sort((a,b) => a.boundingClientRect.top - b.boundingClientRect.top)[0];
      if (!current) return;
      main.querySelectorAll('.case-nav a').forEach(a => {
        if (a.hash === `#${current.target.id}`) a.setAttribute('aria-current','location');
        else a.removeAttribute('aria-current');
      });
    }, {rootMargin:'-5% 0px -55% 0px',threshold:0});
    main.querySelectorAll('.case-section').forEach(s => observer.observe(s));
  }
})();
