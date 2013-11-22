package application;

import application.controler.Warpper;
import application.model.Thumbnail;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Thumbnail top;
    private Thumbnail left;
    private Thumbnail right;
    private Warpper warpper;

    private Singleton() {
        this.top = new Thumbnail();
        this.left = new Thumbnail();
        this.right = new Thumbnail();
        this.warpper = new Warpper();
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
    public Thumbnail getTop() {
        return top;
    }

    public Thumbnail getLeft() {
        return left;
    }

    public Thumbnail getRight() {
        return right;
    }

    public Warpper getWarpper() {
        return this.warpper;
    }

}