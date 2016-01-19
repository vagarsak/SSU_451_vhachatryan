import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        pervoe();
       // vtoroe ();
    }


    public static void pervoe (){
        Scanner in = new Scanner(System.in);
        int god =  in.nextInt();
        int  kit = 0;
        if(god >= 1800  && god <= 2017 ){
            Calendar calendar  = Calendar.getInstance();
            calendar.set(Calendar.YEAR,god);
            for (int i = 0; i<12; i++) {
                calendar.set(Calendar.DATE,13);
                calendar.set(Calendar.MONTH,i);
                String s = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
                if(s.equals("Fri")){
                    kit++;
                    System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));
                }
            }
            System.out.println(kit);
        }else {
            System.out.println("YPS");
        }
    }

    public static void vtoroe (){
        Scanner in = new Scanner(System.in);
        int god =  in.nextInt();
        int  kit = 0;
        if(god >= 1  && god <= 12 ){
            Calendar calendar  = Calendar.getInstance();
            calendar.set(Calendar.DATE,13);
            calendar.set(Calendar.MONTH,god-1);
            for (int i = 1800; i<2017; i++) {
                calendar.set(Calendar.YEAR,i);
                String s = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US);
                if(s.equals("Fri") ){
                    kit++;
                    System.out.println(i);
                }
            }
        }else {
            System.out.println("YPS");
        }
    }

}