/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.LoginControl;
import br.com.unit.ia.musicrecommender.entity.User;
import com.sun.istack.Nullable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonas
 */
@Path("login")
public class LoginService extends GenericService<User> {

    public LoginService() {
        super(new LoginControl());
    }

    @Override
    public Response post(User object) {
        if (object != null) {
            try {
                LoginControl control = (LoginControl) getControle();
                User login = control.login(object);
                return Response.ok(login).build();
            } catch (Exception ex) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao fazer login").build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Usuario invalido.").build();
    }

    @Path("logout")
    @DELETE
    public Response logout() {
        try {
            LoginControl control = (LoginControl) getControle();
            control.logout();
            return Response.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

}
