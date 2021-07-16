import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<Films> film = new ArrayList<>();
    static ArrayList<Klient> klient = new ArrayList<>();
    static ArrayList<Sprzedawca> sprzedawca = new ArrayList<>();
    static ArrayList<Zamowienie> order = new ArrayList<>();

    public static void menu() throws FileNotFoundException {

        Scanner s = new Scanner(new File("menu.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();

        list.forEach(System.out::println);

    }

    public static void allData(){

        klient.add(new Klient(1,"Jan","Zelinski",22,"Madryd",345678987));
        klient.add(new Klient(2,"Maciej","Polanski",34,"Gdansk",123456777));
        klient.add(new Klient(3,"Tom","Kwiatkowski",19,"Kyiv",332211445));
        klient.add(new Klient(4,"Marta","Mazur",36,"Minsk",998877661));
        klient.add(new Klient(5,"Agata","Grabowska",27,"Warszawa",100004321));
        klient.add(new Klient(6,"Alicja","Zalewska",31,"Berlin",656578987));

        sprzedawca.add(new Sprzedawca(11,"Tomasz","Nowak",32,5));
        sprzedawca.add(new Sprzedawca(12,"Jan","Kowalski",41,8));
        sprzedawca.add(new Sprzedawca(13,"Anna","Kowal",28,1));
        sprzedawca.add(new Sprzedawca(14,"Kamila","Tomak",39,3));

        film.add(new Films(21,"Interstellar","Nollan",45,RodzajFilmu.fantastyka));
        film.add(new Films(22,"Star wars","Lukas",50,RodzajFilmu.fantastyka));
        film.add(new Films(23,"Apollo 11","Douglas",35,RodzajFilmu.historyczny));
        film.add(new Films(24,"Sherlock Holmes","Werker",40,RodzajFilmu.detektyw));
        film.add(new Films(25,"Deadpool","Miller",30,RodzajFilmu.komedia));
        film.add(new Films(26,"The dig","Tana",25,RodzajFilmu.dramatyczny));

        order.add(new Zamowienie(31,11,3, new long[]{21, 22, 23,24,25}));
        order.add(new Zamowienie(32,12,5, new long[]{24,26,23}));
        order.add(new Zamowienie(33,13,1, new long[]{22,26}));
        order.add(new Zamowienie(34,14,2, new long[]{24,25}));
        order.add(new Zamowienie(35,12,1, new long[]{23}));

    }

    public static void writeAllFilms()  {
        System.out.println("id   Nazwa    Rezyser   Cena   Zanr");
        for(Films d:film) {
            System.out.println(d);
        }
    }

    public static void writeAllWorkers()  {
        System.out.println("id   Imie   Nazwisko  Wiek  Staż");
        for(Sprzedawca d:sprzedawca) {
            System.out.println(d);
        }
    }
    public static void writeAllKlients()  {
        System.out.println("id   Imie    Nazwisko   Wiek  Miasto  Telefon");
        for(Klient d:klient) {
            System.out.println(d);
        }
    }

    public static void writeAllOrders()  {
        System.out.println("id   id_sprzedawcy    id_klienta   id_filmow");
        for(Zamowienie d:order) {
            System.out.println(d);
        }
    }
             //getFilmById pomocnicza metoda dla innych funkcji
    public static Films getFilmById(long id) {
        Films current = null;
        for (Films f : film) {
            if (f.getIdFilmu() == id) {
                current = f;
                break;
            }
        }
        return current;
    }

    public static int getCountOfSoldFilms(){
        int count =0;
        for(Zamowienie order:order){
            count=count+order.getListaSprzedanego().length;
        }
        return count;
    }

    public static double getPriceOfSoldFilmsInOrder(@NotNull Zamowienie zamowienie){
        double price=0;
        for(long idFilmu : zamowienie.getListaSprzedanego()){
            Films f = getFilmById(idFilmu);
            if (f != null){
                price=price+f.getCena();
            }
        }
        return price;
    }

    public static double getAllPriceOfSoldFilms() {
        double cena = 0;
        for (Zamowienie o : order) {
            cena = cena + getPriceOfSoldFilmsInOrder(o);
        }
        return cena;
    }

    public static double Faktura (@NotNull Zamowienie zamowienie){
        double price=0;
        for(long idFilmu : zamowienie.getListaSprzedanego()){
            Films f = getFilmById(idFilmu);
            if (zamowienie.getKlientId()==7){
                price=price+f.getCena();
            }
        }
        return price;
    }
      //pomocnicza metoda dla HashMap
    public static double getPriceOfSoldFilms_ByGenre(Zamowienie order, RodzajFilmu genre) {
        double cena = 0;

        for (long id : order.getListaSprzedanego()) {
            Films film = getFilmById(id);
            if (film != null && film.getZanrFilmu() == genre)
                cena = cena+film.getCena();
        }
        return cena;
    }


    public static HashMap<RodzajFilmu, Double> getPriceOfSoldFilmsByGenre() {

        HashMap<RodzajFilmu, Double> result = new HashMap<>();

        double cenaFant = 0,cenaHist = 0,cenaDet = 0,cenaKom = 0,cenaDram = 0;

        for (Zamowienie or : order) {
            cenaFant = cenaFant + getPriceOfSoldFilms_ByGenre(or, RodzajFilmu.fantastyka);
            cenaHist = cenaHist + getPriceOfSoldFilms_ByGenre(or, RodzajFilmu.historyczny);
            cenaDet = cenaDet + getPriceOfSoldFilms_ByGenre(or, RodzajFilmu.detektyw);
            cenaKom = cenaKom + getPriceOfSoldFilms_ByGenre(or, RodzajFilmu.komedia);
            cenaDram = cenaDram + getPriceOfSoldFilms_ByGenre(or, RodzajFilmu.dramatyczny);
        }

        result.put(RodzajFilmu.fantastyka, cenaFant);
        result.put(RodzajFilmu.historyczny, cenaHist);
        result.put(RodzajFilmu.detektyw, cenaDet);
        result.put(RodzajFilmu.komedia, cenaKom);
        result.put(RodzajFilmu.dramatyczny, cenaDram);

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException, InterruptedException{

        System.out.println("Dzień dobry!");
        System.out.println("Podaj imie");
        Scanner name=new Scanner(System.in);
        String names=name.nextLine();
        System.out.println("Podaj nazwisko");
        Scanner surname=new Scanner(System.in);
        String surnames=surname.nextLine();
        System.out.println("Podaj wiek");
        Scanner wiek=new Scanner(System.in);
        int wieks=wiek.nextInt();
        System.out.println("Podaj miasto");
        Scanner city=new Scanner(System.in);
        String citys=city.nextLine();

        int czyNum=1;
        long numtel=0;
        int wartownik=3;
        while(wartownik>0){
             System.out.println("Podaj numer telefonu (musi byc 9 cyfr)");
             Scanner tel=new Scanner(System.in);
             long tels=tel.nextLong();
            if(tels<100000000 || tels>999999999){
                System.out.println("Podaj poprawny numer telefonu!");
                wartownik=wartownik-1;
                System.out.println("Masz jeszcze "+wartownik+" sproby.");
             }
            else {
                numtel=tels;
               break;
            }
        }
        if (wartownik==0){
            czyNum=0;
            try {
                throw new MyWyjatek("Koniec prób. Przy zakupie filmu faktura zostanie zapisana tylko do pliku.");
            } catch (MyWyjatek myWyjatek) {
                myWyjatek.printStackTrace();
            }
        }

        MyThread watek1=new MyThread();
        watek1.start();

        allData();

        klient.add(new Klient(7,names,surnames,wieks,citys,numtel));

        int neworder=36;
        double ogolnaCena=0;
         menu();
         Scanner x=new Scanner(System.in);
         int licba=x.nextInt();

         while(licba!=0) {
             if (licba==1) {writeAllFilms(); }
             else if (licba==2){writeAllWorkers();}
             else if (licba==3){
                 System.out.println("Możesz zobaczyć imiona klientów, tylko jeżeli znasz hasło. Podaj hasło:");
                 Scanner y=new Scanner(System.in);
                 int pass=y.nextInt();
                 if(pass==99){ writeAllKlients();}
                 else { System.out.println("Hasło niepoprawne!"); }
             }
             else if (licba==4){
                 writeAllOrders();
             }
             else if(licba==5){
                 System.out.println("###  System zamówień  ###");

                 System.out.println("Podaj id sprzedawcy");
                 Scanner spr=new Scanner(System.in);
                 int spredawcas=spr.nextInt();
                 if(spredawcas==11 ||spredawcas==12||spredawcas==13||spredawcas==14){
                     System.out.println("Ile filmow chcecz kupic?");
                     Scanner fil = new Scanner(System.in);
                     int size = fil.nextInt();
                     long array[] = new long[size];
                     System.out.println("Podaj id filmow po kolei, każdy zatwierdzając ENTER");

                     for (int i = 0; i < size; i++) {
                         array[i] = fil.nextInt();
                     }

                     order.add(new Zamowienie(neworder, spredawcas, 7, array));
                     neworder = neworder + 1;

                     MyThread watek=new MyThread();
                     watek.start();


                 }
                 else {
                     System.out.println("Nie ma takiego sprzedawcy");
                 }
             }

             else if(licba==6){
                 int i= order.size();
                 while (i>5){
                 ogolnaCena=ogolnaCena+Faktura(order.get(i-1));
                 i=i-1;
                 }
                 System.out.println("Musisz zaplacic "+ogolnaCena+" zlotych");
             }

             else if (licba==7){
                 System.out.println("Wszystko było sprzedano "+getCountOfSoldFilms()+" filmów");
             }
             else if (licba==8){
                 System.out.println("Podaj id zamowienia");
                 Scanner zamow=new Scanner(System.in);
                 int zamows=zamow.nextInt();
                 System.out.println("Cena zmowienia numer "+zamows+" wynosi "+getPriceOfSoldFilmsInOrder(order.get(zamows-31))+" zlotych");
             }
             else if (licba==9){
                 System.out.println("Wszystko sprzedano filmow na sume "+getAllPriceOfSoldFilms()+" zlotych");
             }

             else if (licba==10){

                 HashMap<RodzajFilmu, Double> soldBooksPrices = getPriceOfSoldFilmsByGenre();
                 for (RodzajFilmu i : soldBooksPrices.keySet()) {
                     System.out.println("Rodzaj filmu: " + i + "\nKoszt sprzedaży: " + soldBooksPrices.get(i)+" zł\n");
                 }
             }


           else {
                 System.out.println("Nie ma takiej opcji wyboru! Proszę spróbować ponownie.");}
             menu();
           licba=x.nextInt();


         }

        Date d=new Date();
         long konto=1122334455;
        PrintWriter zapis = new PrintWriter("Faktura.txt");
        zapis.println("Dziękuję, "+names+", za korzystanie z naszych usług.\n");
        zapis.println("******** FAKTURA ********\n");
        int j= order.size();
        while (j>5){
            ogolnaCena=ogolnaCena+Faktura(order.get(j-1));
            j=j-1;
        }
        int miesiac=d.getMonth()+1;
        long year=d.getYear()+1900;
        zapis.println("Cena: "+ogolnaCena+" zlotych");
        zapis.println("Data transakcji "+d.getDate()+"."+miesiac+"."+year+"r.");
        zapis.println("Proszę wykonać przelew na numer konta "+konto);
        zapis.println("Za kilka minut po przelewie, filmy będą dostepne pod linkiem www.filmy.com");
        zapis.println("Miłego dnia!");
        zapis.close();



        Thread.sleep(2000);
        MyThread watek=new MyThread();
        watek.start();

        System.out.println("\nDziękuję, "+names+", za korzystanie z naszych usług.");
        if(czyNum==0){
            System.out.println("Faktura bedzie zapisana do pliku Faktura.txt");
        }
        else {
            System.out.println("Faktura bedzie zapisana do pliku Faktura.txt oraz wyslana sms-em.");
        }
         Thread.sleep(2000);
        System.out.println("\nSerdecznie zapraszamy ponownie! \nDo widzenia!");

        Thread.sleep(1000);
    }


}

