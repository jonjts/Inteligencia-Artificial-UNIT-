/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.algorithm.Algorithm;
import br.com.unit.ia.musicrecommender.entity.Song;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.persistence.UserPersistence;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class UserControl implements IControl<User> {

    private UserPersistence persistence;
    private Algorithm algorithm;

    public UserPersistence getPersistence() throws SQLException {
        if (persistence == null) {
            persistence = new UserPersistence();
        }
        return persistence;
    }

    @Override
    public User insert(User object) throws Exception {
        getPersistence().create(object);
        return getPersistence().getLast("id");
    }

    @Override
    public Collection<User> getAll() throws Exception {
        return getPersistence().listAll();
    }

    @Override
    public void delete(Number id) throws Exception {
        getPersistence().deleteById(id.longValue());
    }

    @Override
    public void update(User object) throws Exception {
        getPersistence().update(object);
    }

    @Override
    public User get(Number id) throws Exception {
        return getPersistence().findById(id.longValue());
    }
    
    public User getByName(User user) throws SQLException{
        QueryBuilder<User, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("name", user.getName());
        return queryBuilder.queryForFirst();
    }
    
    public List<Song> getRecomedation(User user, int limitSongs) throws SQLException{
        algorithm = new Algorithm(user, limitSongs);
        return algorithm.doTheMagic();
    }
}
