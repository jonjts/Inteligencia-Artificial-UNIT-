/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.algorithm;

import br.com.unit.ia.musicrecommender.control.ArtistControl;
import br.com.unit.ia.musicrecommender.control.SongControl;
import br.com.unit.ia.musicrecommender.control.UserArtistControl;
import br.com.unit.ia.musicrecommender.control.UserTaggedArtistsControl;
import br.com.unit.ia.musicrecommender.entity.Artist;
import br.com.unit.ia.musicrecommender.entity.Song;
import br.com.unit.ia.musicrecommender.entity.Tag;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserArtist;
import br.com.unit.ia.musicrecommender.entity.UserTaggedArtists;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class Algorithm {

    private UserTaggedArtistsControl taggedArtistsControl = new UserTaggedArtistsControl();
    private UserArtistControl userArtistControl = new UserArtistControl();
    private ArtistControl artistControl = new ArtistControl();
    private SongControl songControl = new SongControl();

    private User userLogado;
    private List<Tag> userTags;
    private List<Artist> userArtists;
    private HashMap<Double, List<User>> userAleatoriosMap;
    private int limitSong;
    private ArrayList<Double> afinidadesSorted;

    public Algorithm(User userLogado, int limitSong) {
        this.userLogado = userLogado;
        this.limitSong = limitSong;
    }

    public List<Song> doTheMagic() {
        try {
            List<User> randomUsers = getRandomUsers();
            checkAfinidade(randomUsers);
            //Ordena o valor da afinidade em ordem decrescente
            sortRandomUsers();
            List<Song> songs = getSongs();
            return songs;
        } catch (SQLException ex) {
            Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private List<Song> getSongs() throws SQLException {
        List<Song> songs = new ArrayList<Song>();
        while (songs.size() != limitSong) {
            //Percorre as finidades
            for (Double afinidade : afinidadesSorted) {
                List<User> users = userAleatoriosMap.get(afinidade);
                for (User user : users) {
                    Song song = songControl.get(user);
                    songs.add(song);
                    if (songs.size() == limitSong) {
                        break;
                    }
                }
                if (songs.size() == limitSong) {
                    break;
                }
            }
        }
        return songs;
    }

    private void sortRandomUsers() {
        Set<Double> keySet = userAleatoriosMap.keySet();
        afinidadesSorted = new ArrayList<Double>(keySet);
        for (int i = 0; i < afinidadesSorted.size(); i++) {
            Double now = afinidadesSorted.get(i);
            if (i + 1 != afinidadesSorted.size()) {
                Double next = afinidadesSorted.get(i + 1);
                if (next > now) {
                    afinidadesSorted.set(i, next);
                    afinidadesSorted.set(i + 1, now);
                }
            } else {
                break;
            }

        }
    }

    private void checkAfinidade(List<User> list) throws SQLException {
        int totalArtist = userArtists.size();
        int totalTags = userTags.size();
        int total = totalArtist + totalTags;

        userAleatoriosMap = new HashMap<Double, List<User>>();
        for (User u : list) {
            double checkAfinidadeArtist = checkAfinidadeArtist(u);
            double checkAfinidadeTags = checkAfinidadeTags(u);
            double totalCheck = checkAfinidadeArtist + checkAfinidadeTags;

            double afinidade = totalCheck / total;
            List<User> get = userAleatoriosMap.get(afinidade);
            if (get == null) {
                get = new ArrayList<User>();
                get.add(u);
                userAleatoriosMap.put(afinidade, get);
            } else {
                get.add(u);
                userAleatoriosMap.put(afinidade, get);
            }
        }

    }

    private double checkAfinidadeArtist(User userAleatorio) throws SQLException {
        List<Artist> list = artistControl.get(userAleatorio);

        int totalArtist = userArtists.size();
        int parcialArtistAleatorio = 0;

        for (Artist a : userArtists) {
            if (list.contains(a)) {
                parcialArtistAleatorio++;
            }
        }
        double afinidade = parcialArtistAleatorio % totalArtist;
        return afinidade;
    }

    private double checkAfinidadeTags(User userAleatorio) throws SQLException {
        List<UserTaggedArtists> list = taggedArtistsControl.get(userAleatorio, true);
        List<Tag> tagsUserAleatorio = getTags(list);

        int totalTags = this.userTags.size();
        int parcialTagsUserAleatorio = 0;

        for (Tag t : userTags) {
            if (tagsUserAleatorio.contains(t)) {
                parcialTagsUserAleatorio++;
            }
        }

        double afinidade = parcialTagsUserAleatorio % totalTags;
        return afinidade;
    }

    private List<User> getRandomUsers() throws SQLException {
        List<UserTaggedArtists> userTaggedArtistses = taggedArtistsControl.get(userLogado, true);

        initListArtistTagsUserLogado(userTaggedArtistses);

        List<Long> idTags = new ArrayList<Long>();

        for (Tag tag : userTags) {
            if (!idTags.contains(tag.getId())) {
                idTags.add(tag.getId());
            }
        }
        List<UserTaggedArtists> userAleatorios = taggedArtistsControl.get(idTags, 300);
        return getUsers(userAleatorios);
    }

    private List<User> getUsers(List<UserTaggedArtists> list) {
        List<User> users = new ArrayList<User>();
        for (UserTaggedArtists u : list) {
            users.add(u.getUser());
        }
        return users;
    }

    private void initListArtistTagsUserLogado(List<UserTaggedArtists> userTaggedArtistses) throws SQLException {
        userTags = getTags(userTaggedArtistses);
        userArtists = getArtists(userLogado);
    }

    private List<Artist> getArtists(User user) throws SQLException {
        return artistControl.get(user);
    }

    private List<Tag> getTags(List<UserTaggedArtists> list) {
        List<Tag> tags = new ArrayList<Tag>();
        for (UserTaggedArtists uta : list) {
            final Tag tag = uta.getTag();
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        return tags;
    }
}
