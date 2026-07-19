package br.eng.jonathan.ntoerp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usr")
    private Long userId;

    @Column(name = "nam_usr")
    private String name;

    @Column(name = "cpf_usr")
    private String cpf;

    @Column(name = "eml_usr")
    private String email;

    @Column(name = "ph_num_usr")
    private String phoneNumber;

    @Embedded
    private Address address;

    @Column(name = "birth_date_usr")
    private OffsetDateTime birthDate;

    @Column(name = "user_nam_usr")
    private String username;

    @Column(name = "pass_usr")
    private String password;

    @Column(name = "sec_quest_usr")
    private String securityQuestion;

    @Column(name = "sec_answ_usr")
    private String securityAnswer;

    @Column(name = "user_type_usr")
    private String userType;

    @Column(name = "crt_at_dat_usr", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "sts_usr")
    private String status;

    @Column(name = "pro_pic_user", columnDefinition = "bytea")
    private byte[] profilePicture;

    @Column(name = "addit_notes_usr", columnDefinition = "TEXT")
    private String additionalNotes;

}
