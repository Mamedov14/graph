package ru.mamedov.graph.ui.travelingsalesmanproblem;

import ru.mamedov.graph.ui.GenericControlPanel;
import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

public class TspControlPanel extends GenericControlPanel{

    private static final int DEFAULT_SPEED = 15;
    private static final int DEFAULT_NODES = 12;
    private static final int DEFAULT_EDGES = 7;

    public TspControlPanel(Main main, GenericTab genericTab) {
        super(main, genericTab, "Find", DEFAULT_EDGES, DEFAULT_NODES, DEFAULT_SPEED);
    }
}
