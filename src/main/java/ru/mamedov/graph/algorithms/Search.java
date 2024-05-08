package ru.mamedov.graph.algorithms;

import ru.mamedov.graph.utils.ConsumerWithException;
import ru.mamedov.graph.utils.GraphUtils;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Graph;
import ru.mamedov.graph.utils.datastructures.Node;
import ru.mamedov.graph.utils.datastructures.NodeStatus;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Search {

    public static void bfs(Graph graph,
                           ConsumerWithException<Node> onVisitedNode,
                           ConsumerWithException<Edge> onVisitedEdge,
                           Consumer<Node> onProcessedNode,
                           Boolean isCanceled,
                           boolean stopAtSearched) throws Exception {
        genericFirstSearch(graph,
                Deque::add,
                Deque::poll,
                onVisitedNode,
                onVisitedEdge,
                onProcessedNode,
                isCanceled,
                stopAtSearched);
    }

    public static void dfs(Graph graph,
                           ConsumerWithException<Node> onVisitedNode,
                           ConsumerWithException<Edge> onVisitedEdge,
                           Consumer<Node> onProcessedNode,
                           Boolean isCanceled,
                           boolean stopAtSearched) throws Exception {
        genericFirstSearch(graph,
                Deque::push,
                Deque::pop,
                onVisitedNode,
                onVisitedEdge,
                onProcessedNode,
                isCanceled,
                stopAtSearched);
    }

    public static void genericFirstSearch(Graph graph,
                                          BiConsumer<Deque<Node>, Node> nodePutter,
                                          Function<Deque<Node>, Node> nodeGetter,
                                          ConsumerWithException<Node> onVisitedNode,
                                          ConsumerWithException<Edge> onVisitedEdge,
                                          Consumer<Node> onProcessedNode,
                                          Boolean isCanceled,
                                          boolean stopAtSearched) throws Exception {
        graph.getNodes().forEach(node -> node.setStatus(NodeStatus.UNKNOWN));
        Deque<Node> queue = new ArrayDeque<>();
        Node startingNode = GraphUtils.getStartingNode(graph);
        nodePutter.accept(queue, startingNode);
        while (!queue.isEmpty()) {
            Node node = nodeGetter.apply(queue);
            if (stopAtSearched && node.isTargetNode()) {
                return;
            }
            node.setStatus(NodeStatus.DISCOVERED);
            onVisitedNode.accept(node);
            for (Edge edge : node.getEdges()) {
                if ((edge.getDestination()).getStatus() == NodeStatus.UNKNOWN) {
                    nodePutter.accept(queue, edge.getDestination());
                    (edge.getDestination()).setStatus(NodeStatus.DISCOVERED);
                    onVisitedEdge.accept(edge);
                }
            }
            node.setStatus(NodeStatus.PROCESSED);
            onProcessedNode.accept(node);
            if (isCanceled) return;
        }
    }
}
