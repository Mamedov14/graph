package ru.mamedov.graph.ui.minimumspanningtree;

import ru.mamedov.graph.ui.GenericTab;
import ru.mamedov.graph.ui.Main;

import javax.swing.*;
import java.awt.*;

public class MstTab extends GenericTab {

    public MstTab(Main main) {
        super(main);
        controlPanel = new MstControlPanel(main, this);
        graphsContainerPanel = new MstGraphsContainerPanel(this, controlPanel);
        divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphsContainerPanel, controlPanel);
        divider.setDividerLocation(450);
        add(divider, BorderLayout.CENTER);
        addComponentListener(this);
    }
}
