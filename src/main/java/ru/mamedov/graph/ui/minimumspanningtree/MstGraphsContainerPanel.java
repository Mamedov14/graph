package ru.mamedov.graph.ui.minimumspanningtree;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericGraphsContainerPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.Constants;
import ru.mamedov.graph.utils.GraphUtils;

import javax.swing.*;

public class MstGraphsContainerPanel extends GenericGraphsContainerPanel {

    public MstGraphsContainerPanel(GenericTab mtsTab, GenericControlPanel genericControlPanel) {
        super(mtsTab, genericControlPanel);
        SpringLayout sl = new SpringLayout();
        setLayout(sl);
        graph = GraphUtils.createRandomGraph(genericControlPanel.getNodesNumber(), genericControlPanel.getEdgesNumber(), Constants.MAX_NODE_VALUE, graph.isDirected());
        MstGraphPanel boruvka = new MstGraphPanel(Algorithm.BORUVKA, mtsTab, new AdjacencyListGraph(graph));
        boruvka.setDrawEdgesWithColorGradient(false);
        boruvka.setWorkingEdgesWidth(2);
        addGraphPanel(boruvka);
        add(boruvka);
        MstGraphPanel prim = new MstGraphPanel(Algorithm.PRIM, mtsTab, new AdjacencyListGraph(graph));
        prim.setDrawEdgesWithColorGradient(false);
        prim.setWorkingEdgesWidth(2);
        addGraphPanel(prim);
        add(prim);
        MstGraphPanel kruskal = new MstGraphPanel(Algorithm.KRUSKAL, mtsTab, new AdjacencyListGraph(graph));
        kruskal.setDrawEdgesWithColorGradient(false);
        kruskal.setWorkingEdgesWidth(2);
        addGraphPanel(kruskal);
        add(kruskal);
        sl.putConstraint(SpringLayout.WEST, boruvka, 5, SpringLayout.WEST, this);
        sl.putConstraint(SpringLayout.NORTH, boruvka, 5, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.WEST, prim, 5, SpringLayout.EAST, boruvka);
        sl.putConstraint(SpringLayout.NORTH, prim, 5, SpringLayout.NORTH, this);
        sl.putConstraint(SpringLayout.WEST, kruskal, 5, SpringLayout.EAST, prim);
        sl.putConstraint(SpringLayout.NORTH, kruskal, 5, SpringLayout.NORTH, this);
    }
}
