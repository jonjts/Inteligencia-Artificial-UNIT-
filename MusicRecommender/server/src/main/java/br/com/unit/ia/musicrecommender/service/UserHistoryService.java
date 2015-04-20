/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.UserHistoryControl;
import br.com.unit.ia.musicrecommender.entity.UserHistory;
import javax.validation.constraints.Past;
import javax.ws.rs.Path;

/**
 *
 * @author Jonas
 */
@Path("userHistorys")
public class UserHistoryService extends GenericService<UserHistory> {

    public UserHistoryService() {
        super(new UserHistoryControl());
    }
    
}
