import java.util.Random;

public class lab13 {
    public static void main (String args[]){
        int N = 10000;
        int Bound = 100000;
        int [] array = new int[N];
        Random random = new Random();
    

        for (int i=0; i<array.length; i++){
            array[i] = random.nextInt(Bound); 
            System.out.println("array: " + array[i]);
        }
    }
}

class PartialSum implements Runnable{

    public void run(){
        try{
            

        } catch(InterruptedException e) {

        }
    }
}