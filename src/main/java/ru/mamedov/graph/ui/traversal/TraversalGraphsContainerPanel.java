package ru.mamedov.graph.ui.traversal;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericGraphsContainerPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;

import javax.swing.*;

public class TraversalGraphsContainerPanel extends GenericGraphsContainerPanel {
    public TraversalGraphsContainerPanel(GenericTab traversalTab, GenericControlPanel genericControlPanel) {
        super(traversalTab, genericControlPanel);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);
        GenericGraphPanel dfsGraph = new TraversalGraphPanel(Algorithm.DFS, traversalTab, new AdjacencyListGraph(graph));
        GenericGraphPanel bfsGraph = new TraversalGraphPanel(Algorithm.BFS, traversalTab, new AdjacencyListGraph(graph));
        add(dfsGraph);
        add(bfsGraph);
        addGraphPanel(dfsGraph);
        addGraphPanel(bfsGraph);
        sl.putConstraint(SpringLayout.WEST, dfsGraph, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, dfsGraph, 5, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.WEST, bfsGraph, 5, SpringLayout.EAST, dfsGraph);
        sl.putConstraint(SpringLayout.NORTH, bfsGraph, 5, SpringLayout.NORTH, this);
    }
}
