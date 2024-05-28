package org.example.app;

import org.example.controller.Controller;
import org.example.model.impls.PsicotropicDAOJDBCOracleImpl;
import org.example.view.FinestraPsicotropic;

import javax.swing.*;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Locale.setDefault(new Locale("ca", "ES"));
                new Controller(new PsicotropicDAOJDBCOracleImpl(), new FinestraPsicotropic());

            }
        });
    }
}
