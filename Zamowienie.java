import java.util.Arrays;

public class Zamowienie {
    private int idZamowienia;
    private int sprzedawcaId;
    private int klientId;
    private long [] listaSprzedanego = new long[30];

    public Zamowienie(int idZamowienia, int sprzedawcaId, int klientId, long[] listaSprzedanego) {
        this.idZamowienia = idZamowienia;
        this.sprzedawcaId = sprzedawcaId;
        this.klientId = klientId;
        this.listaSprzedanego = listaSprzedanego;
    }

    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public int getSprzedawcaId() {
        return sprzedawcaId;
    }

    public void setSprzedawcaId(int sprzedawcaId) {
        this.sprzedawcaId = sprzedawcaId;
    }

    public long getKlientId() {
        return klientId;
    }

    public void setKlientId(int klientId) {
        this.klientId = klientId;
    }

    public long[] getListaSprzedanego() {
        return listaSprzedanego;
    }

    public void setListaSprzedanego(long[] listaSprzedanego) {
        this.listaSprzedanego = listaSprzedanego;
    }

    public String toString(){
        return idZamowienia + "        "+sprzedawcaId+"             "+klientId+"         "+ Arrays.toString(listaSprzedanego);
    }
}
