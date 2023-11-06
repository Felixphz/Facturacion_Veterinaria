/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package View;

/**
 *
 * @author David Gomez
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DynamicTextFieldAddition {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Agregar Campos");
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Agregar Campos");

        frame.add(panel);
        frame.add(addButton, "South");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField1 = new JTextField(20);
                JTextField textField2 = new JTextField(20);
                panel.add(textField1);
                panel.add(textField2);
                panel.revalidate();
                panel.repaint();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

