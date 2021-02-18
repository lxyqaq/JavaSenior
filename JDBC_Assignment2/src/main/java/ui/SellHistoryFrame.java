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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.SellHistoryDao;
import entity.SellHistory;

/**
 * 销售历史记录窗口
 *
 * @author xujinnan
 */
public class SellHistoryFrame extends JFrame {
    private static final long serialVersionUID = -8808883923263763897L;

    private ClientContext clientContext;
    private JScrollPane sellHistoryPanel;
    private JTable sellHistoryTable;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String[] rowname;
    private String[][] data;

    public SellHistoryFrame() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        init();
    }

    public void init() {
        this.setTitle("Sales record query");
        this.setSize(530, 320);
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
                SellHistoryFrame.this.export();
            }
        });
        panel.add(okBtn);
        return panel;
    }

    private Component createStoragePanel() {
        sellHistoryPanel = new JScrollPane();
        initTableData();
        sellHistoryPanel.setViewportView(sellHistoryTable);
        return sellHistoryPanel;
    }

    public void initTableData() {
        String[] rowNames = getRowNames();
        String[][] data = getTableData();
        sellHistoryTable = new JTable(data, rowNames);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        sellHistoryTable.setDefaultRenderer(Object.class, tcr);
    }

    public void refreshTableData() {
        DefaultTableModel model = new DefaultTableModel(getTableData(), getRowNames());
        sellHistoryTable.setModel(model);
        sellHistoryTable.repaint();
        sellHistoryTable.updateUI();
    }

    private String[] getRowNames() {
        if (rowname == null) {
            rowname = new String[]{"Sale date", "Categories", "Name", "Quantity", "Subtotal"};
        }
        return rowname;
    }

    private String[][] getTableData() {
        SellHistoryDao dao = new SellHistoryDao();
        Vector<SellHistory> list = dao.findAllHistory();
        String[][] ret = new String[list.size()][8];
        for (int idx = 0; idx < list.size(); idx++) {
            ret[idx][0] = sdf.format(list.get(idx).getSellDate());
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
            ret[idx][4] = list.get(idx).getProduct().getPrice() * list.get(idx).getQuantity() + "";
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
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("SellHistory.csv"), "utf-8"));

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
            writeCSVfile(sellHistoryTable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SellHistoryFrame mf = new SellHistoryFrame();
        mf.init();
        mf.setVisible(true);
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public JScrollPane getSellHistoryPanel() {
        return sellHistoryPanel;
    }

    public void setSellHistoryPanel(JScrollPane sellHistoryPanel) {
        this.sellHistoryPanel = sellHistoryPanel;
    }

    public JTable getSellHistoryTable() {
        return sellHistoryTable;
    }

    public void setSellHistoryTable(JTable sellHistoryTable) {
        this.sellHistoryTable = sellHistoryTable;
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
