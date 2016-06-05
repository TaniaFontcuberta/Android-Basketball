# Android_JHipster_Basketball
Android frontend that integrates with JHipster backend via OAuth 2.0

This is the corresponding JHipster repository: https://github.com/alfredorueda/basketballOAuth2

You simply need to properly configure the properties:
https://github.com/alfredorueda/Android_JHipster_Basketball/blob/master/app/src/main/assets/app.properties
as defined in your JHipster app:
https://github.com/alfredorueda/basketballOAuth2/blob/master/src/main/resources/config/application.yml

security:
        authentication:
            oauth:
                clientid: basketballapp
                secret: mySecretOAuthSecret
                # Token is valid 30 minutes
                tokenValidityInSeconds: 1800
        rememberme:
            # security key (this key should be unique for your application, and kept secret)
            key: a2a6c0b3627730ff7edad3f9b829c9d80969fe7a
            
            
Then, you simply need to add some players using the AngularJS Web interface as usual.
After that, you can query your new players from your Android application!

TODO: 
1. Complete CRUD
2. Use best practices from this nice book: https://leanpub.com/retrofit-love-working-with-apis-on-android (interceptor)
   (https://futurestud.io/blog/retrofit-series-round-up)

