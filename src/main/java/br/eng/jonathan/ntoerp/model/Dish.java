package br.eng.jonathan.ntoerp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dsh")
    private Long dishId;

    @Column(name = "dish_nam_dsh", nullable = false, length = 255)
    private String name;

    @Column(name = "dish_prc_dsh", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "desc_dsh", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "cat_dsh", nullable = false, length = 100)
    private String category;

    @Column(name = "has_add_dsh", nullable = true, length = 1)
    private Character hasAdditionalOptions;

    @Column(name = "pic_dsh", columnDefinition = "bytea")
    private byte[] picture;
}
