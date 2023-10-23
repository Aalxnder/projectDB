package com.project.BasesDeDatos.projectDB.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table (name = "delitoscometidos")
public class Delito
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddelito") @Setter @Getter
    private Integer id;

    @Setter @Getter @Column(name = "nombredelito")
    @NotBlank
    private String nombreDelito;

    @Setter @Getter @Column(name = "descripcion")
    private String descripcion;

    @Setter @Getter @Column(name = "idagresor")
    private Integer idAgresor;

    @Setter @Getter @Column(name = "evidencia")
    private String evidencia;

    @Setter @Getter @Column(name = "investigadorid")
    private Integer investigadorId;

    @Setter @Getter @Column(name = "idvictima")
    private Integer idVictima;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    @Setter @Getter @Column(name = "fecha")
    private LocalDate fecha;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tipodelito_delito", joinColumns = @JoinColumn(name = "iddelito"), inverseJoinColumns = @JoinColumn(name = "idtipodelito"))
    private List<TipoDelito> tipoDelitos;

    public List<TipoDelito> getTipoDelitos() {
        return tipoDelitos;
    }

    public void setTipoDelitos(List<TipoDelito> tipoDelitos) {
        this.tipoDelitos = tipoDelitos;
    }

    @Setter @Getter @Column(name = "rutaportada")
    private String rutaPortada;

    @Transient
    @Setter @Getter
    private MultipartFile portada;

    public Delito()
    {
        super();
    }

    public Delito(Integer id, String nombreDelito, String descripcion, Integer idAgresor, String evidencia, Integer investigadorId, Integer idVictima, List<TipoDelito> tipoDelitos, MultipartFile portada) {
        super();
        this.id = id;
        this.nombreDelito = nombreDelito;
        this.descripcion = descripcion;
        this.idAgresor = idAgresor;
        this.evidencia = evidencia;
        this.investigadorId = investigadorId;
        this.idVictima = idVictima;
        this.tipoDelitos = tipoDelitos;
        this.portada = portada;
    }

    public Delito(String nombreDelito, String descripcion, Integer idAgresor, String evidencia, Integer investigadorId, Integer idVictima, List<TipoDelito> tipoDelitos, MultipartFile portada) {
        super();
        this.nombreDelito = nombreDelito;
        this.descripcion = descripcion;
        this.idAgresor = idAgresor;
        this.evidencia = evidencia;
        this.investigadorId = investigadorId;
        this.idVictima = idVictima;
        this.tipoDelitos = tipoDelitos;
        this.portada = portada;
    }
}

