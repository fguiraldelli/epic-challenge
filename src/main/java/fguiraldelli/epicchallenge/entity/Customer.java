package fguiraldelli.epicchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerIdOwner;
    @Column(nullable = false, unique = true)
    private String idCard;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private String address;

}
