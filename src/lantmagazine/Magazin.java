package lantmagazine;

import java.io.Serializable;
import java.util.ArrayList;

public class Magazin implements Serializable {

    String nume;
    private ArrayList<ProductStock> stocks = new ArrayList<>();

    void addProdus(Product produs, int nrDeProduse) {
        if (produs.getCod_de_bare() > 0 && produs.getDenumire() != null && produs.getPret() > 0 && produs.getCategorie() != null) {
            boolean b=false;
            for (int i = 0; i < stocks.size(); i++) {
                ProductStock ps = stocks.get(i);
                if (ps.getProdus().getCod_de_bare() == produs.getCod_de_bare()) {
                    b = true;
                    ps.setProdus(produs);
                    ps.setNrDeProduse(ps.getNrDeProduse()+ 1);
                }
            }
            if (b==false){
                ProductStock productStock = new ProductStock();
                productStock.setProdus(produs);
                productStock.setNrDeProduse(nrDeProduse);
                stocks.add(productStock);
            }
        }
    }

    public void removeProdus(Product produs){

        for (int i = 0; i < stocks.size(); i++) {
            ProductStock produsForRemove=stocks.get(i);
            if (produs.getCod_de_bare() == stocks.get(i).getProdus().getCod_de_bare()) {
                stocks.remove(produsForRemove);
            }
        }
    }

    public String getNume() {
        return nume;
    }

    public ArrayList<ProductStock> getStocks () {
        return stocks;
    }

    public void setNume (String nume){
        this.nume = nume;
    }

    @Override
    public String toString () {
        return this.nume;
    }
}





