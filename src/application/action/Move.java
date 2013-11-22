package application.action;

import application.view.Canvas;

import javax.swing.*;

public class Move extends Action {

    private final String actionName = "move";
    private int x;
    private int y;
    private Canvas canvas;
    private ImageIcon im;

    public Move(Canvas v, ImageIcon im, Integer x, Integer y) {
        this.canvas = v;
        this.im = im;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        this.canvas.getSubject().setIcon(im);
        int xValue = this.canvas.getSubject().x() + x;
        int yValue = this.canvas.getSubject().y() + y;
        this.canvas.getSubject().changeBounds(xValue, yValue);
    }

    @Override
    public void invert() {
        int xValue = this.canvas.getSubject().x() - x;
        int yValue = this.canvas.getSubject().y() - y;
        this.canvas.getSubject().changeBounds(xValue, yValue);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
