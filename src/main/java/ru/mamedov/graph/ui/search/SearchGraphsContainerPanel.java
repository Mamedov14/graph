package ru.mamedov.graph.ui.search;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericGraphsContainerPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;

import javax.swing.*;

public class SearchGraphsContainerPanel extends GenericGraphsContainerPanel {

    public SearchGraphsContainerPanel(GenericTab searchTab, GenericControlPanel genericControlPanel) {
        super(searchTab, genericControlPanel);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);
        SearchGraphPanel dfsGraph = new SearchGraphPanel(Algorithm.DFS, searchTab, new AdjacencyListGraph(graph));
        SearchGraphPanel bfsGraph = new SearchGraphPanel(Algorithm.BFS, searchTab, new AdjacencyListGraph(graph));
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
