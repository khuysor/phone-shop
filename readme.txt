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

    before we test we need to separate it
      1.1 - repositories test
          we use h2 database for testing(in memory)
          we don't test on method that create from library
          we test only method that we create

      1.2 - service test
          we use mock test

2. Integration test: it is human testing




