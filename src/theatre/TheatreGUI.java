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
    JButton btnStart, btnStop, btnStart2, btnBooking;
    JTextField tfBooking;
    BoxLayout subLay;


    TheatreGUI() {
        // need for mac OS
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setSize(1000, 600);
        // create theatre object and randomly populate
        theatre = new Theatre(10, 15);
        theatre.populate(40);
        theatre.print();
        // create a reflective button array
        arr = new JButton[10][15];
        popArr();

        //outer panel and layout
        outerPanel = new JPanel();
        BoxLayout boxLay = new BoxLayout(outerPanel, BoxLayout.Y_AXIS);
        outerPanel.setLayout(boxLay);

        mainPanel = new JPanel();
        GridLayout lay = new GridLayout(10, 15, 1, 1);
        mainPanel.setLayout(lay);
        subPanel = new JPanel();
        subLay = new BoxLayout(subPanel, BoxLayout.X_AXIS);
        subPanel.setLayout(subLay);
        addButtons();

        this.add(outerPanel);
        outerPanel.add(mainPanel);
        outerPanel.add(Box.createRigidArea(new Dimension(800, 10)));
        outerPanel.add(subPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addSubButtons();

        // create Timer object with anonymous actionlistener
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
        Dimension dim = new Dimension(200, 40);
        Color grn = new Color(10, 100, 10);
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        btnStart.setPreferredSize(dim);
        btnStart.setMaximumSize(dim);
        btnStart.setForeground(grn);

        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
        btnStop.setPreferredSize(dim);
        btnStop.setMaximumSize(dim);
        btnStop.setForeground(Color.red);

        btnStart2 = new JButton("Start Once");
        btnStart2.setPreferredSize(dim);
        btnStart2.setMaximumSize(dim);
        btnStart2.setForeground(grn);
        btnStart2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theatre.populate(40);
                changeBtnColours();
            }
        });

        tfBooking = new JTextField();
        tfBooking.setPreferredSize(dim);
        tfBooking.setMaximumSize(dim);

        btnBooking = new JButton("Booking");
        btnBooking.setPreferredSize(dim);
        btnBooking.setMaximumSize(dim);
        btnBooking.setForeground(Color.red);
        btnBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int booking = Integer.parseInt(tfBooking.getText());
                int[] seats = theatre.checkBooking(booking);
                if(seats.length > 0){
                    System.out.println("found");
                    for (int s = 0; s < booking; s++){
                        tfBooking.setText("row" + seats[0] + "seat" + seats[1]);
                        arr[seats[0]][seats[1] + s].setBackground(Color.GREEN);
                    }
                } else{
                    System.out.println("not found");
                }
            }
        });

        subPanel.add(btnStart);
        subPanel.add(btnStop);
        subPanel.add(btnStart2);
        subPanel.add(tfBooking);
        subPanel.add(btnBooking);
    }

    private void addButtons() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                mainPanel.add(arr[i][j]);
            }
        }
    }

    private void popArr() {
        // row 0 at the bottom of screen
        for (int r = arr.length - 1; r>=0; r--) {
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
