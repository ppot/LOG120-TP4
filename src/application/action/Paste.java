package application.action;

import application.Flow;
import application.Singleton;
import application.model.Thumbnail;


public class Paste extends Action {
    private final String actionName = "paste";
    private Thumbnail previewInitial;
    private Thumbnail initial;
    private Copy copy;

    public Paste(Thumbnail ini, Copy c) {
        Thumbnail prev=Singleton.getInstance().getPreview();
        this.previewInitial=prev;
        Thumbnail t= ini;
        Thumbnail init=new Thumbnail();
        init.setIcon(t.getIcon());
        init.scale(t.width(), t.height());
        init.changeBounds(t.x(),t.y());
        this.initial = init;
        this.copy = c;
    }

    @Override
    public void execute() {
        Singleton.getInstance().getPreview().copied(previewInitial);
        switch (copy.getCopyType()){
            case ALL:
                Singleton.getInstance().getEdit().copied(copy.getCopy());
            break;
            case SCALE:
                Singleton.getInstance().getEdit().scale(copy.getCopy().width(),copy.getCopy().height());
                break;
            case MOVE:
                Singleton.getInstance().getEdit().changeBounds(copy.getCopy().x(),copy.getCopy().y());
                break;
        }
    }

    @Override
    public void invert() {
        Singleton.getInstance().getPreview().copied(previewInitial);
        switch (copy.getCopyType()){
            case ALL:
                Singleton.getInstance().getEdit().copied(initial);
                break;
            case SCALE:
                Singleton.getInstance().getEdit().scale(initial.width(),initial.height());
                break;
            case MOVE:
                Singleton.getInstance().getEdit().changeBounds(initial.x(),initial.y());
                break;
        }
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
