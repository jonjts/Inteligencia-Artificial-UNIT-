/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus.entity;

import br.com.mundowumpus.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Jonas
 */
public class Monstro extends EntityGame {

    private boolean live;

    public Monstro(Point pontoAtual) {
        super(pontoAtual);
        this.live = true;
    }

    public Monstro() {
        super(null);
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "Monstro";
    }

}
