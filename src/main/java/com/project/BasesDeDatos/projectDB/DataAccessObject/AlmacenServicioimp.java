package com.project.BasesDeDatos.projectDB.DataAccessObject;

import com.project.BasesDeDatos.projectDB.utils.AlmacenException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AlmacenServicioimp implements AlmacenServicio
{
    @Value("${storage.location}")
    private String storageLocation;

    //esta sirve para indicar que este metodo se va a ejecutar cada vez que halla una nueva instancia de esta esta clase
    @PostConstruct
    @Override
    public void iniciarAlmacenDeArchivos()
    {
        try
        {
            Files.createDirectories(Paths.get(storageLocation));
        }
        catch(IOException excepcion)
        {
            throw new AlmacenException("Error al inicializar la ubicación en el almacen de archivos");
        }
    }

    @Override
    public String almacenarArchivo(MultipartFile archivo)
    {
        String nombreArchivo = archivo.getOriginalFilename();
        if(archivo.isEmpty())
        {
            throw new AlmacenException("No se puede almacenar un archivo vacio");
        }
        try
        {
            InputStream inputStream = archivo.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);

        }
        catch (IOException excepcion)
        {
            throw new AlmacenException("Error al almacenar el archivo " + nombreArchivo,excepcion);
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String nombreArchivo)
    {
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }
    @Override
    public Resource cargarComoRecurso(String nombreArchivo)
    {
        try
        {
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());

            if(recurso.exists() || recurso.isReadable())
            {
                return recurso;
            }
            else
            {
                throw new AlmacenException("No se pudo encontrar el archivo " + nombreArchivo);
            }
        }
        catch (MalformedURLException excepcion)
        {
            throw new AlmacenException("No se pudo encontrar el archivo " + nombreArchivo,excepcion);
        }
    }

    @Override
    public void eliminarArchivo(String nombreArchivo)
    {
        Path archivo = cargarArchivo(nombreArchivo);
        try
        {
            FileSystemUtils.deleteRecursively(archivo);
        }
        catch (Exception excepcion)
        {
            System.out.println(excepcion);
        }
    }
}
