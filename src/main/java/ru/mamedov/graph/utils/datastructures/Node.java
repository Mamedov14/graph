package ru.mamedov.graph.utils.datastructures;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Node {

    private final int key;
    private int x;
    private int y;
    private int pathCost;
    private NodeStatus status;
    private final Set<Edge> edges = new HashSet<>();
    private boolean isStartNode = false;
    private boolean isTargetNode = false;
    private Node pathParent;
    private Color color;

    public Node(Node node) {
        this.key = node.getKey();
        this.x = node.getX();
        this.y = node.getY();
        this.status = node.getStatus();
        this.isStartNode = node.isStartNode();
        this.isTargetNode = node.isTargetNode();
    }

    public Node(int index, int x, int y) {
        this.key = index;
        this.x = x;
        this.y = y;
        this.status = NodeStatus.UNKNOWN;
    }

    public void addEdge(Node destination) {
        Edge edge = new Edge(this, destination);
        edges.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (getKey() != node.getKey()) return false;
        if (getX() != node.getX()) return false;
        return getY() == node.getY();
    }

    @Override
    public int hashCode() {
        int result = getKey();
        result = 31 * result + getX();
        result = 31 * result + getY();
        return result;
    }

    public boolean isStartNode() {
        return isStartNode;
    }

    public void setStartNode(boolean startNode) {
        isStartNode = startNode;
    }

    public void setTargetNode(boolean endNode) {
        isTargetNode = endNode;
    }

    public boolean isTargetNode() {
        return isTargetNode;
    }

    @Override
    public String toString() {
        return "" + key;
    }

    public String toString(int distanceFromTargetNode) {
        return "Node [" + key + "] - Coords: (" + x + "," + y + ") - Path cost: " + getPathCost() + " - Distance from searched: " + distanceFromTargetNode + " - Edges: " + edges + (pathParent != null ? " - Parent: " + pathParent : "");
    }

    public boolean hasColor() {
        return color != null;
    }
}
