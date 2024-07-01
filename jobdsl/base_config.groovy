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
          external('ansible/jenkins/jobdsl/*.groovy')
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

// set up project-ender folder structure
folder('project-ender') {
  description('Project Ender jobs for Jenkins')
}