# Todo-App-Java-SQL
# 1. How to run on docker environment

git clone https://github.com/Tush37/Todo-App-Java-SQL.git

move inside cloned repo where docker-compose.yml is present and run below command

docker-compose --env-file env_var up

env_var is variables file to configure db connection string 

App will be accessible on:  http://localhost:8102/api/todos

DB will be accessible on localhost,1433 with sa user and passsword mentioned in env file.


Other Commands

docker-compose build --no-cache //build with --no-cache if code is updated 

docker-compose down --volumes //close the App env by removing app and db conatiners and remove volumes associates with it

# 2. How to Access

Ideally this should not present in db but for demo I'm adding it in repo

Todo_API.postman_collection.json is added in repo please import and use it.

Application will be accessible on port 8102 using below :

[GET]
http://localhost:8102/api/todos

[PUT]
http://localhost:8102/api/todos

[POST]
http://localhost:8102/api/todos

[GET]
http://localhost:8102/api/todos/{id}

[DELETE]
http://localhost:8102/api/todos/{id}

# 3.Architecture Overview

![image](https://github.com/Tush37/Todo-App-Java-SQL/assets/39132762/19d4afb2-435c-41d3-9c89-5d8681885430)


# 4. How it works

Docker-compose creates 2 conatiners using docker file mentioned in repository
1. api
2. db

docker-compose --env-file env_var up 

this command build and start containers from the current code using env variables from env_var file.


