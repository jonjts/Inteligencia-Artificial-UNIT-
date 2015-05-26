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
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class ArtistControl implements IControl<Artist> {

    private ArtistPersistence persistence;
    private SongControl songControl = new SongControl();
    private UserArtistControl userArtistControl = new UserArtistControl();

    public ArtistPersistence getPersistence() throws SQLException {
        if (persistence == null) {
            persistence = new ArtistPersistence();
        }
        return persistence;
    }

    @Override
    public Artist insert(Artist object) throws Exception {
        ArtistPersistence persistence1 = getPersistence();
        persistence1.create(object);
        return persistence1.getLast("id");
    }

    @Override
    public Collection<Artist> getAll() throws Exception {
        return getPersistence().listAll();
    }
    
    public Collection<Song> getSongs(Artist artist) throws Exception{
        Collection<Song> get = songControl.get(artist);
        return get;
    }

    @Override
    public void delete(Number id) throws Exception {
        getPersistence().deleteById(id.longValue());
    }

    @Override
    public void update(Artist object) throws Exception {
        getPersistence().update(object);
    }

    @Override
    public Artist get(Number id) throws Exception {
        return getPersistence().findById(id.longValue());
    }
    
    public List<Artist> get(User user) throws SQLException{
        QueryBuilder<UserArtist, Long> builderUA = userArtistControl.getPersistence().queryBuilder();
        builderUA.where().eq("user_id", user.getId());
        
        QueryBuilder<Artist, Long> builderA = getPersistence().queryBuilder();
        builderA.join(builderUA);
        
        return builderA.query();
    }
}
