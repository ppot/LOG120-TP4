package application.tools;

import application.Singleton;
import application.action.*;
import application.model.Thumbnail;
import application.view.Canvas;
import java.io.File;

public final class Handler {
    public static void init(File f) {
        Singleton.getInstance().getWarpper().handleEvent(new Initialize(f));
    }

    public static void move(Integer x, Integer y) {
        Singleton.getInstance().getWarpper().handleEvent(new Move( x, y));
    }
    public static void zoom(Canvas c,boolean mouseValue) {
        Singleton.getInstance().getWarpper().handleEvent(new Zoom(c,c.getSubject().getIcon(),mouseValue));
    }
    public static void copy() {
        Copy copy=new Copy(CopyType.ALL);
        Singleton.getInstance().getWarpper().setCopy(copy);
    }
    public static void copyMove(){
        Copy copy=new Copy(CopyType.MOVE);
        Singleton.getInstance().getWarpper().setCopy(copy);
    }
    public static void copyScale() {
        Copy copy=new Copy(CopyType.SCALE);
        Singleton.getInstance().getWarpper().setCopy(copy);
    }

    public static void paste() {
        Thumbnail ini= Singleton.getInstance().getEdit();
        Singleton.getInstance().getWarpper().handleEvent(new Paste(ini, Singleton.getInstance().getWarpper().getCopy()));
    }
}
