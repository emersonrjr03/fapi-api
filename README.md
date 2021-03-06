# fapi-api
FAPI api.
Fake API (FAPI) is an application that gives the frontend developers the possibility of start working without having to wait for the backend API to be ready.
The user can create an account, and create projects that have resources and its attributes validations, and even add fake data to those resources using the FAPI interface. 

The URL must be like shown:<br/>
```fapi.com.br``` _/ ```api```_ _/ ```[USER SECRET]```_ _/ ```[RESOURCE NAME]```_ _/ ```{RESOURCE ID}```_

Examples of GET requests to the FAPI:<br/>

GET all products:<br/>
 **```GET```** ```fapi.com.br``` _/ ```api```_ _/ ```1239hh89bui39h9b19opa```_ _/ ```product```_

GET a specific product:<br/>
 **```GET```** ```fapi.com.br``` _/ ```api```_ _/ ```1239hh89bui39h9b19opa```_ _/ ```product```_ _/ ```1000032```_

Even POST requests will be allowed, and the created validations for that resource will be applied on the given JSON.

For example, using the same ```product``` resource, the user added the following attribute validation:

```RESOURCE```**.**```FIELD``` ```CONDITION``` ```VALUE``` **OTHERWISE** ```JSON_RETURN```

| RESOURCE      | . | FIELD         | CONDITION           | VALUE         |               | JSON_RETURN   |
| ------------- | - | ------------- | ------------------- | ------------- | ------------- |:-------------:|
| ```'product'``` | . | ```'price'```       | **```BIGGER_THAN```** | ```0```             | OTHERWISE     | ```json { status: 400, field: 'price', error: 'Price must be bigger than zero!' }``` |

When requesting a POST:

**```POST```** ```fapi.com.br``` _/ ```api```_ _/ ```1239hh89bui39h9b19opa```_ _/ ```product```_
Body content:
```javascript
{
  id: 1000033,
  description: 'Blue Ball',
  price: 0.0
}
```

The response will be:

```javascript

RESPONSE CODE: 400
{
  status: 400,
  field: 'price',
  error: 'Price must be bigger than zero!'
}
```

Get ideas and inspirations from:

​ https://designer.mocky.io/design

​ https://getsandbox.com/

