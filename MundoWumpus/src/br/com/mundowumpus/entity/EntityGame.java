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
public class EntityGame {

    private Point pontoAtual = new Point();

    public EntityGame(Point point) {
        this.pontoAtual = point;
    }

    public Point getPontoAtual() {
        return pontoAtual;
    }

    public void setPontoAtual(Point pontoAtual) {
        this.pontoAtual = pontoAtual;
    }

}
