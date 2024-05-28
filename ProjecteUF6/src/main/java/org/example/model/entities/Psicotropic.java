package org.example.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Psicotropic implements Serializable, Comparable<Psicotropic> {

    private Long id;

    private String nom;

    private int classificacio;

    private double vidaMitja;

    private boolean legal;

    private Collection<Analeg> analegs;


    public enum Tipo {

        PS1("Depressor"), PS2("Estimulant"), PS3("Psicodèlic"), PS4("Dissociatiu"), PS5("Empatògen"), PS6("Opioid"), PS7("Cannabinoid");

        private String nom;

        Tipo(String nom) {

            this.nom = nom;

        }

        @Override
        public String toString() {
            return nom;
        }


    }

    private String classePsicotropic;




    public Psicotropic(Long id, String nom, int classificacio, double vidaMitja, boolean legal, String classePsicotropic) {
        this.id = id;
        this.nom = nom;
        this.classificacio = classificacio;
        this.vidaMitja = vidaMitja;
        this.legal = legal;
        this.classePsicotropic = classePsicotropic;
    }

    public Psicotropic(String nom, int classificacio, double vidaMitja, boolean legal, String classePsicotropic) {
        this.nom = nom;
        this.classificacio = classificacio;
        this.vidaMitja = vidaMitja;
        this.legal = legal;
        this.classePsicotropic = classePsicotropic;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getClassificacio() {
        return classificacio;
    }


    public void setClassificacio(int classificacio) {
        this.classificacio = classificacio;
    }


    public double getVidaMitja() {
        return vidaMitja;
    }


    public void setVidaMitja(double vidaMitja) {
        this.vidaMitja = vidaMitja;
    }


    public boolean isLegal() {
        return legal;
    }


    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public Long getId() {
        return id;
    }


    public Collection<Analeg> getAnalegs() {
        return analegs;
    }

    public void setAnalegs(Collection<Analeg> analegs) {
        this.analegs = analegs;
    }

    public String getTipus() {
        return classePsicotropic;
    }

    @Override
    public int compareTo(Psicotropic o) {
        return this.nom.compareTo(o.nom);
    }


    public static class Analeg implements Comparable<Analeg>, Serializable {

        private Long id;
        private int numAnaleg;
        private String nom;
        private Long idPsicotropic;

        public Analeg(Long id, int numAnaleg, String nom, Long idPsicotropic) {
            this.id = id;
            this.numAnaleg = numAnaleg;
            this.nom = nom;
            this.idPsicotropic = idPsicotropic;


        }

        public Analeg(int numAnaleg, String nom, Long idPsicotropic) {

            this.numAnaleg = numAnaleg;
            this.nom = nom;
            this.idPsicotropic = idPsicotropic;
        }

        public int getNumAnaleg() {
            return numAnaleg;
        }

        public void setNumAnaleg(int numAnaleg) {
            this.numAnaleg = numAnaleg;
        }

        public String getNomAnaleg() {
            return nom;
        }

        public void setNomAnaleg(String nom) {
            this.nom = nom;
        }

        public Long getIdPsicotropic() {
            return idPsicotropic;
        }

        public Long getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }



        @Override
        public int compareTo(Analeg o) {
            return Integer.compare(this.numAnaleg, o.numAnaleg);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Analeg)) return false;
            Analeg analeg = (Analeg) o;
            return numAnaleg == analeg.numAnaleg &&
                    Objects.equals(nom, analeg.nom);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Psicotropic)) return false;
        Psicotropic that = (Psicotropic) o;
        if (classificacio != that.classificacio) return false;
        if (Double.compare(that.vidaMitja, vidaMitja) != 0) return false;
        if (legal != that.legal) return false;
        if (!Objects.equals(nom, that.nom)) return false;
        if (analegs.size() != that.analegs.size()) return false;

        Iterator<Analeg> it1 = analegs.iterator();
        Iterator<Analeg> it2 = that.analegs.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }

        return true;
    }

}


