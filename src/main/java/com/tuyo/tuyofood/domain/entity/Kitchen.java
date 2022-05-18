package com.tuyo.tuyofood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/* @Getter, @Setter e @EqualsAndHashCode = anotações para Lombok, ou seja, métodos get e set são gerados automaticamente */
/*@Getter
@Setter
@EqualsAndHashCode*/
/* Usando o Data, inclui todos os anteriores: @Getter, @Setter e @EqualsAndHashCode e ainda o toString */
/* Mesmo com o Data sendo usado, o EqualsAndHashCode será usado apenas se incluso explicitamente:
*  1. @EqualsAndHashCode(onlyExplicitlyIncluded = true)
*  2. @EqualsAndHashCode.Include: cria um EqualsAndHashCode apenas se eu estiver usando o ID como referência para fazer o hashcode */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kitchen {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

}
