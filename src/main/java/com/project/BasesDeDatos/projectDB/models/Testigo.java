package com.project.BasesDeDatos.projectDB.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="testigos")
public class Testigo
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idtestigo") @Setter @Getter
    private Integer id;

    @Setter @Getter @Column(name="nombre")
    @NotBlank
    private String nombreTestigo;

    @Setter @Getter @Column(name="apellido")
    private String apellidosTestigo;

    @NotNull
    @Setter @Getter @Column(name="fechanacimiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNacimiento;

    @Setter @Getter @Column(name="genero")
    private String genero;

    @NotNull
    @Setter @Getter @Column(name="numerodeidentificacion")
    private Integer numeroDeIdentificacion;

    @Setter @Getter @Column(name="reporte")
    private String reporte;

    @ManyToOne
    @JoinColumn(name = "delito_testigo")
    @Setter @Getter
    private Delito delito;

    public Testigo() {
        super();
    }

    public Testigo(Integer id, String nombreTestigo, String apellidosTestigo, LocalDate fechaNacimiento, String genero, Integer numeroDeIdentificacion, String reporte) {
        super();
        this.id = id;
        this.nombreTestigo = nombreTestigo;
        this.apellidosTestigo = apellidosTestigo;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
        this.reporte = reporte;

    }

    public Testigo(String nombreTestigo, String apellidosTestigo, LocalDate fechaNacimiento, String genero, Integer numeroDeIdentificacion, String reporte) {
        super();
        this.nombreTestigo = nombreTestigo;
        this.apellidosTestigo = apellidosTestigo;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
        this.reporte = reporte;

    }
}
