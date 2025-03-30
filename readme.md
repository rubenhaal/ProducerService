
Swagger for testing:
http://localhost:8080/swagger-ui.html

Api docs for the project:
http://localhost:8080/v3/api-docs

The rabbitmq ui : http://localhost:15672

To launch this example, you need to launch docker compose up -d and then execute this
microservice and the microservice internalas

REST -> chargepoint endpiont -> rabbitmq -> internalas (consumer) -> REST -> chargepoint (endpoint)

The easy way to start is to use swagger ui:  http://localhost:8080/swagger-ui.html

The endpoint "api/session/startsession"

if you use the following json:
```
{
"station_id": "f1b6e83b-4cb7-4a91-a0ec-7f94e0b14c1e",
"driver_id": "driver1",
"callback": "http://localhost:8080/api/session/received"
}
```

It would be a valid combination between station id and driver id, with a
callback url that call the endpoint in ChargeSessionController

The other service internalas on launch will populate a ACl databse on memory.
That would check if the combination id driver and station id is valid:

This is the data:
```
('1', 'f1b6e83b-4cb7-4a91-a0ec-7f94e0b14c1e', 'driver1', 'token_347xc'),
('2', 'a4f1c210-9c76-4f2d-a3e4-f0c43d1f9998', 'driver2', 'token_2749c'),
('3', 'a4f1c210-9c76-4f2d-a3e4-f0c43d1f9998', 'driver3', 'token_2417c'),
('4', 'a4f1c210-9c76-4f2d-a3e4-f0c43d1f9998', 'driver4', 'token_5349c')
```