/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserHistory;
import br.com.unit.ia.musicrecommender.persistence.UserHistoryPersistence;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.AbstractCollection;
import java.util.ArrayList;
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
    
    public Collection<UserHistory> getByArtist(String artist, User user) throws Exception{
        QueryBuilder<UserHistory, String> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("artist_name", artist).ne("user_id", user.getId());
        return queryBuilder.query();
    }
    
    public Collection<UserHistory> getBySong(String song, User user) throws Exception{
        QueryBuilder<UserHistory, String> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("track-name", song).ne("user_id", user.getId());
        return queryBuilder.query();
    }
    
    public Collection<UserHistory> get(User user) throws Exception{
        QueryBuilder<UserHistory, String> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("user_id", user.getId());
        return queryBuilder.query();
    }
    
    public Collection<UserHistory> get(Collection<User> users) throws SQLException{
        QueryBuilder<UserHistory, String> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().in("user_id", users.toArray());
        return queryBuilder.query();
    }
    
    public Object[] createArrayId(Collection<User> users){
        ArrayList<String> al = new ArrayList<String>();
        for(User u : users){
            al.add(u.getId());
        }
        return al.toArray();
    }
    
}
