package br.eng.jonathan.ntoerp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;

    @NotBlank(message = "{ORDER.STATUS_NOT_BLANK}")
    @Size(max = 20, message = "{ORDER.STATUS_SIZE}")
    private String status;

    @NotNull(message = "{ORDER.CREATION_DATE_NOT_NULL}")
    private OffsetDateTime creationDate;

    @DecimalMin(value = "0.00", message = "{ORDER.TOTAL_PRICE_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{ORDER.TOTAL_PRICE_DIGITS}")
    private BigDecimal totalPrice;

    @NotBlank(message = "{ORDER.PAYMENT_STATUS_NOT_BLANK}")
    @Size(max = 20, message = "{ORDER.PAYMENT_STATUS_SIZE}")
    private String paymentStatus;

    private OffsetDateTime endDate;

    @Size(max = 255, message = "{ORDER.NOTES_SIZE}")
    private String notes;

    @NotNull(message = "{ORDER.TABLE_NUMBER_NOT_NULL}")
    @Min(value = 1, message = "{ORDER.TABLE_NUMBER_MIN}")
    private Long tableNumber;

    @NotNull(message = "{ORDER.CASH_REGISTER_ID_NOT_NULL}")
    private Long cashRegisterId;

    @NotNull(message = "{ORDER.USER_ID_NOT_NULL}")
    private Long userId;
}
