package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawBus extends JPanel {

    private AccordionBus accordionBus;

    public void paintComponent(Graphics g) {
        if (accordionBus != null) {
            accordionBus.DrawBus(g);
        }
    }

    public void setAccordionBus(AccordionBus ex) {
        this.accordionBus = ex;
    }
}
