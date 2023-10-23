package com.project.BasesDeDatos.projectDB.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TipoDelito
{
    @Id
    @Column(name = "idtipodelito")
    @Setter @Getter
    private Integer id;

    @Setter @Getter
    private String titulo;

    public TipoDelito(Integer id, String titulo)
    {
        super();
        this.id = id;
        this.titulo = titulo;
    }
    public TipoDelito()
    {
        super();
    }

    public TipoDelito(String titulo)
    {
        super();
        this.titulo = titulo;
    }
    public TipoDelito(Integer id)
    {
        super();
        this.id = id;
    }
}
