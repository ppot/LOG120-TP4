package application.model;

import application.tools.Observer;
import application.tools.Subject;
import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Thumbnail implements Serializable, Subject {
    private List<Observer> observers;
    private ImageIcon im;
    private int height;
    private int width;
    private int x;
    private int y;

    public Thumbnail() {
        this.observers = new ArrayList<Observer>();
        this.im = null;
        this.x=0;
        this.y=0;
        this.height = 0;
        this.width = 0;
    }

    public void newIcon(File f) {
        this.im = new ImageIcon(f.getPath());
        this.height = this.im.getIconHeight();
        this.width = this.im.getIconWidth();
    }

    public void setIcon(ImageIcon im) {
        this.im = im;
    }

    public void reset() {
        this.im = null;
        this.height = 0;
        this.width = 0;
        changeBounds(0, 0);
    }

    public void copied(Thumbnail t) {
        setIcon(t.getIcon());
        this.x=t.x();
        this.y= y();
        this.height=t.height();
        this.width=t.width();
        notifyObservers();
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }
    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

    public void scale(Integer height, Integer width) {
        this.height = height;
        this.width = width;
        Image img = this.im.getImage();
        this.im = new ImageIcon(img.getScaledInstance(this.height, this.width, java.awt.Image.SCALE_SMOOTH));
        notifyObservers();
    }

    public ImageIcon getIcon() {
        return this.im;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void changeBounds(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        notifyObservers();
    }

    @Override
    public void register(Observer obj) {
        if (obj == null) throw new NullPointerException("Null Observer");
        if (!observers.contains(obj)) observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = new ArrayList<Observer>(this.observers);
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    @Override
    public Thumbnail getUpdate(Observer obj) {
        return this;
    }
}
