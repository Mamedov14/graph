package ru.mamedov.graph.ui.search;

import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

import javax.swing.*;
import java.awt.*;

public class SearchTab extends GenericTab {

    public SearchTab(Main main) {
        super(main);
        controlPanel = new SearchControlPanel(main, this);
        graphsContainerPanel = new SearchGraphsContainerPanel(this, controlPanel);
        divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphsContainerPanel, controlPanel);
        divider.setDividerLocation(450);
        add(divider, BorderLayout.CENTER);
        addComponentListener(this);
    }
}
