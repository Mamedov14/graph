package ru.mamedov.graph.ui.minimumspanningtree;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.algorithms.MinimumSpanningTree;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Node;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.Constants;
import ru.mamedov.graph.utils.ConsumerWithException;

import javax.swing.*;
import java.util.function.Consumer;

public class MstGraphPanel extends GenericGraphPanel {

    private GraphSearchWorker searchWorker;

    public MstGraphPanel(Algorithm algorithm, GenericTab genericTab, AdjacencyListGraph graph) {
        super(algorithm, genericTab, graph, true);
        this.drawTree = true;
        this.hasSearchedNode = false;
        JMenuItem menuItem = new JMenuItem(Constants.SET_TARGET_NODE_LABEL);
        menuItem.addActionListener(this);
        popupMenu.add(menuItem);
    }

    public void executeStart() {
        searchWorker = new GraphSearchWorker();
        searchWorker.execute();
    }

    public void executeStop() {
        searchWorker.cancel(true);
    }

    class GraphSearchWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            Boolean isCanceled = Boolean.FALSE;
            Consumer<Node> visitNode = node -> visitedNodes.add(node);
            Consumer<Node> processNode = node -> processedNodes.add(node);
            ConsumerWithException<Edge> visitEdge = edge -> visitedEdges.add(edge);
            ConsumerWithException<Edge> processEdge = edge -> {
                edgesOnPath.add(edge);
                setProgressBar((int) ((edgesOnPath.size() / (float) graph.getNodes().size() - 1) * 100));
                updateGraph();
            };
            switch (algorithm) {
                case BORUVKA:
                    MinimumSpanningTree.boruvka(graph, visitNode, visitEdge, processEdge, isCanceled);
                    break;
                case PRIM:
                    MinimumSpanningTree.prim(graph, visitNode, processNode, visitEdge, processEdge, isCanceled);
                    break;
                case KRUSKAL:
                    MinimumSpanningTree.kruskal(graph, visitNode, visitEdge, processEdge, isCanceled);
                    break;
            }
            setProgressBar(0);
            setOperationAsFinished();
            return null;
        }
    }
}
