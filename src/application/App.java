package application;

import application.menu.AppMenu;
import application.menu.ViewMenu;
import application.view.Canvas;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {

    private JFrame frame;
    private Canvas canvasTop;
    private Canvas canvasLeft;
    private Canvas canvasRight;

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
        canvasTop = new Canvas("TOP", false, new Dimension(200, 200));
        top.add(canvasTop);
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
        canvasLeft = new Canvas("LEFT", true, new Dimension(500, 500));
        left.add(canvasLeft);
        ViewMenu leftMenu = new ViewMenu(canvasLeft);

        leftContainner.add(leftMenu, BorderLayout.NORTH);
        leftContainner.add(left, BorderLayout.SOUTH);
        frame.getContentPane().add(leftContainner, BorderLayout.WEST);

        //right
        JPanel rightContainner = new JPanel();
        BorderLayout rightLyout = new BorderLayout();
        rightContainner.setLayout(rightLyout);
        JPanel right = new JPanel();
        canvasRight = new Canvas("RIGHT", true, new Dimension(500, 500));
        right.add(canvasRight);
        ViewMenu rightMenu = new ViewMenu(canvasRight);
        rightContainner.add(rightMenu, BorderLayout.NORTH);
        rightContainner.add(right, BorderLayout.SOUTH);
        frame.getContentPane().add(rightContainner, BorderLayout.EAST);


        Singleton.getInstance().getTop().register(canvasTop);
        Singleton.getInstance().getLeft().register(canvasLeft);
        Singleton.getInstance().getRight().register(canvasRight);

        canvasTop.setSubject(Singleton.getInstance().getTop());
        canvasLeft.setSubject(Singleton.getInstance().getLeft());
        canvasRight.setSubject(Singleton.getInstance().getRight());

        Singleton.getInstance().getWarpper().setTopCanvas(this.canvasTop);
        Singleton.getInstance().getWarpper().setLeftCanvas(this.canvasLeft);
        Singleton.getInstance().getWarpper().setRightCanvas(this.canvasRight);

    }
}

