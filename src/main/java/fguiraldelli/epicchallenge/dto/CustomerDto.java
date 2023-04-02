package fguiraldelli.epicchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Long id;
    @NotEmpty(message = "Customer id card should not be null or empty")
    @Size(min=7, max = 7)

    private String idCard;
    @NotEmpty(message = "Customer name should not be null or empty")
    private String name;
    @NotEmpty(message = "Customer surname should not be null or empty")
    private String surname;
    private String address;
}
