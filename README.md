### Project Overview 
Task management API with a frontend

### Getting Started 
i. Download the code from https://github.com/3malek/moj-hmcts-task-tracking  
* This is the backend: /hmcts_task_tracking_be/  
* This is the frontend: /hmcts_task_tracking_fe/  

ii. On your workstation, create a folder for the frontend, e.g. /task_tracking_frontend/  
iii. cd into this frontend folder and run the following (on the command line):  
npx govuk-prototype-kit@latest create  
iv. Copy the contents of /hmcts_task_tracking_fe/ into your frontend folder, which you created above (basically, you need to update the app folder).  
v. Run the following (from inside your frontend folder):  
npm run dev  

vi. For the backend, inside /hmcts_task_tracking_be/ run the following (on the command line):  
./gradlew build  
vii. Then run (one of the following):
* ./gradlew bootRun
* ./java -jar build/libs/hmcts-0.0.1-SNAPSHOT.jar
* within an IDE (e.g. IntelliJ)

viii. Finally, via a web browser, navigate to: http://localhost:3000/  

### Usage Instructions
API documentation exists at the following link:  
https://github.com/3malek/moj-hmcts-task-tracking/wiki


###  Testing
You can run the (JUnit) tests via your IDE or on the command line by running ./gradlew test


### cURL 
You can also test the API directly.  
For example, use the following commands:  
curl -v localhost:8080/tasks  

curl -v localhost:8080/tasks/1  
curl -v localhost:8080/tasks/99  

curl -X POST localhost:8080/tasks -H "Content-type:application/json" -d "{\"title\": \"New Test Data\", \"description\": \"Report needs to be ready for review by Thursday.\", \"status\": \"NOT_STARTED\", \"dueDate\": \"2027-01-01T18:11:35.522786900\" }"  

curl -X PUT localhost:8080/tasks/2 -H "Content-type:application/json" -d "{\"title\": \"Updated Test Data\", \"description\": \"Prefer Thursday, but Friday is acceptable\", \"status\": \"IN_PROGRESS\", \"dueDate\": \"2027-01-01T18:11:35.522786900\" }"  

curl -X PATCH localhost:8080/tasks/2/IN_PROGRESS  

curl -X DELETE localhost:8080/tasks/2  


### Contact Information 
urfanmalik2000@yahoo.co.uk
