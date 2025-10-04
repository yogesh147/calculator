import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class CalculatorUI extends JFrame {
    private final JTextField num1Field, num2Field, resultField;
    private final JButton[] operatorButtons;
    private String selectedOperator = "+";
    private final JButton calculateButton;

    // Scientific operators
    private static final String[] OPERATORS = {
            "+", "-", "*", "/", "sin", "cos", "tan", "log", "sqrt", "pow"
    };

    // Custom panel for solid white background
    static class WhitePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public CalculatorUI() {
        setTitle("Calculator UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);

        WhitePanel mainPanel = new WhitePanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Color fgColor = Color.BLACK;
        Color fieldColor = Color.WHITE;

        Border roundedBorder = new EmptyBorder(5, 10, 5, 10);
        int borderRadius = 18;
        Color btnNormalBg = Color.WHITE;
        Color btnHoverBg = new Color(30, 144, 255);
        Color btnNormalFg = Color.BLACK;
        Color btnHoverFg = Color.WHITE;
        Color txtNormalBg = Color.WHITE;
        Color txtHoverBg = new Color(220, 220, 220);
        Color txtNormalFg = Color.BLACK;
        Color txtHoverFg = new Color(30, 144, 255);

        JLabel label1 = new JLabel("First Number:");
        label1.setForeground(fgColor);
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(label1, gbc);
        num1Field = new JTextField() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
                g2.dispose();
            }
        };
        num1Field.setBackground(txtNormalBg);
        num1Field.setForeground(txtNormalFg);
        num1Field.setBorder(roundedBorder);
        num1Field.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(num1Field, gbc);
        // Only allow numbers in num1Field
        num1Field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '\b') {
                    e.consume();
                }
            }
        });
        num1Field.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                num1Field.setBackground(txtHoverBg);
                num1Field.setForeground(txtHoverFg);
            }
            public void mouseExited(MouseEvent e) {
                num1Field.setBackground(txtNormalBg);
                num1Field.setForeground(txtNormalFg);
            }
        });

        JLabel label2 = new JLabel("Second Number:");
        label2.setForeground(fgColor);
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(label2, gbc);
        num2Field = new JTextField() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
                g2.dispose();
            }
        };
        num2Field.setBackground(txtNormalBg);
        num2Field.setForeground(txtNormalFg);
        num2Field.setBorder(roundedBorder);
        num2Field.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(num2Field, gbc);
        // Only allow numbers in num2Field
        num2Field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '\b') {
                    e.consume();
                }
            }
        });
        num2Field.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                num2Field.setBackground(txtHoverBg);
                num2Field.setForeground(txtHoverFg);
            }
            public void mouseExited(MouseEvent e) {
                num2Field.setBackground(txtNormalBg);
                num2Field.setForeground(txtNormalFg);
            }
        });

        JLabel label3 = new JLabel("Operators:");
        label3.setForeground(fgColor);
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(label3, gbc);

        JPanel opPanel = new JPanel();
        opPanel.setBackground(fieldColor);
        opPanel.setLayout(new GridLayout(2, 5, 5, 5));
        operatorButtons = new JButton[OPERATORS.length];
        for (int i = 0; i < OPERATORS.length; i++) {
            String op = OPERATORS[i];
            operatorButtons[i] = new JButton(op) {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
                    g2.dispose();
                }
            };
            operatorButtons[i].setFont(new Font("Arial", Font.BOLD, 15));
            operatorButtons[i].setBackground(btnNormalBg);
            operatorButtons[i].setForeground(btnNormalFg);
            operatorButtons[i].setBorder(roundedBorder);
            operatorButtons[i].setFocusPainted(false);
            opPanel.add(operatorButtons[i]);
            operatorButtons[i].addActionListener(e -> {
                selectedOperator = op;
                for (JButton btn : operatorButtons) {
                    btn.setBackground(btnNormalBg);
                    btn.setForeground(btnNormalFg);
                }
                ((JButton) e.getSource()).setBackground(btnHoverBg);
                ((JButton) e.getSource()).setForeground(btnHoverFg);
            });
            operatorButtons[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    JButton btn = (JButton)e.getSource();
                    if (!btn.getBackground().equals(btnHoverBg)) {
                        btn.setBackground(btnHoverBg);
                        btn.setForeground(btnHoverFg);
                    }
                }
                public void mouseExited(MouseEvent e) {
                    JButton btn = (JButton)e.getSource();
                    if (!btn.equals(operatorButtons[getOperatorIndex(selectedOperator)])) {
                        btn.setBackground(btnNormalBg);
                        btn.setForeground(btnNormalFg);
                    }
                }
            });
        }
        operatorButtons[0].setBackground(Color.LIGHT_GRAY); // default selected
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(opPanel, gbc);

        calculateButton = new JButton("Calculate") {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
                g2.dispose();
            }
        };
        calculateButton.setForeground(btnNormalFg);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(btnNormalBg);
        calculateButton.setBorder(roundedBorder);
        calculateButton.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        mainPanel.add(calculateButton, gbc);
        calculateButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                calculateButton.setBackground(btnHoverBg);
                calculateButton.setForeground(btnHoverFg);
            }
            public void mouseExited(MouseEvent e) {
                calculateButton.setBackground(btnNormalBg);
                calculateButton.setForeground(btnNormalFg);
            }
        });
        JLabel label4 = new JLabel("Result:");
        label4.setForeground(fgColor);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        mainPanel.add(label4, gbc);
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setBackground(fieldColor);
        resultField.setForeground(fgColor);
        resultField.setBorder(roundedBorder);
        resultField.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(resultField, gbc);

        setContentPane(mainPanel);

        calculateButton.addActionListener(e -> {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double result = 0;
                String operator = selectedOperator;
                double num2 = 0;
                if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("pow")) {
                    num2 = Double.parseDouble(num2Field.getText());
                }
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 != 0) result = num1 / num2;
                        else { resultField.setText("Error: Div by 0"); return; }
                        break;
                    case "sin": result = Math.sin(Math.toRadians(num1)); break;
                    case "cos": result = Math.cos(Math.toRadians(num1)); break;
                    case "tan": result = Math.tan(Math.toRadians(num1)); break;
                    case "log":
                        if (num1 > 0) result = Math.log10(num1);
                        else { resultField.setText("Error: log input"); return; }
                        break;
                    case "sqrt":
                        if (num1 >= 0) result = Math.sqrt(num1);
                        else { resultField.setText("Error: sqrt input"); return; }
                        break;
                    case "pow": result = Math.pow(num1, num2); break;
                }
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                resultField.setText("Error: Invalid input");
            }
        });
    }

    private int getOperatorIndex(String op) {
        for (int i = 0; i < OPERATORS.length; i++) {
            if (OPERATORS[i].equals(op)) return i;
        }
        return 0;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorUI().setVisible(true));
    }
}
