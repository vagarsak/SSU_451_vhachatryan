import java.util.*;

/**
 * Created by ??? on 25.01.2016.
 */
public class Backet implements IBacket {
    Map tovar;

    public Backet() {
        //tovar.clear();
        tovar = new HashMap<String, Integer>();
    }

    public void clear(){
        tovar.clear();
        System.out.println("Cart cleared");
    }

    public void addProduct(String product, int quantity){
        if (!tovar.containsKey(product)){
            tovar.put(product,quantity);
            System.out.println("This product has been successfully added");
        }else{
            tovar.put(product,(int)tovar.get(product) + quantity);
        }
    }

    public void removeProduct(String product){
        if (tovar.containsKey(product)){
            tovar.remove(product);
            System.out.println("Goods removed");
        }else{
            System.out.println("This product is no longer");
        }
    }

    public void updateProductQuantity(String product,int quantity){
        if (tovar.containsKey(product)){
            tovar.put(product,quantity);
            System.out.println("This product has been successfully updated");
        }else{
            System.out.println("This product is no longer");
        }
    }

    public int getProductQuantity(String product){
        if (tovar.containsKey(product)){
            return ((int)tovar.get(product));
        }
        return 0;
    }

    public List<String> getProducts(){
        List<String> list = new ArrayList<String>(tovar.keySet());
        return list;
    }
}
