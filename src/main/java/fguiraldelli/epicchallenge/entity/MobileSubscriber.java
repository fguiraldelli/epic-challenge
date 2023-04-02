package fguiraldelli.epicchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mobile_subscribers")
public class MobileSubscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String msisdn;
    private Long customer_id_owner;
    private Long customer_id_user;
    @Enumerated(value = EnumType.STRING)
    private ServiceType service_type;
    private Long service_start_date;
}
