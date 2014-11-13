package net.javainthebox.twitter.swing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import twitter4j.Status;

public class StatusTableModel extends AbstractTableModel {

    private List<Status> statuses = new ArrayList<>();

    private final DateFormat formatter = new SimpleDateFormat("YY/MM/dd hh:mm");

    public void clear() {
        int size = statuses.size();
        if (size != 0) {
            statuses = new ArrayList<>();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public void addStatus(Status status) {
        statuses.add(status);
        fireTableRowsInserted(statuses.size() - 1, statuses.size() - 1);
    }

    @Override
    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return "Date";
            case 1:
                return "User";
            case 2:
                return "Text";
            default:
                return "";
        }
    }

    @Override
    public int getRowCount() {
        return statuses.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Status status = statuses.get(rowIndex);

        switch (columnIndex) {
            case 0:
                Date date = status.getCreatedAt();
                return formatter.format(date);
            case 1:
                return "@" + status.getUser().getScreenName();
            case 2:
                return status.getText();
            default:
                return "";
        }
    }
}
