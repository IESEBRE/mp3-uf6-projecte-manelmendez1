package org.example.view;

import javax.swing.*;

public class FinestraAnaleg extends JFrame {
    
    private JPanel panel1;
    private JButton insertarButton2;
    private JButton modificarButton2;
    private JButton borrarButton2;
    private JTextField numAnalegField;
    private JTextField nomAnalegField;
    private JTable table2;
    private JTextField cadenaField;
    private JButton buscarButton;


    public JPanel getPanel1() {
        return panel1;
    }

    public JButton getInsertarButton2() {
        return insertarButton2;
    }

    public JButton getModificarButton2() {
        return modificarButton2;
    }

    public JButton getBorrarButton2() {
        return borrarButton2;
    }

    public JTextField getNumAnalegField() {
        return numAnalegField;
    }

    public JTextField getNomAnalegField() {
        return nomAnalegField;
    }

    public JTable getTable2() {
        return table2;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTextField getCadenaField() {
        return cadenaField;
    }


    public FinestraAnaleg() {

        this.setTitle("Anàlegs");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }


}
