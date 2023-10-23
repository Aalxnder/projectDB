package com.project.BasesDeDatos.projectDB.utils;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FIleNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public FIleNotFoundException(String mensaje)
    {
        super(mensaje);
    }
    public FIleNotFoundException(String mensaje, Throwable excepcion)
    {
        super(mensaje, excepcion);
    }
}
