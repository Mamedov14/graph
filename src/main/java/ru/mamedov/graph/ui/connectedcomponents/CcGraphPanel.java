package ru.mamedov.graph.ui.connectedcomponents;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.algorithms.ConnectedComponents;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Node;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.ConsumerWithException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class CcGraphPanel extends GenericGraphPanel {

    private GraphCcWorker ccWorker;

    public CcGraphPanel(Algorithm algorithm, GenericTab ccTab, AdjacencyListGraph graph) {
        super(algorithm, ccTab, graph, false);
        this.algorithm = algorithm;
        this.genericTab = ccTab;
        this.graph = graph;
        this.drawWorkingEdges = false;
        this.drawTree = true;
        this.hasSearchedNode = false;
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
                updateGraph();
            };
            if (Objects.requireNonNull(algorithm) == Algorithm.CONNECTED_COMPONENTS_BFS) {
                ConnectedComponents.connectedComponents(graph, visitNode, visitEdge, processNode, isCanceled);
            }
            setProgressBar(0);
            setOperationAsFinished();
            return null;
        }
    }
}
