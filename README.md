# danish-zipcode-service

This is a danish zipcode rest interface you can use to convert a 
zipcode to a city or a city to a zipcode or the response is a Json
object in the format eg.

{
  "cityZipCode" : "4000",
  "city" : "Roskilde",
  "_links" : {
    "cities" : {
      "href" : "http://localhost:8080/cities"
    },
    "self" : [ {
      "href" : "http://localhost:8080/city/Roskilde"
    }, {
      "href" : "http://localhost:8080/zipcode/4000"
    } ]
  }
}

you can get all the zipcodes and cities in denmark as JSon or xml. 

I have build this service primary to use it in my comming tutorial on
Rest Service implementation.

-Work there needs to be done
- Tutorial
- client side example (javascript)
   
