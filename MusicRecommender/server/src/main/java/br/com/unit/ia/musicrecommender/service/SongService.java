/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.SongControl;
import br.com.unit.ia.musicrecommender.entity.Song;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonas
 */
@Path("songs")
public class SongService extends GenericService<Song>{

    public SongService() {
        super(new SongControl());
    }

    @GET
    public Response getAll(@QueryParam("offset") Long offset, @QueryParam("limit") Long limit){
        try {
            offset = (offset == null) ? 0 : offset;
            limit = (limit == null) ? 100 : limit;
            SongControl control = (SongControl) getControl();
            Collection list = control.getAll(offset, limit);
            return Response.ok(list).build();
        } catch (Exception ex) {
            Logger.getLogger(SongService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    
    
}
