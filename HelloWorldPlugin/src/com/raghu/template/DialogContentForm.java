package com.raghu.template;

import javax.swing.*;

/**
 * Created by raghavendraguru on 6/20/17.
 */
public class DialogContentForm {
    private JPanel MainPanel;
    private JLabel Name;
    private JTextField textField1;
    private JLabel lblPath;
    private JTextField textField2;
    private JButton btnShowTree;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JLabel getName() {
        return Name;
    }

    public void setName(JLabel name) {
        Name = name;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }
}
