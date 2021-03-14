#!/bin/bash

# IMPORTANT: If you change a value of these properties, please update the documentation in the README file
# Please choose an appropriate image tag from the official repository https://hub.docker.com/_/postgres/
dbVersion="11-alpine"
dbName=postgres
dbUser=quarkus
dbPass=quarkus

# Run the docker container for PostgreSQL
echo -e "\\nStarting docker container postgresql...\\n"
docker run --rm --name postgresql_quarkus -e POSTGRES_PASSWORD=${dbPass} -e POSTGRES_USER=${dbUser} -e POSTGRES_DB=${dbName} -d -p 127.0.0.1:5500:5432 postgres:${dbVersion}
echo -e "\\nTo stop the database, execute: docker stop postgresql_quarkus\\n"
