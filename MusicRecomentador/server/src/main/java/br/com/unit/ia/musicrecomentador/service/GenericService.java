/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unit.ia.musicrecomentador.service;

import br.com.unit.ia.musicrecomentador.control.IControl;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonas
 */
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
public class GenericService<E> {

    private IControl<E> controle;

    public GenericService(IControl<E> controle) {
        this.controle = controle;
    }

    @GET
    public Response getAll() {
        try {
            return Response.ok(controle.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    
    @PUT
    public Response put(E object){
        try {
            return Response.ok(controle.insert(object)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    
    @POST
    public Response post(E object){
        try {
            controle.update(object);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        try {
            controle.delete(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
}
