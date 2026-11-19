package model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    // Dùng description làm khoá đơn giản để định danh sản phẩm trong giỏ hàng
    private Map<String, LineItems> items = new LinkedHashMap<>();

    // Thêm sản phẩm vào giỏ. Nếu đã có thì tăng số lượng lên 1.
    public void addItem(Product product) {
        String key = product.getDescription();
        if (items.containsKey(key)) {
            LineItems item = items.get(key);
            item.setQuantity(item.getQuantity() + 1);
        } else {
            items.put(key, new LineItems(product, 1));
        }
    }

    // Cập nhật số lượng của một sản phẩm. Nếu số lượng <= 0 thì xoá luôn.
    // shadow field "items" kiểu Map ở trên.
    public void updateItem(String description, int quantity) {
        LineItems item = items.get(description);
        if (item != null) {
            if (quantity > 0) {
                item.setQuantity(quantity);
            } else {
                items.remove(description);
            }
        }
    }

    // Xoá một sản phẩm khỏi giỏ hàng
    public void removeItem(String description) {
        items.remove(description);
    }

    public Collection<LineItems> getItems() {
        return items.values();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Tổng tiền toàn bộ giỏ hàng
    public double getTotal() {
        double total = 0;
        for (LineItems item : items.values()) {
            total += item.getTotal();
        }
        return total;
    }
}