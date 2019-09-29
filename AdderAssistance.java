public class AdderAssistance implements Runnable{
    private Counter ct = null;

    public AdderAssistance(Counter ct){ this.ct = ct; }

    public void run(){ this.ct.increment(); }
}