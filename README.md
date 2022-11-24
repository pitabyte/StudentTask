# StudentTask

## How to run

To connect to your local Postgres database you may need to modify 'application.properties' file (task/demo/src/main/resources) with your login and password. </br>
This app uses jdk-19.

### After cloning this app, open command prompt, navigate to project's root directory (task) and execute:

```bash
# Navigate to 'demo' directory
$ cd demo

# To run an app: (make sure you are in 'demo' directory)
$ mvn spring-boot:run

```

## STUDENT endpoints

 <b>POST</b> (http://localhost:8080/api/student): </br>

body example in JSON format:
```
{
    "name": "adam",
    "surname": "adamowski",
    "age": 21,
    "email": "adam@gmail.com",
    "major": "informatyka"
}
```

<b>GET</b> (http://localhost:8080/api/student): </br>



This endpoint takes multiple parameters: </br>
</br>
**sort** - if set to "true", students will be sorted by 'name' </br>
**teacherID** - e.g. "1" - only students of a particular teacher will be returned </br>
**page** - e.g. "2" - page 2 (counting from 0) will be returned </br>
**size** - e.g. "3" - returns 3 results per page </br>
**name** and **surname** - returns students with a particular 'name' and 'surname' </br>

<b>PUT</b> (http://localhost:8080/api/student/{id}): </br>

You can specify any number of parameters you want to update.

body example in JSON format:
```
{
    "name": "ewa",
    "email": "ewa@gmail.com",
    "age": 25,
}
```

 <b>DELETE</b> (http://localhost:8080/api/student/{id}):

<b>POST</b> (http://localhost:8080/api/student/{studentID}/assign/{teacherID}) </br>
Assigns 'teacher' of 'teacherID' to 'student' of 'studentID'

**POST** (http://localhost:8080/api/student/{studentID}/remove/{teacherID}) </br>
Removes 'teacher' of 'teacherID' from 'student' of 'studentID'

## TEACHER endpoints

### POST (http://localhost:8080/api/teacher):

body example in JSON format: (the only difference is that 'major' has changed to 'subject')
```
{
    "name": "adam",
    "surname": "adamowski",
    "age": 21,
    "email": "adam@gmail.com",
    "subject": "programowanie"
}
```
All parameters specified in endpoints below are the same as in "student" endpoints

**GET** (http://localhost:8080/api/teacher) </br>
**PUT** (http://localhost:8080/api/teacher/{id}) </br>
**DELETE** (http://localhost:8080/api/teacher/{id}) </br>
**POST** (http://localhost:8080/api/teacher/{teacherID}/assign/{studentID}) </br>
**POST** (http://localhost:8080/api/teacher/{teacherID}/remove/{studentID}) </br>


 
