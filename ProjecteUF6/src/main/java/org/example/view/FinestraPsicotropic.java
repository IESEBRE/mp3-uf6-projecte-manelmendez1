package org.example.view;


import org.example.model.entities.Psicotropic;

import javax.swing.*;

public class FinestraPsicotropic extends JFrame {

    private JPanel panel1;
    private JButton insertarButton1;
    private JButton modificarButton1;
    private JButton borrarButton1;
    private JButton analegsButton1;
    private JTextField nomField;
    private JTextField classificacioField;
    private JTextField vidaMitjaField;
    private JCheckBox esLegalCheckBox;
    private JTable table1;
    private JComboBox comboBoxTipus;


    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getInsertarButton1() {
        return insertarButton1;
    }

    public JButton getModificarButton1() {
        return modificarButton1;
    }

    public JButton getBorrarButton1() {
        return borrarButton1;
    }

    public JButton getAnalegsButton1() {
        return analegsButton1;
    }

    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getClassificacioField() {
        return classificacioField;
    }

    public JTextField getVidaMitjaField() {
        return vidaMitjaField;
    }

    public JCheckBox getEsLegalCheckBox() {
        return esLegalCheckBox;
    }

    public JTable getTable1() {
        return table1;
    }


    public JComboBox getComboBoxTipus() {
        return comboBoxTipus;
    }


    public FinestraPsicotropic() {

        this.setTitle("Psicotròpics");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        initCustomComponents();

    }

    private void initCustomComponents() {

        for (Psicotropic.Tipo tipo : Psicotropic.Tipo.values()) {
            comboBoxTipus.addItem(tipo);
        }
    }
}
