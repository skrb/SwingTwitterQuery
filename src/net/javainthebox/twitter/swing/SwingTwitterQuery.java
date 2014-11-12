package net.javainthebox.twitter.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class SwingTwitterQuery {
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
        JTextField field = new JTextField();
        northPane.add(field);

        northLayout.putConstraint(SpringLayout.EAST, northPane, 48, SpringLayout.EAST, field);
        northLayout.putConstraint(SpringLayout.WEST, field, 48, SpringLayout.WEST, northPane);
        northLayout.putConstraint(SpringLayout.NORTH, field, 24, SpringLayout.NORTH, northPane);
        northLayout.putConstraint(SpringLayout.SOUTH, northPane, 24, SpringLayout.SOUTH, field);

        northLayout.putConstraint(SpringLayout.WEST, searchIcon, -28, SpringLayout.EAST, field);
        northLayout.putConstraint(SpringLayout.NORTH, searchIcon, 25, SpringLayout.NORTH, northPane);
        northLayout.putConstraint(SpringLayout.SOUTH, northPane, 25, SpringLayout.SOUTH, searchIcon);
        
        
        JTable table = new JTable();
        frame.add(table);
        
        frame.setVisible(true);
    }
    
    public static void main(String... args) {
        SwingUtilities.invokeLater(SwingTwitterQuery::new);
    }
}
