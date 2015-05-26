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
@DatabaseTable(tableName = "user_tagged_artists")
public class UserTaggedArtists {

    @DatabaseField(id = true)
    private Long id;

    @DatabaseField(columnName = "user_id", foreign = true, foreignAutoRefresh = true)
    private User user;

    @DatabaseField(columnName = "artist_id", foreign = true, foreignAutoRefresh = true)
    private Artist artist;

    @DatabaseField(columnName = "tag_id", foreign = true, foreignAutoRefresh = true)
    private Tag tag;

    @DatabaseField
    private Integer day;

    @DatabaseField
    private Integer month;

    @DatabaseField
    private Integer year;

    public UserTaggedArtists(Long id) {
        super();
        this.id = id;
    }

    public UserTaggedArtists() {
        super();
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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
