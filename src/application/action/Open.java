package application.action;

import application.tools.Handler;
import application.tools.ImageFilter;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public class Open extends AbstractAction {
    private static final long serialVersionUID = 1L;
    final JFileChooser fc = new JFileChooser();

    @Override
    public void actionPerformed(ActionEvent e) {
        fc.addChoosableFileFilter(new ImageFilter());
        JMenuItem mn = (JMenuItem) e.getSource();
        int returnVal = fc.showOpenDialog(mn);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Handler.init(fc.getSelectedFile());
        }
    }

}
