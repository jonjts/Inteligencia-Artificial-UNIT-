/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.UserTaggedArtistsControl;
import br.com.unit.ia.musicrecommender.entity.UserTaggedArtists;
import javax.ws.rs.Path;

/**
 *
 * @author Jonas
 */
@Path("userTaggedArtists")
public class UserTaggedArtistsService extends GenericService<UserTaggedArtists>{

    public UserTaggedArtistsService() {
        super(new UserTaggedArtistsControl());
    }
    
    
}
