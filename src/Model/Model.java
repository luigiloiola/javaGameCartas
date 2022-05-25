package Model;

import Controller.KeyHandler;
import Controller.KeyHandler2;
import view.GamePannel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Model implements Runnable {

    Thread modelThread;
    public KeyHandler keyH1;
    public KeyHandler2 keyH2;


    public double pseudoFPS = 200;

    public int playerX;
    public int playerY;
    public double playerVelocity = 2;



    public Model(KeyHandler keyH1, KeyHandler2 keyH2) {
        this.keyH1 = keyH1;
        this.keyH2 = keyH2;
    }

    public void startModelThread() {
        modelThread = new Thread(this);
        modelThread.start();
    }




    //game loop
    @Override
    public void run() {
        double paintInterval = 1000000000/pseudoFPS;
        double nextPaintTime = System.nanoTime() + paintInterval;


        while(modelThread != null) {

            update();

            try {
                double remainingTime = nextPaintTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0 ){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextPaintTime += paintInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void update() {
        if(keyH1.upPressed){
            playerY -= playerVelocity;
        }
        if(keyH1.downPressed) {
            playerY += playerVelocity;
        }
        if(keyH1.leftPressed) {
            playerX -= playerVelocity;
        }
        if(keyH1.rightPressed) {
            playerX += playerVelocity;
        }
    }
}
