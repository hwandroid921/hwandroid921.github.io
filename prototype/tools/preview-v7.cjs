// Loopback-only preview. Serve only the portfolio's explicitly listed assets.
const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');
const root = path.resolve(__dirname, '..');
const files = new Set([
  '/index.css', '/index.js',
  '/index.html', '/assets/PretendardVariable.woff2', '/images/profiles/Hwan2.png', '/assets/icons/github-logo.svg', '/assets/icons/envelope-simple.svg', '/assets/icons/notebook.svg', '/assets/icons/phone.svg',
  '/images/projects/jeju-project.png', '/images/projects/readme-project.png', '/images/projects/logmile-project.png',
  '/images/projects/token-monitor-project.png'
]);
const types = { '.html': 'text/html; charset=utf-8', '.css': 'text/css; charset=utf-8', '.js': 'text/javascript; charset=utf-8', '.svg': 'image/svg+xml', '.png': 'image/png', '.woff2': 'font/woff2' };
const server = http.createServer((req, res) => {
  const url = new URL(req.url, 'http://127.0.0.1');
  const pathname = url.pathname === '/' ? '/index.html' : url.pathname;
  if (!['GET', 'HEAD'].includes(req.method) || !files.has(pathname)) {
    res.writeHead(404); res.end('Not found'); return;
  }
  fs.readFile(path.join(root, pathname.slice(1)), (err, data) => {
    if (err) { res.writeHead(404); res.end('Not found'); return; }
    res.writeHead(200, {
      'Content-Type': types[path.extname(pathname)],
      'Cache-Control': 'no-store',
      'X-Content-Type-Options': 'nosniff'
    });
    res.end(req.method === 'HEAD' ? undefined : data);
  });
});
server.listen(8767, '127.0.0.1', () => console.log('Portfolio preview: http://127.0.0.1:8767/'));
