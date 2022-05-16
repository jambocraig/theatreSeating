package theatre;

import javax.swing.*;
import java.awt.*;

public class TheatreGUI extends JFrame {
    JButton[][] arr;
    Theatre theatre;
    JPanel mainPanel;

    TheatreGUI(){
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setSize(800, 600);
        theatre = new Theatre(10, 15);
        theatre.populate(20);
        theatre.print();
        arr = new JButton[10][15];
        popArr();
        mainPanel = new JPanel();
        GridLayout lay = new GridLayout(10, 15, 1, 1);
        mainPanel.setLayout(lay);
        addButtons();
        this.add(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                if (theatre.getState(r,c)){
                    btn.setBackground(Color.red);
                } else{
                    btn.setBackground(Color.white);
                }
                arr[r][c] = btn;
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TheatreGUI theatreGUI = new TheatreGUI();
            }
        });
    }
}
