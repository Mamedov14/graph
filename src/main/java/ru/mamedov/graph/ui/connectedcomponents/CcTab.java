package ru.mamedov.graph.ui.connectedcomponents;

import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

import javax.swing.*;
import java.awt.*;

public class CcTab extends GenericTab {
    public CcTab(Main main) {
        super(main);
        controlPanel = new CcControlPanel(main, this);
        graphsContainerPanel = new CcGraphsContainerPanel(this, controlPanel);
        divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphsContainerPanel, controlPanel);
        divider.setDividerLocation(450);
        add(divider, BorderLayout.CENTER);
        addComponentListener(this);
    }
}
