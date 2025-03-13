-- Tạo bảng Người Dùng (Users)
CREATE TABLE IF NOT EXISTS Users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(100) NOT NULL,
                       full_name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       date_of_birth DATE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

 CREATE TABLE IF NOT EXISTS Students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Semester (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    term INT NOT NULL CHECK (term IN (1, 2, 3)),
    start_year INT NOT NULL,
    status varchar(50) DEFAULT 'Chưa diễn ra'
    );

CREATE TABLE IF NOT EXISTS Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    attendance_percentage DECIMAL(5, 2) NOT NULL,
    test_percentage DECIMAL(5, 2) NOT NULL,
    practice_percentage DECIMAL(5, 2) NOT NULL,
    project_percentage DECIMAL(5, 2) NOT NULL
    );


CREATE TABLE IF NOT EXISTS Teacher (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL
    );


CREATE TABLE IF NOT EXISTS Class (
     id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    class_group VARCHAR(50) NOT NULL,
    class_name VARCHAR(100) NOT NULL,
    class_code VARCHAR(50) NOT NULL UNIQUE,
    teacher_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES Course(id),
    FOREIGN KEY (teacher_id) REFERENCES Teacher(id)
    );



CREATE TABLE IF NOT EXISTS Class_Score (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           class_id INT NOT NULL,
                                           student_id INT NOT NULL,
                                           attendance_score DECIMAL(5, 2),
    test_score DECIMAL(5, 2),
    practice_score DECIMAL(5, 2),
    project_score DECIMAL(5, 2),
    FOREIGN KEY (class_id) REFERENCES Class(id),
    FOREIGN KEY (student_id) REFERENCES Students(id)
    );

CREATE TABLE IF NOT EXISTS Semester_Class (
      id INT AUTO_INCREMENT PRIMARY KEY,
      semester_id INT NOT NULL,
      class_id INT NOT NULL,
    FOREIGN KEY (semester_id) REFERENCES Semester(id),
    FOREIGN KEY (class_id) REFERENCES Class(id)
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
