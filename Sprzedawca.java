public class Sprzedawca extends Osoba{

    private int staz;

    public Sprzedawca(int id, String imie, String nazwisko, int wiek,int staz) {
        super(id, imie, nazwisko, wiek);
        this.staz=staz;
    }


    public int getStaz() {
        return staz;
    }

    public void setStaz(int staz) {
        this.staz = staz;
    }

    public String toString(){
        return id + "   "+imie+"    "+nazwisko+"     "+wiek+" "+staz;
    }
}

