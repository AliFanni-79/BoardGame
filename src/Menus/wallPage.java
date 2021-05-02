package Menus;



import Main.Main;

import javax.swing.*;
import java.awt.*;


public class wallPage extends JFrame{

    private JTextField column;
    private JTextField row;
    private JButton addBtn;
    private JPanel MainPanel;

    wallPage(StartMenu ancestorMenu) {
        this.setContentPane(MainPanel);
        this.pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(210, 200));
        setLocationRelativeTo(null);
        addBtn.addActionListener(e -> {
            if(column.getText().equals("") || row.getText().equals("")) {
                new JOptionPane("فرم را تکمیل کنید!").setVisible(true);
            }
            else
            {
                if (row.getText().matches("^\\d+$") && column.getText().matches("^\\d+$")) {
                    int intRow = Integer.parseInt(row.getText());
                    int intColumn = Integer.parseInt(column.getText());

                    if (intColumn <= Main.tableSize[0] && intRow <= Main.tableSize[1])
                        if (Main.isReserved[intColumn][intRow]) {
                            ancestorMenu.addToResult("wall," + intColumn + "," + intRow);
                            dispose();
                        } else new JOptionPane("این خانه قبلا استفاده شده",
                                JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                    else new JOptionPane("سطر و ستون نمیتواند بیشتر از سایز صفحه باشد",
                            JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                } else new JOptionPane( "در سطر و ستون از اعداد استفاده کنید",
                        JOptionPane.ERROR_MESSAGE).createDialog("اخطار").setVisible(true);
                ancestorMenu.addToResult("wall," + row.getText() + "," + column.getText());

            }
        });
    }
}
