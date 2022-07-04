package com.tuyo.tuyofood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import com.tuyo.tuyofood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.tuyo.tuyofood.infrastructure.repository.spec.RestaurantSpecs.withFreteGratis;
import static com.tuyo.tuyofood.infrastructure.repository.spec.RestaurantSpecs.withNomeSemelhante;

/* 1. CriteriaQuery: é responsável por compor as cláusulas.
 * E ela possui todos os métodos de cláusulas do banco: from, where, group by e etc...
 *  3. CriteriaBuilder: é uma instância necessária quando se usa CriteriaQuery.
 * Ele funciona como uma fábrica de construção de elementos necessários para fazer consulta.
 *  4. builder.createQuery(Restaurant.class): traduzindo = falo pra fábrica(instância 'buider')
 * que preciso de uma 'query' para uma 'query' de Restaurant. E daí ele me dá uma instância
 * de uma 'CriteriaQuery' de Restaurant.
 *  5. criteria.from: é o mesmo que // from Restaurant.
 *  6. O CriteriaBuilder possui vários métodos que criam expressões para os nossos critérios como:
 *  a.avg b.concat c.cont d.etc
 *  7. getResultList: retorna uma lista de Restaurant
 *  8. where: dentro dele há varios Predicates(predicados), ou seja, imaginar isso como um filtro.
 *  9. Predicate(predicados): É uma instância.
 * Ex: where x = y and y > 3 (cada uma dessa é um filtro. Um predicado).
 *  10. Ao usar o "find": estamos usando o like, ex: builder.like(root.get("nome"), "%" + nome + "%");
 *  11. like: recebe como primeiro parâmetro a "propriedade"(mas não passamos String aqui)
 * e no segundo parâmetro o "valor". Mas em propriedade, a gente precisa dizer de onde
 * vem essa propriedade, ou seja, de qual entidade que eu quero essa propriedade.
 * Consulta: poder ter várias entidades vinculadas. Ex. Join para outras entidades.
 *  12. Root: ele é do tipo Restaurant e é retornado por ".from(Restaurant.class)".
 * Ele significa, por exemplo, dentro de uma consulta JPQL "from Restaurant", o Root é o "Restaurant".
 * Ou seja, o root(a raiz) de "from Restaurant" é o Restaurant.
 *  13. root.get("nome"): a representação da propriedade "nome" foi pega(.get) dentro do "root Restaurant".
 * e o segundo parâmetro "nome" recebido como parâmetro dentro do método "find". E é usado com
 * "%" + nome + "%" porque queremos o "like".
 *  14. greaterThanOrEqualTo: maior-que-ou-igual-a.
 *  15. lessThanOrEqualTo: menor-que-ou-igual-a.
 *  16. var predicates: cria uma lista de predicates dinâmica. E não precisa especificar predicado por
 * predicado.
 *  17. toArray(new Predicate[0]:
 * a. Convertendo uma arrayList em array
 * b. Passando um array vazio.
 * c. Dessa forma ele já retorna uma instância de um array preenchido com todos os predicates que estão na lista.
 *  18. var: pode substituir sem problemas o CriteriaBuilder e o TypedQuery.
 *  19. @Lazy: informa ao Container do Spring que só instancie as dependências injetadas no momento que for preciso. Isso evita o erro
 * de dependência circular.
 * Dependência Circular: é quando a classe RestaurantRepositoryImpl, é instanciada pelo container do Spring, ela vê
 * as dependências que precisa, e instancia as dependências. Só que como a dependência é RestaurantRepository, e esse vai instanciar o
 * RestaurantRepositoryImpl, fica nessa dependência circular.
 */


@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String nome,
                                 BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var builder = manager.getCriteriaBuilder();

        // Consulta Dinâmica com CriteriaAPI:
        var criteria = builder.createQuery(Restaurant.class);
        var root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        var query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreteGratis(String nome) {
        return restaurantRepository.findAll(withFreteGratis()
                .and(withNomeSemelhante(nome)));
    }

    // 1. Consulta simples exemplo com método from:

        /* CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        criteria.from(Restaurant.class);

         TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();*/

    // 2. Consulta exemplo com método from e where usando Root e Predicados:

        /*CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        // Fazer o bind com três predicates(predicados): nomePredicate, taxaInicialPredicate e taxaFinalPredicate
        Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");

        Predicate taxaInicialPredicate = builder
                .greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);

        Predicate taxaFinalPredicate = builder
                .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);

        // nomePredicate 'and' taxaInicialPredicate 'and' taxaFinalPredicate
        criteria.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();

        */

}
