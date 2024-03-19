/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hieul
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import model.OrderDetailsDAO;
import model.ProductDAO;

/**
 *
 * @author MSI
 */
public class Cart implements Serializable {

    private Map<String, Integer> items;

    /// khong co set vi khong up xot hang loat, them tung item
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addNewQty(String sku, int quantity){
        if (sku == null) {
            return;
        }
        if (sku.trim() == null) {
            return;
        }
        if (quantity == 0) {
            return;
        }
        if (this.items == null) {
            this.items = new HashMap<>();
        } // items chua ton tai
        if (this.items.containsKey(sku)) {
            this.items.put(sku, quantity);
        }
    }

    public void addItemToCart(String sku, int quantity) {
        if (sku == null) {
            return;
        }
        if (sku.trim() == null) {
            return;
        }
        if (quantity == 0) {
            return;
        }
        if (this.items == null) {
            this.items = new HashMap<>();
        } // items chua ton tai
        if (this.items.containsKey(sku)) {
            quantity += this.items.get(sku);
        }

        //update items
        this.items.put(sku, quantity); //put existed key
    }

    public void removeItemFromCart(String sku) {
        if (sku == null) {
            return;
        }
        if (sku.trim() == null) {
            return;
        }
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            if (this.items.isEmpty()) { //huy doi tuong
                this.items = null;
            }
        }
    }

    public void refresh() {
        if (this.items == null) {
            return;
        }
        ProductDAO productdao = new ProductDAO();
        OrderDetailsDAO orderDetailsddao = new OrderDetailsDAO();

        for (String sku : this.items.keySet()) {
            
            int orderedQuantity = orderDetailsddao.getOrderedQuantityOf(sku);
            Product prd = productdao.getProduct(sku);
            int restQuantity = prd.getQuantity() - orderedQuantity;

            int quantityInCart = this.items.get(sku);

            int updateQuantityInCart = Integer.min(restQuantity, quantityInCart);
            //có tác dụng cập nhật số lượng sản phẩm trong giỏ hàng.
            //Trong đó, updateQuantityInCart là biến chứa số lượng sản phẩm cần cập nhật trong giỏ hàng. 
            //Giá trị của updateQuantityInCart sẽ bằng giá trị nhỏ nhất giữa restQuantity (số lượng sản phẩm còn lại) 
            //và quantityInCart (số lượng sản phẩm hiện tại trong giỏ hàng).
            //Nếu số lượng sản phẩm còn lại ít hơn số lượng sản phẩm trong giỏ hàng, 
            //thì số lượng sản phẩm trong giỏ hàng sẽ được cập nhật bằng số lượng sản phẩm còn lại. 
            //Ngược lại, nếu số lượng sản phẩm còn lại nhiều hơn hoặc bằng số lượng sản phẩm trong giỏ hàng, 
            //thì số lượng sản phẩm trong giỏ hàng sẽ không thay đổi.
            if (updateQuantityInCart == 0) { // remove
                this.removeItemFromCart(sku);
            } else { // update
                this.items.put(sku, updateQuantityInCart);
            }
        }
    }

    public Vector<Product> getSelectedProductInCart(String[] selectedItems) {
        if (selectedItems == null) {
            return null;
        }
        if (selectedItems.length == 0) {
            return null;
        }

        if (this.items == null) {
            return null;
        }

        Vector<Product> vector = new Vector<Product>();
        ProductDAO dao = new ProductDAO();

        for (String sku : selectedItems) {
            if (this.items.containsKey(sku) == false) {
                continue;
            }
            Product product = dao.getProduct(sku);

            String name = product.getName();
            String type = product.getType();
            String description = product.getDescription();
            double price = product.getPrice();
            int quantity = this.items.get(sku);

            Product obj = new Product(sku, name, type, description, quantity, price);
            vector.add(obj);
        }
        if (vector.isEmpty()) {
            return null;
        }
        return vector;
    }

    public Vector<Product> getProductInCart() {
        if (this.items == null) {
            return null;
        }

        Vector<Product> vector = new Vector<Product>();
        ProductDAO dao = new ProductDAO();

        for (String sku : this.items.keySet()) {
            Product product = dao.getProduct(sku);

            String name = product.getName();
            String description = product.getDescription();
            double price = product.getPrice();
            int quantity = this.items.get(sku);

            Product obj = new Product(sku, name, name, description, quantity, price);
            vector.add(obj);
        }
        if (vector.isEmpty()) {
            return null;
        }
        return vector;
    }

}
