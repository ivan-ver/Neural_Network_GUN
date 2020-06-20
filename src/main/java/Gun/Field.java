package Gun;

import Visualisation.NN_visualisation;
import Visualisation.Visualisation_geometry;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;


public class Field extends JComponent implements Runnable {

    NN_visualisation visualisation;
    private Gun gun;
    private Ship ship;
    private Line2D line1;
    private Line2D line2;
    private Line2D line3;

    public Field(Gun gun) {
        this.line1 = new Line2D.Double(Visualisation_geometry.border,
                Visualisation_geometry.border,
                Visualisation_geometry.workSpaceWidth,
                Visualisation_geometry.border);

        this.line2 = new Line2D.Double(Visualisation_geometry.border,
                Visualisation_geometry.border,
                Visualisation_geometry.border,
                Visualisation_geometry.windowHeight);

        this.line3 = new Line2D.Double(Visualisation_geometry.workSpaceWidth,
                Visualisation_geometry.border,
                Visualisation_geometry.workSpaceWidth,
                Visualisation_geometry.windowHeight);

        this.gun = gun;
        this.ship = new Ship();
        this.gun.arm(this.ship);

        this.visualisation = new NN_visualisation(this.gun.getBrain());

        (new Thread(this)).start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D line = (Graphics2D) g;
        line.setColor(Color.WHITE);
        line.setStroke(new BasicStroke(5.0f));
        line.draw(this.line1);
        line.draw(this.line2);
        line.draw(this.line3);

        if (this.ship!=null) {
            this.ship.draw(g);
        }
        if (this.gun.getShell()!=null) {
            this.gun.getShell().draw(g);
        }
        this.gun.draw(g);
        this.visualisation.draw(g);

    }

    @Override
    public void run() {
           while (true) {
               this.ship = new Ship();
               sleep(30);
               this.gun.arm(this.ship);
               while (this.gun.getShell().isLive()) {
                    this.gun.getShell().move();
                    this.gun.getShell().check();
                    sleep(15);
                    super.repaint();
               }
           }
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

