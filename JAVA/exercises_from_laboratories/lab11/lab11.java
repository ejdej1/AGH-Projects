
public class lab11 {

    public static void main (String args[]){

        Integer array[] = {1,5,8,4,12,6,77,2,11};
        Integer array4[] = {302,123002,2,100000005,234567,0};
        Double array2[] = {1.23, 2.45, 21.1, 2.5, 0.5};
        String array3[] = {"hel", "hello", "a", "looooooooongText"};

        long var1 = 1000000;
        long var2 = 1000000;

        AddingClass<Integer> test1 = new AddingClass<Integer> (3, 3);
        AddingClass<Double> test5 = new AddingClass<Double> (3.2, 3.2);
        AddingClass<Long> test6 = new AddingClass<Long> (var1, var2);
        System.out.println("");
        System.out.println("---------------");

        BubbleClass<Integer> test2 = new BubbleClass<Integer>(array);
        System.out.println("");
        BubbleClass<Double> test3 = new BubbleClass<Double>(array2);
        System.out.println("");
        BubbleClass<String> test4 = new BubbleClass<String>(array3);
        System.out.println("");
        System.out.println("---------------");

        BubbleClass2<Integer> test7 = new BubbleClass2<Integer>(array4);
        System.out.println("");


    }


}

class AddingClass <T extends Number> {

    private T t;
    private T u;
    private double result;

    AddingClass(T x, T y){ 
        t = x; 
        u = y;  
        result = t.doubleValue() + u.doubleValue();
        
        System.out.println("--> " + t + " + " + u + " = " + result);
    }

    }

        

class BubbleClass <T extends Comparable<T>> {

    private T array1 [];
    private T tmp; 

    BubbleClass(T array []){
        array1 = array;

        //Printing the array
        
        for (int i=0; i<array1.length; i++){
            System.out.print(array1[i] + " ");
        }
        System.out.println(" ");


        for(int i=1; i<array1.length; i++){
            for(int j=0; j<array1.length-i; j++){
                if (array1[j].compareTo(array1[j+1]) > 0) {
                    tmp = array1[j+1];
                    array1[j+1] = array1[j];
                    array1[j] = tmp;
                }
            }
        }

        for (int i=0; i<array1.length; i++){
            System.out.print(array1[i] + " ");
        }
        System.out.println(" ");
    }
}


class BubbleClass2 <T extends Comparable<T> > {

    private T array1 [];
    private T tmp; 
    private String tmp1;
    private String tmp2;
    private String[] tmp1_array;
    private String[] tmp2_array;

    private int tmp1_score;
    private int tmp2_score;

    private double tmp3;
    private double tmp4;

    BubbleClass2(T array []){
        array1 = array;

        //Printing the array
        
        for (int i=0; i<array1.length; i++){
            System.out.print(array1[i] + " ");
        }
        System.out.println(" ");


        for(int i=1; i<array1.length; i++){
            for(int j=0; j<array1.length-i; j++){

                if (array1 instanceof String[] ) { 

                    if (array1[j].compareTo(array1[j+1]) > 0) {
                        tmp = array1[j+1];
                        array1[j+1] = array1[j];
                        array1[j] = tmp;
                    }

                }

                if (array1 instanceof Integer[]){
                    tmp1 = Integer.toString((Integer)array[j]);
                    tmp2 = Integer.toString((Integer)array[j+1]);
                    tmp1_array = tmp1.split("");
                    tmp2_array = tmp2.split("");

                    for (int x=0; x<tmp1_array.length; x++){
                        if (tmp1_array[x].compareTo("0") != 0){
                            tmp1_score +=1;
                        }
                    }

                    for (int x=0; x<tmp2_array.length; x++){
                        if (tmp2_array[x].compareTo("0") != 0){
                            tmp2_score +=1;
                        }
                    }

                   // System.out.print(tmp1_score + " ");

                    if (tmp1_score > tmp2_score) {
                        tmp = array1[j+1];
                        array1[j+1] = array1[j];
                        array1[j] = tmp;
                    }
                    tmp1_score = 0;
                    tmp2_score = 0;
                }

                
        
            }
        }

        for (int i=0; i<array1.length; i++){
            System.out.print(array1[i] + " ");
        }
        System.out.println(" ");
    }
}