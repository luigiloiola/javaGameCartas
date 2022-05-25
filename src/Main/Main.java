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

        KeyHandler keyH1 = new KeyHandler();
        KeyHandler2 keyH2 = new KeyHandler2();

        Model model = new Model(keyH1, keyH2);
        model.startModelThread();


        GamePannel gamePannel = new GamePannel(model, keyH1);
        window.add(gamePannel);
        gamePannel.startViewThread();

        window.pack();



        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
