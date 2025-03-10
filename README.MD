# Delicious Food
Delicious food, is an application which manage users, restaurants and deliveries
allowing each one to be registered and login so 
* users can order 
* restaurants can register itself, register products and offers
* delivery can register itself, register its vehicle, have notification for deliver orders

## Components
* Database, postgres
* rest-api, spring boot application with db connection
* front-end, based on multiplatformm framework like js
  * web application
  * mobile application

## Running Back-End
this application runs rest-api and database systems using docker and docker compose, 
so it can easily be migrated to an ECS later.
### start system
we need to create the spring boot application image
```commandline
 docker build -t order .
```
once it is created, we can start the applications
```commandline
 docker compose up -d
```