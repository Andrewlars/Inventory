package inventory;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.time.LocalDateTime;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import inventory.conn;
import java.sql.Statement;
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
        vaction.setBounds(430, 90, 100, 50); 
        vaction.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel vedit = new JLabel("EDIT", SwingConstants.CENTER);
        vedit.setBounds(510, 100, 75, 30);
        vedit.setFont(new Font("Arial", Font.BOLD, 18));
        vedit.setForeground(Color.decode("#fefefe"));
        vedit.setBackground(Color.decode("#0057ff"));
        vedit.setOpaque(true);
        
        JButton vani = new JButton("Add New Item");
        vani.setBounds(600, 100, 180, 30);
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
                newFrame.setLocationRelativeTo(null);  
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setLayout(null);

                
                JLabel save = new JLabel();
                save.setBounds(0, 0, 600, 50);
                save.setForeground(Color.decode("#fefefe"));
                save.setBackground(Color.decode("#a9e7ae"));
                save.setOpaque(true);

               
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
        
        

        JLabel vitemName = new JLabel("Item Name", SwingConstants.LEFT);
        vitemName.setBounds(50, 150, 130, 50); 
        vitemName.setFont(new Font("Arial", Font.BOLD, 16)); 
       
        
        JLabel vitemno = new JLabel("Item ID", SwingConstants.LEFT);
        vitemno.setBounds(270, 150, 130, 50); 
        vitemno.setFont(new Font("Arial", Font.BOLD, 16)); 
       
        
        JLabel vcategory = new JLabel("Category", SwingConstants.LEFT);
        vcategory.setBounds(500, 150, 130, 50); 
        vcategory.setFont(new Font("Arial", Font.BOLD, 16)); 
        
        
        JLabel vcondition = new JLabel("Condition", SwingConstants.LEFT);
        vcondition.setBounds(720, 150, 130, 50); 
        vcondition.setFont(new Font("Arial", Font.BOLD, 16)); 
        
        
        List<JLabel> dynamicLabels = new ArrayList<>();
        vinputField.addActionListener(e -> {
            int itemsInputted = Integer.parseInt(vinputField.getText());

            // Create the table model with columns
            
            DefaultTableModel model = new DefaultTableModel(0,4);

            // Create the table and scroll pane
            JTable table = new JTable(model);
            table.setFont(new Font("Arial", Font.PLAIN, 12));
            table.setRowHeight(25);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setShowGrid(false);
            table.setBorder(BorderFactory.createEmptyBorder());
            table.getTableHeader().setBorder(null);
            table.setTableHeader(null);
            
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 200, 890, 450);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getViewport().setBackground(Color.WHITE);

           
            for (JLabel label : dynamicLabels) {
                View.remove(label);
            }
            dynamicLabels.clear();
            View.add(scrollPane);

            try (ResultSet rs = inventory.conn.query("SELECT * FROM items")) {
                int count = 0;

                while (rs.next() && count < itemsInputted) {
                    int id = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String category = rs.getString("Category");
                    String conditions = rs.getString("Conditions");

                   
                    model.addRow(new Object[]{itemName, id, category, conditions});

                    count++;
                }
                
                table.getColumnModel().getColumn(0).setPreferredWidth(100); 
                table.getColumnModel().getColumn(1).setPreferredWidth(100);  
                table.getColumnModel().getColumn(2).setPreferredWidth(100);   
                table.getColumnModel().getColumn(3).setPreferredWidth(100);  
                
                table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                        if (value != null && value.toString().equals("WORKING")) {
                            component.setForeground(Color.decode("#7fdc9a")); // Set the text color to green
                        } else {
                            component.setForeground(Color.decode("#ff8080")); // Default text color
                        }

                        return component;
                    }
                });
               

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

          
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
        borrow.revalidate();   
        borrow.repaint();
        borrow.setLayout(null);
        
        JLabel bbrecords = new JLabel("Borrowing Records");
        bbrecords.setBounds(50, 50, 200, 50); 
        bbrecords.setFont(new Font("Arial", Font.PLAIN, 16));
        
        
        JLabel bbdate = new JLabel("Date");
        bbdate.setBounds(120, 140, 200, 50); 
        bbdate.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel bItemName = new JLabel("Item Name");
        bItemName.setBounds(240, 140, 200, 50); 
        bItemName.setFont(new Font("Arial", Font.PLAIN, 12));
    
        JLabel bitemId = new JLabel("Item ID");
        bitemId.setBounds(360, 140, 200, 50); 
        bitemId.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel bsname = new JLabel("Student Name");
        bsname.setBounds(460, 140, 200, 50); 
        bsname.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel bemail = new JLabel("Student Email");
        bemail.setBounds(600, 140, 200, 50); 
        bemail.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel broom = new JLabel("Room Assinment");
        broom.setBounds(760, 140, 200, 50); 
        broom.setFont(new Font("Arial", Font.PLAIN, 12));
        
        
        
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
                LocalDate.now().withDayOfMonth(1),                           
                LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())  
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
        
        
        borrow.add(bbdate);
        borrow.add(bItemName);
        borrow.add(bitemId);
        borrow.add(bsname);
        borrow.add(bemail);
        borrow.add(broom);

        
        
        
        Runnable fetchData = () -> {
            storedDateRange[0] = dateRange[0].format(formatter) + " - " + dateRange[1].format(formatter);

            String startDate = dateRange[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String endDate = dateRange[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String query = "SELECT b.borrowID, b.date, b.time, b.itemID, i.itemName, " +
                           "b.studentName, b.studentEmail, b.roomAssignment " +
                           "FROM borrow b " +
                           "JOIN items i ON b.itemID = i.itemID " +
                           "WHERE b.date BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY b.date ASC";

            String[] columnNames = {"Date", "Item Name", "Item ID", "Student Name", "Student Email", "Room Assignment", "Time"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            try (Connection conn = inventory.conn.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    String date = rs.getString("date");
                    String time = rs.getString("time");  
                    String itemName = rs.getString("itemName");
                    int itemID = rs.getInt("itemID");
                    String studentName = rs.getString("studentName");
                    String studentEmail = rs.getString("studentEmail");
                    String roomAssignment = rs.getString("roomAssignment");

                    LocalDate sqlDate = LocalDate.parse(date);
                    DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                    String formattedDate = sqlDate.format(displayFormat);
                    
                    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

                    model.addRow(new Object[]{formattedDate, formattedTime, itemName, itemID, studentName, studentEmail, roomAssignment});
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            JTable table = new JTable(model);
            table.setFont(new Font("Arial", Font.PLAIN, 12));
            table.setRowHeight(25);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setShowGrid(false);
            table.setBorder(BorderFactory.createEmptyBorder());
            table.getTableHeader().setBorder(null);
            table.setTableHeader(null);
            
            table.getColumnModel().getColumn(0).setPreferredWidth(100); 
            table.getColumnModel().getColumn(1).setPreferredWidth(90); 
            table.getColumnModel().getColumn(2).setPreferredWidth(120); 
            table.getColumnModel().getColumn(3).setPreferredWidth(70);  
            table.getColumnModel().getColumn(4).setPreferredWidth(130);
            table.getColumnModel().getColumn(5).setPreferredWidth(200);
            table.getColumnModel().getColumn(6).setPreferredWidth(150); 


            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 190, 930, 450);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getViewport().setBackground(Color.WHITE);

            Component[] components = borrow.getComponents();
            for (Component comp : components) {
                if (comp instanceof JScrollPane) {
                    borrow.remove(comp);
                }
            }

            borrow.add(scrollPane);
            borrow.revalidate();
            borrow.repaint();
        };


       
        blprev.addActionListener(e -> {
            dateRange[0] = dateRange[0].minusMonths(1).withDayOfMonth(1);                      
            dateRange[1] = dateRange[0].withDayOfMonth(dateRange[0].lengthOfMonth());          
            String range = dateRange[0].format(formatter) + " - " + dateRange[1].format(formatter);
            bdate.setText(range);
            storedDateRange[0] = range;  
            fetchData.run();
        });

        brnext.addActionListener(e -> {
            dateRange[0] = dateRange[0].plusMonths(1).withDayOfMonth(1);                       
            dateRange[1] = dateRange[0].withDayOfMonth(dateRange[0].lengthOfMonth());          
            String range = dateRange[0].format(formatter) + " - " + dateRange[1].format(formatter);
            bdate.setText(range);
            storedDateRange[0] = range;  
            fetchData.run();
        });
        
        
        SwingUtilities.invokeLater(fetchData);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        JPanel Maintenance = new JPanel();
        Maintenance.setBounds(280,90,920,670);
        Maintenance.setBackground(Color.decode("#fefefe"));
        Maintenance.setVisible(false);
        Maintenance.revalidate();   
        Maintenance.repaint();
        Maintenance.setLayout(null);

        JLabel mmdate = new JLabel("Date");
        mmdate.setBounds(100, 140, 200, 50); 
        mmdate.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel mItemName = new JLabel("Item Name");
        mItemName.setBounds(210, 140, 200, 50); 
        mItemName.setFont(new Font("Arial", Font.PLAIN, 12));
    
        JLabel mitemId = new JLabel("Item ID");
        mitemId.setBounds(350, 140, 200, 50); 
        mitemId.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel mcon = new JLabel("Condition");
        mcon.setBounds(470, 140, 200, 50); 
        mcon.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel mreason = new JLabel("Reason");
        mreason.setBounds(650, 140, 200, 50); 
        mreason.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel mcby = new JLabel("Check by");
        mcby.setBounds(790, 140, 200, 50); 
        mcby.setFont(new Font("Arial", Font.PLAIN, 12));

        
        
        JLabel mrecords = new JLabel("Maintenance Records"); 
        mrecords.setBounds(50, 50, 200, 50); 
        mrecords.setFont(new Font("Arial", Font.PLAIN, 16));
        
        ImageIcon mIcon = new ImageIcon(Main.class.getResource("calendar.png"));
        Image mImage = mIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon mresizedButtonIcon = new ImageIcon(mImage);
        
        ImageIcon mlarrow = new ImageIcon(Main.class.getResource("arrow.png"));
        Image mImagelarrow = mlarrow.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH);
        ImageIcon mresizedButtonlarrow = new ImageIcon(mImagelarrow);
        
        ImageIcon mrarrow = new ImageIcon(Main.class.getResource("rarrow.png"));
        Image mImagerarrow = mrarrow.getImage().getScaledInstance(23, 20, Image.SCALE_SMOOTH);
        ImageIcon mresizedButtonrarrow = new ImageIcon(mImagerarrow);
        
        JPanel mdaterange = new JPanel();
        mdaterange.setBounds(50, 90, 350,35);
        mdaterange.setBackground(Color.decode("#ffffff"));
        mdaterange.setVisible(true);
        mdaterange.setLayout(null);
        mdaterange.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        
        JLabel micon = new JLabel(mresizedButtonIcon);
        micon.setBounds(0, 0, 30, 30); 
        micon.setFont(new Font("Arial", Font.PLAIN, 20));
        
        final LocalDate[] mdateRange = {
                LocalDate.now().withDayOfMonth(1),                           
                LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())  
        };
    	
    	final String[] mstoredDateRange = {""}; 
        
        DateTimeFormatter mformatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        JLabel mdate = new JLabel(mdateRange[0].format(mformatter) + " - " + dateRange[1].format(mformatter));
        mdate.setBounds(45, 2, 300, 30);
        mdate.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JButton mlprev = new JButton(mresizedButtonlarrow);
        mlprev.setBounds(290, 6, 30, 20);
        mlprev.setBackground(Color.decode("#fefefe"));
        mlprev.setFocusPainted(false);
        mlprev.setBorderPainted(false);
        mlprev.setOpaque(true);
        
        JButton mrnext = new JButton(mresizedButtonrarrow);
        mrnext.setBounds(320, 6, 30, 20);
        mrnext.setBackground(Color.decode("#fefefe"));
        mrnext.setFocusPainted(false);
        mrnext.setBorderPainted(false);
        mrnext.setOpaque(true);
        
        mdaterange.add(micon);
        mdaterange.add(mdate);
        mdaterange.add(mlprev);
        mdaterange.add(mrnext);
        
        Maintenance.add(mrecords);
        Maintenance.add(mdaterange);
        
        Maintenance.add(mmdate);
        Maintenance.add(mItemName);
        Maintenance.add(mitemId);
        Maintenance.add(mcon);
        Maintenance.add(mreason);
        Maintenance.add(mcby);
        
        Runnable mfetchData = () -> {
            mstoredDateRange[0] = mdateRange[0].format(mformatter) + " - " + mdateRange[1].format(mformatter);

            String mstartDate = mdateRange[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String mendDate = mdateRange[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

           
            String query = "SELECT m.maintenanceID, m.checked_at, m.itemID, i.itemName, i.Conditions, m.checkBy, m.reason " +
                           "FROM maintenance m " +
                           "JOIN items i ON m.itemID = i.itemID " +
                           "WHERE m.checked_at BETWEEN '" + mstartDate + "' AND '" + mendDate + "' ORDER BY m.checked_at ASC";

             
            String[] columnNames = {"Checked At", "Item Name", "Item ID", "Conditions", "Checked By", "Reason"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            try (Connection conn = inventory.conn.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    String checkedAt = rs.getString("checked_at");
                    String itemName = rs.getString("itemName");
                    int itemID = rs.getInt("itemID");
                    String conditions = rs.getString("Conditions");
                    String checkBy = rs.getString("checkBy");
                    String reason = rs.getString("reason");

                    LocalDateTime sqlDateTime = LocalDateTime.parse(checkedAt.replace(" ", "T"));
                    DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy           hh:mm a");
                    String formattedCheckedAt = sqlDateTime.format(displayFormat);

                   
                    model.addRow(new Object[]{formattedCheckedAt, itemName, itemID, conditions, reason, checkBy});
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            JTable table = new JTable(model);
            table.setFont(new Font("Arial", Font.PLAIN, 12));
            table.setRowHeight(25);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.setShowGrid(false);
            table.setBorder(BorderFactory.createEmptyBorder());
            table.getTableHeader().setBorder(null);
            table.setTableHeader(null);

           
            table.getColumnModel().getColumn(0).setPreferredWidth(150); 
            table.getColumnModel().getColumn(1).setPreferredWidth(140);  
            table.getColumnModel().getColumn(2).setPreferredWidth(100);   
            table.getColumnModel().getColumn(3).setPreferredWidth(120);  
            table.getColumnModel().getColumn(4).setPreferredWidth(150);  
            table.getColumnModel().getColumn(5).setPreferredWidth(150);  

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 190, 900, 450);  
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getViewport().setBackground(Color.WHITE);

            Component[] components = Maintenance.getComponents();
            for (Component comp : components) {
                if (comp instanceof JScrollPane) {
                    Maintenance.remove(comp);
                }
            }

            Maintenance.add(scrollPane);
            Maintenance.revalidate();
            Maintenance.repaint();
        };




        mlprev.addActionListener(e -> {
            mdateRange[0] = mdateRange[0].minusMonths(1).withDayOfMonth(1);                      
            mdateRange[1] = mdateRange[0].withDayOfMonth(mdateRange[0].lengthOfMonth());          
            String mrange = mdateRange[0].format(mformatter) + " - " + mdateRange[1].format(mformatter);
            mdate.setText(mrange);
            mstoredDateRange[0] = mrange;  
            mfetchData.run();
        });

        mrnext.addActionListener(e -> {
            mdateRange[0] = mdateRange[0].plusMonths(1).withDayOfMonth(1);                       
            mdateRange[1] = mdateRange[0].withDayOfMonth(mdateRange[0].lengthOfMonth());          
            String mrange = mdateRange[0].format(mformatter) + " - " + mdateRange[1].format(mformatter);
            mdate.setText(mrange);
            mstoredDateRange[0] = mrange;  
            mfetchData.run();
        });
        
        
        SwingUtilities.invokeLater(mfetchData);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        JPanel Manage = new JPanel();
        Manage.setBounds(280, 90, 920, 670);
        Manage.setBackground(Color.decode("#fefefe"));
        Manage.setVisible(false);
        Manage.setLayout(null);
        
        int mcount = 0;
       
        try (ResultSet rs = inventory.conn.query("SELECT COUNT(*) AS total FROM admin")) {
            if (rs.next()) {
                mcount = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel aadmin = new JLabel("Admins ("+ mcount+")");
        aadmin.setBounds(100, 80, 200, 50);
        aadmin.setFont(new Font("Arial", Font.BOLD, 16));
        
        
        JButton addAdminButton = new JButton("Add Admin");
        addAdminButton.setBounds(500, 80, 120, 30); 
        addAdminButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addAdminButton.setBackground(Color.decode("#f6e798")); 


        JLabel auname = new JLabel("Username");
        auname.setBounds(100, 140, 200, 50);
        auname.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel aaName = new JLabel("Admin Name");
        aaName.setBounds(230, 140, 200, 50);
        aaName.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel aId = new JLabel("ID");
        aId.setBounds(390, 140, 200, 50);
        aId.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel alevel = new JLabel("Level");
        alevel.setBounds(500, 140, 200, 50);
        alevel.setFont(new Font("Arial", Font.PLAIN, 12));

        Manage.add(aadmin);
        Manage.add(auname);
        Manage.add(aaName);
        Manage.add(aId);
        Manage.add(alevel);
        Manage.add(addAdminButton);
        
        

        String query = "SELECT * FROM admin";

        String[] columnNames = {"Username", "Admin Name", "ID", "Level", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        
        try (Connection conn = inventory.conn.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
        	
        
            while (rs.next()) {
                String username = rs.getString("Username");
                String adminName = rs.getString("Admin_Name");
                int ID = rs.getInt("ID");
                String level = rs.getString("Level");
                

                    model.addRow(new Object[]{username, adminName, ID, level, "Actions"});
                
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setShowGrid(false);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.getTableHeader().setBorder(null);
        table.setTableHeader(null);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(220);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);  
        table.getColumnModel().getColumn(3).setPreferredWidth(200); 
        table.getColumnModel().getColumn(4).setPreferredWidth(400); 


        
        // Set cell renderer properly
        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
                panel.setBackground(Color.decode("#fefefe"));
                String level = table.getValueAt(row, 3).toString();  

               
                if (level.equals("Student Admin")) {
                	
                    JButton editButton = new JButton("Edit");
                    editButton.setBackground(Color.decode("#004dff"));
                    editButton.setFont(new Font("Arial", Font.PLAIN, 10));
                    editButton.setForeground(Color.decode("#fefefe"));
                    
                    JButton deleteButton = new JButton("Delete");
                    deleteButton.setFont(new Font("Arial", Font.PLAIN, 10));
                    deleteButton.setBackground(Color.decode("#ff0000"));
                    deleteButton.setForeground(Color.decode("#fefefe"));

                    panel.add(editButton);
                    panel.add(deleteButton);
                }

                return panel;
            }
        });

        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
                String level = table.getValueAt(row, 3).toString();  // Get the level from the row

                // Only display buttons if level is "Student Admin"
                if (level.equals("Student Admin")) {
                    JButton editButton = new JButton("Edit");
                    JButton deleteButton = new JButton("Delete");

                    editButton.setFont(new Font("Arial", Font.PLAIN, 10));
                    deleteButton.setFont(new Font("Arial", Font.PLAIN, 10));

                    editButton.setBackground(Color.decode("#004dff"));
                    deleteButton.setBackground(Color.decode("#ff0000"));

                    String username = table.getValueAt(row, 0).toString();

                
                    editButton.addActionListener(e -> {
                        String musername = table.getValueAt(row, 0).toString();
                        String adminName = table.getValueAt(row, 1).toString();
                        String mlevel = table.getValueAt(row, 3).toString();
                        int ID = (int) table.getValueAt(row, 2); 

                        
                        JDialog editDialog = new JDialog();
                        editDialog.setTitle("Edit Admin Details");
                        editDialog.setSize(300, 250);
                        editDialog.setLayout(new GridLayout(5, 2));

                        
                        JTextField txtUsername = new JTextField(musername);
                        JTextField txtAdminName = new JTextField(adminName);
                        JComboBox<String> comboLevel = new JComboBox<>(new String[]{"Admin", "Student Admin"});
                        comboLevel.setSelectedItem(mlevel);
                        JTextField txtID = new JTextField(String.valueOf(ID));
                        txtID.setEditable(false);  

                        
                        editDialog.add(new JLabel("Username"));
                        editDialog.add(txtUsername);
                        editDialog.add(new JLabel("Admin Name"));
                        editDialog.add(txtAdminName);
                        editDialog.add(new JLabel("Level"));
                        editDialog.add(comboLevel);
                        editDialog.add(new JLabel("ID"));
                        editDialog.add(txtID);

                        JButton saveButton = new JButton("Save Changes");
                        editDialog.add(new JLabel());  
                        editDialog.add(saveButton);

                       
                        saveButton.addActionListener(ev -> {
                            String updatedUsername = txtUsername.getText();
                            String updatedAdminName = txtAdminName.getText();
                            String updatedLevel = (String) comboLevel.getSelectedItem();

                           
                            try (Connection conn = inventory.conn.connect()) {
                                String updateQuery = "UPDATE admin SET Username = ?, Admin_Name = ?, Level = ? WHERE ID = ?";
                                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                                    pstmt.setString(1, updatedUsername);
                                    pstmt.setString(2, updatedAdminName);
                                    pstmt.setString(3, updatedLevel);
                                    pstmt.setInt(4, ID);

                                    int rowsAffected = pstmt.executeUpdate();
                                    if (rowsAffected > 0) {
                                        
                                        table.setValueAt(updatedUsername, row, 0);
                                        table.setValueAt(updatedAdminName, row, 1);
                                        table.setValueAt(updatedLevel, row, 3);
                                        JOptionPane.showMessageDialog(null, "Admin details updated successfully!");
                                        editDialog.dispose(); 
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error updating admin details.");
                                    }
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Database error occurred while updating.");
                            }
                        });

                       
                        editDialog.setLocationRelativeTo(null); 
                        editDialog.setVisible(true);
                    });

                    
                    

                    deleteButton.addActionListener(e -> {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + username + "?",
                                "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try (Connection conn = inventory.conn.connect()) {
                                
                                String deleteQuery = "DELETE FROM admin WHERE Username = ?";
                                try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                                    pstmt.setString(1, username); 
                                    int rowsAffected = pstmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                       
                                        ((DefaultTableModel) table.getModel()).removeRow(row);
                                        JOptionPane.showMessageDialog(null, "Deleted: " + username);
                                        
                                       
                                        try (ResultSet rs = inventory.conn.query("SELECT COUNT(*) AS total FROM admin")) {
                                            if (rs.next()) {
                                                int count = rs.getInt("total");
                                                aadmin.setText("Admins ("+ count+")");
                                            }
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error deleting user: " + username);
                                    }
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Database error occurred while deleting.");
                            }
                        }
                    });

                   

                    panel.add(editButton);
                    panel.add(deleteButton);
                }

                return panel;
            }
        });
        
        addAdminButton.addActionListener(e -> {
            JDialog addDialog = new JDialog();
            addDialog.setTitle("Add New Admin");
            addDialog.setSize(300, 250);
            addDialog.setLayout(new GridLayout(5, 2));

            JTextField txtUsername = new JTextField();
            JTextField txtAdminName = new JTextField();
            JComboBox<String> comboLevel = new JComboBox<>(new String[]{"Admin", "Student Admin"});

            addDialog.add(new JLabel("Username"));
            addDialog.add(txtUsername);
            addDialog.add(new JLabel("Admin Name"));
            addDialog.add(txtAdminName);
            addDialog.add(new JLabel("Level"));
            addDialog.add(comboLevel);
            addDialog.add(new JLabel()); 

            JButton saveButton = new JButton("Add Admin");
            addDialog.add(saveButton);

            saveButton.addActionListener(ev -> {
                String username = txtUsername.getText();
                String adminName = txtAdminName.getText();
                String level = (String) comboLevel.getSelectedItem();

                if (username.isEmpty() || adminName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields.");
                    return;
                }

               
                try (Connection conn = inventory.conn.connect()) {
                    String insertQuery = "INSERT INTO admin (Username, Admin_Name, Level) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                        pstmt.setString(1, username);
                        pstmt.setString(2, adminName);
                        pstmt.setString(3, level);

                        int rows = pstmt.executeUpdate();
                        if (rows > 0) {
                            
                            model.addRow(new Object[]{username, adminName, "Auto", level, "Actions"});
                            JOptionPane.showMessageDialog(null, "Admin added successfully.");
                            try (ResultSet rs = inventory.conn.query("SELECT COUNT(*) AS total FROM admin")) {
                                if (rs.next()) {
                                    int count = rs.getInt("total");
                                    aadmin.setText("Admins ("+ count+")");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            addDialog.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to add admin.");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error.");
                }
            });

            addDialog.setLocationRelativeTo(null);
            addDialog.setVisible(true);
        });

        
        


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 190, 830, 450);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        Manage.add(scrollPane);
        
        
           
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
             borrow.setVisible(false);
             Maintenance.setVisible(false);
             Manage.setVisible(false);
             Logout.setVisible(false);
             
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
             Maintenance.setVisible(false);
             Manage.setVisible(false);
             Logout.setVisible(false);
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
             Manage.setVisible(false);
             Logout.setVisible(false);
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
             Logout.setVisible(false);
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
