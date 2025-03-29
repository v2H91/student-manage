# student-management
# docker pull mysql:latest
# docker run --name mysql-container --network my-network -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=student_manage -p 3306:3306 -d mysql:latest
# admin@gmail.com
# admin

# to run java app in background, write to output log file
# nohup java -jar student-management-0.0.1-SNAPSHOT.jar > output.log 2>&1 &

# docker network create my-network
# docker network ls

# docker build -t my-java-app .
# docker run -p 8080:8080 -d my-java-app


# ssh-keygen -t rsa -b 4096
# cat ~/.ssh/id_rsa.pub
# cat ~/.ssh/id_rsa
