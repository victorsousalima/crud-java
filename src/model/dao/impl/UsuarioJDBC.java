package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entites.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJDBC implements UsuarioDao {

    private Connection conn;

    public UsuarioJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario usuario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO usuario " +
                    "(nome, email, data_nascimento, telefone) " +
                    "VALUES " +
                    "(?, ?, ?, ?)");

            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());

            Date date = Date.valueOf(usuario.getData_nascimento());
            st.setDate(3, date);

            st.setString(4, usuario.getTelefone());

            int rows = st.executeUpdate();

            if (rows > 0) {
                System.out.println("Usuário adicionado com sucesso!");
            }
            else {
                throw new DbException("Ocorreu um erro e o usuário não foi adicionado!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Usuario usuario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE usuario " +
                    "SET nome = ?, email = ?, data_nascimento = ?, telefone = ? " +
                    "WHERE id = ?");

            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            Date date = Date.valueOf(usuario.getData_nascimento());
            st.setDate(3, date);
            st.setString(4, usuario.getTelefone());

            st.setInt(5, usuario.getId());

            int rows = st.executeUpdate();

            if (rows > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            }
            else {
                throw new DbException("Ocorreu um erro e o usuário não foi atualizado!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM usuario " +
                    "WHERE id = ?");

            st.setInt(1, id);

            int rows = st.executeUpdate();

            if (rows > 0) {
                System.out.printf("Usuário com id %d deletado com sucesso!", id);
            }
            else {
                throw new DbException("Ocorreu um erro e o usuário com id " + id + " não foi deletado!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Usuario findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM usuario " +
                    "WHERE id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                Usuario usuario = instanciarUsuario(rs);
                return usuario;
            }
            else {
                throw new DbException("Usuário com id " + id + " não encontrado!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Usuario> findAll() {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM usuario");

            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = instanciarUsuario(rs);
                usuarios.add(usuario);
            }

            if (usuarios.size() == 0) {
                throw new DbException("Não existe nenhum usuário nessa tabela!");
            }

            return usuarios;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Usuario instanciarUsuario(ResultSet rs) throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
        usuario.setTelefone(rs.getString("telefone"));

        return usuario;
    }
}
