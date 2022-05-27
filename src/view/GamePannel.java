package view;

import Controller.KeyHandler;
import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class GamePannel extends JPanel implements Runnable{

    Thread viewThread;

    public double FPS = 144;

    final int originalSize = 16;
    final int scale = 3;

    final int size = originalSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = size * maxScreenCol;
    final int screenHeight = size * maxScreenRow;

    KeyHandler keyH = new KeyHandler();


    public GamePannel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        startViewThread();

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
                Thread.sleep((long)remainingTime);
                System.out.println("view" + " " + remainingTime);
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

        for(int i = 0; i < Model.getInstance().keyHList.size(); i++) {
            if(i == 0) {
                g2.setColor(Color.BLUE);
            } else {
                g2.setColor(Color.RED);
            }

            g2.fillRect(Model.getInstance().keyHList.get(i).player.posX, Model.getInstance().keyHList.get(i).player.posY, size, size);
        }

    }
}
