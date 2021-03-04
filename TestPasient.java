public class TestPasient {
    public static void main(String[] args) {

        Pasient pasZero = new Pasient("Knut", "12109843423");
        Pasient trine = new Pasient("Trine", "21127112345");

        Legemiddel vanlig = new Vanlig("Vanlig", 150, 1.0);
        Legemiddel nark = new Narkotisk("Narkotisk", 100, 2.0, 50);
        Lege lege = new Lege("Olav");

        Militaerresept mili = new Militaerresept(nark, lege, pasZero, 4);
        PResept pRes = new PResept(nark, lege, pasZero);
        BlaaResept blaa = new BlaaResept(vanlig, lege, pasZero, 10);

        System.out.println("TESTER PASIENT");

        pasZero.leggTilResept(mili);
        pasZero.leggTilResept(pRes);
        pasZero.leggTilResept(blaa);

        System.out.println(pasZero);
        System.out.println();
        System.out.println(trine);
        System.out.println("\n" + pasZero.hentReseptStabel());
    }
}
