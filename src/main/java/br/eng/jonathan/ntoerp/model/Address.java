package br.eng.jonathan.ntoerp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
@Data
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(name = "str_add_usr")
    private String streetAddress;

    @Column(name = "add_num_usr")
    private Long addressNumber;

    @Column(name = "city_usr")
    private String city;

    @Column(name = "sta_usr")
    private String state;

    @Column(name = "zip_code_usr")
    private String zipCode;

}
