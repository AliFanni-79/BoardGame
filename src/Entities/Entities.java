package Entities;

import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Entities extends JLabel {

    public static ArrayList<Entities> entities = new ArrayList<>();

    private final ImageIcon icon;
    private int row;
    private int column;

    public Entities(Container c, ImageIcon icon, int row, int column) {
        this.icon = icon;
        this.row = row;
        this.column = column;
        c.add(this);
    }

    public void removeEntity() {
        Main.isReserved[getColumn()][getRow()] = false;
        this.getTopLevelAncestor().remove(this);
        entities.remove(this);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
