package lantmagazine;

import java.io.Serializable;

public class ProductStock implements Serializable {
    private Product produs;
    private int nrDeProduse;


    void setProdus(Product produs){

        this.produs=produs;
    }

    void setNrDeProduse(int nrDeProduse){

        this.nrDeProduse=nrDeProduse;
    }

    public int getNrDeProduse() {
        return nrDeProduse;
    }

    public Product getProdus() {
        return produs;
    }
    @Override
    public String toString(){
        return this.produs+" "+this.nrDeProduse;
    }
}