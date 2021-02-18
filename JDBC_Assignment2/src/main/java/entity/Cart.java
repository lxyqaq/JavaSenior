package entity;

/**
 * @ClassName Cart
 * @Description Cart bean
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 17:19
 * @Version 1.0
 */
public class Cart {

    private int seq;
    private Product product;
    private int quantity;
    private double amt;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

}
