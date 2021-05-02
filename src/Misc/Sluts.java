package Misc;

import Main.Main;


public class Sluts {

    static final int FIRST_X = 5, FIRST_Y = 5, X = Main.tableSize[0], Y = Main.tableSize[1];
    static int[][][] sluts = new int[X][Y][2];

    public static void setSluts() {
        for (int i = 0; i < X; i++)
            for (int j = 0; j < Y; j++) {
                sluts[i][j][0] = FIRST_X + i * 30;
                sluts[i][j][1] = FIRST_Y + j * 30;
            }
    }

    public static int[] getSlut(int posX, int posY) {
        for (int i = 0; i < X; i++)
            for (int j = 0; j < Y; j++) {
                if (posX >= sluts[i][j][0] && posX < sluts[i][j][0] + 30
                        && posY >= sluts[i][j][1] && posY < sluts[i][j][1] + 30) {
                    return new int[]{i, j};
                }
            }
        return null;
    }
    public static int[] getSlutPosition(int SlutX, int SlutY) {
        return sluts[SlutX][SlutY];
    }
}
