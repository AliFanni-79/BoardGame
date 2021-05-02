package Main;

import Menus.StartMenu;

import java.util.Arrays;

public class Main {
    public static int maximumSize = 20;
    public static int maximumLimit = -1;
    public static int[] tableSize = {0, 0};// column, row
    public static int[] redSlut = {-1, -1};
    public static String[] names = new String[2];
    public static int[] blueSlut = {-1, -1};
    public static boolean[][] isReserved = new boolean[maximumSize][maximumSize];
    public static void main(String[] args) {
        for (int i = 0; i < maximumSize; i++)
            Arrays.fill(isReserved[i], false);
        StartMenu.Start();
    }
}
