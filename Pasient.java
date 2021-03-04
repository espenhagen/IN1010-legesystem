public class Pasient {

    Stabel<Resept> reseptStabel = new Stabel<>();

    protected String navn, foedselsNummer;
    protected int pasientId;
    protected static int teller = 0;

    public Pasient(String nv, String fn) {
        navn = nv;
        foedselsNummer = fn;
        teller ++;
        pasientId = teller;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFoedselsnr() {
        return foedselsNummer;
    }

    public hentId() {
        return pasientId;
    }

    public Stabel<Resept> hentReseptStabel() {
        return reseptStabel;
    }

    public void leggTilResept(Resept resept) {
        reseptStabel.leggPaa(resept);
    }

}
