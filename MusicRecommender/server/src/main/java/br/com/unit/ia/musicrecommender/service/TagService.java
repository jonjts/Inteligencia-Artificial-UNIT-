/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.TagControl;
import br.com.unit.ia.musicrecommender.entity.Tag;
import javax.ws.rs.Path;

/**
 *
 * @author Jonas
 */
@Path("tags")
public class TagService extends GenericService<Tag> {
    
    public TagService() {
        super(new TagControl());
    }
    
}
