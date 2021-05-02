package Entities;

import Misc.EntityIcon;

import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Entities {
    private final String playerName;
    private final String playerColor;
    public ArrayList<Integer> limited = new ArrayList<>();
    private int score = 0;
    public Pawn(Container c, int row, int column, String playerColor, String playerName) {
        super(  c,
                playerColor.equals("red")?
                EntityIcon.redIcon:
                EntityIcon.blueIcon
                ,
                row,
                column);
        this.playerColor = playerColor;
        this.playerName = playerName;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
