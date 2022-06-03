package Main;

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

        JFrame window2 = new JFrame();
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setResizable(false);
        window2.setTitle("game");


        GamePannel gamePannel2 = new GamePannel();
        gamePannel2.FPS = 144;
        window2.add(gamePannel2);

        window2.pack();



        window2.setLocationRelativeTo(null);
        window2.setVisible(true);



    }
}
