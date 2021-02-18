package entity;

import java.util.Date;

/**
 * @ClassName StockHistory
 * @Description StockHistory bean
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 22:19
 * @Version 1.0
 */
public class StockHistory {

    private int sid;
    private int productId;
    private Product product;
    private Date stockDate;
    private int quantity;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
