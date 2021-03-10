/**
IN1010. Oblig 4
Hovedprogram som oppretter og holder styr på objekter av klassene Pasient,
Lege, Legemiddel og Resept.
Programmet tilbyr også brukergrensesnitt som gir tilgang til å lese inn nye objekter fra fil eller
direkte fra bruker samt skrive ut informasjon.
*/
import java.util.*;
import java.io.*;

class Legesystem    {


static Lenkeliste<Pasient> pasienter = new Lenkeliste<>();
static Lenkeliste<Legemiddel> legemiddler = new Lenkeliste<>();
static SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();
static Lenkeliste<Resept> resepter = new Lenkeliste<>();

public static void main(String[] args) {
    lesInnObjekterFil("pasienter.txt");
    System.out.println(pasienter);
}

public static void lesInnObjekterFil(String filnavn)   {
    Scanner fil = null;
    try {
        fil = new Scanner(new File(filnavn));
    } catch(FileNotFoundException e) {
        System.out.println("Filen ikke funnet eller filen har feil format.");
    }

    while (fil.hasNextLine()){
            String linje = fil.nextLine();
            if (linje.contains("# Pasienter"))   {

                String[] biter = fil.nextLine().split(",");
                while (!biter[0].contains("#"))   {
                    pasienter.leggTil(new Pasient(biter[0],biter[1]));
                    if (fil.hasNextLine())  {
                        biter = fil.nextLine().split(",");
                    }
                }

            }

        }
} //end method lesInnObjekterFil()

} // end class Legesystem
