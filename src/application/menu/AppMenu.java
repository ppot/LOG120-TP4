package application.menu;

import application.Singleton;
import application.action.Open;
import application.action.Redo;
import application.action.Rollback;
import application.model.Thumbnail;
import application.tools.Handler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;

public class AppMenu extends JMenuBar {

    private static final long serialVersionUID = 1536336192561843187L;
    private static final int MENU_MASK = ActionEvent.CTRL_MASK;
    private static final char ROOLBACK_RACC = KeyEvent.VK_Z;
    private static final char SAVE_RACC = KeyEvent.VK_S;
    private static final char OPEN_RACC = KeyEvent.VK_O;
    private static final char OPENCONFIG_RACC = KeyEvent.VK_W;
    private static final char REDO_RACC = KeyEvent.VK_Y;
    private static final char QUIT_RACC = KeyEvent.VK_Q;
    private static final String TITLE = "app",
            QUIT_MENU = "quit",
            SAVE_MENU = "save",
            OPEN_MENU = "open",
            OPENCONFIG_MENU = "open config",
            ROOLBACK_MENU = "go back",
            REDO_MENU = "no redo";
    private static final char COPYALL_RAC = KeyEvent.VK_3;
    private static final char COPYMOVE_RAC = KeyEvent.VK_2;
    private static final char COPYSCALE_RAC = KeyEvent.VK_1;
    private static final char PASTE_RAC = KeyEvent.VK_P;
    private static final String TOOL_TITLE = "tool",
            TOOL_COPYSCALE="copy scale",
            TOOL_COPYMOVE="copy bounds",
            TOOL_COPY = "copy all",
            TOOL_PASTE = "paste";
    public AppMenu() {
        addMenu();
        toolMenu();
    }
    protected void addMenu() {
        JMenu menu = createMenu(TITLE,
                new String[]{OPEN_MENU, ROOLBACK_MENU, REDO_MENU, OPENCONFIG_MENU, SAVE_MENU, QUIT_MENU});
        menu.getItem(0).addActionListener(new Open());
        menu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(OPEN_RACC, MENU_MASK));
        menu.getItem(1).addActionListener(new Rollback());
        menu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(ROOLBACK_RACC, MENU_MASK));
        menu.getItem(2).addActionListener(new Redo());
        menu.getItem(2).setAccelerator(KeyStroke.getKeyStroke(REDO_RACC, MENU_MASK));
        menu.getItem(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                File f = new File("config.ser");
                if (f.exists()) {
                    try {
                        FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Singleton.getInstance().getPreview().copied((Thumbnail) ois.readObject());
                        Singleton.getInstance().getEdit().copied((Thumbnail) ois.readObject());
                        ois.close();

                        // Clean up the file
                        new File("config.ser").delete();
                    } catch (Exception ex) {
                        fail("Exception thrown: " + ex.toString());
                    }
                }

            }
        });

        menu.getItem(3).setAccelerator(KeyStroke.getKeyStroke(OPENCONFIG_RACC, MENU_MASK));
        menu.getItem(4).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    FileOutputStream fos = new FileOutputStream("config.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(Singleton.getInstance().getPreview());
                    oos.writeObject(Singleton.getInstance().getEdit());
                    oos.close();
                } catch (Exception ex) {
                        fail("Exception thrown during test: " + ex.toString());
                }
            }
        });

        menu.getItem(4).setAccelerator(KeyStroke.getKeyStroke(SAVE_RACC, MENU_MASK));
        menu.getItem(5).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        menu.getItem(5).setAccelerator(KeyStroke.getKeyStroke(QUIT_RACC, MENU_MASK));
        add(menu);
    }

    protected void toolMenu() {
        JMenu menu = createMenu(TOOL_TITLE,
                new String[]{TOOL_PASTE,TOOL_COPYSCALE,TOOL_COPYMOVE,TOOL_COPY});

        menu.getItem(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Singleton.getInstance().getWarpper().getCopy()!=null){
                    Handler.paste();
                }
            }
        });

        menu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(PASTE_RAC, MENU_MASK));
        menu.getItem(1).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Singleton.getInstance().getEdit().getIcon()!=null){
                    Handler.copyScale();
                }
            }
        });
        menu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(COPYSCALE_RAC, MENU_MASK));
        menu.getItem(2).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Singleton.getInstance().getEdit().getIcon()!=null){
                    Handler.copyMove();
                }
            }
        });
        menu.getItem(2).setAccelerator(KeyStroke.getKeyStroke(COPYMOVE_RAC, MENU_MASK));
        menu.getItem(3).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(Singleton.getInstance().getEdit().getIcon()!=null){
                    Handler.copy();
                }
            }
        });
        menu.getItem(3).setAccelerator(KeyStroke.getKeyStroke(COPYALL_RAC, MENU_MASK));
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
