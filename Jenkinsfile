pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/v2H91/student-manage.git', branch: 'main'
            }
        }

        stage('Stop Java Application') {
            steps {
                 sh 'sudo pkill -f "java -jar"'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
       stage('Run Java Application') {
            steps {
                sh 'nohup java -jar target/student-management-0.0.1-SNAPSHOT.jar &'
            }
       }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}