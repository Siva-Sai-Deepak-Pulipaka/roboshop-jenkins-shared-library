def compile() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package'
    }
}

def testcases() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package'
    }
    if(app_lang == "golang") {
        sh 'echo'
    }
}

def codequality() {
    withAWSParameters(credentialsId: 'PARAM' naming: 'absolute', path: 'sonarqube.user', recursive: true, regionName: 'us-east-1') 
    {
      sh 'sonar-scanner -Dsonar.host.url=http://172.31.13.99:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts}'
    }
    
}
