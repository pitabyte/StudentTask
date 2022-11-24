# StudentTask

## How to run

To connect to your local Postgres database you may need to modify 'application.properties' file (task/src/main/resources) with your login and password.

### Open command prompt, navigate to project's root directory (task) and execute:

```bash
# Navigate to 'task' directory
$ cd task

# To run an app:
$ mvn spring-boot:run

# or from 'task' directory navigate to 'target' directory and run .jar file
$ cd target
$ java -jar .\task-0.0.1-SNAPSHOT.jar
```

## STUDENT endpoints

### POST (http://localhost:8080/api/student):

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

### GET (http://localhost:8080/api/student):



This endpoints takes multiple parameters:
'sort' - if set to "true", students will be sorted by name
'teacherID' - e.g. "1" - only students of a particular teacher will be returned (whose teacherID is specified)
'page' - e.g. "2" - page 2 (counting from 0) will be returned
'size' - e.g. "3" - returns 3 results per page

### PUT (http://localhost:8080/api/student/{id}):

You can specify any number of parameters you want to update.

body example in JSON format:
```
{
    "name": "ewa",
    "email": "ewa@gmail.com",
    "age": 25,
}
```

### DELETE (http://localhost:8080/api/student/{id}):

### POST (http://localhost:8080/api/student/{studentID}/assign/{teacherID})
Assigns 'teacher' of 'teacherID' to 'student' of 'studentID'

### POST (http://localhost:8080/api/student/{studentID}/remove/{teacherID})
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
    "subject": "informatyka"
}
```

### GET (http://localhost:8080/api/teacher)
### PUT (http://localhost:8080/api/teacher/{id})
### DELETE (http://localhost:8080/api/teacher/{id})
### POST (http://localhost:8080/api/student/{teacherID}/assign/{studentID})
### POST (http://localhost:8080/api/student/{teacherID}/remove/{studentID})


 
