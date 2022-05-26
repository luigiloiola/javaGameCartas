package Main;

import Controller.KeyHandler;
import Controller.KeyHandler2;
import Model.Model;
import view.GamePannel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("game");


        GamePannel gamePannel = new GamePannel();
        window.add(gamePannel);

        window.pack();



        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
