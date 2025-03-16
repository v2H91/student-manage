# student-management
# docker pull mysql:latest
# docker run --name mysql-container --network my-network -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=student_manage -p 3306:3306 -d mysql:latest
# admin@gmail.com
# admin

# to run java app in background, write to output log file
# nohup java -jar your-java-app.jar > output.log 2>&1 &

# docker network create my-network
# docker network ls

# docker build -t my-java-app .
# docker run -p 8080:8080 --network my-network -d my-java-app