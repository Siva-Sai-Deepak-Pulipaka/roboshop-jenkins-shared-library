// call is a default function  
def call() {
    
    if (!env.sonar_extra_opts) {            //means if var is not there then the value is empty
        env.sonar_extra_opts = ""
    }

    pipeline {
        agent any

        stages {

            stage('Compile/Build') {
                steps {
                    script {
                        
                        common.compile()
                    }
                }
            }
            
            stage('Test Cases') {
                steps {
                    script {
                        common.testcases()
                    }
                }
            }
            
            stage('Code Quality') {
                steps {
                    script {
                        common.codequality()
                    }
                }
            }
        }
            post {
                failure {
                    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: "cloudprojectmail001@gmail.com", subject: "${component} - Pipeline Failed", to: "cloudprojectmail001@gmail.com", mimeType: 'text/html'
                }
            }
    }
}  

