package com.blessingmobile.navigationsys.soap.client;
import com.blessingmobile.navigationsys.gen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DistanceEndpointWS {

    private static final String NAMESPACE_URI = "www.blessingmobile.com/navigationsys/gen";

    private DistanceRepository repository;

    @Autowired
    public DistanceEndpointWS(DistanceRepository repository) {
        this.repository = repository;
    }

    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDistanceRequest")
    @ResponsePayload
    public GetDistanceResponse getCountry(@RequestPayload GetDistanceRequest request) {
        GetDistanceResponse response = new GetDistanceResponse();
        response.setDistance(repository.findDistance(
        request.getOrigin(), request.getDestination(), request.isTrafficEnabled()));
        return response;
    }
}
