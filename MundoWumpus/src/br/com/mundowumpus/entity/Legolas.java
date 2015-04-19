/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus.entity;

import br.com.mundowumpus.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 2091140052
 */
public class Legolas extends EntityGame {

    private final HashMap<Point, Boolean> movimentosFeitos;
    private Integer flecha;

    public Legolas(Point pontoAtual) {
        super(pontoAtual);
        this.flecha = 1;
        this.movimentosFeitos = new HashMap<Point, Boolean>();
    }

    public void setMovement(int x, int y, Boolean b) {
        Point m = binMovimento(x, y);
        movimentosFeitos.put(m, b);
    }

    private Point binMovimento(int x, int y) {
        Point m = new Point();
        m.setX(x);
        m.setY(y);
        return m;
    }

    public Boolean isSafe(int x, int y) {
        Point binMovimento = binMovimento(x, y);
        return movimentosFeitos.get(binMovimento);
    }

    @Override
    public String toString() {
        return "Legolas";
    }

}
