/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.unit.ia.musicrecomentador.control;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jonas
 */
public interface IControl<E> {
    
    public E insert(E object) throws Exception;
    
    public Collection<E> getAll() throws Exception;
    
    public void delete(Object id) throws Exception;
    
    public void update(E object) throws Exception;
}
