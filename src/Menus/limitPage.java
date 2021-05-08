package Menus;

import Main.Main;

import javax.swing.*;
import java.awt.*;


public class limitPage extends JFrame{

    private JTextField column;
    private JTextField row;
    private JTextField amount;
    private JButton salaryAdd;
    private JPanel MainPanel;

    limitPage(StartMenu ancestorMenu) {
        this.setContentPane(MainPanel);
        this.pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(210, 200));
        setLocationRelativeTo(null);
        salaryAdd.addActionListener(e -> {
            if(amount.getText().equals("") || column.getText().equals("") || row.getText().equals("")) {
                new JOptionPane("فرم را تکمیل کنید!").setVisible(true);
            }
            else
            {
                if (row.getText().matches("^\\d+$") && column.getText().matches("^\\d+$") &&
                        amount.getText().matches("^\\d+$")) {
                    int intRow = Integer.parseInt(row.getText());
                    int intColumn = Integer.parseInt(column.getText());

                    if (intColumn <= Main.tableSize[0] && intRow <= Main.tableSize[1])
                        if (!Main.isReserved[intColumn - 1][intRow - 1]) {
                            ancestorMenu.addToResult("limit," + intColumn + "," + intRow + "," + amount.getText());
                            Main.isReserved[intColumn - 1][intRow - 1] = true;
                            dispose();
                        } else new JOptionPane("این خانه قبلا استفاده شده",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                    else new JOptionPane("سطر و ستون نمیتواند بیشتر از سایز صفحه باشد",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                } else new JOptionPane( "در سطر و ستون و مقدار سرعتگیر از اعداد استفاده کنید",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
            }
        });
    }
}
