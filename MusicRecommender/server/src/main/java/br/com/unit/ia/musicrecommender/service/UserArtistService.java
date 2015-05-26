/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.UserArtistControl;
import br.com.unit.ia.musicrecommender.entity.UserArtist;
import javax.ws.rs.Path;

/**
 *
 * @author Jonas
 */
@Path("usersArtists")
public class UserArtistService extends GenericService<UserArtist>{

    public UserArtistService() {
        super(new UserArtistControl());
    }
    
    
}
