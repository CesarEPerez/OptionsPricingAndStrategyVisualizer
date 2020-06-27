import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

class FormElements {
    public JLabel label;
    public JTextField field;
}

public class UserGui {

    private JFrame frame;
    private JPanel panelInputs;
    private JPanel panelOutputs;
    private JPanel panelFrame;

    public UserGui() {

        frame = new JFrame();
        panelInputs = new JPanel();
        panelOutputs = new JPanel();
        panelFrame = new JPanel();
        SpringLayout frameLayout = new SpringLayout();
        panelFrame.setLayout(frameLayout);
        frameLayout.putConstraint(SpringLayout.WEST, panelInputs, 5, SpringLayout.EAST, frame);
        frameLayout.putConstraint(SpringLayout.NORTH, panelInputs, 5, SpringLayout.NORTH, frame);
        frameLayout.putConstraint(SpringLayout.WEST, panelOutputs, 5, SpringLayout.EAST, panelInputs);
        frameLayout.putConstraint(SpringLayout.NORTH, panelOutputs, 5, SpringLayout.NORTH, frame);
        frame.add(panelFrame);
        panelFrame.add(panelInputs);
        panelFrame.add(panelOutputs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        // frame.setSize(700,600);
        //frame.setResizable(false);

        SpringLayout layoutInputs = new SpringLayout();
        SpringLayout layoutOutputs = new SpringLayout();
        panelInputs.setLayout(layoutInputs);
        panelOutputs.setLayout(layoutOutputs);
        panelInputs.setBorder(BorderFactory.createTitledBorder("Inputs"));
        panelOutputs.setBorder(BorderFactory.createTitledBorder("Outputs"));

        FormElements[] elements = new FormElements[6];
        for (int i = 0; i < elements.length; i++) elements[i] = new FormElements();
        JComboBox derivativeBox = new JComboBox(new String[] {"European", "American"});
        JComboBox callPutBox = new JComboBox(new String[] {"Call", "Put"});
        JLabel exprationLabel = new JLabel("Time to expration (yr/mo):");
        JTextField exprationField = new JTextField("", 15);

        elements[0].label = exprationLabel;
        elements[0].field = exprationField;
        elements[1].label = new JLabel("Underlying price:");
        elements[1].field = new JTextField("", 15);
        elements[2].label = new JLabel("Strike price:");
        elements[2].field = new JTextField("", 15);
        elements[3].label = new JLabel("Dividend yield:");
        elements[3].field = new JTextField("", 15);
        elements[4].label = new JLabel("Annual risk-free rate:");
        elements[4].field = new JTextField("", 15);
        elements[5].label = new JLabel("Annualized volatility:");
        elements[5].field = new JTextField("", 15);

        layoutInputs.putConstraint(SpringLayout.EAST, derivativeBox, 0, SpringLayout.EAST, exprationField);
        layoutInputs.putConstraint(SpringLayout.NORTH, derivativeBox, 10, SpringLayout.NORTH, panelInputs);
        layoutInputs.putConstraint(SpringLayout.EAST, callPutBox, 0, SpringLayout.WEST, derivativeBox);
        layoutInputs.putConstraint(SpringLayout.NORTH, callPutBox, 10, SpringLayout.NORTH, panelInputs);
        layoutInputs.putConstraint(SpringLayout.WEST, exprationLabel, 20, SpringLayout.WEST, panelInputs);
        layoutInputs.putConstraint(SpringLayout.NORTH, exprationLabel, 20, SpringLayout.SOUTH, derivativeBox);
        layoutInputs.putConstraint(SpringLayout.WEST, exprationField, 20, SpringLayout.EAST, elements[0].label);
        layoutInputs.putConstraint(SpringLayout.NORTH, exprationField, 20, SpringLayout.SOUTH, derivativeBox);

        for (int i = 1; i < elements.length; i++) {
            layoutInputs.putConstraint(SpringLayout.WEST, elements[i].label, 20, SpringLayout.WEST, panelInputs);
            layoutInputs.putConstraint(SpringLayout.NORTH, elements[i].label, 0, SpringLayout.NORTH, elements[i].field);
            layoutInputs.putConstraint(SpringLayout.WEST, elements[i].field, 20, SpringLayout.EAST, elements[0].label);
            layoutInputs.putConstraint(SpringLayout.NORTH, elements[i].field, 2, SpringLayout.SOUTH, elements[i-1].field);
            panelInputs.add(elements[i].label);
            panelInputs.add(elements[i].field);
        }

        layoutInputs.putConstraint(SpringLayout.EAST, panelInputs, 20, SpringLayout.EAST, exprationField);
        layoutInputs.putConstraint(SpringLayout.SOUTH, panelInputs, 10, SpringLayout.SOUTH, elements[elements.length-1].field);

        panelInputs.add(callPutBox);
        panelInputs.add(derivativeBox);
        panelInputs.add(exprationLabel);
        panelInputs.add(exprationField);

        JLabel l1 = new JLabel("Test!");
        JTextField t1 = new JTextField("", 15);
        JButton bt = new JButton("Click me");
        layoutOutputs.putConstraint(SpringLayout.WEST, l1, 20, SpringLayout.WEST, panelOutputs);
        layoutOutputs.putConstraint(SpringLayout.EAST, l1, 0, SpringLayout.EAST, exprationLabel);
        layoutOutputs.putConstraint(SpringLayout.NORTH, l1, 10, SpringLayout.NORTH, panelOutputs);
        layoutOutputs.putConstraint(SpringLayout.WEST, t1, 20, SpringLayout.EAST, l1);
        layoutOutputs.putConstraint(SpringLayout.NORTH, t1, 10, SpringLayout.NORTH, panelOutputs);
        frameLayout.putConstraint(SpringLayout.WEST, bt, 20, SpringLayout.WEST, panelFrame);
        frameLayout.putConstraint(SpringLayout.NORTH, bt, 10, SpringLayout.SOUTH, panelInputs);

        layoutOutputs.putConstraint(SpringLayout.EAST, panelOutputs, 20, SpringLayout.EAST, t1);
        layoutOutputs.putConstraint(SpringLayout.SOUTH, panelOutputs, 10, SpringLayout.SOUTH, t1);
        
        // frameLayout.putConstraint(SpringLayout.EAST, panelFrame, 20, SpringLayout.EAST, t1);
        // frameLayout.putConstraint(SpringLayout.SOUTH, panelFrame, 10, SpringLayout.SOUTH, t1);

        panelOutputs.add(l1);
        panelOutputs.add(t1);
        panelFrame.add(bt);

        frame.pack();
        frame.setVisible(true);

    }

}