import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiSubjectGradeCalculator extends JFrame {

    private JTextField txtName;
    private JTextField[] txtMarks;
    private JLabel lblName, lblMarks, lblTotal, lblAverage, lblPercentage, lblGrade;
    private JButton btnCalculate;

    public MultiSubjectGradeCalculator() {
    
        setTitle("Multi-Subject Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        txtName = new JTextField(10);
        txtMarks = new JTextField[3]; 
        for (int i = 0; i < txtMarks.length; i++) {
            txtMarks[i] = new JTextField(10);
        }
        lblName = new JLabel("Enter Name:");
        lblMarks = new JLabel("Enter Marks for Each Subject :");
        lblTotal = new JLabel("Total Marks:");
        lblAverage = new JLabel("Average Marks:");
        lblPercentage = new JLabel("Percentage:");
        lblGrade = new JLabel("");
        btnCalculate = new JButton("Calculate Result");

    
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    
        add(lblName);
        add(txtName);
        add(lblMarks);
        for (JTextField txtMark : txtMarks) {
            add(txtMark);
        }
        add(btnCalculate);
        add(lblTotal);
        add(lblAverage);
        add(lblPercentage);
        add(lblGrade);


        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        try {
            
            String name = txtName.getText();

            double[] marks = new double[txtMarks.length];
            for (int i = 0; i < txtMarks.length; i++) {
                marks[i] = Double.parseDouble(txtMarks[i].getText());
            }
            double totalMarks = calculateTotal(marks);
            double averageMarks = calculateAverage(marks);
            double percentage = (totalMarks / (marks.length * 100)) * 100;

            
            String grade = determineGrade(percentage);

    
            lblTotal.setText("Total Marks: " + totalMarks);
            lblAverage.setText("Average Marks: " + averageMarks);
            lblPercentage.setText("Percentage: " + percentage + "%");
            lblGrade.setText("Overall Grade for " + name + ": " + grade);
        } catch (NumberFormatException ex) {
            
            lblGrade.setText("Invalid mark. Please enter numeric values.");
        }
    }

    private double calculateTotal(double[] marks) {
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum;
    }

    private double calculateAverage(double[] marks) {
        return calculateTotal(marks) / marks.length;
    }

    private String determineGrade(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MultiSubjectGradeCalculator().setVisible(true);
            }
        });
    }
}
