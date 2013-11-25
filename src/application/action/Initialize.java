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
        Warpper warpper = Singleton.getInstance().getWarpper();
        Singleton.getInstance().getPreview().newIcon(file);
        Singleton.getInstance().getPreview().scale(200,200);
        Singleton.getInstance().getEdit().newIcon(file);
        Singleton.getInstance().getEdit().setInitialScale(200);
        Singleton.getInstance().getEdit().changeBounds(warpper.getLeftCanvas().getPreferredSize().width / 2 - warpper.getLeftCanvas().getSubject().width() / 2,
                 warpper.getLeftCanvas().getPreferredSize().height / 2 - warpper.getLeftCanvas().getSubject().height() / 2);
    }

    @Override
    public void invert() {
        Singleton.getInstance().getPreview().reset();
        Singleton.getInstance().getEdit().reset();
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

}
