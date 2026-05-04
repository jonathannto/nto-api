package br.eng.jonathan.ntoerp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeDTO {

    private Long paymentTypeId;

    @NotBlank(message = "{PAYMENT_TYPE.NAME_NOT_BLANK}")
    @Size(max = 50, message = "{PAYMENT_TYPE.NAME_SIZE}")
    private String name;

    @Size(max = 45, message = "{PAYMENT_TYPE.DESCRIPTION_SIZE}")
    private String description;

}