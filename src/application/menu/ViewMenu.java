package application.menu;

import application.tools.Handler;
import application.view.Canvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ViewMenu extends JMenuBar {
    private static final String TOOL_TITLE = "tool",
            TOOL_ZOOMIN = "zoom in",
            TOOL_ZOOMOUT = "zoom out",
            TOOL_COPY = "copy",
            TOOL_PASTE = "paste";

    private Canvas canvas;

    public ViewMenu(Canvas v) {
        this.canvas = v;
        addMenu();
    }

    protected void addMenu() {
        JMenu menu = createMenu(TOOL_TITLE,
                new String[]{TOOL_ZOOMIN, TOOL_ZOOMOUT, TOOL_COPY, TOOL_PASTE});
        menu.getItem(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Handler.zoomIN(canvas);
            }
        });
        menu.getItem(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Handler.zoomOUT(canvas);
            }
        });
        menu.getItem(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Handler.copy(canvas.getSubject());
            }
        });
        menu.getItem(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Handler.paste(canvas);
            }
        });
        add(menu);
    }

    private static JMenu createMenu(String titleKey, String[] itemKeys) {
        JMenu menu = new JMenu(titleKey);
        for (int i = 0; i < itemKeys.length; ++i) {
            menu.add(new JMenuItem(itemKeys[i]));
        }
        return menu;
    }

}
