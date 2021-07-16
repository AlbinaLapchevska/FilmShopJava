public class Klient extends Osoba{

private String miasto;
private long telefon;

    public Klient(int id, String imie, String nazwisko, int wiek,String miasto,long telefon) {
        super(id, imie, nazwisko, wiek);

        this.miasto=miasto;
        this.telefon=telefon;
    }


    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }


    public long getTelefon() {
        return telefon;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public String toString(){
        return id + "   "+imie+"   "+nazwisko+"   "+wiek+"   "+miasto+"   "+telefon;
    }
}
