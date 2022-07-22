package com.tuyo.tuyofood.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuyo.tuyofood.domain.entity.embeddables.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
* propriedade address é do tipo Embeddedable que torna a classe Address parte dessa entidade.
*  7. @JsonIgnore: ignora a resposta em Json para testar no postman. Se quiser ver o teste completo do @Embedded
* remover ou comentar o @JsonIgnore. Mas no DataBase continua do mesmo jeito.
*  8. *IMPORTANTE*: Ambas propriedades: tanto address quanto paymentForms precisam ser adicionadas no parâmetro
* do método atualizar de RestaurantController para que ao adicionarem novos dados,
* não sejam deletados os antigos.
*  9. LocalDateTime: Representa uma data/hora sem fuso-horário(timestamp) definido
*  10. nullable = false: mostra que as propriedades são obrigatórias. E ainda as usamos porque ainda estamos
* utilizando o "generate ddl" para gerar o banco de dados automaticamente. E isso também faz com que ele crie a
* uma coluna "notNull".
*  11. Generate DDL: geração do banco de dados automaticamente. A configuração é feita no application.properties
*  12. Datas: A lógica é a seguinte, quando salvar um restaurant, é preciso obter uma nova instância de
* LocalDateTime que represente aquele exato momento(data/hora) que precisa ser atribuída.
*  13. dataCadastro: sempre que criar um novo Restaurant, atribui um dataCadastro.
*  14. dataAtualizacao: é atribuição de data/hora a partir do dataCadastro. Ou seja, não é a primeira data/hora
* mas as últimas data/hora.
*  15. As ações acima poderiam ser feitas na classe service no sentido de ter a opção de quando usar uma ou outra
* função.
*  16. @CreationTimestamp: anotação do hibernate. Informa que a propriedade anotada, dataCadastro, deve ser atribuída
* com uma hora e data atual.
*  17. @UpdateTimestamp: anotação do hibernate. Informa que a hora e data atual deve ser atribuída a
* propriedade dataAtualizacao sempre que a propriedade for atualizada.
*  18. columnDefinition = "datetime(6)": mostra a precisão.
*  18. columnDefinition = "datetime": ele não cria precisão de data com os milisegundos.
* */

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @Embedded
    private Address address;

    //postgresql - comentando o @JsonIgnore, ele irá mostrar a dataCadastro e a dataAtualizacao
    //@JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dataCadastro;

    //@JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dataAtualizacao;

    //mysql
   /* @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;*/

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_form",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_form_id"))
    private List<PaymentForm> paymentForms = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus = new ArrayList<>();

}
