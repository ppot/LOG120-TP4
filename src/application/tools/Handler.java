package application.tools;

import application.Singleton;
import application.action.Initialize;
import application.action.Move;
import application.action.ZoomIN;
import application.action.ZoomOUT;
import application.action.Paste;
import application.model.Thumbnail;
import application.view.Canvas;
import javax.swing.*;
import java.io.File;

public final class Handler {
    public static void init(File f) {
        Singleton.getInstance().getWarpper().handleEvent(new Initialize(f));
    }

    public static void move(Canvas c, ImageIcon im, Integer x, Integer y) {
        Singleton.getInstance().getWarpper().handleEvent(new Move(c, im, x, y));
    }

    public static void zoomIN(Canvas c) {
        Singleton.getInstance().getWarpper().handleEvent(new ZoomIN(c, c.getSubject().getIcon()));
    }

    public static void zoomOUT(Canvas c) {
        Singleton.getInstance().getWarpper().handleEvent(new ZoomOUT(c, c.getSubject().getIcon()));
    }

    public static void copy(Thumbnail t) {
        Singleton.getInstance().getWarpper().setCopy(t);
    }

    public static void paste(Canvas c) {
        Thumbnail init=c.getSubject();
        Singleton.getInstance().getWarpper().handleEvent(new Paste(c, init, Singleton.getInstance().getWarpper().getCopy()));
    }
}
