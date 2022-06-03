package Controller;

import Model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Player;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public Player player;

    public KeyHandler() {
        Model.getInstance().keyHList.add(this);
        player = new Player();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            player.posY-=player.velocityY;
            player.hovering -= 4;
            System.out.println(player.hovering);
            System.out.println(Model.getInstance().posicaoCartas.get(player.hovering).conteudo);
        }
        if(code == KeyEvent.VK_S) {
            player.posY+=player.velocityY;
            player.hovering+=4;
            System.out.println(player.hovering);
            System.out.println(Model.getInstance().posicaoCartas.get(player.hovering).conteudo);
        }
        if(code == KeyEvent.VK_A) {
            player.posX-=player.velocityX;
            player.hovering-=1;
            System.out.println(player.hovering);
            System.out.println(Model.getInstance().posicaoCartas.get(player.hovering).conteudo);
        }
        if(code == KeyEvent.VK_D) {
            player.posX+=player.velocityX;
            player.hovering+=1;
            System.out.println(player.hovering);
            System.out.println(Model.getInstance().posicaoCartas.get(player.hovering).conteudo);
        }
        if(code == KeyEvent.VK_E && !Model.getInstance().posicaoCartas.get(player.hovering).out) {
            Model.getInstance().posicaoCartas.get(player.hovering).aberto=true;
            player.viewing.add(Model.getInstance().posicaoCartas.get(player.hovering));
            System.out.println(player.viewing);
        }
        if(code == KeyEvent.VK_E && !Model.getInstance().posicaoCartas.get(player.hovering).out){
            Model.getInstance().posicaoCartas.get(player.hovering).aberto=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
