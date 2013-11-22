package application.action;

import application.view.Canvas;

import javax.swing.*;

public class ZoomOUT extends Action {

    private final String actionName = "zoomOUT";
    private final double FOCUS=0.4;
    private Canvas canvas;
    private ImageIcon im;

    public ZoomOUT(Canvas v, ImageIcon im) {
        this.canvas = v;
        this.im = im;
    }

    @Override
    public void execute() {
        this.canvas.getSubject().setIcon(im);
        int startHeight = this.canvas.getSubject().getIcon().getIconHeight();
        int startWidth = this.canvas.getSubject().getIcon().getIconWidth();
        int height = (int) (startHeight * FOCUS);
        int width = (int) (startWidth * FOCUS);
        this.canvas.getSubject().scale(height, width);
    }

    @Override
    public void invert() {
        int startHeight = this.canvas.getSubject().getIcon().getIconHeight();
        int startWidth = this.canvas.getSubject().getIcon().getIconWidth();
        int height = (int) (startHeight / FOCUS);
        int width = (int) (startWidth / FOCUS);
        this.canvas.getSubject().scale(height, width);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
