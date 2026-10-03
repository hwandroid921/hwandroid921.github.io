// Loopback-only preview. Serve only the portfolio's explicitly listed assets.
const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');
const root = path.resolve(__dirname, '..');
const files = new Set([
  '/portfolio-v7.html', '/portfolio-v7.css', '/portfolio-v7.js',
  '/designs/style.css', '/designs/assets/PretendardVariable.woff2',
  '/jeju-project.png', '/readme-project.png', '/logmile-project.png',
  '/token-monitor-project.png'
]);
const types = { '.html': 'text/html; charset=utf-8', '.css': 'text/css; charset=utf-8', '.js': 'text/javascript; charset=utf-8', '.png': 'image/png', '.woff2': 'font/woff2' };
const server = http.createServer((req, res) => {
  const url = new URL(req.url, 'http://127.0.0.1');
  const pathname = url.pathname === '/' ? '/portfolio-v7.html' : url.pathname;
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
server.listen(8767, '127.0.0.1', () => console.log('Portfolio preview: http://127.0.0.1:8767/portfolio-v7.html'));
