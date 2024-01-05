import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class SignUpMenu extends JFrame implements ActionListener {
    Container container;
    JTextField username = new JTextField("username");
    JPasswordField password = new JPasswordField("password");
    JButton signUp = new JButton("SIGN UP");
    String[] types = {"Admin" , "Seller" , "Customer"};
    JComboBox type = new JComboBox(types);
    JCheckBox showPW = new JCheckBox("Show Password");
    Font font = new Font(Font.SERIF,Font.BOLD,20);
    File file = new File("Priority");

    public SignUpMenu() {
        design();
    }

    private void design() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        container = getContentPane();
        container.setBackground(new Color(0X9973e2));
        //username text field
        usernameField();
        //password text field
        passwordField();
        //check box
        checkbox();
        //combo box
        comboBox();
        //sign up button
        signupBtn();

        container.add(showPW);
        container.add(type);
        container.add(username);
        container.add(password);
        container.add(signUp);
        this.setVisible(true);
    }

    private void usernameField(){
        username.setBounds(45,60,400,50);
        username.setOpaque(false);
        username.setFont(font);
        username.setForeground(new Color(0X3e6545));
    }
    private void passwordField() {
        password.setBounds(45,150,400,50);
        password.setOpaque(false);
        password.setFont(font);
        password.setForeground(new Color(0X3e6545));
        password.setEchoChar('*');
    }
    private void checkbox() {
        showPW.setBounds(45,205,400,30);
        showPW.setOpaque(false);
        showPW.setFont(font);
        showPW.setForeground(new Color(0xE2014938, true));
        showPW.setFocusable(false);
        showPW.addActionListener(this);
    }
    private void comboBox() {
        type.setBounds(145,250,200, 50);
        type.setBackground(new Color(0x3e6545));
        type.setFont(font);
        type.setForeground(new Color(0Xecffb1));
        type.setFocusable(false);
    }
    private void signupBtn() {
        signUp.setBounds(170,330,150,70);
        signUp.addActionListener(this);
        signUp.setBackground(new Color(0X3e6545));
        signUp.setFont(font);
        signUp.setForeground(new Color(0Xecffb1));
        signUp.setFocusable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (showPW.isSelected()) {
            password.setEchoChar((char) 0);
            showPW.setText("Hide Password");
        }else {
            password.setEchoChar('*');
            showPW.setText("Show Password");
        }
        if (e.getSource() == signUp) {
            try {
                if (checkAccount()) {
                    FileWriter fileWriter = new FileWriter(file,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter out = new PrintWriter(bufferedWriter);
                    out.println(username.getText()+" "+password.getText()+" "+numPriority());
                    out.close();
                    new LogInMenu();
                    this.dispose();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private boolean checkAccount () throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            if (line.next().equals(username.getText())) {
                JOptionPane.showMessageDialog(null,"This username already is taken.",
                        "Try another username.",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            line.close();
        }
        scanner.close();
        return true;
    }
    private int numPriority () {
        return type.getSelectedIndex()+1;
    }
    }

