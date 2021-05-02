package Entities;

import Main.Main;
import Misc.EntityIcon;

import java.awt.*;
import java.util.Random;

public class SpeedLimit extends Entities {

    private int limit;

    public SpeedLimit(Container c, int row, int column) {
        super(c, EntityIcon.limitIcon, row, column);
        setLimit();
    }

    public SpeedLimit(Container c, int row, int column, int amount) {
        super(c, EntityIcon.limitIcon, row, column);
        limit = amount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit() {
        Random random = new Random();
        limit = random.nextInt(Main.maximumLimit);
    }
}
