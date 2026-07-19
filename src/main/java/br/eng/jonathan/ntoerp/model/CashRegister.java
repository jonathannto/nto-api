package br.eng.jonathan.ntoerp.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cash_register")
public class CashRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_csh_reg")
    private Long cashRegisterId;

    @Column(name = "init_dt_csh_reg", nullable = false)
    private OffsetDateTime initDate;

    @Column(name = "end_dt_csh_reg")
    private OffsetDateTime endDate;

    @Column(name = "init_blc_csh_reg", nullable = false, precision = 10, scale = 2)
    private BigDecimal initialBalance;

    @Column(name = "end_blc_csh_reg", precision = 10, scale = 2)
    private BigDecimal endBalance;

    @Column(name = "total_sale_csh_reg", precision = 10, scale = 2)
    private BigDecimal totalSales;

    @Column(name = "total_with_csh_reg", precision = 10, scale = 2)
    private BigDecimal totalWithdrawals;

    @Column(name = "status_csh_reg", length = 20, nullable = false)
    private String status;

    @Column(name = "notes_csh_reg", columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usr", nullable = false, foreignKey = @ForeignKey(name = "fk_tb_cash_register_tb_user"))
    private User user;
}
