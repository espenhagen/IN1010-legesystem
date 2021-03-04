public class TestLege {
    public static void main(String[] args) {
        Pasient pasZero = new Pasient("Knut", "12109843423");
        Pasient trine = new Pasient("Trine", "21127112345");

        Legemiddel vanlig = new Vanlig("Vanlig", 150, 1.0);
        Legemiddel nark = new Narkotisk("Narkotisk", 100, 2.0, 50);
        Lege lege = new Lege("Olav");
        Spesialist spesialist = new Spesialist("Espen", "IN1010");

        Militaerresept mili = new Militaerresept(nark, lege, pasZero, 4);
        PResept pRes = new PResept(nark, lege, pasZero);
        BlaaResept blaa = new BlaaResept(vanlig, lege, pasZero, 10);

        try {
            lege.skrivPResept(nark, pasZero);
        }
        catch(UlovligUtskrift e) {
            System.out.println(e);
        }

        try {
            spesialist.skrivBlaaResept(nark, trine, 20);
            spesialist.skrivMilitaerResept(vanlig, pasZero, 3);
        } catch(UlovligUtskrift e) {
            System.out.println(e);
        }

        for (Resept res : spesialist.hentUtskrevedeResepter()) {
            System.out.println(res);
        }
    }
}
