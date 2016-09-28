import java.util.Random;
/**
   Problem 2.1.12 In Algorithms Book by Sedgewick and Wayne
   Uses the shellsort program in the book, modified to print the number
   of compares divided by the array size.
*/
public class ShellSort {

    // This class should not be instantiated.
    private ShellSort() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static Double sort(Comparable[] a) {
        int n = a.length;
        double x = a.length;
        int compares = 0; // <---- Number of compares
        
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < n/3) h = 3*h + 1; 

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                    compares++; // <----- incremented for each compare
                }
            }
            assert isHsorted(a, h); 
            h /= 3;
        }
        assert isSorted(a);
        return compares / x; // <----- returns compares / array size
    }



   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them; 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
              
        int N = 100;
        Double[] a = new Double[N];
        Random rand = new Random();
        
        for( int i = 0; i < N; i++)
        {
           Double n = rand.nextDouble() * 100; 
           a[i] = n;
        }
        /** 
        Prints out the before and after arrays to view
        
        System.out.println("Array NOT Sorted: ");
        for( double r: a)
           System.out.println(r);
        
        System.out.println();
        System.out.println("----------------------");
        System.out.println();
        */
        double test = ShellSort.sort(a);
        System.out.println("Array Sorted: ");
        //show(a);
        System.out.println("Number of compares divided by array size: " + test);
        System.out.println("Int value: " + (int) test);
    }

}