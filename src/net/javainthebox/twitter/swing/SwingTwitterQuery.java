package net.javainthebox.twitter.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class SwingTwitterQuery {

    private StatusTableModel model = new StatusTableModel();
    private JTextField field;
    private TwitterQuery twitterQuery = new TwitterQuery();

    public SwingTwitterQuery() {
        JFrame frame = new JFrame("Twitter Query");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel northPane = new JPanel();
        northPane.setPreferredSize(new Dimension(800, 75));
        SpringLayout northLayout = new SpringLayout();
        northPane.setLayout(northLayout);
        frame.add(northPane, BorderLayout.NORTH);

        ImageIcon icon = new ImageIcon(getClass().getResource("search.png"));
        JLabel searchIcon = new JLabel(icon);
        northPane.add(searchIcon);
        searchIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                search();
            }
        });

        field = new JTextField();
        field.addActionListener(e -> search());
        northPane.add(field);

        northLayout.putConstraint(SpringLayout.EAST, northPane, 96, SpringLayout.EAST, field);
        northLayout.putConstraint(SpringLayout.WEST, field, 48, SpringLayout.WEST, northPane);
        northLayout.putConstraint(SpringLayout.NORTH, field, 24, SpringLayout.NORTH, northPane);
        northLayout.putConstraint(SpringLayout.SOUTH, northPane, 24, SpringLayout.SOUTH, field);

        northLayout.putConstraint(SpringLayout.WEST, searchIcon, 4, SpringLayout.EAST, field);
        northLayout.putConstraint(SpringLayout.NORTH, searchIcon, 25, SpringLayout.NORTH, northPane);
        northLayout.putConstraint(SpringLayout.SOUTH, northPane, 25, SpringLayout.SOUTH, searchIcon);

        JPanel centerPane = new JPanel();
        SpringLayout centerLayout = new SpringLayout();
        centerPane.setLayout(centerLayout);
        frame.add(centerPane);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        centerPane.add(scrollPane);
        centerLayout.putConstraint(SpringLayout.EAST, centerPane, 48, SpringLayout.EAST, scrollPane);
        centerLayout.putConstraint(SpringLayout.WEST, scrollPane, 48, SpringLayout.WEST, centerPane);
        centerLayout.putConstraint(SpringLayout.NORTH, scrollPane, 4, SpringLayout.NORTH, centerPane);
        centerLayout.putConstraint(SpringLayout.SOUTH, centerPane, 24, SpringLayout.SOUTH, scrollPane);

        frame.setVisible(true);
    }

    private void search() {
        String keyword = field.getText();

        if (!keyword.isEmpty()) {
            QueryWorker worker = new QueryWorker(model, twitterQuery, keyword);
            worker.execute();
        }
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(SwingTwitterQuery::new);
    }
}
