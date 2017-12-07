package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoseMsgBox {
    private JFrame frame;

    public LoseMsgBox(Controller controller) {
        this.frame = new JFrame();
        this.frame.setSize(200, 100);
        frame.setLayout(new FlowLayout());


        JButton tryButton = new JButton("Try");
        tryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(tryButton);
        frame.add(exitButton);
    }

    public JFrame getFrame(){
        return this.frame;
    }
}
