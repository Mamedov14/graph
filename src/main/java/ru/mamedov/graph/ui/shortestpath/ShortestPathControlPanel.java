package ru.mamedov.graph.ui.shortestpath;

import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

public class ShortestPathControlPanel extends GenericControlPanel {

    private static final int DEFAULT_SPEED = 10;
    private static final int DEFAULT_NODES = 50;
    private static final int DEFAULT_EDGES = 35;

    public ShortestPathControlPanel(Main main, GenericTab genericTab) {
        super(main, genericTab, "Find", DEFAULT_EDGES, DEFAULT_NODES, DEFAULT_SPEED);
    }
}
