package com.gaelle.satefynetalerts.services;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface UrlsService {
    Map<String, Object> getChildrenList(Long id);
    Map<String, Object> getFireStationByStationNumber(String station);
}
