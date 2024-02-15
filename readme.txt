entity : use for store data in database
repository : database communication (save , update, delete , select)
service : business layout
controller :api endpoint

DTO mean data transfer object

http status :
    200 it ok
    404 not found
    500 internal error

Mapstruct : use for transform DTO to Entity


Testing have twos option :

1. unit test : it is developer work for testing
    we test on
        - controller : create api end point
        - service : it works as business layout
        - repositories : it communicates with data

       - repositories test
          we use h2 database for testing(in memory)

2. Integration test: it is human testing

