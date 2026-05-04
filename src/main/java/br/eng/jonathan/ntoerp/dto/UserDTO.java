package br.eng.jonathan.ntoerp.dto;

import br.eng.jonathan.ntoerp.model.Address;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    @NotNull(message = "{USER.NAME_NOT_BLANK}")
    @Size(min = 6, max = 255, message = "{USER.NAME_SIZE}")
    private String name;

    @Pattern(regexp = "^(|\\d{11})$", message = "{USER.CPF_PATTERN.regexp}")
    private String cpf;

    @NotBlank(message = "{USER.EMAIL_NOT_BLANK}")
    @Email(message = "{USER.EMAIL_EMAIL}")
    private String email;

    @NotBlank(message = "{USER.PHONE_NUMBER_NOT_BLANK}")
    @Size(min = 10, max = 14, message = "{USER.PHONE_NUMBER_SIZE}")
    private String phoneNumber;

    @Embedded
    private Address address;

    private OffsetDateTime birthDate;

    @NotBlank(message = "{USER.USER_NAME_NOT_BLANK}")
    private String username;

    @NotBlank(message = "{USER.PASSWORD_NOT_BLANK}")
    private String password;

    private String securityQuestion;

    private String securityAnswer;

    private String userType;

    private OffsetDateTime createdAt;

    private String status;

    private byte[] profilePicture;

    private String additionalNotes;
}
