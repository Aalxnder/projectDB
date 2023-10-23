package com.project.BasesDeDatos.projectDB.controllers;

import com.project.BasesDeDatos.projectDB.DataAccessObject.AlmacenServicioimp;
import com.project.BasesDeDatos.projectDB.models.Victima;
import com.project.BasesDeDatos.projectDB.repositorios.VictimaRepositorio;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Victima")
public class VictimaController
{
    @Autowired
    private VictimaRepositorio victimaRepositorio;

    @Autowired
    private AlmacenServicioimp servicio;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "nombreVictima", size = 5)Pageable pageable)
    {
        Page<Victima> victimas = victimaRepositorio.findAll(pageable);
        return new ModelAndView("Victimas/index")
                .addObject("victimas",victimas);
    }
    @GetMapping("/victimas/nuevo")
    public ModelAndView mostrarFormularioDeNuevaVictima()
    {
        return new ModelAndView("Victimas/nueva-victima")
                .addObject("victima", new Victima());
    }
    @PostMapping("/victimas/nuevo")
    public ModelAndView registrarVictima(@Validated Victima victima, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors() || victima.getPortada().isEmpty())
        {
            if(victima.getPortada().isEmpty())
            {
                bindingResult.rejectValue("portada","error.portada","Seleccione una imagen");
            }
            return new ModelAndView("Victimas/nueva-victima")
                    .addObject("victima", victima);
        }
        String rutaPortada = servicio.almacenarArchivo(victima.getPortada());
        victima.setRutaPortada(rutaPortada);

        victimaRepositorio.save(victima);
        return new ModelAndView("redirect:/Victima");
    }
    @GetMapping("/victimas/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarVictima(@PathVariable Integer id)
    {
        Victima victima = victimaRepositorio.getOne(id);
        return new ModelAndView("Victimas/editar-victima")
                .addObject("victima",victima);
    }
    @PostMapping("/victimas/{id}/editar")
    public ModelAndView actualizarVictima(@PathVariable Integer id, @Validated Victima victima, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Victimas/editar-victima")
                    .addObject("victima", victima);

        }
        Victima victimaDB = victimaRepositorio.getOne(id);
        victimaDB.setNombreVictima(victima.getNombreVictima());
        victimaDB.setApellidosVictima(victima.getApellidosVictima());
        victimaDB.setFechaNacimiento(victima.getFechaNacimiento());
        victimaDB.setGenero(victima.getGenero());
        victimaDB.setDireccion(victima.getDireccion());
        victimaDB.setNumeroDeIdentificacion(victima.getNumeroDeIdentificacion());
        if(!victima.getPortada().isEmpty())
        {
            servicio.eliminarArchivo(victimaDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(victima.getPortada());
            victimaDB.setRutaPortada(rutaPortada);
        }
        victimaRepositorio.save(victimaDB);
        return new ModelAndView("redirect:/Victima");
    }

    @PostMapping ("/victimas/{id}/eliminar")
    public String eliminarVictima(@PathVariable Integer id)
    {
        Victima victima = victimaRepositorio.getOne(id);
        victimaRepositorio.delete(victima);
        servicio.eliminarArchivo(victima.getRutaPortada());

        return "redirect:/Victima";
    }
}
