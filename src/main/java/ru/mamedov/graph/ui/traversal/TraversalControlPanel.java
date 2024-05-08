package ru.mamedov.graph.ui.traversal;

import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

public class TraversalControlPanel extends GenericControlPanel{

    private static final int DEFAULT_SPEED = 25;
    private static final int DEFAULT_NODES = 100;
    private static final int DEFAULT_EDGES = 10;

    public TraversalControlPanel(Main main, GenericTab genericTab) {
        super(main, genericTab, "Traverse", DEFAULT_EDGES, DEFAULT_NODES, DEFAULT_SPEED);
    }
}
