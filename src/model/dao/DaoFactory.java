package model.dao;

import db.DB;
import model.dao.impl.UsuarioJDBC;

public class DaoFactory {

    public static UsuarioDao createUsuarioDao() {
        return new UsuarioJDBC(DB.getConnection());
    }
}
