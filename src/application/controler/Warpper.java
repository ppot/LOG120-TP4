package application.controler;

import application.Flow;
import application.view.Canvas;
import application.Singleton;
import application.model.Thumbnail;
import application.action.Action;
import java.util.List;

public class Warpper{
    private Canvas canvasTop;
    private Canvas canvasLeft;
    private Canvas canvasRight;
    private Thumbnail copy;

    public Warpper() {
        this.canvasTop = null;
        this.canvasLeft = null;
        this.canvasRight = null;
    }

    public Canvas getTopCanvas() {
        return this.canvasTop;
    }

    public Canvas getLeftCanvas() {
        return this.canvasLeft;
    }

    public Canvas getRightCanvas() {
        return this.canvasRight;
    }

    public Thumbnail getCopy() {
        return this.copy;
    }

    public void setCopy(Thumbnail c) {
        this.copy = c;
    }

    public void setTopCanvas(Canvas c) {
        this.canvasTop = c;
    }

    public void setLeftCanvas(Canvas c) {
        this.canvasLeft = c;
    }

    public void setRightCanvas(Canvas c) {
        this.canvasRight = c;
    }

    public void handleEvent(Action a) {
        Flow.addDone(a);
        a.execute();
    }

    public void invertEvent(Action a) {
        if (!a.getActionName().equals("initialize")) a.invert();
        else {
            List<Action> actions = Flow.ACTIONS;
            if (actions.size() == 1) {
                a.invert();
            } else {
                int value = actions.indexOf(a);
                Action checkPrecedent = actions.get(value - 1);
                checkPrecedent.execute();
            }
        }
        Flow.addUndo(a);
        Flow.ACTIONS.remove(a);
    }

}
