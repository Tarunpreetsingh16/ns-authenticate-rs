pipeline {
    agent any
    triggers {
        pollSCM "* * * * *"
    }
    stages {
        stage('Build') {
            steps {
                echo "Building on ${NODE_NAME}"
                sh 'mvn install -DskipTests=true'
            }
        }
        stage('Test') {
            steps {
                echo "Executing test cases"
                sh 'mvn test'
            }
        }
    }
}