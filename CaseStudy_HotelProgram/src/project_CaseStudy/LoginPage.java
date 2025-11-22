package project_CaseStudy;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel LStar_background; // <-- Declare here
    private JLabel lblNewLabel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }//Upload
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginPage() {
    	setResizable(false);
    	setTitle("Lanlya Hotel Reservation Manager - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel LStar_header = new JLabel("Lanlya Hotel Reservation Manager");
        LStar_header.setFont(new Font("Tahoma", Font.BOLD, 30));
        LStar_header.setHorizontalAlignment(SwingConstants.CENTER);
        LStar_header.setBounds(0, 0, 1010, 82);
        contentPane.add(LStar_header);
        
        lblNewLabel = new JLabel("Please Login to Continue");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(10, 56, 1010, 35);
        contentPane.add(lblNewLabel);
                
                textField = new JTextField();
                textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                textField.setBounds(255, 260, 500, 35);
                contentPane.add(textField);
                textField.setColumns(10);
                
                passwordField = new JPasswordField();
                passwordField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
                passwordField.setBounds(255, 329, 500, 35);
                contentPane.add(passwordField);
                
                UsernameLabel = new JLabel("USERNAME");
                UsernameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
                UsernameLabel.setBounds(255, 238, 166, 12);
                contentPane.add(UsernameLabel);
                        
                        PasswordLabel = new JLabel("PASSWORD");
                        PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
                        PasswordLabel.setBounds(255, 307, 335, 12);
                        contentPane.add(PasswordLabel);
                                
                                JButton LoginBtn = new JButton("LOGIN");
                                LoginBtn.setBackground(Color.ORANGE);
                                LoginBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
                                LoginBtn.setBounds(655, 374, 100, 27);
                                contentPane.add(LoginBtn);
                                
                                //Logic to Validate the login app
                                LoginBtn.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String user = textField.getText().trim();
                                        String pass = new String(passwordField.getPassword()).trim();

                                        if (UserData.validate(user, pass)) {
                                            dispose();
                                            
                                            AdminPage Dashboard = new AdminPage();
                                            Dashboard.setVisible(true);
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                                        }
                                    }
                                });

                                //Background
                                LStar_background = new JLabel(new ImageIcon(LoginPage.class.getResource("/project_CaseStudy/BG1.jpg")));
                                LStar_background.setHorizontalAlignment(SwingConstants.LEFT);
                                LStar_background.setBounds(0, 0, 1010, 683);
                                
                                        // Add background last so it sits behind other components
                                        contentPane.add(LStar_background);
                                        
                                        
                                        
                                        
                                        
                                        
    }
}