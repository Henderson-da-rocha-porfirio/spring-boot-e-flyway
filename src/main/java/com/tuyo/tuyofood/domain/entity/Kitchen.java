package com.tuyo.tuyofood.domain.entity;

import javax.persistence.*;

@Entity
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "nom_cozinha", length = 30) => especificando o tamanho de caracteres*/
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kitchen)) return false;

        Kitchen kitchen = (Kitchen) o;

        return getId().equals(kitchen.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
