public class Drop{
    private String message;
    private boolean empty = true;

    public synchronized String take(){
        while(empty){
            try{
                wait();
            } catch(InterruptedException exception){}
        }

        empty = true;

        notifyAll();
        return message;
    }

    public synchronized void put(String message){
        while(!empty){
            try{
                wait();
            } catch(InterruptedException exception){}
        }

        empty = false;

        this.message = message;
        notifyAll();
    }
}