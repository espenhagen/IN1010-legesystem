/**
Hovedprogrammet oppretter en instans av hver klasse i systemet og skriver ut
innholdet i hvert objekt. 
*/

public class Hovedprogram {

    public static void main(String[] args) {
        Vanlig vanlig = new Vanlig("Paracet", 100, 2.0);
        Narkotisk narkotisk = new Narkotisk("Ritalin", 200, 5.0, 15);
        Vanedannende vanedannende = new Vanedannende("Nesespray", 150, 1.0, 8);

        Lege lege = new Lege("Dr. Ibux");
        Spesialist spesialist = new Spesialist("Dr. Soda", "AB10");

        HvitResept hvit = new HvitResept(vanlig, lege, 200, 5);
        Militaerresept mili = new Militaerresept(vanlig, lege, 1, 10);
        PResept pRes = new PResept(narkotisk, spesialist, 30);
        BlaaResept blaa = new BlaaResept(vanedannende, spesialist, 98, 8);

        System.out.println(vanlig);
        System.out.println(narkotisk);
        System.out.println(vanedannende);

        System.out.println(lege);
        System.out.println(spesialist);

        System.out.println(hvit);
        System.out.println(mili);
        System.out.println(pRes);
        System.out.println(blaa);
    }
}
