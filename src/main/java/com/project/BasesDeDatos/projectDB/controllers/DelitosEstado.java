package com.project.BasesDeDatos.projectDB.controllers;

import ch.qos.logback.core.model.Model;
import com.project.BasesDeDatos.projectDB.models.Delito;
import com.project.BasesDeDatos.projectDB.repositorios.DelitoEstadoRepository;
import com.project.BasesDeDatos.projectDB.repositorios.DelitoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DelitosEstado
{
    @Autowired
    private DelitoEstadoRepository delitoEstadoRepository;

    @Autowired
    private DelitoRepositorio delitoRepositorio;

    @GetMapping("/delitos/resueltos")
    public ModelAndView listarDelitosResueltos()
    {
        List<Delito> delitos = delitoEstadoRepository.findByEstado("Resuelto");
        return new ModelAndView("home/verDelitosResueltos")
                .addObject("delitos",delitos);
    }

    @GetMapping("/delitos/noResueltos")
    public ModelAndView listarDelitosNoResueltos()
    {
        List<Delito> delitosNoResueltos = delitoEstadoRepository.findByEstado("no resuelto");
        return new ModelAndView("home/VerDelitosNoResueltos")
                .addObject("delitosNoResueltos",delitosNoResueltos);
    }
    @PostMapping("/marcar-delito-resuelto")
    public String marcarDelitoResuelto(@RequestParam Integer id)
    {
        Delito delito = delitoRepositorio.findById(id).orElse(null);
        if(delito != null)
        {
            delito.setEstado("Resuelto");
            delitoRepositorio.save(delito);
        }
        return "redirect:/home/verDelitos";
    }
}
