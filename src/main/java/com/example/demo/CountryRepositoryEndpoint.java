package com.example.demo;

import com.example.demo.generated.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Responses need to have suffix Response
 */
@Endpoint
public class CountryRepositoryEndpoint {

    static final String NAMESPACE_URI = "http://example.com/demo";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryRepositoryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeCountryRequest")
    @ResponsePayload
    public RemoveCountryResponse removeCountry(@RequestPayload RemoveCountryRequest request) {
        final RemoveCountryResponse response = new RemoveCountryResponse();
        final Country removed = countryRepository.removeCountry(request.getName());
        response.setRemoved(removed);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
    @ResponsePayload
    public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
        final AddCountryResponse addCountryResponse = new AddCountryResponse();
        final boolean added = countryRepository.addCountry(request.getName(), request.getCapital(),
                request.getPopulation(), request.getCurrency());
        addCountryResponse.setSuccess(added);
        return addCountryResponse;
    }

}
