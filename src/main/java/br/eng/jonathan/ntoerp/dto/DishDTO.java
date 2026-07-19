package br.eng.jonathan.ntoerp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {

    private Long dishId;

    @NotBlank(message = "{DISH.NAME_NOT_BLANK}")
    @Size(max = 255, message = "{DISH.NAME_SIZE}")
    private String name;

    @NotNull(message = "{DISH.PRICE_NOT_NULL}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{DISH.PRICE_MIN}")
    private BigDecimal price;

    @NotBlank(message = "{DISH.DESCRIPTION_NOT_BLANK}")
    private String description;

    @NotBlank(message = "{DISH.CATEGORY_NOT_BLANK}")
    @Size(max = 100, message = "{DISH.CATEGORY_SIZE}")
    private String category;

    @Pattern(regexp = "^[yn]?$", message = "{DISH.HAS_ADDITIONAL_OPTIONS_PATTERN}")
    private String hasAdditionalOptions;

    private byte[] picture;
}
