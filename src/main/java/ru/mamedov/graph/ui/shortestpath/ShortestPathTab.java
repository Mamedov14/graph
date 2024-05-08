package ru.mamedov.graph.ui.shortestpath;

import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

import javax.swing.*;
import java.awt.*;

public class ShortestPathTab extends GenericTab {

    public ShortestPathTab(Main main) {
        super(main);
        controlPanel = new ShortestPathControlPanel(main, this);
        graphsContainerPanel = new ShortestPathGraphsContainerPanel(this, controlPanel);
        divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphsContainerPanel, controlPanel);
        divider.setDividerLocation(450);
        add(divider, BorderLayout.CENTER);
        addComponentListener(this);
    }
}
