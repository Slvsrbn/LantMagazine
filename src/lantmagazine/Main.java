package lantmagazine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Meniu");
        System.out.println("Introdu tip utilizator (AD/UT)");

        LantDeMagazine kaufland = populateLocatii();
        Scanner key = new Scanner(System.in);
        while (true) {
            final String next = key.nextLine();
            //final String next = readUserAndPassword();
            if (next.equals("AD")) {
                if (meniuAdministrator(kaufland, key))
                    break;


            } else if (next.equals("UT")) {
                if (meniuUtilizator(kaufland, key))
                    break;

            }
        }
    }


    private static boolean meniuUtilizator(LantDeMagazine kaufland, Scanner key) {
        //afisarea tutor magazinelor disponibile
        while (true) {
            System.out.println("Magazinele disponibile sunt:");

            for (int i = 0; i < kaufland.getMagazins().size(); i++) {
                System.out.println(kaufland.getMagazins().get(i));
            }
            System.out.println("Selectare magazin");
            System.out.println("Introduceti numele magazinului dorit: ");
            Magazin mag = new Magazin();
            final String p = key.nextLine();
            boolean magazinExista = false;
            for (int i = 0; i < kaufland.getMagazins().size(); i++) {
                if (p.equals(kaufland.getMagazins().get(i).getNume())) {
                    mag = kaufland.getMagazins().get(i);
                    magazinExista = true;
                }

                if (magazinExista = true) {

                    // citim numele magazinului
                    //if(magazinul exista)
                    // TODO ce se intampla daca el introduce un magazin care nu exista?
                    System.out.println("Ai selectat: " + mag.getNume());

                    System.out.println("1 - Afisare produse");
                    // afisam prod magazinului selectat
                    System.out.println("2 - Cumpara produs");
                    // scadem numarul de produse din stoc
                    System.out.println("3 - Exit");
                    // serializare
                    final String selectieOptiune = key.next();
                    if (selectieOptiune.equals("1")) {
                        metodaAfisareProduseDisponibile(mag);
                        System.out.println("Ce doriti sa faceti?");
                        System.out.println("1-Cumpara produs");
                        System.out.println("2-Exit to main menu");
                        String x = key.next();
                        if (x.equals("1")) {
                            metodaCumparareProdus(key, mag);
                        } else {
                            //todo as faci un meniu pentru return in Main menu
                        }
                        break;

                    } else if (selectieOptiune.equals("2")) {
                        metodaCumparareProdus(key, mag);
                        return true;

                    } else if (selectieOptiune.equals("3")) {
                        return true;
                    }

                } else if (magazinExista = false) {
                    System.out.println("Magzinul introdus nu exista.");
                    System.out.println("Va rugam, reintroduceti!");
                    continue;

                }

            }
            return false;
        }
    }


    private static void metodaAfisareProduseDisponibile(Magazin mag) {
        System.out.println("Produsele disponibile in " + mag + " sunt urmatoarele:");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i));
        }
    }

    private static void metodaCumparareProdus(Scanner key, Magazin mag) {
        System.out.println("Produsele disponibile in " + mag + " sunt urmatoarele:");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println("Produs: " + mag.getStocks().get(i).getProdus() + "  Stoc: " + mag.getStocks().get(i).getNrDeProduse());
        }

        System.out.println("Introduceti codul produsului dorit si numarul de produse: ");
        System.out.println("Cod de bare: ");
        final long codProdusIntrodusLaTastatura = key.nextLong();
        System.out.println("Numarul de produse dorit: ");
        final int numarDeProduseIntrodusLaTastatura = key.nextInt();
        ProductStock produsDorit = null;
        boolean produsDoritGasit = false;
        for (int i = 0; i < mag.getStocks().size(); i++) {
            if (codProdusIntrodusLaTastatura == mag.getStocks().get(i).getProdus().getCod_de_bare()
                    && (mag.getStocks().get(i).getNrDeProduse() - numarDeProduseIntrodusLaTastatura) >= 0) {
                //todo vezi conditia de mai sus -- de inteles + adaugare mesaj de informare -- nu avem atatea produse bo$$
                produsDorit = mag.getStocks().get(i);
                produsDorit.setNrDeProduse(produsDorit.getNrDeProduse() - numarDeProduseIntrodusLaTastatura);
                produsDoritGasit = true;

            }
        }
        if (produsDoritGasit == true) {
            System.out.println("Produsele ramase sunt urmatoarele:");
            for (int i = 0; i < mag.getStocks().size(); i++) {
                System.out.println("Produs: " + mag.getStocks().get(i).getProdus() + "  Stoc: " + mag.getStocks().get(i).getNrDeProduse());
            }
        } else  {
            System.out.println("Bo$$, in stoc sunt doar " + produsDorit.getNrDeProduse() + " produse, de unde plm vrei tu " + numarDeProduseIntrodusLaTastatura + "?");
        }
    }


    private static boolean meniuAdministrator(LantDeMagazine kaufland, Scanner key) {
        meniuMesajAfisareMagazineSauExit();
        final String next1 = key.nextLine();
        if (next1.equals("1")) {
            meniuAfisareMagazine(kaufland);
            Magazin mag = metodaSelectareMagazinSiAfisareOptiuni(kaufland, key);
            meniuAfisareOptiuniInMagazin(mag);
            final String next2 = key.next();
            while (true) {
                if (next2.equals("1")) {
                    metodaAdaugareProduse(key, mag);
                    meniuReintroducereSauExitToMeniu();
                    final String p1 = key.next();
                    if (p1.equals("1")) {
                        continue;
                    } else if (p1.equals("2")) {
                        meniuAfisareOptiuniInMagazin(mag);
                        final String p2 = key.next();
                        while (true) {
                            if (p2.equals("1")) {
                                metodaAdaugareProduse(key, mag);
                                meniuReintroducereSauExitToMeniu();
                                String p3 = key.next();
                                if (p3.equals("1")) {
                                    continue;
                                } else if (p3.equals("2")) {
                                    meniuAfisareOptiuniInMagazin(mag);
                                    break;
                                }
                            } else if (p2.equals("2")) {
                                meniuModificareProdus(key, mag);
                                System.out.println("Doriti sa mai modificati?");
                                System.out.println("1-Da");
                                System.out.println("2-Nu");
                                String optiune = key.next();
                                if (optiune.equals("1")) {
                                    continue;
                                } else
                                    break;


                            } else if (p2.equals("3")) {
                                metodaRemoveProdus(key, mag);
                                break;

                            } else if (p2.equals("4")) {
                                meniuAfisareProduse(mag);
                                break;

                            } else if (p2.equals("5")) {
                                break;
                            }
                        }
                    } else break;

                } else if (next2.equals("2")) {
                    meniuModificareProdus(key, mag);
                    System.out.println("Doriti sa mai modificati?");
                    System.out.println("1-Da");
                    System.out.println("2-Nu");
                    String optiune = key.next();
                    if (optiune.equals("1")) {
                        continue;
                    } else
                        break;


                } else if (next2.equals("3")) {

                    metodaRemoveProdus(key, mag);
                    break;

                } else if (next2.equals("4")) {
                    meniuAfisareProduse(mag);
                    break;

                } else if (next2.equals("5")) {
                    break;
                }
            }
        } else if (next1.equals("2")) {
            return true;
        }
        return false;
    }

    private static void meniuAfisareOptiuniInMagazin(Magazin mag) {
        //System.out.println("Esti in: " + mag);
        System.out.println("Selecteaza o actiune din urmatoarele: ");
        System.out.println("1 - Adaugare produs");
        System.out.println("2 - Modificare produs");
        System.out.println("3 - Remove produs");
        System.out.println("4 - Afisare produse");
        System.out.println("5 - Exit");
    }

    private static void meniuReintroducereSauExitToMeniu() {
        System.out.println("Mai introduci ?");
        System.out.println("1-Reintroduceti!");
        System.out.println("2-Exit to Meniu");
    }

    private static void meniuMesajAfisareMagazineSauExit() {
        System.out.println("Meniu administrator");
        System.out.println("1 - Afisare magazine");
        System.out.println("2 - Exit");

        //if - user a selectat magazin existent
    }

    private static void metodaAdaugareProduse(Scanner key, Magazin mag) {
        Product productparameters = citireProduseDeLaTastatura(key);
        System.out.println("Introduceti numarul de produse pe care il adaugati stocului: ");
        int numarulDeProduseAdaugate = key.nextInt();
        mag.addProdus(productparameters, numarulDeProduseAdaugate);
        Product produs = new Product(productparameters.getCod_de_bare(), productparameters.getDenumire(),
                productparameters.getPret(), productparameters.getCategorie());
        System.out.println("Ati adaugat stocului din " + mag + ", produsul cu: " + produs);
        System.out.println("Produsele disponibile sunt: ");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i));
        }
    }

    private static void meniuModificareProdus(Scanner key, Magazin mag) {
        System.out.println("Doriti sa modificati un produs?");
        System.out.println("1-Da");
        System.out.println("2-Inapoi");
        String inputOptiune = key.nextLine();
        if (inputOptiune.equals("2")) {
            System.out.println("Introdu codul de bare al produsului cautat!");
            //TODO -- introdu de la tastatura litere -- o sa crape

            Long codDebareIntrodus;
            while (true) {
                try {
                    String codDebareIntrodusAsString = key.nextLine();
                    codDebareIntrodus = Long.parseLong(codDebareIntrodusAsString);
                    //daca se arunca exceptie la linia de mai sus nu se mai executa codul de dupa ea, ci intra direct in catch
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Optiune invalida, reintroduceti");
                    continue;
                }
            }
            boolean codGasit = false;
            for (int i = 0; i < mag.getStocks().size(); i++) {
                final long codProdus = mag.getStocks().get(i).getProdus().getCod_de_bare();
                if (codDebareIntrodus == codProdus) {
                    codGasit = true;
                }
                if (codGasit == true) {
                    metodaModificareProdus(key, mag, i);
                    break;
                }
            }
        } else if (inputOptiune.equals("2")) {
            meniuAfisareOptiuniInMagazin(mag);
        }
    }


    private static void metodaModificareProdus(Scanner key, Magazin mag, int i) {
        final Product produsCautat = mag.getStocks().get(i).getProdus();
        System.out.println("Produsul cautat este: " + produsCautat);
        System.out.println("Alegeti ce doriti sa modificati: ");
        System.out.println("1-Modificare Denumire");
        System.out.println("2-Modificare Pret");
        System.out.println("3-Modificare Categorie");
        final String optiuneIntrodusa = key.next();
        if (optiuneIntrodusa.equals("1")) {
            System.out.println("Introduceti noua denumire!");
            final String denumireModificata = key.next();
            produsCautat.setDenumire(denumireModificata);
            System.out.println("Produsul modificat este: " + produsCautat);
            return;

        } else if (optiuneIntrodusa.equals("2")) {
            System.out.println("Introduceti pret nou!");
            //douazeci
            //trateaza formatarea numarului ca mai sus (try/catch)
            final double pretModificat = key.nextDouble();
            produsCautat.setPret(pretModificat);
            System.out.println("Produsul modificat este: " + produsCautat);
            return;

        } else if (optiuneIntrodusa.equals("3")) {
            System.out.println("Introduceti noua categorie!");
            final String categorieModificata = key.next();
            produsCautat.setCategorie(categorieModificata);
            System.out.println("Produsul modificat este: " + produsCautat);
            return;
        }
    }


    private static void meniuAfisareProduse(Magazin mag) {
        System.out.println("Produsele disponibile sunt: ");
        for (int i = 0; i < mag.getStocks().size(); i++) {
            System.out.println(mag.getStocks().get(i).getProdus());
        }
    }

    private static void metodaRemoveProdus(Scanner key, Magazin mag) {
        meniuAfisareProduse(mag);
        System.out.println("Introdu codul de bare al produsului cautat!");
        long codDebareIntrodus = key.nextLong();
        boolean codGasit = false;
        for (int i = 0; i < mag.getStocks().size(); i++) {
            final long codProdus = mag.getStocks().get(i).getProdus().getCod_de_bare();
            if (codDebareIntrodus == codProdus) {
                codGasit = true;
            }
            if (codGasit == true) {
                final Product produsCautat = mag.getStocks().get(i).getProdus();
                System.out.println("Produsul cautat este: " + produsCautat);
                System.out.println("Doriti sa stergeti produsul? ");
                System.out.println("1-Da");
                System.out.println("2-Nu");
                final String optiuneIntrodusa = key.next();
                if (optiuneIntrodusa.equals("1")) {
                    mag.getStocks().remove(i);
                    System.out.println("Produsul " + produsCautat + " a fost sters.");
                    System.out.println("Produsele ramase sunt");
                    for (i = 0; i < mag.getStocks().size(); i++) {
                        System.out.println(mag.getStocks().get(i).getProdus());
                    }
                }
            }
        }
        return;
    }

    private static void meniuAfisareMagazine(LantDeMagazine kaufland) {
        System.out.println("Magazinele disponibile sunt:");

        for (int i = 0; i < kaufland.getMagazins().size(); i++) {
            System.out.println(kaufland.getMagazins().get(i));
        }
        System.out.println("Selecteaza un magazin prin introducerea numelui magazinului dorit: ");
        //acum userul trebuie sa selecteze un magazin
    }


    private static Magazin metodaSelectareMagazinSiAfisareOptiuni(LantDeMagazine kaufland, Scanner key) {
        Magazin mag = new Magazin();

        while (true) {
            final String numeMagazinDeLaTastatura = key.nextLine();
            boolean numeMagazinDeLaTastaturaGasit = false;
            for (int i = 0; i < kaufland.getMagazins().size(); i++) {
                //TODO aici o sa crape daca introduce numar negativ -- mai bine ar citi de la tastatura numele magazinului
                if (numeMagazinDeLaTastatura.equals(kaufland.getMagazins().get(i).getNume())) {
                    numeMagazinDeLaTastaturaGasit = true;
                    mag = kaufland.getMagazins().get(i);
                }
            }
            if (numeMagazinDeLaTastaturaGasit == true) {
                System.out.println("Esti in : " + mag);
            } else {
                System.out.println("Selectie invalida!!!!!!!");
                System.out.println("Va rugam reintroduceti un nume valid!");
                continue;
            }
            return mag;
        }
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

        mBucuresti.setNume("Magazin Bucuresti");
        mIasi.setNume("Magazin Iasi");
        mSuceava.setNume("Magazin Suceava");

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

   /* static String readUserAndPassword () {
        Scanner key = new Scanner(System.in);
        final String user = key.nextLine();
        final String parola = key.nextLine();

        //read from file
        //if(file contains user + parola)
        {
            //read from file user type
            return "user type readed from file";
        }
    }
    */


}



