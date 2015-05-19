/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.algorithm;

import br.com.unit.ia.musicrecommender.control.UserControl;
import br.com.unit.ia.musicrecommender.control.UserHistoryControl;
import br.com.unit.ia.musicrecommender.entity.User;
import br.com.unit.ia.musicrecommender.entity.UserHistory;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Jonas
 */
public class Algorithm {
    
    private UserControl userControl;
    private UserHistoryControl historyControl;
    private User userLogado;

    public Algorithm(User userLogado) {
        this.userLogado = userLogado;
        userControl = new UserControl();
        historyControl = new UserHistoryControl();
    }
    
    public void doTheMagic() throws SQLException{
        Collection<User> randomByCountry = getRandomByCountry(userLogado);
        Collection<UserHistory> get = historyControl.get(randomByCountry);
    }
    
    
    
    private Collection<User> getRandomByCountry(User user) throws SQLException{
        Collection<User> byCountry = userControl.getByCountry(user, 10);
        return byCountry;
    }
    
}
