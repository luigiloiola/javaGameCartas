package Model;

import Controller.KeyHandler;

import java.util.ArrayList;

public class Model implements Runnable {

    Thread modelThread;


    final double pseudoFPS = 200;

    public ArrayList<KeyHandler> keyHList;

    private static volatile Model INSTANCE = null;

    private Model() {
        modelThread = new Thread(this);
        modelThread.start();
        keyHList = new ArrayList<>();
    }

    public static Model getInstance() {
        if (INSTANCE == null) {
            synchronized (Model.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Model();
                }
            }
        }
        return INSTANCE;
    }

    public void addKeyHandler(KeyHandler keyH) {
        keyHList.add(keyH);
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
                System.out.println("model" + " " + remainingTime);
                nextPaintTime += paintInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void update() {
        if(keyHList.size() != 0) {
            for(KeyHandler i: keyHList) {
                if(i.upPressed){
                    i.player.posY -= i.player.velocity;
                }
                if(i.downPressed) {
                    i.player.posY += i.player.velocity;
                }
                if(i.leftPressed) {
                    i.player.posX -= i.player.velocity;
                }
                if(i.rightPressed) {
                    i.player.posX += i.player.velocity;
                }
            }
        }
    }
}
