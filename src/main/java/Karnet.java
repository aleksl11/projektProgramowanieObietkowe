public class Karnet extends Usluga{
    int id, dlugosc;

    public Karnet(int cena, int id, int dlugosc) {
        super(cena);
        this.id = id;
        this.dlugosc = dlugosc;
    }

    public void opis(){
        System.out.println(id+". "+dlugosc+"dni");
        wypiszCene();
    }
}
