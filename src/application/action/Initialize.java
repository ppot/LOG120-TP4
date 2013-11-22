package application.action;

import application.Singleton;
import application.controler.Warpper;

import java.io.File;

public class Initialize extends Action {
    private final String actionName = "initialize";
    private File file;

    public Initialize(File f) {
        this.file = f;
    }

    @Override
    public void execute() {
        Singleton.getInstance().getTop().newIcon(file);
        Singleton.getInstance().getTop().scale(200, 200);

        Warpper warpper = Singleton.getInstance().getWarpper();
        Singleton.getInstance().getLeft().newIcon(file);
        Singleton.getInstance().getLeft().changeBounds(warpper.getLeftCanvas().getPreferredSize().width / 2 - warpper.getLeftCanvas().getSubject().width() / 2,
                warpper.getLeftCanvas().getPreferredSize().height / 2 - warpper.getLeftCanvas().getSubject().height() / 2);
        Singleton.getInstance().getRight().newIcon(file);
        warpper.getRightCanvas().getSubject().changeBounds(warpper.getRightCanvas().getPreferredSize().width / 2 - warpper.getRightCanvas().getSubject().width() / 2,
                warpper.getRightCanvas().getPreferredSize().height / 2 - warpper.getRightCanvas().getSubject().height() / 2);
    }

    @Override
    public void invert() {
        Singleton.getInstance().getTop().reset();
        Singleton.getInstance().getLeft().reset();
        Singleton.getInstance().getRight().reset();
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

}
