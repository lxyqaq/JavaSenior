package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProductDao;
import entity.Product;
import entity.ProductOption;

/**
 * @ClassName PurchaseFrame
 * @Description PurchaseFrame
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 22:19
 * @Version 1.0
 */
public class PurchaseFrame extends JFrame {

    private static final long serialVersionUID = 3743009130266090272L;
    private JTextField purNumbers;
    private ClientContext clientContext;
    private JComboBox catCombox;
    private ProductComboBox productCombox;
    private JButton okBtn;

    public PurchaseFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        init();
    }

    private void init() {
        this.setTitle("Purchase management");
        this.setSize(500, 320);
        this.setContentPane(createContentPane());
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
        this.setResizable(false);
    }

    private Container createContentPane() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 4));

        JLabel top = new JLabel("Purchase information registration");
        JPanel jp1 = new JPanel();
        jp1.add(top);
        mainPanel.add(jp1);

        JLabel category = new JLabel("Categories");

        catCombox = new JComboBox();
        catCombox.addItem("Drinks");
        catCombox.addItem("Food");
        catCombox.addItem("Wine");
        catCombox.addItem("Cigarette");
        catCombox.addItem("Snacks");
        catCombox.addItem("Household");
        catCombox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.correlationCombox();
            }
        });
        JPanel jp2 = new JPanel();
        jp2.add(category);
        jp2.add(catCombox);
        mainPanel.add(jp2);

        productCombox = new ProductComboBox(getOption(1));
        productCombox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.setCurrSelectedProduct();
            }
        });
        JLabel name = new JLabel("Name");
        JPanel jp3 = new JPanel();
        jp3.add(name);
        jp3.add(productCombox);
        mainPanel.add(jp3);

        JPanel jp7 = new JPanel();
        mainPanel.setVisible(true);
        mainPanel.add(jp7);

        JLabel number = new JLabel("Please enter the quantity");
        purNumbers = new JTextField(12);
        JPanel jp4 = new JPanel();
        jp4.add(number);
        jp4.add(purNumbers);
        mainPanel.add(jp4);

        JPanel jp5 = new JPanel();
        mainPanel.add(jp5);

        okBtn = new JButton("Yes");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.purchase();
            }
        });

        JButton cancelBtn = new JButton("No");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.showOrHidePurchaseFrame(false);
            }
        });

        JButton addBtn = new JButton("Add product");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.showOrHideNewProductFrame(true);
            }
        });

        JButton stockHistoryBtn = new JButton("Purchase record");
        stockHistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.showOrHideStockHistory(true);
            }
        });
        JPanel jp6 = new JPanel();
        jp6.add(okBtn);
        jp6.add(cancelBtn);
        jp6.add(addBtn);
        jp6.add(stockHistoryBtn);
        mainPanel.add(jp6);
        return mainPanel;
    }

    public Vector<ProductOption> getOption(int category) {
        ProductDao dao = new ProductDao();
        Vector<ProductOption> ret = new Vector<ProductOption>();
        Vector<Product> ps = dao.findProductByCategory(category);
        if (ps == null || ps.size() == 0) {
            ProductOption po = new ProductOption();
            po.setValue(-1);
            po.setText("There are no products in this category");
            ret.add(po);
        }
        for (Product p : ps) {
            ProductOption po = new ProductOption();
            po.setValue(p.getProductId());
            po.setText(p.getName());
            ret.add(po);
        }
        return ret;
    }

    public static void main(String[] args) {
        PurchaseFrame pf = new PurchaseFrame();
        pf.init();
        pf.setVisible(true);
    }

    public JTextField getPurNumbers() {
        return purNumbers;
    }

    public void setPurNumbers(JTextField purNumbers) {
        this.purNumbers = purNumbers;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public JComboBox getCatCombox() {
        return catCombox;
    }

    public void setCatCombox(JComboBox catCombox) {
        this.catCombox = catCombox;
    }

    public ProductComboBox getProductCombox() {
        return productCombox;
    }

    public void setProductCombox(ProductComboBox productCombox) {
        this.productCombox = productCombox;
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public void setOkBtn(JButton okBtn) {
        this.okBtn = okBtn;
    }

}
