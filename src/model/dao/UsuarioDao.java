package model.dao;

import model.entites.Usuario;

import java.util.List;

public interface UsuarioDao {

    void insert(Usuario usuario);
    void update(Usuario usuario);
    void deleteById(Integer id);
    Usuario findById(Integer id);
    List<Usuario> findAll();

}
