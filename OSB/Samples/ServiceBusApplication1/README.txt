Simple OSB Proxy and Pipeline

The pipeline has a global error handler to set a custom error message. 

The accompanying soapui project includes a well-formed JSon request
and a malformed request. 
The malformed request sends a custom response from the global error handler
(instead of the default system error message)