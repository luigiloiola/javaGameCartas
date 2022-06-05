package view;

import Controller.KeyHandler;
import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import Model.Cartas;
import Model.Player;

public class GamePannel extends JPanel implements Runnable{

    Thread viewThread;

    public double FPS = 144;

    final int originalSize = 16;
    final int scale = 3;

    final int size = originalSize * scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 20;

    final int screenWidth = size * maxScreenCol;
    final int screenHeight = size * maxScreenRow;

    KeyHandler keyH = new KeyHandler("nome1");
    KeyHandler keyH2 = new KeyHandler("nome2");


    public GamePannel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.addKeyListener(keyH2);
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
                //System.out.println("view" + " " + remainingTime);
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
        if (Model.getInstance().gameState == 1){
            this.setBackground(Color.darkGray);
            Player jogador1 = Model.getInstance().keyHList.get(0).player;
            Player jogador2 = Model.getInstance().keyHList.get(1).player;

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Sans Serif", Font.BOLD,25));
            if(jogador1.pontos + jogador2.pontos < 6){
                g2.drawString("jogador 1: " + Integer.toString(Model.getInstance().keyHList.get(0).player.pontos), 25,20);
                g2.drawString("jogador 2: " + Integer.toString(Model.getInstance().keyHList.get(1).player.pontos), 800,20);

                for(int i = 0; i < Model.getInstance().keyHList.size(); i++) {
                    if(i == 0) {
                        g2.setColor(Color.BLUE);
                    } else {
                        g2.setColor(Color.RED);
                    }
                    g2.fillRect(Model.getInstance().keyHList.get(i).player.posX, Model.getInstance().keyHList.get(i).player.posY, (size*4)+10, (size*3)+10);
                }
                int x=size*5/2;
                int y=size*5/2;
                int i = 0;
                for(Cartas c: Model.getInstance().posicaoCartas) {
                    if (i == 4) {
                        y+=size*6;
                        x=size*5/2;
                        i=0;
                    }
                    if(!c.out) {
                        g2.setFont(new Font("Sans Serif", Font.BOLD, 25));
                        g2.setColor(Color.WHITE);
                        g2.drawString(c.conteudo,x,y+25);
                        g2.setColor(Color.black);
                        g2.fillRect(x,y,size*4,size*3);
                        if(c.aberto) {
                            g2.setColor(Color.WHITE);
                            g2.drawString(c.conteudo,x,y+25);
                        }
                    }else{
                        g2.setColor(Color.darkGray);
                        g2.fillRect(x,y,size*4,size*3);
                    }
                    i++;
                    x+=size*7;
                }
            }else{
                Player vencedor;
                if(jogador1.pontos==3){
                    g2.drawString("Empate",500,500);
                } else{
                    if(jogador1.pontos>3){
                        vencedor = jogador1;
                    }else{
                        vencedor = jogador2;
                    }
                    g2.drawString("O vencedor foi: " + vencedor.name,500,500);
                }
            }
        }else if(Model.getInstance().gameState == 0) {
            this.setBackground(Color.black);
            g2.setColor(Color.white);
            g2.setFont(new Font("", Font.BOLD, 50));
            g2.drawString("jogar", 650, 400);
            g2.drawString("Leaderboard", 570, 500);
            g2.drawString("->", 500, Model.getInstance().keyHList.get(0).player.posM * 100 + 300);

        }
    }
}
