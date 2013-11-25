package application.action;

import application.Singleton;
import application.view.Canvas;

import javax.swing.*;

public class Zoom extends Action{
    private final String actionName = "zoom";
    private final double FOCUS=0.9;
    private Canvas canvas;
    private ImageIcon i;
    private ImageIcon p;
    private ImageIcon im;
    private boolean zoomed;

    public Zoom(Canvas v, ImageIcon im,boolean zoomValue) {
        this.p= Singleton.getInstance().getPreview().getIcon();
        this.i= Singleton.getInstance().getEdit().getIcon();
        this.canvas = v;
        this.im = im;
        this.zoomed=zoomValue;
    }
    @Override
    public void execute() {
        this.canvas.getSubject().setIcon(im);
        int startHeight = this.canvas.getSubject().getIcon().getIconHeight();
        int startWidth = this.canvas.getSubject().getIcon().getIconWidth();
        int h;
        int w;
        if(zoomed)   {
            w= (int) (startWidth / FOCUS);
            h= (int) (startHeight / FOCUS);
        }
        else{
            w= (int) (startWidth *FOCUS);
            h= (int) (startHeight * FOCUS);
        }
        if(h>50&&w>50&&h<500&&w<500){
            Singleton.getInstance().getPreview().setIcon(p);
            Singleton.getInstance().getPreview().scale(200,200);
            Singleton.getInstance().getEdit().setIcon(i);
            this.canvas.getSubject().scale(w,h);
        }
    }
    @Override
    public void invert() {
        int startHeight = this.canvas.getSubject().getIcon().getIconHeight();
        int startWidth = this.canvas.getSubject().getIcon().getIconWidth();
        int w;
        int h;
        if(zoomed){
            w= (int) (startWidth *FOCUS);
            h= (int) (startHeight * FOCUS);
        }
        else{
            w= (int) (startWidth / FOCUS);
            h= (int) (startHeight / FOCUS);
        }
        if(h>20&&w>20&&h<1200&&w<1200){
            this.canvas.getSubject().scale(w,h);
       }
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
