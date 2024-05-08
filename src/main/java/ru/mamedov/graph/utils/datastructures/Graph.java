package ru.mamedov.graph.utils.datastructures;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public abstract class Graph {

    protected boolean directed;

    public abstract List<Node> getNodes();

    public abstract void addNode(Node node);

    public abstract void removeNode(Node node);

    public abstract void clear();

    public abstract Set<Edge> getEdges();
}
