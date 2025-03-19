package inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
    	
    	JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1200, 90); 
        panel.setBackground(Color.LIGHT_GRAY); 
        panel.setLayout(null);
        
        JPanel main = new JPanel();
        main.setBounds(280,90,920,200);
        main.setBackground(Color.decode("#afafaf"));
        main.setLayout(null);
        main.setVisible(false);
        
        JPanel main1 = new JPanel();
        main1.setBounds(280,290,460,470);
        main1.setBackground(Color.decode("#a8a8a8"));
        main1.setVisible(false);
        
        JPanel main2 = new JPanel();
        main2.setBounds(725,290,460,470);
        main2.setBackground(Color.decode("#858585"));
        main2.setVisible(false);
        
        JPanel View = new JPanel();
        View.setBounds(280,90,920,670);
        View.setBackground(Color.decode("#858585"));
        View.setVisible(false);
        View.setLayout(null);
        
        JLabel vitems = new JLabel("ITEMS");
        vitems.setBounds(30, 50, 100, 100); 
        vitems.setFont(new Font("Arial", Font.BOLD, 24)); 
        
        View.add(vitems);
        
        JPanel Peripheral = new JPanel();
        Peripheral.setBounds(280,90,920,670);
        Peripheral.setBackground(Color.decode("#dbdbdb"));
        Peripheral.setVisible(false);
        
        JPanel Maintenance = new JPanel();
        Maintenance.setBounds(280,90,920,670);
        Maintenance.setBackground(Color.decode("#bfbfbf"));
        Maintenance.setVisible(false);
        
        JPanel Manage = new JPanel();
        Manage.setBounds(280,90,920,670);
        Manage.setBackground(Color.decode("#929292"));
        Manage.setVisible(false);
        
        JPanel Logout = new JPanel();
        Logout.setBounds(280,90,920,670);
        Logout.setBackground(Color.decode("#737373"));
        Logout.setVisible(false);
        
        
        ImageIcon imageIcon = new ImageIcon(Main.class.getResource("olfu.png"));
        Image image = imageIcon.getImage(); 
        Image newImage = image.getScaledInstance(150, 160, Image.SCALE_SMOOTH); 
        Image newImage1 = image.getScaledInstance(60, 70, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon = new ImageIcon(newImage);
        ImageIcon resizedIcon1 = new ImageIcon(newImage1);
       
        JLabel label = new JLabel();
        label.setIcon(resizedIcon);
        label.setBounds(60, 90, 200, 200); 

        JLabel label1 = new JLabel();
        label1.setIcon(resizedIcon1);
        label1.setText("<html>OUR LADY OF FATIMA<br><span style='font-size:24.5px;'>UNIVERSITY</span></html>");
        label1.setBounds(450, -50, 500, 200);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 18)); 
        label1.setForeground(Color.decode("#0e6b0d"));
         
        JPanel nav = new JPanel();
        nav.setLayout(null); 
        nav.setBounds(0, 90, 280, 670);
        nav.setBackground(Color.decode("#e3e3e3"));
        
        ImageIcon buttonIcon = new ImageIcon(Main.class.getResource("home.png"));
        Image buttonImage = buttonIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedButtonIcon = new ImageIcon(buttonImage);
        
        JButton button1 = new JButton("Dashboard", resizedButtonIcon);
        button1.setFont(new Font("Arial", Font.BOLD, 24));
        button1.setBounds(0, 200, 280, 40);
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setIconTextGap(20);
        button1.setBackground(Color.decode("#e3e3e3"));
        button1.setFocusPainted(false);
        button1.setBorderPainted(false);
        button1.setOpaque(true);
        
        button1.addFocusListener(new FocusListener() {
        	
            @Override
            public void focusGained(FocusEvent e) {
                button1.setBackground(Color.decode("#bababa")); 
            }

            @Override
            public void focusLost(FocusEvent e) {
                button1.setBackground(Color.decode("#e3e3e3")); 
            }
        });
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              main.setVisible(true);
              main1.setVisible(true);
              main2.setVisible(true);
            }
        });

        nav.add(button1);
        
        ImageIcon buttonIcon1 = new ImageIcon(Main.class.getResource("all.png"));
        Image buttonImage1 = buttonIcon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedButtonIcon1 = new ImageIcon(buttonImage1);
        
        JButton button2 = new JButton("View and Manage Inventory", resizedButtonIcon1 );
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setBounds(0, 240, 280, 40);
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setIconTextGap(20);
        button2.setBackground(Color.decode("#e3e3e3"));
        button2.setFocusPainted(false); 
        button2.setBorderPainted(false); 
        button2.setOpaque(true);
        
        button2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                button2.setBackground(Color.decode("#bababa")); 
            }

            @Override
            public void focusLost(FocusEvent e) {
                button2.setBackground(Color.decode("#e3e3e3"));
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	main.setVisible(false);
                main1.setVisible(false);
                main2.setVisible(false);
             View.setVisible(true);
             
            }
        });
        nav.add(button2);
        
        
        JLabel reports = new JLabel("Reports and Issues");
        reports.setFont(new Font("Arial", Font.BOLD, 18));
        reports.setBounds(20, 280, 280, 40);
        nav.add(reports);

        JButton button7 = new JButton("Pheripheral Borrowing Records");
        button7.setFont(new Font("Arial", Font.PLAIN, 16));
        button7.setBounds(0, 310, 280, 30);
        button7.setMargin(new Insets(5, 65, 5, 20)); 
        button7.setHorizontalAlignment(SwingConstants.LEFT);
        button7.setIconTextGap(20);
        button7.setBackground(Color.decode("#e3e3e3"));
        button7.setFocusPainted(false);
        button7.setBorderPainted(false);
        button7.setOpaque(true);
        button7.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                button7.setBackground(Color.decode("#bababa"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                button7.setBackground(Color.decode("#e3e3e3"));
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	main.setVisible(false);
                main1.setVisible(false);
                main2.setVisible(false);
             View.setVisible(false);
             Peripheral.setVisible(true);
            }
        });
        nav.add(button7);

        JButton button8 = new JButton("Maintenance Records");
        button8.setFont(new Font("Arial", Font.PLAIN, 16));
        button8.setBounds(0, 340, 280, 30);
        button8.setMargin(new Insets(5, 65, 5, 20)); 
        button8.setHorizontalAlignment(SwingConstants.LEFT);
        button8.setIconTextGap(20);
        button8.setBackground(Color.decode("#e3e3e3"));
        button8.setFocusPainted(false);
        button8.setBorderPainted(false);
        button8.setOpaque(true);
        button8.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                button8.setBackground(Color.decode("#bababa"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                button8.setBackground(Color.decode("#e3e3e3"));
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	main.setVisible(false);
                main1.setVisible(false);
                main2.setVisible(false);
             View.setVisible(false);
             Peripheral.setVisible(false);
             Maintenance.setVisible(true);
            }
        });
        nav.add(button8);

        JLabel account = new JLabel("Account Settings");
        account.setFont(new Font("Arial", Font.BOLD, 18));
        account.setBounds(20, 370, 280, 40);
        nav.add( account);
        
        JButton button9 = new JButton("Manage Admin Accounts");
        button9.setFont(new Font("Arial", Font.PLAIN, 16));
        button9.setBounds(0, 400, 280, 30);
        button9.setMargin(new Insets(5, 65, 5, 20)); 
        button9.setHorizontalAlignment(SwingConstants.LEFT);
        button9.setIconTextGap(20);
        button9.setBackground(Color.decode("#e3e3e3"));
        button9.setFocusPainted(false);
        button9.setBorderPainted(false);
        button9.setOpaque(true);
        button9.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                button9.setBackground(Color.decode("#bababa"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                button9.setBackground(Color.decode("#e3e3e3"));
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	main.setVisible(false);
                main1.setVisible(false);
                main2.setVisible(false);
             View.setVisible(false);
             Peripheral.setVisible(false);
             Maintenance.setVisible(false);
             Manage.setVisible(true);
            }
        });
        nav.add(button9);

        JButton button10 = new JButton("Log Out");
        button10.setFont(new Font("Arial", Font.PLAIN, 16));
        button10.setBounds(0, 430, 280, 30);
        button10.setMargin(new Insets(5, 65, 5, 20)); 
        button10.setHorizontalAlignment(SwingConstants.LEFT);
        button10.setIconTextGap(20);
        button10.setBackground(Color.decode("#e3e3e3"));
        button10.setFocusPainted(false);
        button10.setBorderPainted(false);
        button10.setOpaque(true);
        button10.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                button10.setBackground(Color.decode("#bababa"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                button10.setBackground(Color.decode("#e3e3e3"));
            }
        });
        
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	main.setVisible(false);
                main1.setVisible(false);
                main2.setVisible(false);
             View.setVisible(false);
             Peripheral.setVisible(false);
             Maintenance.setVisible(false);
             Manage.setVisible(false);
             Logout.setVisible(true);
            }
        });
        nav.add(button10);
        
        
        ImageIcon allitems = new ImageIcon(Main.class.getResource("all.png"));
        Image items = allitems.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizeditems = new ImageIcon(items);
        
        ImageIcon admins = new ImageIcon(Main.class.getResource("admins.png"));
        Image aAdmins = admins.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedadmins = new ImageIcon(aAdmins);
        
        int ad = 20;
        int aa = 2;
        
        JLabel aitems = new JLabel();
        aitems.setIcon(resizeditems);
        aitems.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ad+"<br>AVAILABLE ITEMS</html>");
        aitems.setFont(new Font("Arial", Font.PLAIN, 20));
        aitems.setVerticalTextPosition(SwingConstants.BOTTOM);
        aitems.setIconTextGap(20);
        aitems.setBounds(100, 50, 300, 100);
        
        main.add(aitems);
        
        JLabel admin = new JLabel();
        admin.setIcon(resizedadmins);
        admin.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+aa+"<br>ACTIVE ADMINS</html>");
        admin.setFont(new Font("Arial", Font.PLAIN, 20));
        admin.setVerticalTextPosition(SwingConstants.BOTTOM);
        admin.setIconTextGap(20);
        admin.setBounds(500, 50, 300, 100);
        
        main.add(admin);
        
        JFrame frame = new JFrame("Admin");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 
       
        frame.add(label);
        frame.add(label1);
        frame.add(panel);
        frame.add(main);
        frame.add(main1);
        frame.add(main2);
        frame.add(View);
        frame.add(Peripheral);
        frame.add(Maintenance);
        frame.add(Manage);
        frame.add(Logout);
        frame.add(nav);
        
        frame.setVisible(true);
    }
}
