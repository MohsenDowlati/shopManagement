import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogInMenu extends JFrame implements ActionListener {
    JTextField username = new JTextField("username");
    JPasswordField password = new JPasswordField("password");
    Container container;
    JButton login = new JButton("LOGIN");
    JButton signUp = new JButton("SIGN UP");
    Font font = new Font(Font.SERIF,Font.BOLD,20);
    File file = new File("Priority");
    public LogInMenu() {
        design();
    }

    private void design() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        container = getContentPane();
        container.setBackground(new Color(0X9973e2));

        //username
        usernameField();
        //password
        passwordField();
        //login
        loginBtn();
        //sign up
        signupBtn();

        container.add(login);
        container.add(signUp);
        container.add(password);
        container.add(username);
        this.setVisible(true);
    }

    private void usernameField() {
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

    private void loginBtn() {
        login.setBounds(170,250,150,70);
        login.addActionListener(this);
        login.setBackground(new Color(0X3e6545));
        login.setFont(font);
        login.setForeground(new Color(0Xecffb1));
        login.setFocusable(false);
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
        if (e.getSource() == login) {
            String un = username.getText();
            String pw = password.getText();
            try {
                int priority = checkPriority(un,pw);
                /*
                    Admin -> list of users , list of orders , list of goods
                    Seller -> list of goods , list of orders , add goods
                    Costumer -> list of goods , cart , list of orders
                */
                switch (priority) {
                    //login as admin
                    case 1:   new AdminPanel();break;

                    case -1:
                        System.out.println("not found");

                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        if (e.getSource() == signUp) {
            new SignUpMenu();
            this.dispose();
        }
    }

    public int checkPriority (String username,String password) throws FileNotFoundException {
        int p=-1;
        Scanner checker = new Scanner(file);
        while (checker.hasNextLine()) {
            String line = checker.nextLine();
            Scanner finder = new Scanner(line);
            String name = finder.next();
            if (name.equals(username)){
                String word = finder.next();
                if (word.equals(password)){
                    p = new Integer(finder.next());
                    this.dispose();
                    break;
                }
            }
            finder.close();
        }
        checker.close();
        return p;
    }
}
