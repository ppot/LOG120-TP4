package application.action;

import application.Flow;
import application.Singleton;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

public class Rollback extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Action> actions = Flow.ACTIONS;
        if (actions.size() >= 1) {
            Singleton.getInstance().getWarpper().invertEvent(actions.get(actions.size() - 1));
        }
    }
}
