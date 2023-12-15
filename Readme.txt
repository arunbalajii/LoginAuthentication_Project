This is login module runs in port 9003


spring.data.mongodb.uri=mongodb+srv://${MONGODB_USERNAME}:${MONGODB_PASSWORD}@cluster0.g4gimeb.mongodb.net/login?retryWrites=true&w=majority
need to set environment varaibles in local and also in jenkins settings so that the details will be picked

in jenkins add both mongo and docker user/pass details

covers:

1. signup
2. signin
3. jwt token generation
4. jwt token validation
5. mongodb connection
6. admin page (secured)
7. user page (secured)
8. profile (secured)


Refer dockements in another git with name capstone_document