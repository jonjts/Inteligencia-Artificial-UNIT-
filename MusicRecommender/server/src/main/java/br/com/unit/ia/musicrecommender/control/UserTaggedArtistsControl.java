/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.entity.Artist;
import br.com.unit.ia.musicrecommender.entity.Tag;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserTaggedArtists;
import br.com.unit.ia.musicrecommender.persistence.UserTaggedArtistsPersistence;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class UserTaggedArtistsControl implements IControl<UserTaggedArtists> {

    private UserTaggedArtistsPersistence persistence;

    public UserTaggedArtistsPersistence getPersistence() throws SQLException {
        if (persistence == null) {
            persistence = new UserTaggedArtistsPersistence();
        }
        return persistence;
    }

    @Override
    public UserTaggedArtists insert(UserTaggedArtists object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<UserTaggedArtists> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Number id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UserTaggedArtists object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserTaggedArtists get(Number id) throws Exception {
        return getPersistence().findById(id.longValue());
    }

    public List<UserTaggedArtists> get(long limit, long offset) throws SQLException {
        QueryBuilder<UserTaggedArtists, Long> offset1 = getPersistence().queryBuilder().limit(limit).offset(offset);
        return offset1.query();
    }

    public List<UserTaggedArtists> get(User user, boolean groupByTag) throws SQLException {
        final QueryBuilder<UserTaggedArtists, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("user_id", user.getId());
        if (groupByTag) {
            queryBuilder.groupBy("tag_id");
        }
        return queryBuilder.query();
    }

    public List<UserTaggedArtists> get(Artist artist) throws SQLException {
        return getPersistence().queryBuilder().where().eq("artis_id", artist.getId()).query();
    }

    public List<UserTaggedArtists> get(Tag tag) throws SQLException {
        QueryBuilder<UserTaggedArtists, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("tag_id", tag.getId());
        return queryBuilder.query();
    }

    public List<UserTaggedArtists> get(List<Long> tags, long limit) throws SQLException {
        QueryBuilder<UserTaggedArtists, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().in("tag_id", tags);
        queryBuilder.groupBy("user_id");
        queryBuilder.orderByRaw("RAND()");
        queryBuilder.limit(limit);
        return queryBuilder.query();
    }

}
