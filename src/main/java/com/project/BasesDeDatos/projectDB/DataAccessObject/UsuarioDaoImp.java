package com.project.BasesDeDatos.projectDB.DataAccessObject;

import com.project.BasesDeDatos.projectDB.models.Usuario;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios()
    {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }
    @Override
    public void eliminar(int id)
    {
        //
    }
    public void registrar(Usuario usuario)
    {
        entityManager.merge(usuario);
    }
    @Override
    public void editarContra(Usuario usuario)
    {
        //verificar email
        entityManager.merge(usuario);
    }
    @Override
    public Usuario obtenerUsuario(Usuario usuario)
    {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        //si no existe usuario
        if(lista.isEmpty())
        {
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();
        //comparar contraseñas
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, usuario.getPassword()))
        {
            return lista.get(0);
        }
        return null;
    }


}
