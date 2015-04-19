/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecomentador.control;

import br.com.unit.ia.musicrecomentador.entity.User;
import br.com.unit.ia.musicrecomentador.entity.UserHistory;
import br.com.unit.ia.musicrecomentador.persistence.UserHistoryPersistence;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Jonas
 */
public class UserHistoryControl implements IControl<UserHistory>{
    
    private UserHistoryPersistence persistence;

    public UserHistoryPersistence getPersistence() throws SQLException {
        if(persistence == null){
            persistence = new UserHistoryPersistence();
        }
        return persistence;
    }

    @Override
    public UserHistory insert(UserHistory object) throws Exception {
        getPersistence().create(object);
        return getPersistence().getLast("user_id");
    }

    @Override
    public Collection<UserHistory> getAll() throws Exception {
        return getPersistence().listAll();
    }

    @Override
    public void delete(Object id) throws Exception {
        String s = (String) id;
        getPersistence().deleteById(s);
    }

    @Override
    public void update(UserHistory object) throws Exception {
        getPersistence().update(object);
    }
    
    
}
