/**
TestLegemiddel inneholder en rekke metoder som tar imot Legemiddel-objekter og forventede
verdier, for å verifiserer om faktisk reultat, stemmer med forventet resultat.
*/

public class TestLegemiddel {
    public static void main(String[] args) {
        System.out.println("TESTER VANLIG:");

        Vanlig vanlig = new Vanlig("The Usual Stuff", 200, 2.0);

        System.out.println("\nForventet id: 1\nStemmer det?: " + testLegemiddelId(vanlig, 1));
        System.out.println("\nForventet navn: The Usual Stuff\nStemmer det?: " + testLegemiddelNavn(vanlig, "The Usual Stuff"));
        System.out.println("\nForventet pris: 200\nStemmer det?: " + testLegemiddelPris(vanlig, 200));
        System.out.println("\nForventet virkestoff: 2.0\nStemmer det?: " + testLegemiddelVirkestoff(vanlig, 2.0));

        System.out.println("\nTEST AV VANLIG FERDIG, TESTER NARKOTISK:");
 
        Narkotisk nark = new Narkotisk("Drogas", 1000, 5.0, 40);

        System.out.println("\nForventet id: 2\nStemmer det?: " + testLegemiddelId(nark, 2));
        System.out.println("\nForventet navn: Drogas\nStemmer det?: " + testLegemiddelNavn(nark, "Drogas"));
        System.out.println("\nForventet pris: 1000\nStemmer det?: " + testLegemiddelPris(nark, 1000));
        System.out.println("\nForventet virkestoff: 5.0\nStemmer det?: " + testLegemiddelVirkestoff(nark, 5.0));
        System.out.println("\nForventet styrke: 40\nStemmer det?: " + testNarkotiskStyrke(nark, 40));

        System.out.println("\nTEST AV NARKOTISK FERDIG, TESTER VANEDANNENDE:");

        Vanedannende vane = new Vanedannende("Ibux", 90, 1.0, 10);

        System.out.println("\nForventet id: 3\nStemmer det?: " + testLegemiddelId(vane, 3));
        System.out.println("\nForventet navn: Ibux\nStemmer det?: " + testLegemiddelNavn(vane, "Ibux"));
        System.out.println("\nForventet pris: 90\nStemmer det?: " + testLegemiddelPris(vane, 90));
        System.out.println("\nForventet virkestoff: 1.0\nStemmer det?: " + testLegemiddelVirkestoff(vane, 1.0));
        System.out.println("\nForventet styrke: 10\nStemmer det?: " + testVanedannendeStyrke(vane, 10));

        System.out.println("\nTEST FERDIG");
    }

    public static boolean testLegemiddelId(Legemiddel legemiddel, int forventetId){
        return legemiddel.hentId() == forventetId;
    }

    public static boolean testLegemiddelNavn(Legemiddel legemiddel, String navn) {
        return legemiddel.hentNavn().equals(navn);
    }

    public static boolean testLegemiddelPris(Legemiddel legemiddel, int pris) {
        return legemiddel.hentPris() == pris;
    }

    public static boolean testLegemiddelVirkestoff(Legemiddel legemiddel, double virkestoff) {
        return legemiddel.hentVirkestoff() == virkestoff;
    }

    public static boolean testNarkotiskStyrke(Narkotisk narkotisk, int styrke) {
        return narkotisk.hentNarkotiskStyrke() == styrke;
    }

    public static boolean testVanedannendeStyrke(Vanedannende vanedannende, int styrke) {
        return vanedannende.hentVanedannendeStyrke() == styrke;
    }
}
