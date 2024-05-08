package ru.mamedov.graph.ui.search;

import ru.mamedov.graph.algorithms.Algorithm;
import ru.mamedov.graph.algorithms.Search;
import ru.mamedov.graph.ui.GenericGraphPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.utils.Constants;
import ru.mamedov.graph.utils.ConsumerWithException;
import ru.mamedov.graph.utils.datastructures.AdjacencyListGraph;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Node;

import javax.swing.*;
import java.util.function.Consumer;

public class SearchGraphPanel extends GenericGraphPanel {

    private GraphSearchWorker searchWorker;

    public SearchGraphPanel(Algorithm algorithm, GenericTab genericTab, AdjacencyListGraph graph) {
        super(algorithm, genericTab, graph, true);

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
            switch (algorithm) {
                case BFS:
                    Search.bfs(graph, visitNode, visitEdge, processNode, isCanceled, true);
                    break;
                case DFS:
                    Search.dfs(graph, visitNode, visitEdge, processNode, isCanceled, true);
                    break;
            }
            setProgressBar(0);
            setOperationAsFinished();
            return null;
        }
    }
}
