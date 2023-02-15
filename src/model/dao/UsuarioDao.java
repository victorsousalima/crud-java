package model.dao;

import model.entites.Usuario;

import java.util.List;

public interface UsuarioDao {

    void insert(Usuario usuario);
    void update(Integer id);
    void deleteById(Integer id);
    Usuario findById();
    List<Usuario> findAll();

}
