package com.tuyo.tuyofood.infrastructure.repositoryimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/* 1. Para fazer uma consulta JPQL dinâmica, é necessário trabalhar com concatenação.
*  2. StringBuilder: é uma classe usada para criar um mutável, ou em outras palavras,
* uma sucessão modificável de caracteres. Concatenar com StringBuilder é bem melhor devido a isso.
*  3. 0 = 0: é sempre true (verdadeiro).
*  4. Colocar o espaço em:
*  a. 0 = 0 ": não esquecer o espaço depois de 0 = 0 e antes das ".
*  b. nome ": não esquecer o espaço depois de nome " e antes das ".
*  c. taxaInicial ": não esquecer o espaço depois de taxaInicial " e antes das "
*  5. StringUtils: é uma classe utilitária que tem o método hasLenght(ele verifica se ele, o nome, não está
* nulo e não está vazio, ou seja, se o "length"(tamanho) da String é maior que "0".
*  6. append(): é usado para anexar a representação de string de algum argumento à sequência.
*  7. toString(): é obrigatório o uso devido ao StringBuilder.
*  8. createQuery: chama uma consulta "tipada"(TypedQuery)
*  9. HashMap<String, Object>: é uma nova variável de mapeamento. Onde "String" é a chave (nome do parâmetro)
* e Object é o valor(é o valor que tem que informar).  */

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String nome,
                                  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 0 = 0 ");

        /* Nova variável HashMap (mapa): */

        var parametros = new HashMap<String, Object>();

        /* Adiciona ao Mapa os parâmetros que precisam fazer o bind na query: */

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        /* Faz o Loop (forEach que é uma expressão Lambda) e atribui os parâmetros(construídos acima): */

        TypedQuery<Restaurant> query = manager
                .createQuery(jpql.toString(), Restaurant.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }

}
