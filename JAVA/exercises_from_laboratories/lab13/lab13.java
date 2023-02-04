import java.util.Random;
import java.lang.Thread;

public class lab13 {
        static int total;
    
    public static void main (String args[]){
        final int N = 10000;
        final int K = 10;
        int Bound = 100000;
        
        int [] array = new int[N];
        int [] array2 = new int[K];
        int [] array3 = new int [K];
        Thread [] threads = new Thread[K]; 
        Thread [] threads2 = new Thread[K];
        Random random = new Random();
    
        

        for (int i=0; i<array.length; i++){
            array[i] = random.nextInt(Bound); 
        }
        final long startTimeThreads1 = System.nanoTime();
        //Initializing threads
        for (int i=0; i<K; i++){
            threads[i] = new Thread(new PartialSum(N, K, i, array, array2));
        }
        //Starting threads
        for (int i=0; i<K; i++){
            threads[i].start();
        }

        //Wait for all threads to finish
        for(int i=0; i<K; i++){
            try { 
            threads[i].join();
            }
            catch (InterruptedException e){
                System.out.println("Thread" + i + "terminated");
            }
        }
        //Adding the partialSums
        System.out.println("Adding with threads ");
        totalSum(array2, K);
        final long endTimeThreads1 = System.nanoTime();
        System.out.print("Time: " + (endTimeThreads1 - startTimeThreads1) + " nanoseconds" );

        //Adding the array without threads
        final long startTimeNormal1 = System.nanoTime();
        System.out.println("");
        System.out.println("Adding without threads");
        totalSum(array, N);
        final long endTimeNormal1 = System.nanoTime();
        System.out.print("Time: " + (endTimeNormal1 - startTimeNormal1) + " nanoseconds");

        //Adding the partialSums 2. option
        final long startTimeThreads2 = System.nanoTime();
        //Initializing threads
        for (int i=0; i<K; i++){
            threads2[i] = new Thread(new PartialSum2(N, K, i, array, array2));
        }
        //Starting threads
        for (int i=0; i<K; i++){
            threads2[i].start();
        }

        //Wait for all threads to finish
        for(int i=0; i<K; i++){
            try { 
            threads2[i].join();
            }
            catch (InterruptedException e){
                System.out.println("Thread" + i + "terminated");
            }
        }
        
        System.out.println("Adding with threads option 2 ");
        totalSum(array3, K);
        final long endTimeThreads2 = System.nanoTime();
        System.out.print("Time: " + (endTimeThreads2 - startTimeThreads2) + " nanoseconds" );
    }

    static void totalSum(int partialArray[], int K){
        total = 0;
        for(int i=0; i<K; i++){
            total += partialArray[i];
        }
        System.out.print("Sum: " + total + " ");
    }
}

class PartialSum implements Runnable{

    private int N;
    private int K;
    private int x;
    private int array[];
    private int array2[];
    PartialSum(int N, int K, int x, int array[], int array2[]) {
        this.N = N;
        this.K = K;
        this.x = x;
        this.array = array;
        this.array2 = array2;
    }

    int calculate (int array[], int N, int K, int x){
        int tmp = 0;
        int m = N/K;
        int start = (m*x);
        int end = m*(x+1);

        for (int i=start; i<end; i++){
            tmp += array[i];
        }

        return tmp;
    }

    public void run(){
        try{
                array2[x] = calculate(array, N, K, x);
                Thread.sleep(1);

        } catch (InterruptedException e) {}
        System.out.println("Thread finished");
    }
}

class PartialSum2 extends Thread {
    private int N;
    private int K;
    private int x; 
    private int array[];
    private int array2[];
    PartialSum2(int N, int K, int x, int array[], int array2[]) {
        this.N = N;
        this.K = K;
        this.x = x;
        this.array = array;
        this.array2 = array2;
    }

    int calculate (int array[], int N, int K, int x){
        int tmp = 0;
        int m = N/K;
        int start = (m*x);
        int end = m*(x+1);

        for (int i=start; i<end; i++){
            tmp += array[i];
        }

        return tmp;
    }

    public void run(){
        try{
                array2[x] = calculate(array, N, K, x);
                Thread.sleep(1);

        } catch (InterruptedException e) {}
        System.out.println("Thread finished");
    }

}