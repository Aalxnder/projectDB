package com.project.BasesDeDatos.projectDB.repositorios;

import com.project.BasesDeDatos.projectDB.models.Testigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestigoRepositorio extends JpaRepository<Testigo, Integer>
{
    List<Testigo> findByDelitoId(Integer idDelito);
}
