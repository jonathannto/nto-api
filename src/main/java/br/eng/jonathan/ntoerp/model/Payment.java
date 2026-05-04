package br.eng.jonathan.ntoerp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pay")
    private Long paymentId;

    @Column(nullable = false)
    private BigDecimal totalPay;

    @Column(name = "dt_ini_pay", nullable = false)
    private OffsetDateTime initialDatePay;

    @Column(name = "dt_fin_pay")
    private OffsetDateTime finalDatePay;

    @Column(name = "sts_pay", nullable = false, length = 45)
    private String statusPay;

    @ManyToOne
    @JoinColumn(name = "id_pay_tp", nullable = false)
    private PaymentType paymentType;

}
