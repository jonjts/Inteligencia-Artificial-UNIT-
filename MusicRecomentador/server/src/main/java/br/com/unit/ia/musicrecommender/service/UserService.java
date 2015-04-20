/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecomentador.service;

import br.com.unit.ia.musicrecomentador.control.IControl;
import br.com.unit.ia.musicrecomentador.control.UserControl;
import br.com.unit.ia.musicrecomentador.entity.User;
import java.sql.SQLException;
import javax.ws.rs.Path;

/**
 *
 * @author Jonas
 */
@Path("users")
public class UserService extends GenericService<User>{
    
    public UserService() throws SQLException{
        super(new UserControl());
    }
    
}
