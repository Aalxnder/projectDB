package com.project.BasesDeDatos.projectDB.DataAccessObject;

import com.project.BasesDeDatos.projectDB.models.Usuario;

import java.util.List;

public interface UsuarioDao
{
    List<Usuario> getUsuarios();
    void eliminar(int id);

    void registrar(Usuario usuario);
    void editarContra(Usuario usuario);

    Usuario obtenerUsuario(Usuario usuario);
}
