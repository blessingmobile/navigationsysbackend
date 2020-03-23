# Navigation System Backend

Step 1

To run test coverage tool. Currently test coverage is 73%. Need to test SOAP Client and REST Controller to achieve above 80% code coverage.

mvn test jacoco:report 

Then locate the Jacoco index.html file "\target\site\jacoco", then open it on web browser

Step 2

Please find steps to run the Navigation System below.

First paste the excel HR-Offsite_AssignmentV3_0.xlsx to the root folder of this project. The test cases use this file, therefore the tests will fail and 
the application will fail to startup if this file is not there. Alternatively you can comment out the test cases... Thereafter please run command below:

mvn spring-boot:run

Step 3 

The shortest path algorithm is wrapped using SOAP. To test it please find SOAP envelope details below:

WSDL

http://localhost:8081/navigationsys/ws/distance.wsdl

ENDPOINT (POST)

http://localhost:8081/navigationsys/ws

REQUEST

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gen="www.blessingmobile.com/navigationsys/gen">
   <soapenv:Header/>
   <soapenv:Body>
      <gen:getDistanceRequest>
         <gen:origin>0</gen:origin>
         <gen:destination>2</gen:destination>
         <gen:trafficEnabled>true</gen:trafficEnabled>
      </gen:getDistanceRequest>
   </soapenv:Body>
</soapenv:Envelope>

RESPONSE

<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:getDistanceResponse xmlns:ns2="www.blessingmobile.com/navigationsys/gen">
         <ns2:distance>
            <ns2:pathdistance>2.79000000000000003552713678800500929355621337890625</ns2:pathdistance>
            <ns2:directions>[Earth , Jupiter ]</ns2:directions>
         </ns2:distance>
      </ns2:getDistanceResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
