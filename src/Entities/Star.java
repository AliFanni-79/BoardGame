package Entities;

import Misc.EntityIcon;

import java.awt.*;

public class Star extends Entities {
    public static int count = 0;
    public Star(Container c, int row, int column) {
        super(c, EntityIcon.starIcon, row, column);
    }
}
