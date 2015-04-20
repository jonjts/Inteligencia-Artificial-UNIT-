/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.entity;

import br.com.unit.ia.musicrecommender.persistence.UserHistoryPersistence;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

/**
 *
 * @author Jonas
 */
@DatabaseTable(daoClass = UserHistoryPersistence.class, tableName = "user_history")
public class UserHistory {

    @DatabaseField(id = true, columnName = "user_id")
    private String userId;

    @DatabaseField
    private Timestamp timestamp;

    @DatabaseField(columnName = "musicbrainz-artist-id")
    private String musicbrainzArtistId;

    @DatabaseField(columnName = "artist_name")
    private String artistName;

    @DatabaseField(columnName = "musicbrainz-track-id")
    private String musicbrainzTrackId;

    @DatabaseField(columnName = "track-name")
    private String trackName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMusicbrainzArtistId() {
        return musicbrainzArtistId;
    }

    public void setMusicbrainzArtistId(String musicbrainzArtistId) {
        this.musicbrainzArtistId = musicbrainzArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMusicbrainzTrackId() {
        return musicbrainzTrackId;
    }

    public void setMusicbrainzTrackId(String musicbrainzTrackId) {
        this.musicbrainzTrackId = musicbrainzTrackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

}
