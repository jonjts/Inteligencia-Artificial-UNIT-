/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mundowumpus;

import br.com.mundowumpus.entity.Buraco;
import br.com.mundowumpus.entity.Sensor;
import br.com.mundowumpus.entity.Legolas;
import br.com.mundowumpus.entity.Monstro;
import br.com.mundowumpus.entity.Ouro;
import br.com.mundowumpus.entity.Place;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 2091140052
 */
public class Labirinto {

    private Legolas legolas;
    private Place[][] tabela = new Place[4][4];
    private Random random = new Random();

    private boolean gotPrecious = false;

    public Labirinto() {
        setPlaces();
        setLegolas();
        setMonstro(1);
        setOuro(1);
        setBuraco(1);
    }

    public void moveLegolas(int x, int y) {
        if (x >= 0 && x <= 3 && y >= 0 && y <= 3) {
            Point pontoAtual = legolas.getPontoAtual();
            tabela[pontoAtual.getX()][pontoAtual.getY()].removeLegolas();
            legolas.setPontoAtual(new Point(x, y));
            tabela[x][y].add(legolas);
            checkPrecious(x, y);
        }
    }

    public boolean hasResplendor(int x, int y) {
        Place p = tabela[x][y];
        List<Sensor> seonsors = p.getSeonsors();
        for (Sensor s : seonsors) {
            if (s.getId() == Sensor.RESPLENDOR) {
                return true;
            }
        }
        return false;
    }

    public void quitGame() {
        Point pontoAtual = legolas.getPontoAtual();
        final Integer x = pontoAtual.getX();
        final Integer y = pontoAtual.getY();
        checkGameOver(x, y);
        boolean checkPrecious = checkPrecious(x, y);
        if (checkPrecious) {
            draw();
            System.exit(0);
        }
    }

    public List<Sensor> getSensors(int x, int y) {
        Place p = tabela[x][y];
        return p.getSeonsors();
    }

    public Legolas getLegolas() {
        return legolas;
    }

    public boolean isGotPrecious() {
        return gotPrecious;
    }

    public void draw() {
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela.length; j++) {
                System.out.printf("%7s ", tabela[i][j].toString());
            }
            System.out.println("");
        }
        System.out.println("**************************************************");
    }

    public int nextStepX() {
        int x = legolas.getPontoAtual().getX().intValue();
        int nextX = x - 1;
        if (x <= 3) {
            return x;
        }
        return nextX;
    }

    public int nextStepY() {
        int y = legolas.getPontoAtual().getY().intValue();
        int nextY = y - 1;
        if (y <= 3) {
            return y;
        }
        return nextY;
    }

    private void checkGameOver(int x, int y) {
        Place p = tabela[x][y];
        if (p.getMonstro() != null && p.getLegolas() != null) {
            draw();
            System.err.println("GAME OVER: O monstro comeu Legolas ( ° ͜ʖ ͡)");
            System.exit(0);
        }
        if (p.getBuraco() != null && p.getLegolas() != null) {
            draw();
            System.err.println("GAME OVER: Legolas caiu no burraco");
            System.exit(0);
        }

    }

    private boolean checkPrecious(int x, int y) {
        Place p = tabela[x][y];
        if (p.getLegolas() != null && p.getOuro() != null) {
            gotPrecious = true;
        }
        return isGotPrecious();
    }

    private void setPlaces() {
        for (int i = 0; i < tabela.length; i++) {
            for (int j = 0; j < tabela.length; j++) {
                tabela[i][j] = new Place();
            }
        }
    }

    private void setLegolas() {
        legolas = new Legolas(new Point(0, 0));
        tabela[0][0].add(legolas);
    }

    private void setOuro(int quantidadeOuro) {
        for (int i = 0; i < quantidadeOuro; i++) {
            boolean isBeleza = false;
            do {
                int x = random.nextInt(4);
                int y = random.nextInt(4);

                Place p = tabela[x][y];
                if (p.isEmpity() && !isLegolasNear(x, y)) {
                    final Point point = new Point(x, y);
                    p.add(new Ouro(point));
                    setSensor(point, Sensor.RESPLENDOR);
                    isBeleza = true;
                }
            } while (!isBeleza);
        }
    }

    private void setBuraco(int quantidadeBuraco) {
        for (int i = 0; i < quantidadeBuraco; i++) {
            boolean isBeleza = false;
            do {
                int x = random.nextInt(4);
                int y = random.nextInt(4);

                Place p = tabela[x][y];
                if (p.isEmpity() && !isLegolasNear(x, y)) {
                    final Point point = new Point(x, y);
                    p.add(new Buraco(point));
                    setSensor(point, Sensor.BRISA);
                    isBeleza = true;
                }
            } while (!isBeleza);
        }
    }

    private void setMonstro(int quantidadeMonstros) {
        for (int i = 0; i < quantidadeMonstros; i++) {
            boolean isBeleza = false;
            do {
                int x = random.nextInt(4);
                int y = random.nextInt(4);

                Place p = tabela[x][y];
                if (p.isEmpity() && !isLegolasNear(x, y)) {
                    final Point point = new Point(x, y);
                    p.add(new Monstro(point));
                    setSensor(point, Sensor.FEDOR);
                    isBeleza = true;
                }
            } while (!isBeleza);
        }
    }

    private boolean isLegolasNear(int x, int y) {
        int ok = 0;
        if (x - 1 >= 0) {
            Legolas legolas = tabela[x - 1][y].getLegolas();
            ok = isNull(legolas, ok);
        }
        if (x + 1 < tabela.length) {
            Legolas legolas = tabela[x + 1][y].getLegolas();
            ok = isNull(legolas, ok);
        }
        if (y - 1 >= 0) {
            Legolas legolas = tabela[x][y - 1].getLegolas();
            ok = isNull(legolas, ok);
        }
        if (y + 1 < tabela.length) {
            Legolas legolas = tabela[x][y + 1].getLegolas();
            ok = isNull(legolas, ok);
        }
        return ok > 0;
    }

    private int isNull(Legolas legolas1, int ok) {
        if (legolas1 != null) {
            ok++;
        }
        return ok;
    }

    private void setSensor(Point point, int idSensor) {
        int x = point.getX().intValue();
        int y = point.getY().intValue();
        Sensor sensor;

        int tempX = x - 1;
        if (tempX >= 0) {
            Place p = tabela[tempX][y];
            if (p.canPutSensor()) {
                sensor = new Sensor(point);
                sensor.setId(idSensor);
                p.add(sensor);
            }
        }
        tempX = x + 1;
        if (tempX <= 3) {
            Place p = tabela[tempX][y];
            if (p.canPutSensor()) {
                sensor = new Sensor(point);
                sensor.setId(idSensor);
                p.add(sensor);
            }
        }

        int tempY = y - 1;

        if (tempY >= 0) {
            Place p = tabela[x][tempY];
            if (p.canPutSensor()) {
                sensor = new Sensor(point);
                sensor.setId(idSensor);
                p.add(sensor);
            }
        }
        tempY = y + 1;
        if (tempY <= 3) {
            Place p = tabela[x][tempY];
            if (p.canPutSensor()) {
                sensor = new Sensor(point);
                sensor.setId(idSensor);
                p.add(sensor);
            }
        }
    }

    public static List<Point> dangerPlace = new ArrayList<Point>();

    public static void setDangerPlace(int x, int y) {
        Point mov = new Point();

        mov.setX(x);
        mov.setY(y);

        dangerPlace.add(mov);
    }

    public boolean isOk(int x, int y) {
        return tabela[x][y].getSeonsors().isEmpty();
    }
}
