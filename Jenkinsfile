pipeline {
    agent any

    stages {

        stage('Build Maven') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Remove Old Containers') {
            steps {
                sh '''
                docker rm -f personapi-container || true
                docker rm -f mysql-container || true
                '''
            }
        }

        stage('Start MySQL') {
            steps {
                sh '''
                docker run -d \
                  --name mysql-container \
                  -e MYSQL_ROOT_PASSWORD=rootpass \
                  -e MYSQL_DATABASE=persondb \
                  -e MYSQL_USER=personuser \
                  -e MYSQL_PASSWORD=personpass \
                  -p 3306:3306 \
                  mysql:8.0
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t personapi-app .'
            }
        }

        stage('Start API') {
            steps {
                sh '''
                docker run -d \
                  --name personapi-container \
                  --link mysql-container:mysql \
                  -p 8082:8080 \
                  personapi-app
                '''
            }
        }
    }
}