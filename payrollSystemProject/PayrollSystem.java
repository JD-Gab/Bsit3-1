package payrollSystemProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayrollSystem {
    private JTextField nameField, hourlyRateField, hoursWorkedField, daysWorkedField, grossSalaryField;
    
    public PayrollSystem() {
        // Create the main frame
        JFrame mainFrame = new JFrame("PAYROLL SYSTEM");
        mainFrame.setSize(700, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.setLocationRelativeTo(null);
        
        // Left column (input)
        JPanel leftPanel = new JPanel(new GridLayout(7, 2));
        JLabel nameLabel = new JLabel("Employee Name:");
        nameField = new JTextField();
        JLabel rateLabel = new JLabel("Hourly Rate:");
        hourlyRateField = new JTextField();
        JLabel hoursLabel = new JLabel("Number of Hours Worked:");
        hoursWorkedField = new JTextField();
        JLabel daysLabel = new JLabel("Number of Days Worked:");
        daysWorkedField = new JTextField();
        JLabel grossLabel = new JLabel("Gross Salary:");
        grossSalaryField = new JTextField();
        
        
       
        
        leftPanel.add(nameLabel);
        leftPanel.add(nameField);
        leftPanel.add(rateLabel);
        leftPanel.add(hourlyRateField);
        leftPanel.add(hoursLabel);
        leftPanel.add(hoursWorkedField);
        leftPanel.add(daysLabel);
        leftPanel.add(daysWorkedField);
        leftPanel.add(grossLabel);
        leftPanel.add(grossSalaryField);
        
        
        
        
        // clear button and compute button
        JPanel rightPanel = new JPanel(new FlowLayout());
        JButton clearButton = new JButton("Clear");
        clearButton.setSize(100, 50);
        JButton computeButton = new JButton("Compute");
        computeButton.setSize(100, 50);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mainFrame.dispose();
                openComputeFrame();
            }
        });
        
        rightPanel.add(clearButton);
        rightPanel.add(computeButton);
        
        mainFrame.add(leftPanel);
        mainFrame.add(rightPanel);
        
        mainFrame.setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        hourlyRateField.setText("");
        hoursWorkedField.setText("");
        daysWorkedField.setText("");
        grossSalaryField.setText("");
    }
    
    private void openComputeFrame() {
        // Check if the gross salary is valid
        double grossSalary = 0.0;
        try {
            grossSalary = Double.parseDouble(grossSalaryField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Gross Salary");
            return;
        }

        JFrame computeFrame = new JFrame("Result...");
        computeFrame.setSize(300, 200);
        computeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        computeFrame.setLayout(new GridLayout(5, 2));
        computeFrame.setLocationRelativeTo(null);
        
        // Calculate deductions
        double tax = 0.15 * grossSalary;
        double healthInsurance = 0.05 * grossSalary;
        double sss = 0.03 * grossSalary;
        double totalDeduction = tax + healthInsurance + sss;
        double netSalary = grossSalary - totalDeduction;
        
        JLabel taxLabel = new JLabel("Monthly Tax (%15):");
        JLabel healthInsuranceLabel = new JLabel("Health Insurance (%5):");
        JLabel sssLabel = new JLabel("SSS (3%):");
        JLabel totalDeductionLabel = new JLabel("Total Deduction:");
        JLabel netSalaryLabel = new JLabel("Net Salary:");
        
        JLabel taxValueLabel = new JLabel(String.format("Php %.2f", tax));
        JLabel healthInsuranceValueLabel = new JLabel(String.format("Php %.2f", healthInsurance));
        JLabel sssValueLabel = new JLabel(String.format("Php %.2f", sss));
        JLabel totalDeductionValueLabel = new JLabel(String.format("Php %.2f", totalDeduction));
        JLabel netSalaryValueLabel = new JLabel(String.format("Php %.2f", netSalary));
        
        computeFrame.add(taxLabel);
        computeFrame.add(taxValueLabel);
        computeFrame.add(healthInsuranceLabel);
        computeFrame.add(healthInsuranceValueLabel);
        computeFrame.add(sssLabel);
        computeFrame.add(sssValueLabel);
        computeFrame.add(totalDeductionLabel);
        computeFrame.add(totalDeductionValueLabel);
        computeFrame.add(netSalaryLabel);
        computeFrame.add(netSalaryValueLabel);
        
        computeFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PayrollSystem());
    }
}

