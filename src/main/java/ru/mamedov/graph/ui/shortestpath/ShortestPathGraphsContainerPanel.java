package ru.mamedov.graph.ui.shortestpath;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericGraphsContainerPanel;
import ru.mamedov.graph.ui.GenericTab;

import javax.swing.*;

public class ShortestPathGraphsContainerPanel extends GenericGraphsContainerPanel {

    public ShortestPathGraphsContainerPanel(GenericTab shortestPathTab, GenericControlPanel genericControlPanel) {
        super(shortestPathTab, genericControlPanel);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);
        GenericGraphPanel dijkstra = new ShortestPathGraphPanel(Algorithm.DIJKSTRA, shortestPathTab, new AdjacencyListGraph(graph));
        dijkstra.setDrawEdgesWithColorGradient(false);
        dijkstra.setWorkingEdgesWidth(2);
        GenericGraphPanel bellmanFord = new ShortestPathGraphPanel(Algorithm.BELLMANFORD, shortestPathTab, new AdjacencyListGraph(graph));
        bellmanFord.setDrawEdgesWithColorGradient(false);
        bellmanFord.setDrawEdgesWithGrayShade(true);
        bellmanFord.setWorkingEdgesWidth(2);
        add(dijkstra);
        add(bellmanFord);
        addGraphPanel(dijkstra);
        addGraphPanel(bellmanFord);
        sl.putConstraint(SpringLayout.WEST, dijkstra, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, dijkstra, 5, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.WEST, bellmanFord, 5, SpringLayout.EAST, dijkstra);
        sl.putConstraint(SpringLayout.NORTH, bellmanFord, 5, SpringLayout.NORTH, this);
    }
}
