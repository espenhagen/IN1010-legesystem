/**
Klassen Lege tar imot en String, navn, i konstruktøren og har en getter-metode, i tillegg til en toString().
*/

public class Lege {
    String navn;

    public Lege(String nv) {
        navn = nv;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "Legen heter: " + navn + "\n";
    }
}
