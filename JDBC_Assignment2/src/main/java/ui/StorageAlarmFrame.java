package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import service.StorageAlarmService;

import entity.Product;


public class StorageAlarmFrame extends JFrame {
    private static final long serialVersionUID = -8808883923263763897L;

    private ClientContext clientContext;
    private JScrollPane storagePanel;
    private JTable storageTable;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String[] rowname;
    private String[][] data;

    public StorageAlarmFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        init();
    }

    public void init() {
        this.setTitle("Out of stock reminder");
        this.setSize(400, 320);
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
        JLabel title = new JLabel("The following items are out of stock, please replenish the stock as soon as possible!");
        title.setFont(new Font("微软雅黑", 0, 16));
        title.setForeground(Color.red);
        panel.add(title, BorderLayout.NORTH);
        panel.add(createStoragePanel(), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private Component createButtonPanel() {
        JPanel panel = new JPanel();

        JButton okBtn = new JButton("Close");
        okBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StorageAlarmFrame.this.setVisible(false);
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
        String[][] data = getData();
        storageTable = new JTable(data, rowNames);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        storageTable.setDefaultRenderer(Object.class, tcr);
    }

    public void refreshTableData() {
        DefaultTableModel model = new DefaultTableModel(getData(), getRowNames());
        storageTable.setModel(model);
        storageTable.repaint();
        storageTable.updateUI();
    }

    private String[] getRowNames() {
        if (rowname == null) {
            rowname = new String[]{"Product Number", "Categories", "Name", "Stock", "Last purchase"};
        }
        return rowname;
    }

    private String[][] getData() {
        StorageAlarmService service = new StorageAlarmService();
        Vector<Product> list;
        list = service.findAlarmProduct();
        String[][] ret = new String[list.size()][8];
        for (int idx = 0; idx < list.size(); idx++) {
            ret[idx][0] = list.get(idx).getProductNo();
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
            ret[idx][1] = catStr;
            ret[idx][2] = list.get(idx).getName();
            ret[idx][3] = list.get(idx).getStorage() + "";
            ret[idx][4] = sdf.format(list.get(idx).getStockDate());
        }
        data = ret;
        return data;
    }

    public static void main(String[] args) {
        StorageAlarmFrame mf = new StorageAlarmFrame();
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

}
