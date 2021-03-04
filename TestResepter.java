/**
TestResepter inneholder to metoder som tar imot Resept-objekter og forventede
verdier, for å verifiserer om faktisk reultat, stemmer med forventet resultat.
*/

public class TestResepter {

    public static void main(String[] args) {

        Legemiddel vanlig = new Vanlig("Vanlig", 150, 1.0);
        Legemiddel nark = new Narkotisk("Narkotisk", 100, 2.0, 50);
        Lege lege = new Lege("Olav");

        System.out.println("\nTESTER HVIT RESEPT:");

        HvitResept hvit = new HvitResept(vanlig, lege, 23, 5);

        System.out.println("\nForventet farge: hvit\nStemmer det?: " + testReseptFarge(hvit, "hvit"));
        System.out.println("\nForventet pris: 150\nStemmer det?: " + testReseptPrisAaBetale(hvit, 150));

        System.out.println("\nTEST AV HVIT RESEPT FERDIG, TESTER MILITAERRESEPT:");

        Militaerresept mili = new Militaerresept(nark, lege, 10, 4);
        System.out.println("\nForventet pris: 0\nStemmer det?: " + testReseptPrisAaBetale(mili, 0));

        System.out.println("\nTEST AV MILITAERRESEPT FERDIG, TESTER P-RESEPT:");

        PResept pRes = new PResept(nark, lege, 5);
        System.out.println("\nForventet pris: 0\nStemmer det?: " + testReseptPrisAaBetale(pRes, 0));

        System.out.println("\nTEST AV P-RESEPT FERDIG, TESTER BLAA RESEPT");

        BlaaResept blaa = new BlaaResept(vanlig, lege, 1, 10);

        System.out.println("\nForventet farge: blaa\nStemmer det?: " + testReseptFarge(blaa, "blaa"));
        System.out.println("\nForventet pris: 38\nStemmer det?: " + testReseptPrisAaBetale(blaa, 38));

        System.out.println("TEST FERDIG");
    }

    public static boolean testReseptFarge(Resept resept, String farge) {
        return resept.farge().equals(farge);
    }

    public static boolean testReseptPrisAaBetale(Resept resept, int pris) {
        return resept.prisAaBetale() == pris;
    }
}
