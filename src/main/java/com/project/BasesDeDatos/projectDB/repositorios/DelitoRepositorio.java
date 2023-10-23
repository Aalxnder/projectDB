package com.project.BasesDeDatos.projectDB.repositorios;

import com.project.BasesDeDatos.projectDB.models.Delito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelitoRepositorio extends JpaRepository<Delito, Integer>
{
}
