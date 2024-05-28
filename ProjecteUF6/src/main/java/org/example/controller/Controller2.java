package org.example.controller;

import org.example.model.entities.Psicotropic;
import org.example.model.exceptions.DAOException;
import org.example.model.impls.PsicotropicDAOJDBCOracleImpl;
import org.example.view.FinestraAnaleg;
import org.example.view.FinestraPsicotropic;
import org.example.view.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Controller2 implements PropertyChangeListener {

    public static final String PROP_EXCEPCIO = "excepcio";
    private DAOException excepcio;

    public DAOException getExcepcio() {
        return excepcio;
    }

    public void setExcepcio(DAOException excepcio) {
        DAOException oldExcepcio = this.excepcio;
        this.excepcio = excepcio;
        canvis.firePropertyChange(PROP_EXCEPCIO, oldExcepcio, excepcio);
    }

    PropertyChangeSupport canvis = new PropertyChangeSupport(this);


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        DAOException excepcio = (DAOException) evt.getNewValue();

        try {
            throw excepcio;
        } catch (DAOException e) {

            switch (evt.getPropertyName()) {

                case PROP_EXCEPCIO:

                    // switch (excepcio.getTipo()) {

                       // case 0, 1, 5, 11, 13 ,904, 936, 942, 1000, 1400, 1403, 1722, 1747, 4091, 6502, 12154, 2292, 1017:
                    JOptionPane.showMessageDialog(view2, excepcio.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }
        }

    }

    private Model modelAnaleg;
    private FinestraAnaleg view2;
    private FinestraPsicotropic finestraPrincipal;
    private PsicotropicDAOJDBCOracleImpl dadesAnalegs;
    private Long idPsicotropic;

    //Constructor de la classe Controller2 que rep com a paràmetres el model dels anàlegs, la finestra dels anàlegs, la finestra principal, les dades dels anàlegs i l'ID del psicotròpic

    public Controller2(Model modelAnaleg, FinestraAnaleg view2, FinestraPsicotropic finestraPrincipal, PsicotropicDAOJDBCOracleImpl dadesAnalegs, Long idPsicotropic) {

        this.modelAnaleg = modelAnaleg;
        this.view2 = view2;
        this.finestraPrincipal = finestraPrincipal;
        this.dadesAnalegs = dadesAnalegs;
        this.idPsicotropic = idPsicotropic;

        canvis.addPropertyChangeListener(this);

        lligaVistaModel2();


    }

    public void lligaVistaModel2() {

        //Carregem les dades dels anàlegs del psiocotròpic seleccionat a la taula

        try {

            List<Psicotropic.Analeg> dadesBase = dadesAnalegs.getAll(idPsicotropic);

            setModelAnalegs(this.modelAnaleg.getModelAnaleg(), dadesBase);

        } catch (DAOException e) {

            setExcepcio(new DAOException(e.getTipo()));
        }

        JTable taula2 = view2.getTable2();

        taula2.setModel(this.modelAnaleg.getModelAnaleg());

        afegirListeners2();

        //Amagem les columnes de l'ID de l'anàleg i de l'ID del psicotròpic

        taula2.getColumnModel().getColumn(0).setMinWidth(0);
        taula2.getColumnModel().getColumn(0).setMaxWidth(0);
        taula2.getColumnModel().getColumn(0).setWidth(0);

        taula2.getColumnModel().getColumn(3).setMinWidth(0);
        taula2.getColumnModel().getColumn(3).setMaxWidth(0);
        taula2.getColumnModel().getColumn(3).setWidth(0);
    }

    private void setModelAnalegs(DefaultTableModel modelAnaleg, List<Psicotropic.Analeg> dadesBase) {

        //modelAnaleg.setRowCount(0);

        for (Psicotropic.Analeg analeg : dadesBase) {

            modelAnaleg.addRow(new Object[]{analeg.getId(), analeg.getNumAnaleg(), analeg.getNom(), analeg.getIdPsicotropic()});
        }


    }

    private void afegirListeners2() {

        DefaultTableModel modelAnaleg = this.modelAnaleg.getModelAnaleg();

        JTable taula2 = view2.getTable2();

        JButton insertarButton2 = view2.getInsertarButton2();
        JButton modificarButton2 = view2.getModificarButton2();
        JButton borrarButton2 = view2.getBorrarButton2();
        JButton buscarButton = view2.getBuscarButton();

        JTextField numAnalegField = view2.getNumAnalegField();
        JTextField nomAnalegField = view2.getNomAnalegField();
        JTextField cadenaField = view2.getCadenaField();

        view2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                //Quan es tanqui la finestra dels anàlegs, tornem a habilitar la finestra principal

                finestraPrincipal.setEnabled(true);
            }
        });

        view2.getInsertarButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Definim les expressions regulars per comprovar que el nom de l'anàleg sigui correcte

                String psicotropicArtificial = "^(?:[A-Z0-9]+-)?[A-Z][A-Za-z0-9]*(?:-[A-Za-z0-9]+)*$";

                //El nom del psicotròpic artificial pot ser també un nom que contingue comes, apostrofs, i números
                String artificial2 = "\\b(?:[A-Z0-9][\\w'-]*)(?:\\s*[,-]?\\s*[A-Z0-9][\\w'-]*)*\\b";

                Pattern patro = Pattern.compile(psicotropicArtificial);
                Pattern patro2 = Pattern.compile(artificial2);


                try {

                    //Mirem que els camps no estiguin buits

                    if (numAnalegField.getText().isEmpty() || nomAnalegField.getText().isEmpty()) {

                        throw new DAOException(5);
                    }

                    //Mirem que el nom de l'anàleg sigui correcte amb una expressió regular

                    if (!patro2.matcher(nomAnalegField.getText()).matches() && !patro.matcher(nomAnalegField.getText()).matches()) {

                        throw new DAOException(15);
                    }

                    //mirem que l'ID de l'anàleg no sigui menor o igual a 0

                    if (Integer.parseInt(numAnalegField.getText()) <= 0) {

                        throw new DAOException(12);
                    }

                    //Mirem si el número de l'anàleg i el nom de l'anàleg ja existeixen

                    for (int i = 0; i < modelAnaleg.getRowCount(); i++) {

                        if (Integer.parseInt(numAnalegField.getText()) == (int) modelAnaleg.getValueAt(i, 1) && nomAnalegField.getText().equals(modelAnaleg.getValueAt(i, 2))) {

                            throw new DAOException(13);

                        }
                    }


                    //Obtenim el número de l'anàleg i el nom de l'anàleg i instanciem un nou objecte de la classe Analeg amb aquestes dades

                    int numAnaleg = Integer.parseInt(numAnalegField.getText());
                    String nomAnaleg = nomAnalegField.getText();
                    Long idPsicotropic = Controller2.this.idPsicotropic;

                    Psicotropic.Analeg analeg = new Psicotropic.Analeg(numAnaleg, nomAnaleg, idPsicotropic);

                    try {

                        //Guardem l'anàleg a la base de dades

                        dadesAnalegs.saveAnaleg(analeg);

                        //Obtenim l'ID de l'anàleg generat per la base de dades

                        Long id = dadesAnalegs.getIdAnaleg(analeg);

                        //Afegim l'anàleg a la taula

                        modelAnaleg.addRow(new Object[]{id, analeg.getNumAnaleg(), analeg.getNom(), analeg.getIdPsicotropic()});

                        numAnalegField.setText("");
                        nomAnalegField.setText("");

                    } catch (DAOException ex) {

                        setExcepcio(new DAOException(2));
                    }

                } catch (NumberFormatException ex) {

                    setExcepcio(new DAOException(11));

                } catch (DAOException ex) {

                    setExcepcio(new DAOException(ex.getTipo()));
                }
            }
        });

        view2.getBorrarButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int filaSeleccionada = taula2.getSelectedRow();

                if (filaSeleccionada != -1) {

                    //Obtenim l'ID de l'anàleg seleccionat

                    Long idAnaleg = (Long) modelAnaleg.getValueAt(filaSeleccionada, 0);

                    try {
                        //Eliminem l'anàleg de la base de dades

                        dadesAnalegs.deleteAnaleg(idAnaleg);

                        //Eliminem l'anàleg de la taula

                        modelAnaleg.removeRow(filaSeleccionada);

                        numAnalegField.setText("");
                        nomAnalegField.setText("");

                    } catch (DAOException ex) {

                        setExcepcio(new DAOException(3));
                    }

                } else {

                    JOptionPane.showMessageDialog(view2, "Selecciona una fila per borrar");
                }
            }
        });

        view2.getModificarButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String psicotropicArtificial = "^(?:[A-Z0-9]+-)?[A-Z][A-Za-z0-9]*(?:-[A-Za-z0-9]+)*$";

                //El nom del psicotròpic artificial pot ser també un nom que contingue comes, apostrofs, i números
                String artificial2 = "\\b(?:[A-Z0-9][\\w'-]*)(?:\\s*[,-]?\\s*[A-Z0-9][\\w'-]*)*\\b";

                Pattern patro = Pattern.compile(psicotropicArtificial);
                Pattern patro2 = Pattern.compile(artificial2);

                int filaSeleccionada = taula2.getSelectedRow();

                if (filaSeleccionada != -1) {

                    try {

                        //Mirem que els camps no estiguin buits

                        if (numAnalegField.getText().isEmpty() || nomAnalegField.getText().isEmpty()) {

                            throw new DAOException(5);
                        }

                        if (!patro2.matcher(nomAnalegField.getText()).matches() && !patro.matcher(nomAnalegField.getText()).matches()) {

                            throw new DAOException(15);
                        }

                        if (Integer.parseInt(numAnalegField.getText()) <= 0) {

                            throw new DAOException(12);
                        }

                        //Mirem si el número de l'anàleg i el nom de l'anàleg ja existeixen, excepte el de la fila seleccionada

                        for (int i = 0; i < modelAnaleg.getRowCount(); i++) {

                            if (Integer.parseInt(numAnalegField.getText()) == (int) modelAnaleg.getValueAt(i, 1) && nomAnalegField.getText().equals(modelAnaleg.getValueAt(i, 2)) && i != filaSeleccionada) {

                                throw new DAOException(13);

                            }
                        }


                        Long idAnaleg = (Long) modelAnaleg.getValueAt(filaSeleccionada, 0);
                        int numAnaleg = Integer.parseInt(numAnalegField.getText());
                        String nomAnaleg = nomAnalegField.getText();

                        Psicotropic.Analeg analeg = new Psicotropic.Analeg(idAnaleg, numAnaleg, nomAnaleg, idPsicotropic);

                        try {

                            //Modifiquem l'anàleg a la base de dades i a la taula

                            dadesAnalegs.modifyAnaleg(analeg);

                            modelAnaleg.setValueAt(numAnaleg, filaSeleccionada, 1);
                            modelAnaleg.setValueAt(nomAnaleg, filaSeleccionada, 2);

                        } catch (DAOException ex) {

                            setExcepcio(new DAOException(4));
                        }

                    } catch (DAOException ex) {

                        setExcepcio(new DAOException(ex.getTipo()));

                    } catch (NumberFormatException ex) {

                        setExcepcio(new DAOException(11));

                    } catch (Exception ex) {

                        setExcepcio(new DAOException(100));
                    }
                }
            }
        });

        view2.getTable2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int filaSeleccionada = taula2.getSelectedRow();

                if (filaSeleccionada != -1) {

                    int numAnaleg = (int) modelAnaleg.getValueAt(filaSeleccionada, 1);
                    String nomAnaleg = (String) modelAnaleg.getValueAt(filaSeleccionada, 2);

                    numAnalegField.setText(String.valueOf(numAnaleg));
                    nomAnalegField.setText(nomAnaleg);
                }
            }

        });

        view2.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {

                    //Mirem si el camp de la cadena a buscar està buit

                    if (cadenaField.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(view2, "Introdueix una cadena per buscar");

                    } else {

                        //Sinó, busquem la cadena a la base de dades

                        dadesAnalegs.findString(cadenaField.getText(), idPsicotropic, view2);
                    }

                } catch (DAOException ex) {

                    setExcepcio(new DAOException(ex.getTipo()));

                } catch (Exception ex) {

                    setExcepcio(new DAOException(100));
                }
            }
        });
    }
}
