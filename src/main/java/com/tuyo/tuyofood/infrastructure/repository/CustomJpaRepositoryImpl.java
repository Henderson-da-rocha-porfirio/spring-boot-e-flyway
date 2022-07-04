package com.tuyo.tuyofood.infrastructure.repository;

import com.tuyo.tuyofood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

/* * Este é um novo repositório-base customizado:
*  1. "from ": não pode ter entidade fixa porque está sendo construída de forma dinâmica.
*  2. getDomainClass: vem da classe pai SimpleJpaRepository. E ele retorna a classe da entidade que
* representa a entidade.
*  3. getName: está pegando o nome da classe para a qual este repositório existe.
*  4. setMaxResults(1): é o máximo que esta consulta pode retornar.
*  5. getSingleResult: é um único resultado que esta consulta pode retornar.
*  6. Optional.ofNullable: pode ser o retorno de um Optional nulo lá dentro ou com a própria entidade em questão.
*  7. @EnableJpaRepositories: Este repositório customizado precisa ser ativado para mostrar para o Spring Data JPA que a gente um outro
* repositório base customizado para ser usado, ou seja, esta anotação precisa ser colocada abaixo de @SpringBootapplication que está
* na classe Application que contém o método "main". Em nosso caso: TuyoFoodApplication.
*  8. @EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class): ativa os repositórios. Apesar de já virem ativados por padrão, mas
 * deve ser colocado para customizar, mudar o repositório base, na verdade. */

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "from " + getDomainClass().getName();

        T entity = manager.createQuery(jpql, getDomainClass())
                .setMaxResults(1)
                .getSingleResult();

        return Optional.ofNullable(entity);
    }
}
