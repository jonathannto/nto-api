package br.eng.jonathan.ntoerp.dto;

import br.eng.jonathan.ntoerp.model.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long paymentId;

    @NotNull(message = "{PAYMENT.TOTAL_PAY_NOT_NULL}")
    @Digits(integer = 10, fraction = 2, message = "{PAYMENT.TOTAL_PAY_DIGITS}")
    private BigDecimal totalPay;

    @NotNull(message = "{PAYMENT.DT_INI_PAY_NOT_NULL}")
    private OffsetDateTime initialDatePay;

    private OffsetDateTime finalDatePay;

    @NotBlank(message = "{PAYMENT.STS_PAY_NOT_BLANK}")
    @Size(max = 45, message = "{PAYMENT.STS_PAY_SIZE}")
    private String statusPay;

    @NotNull(message = "{PAYMENT.ID_PAY_TP_NOT_NULL}")
    private Long paymentTypeId;
}
