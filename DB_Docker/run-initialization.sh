# Wait to be sure that SQL Server came up
sleep 10s

# command to login to db and create db
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P ${SA_PASSWORD} -d master -Q "CREATE DATABASE $SQL_DB_NAME"
