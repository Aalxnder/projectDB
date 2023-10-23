package com.project.BasesDeDatos.projectDB.controllers;

import com.project.BasesDeDatos.projectDB.DataAccessObject.AlmacenServicioimp;
import com.project.BasesDeDatos.projectDB.models.Agresores;
import com.project.BasesDeDatos.projectDB.models.Delito;
import com.project.BasesDeDatos.projectDB.repositorios.AgresoresRepositorio;
import groovy.transform.AutoClone;
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
@RequestMapping("/Agresores")
public class AgresoresController
{
    @Autowired
    private AgresoresRepositorio agresoresRepositorio;

    @Autowired
    private AlmacenServicioimp servicio;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "nombreAgresor",size = 5) Pageable pageable)
    {
        Page<Agresores> agresores = agresoresRepositorio.findAll(pageable);
        return new ModelAndView("Agresores/Index")
                .addObject("agresores",agresores);
    }
    @GetMapping("/agresores/nuevo")
    public ModelAndView mostrarFormularioDeNuevoDelito()
    {
        return new ModelAndView("Agresores/nuevo-agresor")
                .addObject("agresor",new Agresores());
    }
    @PostMapping("/agresores/nuevo")
    public ModelAndView registrarAgresor(@Validated Agresores agresores, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors() || agresores.getPortada().isEmpty())
        {
            if(agresores.getPortada().isEmpty())
            {
                bindingResult.rejectValue("portada","error.portada","Seleccione una imagen");
            }
            return new ModelAndView("Agresores/nuevo-agresor")
                    .addObject("agresor",agresores);
        }
        String rutaPortada = servicio.almacenarArchivo(agresores.getPortada());
        agresores.setRutaPortada(rutaPortada);

        agresoresRepositorio.save(agresores);
        return new ModelAndView("redirect:/Agresores");
    }

    @GetMapping("/agresores/{id}/editar")
    public ModelAndView mostrarFormularioDeEdicion(@PathVariable Integer id)
    {
        Agresores agresores = agresoresRepositorio.getOne(id);
        return new ModelAndView("Agresores/editar-agresor")
                .addObject("agresor",agresores);
    }

    @PostMapping("/agresores/{id}/editar")
    public ModelAndView actualizarAgresor(@PathVariable Integer id,@Validated Agresores agresores, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Agresores/editar-agresor")
                    .addObject("agresor",agresores);

        }
        Agresores agresoresDB = agresoresRepositorio.getOne(id);
        agresoresDB.setNombreAgresor(agresores.getNombreAgresor());
        agresoresDB.setApellidosAgresor(agresores.getApellidosAgresor());
        agresoresDB.setFechaNacimiento(agresores.getFechaNacimiento());
        agresoresDB.setGenero(agresores.getGenero());
        agresoresDB.setDireccion(agresores.getDireccion());
        agresoresDB.setNumeroDeIdentificacion(agresores.getNumeroDeIdentificacion());
        agresoresDB.setDelitoCometido(agresores.getDelitoCometido());
        if(!agresores.getPortada().isEmpty())
        {
            servicio.eliminarArchivo(agresoresDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(agresores.getPortada());
            agresoresDB.setRutaPortada(rutaPortada);
        }
        agresoresRepositorio.save(agresoresDB);
        return new ModelAndView("redirect:/Agresores");
    }

    @PostMapping("/agresores/{id}/eliminar")
    public String eliminarAgresor(@PathVariable Integer id)
    {
        Agresores agresores = agresoresRepositorio.getOne(id);
        agresoresRepositorio.delete(agresores);
        servicio.eliminarArchivo(agresores.getRutaPortada());

        return "redirect:/Agresores";
    }
}
