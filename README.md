# Taste - Food Discovery & Planning App

A full-stack web application for cataloging meals, recipes, and restaurants, and for planning shared food experiences based on dietary preferences.

## Features

### 🔐 Authentication
- User sign-up and login with JWT tokens
- Secure password hashing
- Session management

### 👤 User Profiles
- Customizable dietary preferences (vegetarian, vegan, gluten-free, etc.)
- Allergy tracking
- Privacy settings (public, friends, private)
- Social features (follow/unfollow friends)

### 🍳 Recipe Management
- Full CRUD operations for recipes
- Categories and tags organization
- Ingredient lists with quantities and units
- Step-by-step instructions
- Difficulty ratings and cooking times
- Image uploads
- Dietary preference tagging

### 🏪 Restaurant Cataloging
- Restaurant information with ratings and notes
- Cuisine types and price ranges
- Menu item tracking
- Location data support
- Personal reviews and recommendations

### 🤝 Social Features
- Follow/unfollow other users
- Share recipes and restaurants via links
- QR code generation for easy sharing
- Privacy controls

### 🎯 Smart Recommendations
- Suggest recipes/restaurants based on dietary overlaps
- Friend-based recommendations
- Allergy-safe filtering
- Preference matching

### 📱 Modern UI
- Responsive design (mobile + desktop)
- Material-UI components
- Clean, intuitive interface
- Real-time updates

## Tech Stack

### Backend
- **Framework**: Spring Boot 3.2.1
- **Database**: MySQL 8.0+
- **Security**: Spring Security with JWT
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Java Version**: 17+

### Frontend
- **Framework**: React 18
- **UI Library**: Material-UI (MUI)
- **Routing**: React Router v6
- **State Management**: React Query + Context API
- **HTTP Client**: Axios
- **Forms**: React Hook Form

## 🌐 Live Demo

**Frontend**: `https://YOUR_USERNAME.github.io/taste`  
**Backend API**: `https://your-railway-app.up.railway.app/api`

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### 🚀 Quick Deploy
See [DEPLOYMENT.md](DEPLOYMENT.md) for complete deployment instructions to make your app live!

### Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE taste_db;
CREATE USER 'taste_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON taste_db.* TO 'taste_user'@'localhost';
FLUSH PRIVILEGES;
```

2. Update database configuration in `backend/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/taste_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: taste_user
    password: password
```

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build and run the Spring Boot application:
```bash
mvn clean install
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

The frontend will be available at `http://localhost:3000`

## API Endpoints

### Authentication
- `POST /api/auth/signup` - User registration
- `POST /api/auth/signin` - User login

### Users
- `GET /api/users/profile` - Get current user profile
- `PUT /api/users/profile` - Update user profile
- `POST /api/users/{id}/follow` - Follow a user
- `DELETE /api/users/{id}/follow` - Unfollow a user

### Recipes
- `GET /api/recipes` - Get recipes
- `POST /api/recipes` - Create recipe
- `GET /api/recipes/{id}` - Get recipe details
- `PUT /api/recipes/{id}` - Update recipe
- `DELETE /api/recipes/{id}` - Delete recipe
- `GET /api/recipes/search` - Search recipes

### Restaurants
- `GET /api/restaurants` - Get restaurants
- `POST /api/restaurants` - Create restaurant
- `GET /api/restaurants/{id}` - Get restaurant details
- `PUT /api/restaurants/{id}` - Update restaurant
- `DELETE /api/restaurants/{id}` - Delete restaurant

### Recommendations
- `GET /api/recommendations/recipes` - Get recipe recommendations
- `GET /api/recommendations/restaurants` - Get restaurant recommendations

## Project Structure

```
taste/
├── backend/                 # Spring Boot backend
│   ├── src/main/java/com/taste/
│   │   ├── model/          # JPA entities
│   │   ├── repository/     # Data access layer
│   │   ├── service/        # Business logic
│   │   ├── controller/     # REST controllers
│   │   ├── security/       # Security configuration
│   │   └── dto/           # Data transfer objects
│   ├── src/main/resources/
│   │   └── application.yml # Configuration
│   └── pom.xml            # Maven dependencies
├── frontend/               # React frontend
│   ├── src/
│   │   ├── components/    # Reusable UI components
│   │   ├── pages/         # Page components
│   │   ├── services/      # API services
│   │   ├── context/       # React context
│   │   └── utils/         # Utility functions
│   └── package.json       # NPM dependencies
└── README.md
```

## Environment Variables

### Backend (.env or application.yml)
```yaml
app:
  jwt:
    secret: your-jwt-secret-key
    expiration: 604800000 # 7 days

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/taste_db
    username: your-db-username
    password: your-db-password
```

### Frontend (.env)
```
REACT_APP_API_URL=http://localhost:8080/api
```

## Edge Cases Handled

- **Conflicting Dietary Preferences**: Smart filtering that finds overlap between user preferences
- **Duplicate Restaurants**: Detection and suggestion system for potential duplicates
- **Ingredient Synonyms**: Flexible ingredient matching
- **Empty Entries**: Validation and fallback content
- **Privacy Settings**: Proper access control based on user relationships

## Testing

### Backend
```bash
cd backend
mvn test
```

### Frontend
```bash
cd frontend
npm test
```

## Deployment

### Production Configuration

1. Update `application.yml` for production profile
2. Set environment variables for database and JWT secret
3. Build the application:

```bash
# Backend
cd backend
mvn clean package -Pprod

# Frontend
cd frontend
npm run build
```

### Docker Deployment (Optional)

Create `Dockerfile` for each component and use Docker Compose for orchestration.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.

## Support

For support and questions, please create an issue in the repository.

---

Built with ❤️ for food lovers everywhere!