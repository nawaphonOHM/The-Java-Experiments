public class ConcurrentMainTest{
    public static void main(String[] args){
        Counter ct = new Counter();

        (new Thread(new AdderAssistance(ct))).run();
        (new Thread(new DecrementerAssistance(ct))).run();
        (new Thread(new ShowingValueAssistance(ct))).run();
    }
}