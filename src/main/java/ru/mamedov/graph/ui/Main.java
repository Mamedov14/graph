package ru.mamedov.graph.ui;

import ru.mamedov.graph.ui.connectedcomponents.CcTab;
import ru.mamedov.graph.ui.minimumspanningtree.MstTab;
import ru.mamedov.graph.ui.search.SearchTab;
import ru.mamedov.graph.ui.shortestpath.ShortestPathTab;
import ru.mamedov.graph.ui.travelingsalesmanproblem.TspTab;
import ru.mamedov.graph.ui.traversal.TraversalTab;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Main extends JFrame {

    private JLabel statusBar;
    private JProgressBar progressBar;

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("ru.mamedov.graph");
        setSize(1000, 510);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Traversal", new TraversalTab(this));
        tabbedPane.addTab("Search", new SearchTab(this));
        tabbedPane.addTab("Shortest Path", new ShortestPathTab(this));
        tabbedPane.addTab("Minimum Spanning Tree", new MstTab(this));
        tabbedPane.addTab("Connected Components", new CcTab(this));
        tabbedPane.addTab("Traveling Salesman Problem", new TspTab(this));
        add(tabbedPane);
        add(createStatusPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createStatusPanel() {
        statusBar = new JLabel("Ready");
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusBar, BorderLayout.SOUTH);
        JPanel statusPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        progressBar = new JProgressBar(0, 100);
        progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        progressBar.setValue(0);
        c.gridx = 0;
        c.weightx = 1;
        statusPanel.add(statusBar, c);
        c.gridx = 10;
        c.weightx = 0;
        statusPanel.add(progressBar, c);
        return statusPanel;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void setProgressBar(int value) {
        if (progressBar.getValue() < value || value == 0) {
            progressBar.setValue(value);
            repaint();
        }
    }

    public void updateStatusBar(String message) {
        statusBar.setText(message);
        repaint();
    }

    public void setStatusBarMessage(String message) {
        statusBar.setText(message);
    }
}
