package fguiraldelli.epicchallenge.mapper;

import fguiraldelli.epicchallenge.dto.MobileSubscriberDto;
import fguiraldelli.epicchallenge.entity.MobileSubscriber;

public class MobileSubscriberMapper {
    public static MobileSubscriberDto mapsToMobileSubscriberDto(MobileSubscriber mobileSubscriber) {
        MobileSubscriberDto mobileSubscriberDto = new MobileSubscriberDto(
                mobileSubscriber.getId(),
                mobileSubscriber.getMsisdn(),
                mobileSubscriber.getCustomer_id_owner(),
                mobileSubscriber.getCustomer_id_user(),
                mobileSubscriber.getService_type(),
                mobileSubscriber.getService_start_date()
        );
        return mobileSubscriberDto;
    }

    public static MobileSubscriber mapsToMobileSubscriber(MobileSubscriberDto mobileSubscriberDto) {
        MobileSubscriber mobileSubscriber = new MobileSubscriber(
                mobileSubscriberDto.getId(),
                mobileSubscriberDto.getMsisdn(),
                mobileSubscriberDto.getCustomerIdOwner(),
                mobileSubscriberDto.getCustomerIdUser(),
                mobileSubscriberDto.getServiceType(),
                mobileSubscriberDto.getService_start_date()
        );
        return mobileSubscriber;
    }
}
