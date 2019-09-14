//Multi branch pipeline job script
node {
   def branchName="${BRANCH_NAME}"
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
      if(branchName.matches("develop")){
         echo "Deployment Started on Dev Environment on following hosts:"
         echo "iassn0012001234"
         sh "java -jar target/springbootoauth.jar"
      }
      if(branchName.matches("release(.*)")){
         echo "Deployment Started on UAT Environment on following hosts:"
         echo "iassn0012001235"
         echo "iassn0012001236"
         sh "java -jar target/springbootoauth.jar"
      }
      if(branchName.matches("master")){
         echo "Deployment Started on Production Environment on following hosts:"
         echo "iassn0012001237"
         echo "iassn0012001238"
         echo "iassn0012001239"
         echo "iassn0012001240"
         sh "java -jar target/springbootoauth.jar"
      }
   }
   stage("Post Deployment"){
       echo "Post deployment work started"
   }
}
