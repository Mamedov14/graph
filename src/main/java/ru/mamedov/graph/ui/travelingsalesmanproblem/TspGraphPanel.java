package ru.mamedov.graph.ui.travelingsalesmanproblem;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.algorithms.TravelingSalesmanProblem;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.ConsumerWithException;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TspGraphPanel extends GenericGraphPanel {

    private GraphCcWorker ccWorker;

    public TspGraphPanel(Algorithm algorithm, GenericTab ccTab, AdjacencyListGraph graph) {
        super(algorithm, ccTab, graph, false);
        this.algorithm = algorithm;
        this.genericTab = ccTab;
        this.graph = graph;
        drawEdgesWithColorGradient = false;
        drawTree = true;
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(200, 200, 200));
        MouseListener popupListener = new PopupListener();
        this.addMouseListener(popupListener);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void executeStart() {
        visitedEdges = new ArrayList<>();
        visitedNodes = new ArrayList<>();
        processedNodes = new ArrayList<>();
        repaint();
        ccWorker = new GraphCcWorker(visitedNodes, visitedEdges, processedNodes);
        ccWorker.execute();
    }

    public void executeStop() {
        ccWorker.cancel(true);
    }

    class GraphCcWorker extends SwingWorker<Void, Void> {
        List<Node> visitedNodes;
        List<Edge> visitedEdges;
        List<Node> processedNodes;

        public GraphCcWorker(List<Node> visitedNodes, List<Edge> visitedEdges, List<Node> processedNodes) {
            this.visitedNodes = visitedNodes;
            this.visitedEdges = visitedEdges;
            this.processedNodes = processedNodes;
        }

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
                edgesOnPath.add(edge);
                updateGraph();
            };
            ConsumerWithException<Edge> unvisitEdge = edge -> {
                visitedEdges.remove(edge);
                edgesOnPath.remove(edge);
                updateGraph();
            };
            switch (algorithm) {
                case NEAREST_NEIGHBOR_TSP:
                    TravelingSalesmanProblem.nearestNeighbor(graph, unvisitEdge, visitNode, visitEdge, processNode, isCanceled);
                    break;
                case TWO_OPT_TSP:
                    TravelingSalesmanProblem.twoOpt(graph, unvisitEdge, visitNode, visitEdge, processNode, isCanceled);
                    break;
            }
            setProgressBar(0);
            setOperationAsFinished();
            return null;
        }
    }
}
