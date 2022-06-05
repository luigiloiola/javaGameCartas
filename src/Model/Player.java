package Model;


import java.util.LinkedList;

public class Player {
    public int posX;
    public int posY;
    public int posM = 1;
    public int velocityY;
    public int velocityX;
    public int hovering = 0;
    public LinkedList<Cartas> viewing;
    public int pontos;
    public String name;


    public Player(String name) {
        posX=115;
        posY=115;
        velocityY = 48*6;
        velocityX = 48*7;
        viewing = new LinkedList<>();
        pontos = 0;
        this.name = name;

    }


}
