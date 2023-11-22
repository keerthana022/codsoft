import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {
    private JTextField amountField;
    private JTextArea displayArea;
    private double balance = 1000.0; 

    public ATMGUI() {
        super("ATM Machine");

        amountField = new JTextField(10);
        displayArea = new JTextArea(10, 25);
        displayArea.setEditable(false);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");

        setLayout(new FlowLayout());

        add(new JLabel("Enter Amount:"));
        add(amountField);
        add(withdrawButton);
        add(depositButton);
        add(new JScrollPane(displayArea));


        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
    }

    private void withdraw() {
        double amount = Double.parseDouble(amountField.getText());

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            displayTransaction("Withdrawal: $" + amount);
        } else {
            displayTransaction("Invalid withdrawal amount.");
        }

        displayBalance();
    }

    private void deposit() {
        double amount = Double.parseDouble(amountField.getText());

        if (amount > 0) {
            balance += amount;
            displayTransaction("Deposit: $" + amount);
        } else {
            displayTransaction("Invalid deposit amount.");
        }

        displayBalance();
    }

    private void displayTransaction(String transaction) {
        displayArea.append(transaction + "\n");
    }

    private void displayBalance() {
        displayArea.append("Current Balance: $" + balance + "\n");
    }

    public static void main(String[] args) {
        ATMGUI atm = new ATMGUI();
        atm.setSize(300, 300);
        atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atm.setVisible(true);
    }
}
