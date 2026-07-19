package br.eng.jonathan.ntoerp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashRegisterDTO {

    private Long cashRegisterId;

    @NotNull(message = "{CASH_REGISTER.INIT_DATE_NOT_NULL}")
    private OffsetDateTime initDate;

    private OffsetDateTime endDate;

    @NotNull(message = "{CASH_REGISTER.INITIAL_BALANCE_NOT_NULL}")
    @DecimalMin(value = "0.00", message = "{CASH_REGISTER.INITIAL_BALANCE_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{CASH_REGISTER.INITIAL_BALANCE_DIGITS}")
    private BigDecimal initialBalance;

    @DecimalMin(value = "0.00", message = "{CASH_REGISTER.END_BALANCE_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{CASH_REGISTER.END_BALANCE_DIGITS}")
    private BigDecimal endBalance;

    @DecimalMin(value = "0.00", message = "{CASH_REGISTER.TOTAL_SALES_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{CASH_REGISTER.TOTAL_SALES_DIGITS}")
    private BigDecimal totalSales;

    @DecimalMin(value = "0.00", message = "{CASH_REGISTER.TOTAL_WITHDRAWALS_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{CASH_REGISTER.TOTAL_WITHDRAWALS_DIGITS}")
    private BigDecimal totalWithdrawals;

    @NotBlank(message = "{CASH_REGISTER.STATUS_NOT_BLANK}")
    @Size(max = 20, message = "{CASH_REGISTER.STATUS_SIZE}")
    private String status;

    private String notes;

    @NotNull(message = "{CASH_REGISTER.USER_ID_NOT_NULL}")
    private Long userId;
}
