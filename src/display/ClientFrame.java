package display;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ClientFrame {

    public ClientFrame() {

        JFrame clientFrame = new JFrame("WebAppServer v1.0");
        clientFrame.setPreferredSize(new Dimension(600, 450));
        clientFrame.pack();
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setVisible(true);
    }
}
