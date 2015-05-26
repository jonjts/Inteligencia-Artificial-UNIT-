/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.persistence;

import br.com.unit.ia.musicrecommender.entity.UserArtist;
import java.sql.SQLException;

/**
 *
 * @author Jonas
 */
public class UserArtistPersistence extends GenericPersistence<UserArtist, Long>{

    public UserArtistPersistence() throws SQLException {
        super(Persistencia.instance().getConnectionSource(), UserArtist.class);
    }
    
    
}
