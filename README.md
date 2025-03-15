# student-management
# docker pull mysql:latest
# docker run --name mysql-container --network my-network -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=student_manage -p 3306:3306 -d mysql:latest
# admin@gmail.com
# admin


# docker network create my-network
# docker network ls

# docker build -t my-java-app .
# docker run -p 8080:8080 --network my-network -d my-java-app