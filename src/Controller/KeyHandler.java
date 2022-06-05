package Controller;

import Model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Player;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public Player player;
    private String name;

    public KeyHandler(String name) {
        this.name = name;
        Model.getInstance().keyHList.add(this);
        player = new Player(name);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(Model.getInstance().gameState == 1){
            if(Model.getInstance().keyHList.get(0) == this) {
                if(code == KeyEvent.VK_W && player.posY!=115) {
                    player.posY-=player.velocityY;
                    player.hovering -= 4;
                    System.out.println(player.posY);
                    System.out.println(player.posX);

                }
                if(code == KeyEvent.VK_S && player.posY!=691) {
                    player.posY+=player.velocityY;
                    player.hovering+=4;}
                if(code == KeyEvent.VK_A && player.posX!=115) {
                    player.posX-=player.velocityX;
                    player.hovering-=1;
                }
                if(code == KeyEvent.VK_D && player.posX!=1123) {
                    player.posX+=player.velocityX;
                    player.hovering+=1;
                }
                if(code == KeyEvent.VK_E && !Model.getInstance().posicaoCartas.get(player.hovering).out) {
                    if(player.viewing.size()<=1){
                        Model.getInstance().posicaoCartas.get(player.hovering).aberto=true;
                        player.viewing.add(Model.getInstance().posicaoCartas.get(player.hovering));
                    }
                }
            }else{
                if(code == KeyEvent.VK_I && player.posY!=115) {
                    player.posY-=player.velocityY;
                    player.hovering -= 4;
                }
                if(code == KeyEvent.VK_K && player.posY !=691) {
                    player.posY+=player.velocityY;
                    player.hovering+=4;

                }
                if(code == KeyEvent.VK_J && player.posX!=115) {
                    player.posX -= player.velocityX;
                    player.hovering -= 1;
                }
                if(code == KeyEvent.VK_L && player.posX!=1123) {

                    player.posX+=player.velocityX;
                    player.hovering+=1;
                }
                if(code == KeyEvent.VK_O && !Model.getInstance().posicaoCartas.get(player.hovering).out) {
                    if(player.viewing.size()<=1){
                        Model.getInstance().posicaoCartas.get(player.hovering).aberto=true;
                        player.viewing.add(Model.getInstance().posicaoCartas.get(player.hovering));
                    }
                }
            }
        }else if(Model.getInstance().gameState == 0){
            if(player == Model.getInstance().keyHList.get(0).player){
                if(code == KeyEvent.VK_DOWN && player.posM!=3){
                    player.posM +=1;
                }
                if(code == KeyEvent.VK_UP && player.posM!=1){
                    player.posM -=1;
                }
                else if(code == KeyEvent.VK_SPACE){
                    Model.getInstance().gameState = player.posM;
                }
            }
        }else if(Model.getInstance().gameState == 2){
            if(player == Model.getInstance().keyHList.get(0).player){
                if(code == KeyEvent.VK_DOWN && player.posM!=1){
                    player.posM +=1;
                }
                if(code == KeyEvent.VK_UP && player.posM!=0){
                    player.posM -=1;
                }
                else if(code == KeyEvent.VK_SPACE){
                    Model.getInstance().gameState = player.posM;
                    if (player.posM == 0){
                        player.posM = 1;
                    }
                }
            }
        }if(code == KeyEvent.VK_ESCAPE){
            Model.getInstance().gameState = 0;
            player.posM = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
