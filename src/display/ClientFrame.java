package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HexFormat;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;

public class ClientFrame {

    public static int FONT_SIZE = 20;
    public static int FRAME_WIDTH = 400;
    public static int FRAME_HEIGHT = 500;

    public ClientFrame() {

        // フレームを作成
        JFrame clientFrame = new JFrame("WebAppServer v1.0");
        clientFrame.setPreferredSize(new Dimension(FRAME_HEIGHT, FRAME_WIDTH));
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.setResizable(false);

        // パネルを作成
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel statusPanel = new JPanel();
        JPanel switchPanel = new JPanel();
        JPanel logPanel = new JPanel();

        // ラベルを作成
        JLabel dummyLeft = new JLabel("        ");
        JLabel dummyRight = new JLabel("        ");
        JLabel label = new JLabel("Server Status: ");
        JLabel status = new JLabel(ClientApp.SERVER_STATUS);
        dummyLeft.setFont(new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE * 3));
        dummyRight.setFont(new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE * 3));
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE));
        status.setFont(new Font(Font.DIALOG, Font.PLAIN, FONT_SIZE));
        ;

        dummyLeft.setBorder(new LineBorder(Color.RED, 2, true));
        dummyRight.setBorder(new LineBorder(Color.RED, 2, true));
        label.setBorder(new LineBorder(Color.RED, 2, true));
        status.setBorder(new LineBorder(Color.RED, 2, true));

        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.LINE_AXIS));
        statusPanel.add(dummyLeft);
        statusPanel.add(label);
        statusPanel.add(status);
        statusPanel.add(dummyRight);

        statusPanel.setPreferredSize(new Dimension(FRAME_HEIGHT, FRAME_WIDTH / 3));
        statusPanel.setBackground(Color.CYAN);

        // ボタンを作成
        JButton bootUpBtn = new JButton("Boot up");
        JButton shutdownBtn = new JButton("Shutdown");
        JButton logOutputBtn = new JButton("Log output");
        switchPanel.add(bootUpBtn);
        switchPanel.add(shutdownBtn);
        logPanel.add(logOutputBtn);

        mainPanel.add(statusPanel, BorderLayout.NORTH);
        mainPanel.add(switchPanel, BorderLayout.WEST);
        mainPanel.add(logPanel, BorderLayout.EAST);
        clientFrame.add(mainPanel);

        clientFrame.pack();
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setVisible(true);
    }
}
