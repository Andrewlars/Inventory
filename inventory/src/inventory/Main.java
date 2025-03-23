package inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import inventory.conn;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

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
        View.setBackground(Color.decode("#fefefe"));
        View.setVisible(false);
        View.setLayout(null);
        
        int itemCount = 0;
        try (ResultSet rs = inventory.conn.query("SELECT COUNT(*) AS total FROM items")) {
            if (rs.next()) {
                itemCount = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        JLabel vitems = new JLabel("ITEMS ("+ itemCount+")");
        vitems.setBounds(50, 50, 130, 50); 
        vitems.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel vshow = new JLabel("SHOW");
        vshow.setBounds(50, 90, 100, 50); 
        vshow.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JTextField vinputField = new JTextField();
        vinputField.setBounds(130, 100, 80, 30);
        
        JLabel vaction = new JLabel("Action: ");
        vaction.setBounds(300, 90, 100, 50); 
        vaction.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel vedit = new JLabel("EDIT", SwingConstants.CENTER);
        vedit.setBounds(380, 100, 75, 30);
        vedit.setFont(new Font("Arial", Font.BOLD, 18));
        vedit.setForeground(Color.decode("#fefefe"));
        vedit.setBackground(Color.decode("#0057ff"));
        vedit.setOpaque(true);
        
        JButton vani = new JButton("Add New Item");
        vani.setBounds(470, 100, 180, 30);
        vani.setFont(new Font("Arial", Font.BOLD, 18));
        vani.setForeground(Color.decode("#000000"));
        vani.setBackground(Color.decode("#f6e798"));
        vani.setFocusPainted(false);
        vani.setBorderPainted(false);
        vani.setOpaque(true);
        
        vani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame newFrame = new JFrame("Add New Item");
                newFrame.setSize(600, 500);
                newFrame.setLocationRelativeTo(null);  // Center the frame
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setLayout(null);

                // Header label
                JLabel save = new JLabel();
                save.setBounds(0, 0, 600, 50);
                save.setForeground(Color.decode("#fefefe"));
                save.setBackground(Color.decode("#a9e7ae"));
                save.setOpaque(true);

                // Save button with icon
                ImageIcon buttonIcon = new ImageIcon(Main.class.getResource("diskette.png"));
                Image buttonImage = buttonIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon resizedButtonIcon = new ImageIcon(buttonImage);

                JButton ssave = new JButton("SAVE", resizedButtonIcon);
                ssave.setBounds(450, 10, 120, 30);
                ssave.setFont(new Font("Arial", Font.BOLD, 18));
                ssave.setForeground(Color.decode("#000000"));
                ssave.setBackground(Color.decode("#a9e7ae"));
                ssave.setFocusPainted(false);
                ssave.setBorderPainted(false);
                ssave.setOpaque(true);

                // Labels
                JLabel label1 = new JLabel("Item Name");
                label1.setFont(new Font("Arial", Font.BOLD, 18));
                label1.setBounds(30, 60, 180, 30);

                JLabel label2 = new JLabel("Item ID");
                label2.setFont(new Font("Arial", Font.BOLD, 18));
                label2.setBounds(30, 100, 180, 30);

                JLabel label3 = new JLabel("Category");
                label3.setFont(new Font("Arial", Font.BOLD, 18));
                label3.setBounds(30, 140, 180, 30);

                JLabel label4 = new JLabel("Item Image");
                label4.setFont(new Font("Arial", Font.BOLD, 18));
                label4.setBounds(30, 180, 180, 30);

                
                JTextField itemNameField = new JTextField();
                itemNameField.setBounds(200, 60, 300, 30);

                JTextField itemIdField = new JTextField();
                itemIdField.setBounds(200, 100, 300, 30);

                JTextField categoryField = new JTextField();
                categoryField.setBounds(200, 140, 300, 30);

                JTextField imagePathField = new JTextField();
                imagePathField.setBounds(200, 180, 200, 30);
                imagePathField.setEditable(false);

                JButton browseButton = new JButton("Browse");
                browseButton.setBounds(410, 180, 90, 30);

                browseButton.addActionListener(e1 -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        imagePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                });
                
                ssave.addActionListener(ex -> {
                	String itemName = itemNameField.getText();
                	int itemId = Integer.parseInt(itemIdField.getText().trim());
                	String category = categoryField.getText();
                	String imagePath = imagePathField.getText();
                	
                	insertItem(itemId, itemName, category, imagePath);
                });
                
                newFrame.add(save);
                newFrame.add(ssave);
                
                newFrame.add(label1);
                newFrame.add(itemNameField);
                
                newFrame.add(label2);
                newFrame.add(itemIdField);
                
                newFrame.add(label3);
                newFrame.add(categoryField);
                
                newFrame.add(label4);
                newFrame.add(imagePathField);
                newFrame.add(browseButton);

                newFrame.setVisible(true);
            }
            private void insertItem(int itemId, String itemName, String category, String imagePath) {
                String sql = "INSERT INTO items (itemID, itemName, Category, Conditions, ItemImage) VALUES (?, ?, ?, 'WORKING', ?)";

                try (Connection conn = inventory.conn.connect();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setInt(1, itemId);
                    stmt.setString(2, itemName);
                    stmt.setString(3, category);
                    stmt.setString(4, imagePath);

                    int rowsInserted = stmt.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Item inserted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to insert item.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error inserting item: " + ex.getMessage());
                }
            }
            
        });
        
        
        
        
        JLabel vitemName = new JLabel("Item Name", SwingConstants.CENTER);
        vitemName.setBounds(50, 150, 130, 50); 
        vitemName.setFont(new Font("Arial", Font.BOLD, 20)); 
        vitemName.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
        
        JLabel vitemno = new JLabel("Item ID", SwingConstants.CENTER);
        vitemno.setBounds(200, 150, 130, 50); 
        vitemno.setFont(new Font("Arial", Font.BOLD, 20)); 
        vitemno.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
        
        JLabel vcategory = new JLabel("Category", SwingConstants.CENTER);
        vcategory.setBounds(350, 150, 130, 50); 
        vcategory.setFont(new Font("Arial", Font.BOLD, 20)); 
        vcategory.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
        
        JLabel vcondition = new JLabel("Condition", SwingConstants.CENTER);
        vcondition.setBounds(500, 150, 130, 50); 
        vcondition.setFont(new Font("Arial", Font.BOLD, 20)); 
        vcondition.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
        
        List<JLabel> dynamicLabels = new ArrayList<>();
        vinputField.addActionListener(e -> {
            int itemsInputted = Integer.parseInt(vinputField.getText());
            
            for (JLabel label : dynamicLabels) {
                View.remove(label);
            }
            dynamicLabels.clear();
            try (ResultSet rs = inventory.conn.query("SELECT * FROM items")) {
                int count = 0;
                int y = 200;
                int x = 50;
                while (rs.next() && count < itemsInputted) {
                	
                    int id = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String category = rs.getString("Category");
                    String conditions = rs.getString("Conditions");

                    JLabel label = new JLabel(itemName, SwingConstants.CENTER);
                    label.setBounds(x, y, 130, 50);
                    label.setFont(new Font("Arial", Font.PLAIN, 20));
                    label.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
                    
                    x += 150;
                    JLabel label1 = new JLabel(String.valueOf(id), SwingConstants.CENTER);
                    label1.setBounds(x, y, 130, 50);
                    label1.setFont(new Font("Arial", Font.PLAIN, 20));
                    label1.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
                    
                    x += 150;
                    JLabel label2 = new JLabel(category, SwingConstants.CENTER);
                    label2.setBounds(x, y, 130, 50);
                    label2.setFont(new Font("Arial", Font.PLAIN, 20));
                    label2.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
                    
                    x += 150;
                    JLabel label3 = new JLabel(conditions, SwingConstants.CENTER);
                    label3.setBounds(x, y, 130, 50);
                    label3.setFont(new Font("Arial", Font.PLAIN, 20));
                    label3.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
                    View.add(label);
                    View.add(label1);
                    View.add(label2);
                    View.add(label3);

                    dynamicLabels.add(label);
                    dynamicLabels.add(label1);
                    dynamicLabels.add(label2);
                    dynamicLabels.add(label3);
                    
                    y += 50;
                    x = 50;
                    count++;
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Refresh the GUI
            View.revalidate();
            View.repaint();
        });
        
        View.add(vitems);
        View.add(vitemName);
        View.add(vitemno);
        View.add(vcategory);
        View.add(vcondition);
        View.add(vshow);
        View.add(vinputField);
        View.add(vaction);
        View.add(vedit);
        View.add(vani);
        
        JPanel borrow = new JPanel();
        borrow.setBounds(280,90,920,670);
        borrow.setBackground(Color.decode("#ffffff"));
        borrow.setVisible(false);
        borrow.setLayout(null);
        
        JLabel bbrecords = new JLabel("Borrowing Records");
        bbrecords.setBounds(50, 50, 200, 50); 
        bbrecords.setFont(new Font("Arial", Font.PLAIN, 16));
        
        ImageIcon bIcon = new ImageIcon(Main.class.getResource("calendar.png"));
        Image bImage = bIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon bresizedButtonIcon = new ImageIcon(bImage);
        
        ImageIcon blarrow = new ImageIcon(Main.class.getResource("arrow.png"));
        Image bImagelarrow = blarrow.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH);
        ImageIcon bresizedButtonlarrow = new ImageIcon(bImagelarrow);
        
        ImageIcon brarrow = new ImageIcon(Main.class.getResource("rarrow.png"));
        Image bImagerarrow = brarrow.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH);
        ImageIcon bresizedButtonrarrow = new ImageIcon(bImagerarrow);
        
        JPanel bdaterange = new JPanel();
        bdaterange.setBounds(50, 90, 350,35);
        bdaterange.setBackground(Color.decode("#ffffff"));
        bdaterange.setVisible(true);
        bdaterange.setLayout(null);
        bdaterange.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        
        JLabel bicon = new JLabel(bresizedButtonIcon);
        bicon.setBounds(0, 0, 30, 30); 
        bicon.setFont(new Font("Arial", Font.PLAIN, 20));
        
        final LocalDate[] dateRange = {
                LocalDate.now().withDayOfMonth(1),                            // First day of current month
                LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())  // Last day of current month
        };
    	
    	final String[] storedDateRange = {""}; 
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        JLabel bdate = new JLabel(dateRange[0].format(formatter) + " - " + dateRange[1].format(formatter));
        bdate.setBounds(45, 2, 300, 30);
        bdate.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JButton blprev = new JButton(bresizedButtonlarrow);
        blprev.setBounds(290, 6, 30, 20);
        blprev.setBackground(Color.decode("#fefefe"));
        blprev.setFocusPainted(false);
        blprev.setBorderPainted(false);
        blprev.setOpaque(true);
        
        JButton brnext = new JButton(bresizedButtonrarrow);
        brnext.setBounds(320, 6, 30, 20);
        brnext.setBackground(Color.decode("#fefefe"));
        brnext.setFocusPainted(false);
        brnext.setBorderPainted(false);
        brnext.setOpaque(true);
        
        bdaterange.add(bicon);
        bdaterange.add(bdate);
        bdaterange.add(blprev);
        bdaterange.add(brnext);
        
        borrow.add(bbrecords);
        borrow.add(bdaterange);
        
        blprev.addActionListener(e -> {
            dateRange[0] = dateRange[0].minusMonths(1).withDayOfMonth(1);                      
            dateRange[1] = dateRange[0].withDayOfMonth(dateRange[0].lengthOfMonth());          
            String range = dateRange[0].format(formatter) + " - " + dateRange[1].format(formatter);
            bdate.setText(range);
            storedDateRange[0] = range;  
        });

        brnext.addActionListener(e -> {
            dateRange[0] = dateRange[0].plusMonths(1).withDayOfMonth(1);                       
            dateRange[1] = dateRange[0].withDayOfMonth(dateRange[0].lengthOfMonth());          
            String range = dateRange[0].format(formatter) + " - " + dateRange[1].format(formatter);
            bdate.setText(range);
            storedDateRange[0] = range;  
        });
        
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

        JButton button7 = new JButton("Borrowing Records");
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
             borrow.setVisible(true);
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
             borrow.setVisible(false);
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
             borrow.setVisible(false);
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
             borrow.setVisible(false);
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
        frame.add(borrow);
        frame.add(Maintenance);
        frame.add(Manage);
        frame.add(Logout);
        frame.add(nav);
        
        frame.setVisible(true);
        
    }
    
    
}
