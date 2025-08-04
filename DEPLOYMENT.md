# Deployment Guide for Taste App

This guide will help you deploy the Taste application to make it live on the internet.

## 🚀 Quick Deployment Steps

### Prerequisites
- GitHub account
- Railway account (for backend) - [Sign up here](https://railway.app/)
- PlanetScale account (for database) - [Sign up here](https://planetscale.com/)

## 1. Frontend Deployment (GitHub Pages)

### Step 1: Prepare GitHub Repository
1. Create a new repository on GitHub named `taste`
2. Clone this repository to your local machine
3. Push all the code to your GitHub repository:

```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/taste.git
git push -u origin main
```

### Step 2: Update Configuration
1. Update the homepage URL in `frontend/package.json`:
```json
"homepage": "https://YOUR_USERNAME.github.io/taste"
```

2. Update CORS configuration in `backend/src/main/resources/application.yml`:
```yaml
allowed-origins: 
  - "http://localhost:3000"
  - "https://YOUR_USERNAME.github.io"
```

### Step 3: Deploy to GitHub Pages
1. In your GitHub repository, go to Settings → Pages
2. Source: Deploy from a branch
3. Branch: `gh-pages` (will be created automatically)
4. Folder: `/ (root)`

The GitHub Actions workflow will automatically deploy your frontend when you push to main.

## 2. Database Deployment (PlanetScale)

### Step 1: Create Database
1. Sign up for PlanetScale
2. Create a new database called `taste-db`
3. Create a branch called `main`
4. Get your connection credentials

### Step 2: Set Up Database Schema
The schema will be automatically created when you first run the Spring Boot application due to `ddl-auto: update` in the configuration.

## 3. Backend Deployment (Railway)

### Step 1: Deploy to Railway
1. Sign up for Railway
2. Create a new project
3. Connect your GitHub repository
4. Select the backend folder as the build path
5. Railway will automatically detect the Dockerfile and deploy

### Step 2: Set Environment Variables
In Railway dashboard, add these environment variables:

```
DB_HOST=your-planetscale-host
DB_NAME=taste-db
DB_USERNAME=your-planetscale-username
DB_PASSWORD=your-planetscale-password
DB_PORT=3306
JWT_SECRET=your-super-secret-jwt-key-min-32-characters
```

### Step 3: Update Frontend Configuration
Update `frontend/.env.production` with your Railway backend URL:
```
REACT_APP_API_URL=https://your-railway-app.up.railway.app/api
```

## 4. Alternative Deployment Options

### Backend Alternatives:
- **Heroku**: Easy deployment with git
- **Render**: Similar to Railway
- **AWS/Google Cloud/Azure**: More complex but scalable

### Database Alternatives:
- **Railway MySQL**: Built-in database option
- **Heroku Postgres**: If using Heroku
- **AWS RDS**: Production-grade database

### Frontend Alternatives:
- **Netlify**: Great for React apps
- **Vercel**: Excellent performance
- **Firebase Hosting**: Google's hosting solution

## 5. Manual Deployment Steps

### Backend (Railway/Heroku)
```bash
# Build the application
cd backend
mvn clean package -DskipTests

# Deploy to Railway (automatically done via GitHub integration)
# Or for manual deployment:
railway login
railway link
railway up
```

### Frontend (Manual GitHub Pages)
```bash
cd frontend
npm install
npm run build
npm run deploy
```

## 6. Environment Variables Setup

### Frontend (.env.production)
```
REACT_APP_API_URL=https://your-backend-url.com/api
GENERATE_SOURCEMAP=false
```

### Backend (Railway/Production)
```
SPRING_PROFILES_ACTIVE=prod
DB_HOST=your-db-host
DB_NAME=taste_db
DB_USERNAME=your-db-user
DB_PASSWORD=your-db-password
JWT_SECRET=your-jwt-secret-key
```

## 7. GitHub Secrets Configuration

In your GitHub repository, go to Settings → Secrets and Variables → Actions, and add:

- `REACT_APP_API_URL`: Your backend URL
- `RAILWAY_TOKEN`: For automated Railway deployment (optional)

## 8. Verification Steps

1. **Frontend**: Visit `https://YOUR_USERNAME.github.io/taste`
2. **Backend**: Visit `https://your-railway-app.up.railway.app/api/health`
3. **Full App**: Test signup, login, and basic functionality

## 9. Monitoring and Maintenance

### Frontend
- GitHub Pages provides basic analytics
- Monitor GitHub Actions for deployment status

### Backend
- Railway provides logs and metrics
- Set up health check monitoring
- Monitor database usage in PlanetScale

### Database
- PlanetScale provides query insights
- Monitor connection limits
- Set up backup strategies

## 10. Troubleshooting

### Common Issues:

1. **CORS Errors**: Ensure backend CORS configuration includes your frontend URL
2. **API Connection Issues**: Check environment variables and network settings
3. **Database Connection**: Verify database credentials and SSL settings
4. **Build Failures**: Check GitHub Actions logs for specific errors

### Debug Commands:
```bash
# Check frontend build
cd frontend && npm run build

# Check backend compilation
cd backend && mvn clean compile

# Test API endpoints
curl https://your-backend-url.com/api/health
```

## 🎉 Your App is Now Live!

Once deployed, your Taste app will be accessible at:
- **Frontend**: `https://YOUR_USERNAME.github.io/taste`
- **Backend API**: `https://your-railway-app.up.railway.app/api`

Users can now:
- Sign up and create accounts
- Add recipes and restaurants
- Follow friends and discover food
- Share via links and QR codes
- Access the app from any device

## Support

If you encounter issues during deployment:
1. Check the GitHub Actions logs
2. Review Railway deployment logs
3. Verify all environment variables
4. Test API endpoints manually

Happy cooking! 🍳