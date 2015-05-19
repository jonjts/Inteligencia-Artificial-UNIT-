/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

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
    public void delete(Object id) throws Exception {
        String s = (String) id;
        getPersistence().deleteById(s);
    }

    @Override
    public void update(User object) throws Exception {
        getPersistence().update(object);
    }
    
    public Collection<User> getByCountry(User user, long limit) throws SQLException{
        QueryBuilder<User, String> queryBuilder = getPersistence().queryBuilder();
        Where<User, String> where = queryBuilder.where();
        where.eq("country", user.getCountry());
        where.ne("id", user.getRegistered());
        queryBuilder.orderByRaw("RAND()");
        queryBuilder.limit(limit);
        return queryBuilder.query();
    }

}
