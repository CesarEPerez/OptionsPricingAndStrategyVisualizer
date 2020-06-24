import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class UserGui {

    JFrame frame;
    JPanel panel;

    public UserGui() {

        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(700,600);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        JLabel label = new JLabel("Enter text:");
        JTextField field = new JTextField("Enter Here", 15);
        JComboBox derivativeBox = new JComboBox(new String[] {"European", "American"});
        // label.setSize(50,20);
        // field.setSize(100,20);

        layout.putConstraint(SpringLayout.EAST, derivativeBox, 0, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.WEST, label, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, field, 20, SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, field, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, panel, 20, SpringLayout.EAST, field);
        layout.putConstraint(SpringLayout.SOUTH, panel, 10, SpringLayout.SOUTH, field);

        panel.add(label);
        panel.add(field);
        panel.add(derivativeBox);
        frame.pack();
        frame.setVisible(true);

    }

}