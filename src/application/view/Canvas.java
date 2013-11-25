package application.view;

import application.tools.Handler;
import application.model.Thumbnail;
import application.tools.Observer;
import application.tools.Subject;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class Canvas extends JComponent implements MouseListener,MouseWheelListener,Observer {
    private static final long serialVersionUID = 1L;
    private Dimension dimention;
    private String id;
    private Subject subject;
    private int mouseX = 0;
    private int mouseY = 0;
    private boolean canZoom;
    private boolean canMove;

    public Canvas(String id,boolean move,boolean zoom, Dimension dim) {
        this.id = id;
        this.canMove=move;
        this.canZoom=zoom;
        this.dimention = dim;
        if (move) {
            this.addMouseListener(this);
        }
        if(zoom){
            this.addMouseWheelListener(this);
        }
        this.mouseX = 0;
        this.mouseY = 0;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public String getId() {
        return this.id;
    }

    public Thumbnail getSubject() {
        Thumbnail response = (Thumbnail) this.subject;
        return response;
    }

    @Override
    public void update() {
        subject.getUpdate(this);
        this.repaint();
    }

    @Override
    public void setSubject(Subject s) {
        this.subject = s;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Thumbnail response = (Thumbnail) this.subject;
        if (response.getIcon() != null) {
            response.getIcon().paintIcon(this, g, response.x(), response.y());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        Thumbnail response = (Thumbnail) this.subject;
        if (response != null) {
            this.mouseX = MouseInfo.getPointerInfo().getLocation().x;
            this.mouseY = MouseInfo.getPointerInfo().getLocation().y;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Thumbnail response = (Thumbnail) this.subject;
        if (response != null) {
            int endX = MouseInfo.getPointerInfo().getLocation().x;
            int endY = MouseInfo.getPointerInfo().getLocation().y;
            int totalX = 0;
            int totalY = 0;
            totalX = endX - this.mouseX;
            totalY = endY - this.mouseY;
            Handler.move(totalX, totalY);
            this.mouseX = 0;
            this.mouseY = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public Dimension getPreferredSize() {
        return dimention;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int value=e.getWheelRotation();
        boolean zoomValue=false;
        if(value>0){
            zoomValue=true;
        }
        else if(value<0){
            zoomValue=false;
        }
        Handler.zoom(this,zoomValue);
    }
}
