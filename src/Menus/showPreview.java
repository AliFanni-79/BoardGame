package Menus;



import Misc.BoardBuilder;
import Main.Main;
import Misc.EntityIcon;

import javax.swing.*;


public class showPreview extends JFrame {

    showPreview(StartMenu ancestorMenu) {
        if (Main.maximumLimit < 0) Main.maximumLimit = 1;
        this.setSize(Main.tableSize[0]*30 + 26, Main.tableSize[1]*30 + 50);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel waitLbl = new JLabel("Wait...");
        waitLbl.setBounds(10, 10, 50, 50);
        add(waitLbl);
        repaint();
        EntityIcon.LoadIcons();
        BoardBuilder.build(this, ancestorMenu.resultPane.getText());
        remove(waitLbl);
        repaint();
    }
}
