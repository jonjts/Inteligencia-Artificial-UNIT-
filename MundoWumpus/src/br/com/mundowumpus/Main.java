/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus;

import br.com.mundowumpus.entity.Sensor;
import java.util.List;

/**
 *
 * @author 2091140052
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto();
        labirinto.draw();

        int y = 0;
        int x = 0;
        boolean checkX = true;
        boolean andouTudoY = false;
        while (!labirinto.isGotPrecious()) {
            if (labirinto.isOk(x, y)) {
                labirinto.moveLegolas(x +1 , y);
                labirinto.getLegolas().setMovement(x+1, y, Boolean.TRUE);
                labirinto.draw();
            } else {
                List<Sensor> sensors = labirinto.getSensors(x, y);
                if (sensors.size() > 0) {
                    if (labirinto.hasResplendor(x, y)) {
                        labirinto.moveLegolas(x+1 , y);
                        labirinto.draw();
                        labirinto.getLegolas().setMovement(x + 1, y, Boolean.TRUE);
                    } else {
                        while (x != 0) {
                            labirinto.moveLegolas(--x, y);
                            labirinto.draw();
                        }
                        checkX = false;
                        if (andouTudoY) {
                            y--;
                        } else {
                            y++;
                        }
                        if (y == 3) {
                            andouTudoY = true;
                        }
                        if (y == 0 && andouTudoY) {
                            andouTudoY = false;
                        }
                        
                        labirinto.moveLegolas(x, y);
                        labirinto.draw();
                    }
                }
            }
            labirinto.quitGame();
            if (checkX) {
                if(x == 3){
                    y++;
                }
                x++;
                if (x > 3) {
                    x = 0;
                }
            } else {
                checkX = true;
            }
        }

    }

}
