package Misc;

import Entities.*;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class BoardBuilder {
    public static JLabel[][] cells = new JLabel[Main.tableSize[0]][Main.tableSize[1]];
    public static void build(Container c, String input) {
        Sluts.setSluts();
        Star.count = 0;
        createEntities(c, input);
        formCells(c);
    }
    @SuppressWarnings("ConstantConditions")
    private static void createEntities(Container c, String input) {
        if (!Arrays.equals(Main.redSlut, new int[]{-1, -1})) {
            Pawn red = new Pawn(c, Main.redSlut[1], Main.redSlut[0], "red", Main.names[0]);
            int[] position = Sluts.getSlutPosition(red.getColumn(), red.getRow());
            red.setBounds(position[0] + 1, position[1] + 1, 28, 28);
            Entities.entities.add(red);
            Main.isReserved[Main.redSlut[0]][Main.redSlut[1]] = true;
        }
        if (!Arrays.equals(Main.blueSlut, new int[]{-1, -1})) {
            Pawn blue = new Pawn(c, Main.blueSlut[1], Main.blueSlut[0], "blue", Main.names[1]);
            int[] position = Sluts.getSlutPosition(blue.getColumn(), blue.getRow());
            blue.setBounds(position[0] + 1, position[1] + 1, 28, 28);
            Entities.entities.add(blue);
            Main.isReserved[Main.blueSlut[0]][Main.blueSlut[1]] = true;
        }
        if(input.length() > 7) {
            while (word(input) != null) {
                switch (Objects.requireNonNull(word(input))) {
                    case "wall" -> {
                        input = jumpToNext(input);
                        int column = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        int row = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        Wall wall = new Wall(c, row, column);
                        Entities.entities.add(wall);
                        int[] position = Sluts.getSlutPosition(column, row);
                        wall.setBounds(position[0] + 1, position[1] + 1, 28, 28);
                        c.repaint();
                    }
                    case "limit" -> {
                        input = jumpToNext(input);
                        int column = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        int row = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        int amount = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        SpeedLimit limit = new SpeedLimit(c, row, column, amount);
                        Entities.entities.add(limit);
                        int[] position = Sluts.getSlutPosition(column, row);
                        limit.setBounds(position[0] + 1, position[1] + 1, 28, 28);
                        c.repaint();
                    }
                    case "star" -> {
                        input = jumpToNext(input);
                        int column = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        int row = Integer.parseInt(word(input)) - 1;
                        input = jumpToNext(input);
                        Star star = new Star(c, row, column);
                        Entities.entities.add(star);
                        int[] position = Sluts.getSlutPosition(column, row);
                        star.setBounds(position[0] + 1, position[1] + 1, 28, 28);
                        Star.count++;
                        c.repaint();
                    }
                }
            }
        } else if (input.equals("rand")) randomEntities(c);
    }

    private static void randomEntities(Container c) {
        Random random = new Random();

        int count = random.nextInt(((Main.tableSize[0]*Main.tableSize[1])/3) + 5);
        for (int i = 0; i < count; i++) {
            int column, row;
            do {
                column = random.nextInt(Main.tableSize[0]);
                row = random.nextInt(Main.tableSize[1]);
            } while (Main.isReserved[column][row]);
            switch (random.nextInt(3)) {
                case 0 -> {
                    Wall temp = new Wall(c, row, column);
                    Entities.entities.add(temp);
                    int[] position = Sluts.getSlutPosition(column, row);
                    temp.setBounds(position[0] + 1, position[1] + 1, 28, 28);
                }
                case 1 -> {
                    SpeedLimit temp = new SpeedLimit(c, row, column);
                    Entities.entities.add(temp);
                    int[] position = Sluts.getSlutPosition(column, row);
                    temp.setBounds(position[0] + 1, position[1] + 1, 28, 28);
                }
                case 2 -> {
                    Star temp = new Star(c, row, column);
                    Star.count++;
                    Entities.entities.add(temp);
                    int[] position = Sluts.getSlutPosition(column, row);
                    temp.setBounds(position[0] + 1, position[1] + 1, 28, 28);
                }
            }
            Main.isReserved[column][row] = true;
        }
    }

    private static void formCells(Container c) {
        for (int i = 0; i < Main.tableSize[0]; i++)
            for (int j = 0; j < Main.tableSize[1]; j++) {
                JLabel cell = new JLabel();
                cell.setIcon(EntityIcon.cellIcon);
                c.add(cell);
                cells[i][j] = cell;
                int[] slutPosition = Sluts.getSlutPosition(i, j);
                cell.setBounds(slutPosition[0], slutPosition[1], 30, 30);
            }
    }

    private static String word(String input) {
        if (input != null) {
            if (input.indexOf('\n') != -1) {
                if (lineFinished(input)) {
                    return input.substring(0, input.indexOf('\n'));
                } else
                    return input.substring(0, input.indexOf(','));
            } else {
                if (input.indexOf(',') != -1)
                    return input.substring(0, input.indexOf(','));
                else return input;
            }
        }
        else return null;
    }

    private static String jumpToNext(String input) {
        if (input.indexOf('\n') != -1)
            if (lineFinished(input)) {
                return input.substring(input.indexOf('\n') + 1);
            }
            else return input.substring(input.indexOf(',') + 1);
        else {
            if (input.indexOf(',') != -1)
                return input.substring(input.indexOf(',') + 1);
            else
                return null;
        }
    }

    private static boolean lineFinished(String input) {
        return input.indexOf(',') > input.indexOf('\n');
    }
}
