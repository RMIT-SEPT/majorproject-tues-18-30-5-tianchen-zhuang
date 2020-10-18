# RMIT SEPT 2020 Major Project

# Group TUES-18.30-5

## Members
* Koutsoukis, Caspar (s3287475)
* Wasco, Christopher (s3780962)
* Chen, Wuchi        (s3716792)
* Zhuang, Tianchen (s3695116)
* Boughton, Lachlan (s3769219)

## Records

* Github repository : https://github.com/RMIT-SEPT/majorproject-tues-18-30-5-tianchen-zhuang
* ClickUp Workspace : https://app.clickup.com/6915855/v/l/s/6938680
* AWS Live Application : http://tues18305live-env.eba-hsi7bj9f.us-east-1.elasticbeanstalk.com/

## Code documentation

[Quick Start](/docs/README.md) in `docs` folder  
Backend written in Java 8 using MVC design model and Springboot - build using maven  
Frontend written in React using CSS styling - build using Node.js

## Running Application Locally
1. Change values api access routes in authenticate.js from awsUrl to local
2. Navigate to project folder using terminal
3. ".\majorproject-tues-18-30-5-tianchen-zhuang\Backend"
4. Run command 'mvn clean install -Pproduction'
5. This will generate an executable jar file in ".\majorproject-tues-18-30-5-tianchen-zhuang\Backend\target"
6. Double click springboot-backend-0.0.1-SNAPSHOT.jar.original and navigate to "http://localhost:8080/"
7. To close the application open resource monitor and navigate to the network tab
8. Using the PID of javaw.exe type taskkill -f -PID xxxx

## Using Application in AWS
1. Navigate to 'http://tues18305live-env.eba-hsi7bj9f.us-east-1.elasticbeanstalk.com/#/'
2. Register Customer or Business Account
3. Can view user details by using the admin dashboard 'http://tues18305live-env.eba-hsi7bj9f.us-east-1.elasticbeanstalk.com/#/adminDashboard'
4. Database can be accessed via 'http://tues18305live-env.eba-hsi7bj9f.us-east-1.elasticbeanstalk.com/h2-console'

## Making changes to Repo
1. Please create a feature branch off of the dev branch
2. Develop changes
3. Push to branch and open a Pull Request to dev which will be reviewed by one of the team members