package lantmagazine;

import java.io.Serializable;

public class Product implements Serializable {
    private long cod_de_bare;
    private String denumire;
    private double pret;
    private String categorie;

    public Product(long cod_de_bare, String denumire, double pret, String categorie) {
        this.cod_de_bare = cod_de_bare;
        this.denumire = denumire;
        this.pret = pret;
        this.categorie = categorie;
    }

    public long getCod_de_bare() {
        return cod_de_bare;
    }

    public void setCod_de_bare(long cod_de_bare) {
        this.cod_de_bare = cod_de_bare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString(){
        return "Denumire: " + this.denumire+", "+ "Pret: " + this.pret+", "+ "Categorie: " + this.categorie+", "+ "Cod bare: " + this.cod_de_bare;
    }
}
