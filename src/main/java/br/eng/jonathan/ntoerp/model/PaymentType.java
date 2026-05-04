package br.eng.jonathan.ntoerp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_payment_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pay_tp", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private Long paymentTypeId;

    @Column(name = "name_pay_tp", nullable = false, length = 50)
    private String name;

    @Column(name = "desc_pay_tp", length = 45)
    private String description;

}
