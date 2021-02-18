package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ProductDao;
import entity.Cart;
import entity.Product;

/**
 * @ClassName CartFrame
 * @Description Shopping cart page
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 22:19
 * @Version 1.0
 */
public class CartFrame extends JFrame {

    public static void main(String[] args) {
        CartFrame mf = new CartFrame();
        mf.init();
        mf.setVisible(true);
    }

    private static final long serialVersionUID = -8808883923263763897L;

    private ClientContext clientContext;
    private JScrollPane storagePanel;
    private JTable storageTable;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private JTextField productQty;
    private JTextField productNumField;
    private JButton addProductBtn;
    private String[] rowname;
    private String[][] data;

    public CartFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        init();
    }

    public void init() {
        this.setTitle("Basket");
        this.setSize(570, 320);
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createTopPanel(), BorderLayout.NORTH);
        panel.add(createStoragePanel(), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private Component createTopPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Product Number"));
        productNumField = new JTextField(10);
        panel.add(productNumField);
        panel.add(new JLabel("Quantity"));
        productQty = new JTextField(5);
        panel.add(productQty);
        addProductBtn = new JButton("Add");
        panel.add(addProductBtn);
        addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.addCart();
            }
        });
        return panel;
    }

    private Component createButtonPanel() {
        JPanel panel = new JPanel();
        JButton delBtn = new JButton("Delete");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storageTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(CartFrame.this, "Please select the product to be operated!", "Commodity management", JOptionPane.WARNING_MESSAGE);
                } else {
                    int val = JOptionPane.showConfirmDialog(CartFrame.this, "Do you want to delete this product?");
                    if (val == JOptionPane.YES_OPTION) {
                        int deleteRow = storageTable.getSelectedRow();
                        clientContext.getSellProducts().remove(deleteRow);
                        String[][] tmp = new String[data.length - 1][6];
                        for (int i = 0; i < data.length; i++) {
                            if (i == deleteRow) {
                                continue;
                            } else if (i > deleteRow) {
                                for (int j = 0; j < 6; j++) {
                                    tmp[i - 1][j] = data[i][j];
                                }
                            } else {
                                for (int j = 0; j < 6; j++) {
                                    tmp[i][j] = data[i][j];
                                }
                            }
                        }
                        data = tmp;
                        DefaultTableModel model = new DefaultTableModel(data, getRowNames());
                        storageTable.setModel(model);
                        storageTable.repaint();
                        storageTable.updateUI();
                    }
                }
            }
        });
        panel.add(delBtn);

        JButton settleBtn = new JButton("Checkout");
        settleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.settle();
            }
        });
        panel.add(settleBtn);

        JButton okBtn = new JButton("Close");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CartFrame.this.setVisible(false);
            }
        });
        panel.add(okBtn);
        return panel;
    }

    private Component createStoragePanel() {
        storagePanel = new JScrollPane();
        initTableData();
        storagePanel.setViewportView(storageTable);
        return storagePanel;
    }

    public void initTableData() {
        String[] rowNames = getRowNames();
        String[][] data = getData(new Vector<Cart>());
        storageTable = new JTable(data, rowNames);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        storageTable.setDefaultRenderer(Object.class, tcr);
        storageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void refreshTableData(List<Cart> list) {
        Vector<Cart> v = new Vector<>();
        for (Cart cart : list) {
            v.add(cart);
        }
        DefaultTableModel model = new DefaultTableModel(getData(v), getRowNames());
        storageTable.setModel(model);
        storageTable.repaint();
        storageTable.updateUI();
    }

    private String[] getRowNames() {
        if (rowname == null) {
            rowname = new String[]{"Product Number", "Categories", "Name", "Quantity", "Price", "Subtotal"};
        }
        return rowname;
    }

    private String[][] getData(Vector<Cart> list) {
        String[][] ret = new String[list.size()][6];
        for (int idx = 0; idx < list.size(); idx++) {
            ret[idx][0] = list.get(idx).getProduct().getProductNo();
            int cat = list.get(idx).getProduct().getCatogery();
            String catStr = "";
            switch (cat) {
                case 1:
                    catStr = "Drinks";
                    break;
                case 2:
                    catStr = "Food";
                    break;
                case 3:
                    catStr = "Wine";
                    break;
                case 4:
                    catStr = "Cigarette";
                    break;
                case 5:
                    catStr = "Snacks";
                    break;
                case 6:
                    catStr = "Household";
                    break;
            }
            ret[idx][1] = catStr;
            ret[idx][2] = list.get(idx).getProduct().getName();
            ret[idx][3] = list.get(idx).getQuantity() + "";
            ret[idx][4] = list.get(idx).getProduct().getPrice() + "";
            ret[idx][5] = list.get(idx).getAmt() + "";
        }
        data = ret;
        return data;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public JScrollPane getStoragePanel() {
        return storagePanel;
    }

    public void setStoragePanel(JScrollPane storagePanel) {
        this.storagePanel = storagePanel;
    }

    public JTable getStorageTable() {
        return storageTable;
    }

    public void setStorageTable(JTable storageTable) {
        this.storageTable = storageTable;
    }

    public JTable getScoreTable() {
        return storageTable;
    }

    public void setScoreTable(JTable scoreTable) {
        this.storageTable = scoreTable;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public JTextField getProductQty() {
        return productQty;
    }

    public void setProductQty(JTextField productQty) {
        this.productQty = productQty;
    }

    public JTextField getProductNumField() {
        return productNumField;
    }

    public void setProductNumField(JTextField productNumField) {
        this.productNumField = productNumField;
    }

    public JButton getAddProductBtn() {
        return addProductBtn;
    }

    public void setAddProductBtn(JButton addProductBtn) {
        this.addProductBtn = addProductBtn;
    }

    public String[] getRowname() {
        return rowname;
    }

    public void setRowname(String[] rowname) {
        this.rowname = rowname;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

}
