package com.project.BasesDeDatos.projectDB.DataAccessObject;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface AlmacenServicio
{
    void iniciarAlmacenDeArchivos();
    String almacenarArchivo(MultipartFile archivo);
    Path cargarArchivo(String nombreArchivo);
    Resource cargarComoRecurso(String nombreArchivo);
    void eliminarArchivo(String nombreArchivo);
}
