/**
PResept arver fra HvitResept, og har alltid 3 reit til å begynne med.
Prisen som betales trekkes fra 108 kroner, men prisen blir aldri mindre enn 0.
*/

public class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pas) {
        super(legemiddel, utskrivendeLege, pas, 3);
    }

    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() - 108 < 0) {
            return 0;
        }
        return legemiddel.hentPris() - 108;
    }

    @Override
    public String toString() {
        return "\nType: P-Resept" + super.toString();
    }
}
