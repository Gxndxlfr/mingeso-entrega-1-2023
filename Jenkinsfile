pipeline{
    agent any
    tools{
        maven "maven"
    }
    stages{
        stage("Build JAR FILE"){
          steps{
              checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Gxndxlfr/mingeso-Entrega-1-1-2023/']])
              sh "mvn clean install"
          }
        }
        stage("Test"){
            steps{
                sh "mvn test"
            }
        }
        stage("SonarQube Analysis"){
            steps{
                sh "mvn clean verify sonar:sonar -Dsonar.projectKey=pep1 -Dsonar.projectName='pep1' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_af0399fe14ba674a94204b10ff8ba4d6ec409cac"
            }
        }
        stage("Build Docker Image"){
            steps{
                sh "docker build -t derflinger/proyecto_docker ."
            }
        }
        stage("Push Docker Image"){
            steps{
                withCredentials([string(credentialsId: 'dckrhubpassword2', variable: 'dckpass2')]) {
                    sh "docker login -u derflinger -p ${dckpass}"
                }
                sh "docker push derflinger/proyecto_docker"
            }
        }
    }
    post{
        always{
            sh "docker logout"
        }
    }
}