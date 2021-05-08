package Menus;



import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class bluePage extends JFrame{

    private JTextField column;
    private JTextField row;
    private JButton changeBtn;
    private JPanel MainPanel;

    bluePage() {
        this.setContentPane(MainPanel);
        this.pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(210, 200));
        setLocationRelativeTo(null);
        changeBtn.addActionListener(e -> {
            if(column.getText().equals("") || row.getText().equals("")) {
                new JOptionPane("فرم را تکمیل کنید!").setVisible(true);
            }
            else
            {
                if (row.getText().matches("^\\d+$") && column.getText().matches("^\\d+$")) {
                    int intRow = Integer.parseInt(row.getText()) - 1;
                    int intColumn = Integer.parseInt(column.getText()) - 1;

                    if (intColumn <= Main.tableSize[0] && intRow <= Main.tableSize[1])
                        if (!Arrays.equals(Main.blueSlut, new int[]{intColumn, intRow})) {
                            if (!Main.isReserved[intColumn][intRow]) {
                                if (!Arrays.equals(Main.blueSlut, new int[]{-1, -1}))
                                    Main.isReserved[Main.blueSlut[0]][Main.blueSlut[1]] = false;
                                Main.blueSlut = new int[]{intColumn, intRow};
                                Main.isReserved[intColumn][intRow] = true;
                                dispose();
                            } else new JOptionPane("این خانه قبلا استفاده شده",
                                    JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                        } else dispose();
                    else new JOptionPane("سطر و ستون نمیتواند بیشتر از سایز صفحه باشد",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                } else
                        new JOptionPane( "در سطر و ستون از اعداد استفاده کنید",
                                JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
            }
        });
    }
}
