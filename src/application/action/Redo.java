package application.action;

import application.Flow;
import application.Singleton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Redo extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Action> undos = Flow.UNDOACTIONS;
        if (undos.size() >= 1) {
            Action toUndo = undos.get(undos.size() - 1);
            Flow.UNDOACTIONS.remove(toUndo);
            Singleton.getInstance().getWarpper().handleEvent(toUndo);
        }
    }
}
