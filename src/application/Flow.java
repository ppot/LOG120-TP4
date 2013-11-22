package application;

import application.action.Action;
import java.util.ArrayList;
import java.util.List;

public final class Flow {
    public static final List<Action> ACTIONS= new ArrayList<Action>();
    public static final List<Action> UNDOACTIONS= new ArrayList<Action>();

    public static void addDone(Action a) {
       ACTIONS.add(a);
    }

    public static void addUndo(Action a) {
        UNDOACTIONS.add(a);
    }
}
