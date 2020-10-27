# fapi-api
FAPI api.
FAPI is an application that you give to frontend developers the possibility of start working without having to wait for the backend API to be ready.
The user can create an account, and create projects that have resources and its rules, and even add fake data to those resources using the FAPI interface. 

It's possible create a GET request to the FAPI like the following:<br/>
```fapi.com.br/USER_TOKEN/RESOURCE```

**```GET```** ```fapi.com.br```_/```1239hh89bui39h9b19opa```_ _/```product```_

OR with ID in the path:<br/>
```fapi.com.br/USER_TOKEN/RESOURCE/{ID}```<br/>

```**GET** fapi.com.br/1239hh89bui39h9b19opa/_product_/1000032```

Even POST requests will be allowed, and the created rules for that resource will be applied on the given JSON.

For example, using the same 'product' resource, the user added the following rule:

RESOURCE.FIELD CONDITION VALUE OTHERWISE JSON_RETURN_

| RESOURCE      | . | FIELD         | CONDITION           | VALUE         | OTHERWISE     | JSON_RETURN   |
| ------------- | - | ------------- | ------------------- | ------------- | ------------- |:-------------:|
| ```'product'``` | . | ```'price'```       | **```BIGGER_THAN```** | ```0```             | OTHERWISE     | ```json { status: 400, field: 'price', error: 'Price must be bigger than zero!' }``` |

When requesting a POST:

POST fapi.com.br/1239hh89bui39h9b19opa/product
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
