package com.project.BasesDeDatos.projectDB.controllers;

import com.project.BasesDeDatos.projectDB.models.Agresores;
import com.project.BasesDeDatos.projectDB.models.Delito;
import com.project.BasesDeDatos.projectDB.models.Victima;
import com.project.BasesDeDatos.projectDB.repositorios.AgresoresRepositorio;
import com.project.BasesDeDatos.projectDB.repositorios.DelitoRepositorio;
import com.project.BasesDeDatos.projectDB.repositorios.VictimaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class homeController
{
    @Autowired
    private AgresoresRepositorio agresoresRepositorio;
    @Autowired
    private DelitoRepositorio delitoRepositorio;
    @Autowired
    private VictimaRepositorio victimaRepositorio;

    @GetMapping("")
    public ModelAndView listarAgresores(@PageableDefault(sort = "nombreAgresor",direction = Sort.Direction.ASC) Pageable pageable)
    {
        Page<Agresores> Agresores = agresoresRepositorio.findAll(pageable);
        return new ModelAndView("home/VerAgresores")
                .addObject("Agresores", Agresores);
    }

    @GetMapping("/verAgresores/{id}")
    public ModelAndView mostrarDetallesDeAgresor(@PathVariable Integer id)
    {
        Agresores agresor = agresoresRepositorio.getOne(id);
        return new ModelAndView("home/detallesAgresor")
                .addObject("agresor",agresor);
    }
    @GetMapping("/verDelitos")
    public ModelAndView listarDelitos(@PageableDefault(sort = "fecha",direction = Sort.Direction.ASC) Pageable pageable)
    {
        Page<Delito> delitos = delitoRepositorio.findAll(pageable);
        return new ModelAndView("/home/verDelitos")
                .addObject("delitos",delitos);
    }
    @GetMapping("/verDelitos/{id}")
    public ModelAndView mostrarDetallesDelito(@PathVariable Integer id)
    {
        Delito delito = delitoRepositorio.getOne(id);
        return new ModelAndView("home/detallesDelito")
                .addObject("delito",delito);
    }
    @GetMapping("/verVictimas")
    public ModelAndView listarVictimas(@PageableDefault(sort = "nombreVictima",direction = Sort.Direction.ASC) Pageable pageable)
    {
        Page<Victima> victimas = victimaRepositorio.findAll(pageable);
        return new ModelAndView("/home/verVictimas")
                .addObject("victimas",victimas);
    }
    @GetMapping("/verVictimas/{id}")
    public ModelAndView mostrarDetallesVictima(@PathVariable Integer id)
    {
        Victima victima = victimaRepositorio.getOne(id);
        return new ModelAndView("/home/detallesVictima")
                .addObject("victima",victima);
    }
}

