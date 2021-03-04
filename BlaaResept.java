/**
Klassen arver fra Resept og metoden farge() returnerer "blaa".
Prisen som betales er 25% av full pris, og rundes av til nærmeste heltall,
med hjelp av Math.round().
*/

public class BlaaResept extends Resept {

    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public String farge() {
        return "blaa";
    }

    public int prisAaBetale() {
        return (int) Math.round(legemiddel.hentPris() * 0.25);
    }

    @Override
    public String toString() {
        return "\nType: Blaa resept"
        + "\nFarge: " + farge() + super.toString()
        + "\nPris aa betale: " + prisAaBetale();
    }
}
