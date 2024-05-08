package ru.mamedov.graph.ui.search;

import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

public class SearchControlPanel extends GenericControlPanel {

    private static final int DEFAULT_SPEED = 30;
    private static final int DEFAULT_NODES = 150;
    private static final int DEFAULT_EDGES = 8;

    public SearchControlPanel(Main main, GenericTab genericTab) {
        super(main, genericTab, "Search", DEFAULT_EDGES, DEFAULT_NODES, DEFAULT_SPEED);
    }
}
