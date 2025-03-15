# Sử dụng một image Java chính thức từ Docker Hub
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR của ứng dụng vào container
COPY target/student-management-0.0.1-SNAPSHOT.jar app.jar

# Chạy ứng dụng Java
ENTRYPOINT ["java", "-jar", "app.jar"]