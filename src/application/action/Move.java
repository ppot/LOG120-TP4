package application.action;
import application.Singleton;

import javax.swing.*;

public class Move extends Action {

    private final String actionName = "move";
    private ImageIcon i;
    private ImageIcon p;
    private int x;
    private int y;

    public Move(Integer x, Integer y) {
        this.p=Singleton.getInstance().getPreview().getIcon();
        this.i= Singleton.getInstance().getEdit().getIcon();
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        int xValue = Singleton.getInstance().getEdit().x()+x;
        int yValue =  Singleton.getInstance().getEdit().y() + y;
        Singleton.getInstance().getPreview().setIcon(p);
        Singleton.getInstance().getPreview().scale(200,200);
        Singleton.getInstance().getEdit().setIcon(i);
        Singleton.getInstance().getEdit().changeBounds(xValue, yValue);
    }

    @Override
    public void invert() {
        int xValue =  Singleton.getInstance().getEdit().x() - x;
        int yValue =  Singleton.getInstance().getEdit().y() - y;
        Singleton.getInstance().getEdit().changeBounds(xValue, yValue);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
