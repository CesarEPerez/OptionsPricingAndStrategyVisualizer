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

/**
 * FormElements class to store both the label and input/output field/label associated with it.
 * ex: Username: [          ]
 */
class FormElements {
    private JLabel label;
    private JLabel outputLabel;
    private JTextField inputField;
    public static final int FIELD = 0; // to be used to determine whether the right-hand element is a label or a field
    public static final int LABEL = 1; // to be used to determine whether the right-hand element is a label or a field

    public FormElements() {
        this.inputField = new JTextField("", 15); // initialize the field to be empty
    }

    public void setLabel(JLabel label) { this.label = label; }
    public void setOutputLabel(JLabel label) { this.outputLabel = label; }

    public JLabel getLabel() { return this.label; }
    public JLabel getOutputLabel() { return this.outputLabel; }
    public JTextField getInputField() { return this.inputField; }

}

/**
 * The main class for the program. Instantiates and display the user GUI.
 */
class UserGui {

    private static JFrame frame; // the outer frame (the window)
    private static JPanel panelInputs; // the input panel (left-hand panel)
    private static JPanel panelOutputs; // the output panel (right-hand panel)
    private static JPanel panelFrame; // the frame panel to store both input and output panels, as well as buttons and ComboBoxes

    public static void main(String[] args) {

        // initiate frame and panels
        frame = new JFrame();
        panelInputs = new JPanel();
        panelOutputs = new JPanel();
        panelFrame = new JPanel();
        SpringLayout frameLayout = new SpringLayout(); // initiate frame layout
        panelFrame.setLayout(frameLayout); // set frame layout
        frame.add(panelFrame); // add frame panel to the frame
        // add input and output panel to the frame panel
        panelFrame.add(panelInputs);
        panelFrame.add(panelOutputs);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(700,600);
        frame.setResizable(false);

        // create and position both ComboBoxes
        JComboBox derivativeBox = new JComboBox(new String[] {"European", "American"});
        JComboBox callPutBox = new JComboBox(new String[] {"Call", "Put"});
        frameLayout.putConstraint(SpringLayout.EAST, derivativeBox, 0, SpringLayout.EAST, panelOutputs);
        frameLayout.putConstraint(SpringLayout.NORTH, derivativeBox, 10, SpringLayout.NORTH, panelFrame);
        frameLayout.putConstraint(SpringLayout.EAST, callPutBox, 0, SpringLayout.WEST, derivativeBox);
        frameLayout.putConstraint(SpringLayout.NORTH, callPutBox, 10, SpringLayout.NORTH, panelFrame);

        // set top-left corner of both input/ouput panels
        frameLayout.putConstraint(SpringLayout.WEST, panelInputs, 5, SpringLayout.WEST, frame);
        frameLayout.putConstraint(SpringLayout.NORTH, panelInputs, 5, SpringLayout.SOUTH, derivativeBox);
        frameLayout.putConstraint(SpringLayout.WEST, panelOutputs, 5, SpringLayout.EAST, panelInputs);
        frameLayout.putConstraint(SpringLayout.NORTH, panelOutputs, 5, SpringLayout.SOUTH, derivativeBox);

        // add ComboBoxes to the frame panel
        panelFrame.add(callPutBox);
        panelFrame.add(derivativeBox);

        // create and set layout for both input/output panels, and set their title-borders
        SpringLayout layoutInputs = new SpringLayout();
        SpringLayout layoutOutputs = new SpringLayout();
        panelInputs.setLayout(layoutInputs);
        panelOutputs.setLayout(layoutOutputs);
        panelInputs.setBorder(BorderFactory.createTitledBorder("Inputs"));
        panelOutputs.setBorder(BorderFactory.createTitledBorder("Outputs"));

        // create and initialize FormElements list to store all form elements
        FormElements[] elements = new FormElements[10];
        for (int i = 0; i < elements.length; i++) elements[i] = new FormElements();

        // create the labels for the input panel (fields are initialized automatically inside the constructor)
        elements[0].setLabel(new JLabel("Time to expration (yr/mo):"));
        elements[1].setLabel(new JLabel("Underlying price ($):"));
        elements[2].setLabel(new JLabel("Strike price ($):"));
        elements[3].setLabel(new JLabel("Dividend yield (%):"));
        elements[4].setLabel(new JLabel("Annual risk-free rate (%):"));
        elements[5].setLabel(new JLabel("Annualized volatility (%):"));

        // position the input panel elements
        positionElements(elements, FormElements.FIELD, layoutInputs, panelInputs, elements[0].getLabel(), 0, 6, true);

        // set the bottom-right corner of the input panel
        layoutInputs.putConstraint(SpringLayout.EAST, panelInputs, 20, SpringLayout.EAST, elements[0].getInputField());
        layoutInputs.putConstraint(SpringLayout.SOUTH, panelInputs, 20, SpringLayout.SOUTH, elements[5].getLabel());

        // create the labels for the output panel, as well as the submit button
        elements[6].setLabel(new JLabel("Options price is:"));
        elements[6].setOutputLabel(new JLabel("$0.00"));
        elements[7].setLabel(new JLabel("Delta:"));
        elements[7].setOutputLabel(new JLabel("0"));
        elements[8].setLabel(new JLabel("Gamma:"));
        elements[8].setOutputLabel(new JLabel("0"));
        elements[9].setLabel(new JLabel("Theta:"));
        elements[9].setOutputLabel(new JLabel("0"));
        JButton buttonSubmit = new JButton("Submit");

        // position the output panel elements
        positionElements(elements, FormElements.LABEL, layoutOutputs, panelOutputs, elements[6].getLabel(), 6, 10, true);
        
        // position the button, as well as the bottom-right corner of the output panel and frame panel
        frameLayout.putConstraint(SpringLayout.WEST, buttonSubmit, 5, SpringLayout.WEST, panelFrame);
        frameLayout.putConstraint(SpringLayout.NORTH, buttonSubmit, 10, SpringLayout.SOUTH, panelInputs);
        layoutOutputs.putConstraint(SpringLayout.EAST, panelOutputs, 20, SpringLayout.EAST, elements[6].getOutputLabel());
        frameLayout.putConstraint(SpringLayout.SOUTH, panelOutputs, 0, SpringLayout.SOUTH, panelInputs);
        frameLayout.putConstraint(SpringLayout.EAST, panelFrame, 20, SpringLayout.EAST, panelOutputs);
        frameLayout.putConstraint(SpringLayout.SOUTH, panelFrame, 10, SpringLayout.SOUTH, buttonSubmit);

        // add the submit button to the frame panel
        panelFrame.add(buttonSubmit);

        // pack the frame and position it near the macOS centre position (half-way between the centre and top of the screen)
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLocation(frame.getX(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4);
        frame.setVisible(true);

        // implement the button clicking event
        buttonSubmit.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double S, X, q, r, stdev, t = 0; // calculation formula variables

                    // determine whether the expration time is in years or in months
                    String[] expration = elements[0].getInputField().getText().split(" ");
                    if (expration[1].equalsIgnoreCase("yr")) {
                        t = Double.parseDouble(expration[0]);
                    } else {
                        t = Double.parseDouble(expration[0]) / 12;
                    }

                    // get the variable numbers from the user
                    S = Double.parseDouble(elements[1].getInputField().getText());
                    X = Double.parseDouble(elements[2].getInputField().getText());
                    q = Double.parseDouble(elements[3].getInputField().getText());
                    r = Double.parseDouble(elements[4].getInputField().getText());
                    stdev = Double.parseDouble(elements[5].getInputField().getText());

                    // calculate the output
                    double callPrice = OptionsCalculations.call_calc (S, X, t, q, r, stdev);
                    double putPrice = OptionsCalculations.put_calc (S, X, t, q, r, stdev);
                    double callDelta = OptionsCalculations.call_delta_calc (S, X, t, q, r, stdev);
                    double putDelta = OptionsCalculations.put_delta_calc (S, X, t, q, r, stdev);
                    double callTheta = OptionsCalculations.call_theta_calc (S, X, t, q, r, stdev);
                    double putTheta = OptionsCalculations.put_theta_calc (S, X, t, q, r, stdev);
                    double gamma = OptionsCalculations.gamma_calc (S, X, t, q, r, stdev);
                    elements[8].getOutputLabel().setText(Double.toString(gamma)); // set the gamma

                    // set the other output labels depending on whether calculation is "call" or "put"
                    if (callPutBox.getSelectedIndex() == 0) { // call
                        elements[6].getOutputLabel().setText("$"+Double.toString(callPrice));
                        elements[7].getOutputLabel().setText(Double.toString(callDelta));
                        elements[9].getOutputLabel().setText(Double.toString(callTheta));
                    } else { // put
                        elements[6].getOutputLabel().setText("$"+Double.toString(putPrice));
                        elements[7].getOutputLabel().setText(Double.toString(putDelta));
                        elements[9].getOutputLabel().setText(Double.toString(putTheta));
                    }

                    // reposition output panel elements (due to label text changing), as well as the bottom-right corner of both input/output panels
                    positionElements(elements, FormElements.LABEL, layoutOutputs, panelOutputs, elements[6].getLabel(), 6, 10, false);
                    layoutInputs.putConstraint(SpringLayout.EAST, panelInputs, 20, SpringLayout.EAST, elements[0].getInputField());
                    layoutInputs.putConstraint(SpringLayout.SOUTH, panelInputs, 20, SpringLayout.SOUTH, elements[5].getLabel());
                    layoutOutputs.putConstraint(SpringLayout.EAST, panelOutputs, 20, SpringLayout.EAST, elements[6].getOutputLabel());
                    frameLayout.putConstraint(SpringLayout.SOUTH, panelOutputs, 0, SpringLayout.SOUTH, panelInputs);
                    
                }
            }
        );

    }

    /**
     * Function that automatically positions form elements in their correct position.
     * @param elements the FormElements list containing all elements
     * @param rightSide whether the right-hand side of the form is a label or a field
     * @param layout the layout manager being used
     * @param panel the panel the elements are found in
     * @param longestLabel the longest label in the form
     * @param start the start index of the form
     * @param end the end index of the form
     * @param add whether to add the elements in the form to the panel
     */
    private static void positionElements(FormElements[] elements, int rightSide, SpringLayout layout, JPanel panel, JLabel longestLabel, int start, int end, boolean add) {
        layout.putConstraint(SpringLayout.WEST, elements[start].getLabel(), 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, elements[start].getLabel(), 10, SpringLayout.NORTH, panel);
        if (add) panel.add(elements[start].getLabel());
        if (rightSide == FormElements.FIELD) {
            layout.putConstraint(SpringLayout.WEST, elements[start].getInputField(), 20, SpringLayout.EAST, longestLabel);
            layout.putConstraint(SpringLayout.NORTH, elements[start].getInputField(), 10, SpringLayout.NORTH, panel);
            if (add) panel.add(elements[start].getInputField());
        } else {
            layout.putConstraint(SpringLayout.WEST, elements[start].getOutputLabel(), 20, SpringLayout.EAST, longestLabel);
            layout.putConstraint(SpringLayout.NORTH, elements[start].getOutputLabel(), 10, SpringLayout.NORTH, panel);
            if (add) panel.add(elements[start].getOutputLabel());
        }
        for (int i = start+1; i < end; i++) {
            layout.putConstraint(SpringLayout.WEST, elements[i].getLabel(), 20, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, elements[i].getLabel(), 10, SpringLayout.SOUTH, elements[i-1].getLabel());
            if (add) panel.add(elements[i].getLabel());
            if (rightSide == FormElements.FIELD) {
                layout.putConstraint(SpringLayout.WEST, elements[i].getInputField(), 20, SpringLayout.EAST, longestLabel);
                layout.putConstraint(SpringLayout.NORTH, elements[i].getInputField(), 10, SpringLayout.SOUTH, elements[i-1].getLabel());
                layout.putConstraint(SpringLayout.EAST, elements[i].getInputField(), 0, SpringLayout.EAST, elements[start].getInputField());
                if (add) panel.add(elements[i].getInputField());
            } else {
                layout.putConstraint(SpringLayout.WEST, elements[i].getOutputLabel(), 20, SpringLayout.EAST, longestLabel);
                layout.putConstraint(SpringLayout.NORTH, elements[i].getOutputLabel(), 10, SpringLayout.SOUTH, elements[i-1].getLabel());
                if (add) panel.add(elements[i].getOutputLabel());
            }
        }
    }

}