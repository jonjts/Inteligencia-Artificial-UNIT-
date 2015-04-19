/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus.entity;

import br.com.mundowumpus.Point;

/**
 *
 * @author Jonas
 */
public class Buraco extends EntityGame {

    public Buraco(Point point) {
        super(point);
    }

    @Override
    public String toString() {
        return "Buraco";
    }

    
}
