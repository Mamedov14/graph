package ru.mamedov.graph.ui.connectedcomponents;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericGraphsContainerPanel;
import ru.mamedov.graph.ui.GenericTab;

import javax.swing.*;

public class CcGraphsContainerPanel extends GenericGraphsContainerPanel {

    public CcGraphsContainerPanel(GenericTab ccTab, GenericControlPanel genericControlPanel) {
        super(ccTab, genericControlPanel);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);
        GenericGraphPanel bfsConnectedComponentsGraph = new CcGraphPanel(Algorithm.CONNECTED_COMPONENTS_BFS, ccTab, new AdjacencyListGraph(graph));
        add(bfsConnectedComponentsGraph);
        sl.putConstraint(SpringLayout.WEST, bfsConnectedComponentsGraph, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, bfsConnectedComponentsGraph, 5, SpringLayout.NORTH, this);
        addGraphPanel(bfsConnectedComponentsGraph);
    }
}
