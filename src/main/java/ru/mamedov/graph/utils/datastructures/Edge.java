package ru.mamedov.graph.utils.datastructures;

import lombok.Getter;
import lombok.Setter;
import ru.mamedov.graph.utils.GraphUtils;

import java.awt.*;

@Getter
@Setter
public class Edge implements Comparable<Edge> {
    private final Node source;
    private final Node destination;
    private int cost;
    private Color color;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
        this.cost = GraphUtils.getDistance(source, destination);
    }

    public void recomputeCost() {
        this.cost = GraphUtils.getDistance(source, destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (getSource() != null ? !getSource().equals(edge.getSource()) : edge.getSource() != null) return false;
        return getDestination() != null ? getDestination().equals(edge.getDestination()) : edge.getDestination() == null;

    }

    @Override
    public int hashCode() {
        int result = getSource() != null ? getSource().hashCode() : 0;
        result = 31 * result + (getDestination() != null ? getDestination().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + source.getKey() + "] -> [" + destination.getKey() + "]";
    }

    public boolean hasColor() {
        return color != null;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.getCost());
    }
}
