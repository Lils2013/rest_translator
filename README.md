# RestTranslator
REST-api for text translation using Yandex.translate

## Requirements
* Java 8
* Maven 3+

## Usage
Build application and run integration tests with:
```
    mvn clean package
```
Then execute the application with:
```
    java -jar target/rest-translator-0.0.1-SNAPSHOT.jar
```
The application runs on port 8080

## Endpoints
### Translate
Request:
```
    GET localhost:8080/translate?text=один два три&to=en&from=ru
```
Response:
```
    HTTP 200 OK
    [ "one", "three", "two" ]
```
Parameter "from" is optional

### Get log
Request:
```
    GET localhost:8080/log
```
Response:
```
    HTTP 200 OK
    [ {
      "id" : 1,
      "dateTime" : "2019-01-23T00:31:24.112+03:00",
      "inputText" : "один два три",
      "translatedText" : "one three two",
      "lang" : "ru-en",
      "status" : "SUCCESS"
    } ]
```