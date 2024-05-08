package ru.mamedov.graph.ui.connectedcomponents;

import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

public class CcControlPanel extends GenericControlPanel {

    private static final int DEFAULT_SPEED = 10;
    private static final int DEFAULT_NODES = 60;
    private static final int DEFAULT_EDGES = 3;

    public CcControlPanel(Main main, GenericTab genericTab) {
        super(main, genericTab, "Find", DEFAULT_EDGES, DEFAULT_NODES, DEFAULT_SPEED);
    }
}
