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

    static Lenkeliste<Pasient> pasienter = new Lenkeliste <>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste <>();
    static SortertLenkeliste<Lege> leger = new SortertLenkeliste <>();
    static Lenkeliste<Resept> resepter = new Lenkeliste <>();

    public static void main(String[] args) throws FormatException, UlovligUtskrift {
        lesInnObjekterFil("storTestfil.txt");
        hovedmeny();
    }

    // Skriver ut en hovedmeny som bruker kan navigere i
    public static void hovedmeny() throws UlovligUtskrift {
        String hovedmeny = "\nHovedmeny: " +
                           "\n1: Skrive ut en fullstendig oversikt over " +
                           "leger, resepter og legemidler." +
                           "\n2: Legg til ny lege, pasient, legemiddel eller resept." +
                           "\n3: Bruk resept." +
                           "\n4: Vis statistikk." +
                           "\n5: Skriv alle data til fil." +
                           "\n6: Avslutt." +
                           "\nVelg handling:" +
                           "\nTast inn menynummer og avslutt med ENTER. \n>";
        Scanner tastatur = new Scanner(System.in);
        String input = "";

        while (!input.equals("6"))   {
            System.out.print(hovedmeny);
            input = tastatur.nextLine();
            if (input.equals("1")) {skrivOversikt();}
            else if (input.equals("2"))   {leggTilNy();}
            else if (input.equals("3"))   {brukResept();}
            else if (input.equals("4"))   {visStatistikk();}
            else if (input.equals("5"))   {skrivTilFil();}
            else if (input.equals("6"))    {System.exit(0);}
            else {System.out.println("Feil. Input maa vaere et tall mellom 1 og 6. Prov igjen. \n");}

        }
    } // end method hovedmeny()

    // Metoden skriver ut dataen i strukturen, til en ny fil som brukeren angir
    public static void skrivTilFil() {
        Scanner tastatur = new Scanner(System.in);
        System.out.print("Oppgi filnavn:\n-> ");
        String filnavn = tastatur.nextLine();


        try {
            PrintWriter skriver = new PrintWriter(filnavn);

            skriver.println("# Pasienter (navn, fnr)");
            for (Pasient pasient : pasienter) {
                skriver.println(pasient.hentNavn() + "," + pasient.hentFoedselsnr());
            }

            skriver.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
            for (Legemiddel legemiddel : legemidler) {
                skriver.print(legemiddel.hentNavn() + ","
                + legemiddel.hentType() + ","
                + legemiddel.hentPris() + ","
                + legemiddel.hentVirkestoff() + "," );

                if (legemiddel instanceof Vanedannende) {
                    Vanedannende vanedannende = (Vanedannende) legemiddel;
                    skriver.println(vanedannende.hentVanedannendeStyrke());
                }

                else if (legemiddel instanceof Narkotisk) {
                    Narkotisk narkotisk = (Narkotisk) legemiddel;
                    skriver.println(narkotisk.hentNarkotiskStyrke());
                }

                else {
                    skriver.println();
                }
            }

            skriver.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
            for (Lege lege : leger) {
                skriver.print(lege.hentNavn() + ",");
                if (lege.erSpesialist()) {
                    Spesialist spesialist = (Spesialist) lege;
                    skriver.print(spesialist.hentKontrollID());
                }
                else {
                    skriver.print(0);
                }
                skriver.println();
            }

            skriver.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
            for (Resept resept : resepter) {
                skriver.print(resept.hentLegemiddel().hentId() + ","
                + resept.hentLege().hentNavn() + ","
                + resept.hentPasient().hentId() + ","
                + resept.hentType());

                if (resept.hentType().equals("p")) {
                    skriver.println();
                }
                else {
                    skriver.println("," + resept.hentReit());
                }
            }

            System.out.println("Din fil er naa skrevet til :)");
            skriver.close();
        }
        catch(IOException e) {
            System.out.println("Kan ikke skrive til fil.");
        }

    }

    // Viser ulik statisikk over legemidler og bruk
    public static void visStatistikk() throws UlovligUtskrift {
        Scanner tastatur = new Scanner(System.in);
        String leggTilMeny = "\nHva vil du se statistikk over?" +
                            "\n1: Utskrevne resepter paa vanedannende legemidler" +
                            "\n2: Utskrevne resepter paa narkotiske legemidler" +
                            "\n3: Leger som har skrevet ut narkotiske legemidler" +
                            "\n4: Pasienter med gyldige resepter paa narkotiske legemidler" +
                            "\n5: Tilbake til hovedmeny" +
                            "\nTast onsket menynummer og avslutt med ENTER." +
                            "\n> ";
        String menyvalg = "";

        while (!menyvalg.equals("5"))    {
            System.out.print(leggTilMeny);
            menyvalg = tastatur.nextLine();
            if (menyvalg.equals("1"))   {skrivAntallVanedannende();}
            else if (menyvalg.equals("2"))   {skrivAntallNarkotiske();}
            else if (menyvalg.equals("3"))   {antallNarkotiskePerLege();}
            else if (menyvalg.equals("4"))   {antallGyldigeNarktoiskePerPasient();}
            else if (menyvalg.equals("5"))   {hovedmeny();}
            else {System.out.println("Feil. Input maa være et tall mellom 1 og 5. Prov igjen. \n");}
        }
    }

    // Henter antall gyldige narkotiske legemidler per pasient
    public static void antallGyldigeNarktoiskePerPasient() {
        for (Pasient pasient : pasienter) {
            if (pasient.antallGyldigeNarktoiske() > 0) {
                System.out.println(pasient.hentNavn() + ", antall gyldige resepter paa narkotiske legemidler: " + pasient.antallGyldigeNarktoiske());
            }
        }
    }

    // Henter antall narkotiske utskrevede resepter for legene
    public static void antallNarkotiskePerLege() {
        for (Lege lege : leger) {
            if (lege.antallNarkotiske() > 0) {
                System.out.println(lege.hentNavn() + ", antall narkotiske legemidler: " + lege.antallNarkotiske());
            }
        }
    }

    // Henter antall vanedannende legemidler i systemet
    public static void skrivAntallVanedannende() {
        int antall = 0;

        for (Resept resept : resepter) {
            Legemiddel legemiddel = resept.hentLegemiddel();
            if (legemiddel instanceof Vanedannende) {
                antall++;
            }
        }

        System.out.println("Totalt antall utskrevne resepter paa vanedannende legemidler: " + antall);
    }

    // Henter antall narkotiske legemidler i systemet
    public static void skrivAntallNarkotiske() {
        int antall = 0;

        for (Resept resept : resepter) {
            Legemiddel legemiddel = resept.hentLegemiddel();
            if (legemiddel instanceof Narkotisk) {
                antall++;
            }
        }

        System.out.println("Totalt antall utskrevne resepter paa narkotiske legemidler: " + antall);
    }

    // Forsøker å bruke en resept for valgt pasient
    public static void brukResept() {
        Scanner tastatur = new Scanner(System.in);

        System.out.println("\nHvilken pasient vil du se resepter for?");
        System.out.println(pasienter);
        int id = 0;
        Pasient pasient = null;

        try {
            id = Integer.parseInt(tastatur.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Feil format");
            return;
        }

        for (Pasient pas : pasienter) {
            if (pas.hentId() == id) {
                pasient = pas;
            }
        }

        if (pasient == null) {
            System.out.println("Fant ikke pasienten");
            return;
        }

        System.out.println("Hvilken resept vil du bruke?");
        System.out.println(pasient.hentReseptStabel());
        int reseptId = 0;
        Resept resept = null;

        try {
            reseptId = Integer.parseInt(tastatur.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Feil format");
            return;
        }

        for (Resept res : pasient.hentReseptStabel()) {
            if (res.hentId() == reseptId) {
                resept = res;
            }
        }

        if (resept == null) {
            System.out.println("Fant ikke resepten");
            return;
        }

        if (resept.bruk()) {
            System.out.println("Brukte resept paa " + resept.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit: "
            + resept.hentReit());
        }
        else {
            System.out.println("Kunne ikke bruke resept paa " + resept.hentLegemiddel().hentNavn()
            + " (ingen gjenvaerende reit).");
        }
    }

    // Undermeny for å legge til et nytt objekt i systemet
    public static void leggTilNy() throws UlovligUtskrift {
        Scanner tastatur = new Scanner(System.in);
        String leggTilMeny = "\nHva vil du legge til?" +
                            "\n1: Lege" +
                            "\n2: Pasient" +
                            "\n3: Legemiddel" +
                            "\n4: Resept" +
                            "\n5: Tilbake til hovedmeny" +
                            "\nTast onsket menynummer og avslutt med ENTER." +
                            "\n> ";
        String menyvalg = "";

        while (!menyvalg.equals("5"))    {
            System.out.print(leggTilMeny);
            menyvalg = tastatur.nextLine();
            if (menyvalg.equals("1"))   {leggTilLege();}
            else if (menyvalg.equals("2"))   {leggTilPasient();}
            else if (menyvalg.equals("3"))   {leggTilLegemiddel();}
            else if (menyvalg.equals("4"))   {leggTilResept();}
            else if (menyvalg.equals("5"))   {hovedmeny();}
            else {System.out.println("Feil. Input maa være et tall mellom 1 og 5. Prov igjen. \n");}
        }
    }

    // Legger til en ny resept, og håndterer unntak
    public static void leggTilResept() throws UlovligUtskrift {
        Scanner tastatur = new Scanner(System.in);
        boolean gyldigLege = false;
        boolean gyldigLegemiddel = false;
        boolean gyldigPasient = false;
        boolean gyldig = false;

        Lege lege = null;
        Legemiddel legemiddel = null;
        Pasient pasient = null;
        int pasientId = 0;
        int legemiddelId = 0;

        while (! gyldigLege) {
            System.out.println("Oppgi navn paa legen: ");
            String legeNavn = tastatur.nextLine();

            for (Lege doc : leger) {
                if (doc.hentNavn().equals(legeNavn)) {
                    lege = doc;
                    gyldigLege = true;
                }
            }
            if (!gyldigLege) {
                System.out.println("Fant ikke legen, prov paa nytt");
            }
        }

        while (! gyldigPasient) {
            System.out.println("Oppgi id paa pasienten: ");

            try {
                pasientId = Integer.parseInt(tastatur.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Ugyldig id");
                return;
            }

            for (Pasient pas : pasienter) {
                if (pas.hentId() == pasientId) {
                    pasient = pas;
                    gyldigPasient = true;
                }
            }
            if (!gyldigPasient) {
                System.out.println("Fant ikke pasienten, prov paa nytt");
            }
        }

        while (! gyldigLegemiddel) {
            System.out.println("Oppgi legemiddel id: ");

            try {
                legemiddelId = Integer.parseInt(tastatur.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Ugyldig id");
                return;
            }

            for (Legemiddel middel : legemidler) {
                if (middel.hentId() == legemiddelId) {
                    legemiddel = middel;
                    gyldigLegemiddel = true;
                }
            }
            if (!gyldigLegemiddel) {
                System.out.println("Fant ikke legemiddelet, prov paa nytt");
            }
        }

        while (! gyldig) {

            System.out.println("Oppgi type paa resept (hvit / blaa / militaer / p-resept): ");
            String type = tastatur.nextLine();

            if (type.equalsIgnoreCase("hvit")) {
                System.out.println("Oppgi antall reit: ");
                int reit = 0;

                try {
                    reit = Integer.parseInt(tastatur.nextLine());
                } catch(NumberFormatException e) {
                    System.out.println("Feil format");
                    return;
                }

                Resept hvit = lege.skrivHvitResept(legemiddel, pasient, reit);
                resepter.leggTil(hvit);
                gyldig = true;
            }

            else if (type.equalsIgnoreCase("militaer")) {
                System.out.println("Oppgi antall reit: ");
                int reit = 0;

                try {
                    reit = Integer.parseInt(tastatur.nextLine());
                } catch(NumberFormatException e) {
                    System.out.println("Feil format");
                    return;
                }

                Resept mili = lege.skrivMilitaerResept(legemiddel, pasient, reit);
                resepter.leggTil(mili);
                gyldig = true;
            }

            else if (type.equalsIgnoreCase("p-resept")) {
                Resept pRes = lege.skrivPResept(legemiddel, pasient);
                resepter.leggTil(pRes);
                gyldig = true;
            }

            else if (type.equalsIgnoreCase("blaa")) {
                System.out.println("Oppgi antall reit: ");
                int reit = 0;

                try {
                    reit = Integer.parseInt(tastatur.nextLine());
                } catch(NumberFormatException e) {
                    System.out.println("Feil format");
                    return;
                }

                Resept blaa = lege.skrivBlaaResept(legemiddel, pasient, reit);
                resepter.leggTil(blaa);
                gyldig = true;
            }
        }
    }

    // Legger til et ny legemiddel, og håndterer unntak
	public static void leggTilLegemiddel() {
		Scanner input = new Scanner(System.in);
        int pris = 0;
        double virkestoff = 0;
        int styrk = 0;

		boolean gyldig = false;
		while (!gyldig) {
			System.out.println("Oppgi type legemiddel (Vanlig/Vanedannende/Narkotisk): ");
			String middelType = input.nextLine();

			System.out.println("Oppgi navn paa legemiddelet: ");
			String navn = input.nextLine();

			System.out.println("Oppgi pris paa legemiddelet: ");
			try {
				pris = Integer.parseInt(input.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Feil format!");
				return;
			}

			System.out.println("Oppgi virkestoff paa legemiddelet: ");
			try {
				virkestoff = Double.parseDouble(input.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Feil format!");
				return;
			}

			if (middelType.equalsIgnoreCase("vanlig")) {
				Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
				legemidler.leggTil(vanlig);
				gyldig = true;

			} else if (middelType.equalsIgnoreCase("vanedannende")) {
				System.out.println("Oppgi styrke paa legemiddelet: ");
				try {
					styrk = Integer.parseInt(input.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Feil format!");
					return;
				}
				Vanedannende vane = new Vanedannende(navn, pris, virkestoff, styrk);
				legemidler.leggTil(vane);
				gyldig = true;

			} else if (middelType.equalsIgnoreCase("Narkotisk")) {
				System.out.println("Oppgi styrke paa legemiddelet: ");
				try {
					styrk = Integer.parseInt(input.nextLine());
				} catch(NumberFormatException e) {
					System.out.println("Feil format!");
					return;
				}
				Narkotisk nark = new Narkotisk(navn, pris, virkestoff, styrk);
				legemidler.leggTil(nark);
				gyldig = true;
			}
		}
	}

    // Legger til en ny lege, og håndterer unntak
	public static void leggTilLege() {
		Scanner input = new Scanner(System.in); //Lager scanner-objekt

        boolean gyldig = false;
        while (!gyldig) {
            System.out.println("Vil du ha lege eller spesialist? ");
            String legeType = input.nextLine(); //Leser input

            System.out.println("Skriv inn navnet paa legen/spesialisten: ");
            String legeNavn = input.nextLine();

    		if (legeType.equalsIgnoreCase("lege")) {
    			Lege lege = new Lege(legeNavn);
                leger.leggTil(lege);
                gyldig = true;
    		}
            else {
    			if (legeType.equalsIgnoreCase("spesialist")) {
    				System.out.println("Skriv inn spesialistens kontrollid: ");
    				String kontrollid = input.nextLine();

    				Spesialist spes = new Spesialist(legeNavn, kontrollid);
                    leger.leggTil(spes);
                    gyldig = true;
    			}
    		}
        }
	}

    // Legger til en ny pasient, og håndterer unntak
    public static void leggTilPasient() {
        Scanner tastatur = new Scanner(System.in);
        boolean gyldig = false;
        String navn = "";
        String foedselsNummer = "";

        while (!gyldig) {
            System.out.println("Oppgi navn paa pasient: ");
            navn = tastatur.nextLine();

            System.out.println("Oppgi foedselsNummer: ");
            foedselsNummer = tastatur.nextLine();

            if (!navn.isEmpty() && !foedselsNummer.isEmpty()) {
                gyldig = true;
            }
            else {
                System.out.println("Prov paa nytt");
            }
        }

        Pasient pasient = new Pasient(navn, foedselsNummer);
        pasienter.leggTil(pasient);
    }

    // Skriver ut en fullstendig oversikt over dataen i systemet
    public static void skrivOversikt() {
        System.out.println("PASIENTER");
        System.out.println(pasienter);

        System.out.println("LEGEMIDLER");
        System.out.println(legemidler);

        System.out.println("LEGER");
        System.out.println(leger);

        System.out.println("\nRESEPTER");
        System.out.println(resepter);
    }

    // Leser inn fil og oppdaterer datastrukturen i systemet
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

                    try {
                        String navn = biter[0];
                        String type = biter[1];
                        int pris = (int) Double.parseDouble(biter[2]);
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
                    }
                    catch(Exception e) {
                        // System.out.println("Feil i format");
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

    // Hjelpemetode for lesInnObjekterFil(), og håndterer reseptene
    public static void skrivResepter(String[] biter) throws UlovligUtskrift {
        try {
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
                    lege = doc;
                }
            }

            for (Legemiddel middel : legemidler) {
                if (middel.hentId() == legemiddelNummer) {
                    legemiddel = middel;
                }
            }

            if (legemiddel != null && lege != null && pasient != null) {

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

        }
        catch(Exception e) {
            // System.out.println("Feil i format");
        }
    }
} // end class Legesystem
