-- Tạo bảng Người Dùng (Users)
CREATE TABLE IF NOT EXISTS Users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('student', 'teacher', 'admin') NOT NULL,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       date_of_birth DATE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng Môn Học (Subjects)
CREATE TABLE IF NOT EXISTS Subjects (
                          subject_id INT AUTO_INCREMENT PRIMARY KEY,
                          subject_name VARCHAR(100) NOT NULL,
                          subject_code VARCHAR(20) NOT NULL,
                          semester VARCHAR(50) NOT NULL,
                          credits INT NOT NULL
);

-- Tạo bảng Điểm (Grades)
CREATE TABLE IF NOT EXISTS Grades (
                        grade_id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT,
                        subject_id INT,
                        semester VARCHAR(50) NOT NULL,
                        score DECIMAL(5, 2) NOT NULL,
                        grade_type ENUM('test', 'exam', 'assignment') NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES Users(user_id),
                        FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);

-- Tạo bảng Đăng Ký Môn Học (Course_Registrations)
CREATE TABLE IF NOT EXISTS Course_Registrations (
                                      registration_id INT AUTO_INCREMENT PRIMARY KEY,
                                      user_id INT,
                                      subject_id INT,
                                      semester VARCHAR(50) NOT NULL,
                                      status ENUM('registered', 'completed', 'dropped') NOT NULL,
                                      registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (user_id) REFERENCES Users(user_id),
                                      FOREIGN KEY (subject_id) REFERENCES Subjects(subject_id)
);

-- Tạo bảng Thông Báo (Notifications)
CREATE TABLE IF NOT EXISTS Notifications (
                               notification_id INT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT,
                               sender_id INT,
                               message TEXT NOT NULL,
                               sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               read_status BOOLEAN DEFAULT FALSE,
                               FOREIGN KEY (user_id) REFERENCES Users(user_id),
                               FOREIGN KEY (sender_id) REFERENCES Users(user_id)
);

-- Tạo bảng Báo Cáo (Reports)
CREATE TABLE IF NOT EXISTS Reports (
                         report_id INT AUTO_INCREMENT PRIMARY KEY,
                         report_type ENUM('grade', 'attendance', 'statistical') NOT NULL,
                         generated_by INT,
                         report_data TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (generated_by) REFERENCES Users(user_id)
);

-- Tạo bảng Quyền Truy Cập (Permissions)
CREATE TABLE IF NOT EXISTS Permissions (
                             permission_id INT AUTO_INCREMENT PRIMARY KEY,
                             user_role ENUM('student', 'teacher', 'admin') NOT NULL,
                             access_function VARCHAR(255) NOT NULL,
                             can_access BOOLEAN DEFAULT TRUE
);
