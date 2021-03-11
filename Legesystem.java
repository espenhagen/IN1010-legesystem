/**
IN1010. Oblig 4
Hovedprogram som oppretter og holder styr på objekter av klassene Pasient,
Lege, Legemiddel og Resept.
Programmet tilbyr også brukergrensesnitt som gir tilgang til å lese inn nye objekter fra fil eller
direkte fra bruker samt skrive ut informasjon.
*/
import java.util.*;
import java.io.*;

public class Legesystem {

    static Lenkeliste<Pasient> pasienter = new Lenkeliste<>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<>();
    static SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();
    static Lenkeliste<Resept> resepter = new Lenkeliste<>();

    public static void main(String[] args) throws FormatException, UlovligUtskrift {
        lesInnObjekterFil("storTestfil.txt");
        System.out.println(pasienter);
        System.out.println();
        System.out.println(legemidler);
        System.out.println();
        System.out.println(leger);
        System.out.println("NÅ KOMMER DET");
        System.out.println(resepter);
        hovedmeny();
    }

    public static void hovedmeny()  {
        String hovedmeny = "\nHovedmeny: " +
                           "\n1: Skrive ut en fullstend oversikt over " +
                           "leger, resepter og legemidler." +
                           "\n2: Legg til ny lege, pasient, legemidel eller resept." +
                           "\n3: Bruk resept." +
                           "\n4: Vis statistikk." +
                           "\n5: Skriv alle data til fil." +
                           "\n6: Avslutt." +
                           "\nVelg handling:" +
                           "\nTast inn menynummer og avslutt med ENTER. \n>";
        Scanner tastatur = new Scanner(System.in);
        int input = 0;

    while (input != 6)   {
        System.out.print(hovedmeny);
        input = tastatur.nextInt();
        if (input == 1) {}
        else if (input == 2)    {}
        else if (input == 3)    {}
        else if (input == 4)    {}
        else if (input == 5)    {}
        else if (input == 6)    {System.exit(0);}
        else {System.out.println("Feil. Input maa være et tall mellom 1 og 6. Prov igjen. \n");}

    } //end while
    }

    public static void lesInnObjekterFil(String filnavn) throws FormatException, UlovligUtskrift {
        Scanner fil = null;
        try {
            fil = new Scanner(new File(filnavn));
        } catch(FileNotFoundException e) {
            System.out.println("Filen ikke funnet eller filen har feil format.");
        }

        while (fil.hasNextLine()) {
            String linje = fil.nextLine();

            if (linje.contains("# Pasienter"))   {
                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while (!biter[0].contains("#"))   {
                    // biter[0] er navnet til pasienten, og biter[1] tilsvarer fødselsnummeret
                    pasienter.leggTil(new Pasient(biter[0], biter[1]));
                    linje = fil.nextLine();
                    biter = linje.split(",");
                }
            }

            if (linje.contains("# Legemidler")) {
                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while (!biter[0].contains("#"))   {
                    String navn = biter[0];
                    String type = biter[1];
                    int pris = Integer.parseInt(biter[2]);
                    double virkestoff = Double.parseDouble(biter[3]);

                    if (type.equals("vanlig")) {
                        legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                    }

                    else if (type.equals("vanedannende")) {
                        int styrke = Integer.parseInt(biter[4]);
                        legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                    }
                    else if (type.equals("narkotisk")) {
                        int styrke = Integer.parseInt(biter[4]);
                        legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                    }
                    else {
                        throw new FormatException("Feil format på input.");
                    }

                    linje = fil.nextLine();
                    biter = linje.split(",");
                }
            }

            if (linje.contains("# Leger")) {
                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while (!biter[0].contains("#"))   {
                    String navn = biter[0];
                    String kontrollid = biter[1];

                    if (kontrollid.equals("0")) {
                        leger.leggTil(new Lege(navn));
                    }
                    else {
                        leger.leggTil(new Spesialist(navn, kontrollid));
                    }

                    linje = fil.nextLine();
                    biter = linje.split(",");
                }
            }

            if (linje.contains("# Resepter")) {
                linje = fil.nextLine();
                String[] biter = linje.split(",");

                while (fil.hasNextLine())   {
                    skrivResepter(biter);

                    linje = fil.nextLine();
                    biter = linje.split(",");
                }
                skrivResepter(biter);
            }
        }

    } //end method lesInnObjekterFil()

    public static void skrivResepter(String[] biter) throws UlovligUtskrift {
        int legemiddelNummer = Integer.parseInt(biter[0]);
        String legeNavn = biter[1];
        int pasientId = Integer.parseInt(biter[2]);
        String type = biter[3];

        Pasient pasient = null;
        Lege lege = null;
        Legemiddel legemiddel = null;

        for (Pasient pas : pasienter) {
            if (pas.hentId() == pasientId) {
                pasient = pas;
            }
        }

        for (Lege doc : leger) {
            if (doc.hentNavn().equals(legeNavn)) {
                System.out.println(doc.hentNavn());
                lege = doc;
            }
        }

        for (Legemiddel middel : legemidler) {
            if (middel.hentId() == legemiddelNummer) {
                legemiddel = middel;
            }
        }

        if (type.equals("blaa")) {
            int reit = Integer.parseInt(biter[4]);
            resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
        }

        if (type.equals("hvit")) {
            int reit = Integer.parseInt(biter[4]);
            resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
        }

        // SKRIVEFEIL i testfiler og obligtekster
        if (type.equals("militaer") || type.equals("millitaer")) {
            int reit = Integer.parseInt(biter[4]);
            resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
        }

        if (type.equals("p")) {
            resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
        }
    }

} // end class Legesystem
