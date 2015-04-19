/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus.entity;

import br.com.mundowumpus.Point;

/**
 *
 * @author 2091140052
 */
public class Sensor extends EntityGame {

    public static final int FEDOR = 0;
    public static final int BRISA = 1;
    public static final int RESPLENDOR = 2;
    public static final int IMPACTO = 3;
    public static final int AUDICAO = 4;

    private Integer id;

    public static Sensor getFedor(Point point) {
        Sensor sensor = new Sensor(point);
        sensor.setId(FEDOR);
        return sensor;
    }

    public Sensor(Point point) {
        super(point);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String string = "";
        switch (getId()) {
            case BRISA:
                string = "B";
                break;
            case FEDOR:
                string = "F";
                break;
            case RESPLENDOR:
                string = "R";
                break;
        }
        return string;
    }

}
