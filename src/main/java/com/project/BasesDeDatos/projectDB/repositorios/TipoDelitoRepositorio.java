package com.project.BasesDeDatos.projectDB.repositorios;

import com.project.BasesDeDatos.projectDB.models.TipoDelito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDelitoRepositorio extends JpaRepository<TipoDelito,Integer>
{
}
