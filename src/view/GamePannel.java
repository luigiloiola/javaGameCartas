package view;

import Controller.KeyHandler;
import Model.Model;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable{

    Thread viewThread;

    Model model;

    KeyHandler keyH;

    final double FPS = 144;

    final int originalSize = 16;
    final int scale = 3;

    final int size = originalSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = size * maxScreenCol;
    final int screenHeight = size * maxScreenRow;



    public GamePannel (Model model, KeyHandler keyH) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.model = model;
        this.keyH = keyH;
        this.addKeyListener(keyH);

    }

    public void startViewThread() {
        viewThread = new Thread(this);
        viewThread.start();
    }


    @Override
    public void run() {

        double paintInterval = 1000000000/FPS;
        double nextPaintTime = System.nanoTime() + paintInterval;


        while(viewThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextPaintTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0 ){
                    remainingTime = 0;
                }
                System.out.println(remainingTime);
                Thread.sleep((long)remainingTime);

                nextPaintTime += paintInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);

        g2.fillRect(model.playerX, model.playerY, size, size);



    }
}