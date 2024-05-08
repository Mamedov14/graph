package ru.mamedov.graph.algorithms;

import ru.mamedov.graph.utils.datastructures.DisjointSets;
import ru.mamedov.graph.utils.datastructures.Edge;
import ru.mamedov.graph.utils.datastructures.Graph;
import ru.mamedov.graph.utils.datastructures.Node;
import ru.mamedov.graph.utils.datastructures.NodeStatus;
import ru.mamedov.graph.utils.ConsumerWithException;
import ru.mamedov.graph.utils.GraphUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

public class MinimumSpanningTree {

    public static void boruvka(Graph graph, Consumer<Node> onVisitedNode, ConsumerWithException<Edge> onVisitedEdge, ConsumerWithException<Edge> onFoundEdge, Boolean isCanceled) throws Exception {
        DisjointSets sets = new DisjointSets(graph.getNodes());
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        sortedEdges.sort(Comparator.comparingInt(Edge::getCost));
        while (sets.size() > 1) {
            Iterator<DisjointSets.DisjointSet> setsIterator = sets.get().iterator();
            while (setsIterator.hasNext()) {
                TreeSet<Edge> edges = new TreeSet<>();
                DisjointSets.DisjointSet set = setsIterator.next();
                for (Node node : set.getNodes()) {
                    Optional<Edge> closestEdgeForNode = node.getEdges()
                            .stream()
                            .filter(edge -> !set.isNodeInSet(edge.getDestination()))
                            .min(Comparator.comparingInt(Edge::getCost));
                    if (closestEdgeForNode.isPresent()) {
                        onVisitedEdge.accept(closestEdgeForNode.get());
                        edges.add(closestEdgeForNode.get());
                    }
                }
                Iterator<Edge> edgeIterator = edges.iterator();
                if (edgeIterator.hasNext()) {
                    Edge closestEdge = edgeIterator.next();
                    onFoundEdge.accept(closestEdge);
                    sets.merge(closestEdge.getSource(), closestEdge.getDestination(), false);
                    setsIterator.remove();
                }
                if (isCanceled) {
                    return;
                }
            }
        }
    }

    public static void prim(Graph graph, Consumer<Node> onVisitedNode,
                            Consumer<Node> onProcessedNode,
                            ConsumerWithException<Edge> onVisitedEdge,
                            ConsumerWithException<Edge> onFoundEdge,
                            Boolean isCanceled) throws Exception {
        Set<Node> unvisitedNodes = new HashSet<>();
        graph.getNodes().stream().filter(node -> !node.isStartNode()).forEach(unvisitedNodes::add);
        Queue<Edge> availableEdges = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        Node currentNode = GraphUtils.getStartingNode(graph);
        while (!unvisitedNodes.isEmpty()) {
            onVisitedNode.accept(currentNode);
            unvisitedNodes.remove(currentNode);
            currentNode.getEdges().stream()
                    .filter(edge -> unvisitedNodes.contains(edge.getDestination()))
                    .forEach(edge -> {
                        availableEdges.add(edge);
                        try {
                            onVisitedEdge.accept(edge);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
            currentNode.setStatus(NodeStatus.PROCESSED);
            onProcessedNode.accept(currentNode);
            Edge closestEdge = availableEdges.poll();
            if (closestEdge != null) {
                onFoundEdge.accept(closestEdge);
                closestEdge.getDestination().setPathParent(closestEdge.getSource());
                availableEdges.removeIf(edge -> edge.getDestination().equals(closestEdge.getDestination()));
                currentNode = closestEdge.getDestination();
            }
            if (isCanceled) {
                return;
            }
        }
    }

    public static void kruskal(Graph graph,
                               Consumer<Node> onVisitedNode,
                               ConsumerWithException<Edge> onVisitedEdge,
                               ConsumerWithException<Edge> onFoundEdge,
                               Boolean isCanceled) throws Exception {
        DisjointSets sets = new DisjointSets(graph.getNodes());
        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        sortedEdges.sort(Comparator.comparingInt(Edge::getCost));
        for (Edge edge : sortedEdges) {
            onVisitedEdge.accept(edge);
            if (sets.nodesAreInDifferentSets(edge.getSource(), edge.getDestination())) {
                onFoundEdge.accept(edge);
                sets.merge(sets.find(edge.getDestination()), sets.find(edge.getSource()), true);
                edge.getDestination().setPathParent(edge.getSource());
            }
        }
    }
}
