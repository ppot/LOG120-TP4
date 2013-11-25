package application.controler;

import application.Flow;
import application.action.Copy;
import application.view.Canvas;
import application.action.Action;
import java.util.List;

public class Warpper{
    private Canvas preview;
    private Canvas move;
    private Canvas zoom;
    private Copy copyInstance;
    public Warpper() {
        this.preview = null;
        this.move = null;
        this.zoom = null;
    }

    public Canvas getTopCanvas() {
        return this.preview;
    }

    public Canvas getLeftCanvas() {
        return this.move;
    }

    public Canvas getRightCanvas() {
        return this.zoom;
    }

    public Copy getCopy() {
        return this.copyInstance;
    }

    public void setCopy(Copy c) {
        this.copyInstance = c;
    }

    public void setTopCanvas(Canvas c) {
        this.preview = c;
    }

    public void setLeftCanvas(Canvas c) {
        this.move = c;
    }

    public void setRightCanvas(Canvas c) {
        this.zoom = c;
    }

    public void handleEvent(Action a) {
        a.execute();
        Flow.addDone(a);
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
