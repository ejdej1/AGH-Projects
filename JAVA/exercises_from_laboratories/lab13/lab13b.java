import java.util.Scanner;

class GetNumbers implements Runnable {
  private int[] array;

  public GetNumbers(int[] array) {
    this.array = array;
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    synchronized (array) {
      System.out.println("Enter " + array.length + " numbers:");
      for (int i = 0; i < array.length; i++) {
        array[i] = scanner.nextInt();
      }
      array.notify();
    }
  }
}

class GetSum implements Runnable {
  private int[] array;

  public GetSum(int[] array) {
    this.array = array;
  }

  public void run() {
    while (true) {
      synchronized (array) {
        try {
          array.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
          sum += array[i];
        }
        System.out.println("The sum is: " + sum);
        for (int i = 0; i < array.length; i++) {
          array[i] = 0;
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

public class lab13b {
  public static void main(String[] args) {
    int[] array = new int[5];
    Thread t1 = new Thread(new GetNumbers(array));
    Thread t2 = new Thread(new GetSum(array));
    t1.start();
    t2.start();
  }
}