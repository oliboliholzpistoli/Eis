package com.eis.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    public GUI() {
        super("amogus");
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addGame();
        setLayout(null);
        setVisible(true);
    }
    private void addKonata(){
        ImageIcon ii = new ImageIcon("D:\\Libraries\\Downloads\\communityIcon_6454s8jiqbl41.png");
        JButton button = new JButton(ii);
        button.setBounds(130,100,ii.getIconWidth(),ii.getIconHeight());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBounds((int) (Math.random()*1920), (int) (Math.random()*1080),ii.getIconWidth(),ii.getIconHeight());
            }
        });
        add(button);
    }
    private void addGame(){
        JPanel jp = new JPanel();
        jp.setBackground(Color.GREEN);
    }
}
