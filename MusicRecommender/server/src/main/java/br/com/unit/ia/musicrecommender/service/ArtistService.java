/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecommender.service;

import br.com.unit.ia.musicrecommender.control.ArtistControl;
import br.com.unit.ia.musicrecommender.control.IControl;
import br.com.unit.ia.musicrecommender.entity.Artist;
import br.com.unit.ia.musicrecommender.entity.Song;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonas
 */
@Path("artists")
public class ArtistService extends GenericService<Artist>{

    public ArtistService() {
        super(new ArtistControl());
    }
    
    @GET
    @Path("{id}/songs")
    public Response getSongs(@PathParam("id") Long id){
        if(id != null){
            try {
                ArtistControl controle = (ArtistControl) getControl();
                Collection<Song> songs = controle.getSongs(new Artist(id));
                return Response.ok(songs).build();
            } catch (Exception ex) {
                Logger.getLogger(ArtistService.class.getName()).log(Level.SEVERE, null, ex);
                return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            }
        } else{
            return Response.status(Response.Status.BAD_REQUEST).entity("ID invalido.").build();
        }
    }
    
}
