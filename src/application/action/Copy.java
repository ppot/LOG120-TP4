package application.action;

import application.Singleton;
import application.model.Thumbnail;

public class Copy {
    private CopyType copyType;
    private Thumbnail copy;
    public Copy(CopyType type){
        this.copyType=type;
        Thumbnail t= Singleton.getInstance().getEdit();
        Thumbnail c=new Thumbnail();
        c.setIcon(t.getIcon());
        c.scale(t.width(), t.height());
        c.changeBounds(t.x(),t.y());
        this.copy=c;
    }
    public Thumbnail getCopy(){
        return this.copy;
    }
    public CopyType getCopyType(){
        return this.copyType;
    }

}
