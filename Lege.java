/**
Klassen Lege tar imot en String, navn, i konstruktøren og har en getter-metode, i tillegg til en toString().
*/

public class Lege implements Comparable <Lege> {
    protected String navn;
    protected Lenkeliste <Resept> utskrevedeResepter = new Lenkeliste <>();

    public Lege(String nv) {
        navn = nv;
    }

    public int antallNarkotiske() {
        int antall = 0;

        for (Resept resept : utskrevedeResepter) {
            if (erNarkotisk(resept.hentLegemiddel())) {
                antall++;
            }
        }
        return antall;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (erNarkotisk(legemiddel) && !erSpesialist()) {
            throw new UlovligUtskrift(this, legemiddel, pasient.hentId());
        }

        HvitResept hvit = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(hvit);
        pasient.leggTilResept(hvit);
        return hvit;
    }

    public Militaerresept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (erNarkotisk(legemiddel) && !erSpesialist()) {
            throw new UlovligUtskrift(this, legemiddel, pasient.hentId());
        }

        Militaerresept mili = new Militaerresept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(mili);
        pasient.leggTilResept(mili);
        return mili;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (erNarkotisk(legemiddel) && !erSpesialist()) {
            throw new UlovligUtskrift(this, legemiddel, pasient.hentId());
        }

        PResept pRes = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(pRes);
        pasient.leggTilResept(pRes);
        return pRes;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (erNarkotisk(legemiddel) && !erSpesialist()) {
            throw new UlovligUtskrift(this, legemiddel, pasient.hentId());
        }

        BlaaResept blaa = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(blaa);
        pasient.leggTilResept(blaa);
        return blaa;
    }

    public boolean erSpesialist() {
        return this instanceof Spesialist;
    }

    private boolean erNarkotisk(Legemiddel legemiddel) {
        return legemiddel instanceof Narkotisk;
    }

    public String hentNavn() {
        return navn;
    }

    public Lenkeliste <Resept> hentUtskrevedeResepter() {
        return utskrevedeResepter;
    }

    @Override
    public String toString() {
        return "\n" + navn;
    }

    @Override
    public int compareTo(Lege annen) {
        return navn.compareTo(annen.hentNavn());
    }
}
