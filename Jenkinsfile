pipeline {
    agent any
    options { buildDiscarder(logRotator(numToKeepStr: '5')) }
    triggers {
        pollSCM "H * * * *"
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building on ${NODE_NAME}'
                sh 'mvn install -DskipTests=true'
            }
        }
        stage('Test') {
            steps {
                echo 'Executing test cases'
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging artifact'
                sh 'mvn package'
            }
        }
//         stage('Publish to Artifactory') {
//             steps {
//                 rtUpload (
//                     serverId: 'Artifactory',
//                     spec: '''{
//                         "files": [
//                             {
//                             "pattern": "target/*.jar",
//                             "target": "local-maven-snapshot/"
//                             }
//                         ]
//                     }'''
//             )
//         }
    }
}