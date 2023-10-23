package com.project.BasesDeDatos.projectDB.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Table(name="agresores")
public class Agresores
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idagresor") @Setter @Getter
    private Integer id;

    @Setter @Getter @Column(name="nombre")
    @NotBlank
    private String nombreAgresor;

    @Setter @Getter @Column(name="apellidos")
    private String apellidosAgresor;

    @NotNull
    @Setter @Getter @Column(name="fechanacimiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNacimiento;

    @Setter @Getter @Column(name="genero")
    private String genero;

    @Setter @Getter @Column(name="direccion")
    private String direccion;

    @NotNull
    @Setter @Getter @Column(name="numerodeidentificacion")
    private Integer numeroDeIdentificacion;

    @Setter @Getter @Column(name="delitocometido")
    @NotBlank
    private String delitoCometido;

    @Setter @Getter @Column(name="rutaportada")
    private String rutaPortada;

    @Transient
    @Setter @Getter
    private MultipartFile portada;

    public Agresores()
    {
        super();
    }

    public Agresores(Integer id, String nombreAgresor, String apellidosAgresor, LocalDate fechaNacimiento, String genero, String direccion, Integer numeroDeIdentificacion, String delitoCometido, String rutaPortada, MultipartFile portada) {
        super();
        this.id = id;
        this.nombreAgresor = nombreAgresor;
        this.apellidosAgresor = apellidosAgresor;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
        this.delitoCometido = delitoCometido;
        this.rutaPortada = rutaPortada;
        this.portada = portada;
    }

    public Agresores(String nombreAgresor, String apellidosAgresor, LocalDate fechaNacimiento, String genero, String direccion, Integer numeroDeIdentificacion, String delitoCometido, String rutaPortada, MultipartFile portada) {
        this.nombreAgresor = nombreAgresor;
        this.apellidosAgresor = apellidosAgresor;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
        this.delitoCometido = delitoCometido;
        this.rutaPortada = rutaPortada;
        this.portada = portada;
    }
}
