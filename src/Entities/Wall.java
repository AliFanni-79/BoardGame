package Entities;

import Misc.EntityIcon;

import java.awt.*;

public class Wall extends Entities {
    public Wall(Container c, int row, int column) {
        super(c, EntityIcon.wallIcon, row, column);
    }
}
