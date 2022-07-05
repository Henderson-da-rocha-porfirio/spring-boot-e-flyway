package com.tuyo.tuyofood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/* 1. Modificando o nome da representação sem mudar a propriedade do nome de domínio: @JsonProperty.
*  2. Ignorando a propriedade: @JsonIgnore
*  3. Modificando o nome do elemento para XML: @JsonRootName
*  4. List: é uma coleção do java para listar vários restaurants.
*  5. mappedBy: a propriedade passada no parâmetro deve ser o nome que foi passada na outra entidade, no caso,
* Restaurant que é o dono desse relacionamento "bidirecional". Na associação @ManyToOne, dentro desse,
* foi passado kitchen, que é a contrária de @OneToMany que está nesta entidade aqui.
*  6. @OneToMany(mappedBy = "kitchen"), traduz dessa forma: faz um @OneToMany para mim que foi mapeado (mappedBy)
* por "kitchen". Onde? Em Restaurant (dentro da entidade)
*  7. @OneToMany: serve, no exemplo abaixo, para especificar algum tipo de cozinha dentro de uma regra de negócio.
*  8. @JsonIgnore: ignora a propriedade restaurants quando for 'serializar' a propriedade cozinha. */

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

    @JsonIgnore
    @OneToMany(mappedBy = "kitchen")
    private List<Restaurant> restaurants = new ArrayList<>();

}
