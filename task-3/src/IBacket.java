import java.util.List;

/**
 * Created by HachatryanVA on 26.01.2016.
 */
public interface IBacket {

    void clear();
    void addProduct(String product, int quantity);
    void removeProduct(String product);
    void updateProductQuantity(String product,int quantity);
    int getProductQuantity(String product);
    List<String> getProducts();
}
