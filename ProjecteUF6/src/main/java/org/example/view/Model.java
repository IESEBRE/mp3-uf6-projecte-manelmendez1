package org.example.view;

import org.example.model.entities.Psicotropic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Model {

    private DefaultTableModel modelPsicotropic;
    private TableColumn column;
    private DefaultTableModel modelAnaleg;
    private ComboBoxModel<Psicotropic.Tipo> modelTipo;


    public DefaultTableModel getModelPsicotropic() {
        return modelPsicotropic;
    }

    public DefaultTableModel getModelAnaleg() {
        return modelAnaleg;
    }

    public ComboBoxModel<Psicotropic.Tipo> getModelTipo() {
        return modelTipo;
    }


    public Model() {

        modelPsicotropic = new DefaultTableModel(new Object[]{"ID", "Nom", "Classificació", "Vida mitja", "Legal", "Tipus"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class getColumnClass(int column) {

                switch (column) {
                    case 0:
                        return Long.class;
                    case 1, 5:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Double.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return Object.class;
                }

            }
        };


        modelAnaleg = new DefaultTableModel(new Object[]{"ID", "Número Analeg", "Nom", "ID Psicotròpic"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class getColumnClass(int column) {

                switch (column) {
                    case 0, 3:
                        return Long.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return String.class;
                    default:
                        return Object.class;
                }
            }
        };

        modelTipo = new DefaultComboBoxModel<>(Psicotropic.Tipo.values());

    }
}
