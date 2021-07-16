public class Films {
    private long idFilmu;
    private  String nazwa;
    private String rezyser;
    private int cena;
    private RodzajFilmu zanrFilmu;

    public Films(long idFilmu, String nazwa, String rezyser, int cena, RodzajFilmu zanrFilmu) {
        this.idFilmu = idFilmu;
        this.nazwa = nazwa;
        this.rezyser = rezyser;
        this.cena = cena;
        this.zanrFilmu = zanrFilmu;
    }

    public long getIdFilmu() {
        return idFilmu;
    }

    public void setIdFilmu(long idFilmu) {
        this.idFilmu = idFilmu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRezyser() {
        return rezyser;
    }

    public void setRezyser(String rezyser) {
        this.rezyser = rezyser;
    }



    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public RodzajFilmu getZanrFilmu() {
        return zanrFilmu;
    }

    public void setZanrFilmu(RodzajFilmu zanrFilmu) {
        this.zanrFilmu = zanrFilmu;
    }

    public String toString(){
        return idFilmu + "   "+nazwa+"   "+rezyser+"   "+cena+"    "+zanrFilmu;
    }
}
