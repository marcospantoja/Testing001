# How to add new resource module to project

`mvn io.quarkus:quarkus-maven-plugin:1.6.0.Final:create -DprojectGroupId=com.bbva.ninjahack -DprojectArtifactId=users-service -DclassName="com.bbva.ninjahack.services.user.UserResource" -Dpath="api/users"`

** Delete NativexxxTest class after project are created **

# How to add database dependencies

`quarkus:add-extension -Dextensions="jdbc-postgresql,hibernate-orm-panache,hibernate-validator,resteasy-jsonb,agroal"`

# How to run local database

`docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test -e POSTGRES_DB=usersdb -p 5432:5432 postgres:11.5`