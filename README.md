# Nextneo System Project
Nextneo System Project

Java SOA project with Spring 5.

- Web module was used: Spring MVC running on Tomcat 
- Service modules were used: Spring MVC, Spring Data, JPA 2 (with Hibernate), Spring AOP, Spring JMS running on Tomcat

## Requirements

- JDK 8
- MySQL
- Tomcat 9

## Configure

### Database

- Run <a href="https://github.com/ortizraf/nextneo-system/raw/master/docs/scripts/dump.sql">script</a>

## Info

### Web

#### /system

### API Services

#### POST - /system-service/user

##### request
```json
{  
   "login":"friedman"
}
```

##### response
```json
{
    "id": 1,
    "lastAccess": null,
    "login": "friedman",
    "password": "blR/EdGICTRW4fFI7xrQZA==",
    "type": "CUSTOMER",
    "groupRoles": [
        {
            "id": 1,
            "name": "CUSTOMER",
            "users": null
        }
    ]
}
```
#### POST - /system-service/login/doLogin

##### request
```json
{  
   "userName":"friedman",
   "password":"367030"
}
```
##### response
```json
{
    "lastAccess": null,
    "login": "friedman",
    "password": "blR/EdGICTRW4fFI7xrQZA==",
    "type": "CUSTOMER",
    "groupRoles": [
        {
            "id": 1,
            "name": "CUSTOMER",
            "users": null
        }
    ]
}
```
#### GET - /system-service/user/findById/1

##### response
```json
{  
   "id":1,
   "lastAccess":null,
   "login":"friedman",
   "password":"blR/EdGICTRW4fFI7xrQZA==",
   "type":"CUSTOMER",
   "groupRoles":[  
      {  
         "id":1,
         "name":"CUSTOMER",
         "users":null
      }
   ]
}
```