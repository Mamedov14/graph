package ru.mamedov.graph.ui.travelingsalesmanproblem;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericGraphsContainerPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;

import javax.swing.*;

public class TspGraphsContainerPanel extends GenericGraphsContainerPanel {
    public TspGraphsContainerPanel(GenericTab tspTab, GenericControlPanel genericControlPanel) {
        super(tspTab, genericControlPanel);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);
        GenericGraphPanel nearestNeighbor = new TspGraphPanel(Algorithm.NEAREST_NEIGHBOR_TSP, tspTab, new AdjacencyListGraph(graph));
        GenericGraphPanel twoOpt = new TspGraphPanel(Algorithm.TWO_OPT_TSP, tspTab, new AdjacencyListGraph(graph));
        add(nearestNeighbor);
        addGraphPanel(nearestNeighbor);
        add(twoOpt);
        addGraphPanel(twoOpt);
        sl.putConstraint(SpringLayout.WEST, nearestNeighbor, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, nearestNeighbor, 5, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.WEST, twoOpt, 5, SpringLayout.EAST, nearestNeighbor);
        sl.putConstraint(SpringLayout.NORTH, twoOpt, 5, SpringLayout.NORTH, this);
    }
}
