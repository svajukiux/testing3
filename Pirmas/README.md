###To run the app run the following command
docker-compose up --build -d 


#Web service API documentation

## Usage

Response form:

```json
{
  "data": "Holds the contents of a response",
  "message": "Description of what happened"
}
```

Further end-point documentation will only detail the data field

### Show All users

**Definition**

`GET /users`

**Response**

- `200 Ok` on success

```json
[
  {
    "firstName": "Tomas",
    "lastName": "Anavicius",
    "email": "tomas.anavicius@gmail.com"
  },
  
  {
    "firstName": "Paulius",
    "lastName": "Staisiunas",
    "email": "paulius.paulius@gmail.com"
  }
]
```

### Add new user

**Definition**

`POST /users`

**Arguments**

- `"firstName": string`
- `"lastName": string`
- `"email": string`

**Response**

- `201 Created` on success
- `3000 Email Already Exists` if email is already taken 

```json
{
    "firstName": "Paulius",
    "lastName": "Staisiunas",
    "email": "paulius.paulius@gmail.com"
}
```

### Show user

**Definition**

`GET /users/{email}`

**Response**

- `200 Ok` on success
- `404 Not Found` if user doesnt exist

```json
{
    "firstName": "Paulius",
    "lastName": "Staisiunas",
    "email": "paulius.paulius@gmail.com"
}
```

### Update a user

**Definition**

`PUT /user/{email}`

**Arguments**

- `"firstName": string`
- `"lastName": string`
- `"email": string`

**Response**

- `202 Accepted` on success
- `404 Not Found` if user doesnt exist
- `3000 Email Already Exists` if email is already taken 

### Patch a user

**Definition**

`PATCH /user/{email}`

**Arguments**

- `"firstName": string`
- `"lastName": string`
- `"email": string`

**Response**

- `202 Accepted` on success
- `404 Not Found` if user doesnt exist
- `3000 Email Already Exists` if email is already taken 

### Delete a user

**Definition**

`DELTE /users/{email}`

**Response**

- `204 No Content` on success
- `404 Not found` if user doesnt exist