/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.persistence;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jonas
 */
public abstract class GenericPersistence<E, T> extends BaseDaoImpl<E, T> implements Dao<E, T> {

    public GenericPersistence(ConnectionSource connectionSource, Class<E> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        initialize();
    }

    @SuppressWarnings("unchecked")
    public void persist(E obj) throws SQLException {
        create((E) obj);
    }

    @SuppressWarnings("unchecked")
    public void merge(E obj) throws SQLException {
        update(obj);
    }

    @SuppressWarnings("unchecked")
    public void remove(E obj) throws SQLException {
        delete(obj);
    }

    public List<E> listAll() throws SQLException {
        return queryForAll();
    }

    @SuppressWarnings("unchecked")
    public List<E> resultList(PreparedQuery<E> queryBuilder) throws SQLException {
        return query(queryBuilder);
    }

    @SuppressWarnings("unchecked")
    public E singleResult(PreparedQuery<E> preparedQuery) throws SQLException {
        return queryForFirst(preparedQuery);
    }

    @SuppressWarnings("unchecked")
    public E findById(T id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("Valor de id não pode ser nulo");
        } else {
            return queryForId(id);
        }
    }
    
     public E getLast(String idColumn) throws SQLException{
        List<E> list = query(queryBuilder().orderBy(idColumn, false).limit(1L).prepare());
        return list.get(0);
    }
}
