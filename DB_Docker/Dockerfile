# Choose ubuntu version
FROM mcr.microsoft.com/mssql/server:2022-latest

# Create app directory
WORKDIR /usr/src/app

# Copy initialization scripts 
COPY . /usr/src/app
             


# Expose port 1433 in case accessing from other container
EXPOSE 1433

# Run Microsoft SQL Server and initialization script (at the same time)
CMD /bin/bash ./entrypoint.sh