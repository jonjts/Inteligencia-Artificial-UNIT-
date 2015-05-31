/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.UserControl;
import br.com.unit.ia.musicrecommender.entity.Song;
import br.com.unit.ia.musicrecommender.entity.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonas
 */
@Path("users")
public class UserService extends GenericService<User>{
    
    public UserService() throws SQLException{
        super(new UserControl());
    }
    
    @GET
    @Path("{userName}/recommendation")
    public Response getRecommendation(@PathParam("userName") String userName,  @QueryParam("limit") Integer limit){
        try {
            UserControl control = (UserControl) getControl();
            
            limit = limit == null ? 10 : limit;
            
            User u = new User();
            u.setName(userName);
            User byName = control.getByName(u);
           if(byName != null){
                List<Song> recomedation = control.getRecomedation(byName, limit);
                return Response.ok(recomedation).build();
            }
            
            return Response.status(Response.Status.OK).build();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }
        
    }
    
    
}
