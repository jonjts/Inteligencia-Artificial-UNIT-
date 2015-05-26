/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.control;

import br.com.unit.ia.musicrecommender.entity.Tag;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserTaggedArtists;
import br.com.unit.ia.musicrecommender.persistence.TagPersistence;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class TagControl implements IControl<Tag>{

    private TagPersistence persistence;
    private UserTaggedArtistsControl userTaggedArtistsControl = new UserTaggedArtistsControl();

    public TagPersistence getPersistence() throws SQLException {
        if (persistence == null) {
            persistence = new TagPersistence();
        }
        return persistence;
    }

    @Override
    public Tag insert(Tag object) throws Exception {
        getPersistence().create(object);
        return getPersistence().getLast("id");
    }

    @Override
    public Collection<Tag> getAll() throws Exception {
        return getPersistence().listAll();
    }

    @Override
    public void delete(Number id) throws Exception {
        getPersistence().deleteById(id.longValue());
    }

    @Override
    public void update(Tag object) throws Exception {
        getPersistence().update(object);
    }

    @Override
    public Tag get(Number id) throws Exception {
        return getPersistence().findById(id.longValue());
    }
    
    public List<Tag> get(User user) throws SQLException{
        QueryBuilder<UserTaggedArtists, Long> builderUTA = userTaggedArtistsControl.getPersistence().queryBuilder();
        builderUTA.where().eq("user_id", user.getId());
        
        QueryBuilder<Tag, Long> builder = getPersistence().queryBuilder();
        builder.join(builderUTA);
        
        return builder.query();
    }

}
