import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class FormElements {
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JTextField outputField;
    public static final int FIELD = 0;
    public static final int LABEL = 1;

    public FormElements() {
        this.outputField = new JTextField("", 15);
    }

    public void setInput(JLabel input) { this.inputLabel = input; }
    public void setOutput(JLabel output) { this.outputLabel = output; }

    public JLabel getInput() { return this.inputLabel; }
    public JLabel getOutputLabel() { return this.outputLabel; }
    public JTextField getOutputField() { return this.outputField; }

}

class UserGui {

    private static JFrame frame;
    private static JPanel panelInputs;
    private static JPanel panelOutputs;
    private static JPanel panelFrame;

    public static void main(String[] args) {

        frame = new JFrame();
        panelInputs = new JPanel();
        panelOutputs = new JPanel();
        panelFrame = new JPanel();
        SpringLayout frameLayout = new SpringLayout();
        panelFrame.setLayout(frameLayout);
        frame.add(panelFrame);
        panelFrame.add(panelInputs);
        panelFrame.add(panelOutputs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(700,600);
        // frame.setResizable(false);

        JComboBox derivativeBox = new JComboBox(new String[] {"European", "American"});
        JComboBox callPutBox = new JComboBox(new String[] {"Call", "Put"});
        frameLayout.putConstraint(SpringLayout.EAST, derivativeBox, 0, SpringLayout.EAST, panelOutputs);
        frameLayout.putConstraint(SpringLayout.NORTH, derivativeBox, 10, SpringLayout.NORTH, panelFrame);
        frameLayout.putConstraint(SpringLayout.EAST, callPutBox, 0, SpringLayout.WEST, derivativeBox);
        frameLayout.putConstraint(SpringLayout.NORTH, callPutBox, 10, SpringLayout.NORTH, panelFrame);

        frameLayout.putConstraint(SpringLayout.WEST, panelInputs, 5, SpringLayout.WEST, frame);
        frameLayout.putConstraint(SpringLayout.NORTH, panelInputs, 5, SpringLayout.SOUTH, derivativeBox);
        frameLayout.putConstraint(SpringLayout.WEST, panelOutputs, 5, SpringLayout.EAST, panelInputs);
        frameLayout.putConstraint(SpringLayout.NORTH, panelOutputs, 5, SpringLayout.SOUTH, derivativeBox);

        panelFrame.add(callPutBox);
        panelFrame.add(derivativeBox);

        SpringLayout layoutInputs = new SpringLayout();
        SpringLayout layoutOutputs = new SpringLayout();
        panelInputs.setLayout(layoutInputs);
        panelOutputs.setLayout(layoutOutputs);
        panelInputs.setBorder(BorderFactory.createTitledBorder("Inputs"));
        panelOutputs.setBorder(BorderFactory.createTitledBorder("Outputs"));

        FormElements[] elements = new FormElements[10];
        for (int i = 0; i < elements.length; i++) elements[i] = new FormElements();

        elements[0].setInput(new JLabel("Time to expration (yr/mo):"));
        elements[1].setInput(new JLabel("Underlying price ($):"));
        elements[2].setInput(new JLabel("Strike price ($):"));
        elements[3].setInput(new JLabel("Dividend yield (%):"));
        elements[4].setInput(new JLabel("Annual risk-free rate (%):"));
        elements[5].setInput(new JLabel("Annualized volatility (%):"));

        positionElements(elements, FormElements.FIELD, layoutInputs, panelInputs, elements[0].getInput(), 0, 6, true);

        layoutInputs.putConstraint(SpringLayout.EAST, panelInputs, 20, SpringLayout.EAST, elements[0].getOutputField());
        layoutInputs.putConstraint(SpringLayout.SOUTH, panelInputs, 20, SpringLayout.SOUTH, elements[5].getInput());

        elements[6].setInput(new JLabel("Options price is:"));
        elements[6].setOutput(new JLabel("$0.00"));
        elements[7].setInput(new JLabel("Delta:"));
        elements[7].setOutput(new JLabel("0"));
        elements[8].setInput(new JLabel("Gamma:"));
        elements[8].setOutput(new JLabel("0"));
        elements[9].setInput(new JLabel("Theta:"));
        elements[9].setOutput(new JLabel("0"));
        JButton buttonSubmit = new JButton("Submit");

        positionElements(elements, FormElements.LABEL, layoutOutputs, panelOutputs, elements[6].getInput(), 6, 10, true);
        
        frameLayout.putConstraint(SpringLayout.WEST, buttonSubmit, 5, SpringLayout.WEST, panelFrame);
        frameLayout.putConstraint(SpringLayout.NORTH, buttonSubmit, 10, SpringLayout.SOUTH, panelInputs);
        layoutOutputs.putConstraint(SpringLayout.EAST, panelOutputs, 20, SpringLayout.EAST, elements[6].getOutputLabel());
        frameLayout.putConstraint(SpringLayout.SOUTH, panelOutputs, 0, SpringLayout.SOUTH, panelInputs);
        frameLayout.putConstraint(SpringLayout.EAST, panelFrame, 20, SpringLayout.EAST, panelOutputs);
        frameLayout.putConstraint(SpringLayout.SOUTH, panelFrame, 10, SpringLayout.SOUTH, buttonSubmit);

        panelFrame.add(buttonSubmit);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLocation(frame.getX(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
        frame.setVisible(true);

        buttonSubmit.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double S, X, q, r, stdev;
                    double t = 0;
                    String[] expration = elements[0].getOutputField().getText().split(" ");
                    if (expration[1].equalsIgnoreCase("yr")) {
                        t = Double.parseDouble(expration[0]);
                    } else {
                        t = Double.parseDouble(expration[0]) / 12;
                    }
                    S = Double.parseDouble(elements[1].getOutputField().getText());
                    X = Double.parseDouble(elements[2].getOutputField().getText());
                    q = Double.parseDouble(elements[3].getOutputField().getText());
                    r = Double.parseDouble(elements[4].getOutputField().getText());
                    stdev = Double.parseDouble(elements[5].getOutputField().getText());

                    double callPrice = OptionsCalculations.call_calc (S, X, t, q, r, stdev);
                    double putPrice = OptionsCalculations.put_calc (S, X, t, q, r, stdev);
                    double callDelta = OptionsCalculations.call_delta_calc (S, X, t, q, r, stdev);
                    double putDelta = OptionsCalculations.put_delta_calc (S, X, t, q, r, stdev);
                    double callTheta = OptionsCalculations.call_theta_calc (S, X, t, q, r, stdev);
                    double putTheta = OptionsCalculations.put_theta_calc (S, X, t, q, r, stdev);
                    double gamma = OptionsCalculations.gamma_calc (S, X, t, q, r, stdev);
                    elements[8].getOutputLabel().setText(Double.toString(gamma));

                    if (callPutBox.getSelectedIndex() == 0) { // call
                        elements[6].getOutputLabel().setText("$"+Double.toString(callPrice));
                        elements[7].getOutputLabel().setText(Double.toString(callDelta));
                        elements[9].getOutputLabel().setText(Double.toString(callTheta));
                    } else { // put
                        elements[6].getOutputLabel().setText("$"+Double.toString(putPrice));
                        elements[7].getOutputLabel().setText(Double.toString(putDelta));
                        elements[9].getOutputLabel().setText(Double.toString(putTheta));
                    }

                    positionElements(elements, FormElements.LABEL, layoutOutputs, panelOutputs, elements[6].getInput(), 6, 10, false);
                    layoutInputs.putConstraint(SpringLayout.EAST, panelInputs, 20, SpringLayout.EAST, elements[0].getOutputField());
                    layoutInputs.putConstraint(SpringLayout.SOUTH, panelInputs, 20, SpringLayout.SOUTH, elements[5].getInput());
                    layoutOutputs.putConstraint(SpringLayout.EAST, panelOutputs, 20, SpringLayout.EAST, elements[6].getOutputLabel());
                    frameLayout.putConstraint(SpringLayout.SOUTH, panelOutputs, 0, SpringLayout.SOUTH, panelInputs);
                    
                }
            }
        );

    }

    private static void positionElements(FormElements[] elements, int output, SpringLayout layout, JPanel panel, JLabel longestLabel, int start, int end, boolean add) {
        layout.putConstraint(SpringLayout.WEST, elements[start].getInput(), 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, elements[start].getInput(), 10, SpringLayout.NORTH, panel);
        if (add) panel.add(elements[start].getInput());
        if (output == FormElements.FIELD) {
            layout.putConstraint(SpringLayout.WEST, elements[start].getOutputField(), 20, SpringLayout.EAST, longestLabel);
            layout.putConstraint(SpringLayout.NORTH, elements[start].getOutputField(), 10, SpringLayout.NORTH, panel);
            if (add) panel.add(elements[start].getOutputField());
        } else {
            layout.putConstraint(SpringLayout.WEST, elements[start].getOutputLabel(), 20, SpringLayout.EAST, longestLabel);
            layout.putConstraint(SpringLayout.NORTH, elements[start].getOutputLabel(), 10, SpringLayout.NORTH, panel);
            if (add) panel.add(elements[start].getOutputLabel());
        }
        for (int i = start+1; i < end; i++) {
            layout.putConstraint(SpringLayout.WEST, elements[i].getInput(), 20, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, elements[i].getInput(), 10, SpringLayout.SOUTH, elements[i-1].getInput());
            if (add) panel.add(elements[i].getInput());
            if (output == FormElements.FIELD) {
                layout.putConstraint(SpringLayout.WEST, elements[i].getOutputField(), 20, SpringLayout.EAST, longestLabel);
                layout.putConstraint(SpringLayout.NORTH, elements[i].getOutputField(), 10, SpringLayout.SOUTH, elements[i-1].getInput());
                layout.putConstraint(SpringLayout.EAST, elements[i].getOutputField(), 0, SpringLayout.EAST, elements[start].getOutputField());
                if (add) panel.add(elements[i].getOutputField());
            } else {
                layout.putConstraint(SpringLayout.WEST, elements[i].getOutputLabel(), 20, SpringLayout.EAST, longestLabel);
                layout.putConstraint(SpringLayout.NORTH, elements[i].getOutputLabel(), 10, SpringLayout.SOUTH, elements[i-1].getInput());
                if (add) panel.add(elements[i].getOutputLabel());
            }
        }
    }

}