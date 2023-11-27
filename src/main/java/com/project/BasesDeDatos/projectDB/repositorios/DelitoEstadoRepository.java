package com.project.BasesDeDatos.projectDB.repositorios;

import com.project.BasesDeDatos.projectDB.models.Delito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DelitoEstadoRepository extends JpaRepository<Delito, Long>
{
    List<Delito> findByEstado(String estado);
}
