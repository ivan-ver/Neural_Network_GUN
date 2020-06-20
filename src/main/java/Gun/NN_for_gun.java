package Gun;


import Visualisation.Visualisation_geometry;

import javax.swing.*;
import java.awt.*;

public class NN_for_gun {


    public static void main(String[] args) {
        Gun gun = new Gun();
        trainGun(gun);

        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - Visualisation_geometry.windowWidth/2,
                dimension.height/2 - Visualisation_geometry.windowHeight/2,
                Visualisation_geometry.windowWidth,
                Visualisation_geometry.windowHeight);
        jFrame.setVisible(true);
        jFrame.setTitle("Neural Network Gun");
        jFrame.getContentPane().setBackground(new Color(67, 67, 67));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(new Field(gun));
    }

    static void trainGun(Gun gun){
        Ship ship;
        for (int i = 50; i < 736; i += 46) {
            for (int j = 50; j < 1100; j += 82) {
                ship = new Ship(j,i);
                gun.arm(ship);
                while (ship.isLive()) {
                    while (gun.getShell().isLive()) {
                        gun.getShell().move();
                        gun.getShell().trainCheck();
                    }
                }
            }
        }
        gun.train();
    }
}

