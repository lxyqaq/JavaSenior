package ui;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.*;
import service.StorageAlarmService;
import entity.Cart;
import entity.Product;
import entity.SellHistory;
import entity.StockHistory;

/**
 * @ClassName ClientContext
 * @Description ClientContext
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 22:19
 * @Version 1.0
 */
public class ClientContext {
    private MainFrame mainFrame;
    private LoginFrame loginFrame;
    private ModifyPwdFrame modifyPwdFrame;
    private PurchaseFrame purchaseFrame;
    private NewProductFrame newProductFrame;
    private StorageFrame storageFrame;
    private ModifyProductFrame modifyProductFrame;
    private StockHistoryFrame stockHistoryFrame;
    private StorageAlarmFrame storageAlarmFrame;
    private SellHistoryFrame sellHistoryFrame;
    private CartFrame cartFrame;
    private ProductDao productDao = new ProductDao();
    private SellHistoryDao shDao = new SellHistoryDao();
    private StockHistoryDao stockHistoryDao = new StockHistoryDao();
    private StorageAlarmService storageService = new StorageAlarmService();
    private Product currProduct;
    private List<Cart> sellProducts = new ArrayList<>();
    private double amount = 0;

    public void exit(JFrame frame) {
        int val = JOptionPane.showConfirmDialog(frame, "confirm to close?");
        if (val == JOptionPane.YES_OPTION) {
            frame.setVisible(false);
            System.exit(0);
        }
    }

    public void login() {
        try {
            String pwd = loginFrame.getUserPwd();
            String realPwd = LoginDao.getPwd();
            if (realPwd.equals(pwd.trim())) {
                mainFrame.init();
                mainFrame.setVisible(true);
                this.loginFrame.setVisible(false);
                if (storageService.checkStorage()) {
                    storageAlarmFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(loginFrame, "wrong password", "Login failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(loginFrame, e.getMessage());
        }
    }

    public void showModifyPwdFrame(boolean show) {
        modifyPwdFrame.setVisible(show);
    }

    public void updatePwd() {
        String pwd = new String(modifyPwdFrame.getOrgPwd().getPassword());
        String realPwd = LoginDao.getPwd();
        if (pwd.equals(realPwd)) {
            String np = new String(modifyPwdFrame.getPwdField().getPassword());
            String rp = new String(modifyPwdFrame.getRepeatPwd().getPassword());
            if (np.equals(rp)) {
                LoginDao.updatePwd(np);
                JOptionPane.showMessageDialog(modifyPwdFrame, "Password update successfully!", "Change Password", JOptionPane.INFORMATION_MESSAGE);
                showModifyPwdFrame(false);
            } else {
                JOptionPane.showMessageDialog(modifyPwdFrame, "The two passwords entered are inconsistent!", "Change Password", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(modifyPwdFrame, "The original password was entered incorrectly!", "Change Password", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showOrHidePurchaseFrame(boolean showOrHide) {
        purchaseFrame.setVisible(showOrHide);

    }

    public void showSellFrame() {
        amount = 0;
        cartFrame.refreshTableData(sellProducts);
        cartFrame.setVisible(true);
    }

    public void showOrHideNewProductFrame(boolean showOrHide) {
        if (showOrHide) {
            newProductFrame.getCatCom().setSelectedIndex(0);
            newProductFrame.getAlarmStorageText().setText("");
            newProductFrame.getProductNameText().setText("");
            newProductFrame.getProductNoText().setText("");
            newProductFrame.getStorageText().setText("");
            newProductFrame.getPurchasePriceText().setText("");
            newProductFrame.getPriceText().setText("");
        }
        newProductFrame.setVisible(showOrHide);
    }

    public void saveNewProduct() {
        Product p = new Product();
        p.setName(newProductFrame.getProductNameText().getText());
        p.setAlarmStorage(Integer.valueOf(newProductFrame.getAlarmStorageText().getText()));
        p.setCatogery(newProductFrame.getCatCom().getSelectedIndex() + 1);
        p.setPrice(Double.parseDouble(newProductFrame.getPriceText().getText()));
        p.setProductNo(newProductFrame.getProductNoText().getText());
        p.setPurPrice(Double.parseDouble(newProductFrame.getPurchasePriceText().getText()));
        p.setStorage(Integer.parseInt(newProductFrame.getStorageText().getText()));
        p.setStockDate(new Date());
        productDao.saveProduct(p);
        newProductFrame.setVisible(false);
    }

    public void showOrHideStockHistory(boolean showOrHide) {
        if (showOrHide) {
            stockHistoryFrame.refreshTableData();
        }
        stockHistoryFrame.setVisible(showOrHide);
    }

    public void showOrHideStorageFrame(boolean showOrHide) {
        if (showOrHide) {
            storageFrame.refreshTableData(null);
        }
        storageFrame.setVisible(showOrHide);
    }


    public void filterCategory() {
        int cat = storageFrame.getCatCombox().getSelectedIndex() + 1;
        storageFrame.filterCategory(cat);
    }

    public void orderStorageData(String order) {
        storageFrame.refreshTableData(order);
    }

    public void showOrHideModifyProductFrame(boolean showOrHide) {
        if (showOrHide) {
            modifyProductFrame.getCatCom().setSelectedIndex(0);
            modifyProductFrame.getAlarmStorageText().setText(currProduct.getAlarmStorage() + "");
            modifyProductFrame.getProductNameText().setText(currProduct.getName());
            modifyProductFrame.getProductNoText().setText(currProduct.getProductNo());
            modifyProductFrame.getStorageText().setText(currProduct.getStorage() + "");
            modifyProductFrame.getPurchasePriceText().setText(currProduct.getPurPrice() + "");
            modifyProductFrame.getPriceText().setText(currProduct.getPrice() + "");
        }
        modifyProductFrame.setVisible(showOrHide);
    }

    public void updateProduct() {
        Product p = currProduct;
        p.setName(modifyProductFrame.getProductNameText().getText());
        p.setAlarmStorage(Integer.valueOf(modifyProductFrame.getAlarmStorageText().getText()));
        p.setCatogery(modifyProductFrame.getCatCom().getSelectedIndex() + 1);
        p.setPrice(Double.parseDouble(modifyProductFrame.getPriceText().getText()));
        p.setProductNo(modifyProductFrame.getProductNoText().getText());
        p.setPurPrice(Double.parseDouble(modifyProductFrame.getPurchasePriceText().getText()));
        p.setStorage(Integer.parseInt(modifyProductFrame.getStorageText().getText()));
        productDao.updateProduct(p);
        storageFrame.refreshTableData(null);
        modifyProductFrame.setVisible(false);
        currProduct = null;
    }

    public void deleteProduct(int pid) {
        productDao.deleteProduct(pid);
        storageFrame.refreshTableData(null);
    }

    public void correlationCombox() {
        purchaseFrame.getPurNumbers().setEditable(true);
        purchaseFrame.getOkBtn().setEnabled(true);
        int cat = purchaseFrame.getCatCombox().getSelectedIndex() + 1;
        ComboBoxModel aModel = new DefaultComboBoxModel(purchaseFrame.getOption(cat));
        purchaseFrame.getProductCombox().setModel(aModel);
        if (purchaseFrame.getProductCombox().getValue() == -1) {
            purchaseFrame.getPurNumbers().setEditable(false);
            purchaseFrame.getOkBtn().setEnabled(false);
        } else {
            currProduct = productDao.findProduct(purchaseFrame.getProductCombox().getValue());
        }
    }

    public void setCurrSelectedProduct() {
        currProduct = productDao.findProduct(purchaseFrame.getProductCombox().getValue());
    }

    public void purchase() {
        String txt = purchaseFrame.getPurNumbers().getText();
        if (txt == null || txt.trim().length() == 0) {
            JOptionPane.showMessageDialog(purchaseFrame, "Please enter the quantity of goods!", "Product storage", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Integer num = Integer.valueOf(txt);
        currProduct = productDao.findProduct(purchaseFrame.getProductCombox().getValue());
        purchaseFrame.getPurNumbers().setText("");
        StockHistory sh = new StockHistory();
        sh.setProductId(currProduct.getProductId());
        sh.setQuantity(num);
        stockHistoryDao.saveStockHistory(sh);
        JOptionPane.showMessageDialog(purchaseFrame, "The storage is successful!", "Product storage", JOptionPane.INFORMATION_MESSAGE);
        currProduct = null;
    }

    public void addCart() {
        String qtyTxt = cartFrame.getProductQty().getText();
        if (qtyTxt == null || qtyTxt.trim().length() == 0) {
            JOptionPane.showMessageDialog(cartFrame, "Please enter the quantity of goods!", "Add to cart", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!isNumeric(qtyTxt)) {
            JOptionPane.showMessageDialog(cartFrame, "Please enter the correct quantity of goods!", "Add to cart", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int quantity = Integer.parseInt(qtyTxt);
        Product p = productDao.findProductByNo(cartFrame.getProductNumField().getText());
        if (p != null) {
            if (p.getStorage() < quantity) {
                JOptionPane.showMessageDialog(cartFrame, p.getName() + "Inventory shortage!", "Inventory warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (sellProducts.size() > 0) {
                    boolean exist = false;
                    for (Cart c : sellProducts) {
                        if (c.getProduct().getProductId() == p.getProductId()) {
                            c.setQuantity(c.getQuantity() + quantity);
                            c.setAmt(c.getAmt() + p.getPrice() * quantity);
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        Cart c = new Cart();
                        c.setProduct(p);
                        c.setQuantity(quantity);
                        c.setAmt(p.getPrice() * quantity);
                        sellProducts.add(c);
                    }
                } else {
                    Cart c = new Cart();
                    c.setProduct(p);
                    c.setQuantity(quantity);
                    c.setAmt(p.getPrice() * quantity);
                    sellProducts.add(c);
                }
                cartFrame.refreshTableData(sellProducts);
                cartFrame.getProductNumField().setText("");
                cartFrame.getProductQty().setText("");
            }
        } else {
            JOptionPane.showMessageDialog(cartFrame, "There is no such product, please check the input number!", "input error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void settle() {
        amount = 0;
        for (Cart c : sellProducts) {
            amount += c.getAmt();
            Product p = c.getProduct();
            p.setStorage(p.getStorage() - c.getQuantity());
            SellHistory sh = new SellHistory();
            sh.setProductId(p.getProductId());
            sh.setQuantity(c.getQuantity());
            shDao.saveSellHistory(sh);
        }
        sellProducts.clear();
        JOptionPane.showMessageDialog(cartFrame, "Total Commodity:" + amount, "Merchandising", JOptionPane.INFORMATION_MESSAGE);
        cartFrame.setVisible(false);
    }

    public void showSellHistoryFrame() {
        sellHistoryFrame.refreshTableData();
        sellHistoryFrame.setVisible(true);
    }

    public void writeToFile(ResultSet rs) {
        try{
            System.out.println("In writeToFile");
            FileWriter outputFile = new FileWriter("product.csv");
            PrintWriter printWriter = new PrintWriter(outputFile);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            for(int i=0;i<numColumns;i++){
                printWriter.print(rsmd.getColumnLabel(i+1)+",");
            }
            printWriter.print("\n");
            while(rs.next()){
                for(int i=0;i<numColumns;i++){
                    printWriter.print(rs.getString(i+1)+",");
                }
                printWriter.print("\n");
                printWriter.flush();
            }
            printWriter.close();
        }
        catch(Exception e){e.printStackTrace();}
    }

    public void export() {
        Connection conn = DataBaseUtil.getConnection();
        String sql = "select * from product";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            writeToFile(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public PurchaseFrame getPurchaseFrame() {
        return purchaseFrame;
    }

    public void setPurchaseFrame(PurchaseFrame purchaseFrame) {
        this.purchaseFrame = purchaseFrame;
    }

    public NewProductFrame getNewProductFrame() {
        return newProductFrame;
    }

    public void setNewProductFrame(NewProductFrame newProductFrame) {
        this.newProductFrame = newProductFrame;
    }

    public StorageFrame getStorageFrame() {
        return storageFrame;
    }

    public void setStorageFrame(StorageFrame storageFrame) {
        this.storageFrame = storageFrame;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getCurrProduct() {
        return currProduct;
    }

    public void setCurrProduct(Product currProduct) {
        this.currProduct = currProduct;
    }

    public ModifyProductFrame getModifyProductFrame() {
        return modifyProductFrame;
    }

    public void setModifyProductFrame(ModifyProductFrame modifyProductFrame) {
        this.modifyProductFrame = modifyProductFrame;
    }

    public StockHistoryFrame getStockHistoryFrame() {
        return stockHistoryFrame;
    }

    public void setStockHistoryFrame(StockHistoryFrame stockHistoryFrame) {
        this.stockHistoryFrame = stockHistoryFrame;
    }

    public StorageAlarmFrame getStorageAlarmFrame() {
        return storageAlarmFrame;
    }

    public void setStorageAlarmFrame(StorageAlarmFrame storageAlarmFrame) {
        this.storageAlarmFrame = storageAlarmFrame;
    }

    public SellHistoryFrame getSellHistoryFrame() {
        return sellHistoryFrame;
    }

    public void setSellHistoryFrame(SellHistoryFrame sellHistoryFrame) {
        this.sellHistoryFrame = sellHistoryFrame;
    }

    public ModifyPwdFrame getModifyPwdFrame() {
        return modifyPwdFrame;
    }

    public void setModifyPwdFrame(ModifyPwdFrame modifyPwdFrame) {
        this.modifyPwdFrame = modifyPwdFrame;
    }

    public CartFrame getCartFrame() {
        return cartFrame;
    }

    public void setCartFrame(CartFrame cartFrame) {
        this.cartFrame = cartFrame;
    }

    public List<Cart> getSellProducts() {
        return sellProducts;
    }

    public void setSellProducts(List<Cart> sellProducts) {
        this.sellProducts = sellProducts;
    }

    private boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
