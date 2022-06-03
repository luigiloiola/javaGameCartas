package Model;

import Controller.KeyHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

public class Model implements Runnable {

    Thread modelThread;

    public Cartas head;
    public ArrayList<Cartas> posicaoCartas;


    final double pseudoFPS = 200;

    public ArrayList<KeyHandler> keyHList;

    private static volatile Model INSTANCE = null;

    private Model() {
        modelThread = new Thread(this);
        modelThread.start();
        keyHList = new ArrayList<>();
        head = Cartas.gerarCartas();
        map();
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


    public void map() {

        Cartas curr = head;
        int size = head.getSize();
        posicaoCartas = new ArrayList<Cartas>();

        int i=0;
        while(curr.next != null) {
            curr = curr.next;
            posicaoCartas.add(curr);
            posicaoCartas.add(curr.par);
            System.out.println(curr.conteudo);
            System.out.println(posicaoCartas.get(i));
            i+=2;
        }
        Collections.shuffle(posicaoCartas);
        int pos = 0;
        for(Cartas c:posicaoCartas) {
            c.pos = pos;
            pos++;
            System.out.println(c.conteudo);
        }
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
                //System.out.println("model" + " " + remainingTime);
                nextPaintTime += paintInterval;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void update() {
        for(KeyHandler k:keyHList) {
            if (k.player.viewing.size() ==2) {
                if(k.player.viewing.getFirst().par == k.player.viewing.getLast()) {
                    k.player.viewing.getFirst().out = true;
                    k.player.viewing.getLast().out = true;


                    k.player.viewing.getFirst().aberto = false;
                    k.player.viewing.remove(k.player.viewing.getFirst());
                    k.player.viewing.getLast().aberto = false;
                    k.player.viewing.remove(k.player.viewing.getLast());

                    System.out.println("aaaa");
                } else{
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k.player.viewing.getFirst().aberto = false;
                    k.player.viewing.remove(k.player.viewing.getFirst());
                    k.player.viewing.getLast().aberto = false;
                    k.player.viewing.remove(k.player.viewing.getLast());
                }

            }
        }
    }
}
