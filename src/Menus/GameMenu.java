package Menus;


import Entities.*;
import Main.Main;
import Misc.BoardBuilder;
import Misc.EntityIcon;
import Misc.Sluts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMenu extends JFrame {
    int turn = 0;
    String[] directions = {"بالا","راست","پایین","چپ"};
    String turnText = "نوبت ";
    String redText = "بازیکن قرمز: ";
    String blueText = "بازیکن آبی: ";
    String moves = " واحد حرکت ";
    String toDirection = " به سمت ";
    int redScore = 0;
    int blueScore = 0;
    JLabel redPlayer = new JLabel(redText + redScore);
    JLabel bluePlayer = new JLabel(blueText + blueScore);
    JLabel currentPlayer = new JLabel(turnText + redText);
    JLabel nextMove = new JLabel();
    ArrayList<Integer> count = new ArrayList<>();
    JComboBox<Object> countBox;
    JComboBox<String> directorBox = new JComboBox<>(directions);
    SpringLayout layout = new SpringLayout();
    JPanel controlPanel = new JPanel(layout);
    JButton okButton = new JButton("انجام");

    GameMenu(StartMenu ancestorMenu) {
        Entities.entities.clear();
        if (Main.maximumLimit <= 0) Main.maximumLimit = 2;
        this.setSize(Main.tableSize[0]*30 + 26, Main.tableSize[1]*30 + 155);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel waitLbl = new JLabel("Wait...");
        waitLbl.setBounds(10, 10, 50, 50);
        add(waitLbl);
        repaint();
        addControlPanel();
        EntityIcon.LoadIcons();
        if (Arrays.equals(Main.redSlut, new int[]{-1, -1})) {
            Main.redSlut = new int[]{0, 0};
        }
        if (Arrays.equals(Main.blueSlut, new int[]{-1, -1})) {
            Main.blueSlut = new int[]{Main.tableSize[0] - 1, 0};
        }
        if (ancestorMenu.resultPane.getText().length() < 7) {
            BoardBuilder.build(this, "rand");
        }
        else
            BoardBuilder.build(this, ancestorMenu.resultPane.getText());
        remove(waitLbl);
        repaint();
        listeners();
    }

    private void listeners() {
        for (int i = 0; i < Main.tableSize[0]; i++)
            for (int j = 0; j < Main.tableSize[1]; j++) {
                int finalI = i;
                int finalJ = j;
                BoardBuilder.cells[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {movePlayer(finalI, finalJ);}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
    }

    @SuppressWarnings("DuplicatedCode")
    private void movePlayer(int finalI, int finalJ) {
        Pawn player = findPlayerByTurn();
        assert player != null;

        if (finalI == player.getColumn()) {

            if (finalJ == player.getRow()) return;

            if (!player.limited.isEmpty()) {
                int distance = Math.abs(player.getRow() - finalJ);
                int limit = player.limited.get(player.limited.size() - 1);
                if (distance <= limit) player.limited.remove(player.limited.size() - 1);

                else {
                    new JOptionPane( "مقدار محدود حرکت رعایت نشده: " + limit + " حرکت",
                            JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
                    return;
                }
            }
            for (int i = player.getRow(); ; ) {
                if (player.getRow() < finalJ) {
                    i++;
                    if (i > finalJ) break;
                }
                else if (player.getRow() > finalJ) {
                    i--;
                    if (i < finalJ) break;
                }
                if (Main.isReserved[finalI][i]) {

                    for (int j = 0; j < Entities.entities.size(); j++) {
                        Entities temp = Entities.entities.get(j);
                        if (temp.getColumn() == finalI && temp.getRow() == i) {

                            if (temp.getClass() == Wall.class) {
                                new JOptionPane("در مسیر دیوار قرار دارد",
                                        JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
                                return;

                            } else if (temp.getClass() == SpeedLimit.class) {
                                player.limited.add(((SpeedLimit) temp).getLimit());
                                temp.removeEntity();

                            } else if (temp.getClass() == Star.class) {
                                player.setScore(player.getScore() + 1);
                                Star.count--;
                                temp.removeEntity();
                            }
                        }
                    }
                }
            }
            Main.isReserved[player.getColumn()][player.getRow()] = false;
            Main.isReserved[finalI][finalJ] = true;
            Entities.entities.remove(player);
            player.setRow(finalJ);
            int[] position = Sluts.getSlutPosition(finalI, finalJ);
            player.setBounds(position[0] + 1, position[1] + 1, 28, 28);
            Entities.entities.add(player);

        } else if (player.getRow() == finalJ) {

            if (finalI == player.getColumn()) return;

            if (!player.limited.isEmpty()) {

                int distance = Math.abs(player.getColumn() - finalI);
                int limit = player.limited.get(player.limited.size() - 1);

                if (distance <= limit) {
                    player.limited.remove(player.limited.size() - 1);
                }
                else {
                    new JOptionPane( "مقدار محدود حرکت رعایت نشده: " + limit + " حرکت",
                            JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
                    return;
                }
            }
            for (int i = player.getColumn();;) {
                if (player.getColumn() < finalI) {
                    i++;
                    if (i > finalI) break;
                } else {
                    i--;
                    if (i < finalI) break;
                }
                if (Main.isReserved[i][finalJ]) {
                    for (int j = 0; j < Entities.entities.size(); j++) {
                        Entities temp = Entities.entities.get(j);
                        if (temp.getRow() == finalJ && temp.getColumn() == i) {
                            if (temp.getClass() == Wall.class) {
                                new JOptionPane("در مسیر دیوار قرار دارد",
                                        JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
                                return;
                            } else if (temp.getClass() == SpeedLimit.class) {
                                player.limited.add(((SpeedLimit) temp).getLimit());
                                temp.removeEntity();
                            } else if (temp.getClass() == Star.class) {
                                player.setScore(player.getScore() + 1);
                                Star.count--;
                                temp.removeEntity();
                            }
                        }
                    }
                }
            }
            Main.isReserved[player.getColumn()][player.getRow()] = false;
            Main.isReserved[finalI][finalJ] = true;
            Entities.entities.remove(player);
            player.setColumn(finalI);
            int[] position = Sluts.getSlutPosition(finalI, finalJ);
            player.setBounds(position[0] + 1, position[1] + 1, 28, 28);
            Entities.entities.add(player);
        }
        if (player.getRow() != finalJ || player.getColumn() != finalI) {
            new JOptionPane( "حرکت به صورت مورب مجاز نیست",
                    JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
            return;
        }
        turn = turn == 0? 1:0;
        updateControlPanel(player);
    }

    private void updateControlPanel(Pawn player) {
        if (player.getPlayerColor().equals("red")) redScore = player.getScore();
        else blueScore = player.getScore();
        redPlayer.setText(redText + redScore);
        bluePlayer.setText(blueText + blueScore);
        if (turn == 0) currentPlayer.setText(turnText + redText);
        else currentPlayer.setText(turnText + blueText);
        if (Star.count == 0) endGame();
    }

    private void endGame() {
        if (redScore > blueScore) {
            new JOptionPane( String.format("بازیکن قرمز %s با %d امتیاز برنده شد", Main.names[0], redScore),
                    JOptionPane.ERROR_MESSAGE).createDialog("پایان بازی").setVisible(true);
        } else if (redScore < blueScore) {
            new JOptionPane( String.format("بازیکن آبی %s با %d امتیاز برنده شد", Main.names[1], blueScore),
                    JOptionPane.ERROR_MESSAGE).createDialog("پایان بازی").setVisible(true);
        } else {
            new JOptionPane( "بازی مساوی شد!",
                    JOptionPane.ERROR_MESSAGE).createDialog("پایان بازی").setVisible(true);
        }
        for (int i = 0; i < Main.tableSize[0]; i++)
            for (int j = 0; j < Main.tableSize[1]; j++) {
                BoardBuilder.cells[i][j].removeMouseListener(BoardBuilder.cells[i][j].getMouseListeners()[0]);
            }
        okButton.setEnabled(false);
    }

    private void addControlPanel() {
        for (int i = 1; i < Main.maximumSize; i++) {
            count.add(i);
        }
        countBox = new JComboBox<>(count.toArray());

        controlPanel.setPreferredSize(new Dimension(Main.tableSize[0]*30 + 26, 100));
        controlPanel.setBounds(5, Main.tableSize[1]*30 + 10,Main.tableSize[0]*30, 100);
        controlPanel.setBackground(Color.WHITE);
        add(controlPanel);

        layout.putConstraint(SpringLayout.EAST, redPlayer, -5, SpringLayout.EAST, controlPanel);
        layout.putConstraint(SpringLayout.NORTH, redPlayer, 5, SpringLayout.NORTH, controlPanel);
        layout.putConstraint(SpringLayout.EAST, bluePlayer, -15, SpringLayout.WEST, redPlayer);
        layout.putConstraint(SpringLayout.NORTH, bluePlayer, 5, SpringLayout.NORTH, controlPanel);

        controlPanel.add(redPlayer);
        controlPanel.add(bluePlayer);

        layout.putConstraint(SpringLayout.EAST, currentPlayer, -5, SpringLayout.EAST, controlPanel);
        layout.putConstraint(SpringLayout.NORTH, currentPlayer, 10, SpringLayout.SOUTH, redPlayer);
        layout.putConstraint(SpringLayout.EAST, countBox, -5, SpringLayout.EAST, controlPanel);
        layout.putConstraint(SpringLayout.NORTH, countBox, 5, SpringLayout.SOUTH, currentPlayer);

        controlPanel.add(currentPlayer);
        controlPanel.add(countBox);

        layout.putConstraint(SpringLayout.EAST, directorBox, -10, SpringLayout.WEST, countBox);
        layout.putConstraint(SpringLayout.NORTH, directorBox, 5, SpringLayout.SOUTH, currentPlayer);
        layout.putConstraint(SpringLayout.EAST, nextMove, -5, SpringLayout.EAST, controlPanel);
        layout.putConstraint(SpringLayout.NORTH, nextMove, 5, SpringLayout.SOUTH, directorBox);

        controlPanel.add(directorBox);
        controlPanel.add(nextMove);
        showMove();
        directorBox.addItemListener(e -> showMove());
        countBox.addItemListener(e -> showMove());

        layout.putConstraint(SpringLayout.WEST, okButton, 5, SpringLayout.WEST, controlPanel);
        layout.putConstraint(SpringLayout.NORTH, okButton, -5, SpringLayout.NORTH, currentPlayer);

        controlPanel.add(okButton);

        okButton.addActionListener(e -> {
            int multiplier = 1;
            boolean vertical = false;
            switch (directorBox.getSelectedIndex()) {
                case 0 -> {
                    multiplier = -1;
                    vertical = true;
                }
                case 2 -> vertical = true;
                case 3 -> multiplier = -1;
            }
            Pawn player = findPlayerByTurn();
            int finalI, finalJ;
            if (vertical) {
                finalI = player.getColumn();
                finalJ = player.getRow() + (multiplier * Integer.parseInt(countBox.getSelectedItem().toString()));
                if (finalJ > Main.tableSize[1]) {
                    new JOptionPane( "حرکت خارج از صفحه ی بازی است",
                            JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
                } else {
                    movePlayer(finalI, finalJ);
                }
            } else {
                finalI = player.getColumn() + (multiplier * Integer.parseInt(countBox.getSelectedItem().toString()));
                finalJ = player.getRow();
                if (finalI > Main.tableSize[0]) {
                    new JOptionPane( "حرکت خارج از صفحه ی بازی است",
                            JOptionPane.ERROR_MESSAGE).createDialog("حرکت نامجاز").setVisible(true);
                } else {
                    movePlayer(finalI, finalJ);
                }
            }
        });
    }

    private Pawn findPlayerByTurn() {
        Pawn player = null;
        for (int i = 0; i < Entities.entities.size(); i++) {
            if (Entities.entities.get(i).getClass() == Pawn.class) {
                if (turn == 0) {
                    if (((Pawn) Entities.entities.get(i)).getPlayerColor().equals("red")) {
                        player = ((Pawn) Entities.entities.get(i));
                        break;
                    }
                } else {
                    if (((Pawn) Entities.entities.get(i)).getPlayerColor().equals("blue")) {
                        player = ((Pawn) Entities.entities.get(i));
                        break;
                    }
                }
            }
        }
        return player;
    }

    private void showMove() {
        nextMove.setText(countBox.getSelectedItem() + moves + toDirection + directorBox.getSelectedItem());

    }
}
