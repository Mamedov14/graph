package ru.mamedov.graph.ui.shortestpath;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.algorithms.ShortestPath;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Node;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.Constants;
import ru.mamedov.graph.utils.ConsumerWithException;

import javax.swing.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ShortestPathGraphPanel extends GenericGraphPanel {

    private ShortestPathWorker shortestPathWorker;

    public ShortestPathGraphPanel(Algorithm algorithm, GenericTab genericTab, AdjacencyListGraph graph) {
        super(algorithm, genericTab, graph, true);
        this.drawShortestPath = true;
        JMenuItem menuItem = new JMenuItem(Constants.SET_TARGET_NODE_LABEL);
        menuItem.addActionListener(this);
        popupMenu.add(menuItem);
    }

    public void executeStart() {
        bellmanFordStep = 0;
        shortestPathWorker = new ShortestPathWorker();
        shortestPathWorker.execute();
    }


    @Override
    public void reset() {
        super.reset();
        this.drawThinEdges = true;
    }

    @Override
    public void newGraph() {
        super.newGraph();
        this.drawThinEdges = true;
    }

    public void executeStop() {
        shortestPathWorker.cancel(true);
        bellmanFordStep = 0;
    }

    class ShortestPathWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            Boolean isCanceled = Boolean.FALSE;
            ConsumerWithException<Node> visitNode = node -> {
                visitedNodes.add(node);
                setProgressBar((int) ((visitedNodes.size() / (float) graph.getNodes().size()) * 100));
                updateGraph();
            };
            Consumer<Node> processNode = node -> processedNodes.add(node);
            ConsumerWithException<Edge> visitEdge = edge -> {
                visitedEdges.add(edge);
                updateGraph();
            };
            Callable incrementStep = () -> {
                bellmanFordStep++;
                return null;
            };
            switch (algorithm) {
                case DIJKSTRA:
                    ShortestPath.dijkstra(graph, visitNode, visitEdge, processNode, isCanceled);
                    break;
                case BELLMANFORD:
                    ShortestPathGraphPanel.this.drawThinEdges = true;
                    ShortestPath.bellmanFord(graph, visitNode, visitEdge, processNode, incrementStep, isCanceled);
                    ShortestPathGraphPanel.this.drawThinEdges = false;
                    break;
            }
            setProgressBar(0);
            setOperationAsFinished();
            return null;
        }
    }
}
