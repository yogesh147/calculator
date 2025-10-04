import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame {
    private JTextField num1Field, num2Field, resultField;
    private JComboBox<String> operatorBox;
    private JButton calculateButton;

    public CalculatorUI() {
        setTitle("Calculator UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        Color bgColor = new Color(40, 44, 52); // dark background
        Color fgColor = new Color(220, 220, 220); // light foreground
        Color btnColor = new Color(98, 114, 164); // button color
        Color fieldColor = new Color(56, 60, 74); // input field color

        getContentPane().setBackground(bgColor);

        JLabel label1 = new JLabel("First Number:");
        label1.setForeground(fgColor);
        add(label1);
        num1Field = new JTextField();
        num1Field.setBackground(fieldColor);
        num1Field.setForeground(fgColor);
        add(num1Field);

        JLabel label2 = new JLabel("Second Number:");
        label2.setForeground(fgColor);
        add(label2);
        num2Field = new JTextField();
        num2Field.setBackground(fieldColor);
        num2Field.setForeground(fgColor);
        add(num2Field);

        JLabel label3 = new JLabel("Operator:");
        label3.setForeground(fgColor);
        add(label3);
        operatorBox = new JComboBox<>(new String[]{"+", "-", "*", "/"});
        operatorBox.setBackground(fieldColor);
        operatorBox.setForeground(fgColor);
        add(operatorBox);

        JLabel label4 = new JLabel("Result:");
        label4.setForeground(fgColor);
        add(label4);
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setBackground(fieldColor);
        resultField.setForeground(fgColor);
        add(resultField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBackground(btnColor);
        calculateButton.setForeground(fgColor);
        add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    String operator = (String) operatorBox.getSelectedItem();
                    double result = 0;
                    switch (operator) {
                        case "+": result = num1 + num2; break;
                        case "-": result = num1 - num2; break;
                        case "*": result = num1 * num2; break;
                        case "/":
                            if (num2 != 0) result = num1 / num2;
                            else { resultField.setText("Error: Div by 0"); return; }
                            break;
                    }
                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    resultField.setText("Error: Invalid input");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorUI().setVisible(true);
        });
    }
}
