/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.persistence;

import br.com.unit.ia.musicrecommender.entity.UserHistory;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Jonas
 */
public class UserHistoryPersistence extends GenericPersistence<UserHistory, String>{

    public UserHistoryPersistence() throws SQLException {
        super(Persistencia.instance().getConnectionSource(), UserHistory.class);
    }
    
}
