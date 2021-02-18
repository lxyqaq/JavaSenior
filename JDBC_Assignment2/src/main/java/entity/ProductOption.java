package entity;

/**
 * @ClassName ProductOption
 * @Description ProductOption bean
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 20:19
 * @Version 1.0
 */
public class ProductOption {

    private int value;
    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
