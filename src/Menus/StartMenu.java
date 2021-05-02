package Menus;

import Main.Main;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("DuplicatedCode")
public class StartMenu extends JFrame {

    //Generating Form Components
    private JPanel MainPanel;
    public JTextArea resultPane;
    private JTextField column;
    private JTextField row;
    private JButton confirmBtn;
    private JButton limitAdd;
    private JButton starAdd;
    private JButton wallAdd;
    private JButton showBtn;
    private JTextField player1;
    private JTextField player2;
    private JButton redPosBtn;
    private JButton bluePosBtn;
    private JTextField maximumLimit;
    private JPanel p1;
    private JButton startBtn;

    public StartMenu() {
        this.setContentPane(MainPanel);
        this.pack();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("شروع بازی");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(410, 200));
        setLocationRelativeTo(null);

        confirmBtn.addActionListener(e -> {
            if (player1.getText().equals("") && column.getText().equals("") && player2.getText().equals("")
                    && row.getText().equals("") && maximumLimit.getText().equals("")) {

                new JOptionPane("تمامی خانه ها را پر کنید", JOptionPane.ERROR_MESSAGE)
                        .createDialog("اخطار").setVisible(true);

            } else {
                Main.names[0] = player1.getText();
                Main.names[1] = player2.getText();
                if (row.getText().matches("^\\d+$") && column.getText().matches("^\\d+$")
                        && maximumLimit.getText().matches("^\\d+$")) {
                    int intRow = Integer.parseInt(row.getText());
                    int intColumn = Integer.parseInt(column.getText());

                    if (intRow <= Main.maximumSize && intColumn <= Main.maximumSize) {
                        if (intRow > 4 && intColumn > 4) {
                            Main.tableSize = new int[]{intColumn, intRow};
                            Main.maximumLimit = Integer.parseInt(maximumLimit.getText());
                            p1.setVisible(true);
                            startBtn.setVisible(true);
                            this.pack();
                        } else new JOptionPane( "مقدار سطر و ستون باید بیشتر از پنج باشد",
                                JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                    } else new JOptionPane( "سطر و ستون نمیتواند بیشتر از " + Main.maximumSize + " باشد.",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                } else new JOptionPane( "در سطر و ستون و حداکثر سرعتگیر از اعداد استفاده کنید",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
            }
        });

        showBtn.addActionListener(e -> new showPreview(this).setVisible(true));
        wallAdd.addActionListener(e -> new wallPage(this).setVisible(true));
        limitAdd.addActionListener(e -> new limitPage(this).setVisible(true));
        starAdd.addActionListener(e -> new starPage(this).setVisible(true));
        redPosBtn.addActionListener(e -> new redPage().setVisible(true));
        bluePosBtn.addActionListener(e -> new bluePage().setVisible(true));

        startBtn.addActionListener(e -> {
            new GameMenu(this).setVisible(true);
            dispose();
        });
    }
    public static void Start() {
        new StartMenu().setVisible(true);
    }

    public void addToResult(String text) {
        resultPane.setText(resultPane.getText() + (resultPane.getText().equals("")?"":"\n") + text);
    }
}
