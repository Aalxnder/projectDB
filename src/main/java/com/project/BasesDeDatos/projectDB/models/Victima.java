package com.project.BasesDeDatos.projectDB.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Table(name="victima")
public class Victima
{
    @Id @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idvictima")
    private Integer id;

    @NotNull
    @Setter @Getter @Column(name="nombre")
    private String nombreVictima;

    @Setter @Getter @Column(name="apellido")
    private String apellidosVictima;

    @Setter @Getter @Column(name="fechanacimiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNacimiento;

    @NotNull
    @Setter @Getter @Column(name="genero")
    private String genero;

    @Setter @Getter @Column(name="direccion")
    private String direccion;

    @NotNull
    @Setter @Getter @Column(name="numerodeidentificacion")
    private Integer numeroDeIdentificacion;

    @Setter @Getter @Column(name="rutaportada")
    private String rutaPortada;

    @Transient
    @Setter @Getter
    private MultipartFile portada;

    public Victima()
    {
        super();
    }

    public Victima(Integer id, String nombreVictima, String apellidosVictima, LocalDate fechaNacimiento, String genero, String direccion, Integer numeroDeIdentificacion, String rutaPortada, MultipartFile portada) {
        super();
        this.id = id;
        this.nombreVictima = nombreVictima;
        this.apellidosVictima = apellidosVictima;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
        this.rutaPortada = rutaPortada;
        this.portada = portada;
    }

    public Victima(String nombreVictima, String apellidosVictima, LocalDate fechaNacimiento, String genero, String direccion, Integer numeroDeIdentificacion, String rutaPortada, MultipartFile portada) {
        this.nombreVictima = nombreVictima;
        this.apellidosVictima = apellidosVictima;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
        this.rutaPortada = rutaPortada;
        this.portada = portada;
    }
}
