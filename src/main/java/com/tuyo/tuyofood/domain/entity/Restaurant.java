package com.tuyo.tuyofood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/* 1. Mudando o nome da coluna: @JoinColumn(name =  "kitchen_legacy")
*  2. Restaurant é o "dono" desse relacionamento "bidirecional". */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

 // @JsonIgnore
    @ManyToOne
    @JoinColumn(name =  "kitchen_id", nullable = false)
    private Kitchen kitchen;

}
