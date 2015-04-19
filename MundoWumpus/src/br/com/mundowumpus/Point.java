/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus;

/**
 *
 * @author 2091140052
 */
public class Point {

    private Integer x;
    private Integer y;

    public Point() {
        super();
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point m = ((Point) obj);
        if (this.x == m.x && this.y == m.y) {
            return true;
        }
        return false;
    }

}
