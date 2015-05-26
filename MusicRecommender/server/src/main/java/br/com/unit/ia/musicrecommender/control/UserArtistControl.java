/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.entity.Artist;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserArtist;
import br.com.unit.ia.musicrecommender.persistence.UserArtistPersistence;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Jonas
 */
public class UserArtistControl implements IControl<UserArtist>{
    
    private UserArtistPersistence persistence;
    
    public UserArtistPersistence getPersistence() throws SQLException {
        if(persistence == null){
            persistence = new UserArtistPersistence();
        }
        return persistence;
    }

    @Override
    public UserArtist insert(UserArtist object) throws Exception {
        UserArtistPersistence persistence1 = getPersistence();
        persistence1.create(object);
        return persistence1.getLast("id");
    }

    @Override
    public Collection<UserArtist> getAll() throws Exception {
        return getPersistence().listAll();
    }

    @Override
    public void delete(Number id) throws Exception {
        getPersistence().deleteById(id.longValue());
    }

    @Override
    public void update(UserArtist object) throws Exception {
        getPersistence().update(object);
    }

    @Override
    public UserArtist get(Number id) throws Exception {
        return getPersistence().findById(id.longValue());
    }
    
    public Collection<UserArtist> get(User user) throws SQLException{
        QueryBuilder<UserArtist, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("user_id", user.getId());
        return queryBuilder.query();
    }
    
    public Collection<UserArtist> get(Artist artist) throws SQLException{
        QueryBuilder<UserArtist, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("artist_id", artist.getId());
        return queryBuilder.query();
    }
    
    public Collection<UserArtist> get(User user, Artist artist) throws SQLException{
        QueryBuilder<UserArtist, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("user_id", user.getId()).eq("artist_id", artist.getId());
        return queryBuilder.query();
    }
}
