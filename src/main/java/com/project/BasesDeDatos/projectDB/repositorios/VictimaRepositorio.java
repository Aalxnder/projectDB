package com.project.BasesDeDatos.projectDB.repositorios;

import com.project.BasesDeDatos.projectDB.models.Victima;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VictimaRepositorio extends JpaRepository<Victima, Integer>
{
}
