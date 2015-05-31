/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.entity.Artist;
import br.com.unit.ia.musicrecommender.entity.Song;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserArtist;
import br.com.unit.ia.musicrecommender.persistence.ArtistPersistence;
import br.com.unit.ia.musicrecommender.persistence.SongPersistence;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Jonas
 */
public class SongControl implements IControl<Song> {

    private SongPersistence persistence;

    public SongPersistence getPersistence() throws SQLException {
        if (persistence == null) {
            persistence = new SongPersistence();
        }
        return persistence;
    }

    @Override
    public Song insert(Song object) throws Exception {
        getPersistence().create(object);
        return getPersistence().getLast("id");
    }

    @Override
    public Collection<Song> getAll() throws Exception {
        return getPersistence().listAll();
    }

    public Collection<Song> get(Artist artist) throws Exception {
        QueryBuilder<Song, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().eq("artist_id", artist.getId());
        return queryBuilder.query();
    }

    public Collection<Song> getAll(long offset, long limit) throws Exception {
        return getPersistence().queryBuilder().offset(offset).limit(limit).query();
    }

    @Override
    public void delete(Number id) throws Exception {
        getPersistence().deleteById(id.longValue());
    }

    @Override
    public void update(Song object) throws Exception {
        getPersistence().update(object);
    }

    @Override
    public Song get(Number id) throws Exception {
        return getPersistence().findById(id.longValue());
    }

    public Song get(User user) throws SQLException {
        QueryBuilder<Song, Long> queryBuilder = getPersistence().queryBuilder();
        queryBuilder.where().ne("title", '-');
        
        UserArtistControl userArtistControl = new UserArtistControl();
        QueryBuilder<UserArtist, Long> queryUserArtist = userArtistControl.getPersistence().queryBuilder();
        queryUserArtist.where().eq("user_id", user.getId());
        
        ArtistControl artistControl = new ArtistControl();
        QueryBuilder<Artist, Long> queryArtist = artistControl.getPersistence().queryBuilder();
        
        queryArtist.join(queryUserArtist);
        
        queryBuilder.join(queryArtist);
        queryBuilder.orderByRaw("RAND()");
        return queryBuilder.queryForFirst();
    }

}
