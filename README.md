# Todo-App-Java-SQL
//How to run on docker envirnment
git clone https://github.com/Tush37/Todo-App-Java-SQL.git

#run below command
docker-compose --env-file my-env up

my-env is variables fileto configure db connection string 
#ideally this should not present in db but for demo I'm adding it in repo

#Application will be accessible using below requests
#postman-collection.json is added in repo please import and use it
[GET]
http://localhost:8102/api/todos

[PUT]
http://localhost:8103/api/todos

[POST}
http://localhost:8102/api/todos

[GET]
http://localhost:8102/api/todos/{id}

[DELETE]
http://localhost:8102/api/todos/{id}
