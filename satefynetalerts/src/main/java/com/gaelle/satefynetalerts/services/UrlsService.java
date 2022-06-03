package com.gaelle.satefynetalerts.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UrlsService {
    Map<String, Object> getChildrenList(Long id);
    Map<String, Object> getFireStationByStationNumber(String station);
    List<String> getPhoneByFireStation(Long id);
    Map<String, Object> getPersonByAddress(Long id);

    Map<String, Object> getPersonsByFirestation(List<Long> stations);
}
