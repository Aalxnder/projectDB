package com.project.BasesDeDatos.projectDB.controllers;

import com.project.BasesDeDatos.projectDB.DataAccessObject.UsuarioDao;
import com.project.BasesDeDatos.projectDB.models.Usuario;
import com.project.BasesDeDatos.projectDB.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController
{
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value ="usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token)
    {
        if(!validarToken(token))
        {
            return null;
        }
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token)
    {
        String UsuarioID = jwtUtil.getKey(token);
        return UsuarioID != null;
    }

    @RequestMapping(value = "usuario/registrar", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario)
    {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1023,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "usuario/editarContra ", method = RequestMethod.POST)
    public void editarContra(@RequestBody Usuario usuario, @RequestHeader(value = "Authorization") String token)
    {
        if(!validarToken(token))
        {
            return;
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1023,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.editarContra(usuario);
    }
}
