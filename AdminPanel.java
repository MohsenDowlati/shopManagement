import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminPanel extends JFrame  {
    Container container;
    String[] tabsName = {"Users" , "Goods" , "Orders"};
    JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
    JPanel card1 , card2 , card3;
    JTable users , goods;
    File user = new File("priority"), good = new File("Goods"), order = new File("Orders");
    ArrayList<String[]> userArr = new ArrayList<>();
    ArrayList<String[]> goodArr = new ArrayList<>();

    public AdminPanel() {
        try {
            setUserArr();
            setGoodArr();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        design();
    }

    private void design() {
        this.setSize(1500,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        container = getContentPane();
        //first page
        card1 = new JPanel(){public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.width += 1500;
            return size;
        }};
        String[] userTitle = {"num","username","position"};
        DefaultTableModel tableModel1 = new DefaultTableModel(userTitle,0);
        int i = 1;
        for (String[] s : userArr) {
            String[] row = {String.valueOf(i) , s[0] ,s[1] };
            tableModel1.addRow(row);
            i++;
        }
        users = new JTable(tableModel1);
        //second page
        card2 = new JPanel(){public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.width += 1500;
            return size;
        }};
        String[] goodsTitle = {"good","price","num","delete"};
        DefaultTableModel tableModel2 = new DefaultTableModel(goodsTitle,0);
        for (String[] s : goodArr)
            tableModel2.addRow(s);
        goods = new JTable(tableModel2);
        //third page
        card3 = new JPanel(){public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            size.width += 1500;
            return size;
        }};



        card1.add(users);
        container.add(card1);
       // container.add(card2);
       // container.add(card3);
        this.setVisible(true);
    }

    private void setUserArr() throws FileNotFoundException {
        Scanner setter = new Scanner(user);
        while (setter.hasNextLine()) {
            String[] data = new String[2];
            Scanner line = new Scanner(setter.nextLine());
            data[0] = line.next();
            line.next();
            int pos = line.nextInt();
            if (pos==1)
                data[1] = "Admin";
            else if (pos==2)
                data[1] = "Seller";
            else
                data[1] = "Customer";
            userArr.add(data);
            line.close();
        }
        setter.close();
    }

    private void setGoodArr() throws FileNotFoundException {
        Scanner setter = new Scanner(good);
        while (setter.hasNextLine()) {
            String[] data = new String[3];
            Scanner line = new Scanner(setter.nextLine());
            data[0] = line.next();
            data[1] = String.valueOf(line.nextDouble());
            data[2] = String.valueOf(line.nextInt());
            goodArr.add(data);
            line.close();
        }
        setter.close();
    }
}