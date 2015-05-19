/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.persistence.UserPersistence;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.SQLException;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import org.apache.commons.httpclient.HttpClient;

/**
 *
 * @author Jonas
 */
public class LoginControl implements IControl<User> {
    
    @Context
    private static HttpServletRequest request;

    private static final String USER = "user";
    private UserPersistence persistence;

    public UserPersistence getPersistence() throws SQLException {
        if (persistence == null) {
            persistence = new UserPersistence();
        }
        return persistence;
    }

    public HttpSession getSession() {
        HttpSession sess = ((HttpServletRequest) request).getSession(false);
        return sess;
    }

    public User login(User user) throws Exception {
        UserPersistence persistence = getPersistence();
        User findById = persistence.findById(user.getId());
        if (findById != null) {
            getSession().setAttribute(USER, findById);
            return findById;
        }
        return null;
    }
    
    public void logout(){
        final HttpSession session = getSession();
        session.setAttribute(USER, null);
    }

    @Override
    public User insert(User object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<User> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
