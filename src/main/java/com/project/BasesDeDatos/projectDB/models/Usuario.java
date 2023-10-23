package com.project.BasesDeDatos.projectDB.models;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="investigador")
public class Usuario
{
    @Getter @Setter @Column(name="investigadorid")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter @Column(name="nombre")
    private String nombre;

    @Getter @Setter @Column(name="apellido")
    private String apellido;

    @Getter @Setter @Column(name="rango")
    private String rango;

    @Getter @Setter @Column(name="numerodeidentificacion")
    private int numerodeidentificacion;

    @Getter @Setter @Column(name="departamento")
    private String departamento;

    @Getter @Setter @Column(name="email")
    private String email;

    @Getter @Setter @Column(name="contrasenia")
    private String password;

}
