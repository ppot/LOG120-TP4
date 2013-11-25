package application;

import application.menu.AppMenu;
import application.view.Canvas;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {

    private JFrame frame;
    private Canvas preview;
    private Canvas move;
    private Canvas zoom;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App window = new App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public App() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setSize(1050, 781);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;
        frame.setLocation(x, y);
        //top
        AppMenu mn = new AppMenu();
        JPanel container = new JPanel();
        JPanel top = new JPanel();
        preview = new Canvas("PREVIEW",false,false, new Dimension(200, 200));
        top.add(preview);
        BorderLayout border = new BorderLayout();
        container.setLayout(border);
        container.add(mn, BorderLayout.NORTH);
        container.add(top, BorderLayout.SOUTH);
        frame.getContentPane().add(container, BorderLayout.NORTH);

        //left
        JPanel leftContainner = new JPanel();
        BorderLayout leftLyout = new BorderLayout();
        leftContainner.setLayout(leftLyout);

        JPanel left = new JPanel();
        move = new Canvas("MOVE",true,false, new Dimension(500, 500));
        left.add(move);
        leftContainner.add(left, BorderLayout.SOUTH);
        frame.getContentPane().add(leftContainner, BorderLayout.WEST);

        //right
        JPanel rightContainner = new JPanel();
        BorderLayout rightLyout = new BorderLayout();
        rightContainner.setLayout(rightLyout);
        JPanel right = new JPanel();
        zoom = new Canvas("ZOOM",false,true, new Dimension(500, 500));
        right.add(zoom);
        rightContainner.add(right, BorderLayout.SOUTH);
        frame.getContentPane().add(rightContainner, BorderLayout.EAST);
         Singleton.getInstance().getPreview().register(preview);
        Singleton.getInstance().getEdit().register(move);
        Singleton.getInstance().getEdit().register(zoom);

        preview.setSubject(Singleton.getInstance().getPreview());
        move.setSubject(Singleton.getInstance().getEdit());
        zoom.setSubject(Singleton.getInstance().getEdit());

        Singleton.getInstance().getWarpper().setTopCanvas(this.preview);
        Singleton.getInstance().getWarpper().setLeftCanvas(this.move);
        Singleton.getInstance().getWarpper().setRightCanvas(this.zoom);

    }
}

