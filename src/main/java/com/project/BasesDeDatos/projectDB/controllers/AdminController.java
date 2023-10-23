package com.project.BasesDeDatos.projectDB.controllers;

import com.project.BasesDeDatos.projectDB.DataAccessObject.AlmacenServicioimp;
import com.project.BasesDeDatos.projectDB.models.Delito;
import com.project.BasesDeDatos.projectDB.models.TipoDelito;
import com.project.BasesDeDatos.projectDB.repositorios.DelitoRepositorio;
import com.project.BasesDeDatos.projectDB.repositorios.TipoDelitoRepositorio;
import com.project.BasesDeDatos.projectDB.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private DelitoRepositorio delitoRepositorio;

    @Autowired
    private TipoDelitoRepositorio tipoDelitoRepositorio;

    @Autowired
    private AlmacenServicioimp servicio;

    @Autowired
    private JWTUtil jwtUtil;
    //ver pagina de inicio
    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "nombreDelito",size = 5)Pageable pageable)
    {
        Page<Delito> delitos = delitoRepositorio.findAll(pageable);
        return new ModelAndView("admin/index")
                .addObject("delitos",delitos);
    }

    @GetMapping("/delitos/nuevo")
    public ModelAndView mostrarFormularioDeNuevoDelito()
    {
        List<TipoDelito> tiposDelitos = tipoDelitoRepositorio.findAll(Sort.by("titulo"));
        return new ModelAndView("admin/nuevo-delito")
                .addObject("delito",new Delito())
                .addObject("tiposDelitos",tiposDelitos);
    }
    private boolean validarToken(String token)
    {
        String UsuarioID = jwtUtil.getKey(token);
        return UsuarioID != null;
    }
    @PostMapping("/delitos/nuevo")
    public ModelAndView registrarDelito(@Validated Delito delito, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors() || delito.getPortada().isEmpty())
            {
                if(delito.getPortada().isEmpty())
                {
                    bindingResult.rejectValue("portada","MultipartNotEmpty");
                }
                List<TipoDelito> tiposDelitos = tipoDelitoRepositorio.findAll(Sort.by("titulo"));
                return new ModelAndView("admin/nuevo-delito")
                        .addObject("delito",delito)
                        .addObject("tiposDelitos",tiposDelitos);
            }
            String rutaPortada = servicio.almacenarArchivo(delito.getPortada());
            delito.setRutaPortada(rutaPortada);

            delitoRepositorio.save(delito);
            return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/delitos/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarDelito(@PathVariable Integer id)
    {
        Delito delito = delitoRepositorio.getOne(id);
        List<TipoDelito> tiposDelitos = tipoDelitoRepositorio.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-delito")
                .addObject("delito",delito)
                .addObject("tiposDelitos",tiposDelitos);
    }
    @PostMapping("/delitos/{id}/editar")
    public ModelAndView actualizarDelito(@PathVariable Integer id,@Validated Delito delito, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            List<TipoDelito> delitos = tipoDelitoRepositorio.findAll(Sort.by("titulo"));
            return new ModelAndView("admin/editar-delito")
                    .addObject("delito",delito)
                    .addObject("tiposDelitos",delitos);
        }
        Delito delitoDB = delitoRepositorio.getOne(id);
        delitoDB.setNombreDelito(delito.getNombreDelito());
        delitoDB.setDescripcion(delito.getDescripcion());
        delitoDB.setEvidencia(delito.getEvidencia());
        delitoDB.setInvestigadorId(delito.getInvestigadorId());
        delitoDB.setIdAgresor(delito.getIdAgresor());
        delitoDB.setIdVictima(delito.getIdVictima());
        delitoDB.setTipoDelitos(delito.getTipoDelitos());

        if(!delito.getPortada().isEmpty())
        {
            servicio.eliminarArchivo(delitoDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(delito.getPortada());
            delitoDB.setRutaPortada(rutaPortada);
        }
        delitoRepositorio.save(delitoDB);
        return new ModelAndView("redirect:/admin");

    }

    @PostMapping("/delitos/{id}/eliminar")
    public String eliminarDelito(@PathVariable Integer id)
    {
        Delito delito = delitoRepositorio.getOne(id);
        delitoRepositorio.delete(delito);
        servicio.eliminarArchivo(delito.getRutaPortada());

        return "redirect:/admin";
    }
}
