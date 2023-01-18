public class Grupa extends Usluga{
    int id;
    String nazwa, trener;
    int max, uczestnicy;

    public Grupa(int cena, int id, String nazwa, String trener, int max, int uczestnicy) {
        super(cena);
        this.id = id;
        this.nazwa = nazwa;
        this.trener = trener;
        this.max = max;
        this.uczestnicy = uczestnicy;
    }
    public void opis(){
        System.out.println(id+". "+nazwa);
        System.out.println("Trener: "+trener);
        System.out.println("Ilość uczestnikow: "+uczestnicy+"/"+max);
        wypiszCene();
    }
    public boolean pelna(){
        if(max>uczestnicy)return false;
        else return true;
    }
}
