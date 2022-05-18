package com.tuyo.tuyofood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

/* Criando o Relacionamento:
*  1. Colocar o Tipo da propriedade: Tipo Kitchen
*  2. ManyToOne: Muitos para Um, ou seja, muitos restaurantes possuem uma cozinha */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @ManyToOne
    private Kitchen kitchen;

}
