package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.StockHistoryDao;
import entity.StockHistory;

public class StockHistoryFrame extends JFrame {
    private static final long serialVersionUID = -8808883923263763897L;

    private ClientContext clientContext;
    private JScrollPane stockHistoryPanel;
    private JTable stockHistoryTable;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String[] rowname;
    private String[][] data;

    public StockHistoryFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        init();
    }

    public void init() {
        this.setTitle("Purchase record query");
        this.setSize(420, 320);
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
        JButton okBtn = new JButton("Export");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockHistoryFrame.this.export();
            }
        });
        panel.add(okBtn);
        return panel;
    }

    private Component createStoragePanel() {
        stockHistoryPanel = new JScrollPane();
        initTableData();
        stockHistoryPanel.setViewportView(stockHistoryTable);
        return stockHistoryPanel;
    }

    public void initTableData() {
        String[] rowNames = getRowNames();
        String[][] data = getTableData();
        stockHistoryTable = new JTable(data, rowNames);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        stockHistoryTable.setDefaultRenderer(Object.class, tcr);
        stockHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stockHistoryTable.isCellEditable(1, 1);
    }

    public void refreshTableData() {
        DefaultTableModel model = new DefaultTableModel(getTableData(), getRowNames());
        stockHistoryTable.setModel(model);
        stockHistoryTable.repaint();
        stockHistoryTable.updateUI();
    }

    private String[] getRowNames() {
        if (rowname == null) {
            rowname = new String[]{"Purchase date", "Categories", "Name", "Purchase quantity"};
        }
        return rowname;
    }

    private String[][] getTableData() {
        StockHistoryDao dao = new StockHistoryDao();
        Vector<StockHistory> list = dao.findAllHistory();
        String[][] ret = new String[list.size()][8];
        for (int idx = 0; idx < list.size(); idx++) {
            ret[idx][0] = sdf.format(list.get(idx).getStockDate());
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
        }
        data = ret;
        return data;
    }

    public void writeCSVfile(JTable table) throws IOException, ClassNotFoundException, SQLException {
        Writer writer = null;
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount();
        int nCol = dtm.getColumnCount();
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("StockHistory.csv"), "utf-8"));

            //write the header information
            StringBuffer bufferHeader = new StringBuffer();
            for (int j = 0; j < nCol; j++) {
                bufferHeader.append(dtm.getColumnName(j));
                if (j!=nCol) bufferHeader.append(", ");
            }
            writer.write(bufferHeader.toString() + "\r\n");

            //write row information
            for (int i = 0 ; i < nRow ; i++){
                StringBuffer buffer = new StringBuffer();
                for (int j = 0 ; j < nCol ; j++){
                    buffer.append(dtm.getValueAt(i,j));
                    if (j!=nCol) buffer.append(", ");
                }
                writer.write(buffer.toString() + "\r\n");
            }
        } finally {
            writer.close();
        }
    }

    public void export() {
        refreshTableData();
        try {
            writeCSVfile(stockHistoryTable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StockHistoryFrame mf = new StockHistoryFrame();
        mf.init();
        mf.setVisible(true);
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public JScrollPane getStockHistoryPanel() {
        return stockHistoryPanel;
    }

    public void setStockHistoryPanel(JScrollPane stockHistoryPanel) {
        this.stockHistoryPanel = stockHistoryPanel;
    }

    public JTable getStockHistoryTable() {
        return stockHistoryTable;
    }

    public void setStockHistoryTable(JTable stockHistoryTable) {
        this.stockHistoryTable = stockHistoryTable;
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
