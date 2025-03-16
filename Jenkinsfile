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
                def pid = sh(script: "ps -ef | grep 'java -jar' | grep -v grep | awk '{print \$2}'", returnStdout: true).trim()
                if (pid) {
                sh "sudo kill -9 ${pid}"
                }
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