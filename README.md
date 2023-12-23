# Your Bunny - Dating site API

## How to run 
### Dev version
```shell
./mvnw -Dspring.profiles.active=dev
```
### Prod version
```shell
export ADMIN_USERNAME=*username* \ 
ADMIN_PASSWORD=*password* \
ADMIN_EMAIL=*email* \ 
ADMIN_PHONE=*phone* \ 
DB_USERNAME=*username* \ 
DB_PASSWORD=*password* \
JWT_SECRET=*secret* \
JWT_LIFETIME=*lifetime*
&& ./mvnw -Dspring.profiles.active=prod
```
## Testing
You can test API with [Postman collection](your-bunny-api.postman_collection.json) or on the endpoint /swagger-ui.html

## TODO:

- [x] Registration
  - [x] Save user to database
  - [x] Generate and return token with DTO 
  - [x] Fix exception handling with some issues
- [x] Fix obtaining a token for user
- [ ] Chat API
  - [ ] Implement interaction via Websockets
  - [ ] Fix exception handling with some issues
- [ ] Messages API 
  - [ ] Fix sending a message, updating message 
  - [ ] Fix exception handling with some issues
- [ ] Admin Panel API 
  - [ ] Fix CRUD operations with users and profiles
  - [ ] Fix exception handling with some issues
- [ ] Profile API
  - [ ] Fix CRUD operations (update profile, delete profile, )
  - [ ] Fix exception handling
- [x] Fix roles and roles hierarchy
- [x] Fix problem with roles and privileges
- [x] Fix exception handling with bad JWT token (PRIORITY)
- [ ] Docker file (VERY OPTIONAL)
- [x] Fix error with getting user from DB (return null when user is exists)
- [x] Fix prod and dev profiles' properties
- [ ] Fix logging
- [ ] Uploading avatars 
- [ ] Fix validator which check good password, email and phone number
- [ ] Fix advices

## Authors 
[DashaGorodilova](https://github.com/DashaGorodilova)<br>
[DmitriyVx](https://github.com/DmitriyVx)<br>
[helgesander](https://github.com/helgesander)<br>