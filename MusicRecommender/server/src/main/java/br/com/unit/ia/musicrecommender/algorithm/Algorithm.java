/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.algorithm;

import br.com.unit.ia.musicrecommender.control.ArtistControl;
import br.com.unit.ia.musicrecommender.control.UserArtistControl;
import br.com.unit.ia.musicrecommender.control.UserTaggedArtistsControl;
import br.com.unit.ia.musicrecommender.entity.Artist;
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

    private User userLogado;
    private List<Tag> userTags;
    private List<Artist> userArtists;
    private HashMap<Double, User> userAleatoriosMap;

    public Algorithm(User userLogado) {
        this.userLogado = userLogado;
    }

    public void doTheMagic() {
        try {
            List<User> randomUsers = getRandomUsers();
            checkAfinidade(randomUsers);
        } catch (SQLException ex) {
            Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkAfinidade(List<User> list) throws SQLException {
        int totalArtist = userArtists.size();
        int totalTags = userTags.size();
        int total = totalArtist + totalTags;

        userAleatoriosMap = new HashMap<Double, User>();
        for (User u : list) {
            double checkAfinidadeArtist = checkAfinidadeArtist(u);
            double checkAfinidadeTags = checkAfinidadeTags(u);
            double totalCheck = checkAfinidadeArtist + checkAfinidadeTags;
            
            double afinidade = totalCheck /total;
            userAleatoriosMap.put(afinidade, u);
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
