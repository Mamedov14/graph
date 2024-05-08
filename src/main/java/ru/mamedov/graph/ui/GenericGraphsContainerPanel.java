package ru.mamedov.graph.ui;

import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.utils.Constants;
import ru.mamedov.graph.utils.GraphUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenericGraphsContainerPanel extends JPanel {

    protected AdjacencyListGraph graph;
    public List<GenericGraphPanel> genericGraphPanels;
    private final GenericControlPanel controlPanel;

    public GenericGraphsContainerPanel(GenericTab genericTab, GenericControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        genericGraphPanels = new ArrayList<>();
        graph = GraphUtils.createRandomGraph(controlPanel.getNodesNumber(), controlPanel.getEdgesNumber(), Constants.MAX_NODE_VALUE, controlPanel.getIsDirected());
    }

    public void newGraph() {
        AdjacencyListGraph graph = GraphUtils.createRandomGraph(controlPanel.getNodesNumber(), controlPanel.getEdgesNumber(), Constants.MAX_NODE_VALUE, controlPanel.getIsDirected());
        genericGraphPanels.forEach(panel -> panel.setGraph(new AdjacencyListGraph(graph)));
        genericGraphPanels.forEach(GenericGraphPanel::newGraph);
    }

    public void addGraphPanel(GenericGraphPanel genericGraphPanel) {
        genericGraphPanels.add(genericGraphPanel);
    }

    public void reset() {
        genericGraphPanels.forEach(GenericGraphPanel::reset);
    }

    public void start() {
        genericGraphPanels.forEach(GenericGraphPanel::start);
    }

    public void stop() {
        genericGraphPanels.forEach(GenericGraphPanel::stop);
    }

    public void updateSpeed() { genericGraphPanels.forEach(panel -> panel.setSpeed(controlPanel.getSpeed())); }
}
