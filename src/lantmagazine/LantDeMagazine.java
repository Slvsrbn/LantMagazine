package lantmagazine;
import java.io.Serializable;
import java.util.ArrayList;

public class LantDeMagazine implements Serializable {

    private ArrayList<Magazin>magazins=new ArrayList<>();

    public ArrayList<Magazin> getMagazins() {
        return magazins;
    }
    public void setMagazins(ArrayList<Magazin> magazins) {
        this.magazins = magazins;
    }
}



//void addLocatii(Magazin magazin,String nume){
//    LocatiiOfMagazins locatii=new LocatiiOfMagazins();
//    locatii.setMagazin(magazin);
//    locatii.setNume(nume);
//    magazins.add(locatii);
// }
// private ArrayList <Magazin> Profi = new ArrayList<>();

//public ArrayList<Magazin> getProfi() {
//  return Profi;
//}