package ru.mamedov.graph.ui.travelingsalesmanproblem;

import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

import javax.swing.*;
import java.awt.*;

public class TspTab extends GenericTab {

    public TspTab(Main main) {
        super(main);
        controlPanel = new TspControlPanel(main, this);
        graphsContainerPanel = new TspGraphsContainerPanel(this, controlPanel);
        divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphsContainerPanel, controlPanel);
        divider.setDividerLocation(450);
        add(divider, BorderLayout.CENTER);
        addComponentListener(this);
    }
}
