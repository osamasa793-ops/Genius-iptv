# Backend API - Genius IPTV

## متطلبات التشغيل
- Node.js v14+
- MongoDB أو MySQL
- npm

## التثبيت

```bash
npm install
```

## المتغيرات البيئية (.env)

```
PORT=5000
MONGO_URI=mongodb://localhost:27017/genius-iptv
# أو للـ MySQL
DB_HOST=localhost
DB_USER=root
DB_PASSWORD=password
DB_NAME=genius_iptv

ADMIN_USERNAME=Osama1980
ADMIN_PASSWORD=Miramaya2026
JWT_SECRET=your_secret_key_here
```

## التشغيل

```bash
npm start
```

الـ API سيكون متاحاً على: `http://localhost:5000/api`
