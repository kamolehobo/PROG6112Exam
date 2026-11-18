package com.mycompany.progexam;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * GUI form for capturing and processing a mattress sale.
 * Shows the results in a message box and saves them to data.txt.
 */
public class ProductApp extends JFrame {

    
    private static final String[] PRODUCTS = {
        "Cozy Nights Monarch",
        "Restonic Barbados",
        "Sealy Phuket"
    };
    private static final String FILE_NAME = "data.txt";
    private static final String SEPARATOR = "*".repeat(30);

    
    private final JTextField txtCustomerName = new JTextField(20);
    private final JComboBox<String> cmbProduct = new JComboBox<>(PRODUCTS);
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(createMenuBar());
        add(createFormPanel(), BorderLayout.CENTER);

        
        btnProcess.addActionListener(e -> processSale());

        pack();
        setLocationRelativeTo(null); 
    }

    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> processSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomerName);
        panel.add(new JLabel("Select Product:"));
        panel.add(cmbProduct);
        panel.add(new JLabel("Enter Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel()); 
        panel.add(btnProcess);

        return panel;
    }

    
    private void processSale() {
        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cmbProduct.getSelectedItem();

        
        if (customerName.isEmpty()) {
            showError("Please enter the customer name.");
            return;
        }

        
        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            showError("Please enter a valid numeric price.");
            return;
        }
        if (price <= 0) {
            showError("Price must be greater than 0.");
            return;
        }

        
        IProductCalculations calc = new ProductCalculations(price);

        
        String details = "Customer Name: " + customerName + "\n"
                + "Product: " + productName + "\n"
                + "VAT: R " + calc.GetVat() + "\n"
                + "COMMISSION: R " + calc.GetCommission() + "\n"
                + "TOTAL: R " + calc.GetTotal();

        JOptionPane.showMessageDialog(this, details, "Message",
                JOptionPane.INFORMATION_MESSAGE);

        saveToFile(details);
    }

    
    private void saveToFile(String details) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("DATA LOG");
            writer.newLine();
            writer.write(SEPARATOR);
            writer.newLine();
            writer.write(details);
            writer.newLine();
            writer.write(SEPARATOR);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            showError("Could not save to " + FILE_NAME + ": " + e.getMessage());
        }
    }

    
    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
        txtCustomerName.requestFocus();
    }

    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error",
                JOptionPane.ERROR_MESSAGE);
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}