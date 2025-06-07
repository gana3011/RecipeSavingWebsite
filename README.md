# 🍲 FlavourFile

**FlavourFile** is a full-stack web application that allows users to store, manage, and search for their favorite recipes. Users can sign up, log in, verify their email via OTP, and organize recipes with titles, descriptions, links, and tags.

---

## 🚀 Features

* 🔐 User Authentication with JWT and Email OTP verification
* 📬 Email service integration via Gmail SMTP
* 📚 Recipe management: add, view, and filter recipes by name or tags
* 🔎 Search functionality for better accessibility
* 🌐 Full REST API backend with Spring Boot and PostgreSQL
* 💅 Intuitive and responsive frontend built with React

---

## 🛠️ Tech Stack

### Frontend

* React.js
* Tailwind CSS
* Axios

### Backend

* Spring Boot
* Spring Security
* Spring Data JPA
* JavaMailSender
* PostgreSQL
* JWT for authentication

### DevOps / Deployment

* Docker
* Render (for backend deployment)
* Vercel (for frontend deployment)

---

### Running Locally

#### 1. Clone the repo:

```bash
git clone https://github.com/yourusername/flavourfile.git
cd flavourfile
```

#### 2. Backend Setup

```bash
cd backend
Fill the variables according to application.properties-example   # Fill in required environment variables

# Build and run with Docker
docker build -t flavourfile .
docker run -p 8080:8080 flavourfile
```

#### 3. Frontend Setup

```bash
cd frontend
npm install
npm start
```

---

## ⚙️ Environment Variables

These should be set in your `application.properties` or Render dashboard:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:5432/<db>
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password
JWT_SECRET=your_jwt_secret
SPRING_MAIL_USERNAME=your_email@gmail.com
SPRING_MAIL_PASSWORD=your_app_password
```

---

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/a901dbae-1129-4051-a7cb-5250df22f426)

![image](https://github.com/user-attachments/assets/0436149f-3cbf-4dbd-82fe-0a0fa23cc7eb)



---


MIT License – see the [LICENSE](LICENSE) file for details.

---

Let me know if you want to customize the README based on your actual deployment links, team members, or specific screenshots.
