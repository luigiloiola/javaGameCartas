package Model;


import java.util.LinkedList;

public class Player {
    public int posX;
    public int posY;
    public int velocityY;
    public int velocityX;
    public int hovering = 0;
    public LinkedList<Cartas> viewing;


    public Player() {
        posX=43;
        posY=43;
        velocityY = 48*6;
        velocityX = 48*7;
        viewing = new LinkedList<>();
    }


}
