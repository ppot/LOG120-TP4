package application.action;

import application.model.Thumbnail;
import application.view.Canvas;


public class Paste extends Action {
    private final String actionName = "paste";
    private Canvas canvas;
    private Thumbnail initial;
    private Thumbnail copy;

    public Paste(Canvas v, Thumbnail ini, Thumbnail copied) {
        this.canvas = v;
        this.initial = ini;
        this.copy = copied;
    }

    @Override
    public void execute() {
        this.canvas.getSubject().copied(this.copy);
    }

    @Override
    public void invert() {
        this.canvas.getSubject().copied(this.initial);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
