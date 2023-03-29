package fguiraldelli.epicchallenge.dto;

import fguiraldelli.epicchallenge.entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileSubscriberDto {
    private Long id;
    private String msisdn;
    private Long customerIdOwner;
    private Long customerIdUser;
    private ServiceType serviceType;
    private Long service_start_date;
}
