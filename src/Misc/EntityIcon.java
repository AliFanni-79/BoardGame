package Misc;

import javax.swing.*;

public class EntityIcon {
    public static ImageIcon cellIcon;
    public static ImageIcon redIcon;
    public static ImageIcon blueIcon;
    public static ImageIcon limitIcon;
    public static ImageIcon starIcon;
    public static ImageIcon wallIcon;
    public static void LoadIcons() {
        cellIcon = new ImageIcon("icons/cell.png");
        redIcon = new ImageIcon("icons/red.png");
        blueIcon = new ImageIcon("icons/blue.png");
        limitIcon = new ImageIcon("icons/limit.png");
        starIcon = new ImageIcon("icons/star.png");
        wallIcon = new ImageIcon("icons/wall.png");
    }
}
