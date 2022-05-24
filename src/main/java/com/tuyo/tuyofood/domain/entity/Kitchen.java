package com.tuyo.tuyofood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/* 1. Modificando o nome da representação sem mudar a propriedade do nome de domínio: @JsonProperty.
*  2. Ignorando a propriedade: @JsonIgnore
*  3. Modificando o nome do elemento para XML: @JsonRootName */
@JsonRootName("kitchen")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kitchen {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

}
