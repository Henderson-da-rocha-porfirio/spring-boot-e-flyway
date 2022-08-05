package com.tuyo.tuyofood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SolicitationItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "preco_total")
    private BigDecimal precoTotal;
    private Integer quantidade;
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Solicitation solicitation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Menu menu;
}
