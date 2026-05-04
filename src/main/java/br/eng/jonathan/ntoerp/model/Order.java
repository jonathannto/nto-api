package br.eng.jonathan.ntoerp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ord", nullable = false)
    private Long orderId;

    @Column(name = "status_ord", length = 20)
    private String status;

    @Column(name = "date_ord", nullable = false)
    private OffsetDateTime creationDate;

    @Column(name = "tab_num_ord", nullable = false)
    private Long tableNumber;

    @Column(name = "total_prc_ord", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "paymt_sts_ord", length = 20, nullable = false)
    private String paymentStatus;

    @Column(name = "end_date_ord")
    private OffsetDateTime endDate;

    @Column(name = "notes_ord")
    private String notes;

    @Column(name = "id_csh_reg", nullable = false)
    private Long cashRegisterId;

    @Column(name = "id_usr", nullable = false)
    private Long userId;
}
