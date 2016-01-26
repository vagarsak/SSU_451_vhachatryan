import java.util.*;
public class Main {

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        IBacket basket = new Backet();

        while(1==1){
            System.out.println("enter" +
                    "\n1 - Add" +
                    "\n2 - del" +
                    "\n3 - change" +
                    "\n4 - claer" +
                    "\n5 - Number of return of goods" +
                    "\n6 - All goods");
            Scanner com = new Scanner(System.in);
            String ss = com.next();
            if (!checkString(ss)){
                continue;
            }
            Integer x = Integer.valueOf(ss);
            switch (x) {
                case 1:
                    System.out.println("Enter goods:");
                    String a = com.next();
                    System.out.println("Enter Number:");
                    int zik = com.nextInt();
                    basket.addProduct(a,zik);
                    continue;
                case 2:
                    System.out.println("Enter goods:");
                    String a1 = com.next();
                    basket.removeProduct(a1);
                    continue;
                case 3:
                    System.out.println("Enter goods:");
                    String a2 = com.next();
                    System.out.println("Enter Number:");
                    int zik1 = com.nextInt();
                    basket.updateProductQuantity(a2,zik1);
                    continue;
                case 4:
                    basket.clear();
                    continue;
                case 5:
                    System.out.println("Enter goods:");
                    String a3 = com.next();
                    System.out.println(basket.getProductQuantity(a3));
                    continue;
                case 6:
                    System.out.println(basket.getProducts());
                    continue;
                default: continue;
            }
        }
    }
}
