/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus.entity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class Place {

    private List<EntityGame> elementos = new ArrayList<EntityGame>();

    public List<EntityGame> getElementos() {
        return elementos;
    }

    public boolean add(EntityGame entityGame) {
        return elementos.add(entityGame);
    }

    public boolean isEmpity() {
        return elementos.isEmpty();
    }

    public List<Sensor> getSeonsors() {
        List<Sensor> sensors = new ArrayList<Sensor>();
        for (EntityGame eg : elementos) {
            if (eg instanceof Sensor) {
                sensors.add((Sensor) eg);
            }
        }
        return sensors;
    }

    public boolean removeLegolas() {
        Legolas legolas = getLegolas();
        if (legolas != null) {
            return elementos.remove(legolas);
        }
        return false;
    }

    public boolean canPutSensor() {
        if (getBuraco() == null && getMonstro() == null && getLegolas() == null && getOuro() == null) {
            return true;
        }
        return false;
    }

    public Legolas getLegolas() {
        for (EntityGame eg : elementos) {
            if (eg instanceof Legolas) {
                return (Legolas) eg;
            }
        }
        return null;
    }

    public Buraco getBuraco() {
        for (EntityGame eg : elementos) {
            if (eg instanceof Buraco) {
                return (Buraco) eg;
            }
        }
        return null;
    }

    public Monstro getMonstro() {
        for (EntityGame eg : elementos) {
            if (eg instanceof Monstro) {
                return (Monstro) eg;
            }
        }
        return null;
    }

    public Ouro getOuro() {
        for (EntityGame eg : elementos) {
            if (eg instanceof Ouro) {
                return (Ouro) eg;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (!isEmpity()) {
            StringBuilder builder = new StringBuilder();
            for (EntityGame entityGame : elementos) {
                builder.append(entityGame.toString());
            }
            return builder.toString();
        } else {
            return "-";
        }
    }

}
