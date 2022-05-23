package theatre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheatreGUI extends JFrame {
    JButton[][] arr;
    Theatre theatre;
    JPanel mainPanel, subPanel, outerPanel;
    Timer timer;
    JButton btnStart, btnStop;
    BoxLayout subLay;

    TheatreGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setSize(1000, 600);
        theatre = new Theatre(10, 15);
        theatre.populate(40);
        theatre.print();
        arr = new JButton[10][15];
        popArr();
        outerPanel = new JPanel();
        BoxLayout boxLay = new BoxLayout(outerPanel, BoxLayout.Y_AXIS);

        outerPanel.setLayout(boxLay);
        mainPanel = new JPanel();
        GridLayout lay = new GridLayout(10, 15, 1, 1);
        mainPanel.setLayout(lay);
        subPanel = new JPanel();
        BoxLayout subLay = new BoxLayout(subPanel, BoxLayout.X_AXIS);
        subPanel.setLayout(subLay);
        addButtons();

        this.add(outerPanel);
        outerPanel.add(mainPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(800, 10)));
        outerPanel.add(subPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addSubButtons();
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theatre.populate(40);
                changeBtnColours();
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TheatreGUI theatreGUI = new TheatreGUI();
            }
        });
    }

    private void addSubButtons() {
        btnStart = new JButton();
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        btnStart.setPreferredSize(new Dimension(400, 50));
        btnStart.setMaximumSize(new Dimension(400, 50));

        btnStop = new JButton();
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        btnStop.setPreferredSize(new Dimension(400, 50));
        btnStop.setMaximumSize(new Dimension(400, 50));

        subPanel.add(btnStart);
        subPanel.add(btnStop);
    }

    private void addButtons() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                mainPanel.add(arr[i][j]);
            }
        }
    }

    private void popArr() {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                JButton btn = new JButton();
                btn.setOpaque(true);
                btn.setBorderPainted(false);
                if (theatre.getState(r, c)) {
                    btn.setBackground(Color.red);
                } else {
                    btn.setBackground(Color.white);
                }
                arr[r][c] = btn;
            }
        }
    }

    private void changeBtnColours() {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                if (theatre.getState(r, c)) {
                    arr[r][c].setBackground(Color.red);
                } else {
                    arr[r][c].setBackground(Color.white);
                }
            }
        }
    }

}
