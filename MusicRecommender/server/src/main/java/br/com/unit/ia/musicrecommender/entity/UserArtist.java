/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Jonas
 */
@DatabaseTable(tableName = "user_artist")
public class UserArtist {

    @DatabaseField(id = true)
    private Long id;

    @DatabaseField(foreign = true, columnName = "user_id", foreignAutoRefresh = true)
    private User user;

    @DatabaseField(foreign = true, columnName = "artist_id", foreignAutoRefresh = true)
    private Artist artist;

    @DatabaseField
    private Integer weight;

    public UserArtist() {
        super();
    }

    public UserArtist(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
