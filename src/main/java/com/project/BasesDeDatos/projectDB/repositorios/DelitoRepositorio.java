package com.project.BasesDeDatos.projectDB.repositorios;

import com.project.BasesDeDatos.projectDB.models.Delito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DelitoRepositorio extends JpaRepository<Delito, Integer>
{
    @Query("SELECT d FROM Delito d WHERE d.nombreDelito LIKE %:nombreDelito%")
    Page<Delito> findByNombreDelitoContaining(@Param("nombreDelito") String nombreDelito, Pageable pageable);
}
