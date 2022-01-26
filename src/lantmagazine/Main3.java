package lantmagazine;

import java.io.*;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        LantDeMagazine kaufland = metodaDeserializare();

        System.out.println("Meniu");
        System.out.println("Introdu tip utilizator (AD/UT)");

        //kaufland = populateLocatii();
        Scanner key = new Scanner(System.in);
        while (true) {
            String next = key.nextLine();
            //final String next = readUserAndPassword();
            if (next.equalsIgnoreCase("AD")) {
                meniuAdmin(key, kaufland);
                break;

            } else if (next.equalsIgnoreCase("UT")) {
                meniuUtilizator(key,kaufland);
                break;
            }else {
                System.out.println("Optiune invalida, reintroduceti!");
                continue;
            }
        }

        metodaSerializare(kaufland);
    }








    private static void metodaSerializare(LantDeMagazine kaufland) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("MagazineInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(kaufland);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in MagazineInfo.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static LantDeMagazine metodaDeserializare() {

        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Andreia\\IdeaProjects\\NewReadingProject\\MagazineInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            in.close();
            fileIn.close();
            return  (LantDeMagazine) in.readObject();
            //return ka

        } catch (IOException i) {
            i.printStackTrace();
            //return true;
        } catch (ClassNotFoundException c) {
            System.out.println("LantDeMagazine class not found");
            c.printStackTrace();
          ///  return true;
        }

        return populateLocatii();
       // return false;
    }

    public static void meniuUtilizator(Scanner key,LantDeMagazine kaufland){
        System.out.println("Meniu utilizator");
        System.out.println("1 - Afisare locatii");
        System.out.println("2 - Exit");

        while (true) {
            String next1 = key.next();
            if (next1.equals("1")) {
                meniuAfisareLocatiiUtilizator(key, kaufland);
            } else if (next1.equals("2")) {
                break;
            } else {
                System.out.println("Optiune invalida, reintroduceti!");
            }
        }
    }

    private static void meniuAfisareLocatiiUtilizator(Scanner key, LantDeMagazine kaufland) {
        System.out.println("Locatiile disponibile sunt:");
        for (int i = 0; i < kaufland.getMagazins().size(); i++) {
            System.out.println(kaufland.getMagazins().get(i));
        }
        System.out.println("\nIntroduceti numele locatiei dorite:");
        Magazin mag = new Magazin();
        while (true) {
            mag = meniuSelectareMagazin(key, kaufland, mag);
            continue;
        }
    }

    private static Magazin meniuSelectareMagazin(Scanner key, LantDeMagazine kaufland, Magazin mag) {
        String numeMagazinDeLaTastatura = key.next();
        boolean numeMagazinDeLaTastaturaGasit = false;
        for (int i = 0; i < kaufland.getMagazins().size(); i++) {
            if (numeMagazinDeLaTastatura.equals(kaufland.getMagazins().get(i).getNume())) {
                numeMagazinDeLaTastaturaGasit = true;
                mag = kaufland.getMagazins().get(i);

            }
        }
        if (numeMagazinDeLaTastaturaGasit == true) {
            metodaNumeMagazinGasitTrueUtilizator(key, mag);

        } else if (numeMagazinDeLaTastaturaGasit == false) {
            System.out.println("Optiune invalida, reintroduceti!");

        }
        return mag;
    }

    private static void metodaNumeMagazinGasitTrueUtilizator(Scanner key, Magazin mag) {
        meniuAfisareMagazinSiOptiuniUtilizator(mag);

        String selectieOptiune2 = key.next();
        while (true) {

            if (selectieOptiune2.equals("1")) {
                meniuAfisareSiCumparare(key, mag);


            } else if (selectieOptiune2.equals("2")) {
                break;
            }
            meniuAfisareMagazinSiOptiuniUtilizator(mag);

            selectieOptiune2 = key.next();
            if (selectieOptiune2.equals("1")) {
                continue;
            } else if (selectieOptiune2.equals("2")) {
                continue;
            } else {
                System.out.println("Optiune invalida, reintroduceti!");
                continue;
            }
        }
    }

    private static void meniuAfisareMagazinSiOptiuniUtilizator(Magazin mag) {
        System.out.println("Esti in: Magazin " + mag);
        System.out.println("Selecteaza o actiune din urmatoarele: ");
        System.out.println("1 - Afiseaza si Cumpara produse");
        System.out.println("2 - Exit");
    }

    public static void meniuAfisareSiCumparare(Scanner key, Magazin mag){
        System.out.println("Produsele disponibile in " + mag + " sunt urmatoarele:");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println("Produs: " + mag.getStocks().get(i).getProdus() + "  Stoc: " + mag.getStocks().get(i).getNrDeProduse());
        }

        System.out.println("\nIntroduceti codul de bare al produsului si numarul de produse dorit: ");

        while (true) {
            System.out.println("Cod de bare: ");
            long codProdusIntrodusLaTastatura;
            String codProdusIntrodusLaTastaturaAsString = key.next();
            System.out.println("Numarul de produse dorit: ");
            int numarDeProduseIntrodusLaTastatura;
            String numarDeProduseIntrodusLaTastaturaAsString = key.next();

            try {
                codProdusIntrodusLaTastatura = Long.parseLong(codProdusIntrodusLaTastaturaAsString);
                numarDeProduseIntrodusLaTastatura = Integer.parseInt(numarDeProduseIntrodusLaTastaturaAsString);
                ProductStock produsDorit = null;
                boolean produsDoritGasit = false;
                for (int i = 0; i < mag.getStocks().size(); i++) {
                    if (codProdusIntrodusLaTastatura == mag.getStocks().get(i).getProdus().getCod_de_bare()
                            && ((mag.getStocks().get(i).getNrDeProduse() - numarDeProduseIntrodusLaTastatura)) >= 0) {
                        //todo vezi conditia de mai sus -- de inteles + adaugare mesaj de informare -- nu avem atatea produse bo$$
                        produsDorit = mag.getStocks().get(i);
                        produsDorit.setNrDeProduse(produsDorit.getNrDeProduse() - numarDeProduseIntrodusLaTastatura);
                        produsDoritGasit = true;
                        break;
                    }

                    if (codProdusIntrodusLaTastatura == mag.getStocks().get(i).getProdus().getCod_de_bare()
                            && (mag.getStocks().get(i).getNrDeProduse() - numarDeProduseIntrodusLaTastatura) < 0) {
                        produsDorit = mag.getStocks().get(i);
                        System.out.println("Bo$$, in stoc sunt doar " + produsDorit.getNrDeProduse()
                                + " produse, de unde plm vrei tu " + numarDeProduseIntrodusLaTastatura + "?");
                        break;
                    }
                }
                if (produsDoritGasit == true) {
                    System.out.println("Produsele ramase sunt urmatoarele:");
                    for (int i = 0; i < mag.getStocks().size(); i++) {
                        System.out.println("Produs: " + mag.getStocks().get(i).getProdus() + "  Stoc: " + mag.getStocks().get(i).getNrDeProduse());
                    }
                    break;
                } else if (produsDoritGasit == false) {
                    System.out.println("Optiune invalida, reintroduceti!");
                    continue;
                }

            } catch (NumberFormatException e) {
                System.out.println("Optiune invalida, reintroduceti!");
                continue;
            }
        }
    }

    public static void meniuAdmin(Scanner key, LantDeMagazine kaufland){
        System.out.println("Meniu administrator");
        System.out.println("1 - Afisare locatii");
        System.out.println("2 - Exit");

        while (true) {
            String next1 = key.nextLine();
            if (next1.equals("1")) {
                metodaAfisareLocatii(key, kaufland);
                break;

            } else if (next1.equals("2")) {
                break;
            } else {
                System.out.println("Optiune invalida, reintroduceti!");
                continue;
            }
        }
    }

    private static void metodaAfisareLocatii(Scanner key, LantDeMagazine kaufland) {
        System.out.println("Locatiile disponibile sunt:");
        for (int i = 0; i < kaufland.getMagazins().size(); i++) {
            System.out.println(kaufland.getMagazins().get(i));
        }
        System.out.println("\nIntroduceti numele locatiei dorite:");
        Magazin mag = new Magazin();
        while (true) {
            String numeMagazinDeLaTastatura = key.nextLine();
            boolean numeMagazinDeLaTastaturaGasit = false;
            for (int i = 0; i < kaufland.getMagazins().size(); i++) {
                if (numeMagazinDeLaTastatura.equalsIgnoreCase(kaufland.getMagazins().get(i).getNume())) {
                    numeMagazinDeLaTastaturaGasit = true;
                    mag = kaufland.getMagazins().get(i);
                    break;
                }

            }
            if (numeMagazinDeLaTastaturaGasit == true) {
                System.out.println("Esti in: Magazin " + mag);
                System.out.println("Selecteaza o actiune din urmatoarele: ");
                System.out.println("1 - Adaugare produs");
                System.out.println("2 - Modificare produs");
                System.out.println("3 - Remove produs");
                System.out.println("4 - Afisare produse");
                System.out.println("5 - Exit");
                String next2 = key.next();
                while (true) {
                    if (next2.equals("1")) {
                        next2 = metodaAdaugareProduse(key, mag);
                        continue;

                    } else if (next2.equals("2")) {
                        next2 = metodaModificareProdus(key, mag);
                        continue;

                    } else if (next2.equals("3")) {
                        metodaRemoveProduse(key, mag);
                        //TODO de adaugat meniul de final

                    } else if (next2.equals("4")) {
                        next2 = metodaAfisareProduse(key, mag);
                        continue;

                    } else if (next2.equals("5")) {
                        break;
                    }
                }
                break;
            } else {
                System.out.println("Optiune invalida, reintroduceti!");
                continue;
            }
        }
        return;
    }

    private static void metodaRemoveProduse(Scanner key, Magazin mag) {
        System.out.println("Produsele disponibile sunt: ");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i).getProdus());
        }

        long codDebareIntrodus;
        while (true) {
            System.out.println("Introdu codul de bare al produsului cautat!");
            String codDeBareIntrodusAsString = key.next();
            try {
                codDebareIntrodus = Long.parseLong(codDeBareIntrodusAsString);
                boolean codGasit = false;
                Product produsCautat = null;
                for (int i = 0; i < mag.getStocks().size(); i++) {

                    final long codProdus = mag.getStocks().get(i).getProdus().getCod_de_bare();
                    if (codDebareIntrodus == codProdus) {
                        produsCautat = mag.getStocks().get(i).getProdus();
                        codGasit = true;
                        System.out.println("Produsul cautat este: " + produsCautat);
                        System.out.println("Doriti sa stergeti produsul? ");
                        System.out.println("1-Da");
                        System.out.println("2-Nu");
                        String optiuneIntrodusa = key.next();
                        if (optiuneIntrodusa.equals("1")) {
                            i = meniuStergereProdus(mag, produsCautat, i);

                        } if (optiuneIntrodusa.equals("2")) {
                            System.out.println("Optiune invalida, reintroduceti");
                            continue;
                        }
                        break;
                    }
                }if (mag.getStocks().size()==0){
                    System.out.println("Mgazinul a ramas fara produse");
                }
                if (codGasit == false) {
                    System.out.println("Optiune invalida, reintroduceti");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Optiune invalida, reintroduceti");
                continue;
            }

        }
    }

    private static int meniuStergereProdus(Magazin mag, Product produsCautat, int i) {
        mag.getStocks().remove(i);
        System.out.println("Produsul " + produsCautat + " a fost sters.");
        System.out.println("\nProdusele ramase sunt:\n");
        for (i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i).getProdus());
            continue;
        }
        return i;
    }

    private static String metodaAfisareProduse(Scanner key, Magazin mag) {
        String next2;
        System.out.println("Produsele disponibile sunt: ");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i).getProdus());
        }
        System.out.println("\n1 - Adaugare produs");
        System.out.println("2 - Modificare produs");
        System.out.println("3 - Remove produs");
        System.out.println("4 - Reafisare produse");
        System.out.println("5 - Exit");
        next2 = key.next();
        if (next2.equals("1")) {
            return next2;
        } else if (next2.equals("2")) {
            return next2;
        } else if (next2.equals("3")) {
            return next2;
        } else if (next2.equals("4")) {
            return next2;
        } else if (next2.equals("5")) {
            return next2;
        }
        return next2;
    }

    private static String metodaModificareProdus(Scanner key, Magazin mag) {
        String next2;
        System.out.println("Produsele disponibile in " + mag + " sunt urmatoarele: \n");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i).getProdus());
        }
        System.out.println("\nIntrodu codul de bare al produsului pe care doriti sa il modificati!");
        //TODO -- introdu de la tastatura litere -- o sa crape --->>L-am facut!!!
        Long codDebareIntrodus;
        while (true) {
            String codDeBareIntrodusCaString = key.next();
            try {
                codDebareIntrodus = Long.parseLong(codDeBareIntrodusCaString);
                boolean codGasit = false;
                Product produsCautat = null;
                for (int i = 0; i < mag.getStocks().size(); i++) {
                    final long codProdus = mag.getStocks().get(i).getProdus().getCod_de_bare();
                    if (codDebareIntrodus == codProdus) {
                        produsCautat = mag.getStocks().get(i).getProdus();
                        codGasit = true;
                    }
                }
                if (codGasit == true) {
                    System.out.println("Produsul cautat este: " + produsCautat + "\n");
                    System.out.println("Alegeti ce doriti sa modificati: ");
                    System.out.println("1-Modificare Denumire");
                    System.out.println("2-Modificare Pret");
                    System.out.println("3-Modificare Categorie");
                    String optiuneIntrodusa = key.next();
                    while (true) {
                        if (optiuneIntrodusa.equals("1")) {
                            System.out.println("Introduceti noua denumire!");
                            final String denumireModificata = key.next();
                            produsCautat.setDenumire(denumireModificata);
                            System.out.println("Produsul modificat este: " + produsCautat + "\n");
                            System.out.println("Doriti sa mai modificati produsul selectat?");
                            System.out.println("1-Modificare Denumire");
                            System.out.println("2-Modificare Pret");
                            System.out.println("3-Modificare Categorie");
                            System.out.println("4-Exit");
                            optiuneIntrodusa = key.next();
                            if (optiuneIntrodusa.equals("1")) {
                                continue;
                            } else if (optiuneIntrodusa.equals("2")) {
                                continue;
                            } else if (optiuneIntrodusa.equals("3")) {
                                continue;
                            } else if (optiuneIntrodusa.equals("4")) {
                                break;
                            }
                            break;
                        } else if (optiuneIntrodusa.equals("2")) {
                            System.out.println("Introduceti pret nou!");
                            //douazeci
                            //trateaza formatarea numarului ca mai sus (try/catch)
                            double pretModificat;
                            String pretModificatAsString = key.next();
                            try {
                                pretModificat = Double.parseDouble(pretModificatAsString);
                                produsCautat.setPret(pretModificat);
                                System.out.println("Produsul modificat este: " + produsCautat + "\n");
                                System.out.println("Doriti sa mai modificati produsul selectat?");
                                System.out.println("1-Modificare Denumire");
                                System.out.println("2-Modificare Pret");
                                System.out.println("3-Modificare Categorie");
                                System.out.println("4-Exit");
                                optiuneIntrodusa = key.next();
                                if (optiuneIntrodusa.equals("1")) {
                                    continue;
                                } else if (optiuneIntrodusa.equals("2")) {
                                    continue;
                                } else if (optiuneIntrodusa.equals("3")) {
                                    continue;
                                } else if (optiuneIntrodusa.equals("4")) {
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Optiune invalida, reintroduceti");
                                continue;
                            }
                            break;

                        } else if (optiuneIntrodusa.equals("3")) {

                            System.out.println("Introduceti noua categorie!");
                            final String categorieModificata = key.next();
                            produsCautat.setCategorie(categorieModificata);
                            System.out.println("Produsul modificat este: " + produsCautat + "\n");
                            System.out.println("Doriti sa mai modificati produsul selectat?");
                            System.out.println("1-Modificare Denumire");
                            System.out.println("2-Modificare Pret");
                            System.out.println("3-Modificare Categorie");
                            System.out.println("4-Exit");
                            optiuneIntrodusa = key.next();
                            if (optiuneIntrodusa.equals("1")) {
                                continue;
                            } else if (optiuneIntrodusa.equals("2")) {
                                continue;
                            } else if (optiuneIntrodusa.equals("3")) {
                                continue;
                            } else if (optiuneIntrodusa.equals("4")) {
                                break;
                            }
                            break;
                        }
                    }
                    break;
                } else if (codGasit == false) {
                    System.out.println("Optiune invalida, reintroduceti!");
                    continue;
                }
            } catch (NumberFormatException formatException) {
                System.out.println("Optiune invalida, reintroduceti");
                continue;
            }
        }
        System.out.println("\nEsti in Magazin " + mag);
        System.out.println("Selecteaza o actiune din urmatoarele:");
        System.out.println("\n1 - Adaugare produs");
        System.out.println("2 - Modificare produs");
        System.out.println("3 - Remove produs");
        System.out.println("4 - Afisare produse");
        System.out.println("5 - Exit");
        next2 = key.next();
        if (next2.equals("1")) {
            return next2;
        } else if (next2.equals("2")) {
            return next2;
        } else if (next2.equals("3")) {
            return next2;
        } else if (next2.equals("4")) {
            return next2;
        } else if (next2.equals("5")) {
            return next2;
        }
        return next2;
    }

    private static String metodaAdaugareProduse(Scanner key, Magazin mag) {
        String next2;
        Product productparameters = citireProduseDeLaTastatura(key);
        System.out.println("Introduceti numarul de produse pe care il adaugati stocului: ");
        int numarulDeProduseAdaugate = key.nextInt();
        mag.addProdus(productparameters, numarulDeProduseAdaugate);
        Product produs = new Product(productparameters.getCod_de_bare(), productparameters.getDenumire(),
                productparameters.getPret(), productparameters.getCategorie());
        System.out.println("Ati adaugat stocului din Magazinului " + mag + ", " + numarulDeProduseAdaugate + " bucati din produsul cu codul de bare: " + produs.getCod_de_bare());
        System.out.println("\nProdusele disponibile sunt: ");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i));
        }
        System.out.println("\nMai introduci ?");
        System.out.println("1 - Reintroduceti!");
        System.out.println("2 - Modificare produs");
        System.out.println("3 - Remove produs");
        System.out.println("4 - Afisare produse");
        System.out.println("5 - Exit");
        next2 = key.next();
        if (next2.equals("1")) {
            return next2;
        } else if (next2.equals("2")) {
            return next2;
        } else if (next2.equals("3")) {
            return next2;
        } else if (next2.equals("4")) {
            return next2;
        } else if (next2.equals("5")) {
            return next2;
        }
        return next2;
    }

    private static Product citireProduseDeLaTastatura(Scanner key) {


        System.out.println("Introdu date produs");
        System.out.println("Introdu Codul de bare:");
        final long cod_de_bare = key.nextLong();
        System.out.println("Introdu Denumire:");
        final String denumire = key.next();
        System.out.println("Introdu Pretul:");
        final double pret = key.nextDouble();
        System.out.println("Introdu Categorie:");
        final String categorie = key.next();
        return new Product(cod_de_bare, denumire, pret, categorie);
    }

    private static LantDeMagazine populateLocatii() {
        LantDeMagazine kaufland = new LantDeMagazine();
        Magazin mIasi = new Magazin();
        Magazin mBucuresti = new Magazin();
        Magazin mSuceava = new Magazin();

        kaufland.getMagazins().add(mIasi);
        kaufland.getMagazins().add(mSuceava);
        kaufland.getMagazins().add(mBucuresti);

        mBucuresti.setNume("Bucuresti");
        mIasi.setNume("Iasi");
        mSuceava.setNume("Suceava");

        Product p1 = new Product(123, "kinder", 3.99, "ciocolata");
        Product p2 = new Product(1234, "ciocotoff", 8.99, "ciocolata");
        Product p3 = new Product(1543, "poiana", 2.99, "ciocolata");


        mSuceava.addProdus(p3, 3);
        mSuceava.addProdus(p3, 5);
        mBucuresti.addProdus(p2, 4);
        mBucuresti.addProdus(p3, 2);
        mBucuresti.addProdus(p1, 5);
        mIasi.addProdus(p2, 3);
        mIasi.addProdus(p1, 3);
        mIasi.addProdus(p3, 3);
        return kaufland;
    }
}



