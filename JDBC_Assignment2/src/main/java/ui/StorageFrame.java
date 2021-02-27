package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ProductDao;
import entity.Product;

/**
 * @ClassName StorageFrame
 * @Description StorageFrame
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
 * @Date 2021/2/10 12:19
 * @Version 1.0
 */
public class StorageFrame extends JFrame {

    private static final long serialVersionUID = -8808883923263763897L;
    private ClientContext clientContext;
    private JScrollPane storagePanel;
    private JTable storageTable;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private JComboBox catCombox;
    private String[] rowname;
    private String[][] data;
    private int currCat = 1;
    private String currOrder = "desc";

    public StorageFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        init();
    }

    public void init() {
        this.setTitle("Inventory inquiry");
        this.setSize(760, 400);
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
        panel.add(createStoragePanel(), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private Component createButtonPanel() {
        JPanel panel = new JPanel();
        catCombox = new JComboBox();
        panel.add(catCombox);
        catCombox.addItem("Drinks");
        catCombox.addItem("Food");
        catCombox.addItem("Wine");
        catCombox.addItem("Cigarette");
        catCombox.addItem("Snacks");
        catCombox.addItem("Household");
        catCombox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.filterCategory();
            }
        });
        JButton ascBtn = new JButton("Asc");
        ascBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.orderStorageData("asc");
            }
        });
        panel.add(ascBtn);
        JButton descBtn = new JButton("Desc");
        descBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.orderStorageData("desc");
            }
        });
        panel.add(descBtn);
        JButton modifyBtn = new JButton("Modify");
        modifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storageTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(StorageFrame.this, "Please select the product to be operated!", "Commodity management", JOptionPane.WARNING_MESSAGE);
                } else {
                    ProductDao dao = new ProductDao();
                    int pid = Integer.parseInt(data[storageTable.getSelectedRow()][0]);
                    Product currProduct = dao.findProduct(pid);
                    clientContext.setCurrProduct(currProduct);
                    clientContext.showOrHideModifyProductFrame(true);
                }
            }
        });
        panel.add(modifyBtn);

        JButton delBtn = new JButton("Delete");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storageTable.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(StorageFrame.this, "Please select the product to be operated!", "Product management", JOptionPane.WARNING_MESSAGE);
                } else {
                    int val = JOptionPane.showConfirmDialog(StorageFrame.this, "Do you want to delete this product?");
                    if (val == JOptionPane.YES_OPTION) {
                        int pid = Integer.parseInt(data[storageTable.getSelectedRow()][0]);
                        JOptionPane.showMessageDialog(StorageFrame.this, "The product has been deleted!", "Product management", JOptionPane.INFORMATION_MESSAGE);
                        clientContext.deleteProduct(pid);
                    }
                }
            }
        });
        panel.add(delBtn);

        JButton okBtn = new JButton("Export");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.export();
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
        String[][] data = getData(null);
        storageTable = new JTable(data, rowNames);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        storageTable.setDefaultRenderer(Object.class, tcr);
        storageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void refreshTableData(String order) {
        currOrder = order;
        DefaultTableModel model = new DefaultTableModel(getData(order), getRowNames());
        storageTable.setModel(model);
        storageTable.repaint();
        storageTable.updateUI();
    }

    public void filterCategory(int cat) {
        this.currCat = cat;
        DefaultTableModel model = new DefaultTableModel(getData(currOrder), getRowNames());
        storageTable.setModel(model);
        storageTable.repaint();
        storageTable.updateUI();
    }

    private String[] getRowNames() {
        if (rowname == null) {
            rowname = new String[]{"ID", "Product Number", "Categories", "Name", "Purchase price", "Price", "Stock", "Last purchase"};
        }
        return rowname;
    }

    private String[][] getData(String order) {
        ProductDao dao = new ProductDao();
        Vector<Product> list;
        if (order == null) {
            list = dao.findProductByCategory(currCat);
        } else {
            list = dao.findProductByCategory(currCat, order);
        }
        String[][] ret = new String[list.size()][8];
        for (int idx = 0; idx < list.size(); idx++) {
            ret[idx][0] = list.get(idx).getProductId() + "";
            ret[idx][1] = list.get(idx).getProductNo();
            int cat = list.get(idx).getCatogery();
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
            ret[idx][2] = catStr;
            ret[idx][3] = list.get(idx).getName();
            ret[idx][4] = list.get(idx).getPurPrice() + "";
            ret[idx][5] = list.get(idx).getPrice() + "";
            ret[idx][6] = list.get(idx).getStorage() + "";
            ret[idx][7] = sdf.format(list.get(idx).getStockDate());
        }
        data = ret;
        return data;
    }

    public static void main(String[] args) {
        StorageFrame mf = new StorageFrame();
        mf.init();
        mf.setVisible(true);
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

    public JComboBox getCatCombox() {
        return catCombox;
    }

    public void setCatCombox(JComboBox catCombox) {
        this.catCombox = catCombox;
    }

    public int getCurrCat() {
        return currCat;
    }

    public void setCurrCat(int currCat) {
        this.currCat = currCat;
    }

    public String getCurrOrder() {
        return currOrder;
    }

    public void setCurrOrder(String currOrder) {
        this.currOrder = currOrder;
    }

}
