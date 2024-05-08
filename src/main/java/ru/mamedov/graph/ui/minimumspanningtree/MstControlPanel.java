package ru.mamedov.graph.ui.minimumspanningtree;

import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

public class MstControlPanel extends GenericControlPanel {

    private static final int DEFAULT_SPEED = 50;
    private static final int DEFAULT_NODES = 50;
    private static final int DEFAULT_EDGES = 8;

    public MstControlPanel(Main main, GenericTab genericTab) {
        super(main, genericTab, "Find", DEFAULT_EDGES, DEFAULT_NODES, DEFAULT_SPEED);
    }
}
