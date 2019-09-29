public class ShowingValueAssistance implements Runnable{
    private Counter ct = null;
    
    public ShowingValueAssistance(Counter ct){ this.ct = ct; }
    public void run(){ 
        System.out.println(this.ct.value()); 
    }
}