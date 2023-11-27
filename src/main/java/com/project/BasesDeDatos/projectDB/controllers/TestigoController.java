package com.project.BasesDeDatos.projectDB.controllers;

import com.project.BasesDeDatos.projectDB.models.Delito;
import com.project.BasesDeDatos.projectDB.models.Testigo;
import com.project.BasesDeDatos.projectDB.repositorios.DelitoRepositorio;
import com.project.BasesDeDatos.projectDB.repositorios.TestigoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/testigo")
public class TestigoController
{
    @Autowired
    private TestigoRepositorio testigoRepositorio;
    @Autowired
    private DelitoRepositorio delitoRepositorio;

    @GetMapping("/nuevo-testigo")
    public ModelAndView mostrarFormularioDeNuevoTestigo(@RequestParam(name = "delitoId", required = true) Integer delitoId)
    {
        Delito delito = delitoRepositorio.findById(delitoId).orElse(null);

        if (delito == null) {
            return new ModelAndView("404");
        }

        Testigo testigo = new Testigo();
        testigo.setDelito(delito);

        return new ModelAndView("testigos/nuevo-testigo")
                .addObject("testigo",testigo)
                .addObject("delitoId", delitoId);
    }

    @PostMapping("/nuevo-testigo")
    public ModelAndView registrarTestigo(@RequestParam(name = "delitoId", required = true) Integer delitoId, @Validated Testigo testigo, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("testigos/nuevo-testigo")
                    .addObject("testigo",testigo)
                    .addObject("delitoId", delitoId);
        }
        Delito delito = delitoRepositorio.findById(delitoId).orElse(null);

        if (delito == null) {
            return new ModelAndView("404");
        }

        testigo.setDelito(delito);
        testigoRepositorio.save(testigo);

        return new ModelAndView("redirect:/home/verDelitos/" + delitoId);
    }
    @GetMapping("/ver-testigos/{idDelito}")
    public ModelAndView verTestigosDelDelito(@PathVariable Integer idDelito)
    {
        List<Testigo> testigos = testigoRepositorio.findByDelitoId(idDelito);

        return new ModelAndView("testigos/detallesTestigos")
                .addObject("testigos", testigos);
    }

}
