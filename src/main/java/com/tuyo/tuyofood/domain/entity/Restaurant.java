package com.tuyo.tuyofood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuyo.tuyofood.domain.entity.embeddables.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* 1. Mudando o nome da coluna: @JoinColumn(name =  "kitchen_legacy")
*  2. Restaurant é o "dono" desse relacionamento "bidirecional".
*  3. @JoinTable(name ... ): "name" é o nome da tabela. Customiza o nome da tabela intermediária.
*  4. joinColumns: define o nome da coluna(restaurant_id) que faz referência a própria classe que estamos
* mapeando(id de Restaurant).
*  5. inverseJoinColumns: define o nome da coluna(id de Restaurant) que é inversa da
* chave estrangeira(id de PaymentForm ).
*  6. @Embedded: A entidade que incorpora a classe incorporavél(Embeddedable). Ou seja, ela mostra que a
* propriedade address é do tipo Embeddedable que torna a classe Address parte dessa entidade. */

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

    @Embedded
    private Address address;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_form",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_form_id"))
    private List<PaymentForm> paymentForms = new ArrayList<>();

}
