# Courses API

## 🔎 About the project
Welcome to my Course API project. This project is part of the Rocketseat One Java course. The challenge was to create a CRUD for managing courses with features such as:
- Create courses
- List courses filtering by name and category
- Update courses by `id`
- Delete courses by `id`
- Toggle the active status of a course

## ⚙️ Technologies and libraries used
- Java 17
- Spring
- JUnit
- JPA
- Lombok
- Swagger
- Jakarta
- PostgreSQL
- Docker

## 📋 Installation
To get a copy of the project on your local computer, just follow these steps:
1. Clone the repository
```bash
git clone https://github.com/ledoctah/courses-api.git
```
2. Navigate to the project repository
```bash
cd courses-api
```
3. Run the Maven command
```bash
mvn clean install
```
4. Execute docker-compose
```bash
docker-compose up -d
```
5. Execute the generated jar file
```bash
java -jar target/courses-api-*.jar
```
6. The project should be up and running now!


You can access the swagger docs by the following address:
http://localhost:8080/swagger-ui/index.html

There is also a Postman collection with all the endpoints requests inside the folder `./postman`.

### Made with ❤ by [Matheus Ferreira](https://www.github.com/ledoctah)

[![Linkedin Badge](https://img.shields.io/badge/-Matheus%20Ferreira-6633cc?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/o-matheus-ferreira/)](https://www.linkedin.com/in/o-matheus-ferreira/)
