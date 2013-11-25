package application;

import application.controler.Warpper;
import application.model.Thumbnail;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Thumbnail preview;
    private Thumbnail edit;
    private Warpper warpper;

    private Singleton() {
        this.preview=new Thumbnail();
        this.edit =new Thumbnail();
        this.warpper = new Warpper();
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
    public Thumbnail getPreview(){
        return  preview;
    }
    public Thumbnail getEdit(){
        return edit;
    }

    public Warpper getWarpper() {
        return this.warpper;
    }

}