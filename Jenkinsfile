node {
   stage("Git Checkout") { // for display purposes
      // Get some code from a GitHub repository
      echo "Job '${JOB_NAME}' Branch: '${BRANCH_NAME}' and Build No: (${BUILD_NUMBER})"
      git 'https://github.com/sendkumaranil/springbootoauth.git'
   }
   stage("Source Code Build") {
      // Run the maven build here Maven tool is defined under global configuration
         def mvnHome = tool 'Maven'
         sh "'${mvnHome}/bin/mvn' clean package"
   }
   stage("Test"){
       echo "Junit test coverage"
   }
   stage("Rename Artifact"){
       sh "mv target/*.jar target/springbootoauth.jar"
   }
   stage("Deploy") {
      sh "java -jar target/springbootoauth.jar"
   }
}
