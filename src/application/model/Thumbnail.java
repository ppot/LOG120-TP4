package application.model;

import application.tools.Observer;
import application.tools.Subject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

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
        this.im=t.getIcon();
        changeBounds(t.x(),t.y());
        scale(t.width(),t.height());
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

    public void scale(Integer width, Integer height) {
        this.height = height;
        this.width = width;
        BufferedImage scaled = resizeImage( this.im.getImage(), width, height);
        this.im = new ImageIcon(scaled);
        notifyObservers();
    }
    public void setInitialScale(Integer width) {
        double aspectRatio = (double) this.im.getIconWidth()/(double) this.im.getIconHeight();
        BufferedImage scaled = resizeImage(this.im.getImage(), width, (int) (width/aspectRatio));
        this.im = new ImageIcon(scaled);
        this.height = im.getIconHeight();
        this.width = im.getIconWidth();
        notifyObservers();
    }
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.SCALE_SMOOTH);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
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
    public void setHeight(Integer h){
        this.height=h;
    }
    public void setWidth(Integer w){
        this.width=w;
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
