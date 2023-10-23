package com.project.BasesDeDatos.projectDB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sesion")
public class SesionController
{
    @GetMapping("/login")
    public ModelAndView verPaginaDeInicioDeSesion()
    {
        return new ModelAndView("sesion/login");
    }
    @GetMapping("/registrar")
    public ModelAndView verPaginaDeRegistro()
    {
        return new ModelAndView("sesion/registrar");
    }
}
