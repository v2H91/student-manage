# Sử dụng image Maven chính thức để build ứng dụng
FROM maven:3.8.5-openjdk-17 AS build

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng bằng Maven
RUN mvn clean package

# Sử dụng image Java chính thức để chạy ứng dụng
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR đã được build từ image Maven
COPY --from=build /app/target/student-management-0.0.1-SNAPSHOT.jar app.jar

# Chạy ứng dụng Java
ENTRYPOINT ["java", "-jar", "app.jar"]