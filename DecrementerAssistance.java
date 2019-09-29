public class DecrementerAssistance implements Runnable{
    private Counter ct = null;

    public DecrementerAssistance(Counter ct){ this.ct = ct; }
    public void run(){ this.ct.decrement(); }
}