package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRegister {

   /* Interface EntityManager: gerencia o contexto de persitência de dados.
   * @PersistenceContext: é a anotação responsável em injetar a interface EntityManager.
   * Dessa forma, é possível salvar objetos no database, fazer consultas e etc */
    @PersistenceContext
    private EntityManager manager;

    /* Método para listar a lista de Kitchen:
    * 1. Usando o JPQL para fazer consultas em objetos: "from Kitchen".
    * 2. Utilizando "from Kitchen" significa que quero o objeto Kitchen inteiro em minha listagem.
    * 3. O retorno é realizado em "Kitchen.class" */
    public List<Kitchen> listar() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    /* A anotação @Transactional = representa a transação que precisa ser feita no database.
    *  1. Inserção direta no database sem JPA: insert into kitchen (nome) values (kitchen = tabela e (nome)= seriam as colunas)
    *  2. Com JPA: return manager.merge(entity) = colocando a entity, kitchen, no contexto de persistência.
    *  3. return = representa o retorno da instância persistida.
    *  4. O método "merge" não altera a instância no parâmetro: quando uma nova cozinha é incluída, um novo "id" é atribuído
    * no database que está configurado com "auto-incremento". Esse "id" não estará atribuído na instância de Kitchen em
    * (Kitchen kitchen), mas ele está sendo está sendo atribuído na instância que está retornando pelo método merge.
    * E por isso que Kitchen é que está sendo retornado: */
    @Transactional
    public Kitchen adicionar(Kitchen kitchen) {
        return manager.merge(kitchen);
    }
}

