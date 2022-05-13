package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import org.springframework.stereotype.Component;

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
}
