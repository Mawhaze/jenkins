// set up base dsl for job for jenkins
job('root-dsl-seed') {
  description('Seed job for Jenkins Job DSL')
  logRotator {
    numToKeep(10)
  }
  scm {
    git {
      remote {
        url('https://github.com/Mawhaze/jenkins.git')
        credentials('github_access_token')
      }
      triggers{
        scm('H/15 * * * *')
      }
      steps {
        dsl {
          external('jobdsl/*.groovy')
        }
      }
    }
  }
}

// set up root folder strupcture and seed jobs
folder('ansible') {
  description('Ansible jobs for Jenkins')
}

job('ansible/ansible-dsl-seed') {
  description('Seed job for Jenkins Job DSL')
  logRotator {
    numToKeep(10)
  }
  scm {
    git {
      remote {
        url('https://github.com/Mawhaze/ansible.git')
        credentials('github_access_token')
      }
      triggers{
        scm('H/15 * * * *')
      }
      steps {
        dsl {
          external('jenkins/jobdsl/*.groovy')
        }
      }
    }
  }
}

// set up docker folder structure 
// seed jobs should be configured in their respective repositories
folder('docker') {
  description('Docker jobs for Jenkins')
}

folder('docker/build') {
  description('Docker build jobs for Jenkins')
}

// Define the jenkins_controller build job within the docker/build folder
pipelineJob('docker/build/jenkins_controller') {
  description('Build the Ansible Docker image from a Jenkinsfile')
  logRotator {
    numToKeep(10) //Only keep the last 10
  }
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/mawhaze/jenkins.git')
            credentials('github_access_token')
          }
          branches('*/master')
          scriptPath('controller/Jenkinsfile')
        }
      }
    }
  }
  triggers {
    scm('H 2 * * *') // Build daily between 2am and 3am
  }
}

// Define the jenkins_agent build job within the docker/build folder
pipelineJob('docker/build/jenkins_agent') {
  description('Build the Ansible Docker image from a Jenkinsfile')
  logRotator {
    numToKeep(10) //Only keep the last 10
  }
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/mawhaze/jenkins.git')
            credentials('github_access_token')
          }
          branches('*/master')
          scriptPath('agent/Jenkinsfile')
        }
      }
    }
  }
  triggers {
    scm('H 2 * * *') // Build daily between 2am and 3am
  }

// set up project-ender folder structure
folder('project-ender') {
  description('Project Ender jobs for Jenkins')
}