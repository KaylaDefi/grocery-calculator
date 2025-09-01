import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class GroceryCalculatorUI extends JFrame {
    private final JTextField tfCoupon = new JTextField(".10", 8);
    private final JTextField tfW1 = new JTextField(8);
    private final JTextField tfW2 = new JTextField(8);
    private final JTextField tfW3 = new JTextField(8);
    private final JTextField tfW4 = new JTextField(8);

    private final JTextField tfMonthlyNo = new JTextField(12);
    private final JTextField tfWeeklyNo  = new JTextField(12);
    private final JTextField tfMonthlyYes= new JTextField(12);
    private final JTextField tfWeeklyYes = new JTextField(12);

    private final DecimalFormat money = new DecimalFormat("$#,##0.00");

    public GroceryCalculatorUI() {
        super("Grocery Calculator (Swing)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);

        for (JTextField tf : new JTextField[]{tfMonthlyNo, tfWeeklyNo, tfMonthlyYes, tfWeeklyYes}) {
            tf.setEditable(false);
            tf.setBackground(new Color(245,245,245));
        }

        setLayout(new BorderLayout(10,10));
        add(buildInputs(), BorderLayout.NORTH);
        add(buildOutputs(), BorderLayout.CENTER);
        add(buildButtons(), BorderLayout.SOUTH);
    }

    private JPanel buildInputs() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Inputs"));
        GridBagConstraints c = gbc();

        addL(p, "Coupon (decimal, e.g., .10 = 10%):", 0,0,c); addF(p, tfCoupon, 1,0,c);
        addL(p, "Week 1:", 0,1,c); addF(p, tfW1, 1,1,c);
        addL(p, "Week 2:", 0,2,c); addF(p, tfW2, 1,2,c);
        addL(p, "Week 3:", 0,3,c); addF(p, tfW3, 1,3,c);
        addL(p, "Week 4:", 0,4,c); addF(p, tfW4, 1,4,c);
        return p;
    }

    private JPanel buildOutputs() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Results"));
        GridBagConstraints c = gbc();

        addL(p, "Monthly Total (no coupon):", 0,0,c); addF(p, tfMonthlyNo, 1,0,c);
        addL(p, "Weekly Average (no coupon):",0,1,c); addF(p, tfWeeklyNo,  1,1,c);
        addL(p, "Monthly Total (with coupon):",0,2,c); addF(p, tfMonthlyYes,1,2,c);
        addL(p, "Weekly Average (with coupon):",0,3,c); addF(p, tfWeeklyYes, 1,3,c);
        return p;
    }

    private JPanel buildButtons() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton calc = new JButton("Calculate");
        JButton reset = new JButton("Reset");
        JButton exit = new JButton("Exit");

        calc.addActionListener(this::onCalculate);
        reset.addActionListener(e -> resetFields());
        exit.addActionListener(e -> dispose());

        p.add(calc); p.add(reset); p.add(exit);
        return p;
    }

    private void onCalculate(ActionEvent e) {
        try {
            double coupon = parseCoupon(tfCoupon.getText());
            double w1 = parseMoney(tfW1.getText(), "Week 1");
            double w2 = parseMoney(tfW2.getText(), "Week 2");
            double w3 = parseMoney(tfW3.getText(), "Week 3");
            double w4 = parseMoney(tfW4.getText(), "Week 4");

            double monthly = w1 + w2 + w3 + w4;
            double weeklyAvg = monthly / 4.0;
            double monthlyWith = monthly - (monthly * coupon);
            double weeklyWith = monthlyWith / 4.0;

            tfMonthlyNo.setText(money.format(monthly));
            tfWeeklyNo.setText(money.format(weeklyAvg));
            tfMonthlyYes.setText(money.format(monthlyWith));
            tfWeeklyYes.setText(money.format(weeklyWith));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseCoupon(String s) {
        try {
            double c = Double.parseDouble(s.trim());
            if (c <= 0 || c > 1) return 0.10;
            return c;
        } catch (Exception ex) {
            return 0.10;
        }
    }
    private double parseMoney(String s, String label) {
        if (s == null || s.trim().isEmpty()) throw new NumberFormatException(label + ": value is required.");
        double v = Double.parseDouble(s.trim());
        if (v < 0) throw new NumberFormatException(label + ": value cannot be negative.");
        return v;
    }
    private void resetFields() {
        tfCoupon.setText(".10");
        tfW1.setText(""); tfW2.setText(""); tfW3.setText(""); tfW4.setText("");
        tfMonthlyNo.setText(""); tfWeeklyNo.setText(""); tfMonthlyYes.setText(""); tfWeeklyYes.setText("");
        tfCoupon.requestFocus();
    }

    private static GridBagConstraints gbc() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        return c;
    }
    private static void addL(JPanel p, String text, int x, int y, GridBagConstraints base) {
        GridBagConstraints c = (GridBagConstraints) base.clone();
        c.gridx = x; c.gridy = y; c.weightx = 0.0;
        p.add(new JLabel(text), c);
    }
    private static void addF(JPanel p, JComponent comp, int x, int y, GridBagConstraints base) {
        GridBagConstraints c = (GridBagConstraints) base.clone();
        c.gridx = x; c.gridy = y; c.weightx = 1.0;
        p.add(comp, c);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new GroceryCalculatorUI().setVisible(true));
    }
}
