package fguiraldelli.epicchallenge.service;

import fguiraldelli.epicchallenge.dto.MobileSubscriberDto;

import java.util.List;

public interface MobileSubscriberService {

    MobileSubscriberDto createMobileSubscriber (MobileSubscriberDto mobileSubscriberDto);

    MobileSubscriberDto updateMobileSubscriber (MobileSubscriberDto mobileSubscriberDto);

    List<MobileSubscriberDto> getAllMobileSubscribers();


}
