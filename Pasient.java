/**
IN1010 - Oblig 4
Klassen Pasient: Tar inn navn og fødselsnummer. Hver pasient har en unik Id som registreres av klassen.
I tillegg har hver pasient en liste (stabel) over sine resepter
Klassen tilbyr hent-metoder, og kan også legge til Resept-objekter i stabelen.
*/

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

    public int hentId() {
        return pasientId;
    }

    public Stabel<Resept> hentReseptStabel() {
        return reseptStabel;
    }

    public void leggTilResept(Resept resept) {
        reseptStabel.leggPaa(resept);
    }

    @Override
    public String toString() {
        return "\nNavn: " + navn
               + "\nFoedselsnummer: " + foedselsNummer
               + "\nPasient ID: " + pasientId;
    }
}
