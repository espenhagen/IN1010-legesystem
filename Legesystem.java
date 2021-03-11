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
        hovedmeny();
    }

    public static void hovedmeny()  {
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
            else if (input.equals("3"))   {}
            else if (input.equals("4"))   {}
            else if (input.equals("5"))   {}
            else if (input.equals("6"))    {System.exit(0);}
            else {System.out.println("Feil. Input maa vaere et tall mellom 1 og 6. Prov igjen. \n");}

        } //end while
    } // end method hovedmeny()

    public static void leggTilNy() {
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

    public static void leggTilResept() {
        Scanner tastatur = new Scanner(System.in);
        boolean gyldigLege = false;
        boolean gyldigLegemiddel = false;
        boolean gyldigPasient = false;

        Lege lege = null;
        Legemiddel legemiddel = null;
        Pasient pasient = null;
        int pasientId = 0;

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

        // MÅ HENTE LEGEMIDDEL
    }

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
    			if (legeType.equalsIgnoreCase("Spesialist")) {
    				System.out.println("Skriv inn spesialistens kontrollid: ");
    				String kontrollid = input.nextLine();

    				Spesialist spes = new Spesialist(legeNavn, kontrollid);
                    leger.leggTil(spes);
                    gyldig = true;
    			}
    		}
        }
	}

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

    public static void skrivOversikt() {
        System.out.println("PASIENTER");
        System.out.println(pasienter);

        System.out.println("LEGEMIDLER");
        System.out.println(legemidler);

        System.out.println("LEGER");
        System.out.println(leger);

        System.out.println("RESEPTER");
        System.out.println(resepter);
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
