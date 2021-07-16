public class MyThread  extends Thread{
    public void run(){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Dane zapisują się.Proszę poczekać kilka sekund.");
            System.out.println("W tym czasie można dalej korzystać  naszych uslug :)");
             try {
                 Thread.sleep(4000);
            } catch (InterruptedException e) {
              e.printStackTrace();
              }

    }
}
