package project_CaseStudy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date; // Required for Date logic

public class LStar_UserExp extends JFrame {
	
	// --- Constants (Same as original) ---
    static final int ADDON_BED_PER_NIGHT = 650;
    static final int ADDON_BLANKET = 250;
    static final int ADDON_PILLOW = 100;
    static final int ADDON_TOILETRIES = 200;

    static final int POOL_PER_PERSON_PER_DAY = 300;
    static final int GYM_PER_PERSON_PER_DAY = 500;
    static final int SPA_FOOT = 825;
    static final int SPA_AROMA = 1045;
    static final int SPA_THAI = 1540;
    static final double PWD_SENIOR_DISCOUNT = 0.20;

    // Data Arrays
    String[] locationDestination = {"Baguio", "Boracay", "El Nido", "Siargao", "Hong Kong", "Japan", "Singapore", "South Korea"};
    static String[] roomTypes = {"Standard", "Deluxe", "Quadruple", "Family", "Suite"};
    static int[] capacities = {1, 2, 4, 6, 4};
    int[] availableLocal = {5, 4, 5, 3, 2};
    int[] availableIntl = {5, 4, 5, 3, 2};

    // Rates
    static int[] localLean = {2000, 3000, 4000, 5000, 6000};
    static int[] localHigh = {4000, 5000, 7000, 9000, 11000};
    static int[] localPeak = {6000, 8000, 10000, 12000, 14000};
    static int[] localSuper = {9000, 12000, 15000, 18000, 21000};

    static int[] intlLean = {2500, 5000, 7500, 10000, 12500};
    static int[] intlHigh = {4500, 7000, 9500, 12000, 14500};
    static int[] intlPeak = {6500, 9000, 11500, 14000, 16500};
    static int[] intlSuper = {10000, 13000, 16000, 19000, 22000};

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel LStar_background;

    // --- CLASS LEVEL VARIABLES (Accessible by the whole class) ---
    private static JComboBox<String> DestinationList;
    private static JComboBox<String> RoomtypeList;
    private static JSpinner CheckinDate;
    private static JSpinner CheckoutDate;
    private static JTextField roomNum;
    private static JTextField Fname;
    private static JTextField Email;
    private static JTextField contactNum;
    private static JTextField ageField;
    private static JTextField adultNum;
    private static JTextField childNum;
    private static JCheckBox Service1; 
    private static JCheckBox Service2; 
    private static JCheckBox Service3; 
    private static JCheckBox Addon1;
    private static JCheckBox Addon2;   
    private static JCheckBox Addon3;   
    private static JSpinner blanketNum; // Assuming this is your spinner
    private static JSpinner toiletNum; // Assuming this is your spinner
    private static JSpinner pillowNum; // Assuming this is your spinner
    private static JSpinner gymSpinner1;
    private static JSpinner gymSpinner2;
    private static JSpinner gymSpinner3;
    private static JSpinner poolSpinner1;
    private static JSpinner poolSpinner2;
    private static JSpinner poolSpinner3;
    private static JSpinner spaSpinner1;
    private static JSpinner spaSpinner2;
    private static JComboBox spaSpinner3;
    
    public static void infoBox() { //Unang JFrame box parang webpage pero naka list lahat ng prices before mag continue sa booking
    	JFrame introFrame = new JFrame("Lanlya Hotel Reservation - Intro");
        introFrame.setSize(600, 400);
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.getContentPane().setLayout(null);

        JLabel header = new JLabel("Welcome to Lanlya Hotel!", SwingConstants.CENTER);
        header.setFont(new Font("Tahoma", Font.BOLD, 20));
        header.setBounds(50, 30, 500, 40);
        introFrame.getContentPane().add(header);

        JButton nextBtn = new JButton("Press to Continue");
        nextBtn.setBounds(200, 200, 200, 40);
        introFrame.getContentPane().add(nextBtn);

        nextBtn.addActionListener(e -> {
            introFrame.dispose();
            LStar_UserExp userExpFrame = new LStar_UserExp(); // create new frame
            userExpFrame.setVisible(true); // show it
        });

        introFrame.setVisible(true);
    }
    
    // ==========================================
    //  UPDATED BACKEND (MEETS ALL 14 REQUIREMENTS)
    // ==========================================
    public static void backEnd() {
        // --- 1. DATA RETRIEVAL ---
        String bookerName = Fname.getText();
        String bookerEmail = Email.getText(); // Req 7
        String bookerContactNum = contactNum.getText();
        String destination = (String) DestinationList.getSelectedItem();
        int roomChoiceIndex = RoomtypeList.getSelectedIndex();
        
        // Parse Numbers safely
        int bookerAge = 0, numRooms = 0, adults = 0;
        try {
            bookerAge = Integer.parseInt(ageField.getText().trim());
            numRooms = Integer.parseInt(roomNum.getText().trim());
            adults = Integer.parseInt(adultNum.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(bookerAge < 18) {
        	JOptionPane.showMessageDialog(null, "Invalid Age Not elible to place booking order", null, JOptionPane.ERROR_MESSAGE);
        	return ;
        }

        // Amenities Data
        int gymP = (int) gymSpinner1.getValue();
        int gymPWD = (int) gymSpinner2.getValue();
        int gymD = (int) gymSpinner3.getValue();
        
        int poolP = (int) poolSpinner1.getValue();
        int poolPWD = (int) poolSpinner2.getValue();
        int poolD = (int) poolSpinner3.getValue();
        
        int spaP = (int) spaSpinner1.getValue();
        int spaPWD = (int) spaSpinner2.getValue();
        int spaIdx = spaSpinner3.getSelectedIndex(); // Spa is 1 day per session usually

        // Date Logic
        Date checkInDateObj = (Date) CheckinDate.getValue();
        Date checkOutDateObj = (Date) CheckoutDate.getValue();
        LocalDate checkIn = checkInDateObj.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkOut = checkOutDateObj.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

        // Calculate Total Guests
        String childAgeStr = childNum.getText().trim();
        int childrenCount = 0;
        if (!childAgeStr.isEmpty()) {
            try {
                for (String p : childAgeStr.split(",")) {
                    if (Integer.parseInt(p.trim()) >= 0) childrenCount++; // Count all children
                }
            } catch (Exception e) {}
        }
        int totalGuests = adults + childrenCount; // Simplified for total count logic

        // --- 2. VALIDATION (REQUIREMENTS 3, 4, 6, 7) ---

        // Req 7: Check Email, Name, Contact
        if (bookerName.isEmpty() || bookerContactNum.isEmpty() || bookerEmail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name, Email, and Contact Number are required.", "Req 7 Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Req 3 & 4 & 6: Validate Amenities (Guests, Duration, Discounts)
        // Gym Validation
        if (gymP > totalGuests) { JOptionPane.showMessageDialog(null, "Gym: Cannot avail for more people than guests.", "Req 3 Error", JOptionPane.ERROR_MESSAGE); return; }
        if (gymD > nights) { JOptionPane.showMessageDialog(null, "Gym: Cannot avail for more days than stay duration.", "Req 4 Error", JOptionPane.ERROR_MESSAGE); return; }
        if (gymPWD > gymP) { JOptionPane.showMessageDialog(null, "Gym: PWD discounts cannot exceed number of gym users.", "Req 6 Error", JOptionPane.ERROR_MESSAGE); return; }

        // Pool Validation
        if (poolP > totalGuests) { JOptionPane.showMessageDialog(null, "Pool: Cannot avail for more people than guests.", "Req 3 Error", JOptionPane.ERROR_MESSAGE); return; }
        if (poolD > nights) { JOptionPane.showMessageDialog(null, "Pool: Cannot avail for more days than stay duration.", "Req 4 Error", JOptionPane.ERROR_MESSAGE); return; }
        if (poolPWD > poolP) { JOptionPane.showMessageDialog(null, "Pool: PWD discounts cannot exceed number of pool users.", "Req 6 Error", JOptionPane.ERROR_MESSAGE); return; }

        // Spa Validation
        if (spaP > totalGuests) { JOptionPane.showMessageDialog(null, "Spa: Cannot avail for more people than guests.", "Req 3 Error", JOptionPane.ERROR_MESSAGE); return; }
        if (spaPWD > spaP) { JOptionPane.showMessageDialog(null, "Spa: PWD discounts cannot exceed number of spa users.", "Req 6 Error", JOptionPane.ERROR_MESSAGE); return; }

        // --- 3. CALCULATIONS & AUTO-BED (REQUIREMENT 1) ---
        
        int capacityPerRoom = capacities[roomChoiceIndex];
        int totalCapacity = capacityPerRoom * numRooms;
        
        // Req 1: Must automatically require availing an extra bed if capacity exceeded
        int extraGuests = Math.max(0, totalGuests - totalCapacity);
        double extraBedCost = 0.0;
        
        if (extraGuests > 0) {
            extraBedCost = extraGuests * ADDON_BED_PER_NIGHT * nights;
            // No confirmation dialog - Automatic application per Req 1
        }

        // Room Cost
        boolean isIntl = isInternationalDestination(destination);
        String season = detectSeasonSimple(checkIn);
        int roomRate = getRateFor(roomChoiceIndex, season, isIntl);
        double totalRoomCost = (double) roomRate * numRooms * nights;

        // Add-ons
        double addonCost = extraBedCost;
        if (Addon1.isSelected()) addonCost += (int) blanketNum.getValue() * ADDON_BLANKET;
        if (Addon2.isSelected()) addonCost += (int) toiletNum.getValue() * ADDON_TOILETRIES;
        if (Addon3.isSelected()) addonCost += (int) pillowNum.getValue() * ADDON_PILLOW;

        // Amenities (Req 5: Discounts only applied here - handled in calculateAmenityCost)
        double amenityCost = 0.0;
        if (Service1.isSelected()) amenityCost += calculateAmenityCost(gymP, gymPWD, gymD, GYM_PER_PERSON_PER_DAY);
        if (Service2.isSelected()) amenityCost += calculateAmenityCost(poolP, poolPWD, poolD, POOL_PER_PERSON_PER_DAY);
        if (Service3.isSelected()) {
            int sRate = (spaIdx==0)?SPA_FOOT:(spaIdx==1)?SPA_AROMA:SPA_THAI;
            amenityCost += calculateAmenityCost(spaP, spaPWD, 1, sRate);
        }

        double grandTotal = totalRoomCost + addonCost + amenityCost;

        // --- 4. PAYMENT (REQ 8, 9, 10, 12) ---
        
        String[] options = {"Cash", "Credit Card"};
        int choice = JOptionPane.showOptionDialog(null, 
            String.format("Total: P %.2f\nSelect Payment:", grandTotal), 
            "Payment", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (choice == -1) return;

        String paymentInfo = "";

        if (choice == 0) { // Cash (Req 8)
            boolean valid = false;
            while(!valid) {
                String input = JOptionPane.showInputDialog("Enter Cash Amount (Exact or More):");
                if (input == null) return;
                try {
                    double cash = Double.parseDouble(input);
                    if (cash >= grandTotal) {
                        paymentInfo = String.format("Paid Cash: P %.2f\nChange: P %.2f", cash, (cash - grandTotal));
                        valid = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient cash.");
                    }
                } catch(Exception e) { JOptionPane.showMessageDialog(null, "Invalid amount."); }
            }
        } else { // Credit Card (Req 9, 10, 12)
            // Create a custom panel to HIDE the number and ask for CVV
            JPanel cardPanel = new JPanel(new GridLayout(2, 2, 5, 5));
            JPasswordField cardField = new JPasswordField(16); // Req 12: Hidden
            JPasswordField cvvField = new JPasswordField(3);   // Req 9: CVV
            
            cardPanel.add(new JLabel("Card Number (16 digits):"));
            cardPanel.add(cardField);
            cardPanel.add(new JLabel("CVV (3 digits):"));
            cardPanel.add(cvvField);

            int result = JOptionPane.showConfirmDialog(null, cardPanel, "Enter Card Details", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) return;

            String cardNumStr = new String(cardField.getPassword());
            String cvvStr = new String(cvvField.getPassword());

            // Simple Length Check
            if (cardNumStr.length() != 16 || cvvStr.length() != 3) {
                JOptionPane.showMessageDialog(null, "Invalid Card Details! Must be 16 digits + 3 digit CVV.");
                return;
            }
            
            // Req 10: Display exact amount deducted
            paymentInfo = String.format("Credit Card Deducted: P %.2f\n(Card Ending in %s)", grandTotal, cardNumStr.substring(12));
        }

     // =========================================================================
        // PHASE 5: FINAL OUTPUT (DETAILED RECEIPT)
        // =========================================================================
        
        StringBuilder receipt = new StringBuilder();
        receipt.append("--------------------------------------------\n");
        receipt.append("         LANLYA HOTEL OFFICIAL RECEIPT      \n");
        receipt.append("--------------------------------------------\n\n");

        // 1. GUEST & STAY DETAILS
        receipt.append("GUEST DETAILS:\n");
        receipt.append(" Name: ").append(bookerName).append("\n");
        receipt.append(" Email: ").append(bookerEmail).append("\n");
        receipt.append(" Contact: ").append(bookerContactNum).append("\n");
        receipt.append(" Destination: ").append(destination).append(" (").append(isIntl ? "International" : "Local").append(")\n");
        receipt.append(" Stay: ").append(checkIn).append(" to ").append(checkOut).append(" (").append(nights).append(" nights)\n");
        receipt.append("\n");

        // 2. ACCOMMODATION CHARGES
        receipt.append("ACCOMMODATION:\n");
        receipt.append(String.format(" • %s Room (x%d) @ %s Rate\n", roomTypes[roomChoiceIndex], numRooms, season));
        receipt.append(String.format("    Price: P %.2f\n", totalRoomCost));
        
        if (extraGuests > 0) {
            receipt.append(String.format(" • Extra Bed Charge (%d pax): P %.2f\n", extraGuests, extraBedCost));
        }
        receipt.append("\n");

        // 3. ADD-ONS BREAKDOWN (Only show if selected)
        if (Addon1.isSelected() || Addon2.isSelected() || Addon3.isSelected()) {
            receipt.append("ADD-ONS:\n");
            if (Addon1.isSelected()) {
                int qty = (int) blanketNum.getValue();
                receipt.append(String.format(" • Blankets (x%d): P %d\n", qty, qty * ADDON_BLANKET));
            }
            if (Addon2.isSelected()) {
                int qty = (int) toiletNum.getValue();
                receipt.append(String.format(" • Toiletries (x%d): P %d\n", qty, qty * ADDON_TOILETRIES));
            }
            if (Addon3.isSelected()) {
                int qty = (int) pillowNum.getValue();
                receipt.append(String.format(" • Pillows (x%d): P %d\n", qty, qty * ADDON_PILLOW));
            }
            receipt.append("\n");
        }

        // 4. AMENITIES BREAKDOWN (Only show if selected)
        if (Service1.isSelected() || Service2.isSelected() || Service3.isSelected()) {
            receipt.append("AMENITIES (PWD Discounts Applied):\n");
            
            if (Service1.isSelected()) {
                double cost = calculateAmenityCost(gymP, gymPWD, gymD, GYM_PER_PERSON_PER_DAY);
                receipt.append(String.format(" • Gym (%d pax, %d days): P %.2f\n", gymP, gymD, cost));
            }
            if (Service2.isSelected()) {
                double cost = calculateAmenityCost(poolP, poolPWD, poolD, POOL_PER_PERSON_PER_DAY);
                receipt.append(String.format(" • Pool (%d pax, %d days): P %.2f\n", poolP, poolD, cost));
            }
            if (Service3.isSelected()) {
                int sRate = (spaIdx == 0) ? SPA_FOOT : (spaIdx == 1) ? SPA_AROMA : SPA_THAI;
                double cost = calculateAmenityCost(spaP, spaPWD, 1, sRate);
                receipt.append(String.format(" • Spa (%d pax): P %.2f\n", spaP, cost));
            }
            receipt.append("\n");
        }

        // 5. TOTALS AND PAYMENT
        receipt.append("--------------------------------------------\n");
        receipt.append(String.format(" GRAND TOTAL: P %.2f\n", grandTotal));
        receipt.append("--------------------------------------------\n");
        receipt.append("PAYMENT DETAILS:\n");
        receipt.append(paymentInfo).append("\n");
        receipt.append("--------------------------------------------\n");
        receipt.append("      Thank you for choosing Lanlya!        ");

        // Display the detailed receipt
        JTextArea textArea = new JTextArea(receipt.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Monospace font aligns text nicely
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        
        JOptionPane.showMessageDialog(null, scrollPane, "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);

        // --- 6. LOOPING (REQ 13, 14) ---
        int again = JOptionPane.showConfirmDialog(null, "Would you like to book another stay?", "Book Again?", JOptionPane.YES_NO_OPTION);
        if (again == JOptionPane.YES_OPTION) {
            resetForm(); // Req 14: Loop/Reset
        } else {
            System.exit(0);
        }
    }

    // Helper Method to Reset the Form (Req 14)
    public static void resetForm() {
        Fname.setText("");
        Email.setText("");
        contactNum.setText("");
        ageField.setText("");
        adultNum.setText("1");
        childNum.setText("1");
        roomNum.setText("1");
        DestinationList.setSelectedIndex(0);
        RoomtypeList.setSelectedIndex(0);
        
        // Reset Checkboxes
        Addon1.setSelected(false); Addon2.setSelected(false); Addon3.setSelected(false);
        Service1.setSelected(false); Service2.setSelected(false); Service3.setSelected(false);
        
        // Reset Spinners to 0
        blanketNum.setValue(0); pillowNum.setValue(0); toiletNum.setValue(0);
        gymSpinner1.setValue(0); gymSpinner2.setValue(0); gymSpinner3.setValue(0);
        poolSpinner1.setValue(0); poolSpinner2.setValue(0); poolSpinner3.setValue(0);
        spaSpinner1.setValue(0); spaSpinner2.setValue(0);
    }
    
    //Boolean to check 
    static boolean isInternationalDestination(String dest) {
        String[] intl = {"Hong Kong", "Japan", "Singapore", "South Korea"};
        for (String s : intl) if (s.equalsIgnoreCase(dest)) return true;
        return false;
    }
    
    //Holidays
    static String detectSeasonSimple(LocalDate d) {
        int m = d.getMonthValue();
        int day = d.getDayOfMonth();

        // Holy Week (Apr 10 - Apr 16)
        LocalDate holyStart = LocalDate.of(d.getYear(), 4, 10);
        LocalDate holyEnd = LocalDate.of(d.getYear(), 4, 16);
        if (!d.isBefore(holyStart) && !d.isAfter(holyEnd)) return "Holy Week";

        // Chinese New Year (Feb 9 - Feb 15)
        LocalDate cnyStart = LocalDate.of(d.getYear(), 2, 9);
        LocalDate cnyEnd = LocalDate.of(d.getYear(), 2, 15);
        if (!d.isBefore(cnyStart) && !d.isAfter(cnyEnd)) return "Chinese New Year";

        if ((m == 12 && day >= 20 && day <= 31) || (m == 1 && day >= 1 && day <= 5)) return "Super Peak";
        if ((m == 11) || (m == 12 && day <= 19) || (m == 1 && day >= 6) || (m == 2)) return "High";
        if (m >= 3 && m <= 5) return "Peak";
        if (m >= 6 && m <= 10) return "Lean";
        return "Lean";
    }
    
    private static double calculateAmenityCost(int persons, int pwds, int units, int rate) {
        double total = (double) persons * rate * units;
        double discount = (double) pwds * rate * units * PWD_SENIOR_DISCOUNT;
        return total - discount;
    }
    
    static int getRateFor(int idx, String season, boolean isInternational) {
        // NOTE: In the GUI, I removed passing arrays as args to simplify. 
        // I accessed the class-level arrays directly.
        if (isInternational) {
            if (season.equals("Lean")) return intlLean[idx];
            if (season.equals("High")) return intlHigh[idx];
            if (season.equals("Peak")) return intlPeak[idx];
            if (season.equals("Super Peak") || season.equals("Holy Week") || season.equals("Chinese New Year")) return intlSuper[idx];
            return intlLean[idx];
        } else {
            if (season.equals("Lean")) return localLean[idx];
            if (season.equals("High")) return localHigh[idx];
            if (season.equals("Peak")) return localPeak[idx];
            if (season.equals("Super Peak") || season.equals("Holy Week") || season.equals("Chinese New Year")) return localSuper[idx];
            return localLean[idx];
        }
    }

    
    
    public LStar_UserExp() {
    	setResizable(false);
    	setBackground(new Color(255, 255, 255));
        setTitle("Lanlya Hotel Reservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 720);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(100, 149, 237));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel LStar_header_1 = new JLabel("Welcome, to Lanlya Hotel Reservation System!");
        LStar_header_1.setHorizontalAlignment(SwingConstants.CENTER);
        LStar_header_1.setForeground(new Color(255, 215, 0));
        LStar_header_1.setFont(new Font("Tahoma", Font.BOLD, 35));
        LStar_header_1.setBounds(0, 0, 1020, 112);
        contentPane.add(LStar_header_1);

        // --- Yellow Panel ---
        JPanel PanelList = new JPanel();
        PanelList.setBackground(new Color(255, 215, 0));
        PanelList.setBounds(10, 111, 990, 500);
        PanelList.setLayout(null);
        contentPane.add(PanelList);

        JLabel Header = new JLabel("----HOTEL RESERVATION BOOKING DETAILS ------------------------------------------------------------------------------------------------------------------------------------");
        Header.setForeground(SystemColor.textHighlight);
        Header.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header.setBounds(10, 10, 970, 19);
        PanelList.add(Header);

        // --- Destination ---
        JLabel DestinationHeader = new JLabel("Destination:");
        DestinationHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        DestinationHeader.setBounds(20, 39, 105, 13);
        PanelList.add(DestinationHeader);
        
        DestinationList = new JComboBox<>(locationDestination);
        DestinationList.setFont(new Font("Tahoma", Font.ITALIC, 10));
        DestinationList.setBounds(85, 36, 147, 19);
        PanelList.add(DestinationList);

        // --- Check In Date ---
        JLabel CheckInHeader = new JLabel("Check-in-Date: (YYYY-MM-DD)");
        CheckInHeader.setHorizontalAlignment(SwingConstants.LEFT);
        CheckInHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        CheckInHeader.setBounds(290, 39, 177, 13);
        PanelList.add(CheckInHeader);

        CheckinDate = new JSpinner(new SpinnerDateModel());
        CheckinDate.setFont(new Font("Tahoma", Font.ITALIC, 10));
        JSpinner.DateEditor de_CheckinDate = new JSpinner.DateEditor(CheckinDate, "yyyy-MM-dd");
        CheckinDate.setEditor(de_CheckinDate);
        CheckinDate.setBounds(477, 36, 147, 20);
        CheckinDate.setValue(new Date()); // Set to today
        PanelList.add(CheckinDate);

        // --- Check Out Date ---
        JLabel CheckOutHeader = new JLabel("Check-out-Date: (YYYY-MM-DD)");
        CheckOutHeader.setHorizontalAlignment(SwingConstants.LEFT);
        CheckOutHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        CheckOutHeader.setBounds(290, 65, 177, 13);
        PanelList.add(CheckOutHeader);

        CheckoutDate = new JSpinner(new SpinnerDateModel());
        CheckoutDate.setFont(new Font("Tahoma", Font.ITALIC, 10));
        JSpinner.DateEditor de_CheckoutDate = new JSpinner.DateEditor(CheckoutDate, "yyyy-MM-dd");
        CheckoutDate.setEditor(de_CheckoutDate);
        CheckoutDate.setBounds(477, 61, 147, 21);
        // Default checkout to tomorrow
        CheckoutDate.setValue(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24))); 
        PanelList.add(CheckoutDate);

        // --- Room Type (MOVED OUTSIDE THE LISTENER) ---
        JLabel RoomTypeHeader = new JLabel("Room Type:");
        RoomTypeHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        RoomTypeHeader.setBounds(20, 65, 105, 13);
        PanelList.add(RoomTypeHeader);

        RoomtypeList = new JComboBox<>(roomTypes);
        RoomtypeList.setFont(new Font("Tahoma", Font.ITALIC, 10));
        RoomtypeList.setBounds(85, 61, 147, 20);
        PanelList.add(RoomtypeList);

        // --- Date Logic Listener ---
        // This ensures the logic runs, but doesn't redraw the UI components repeatedly
        CheckinDate.addChangeListener(e -> {
            Date checkInDate = (Date) CheckinDate.getValue();
            Date checkOutDate = (Date) CheckoutDate.getValue();

            // If checkout is before checkin, force checkout to checkin + 1 day
            if (!checkOutDate.after(checkInDate)) {
                CheckoutDate.setValue(new Date(checkInDate.getTime() + 24L * 60 * 60 * 1000));
            }
            
        });
        
        JLabel NumberRooms = new JLabel("No. Rooms:");
        NumberRooms.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        NumberRooms.setBounds(20, 92, 105, 13);
        PanelList.add(NumberRooms);
        
        roomNum = new JTextField();
        roomNum.setFont(new Font("Tahoma", Font.BOLD, 10));
        roomNum.setText("1");
        roomNum.setBounds(85, 88, 147, 18);
        PanelList.add(roomNum);
        roomNum.setColumns(10);
        
        JLabel Header2 = new JLabel("---- ADD-ONS -----------------------------------------------------------------------------------------------------------------------------------------------");
        Header2.setForeground(SystemColor.textHighlight);
        Header2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header2.setBounds(10, 239, 970, 19);
        PanelList.add(Header2);
        
        JLabel Header3 = new JLabel("---- AMENITIES (GYM / POOL / SPA)\r\n------------------------------------------------------------------------------------------------------------------------------------");
        Header3.setForeground(SystemColor.textHighlight);
        Header3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header3.setBounds(10, 115, 970, 19);
        PanelList.add(Header3);
        
        JLabel Header4 = new JLabel("---- BOOKER INFORMATION\r\n------------------------------------------------------------------------------------------------------------------------------------");
        Header4.setForeground(SystemColor.textHighlight);
        Header4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header4.setBounds(10, 389, 970, 19);
        PanelList.add(Header4);
        
                // --- Header ---
                JLabel LStar_header = new JLabel("Welcome, to Lanlya Hotel Reservation System!");
                LStar_header.setBounds(-13, 461, 1020, 113);
                PanelList.add(LStar_header);
                LStar_header.setForeground(new Color(255, 215, 0));
                LStar_header.setFont(new Font("Tahoma", Font.ITALIC, 10));
                LStar_header.setHorizontalAlignment(SwingConstants.CENTER);
                
                JButton submitBtn = new JButton("Submit Booking");
                submitBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
                submitBtn.setForeground(new Color(255, 255, 255));
                submitBtn.setBackground(new Color(100, 149, 237));
                submitBtn.setBounds(10, 470, 970, 20);
                PanelList.add(submitBtn);

                // Add ActionListener for button click
                submitBtn.addActionListener(e -> backEnd());
                
                Fname = new JTextField();
                Fname.setFont(new Font("Tahoma", Font.ITALIC, 10));
                Fname.setBounds(95, 418, 147, 18);
                PanelList.add(Fname);
                Fname.setColumns(10);
                
                Email = new JTextField();
                Email.setFont(new Font("Tahoma", Font.ITALIC, 10));
                Email.setBounds(95, 446, 147, 18);
                PanelList.add(Email);
                Email.setColumns(10);
                
                contactNum = new JTextField();
                contactNum.setFont(new Font("Tahoma", Font.ITALIC, 10));
                contactNum.setBounds(370, 418, 147, 18);
                PanelList.add(contactNum);
                contactNum.setColumns(10);

                // Simple beginner-friendly restriction
                contactNum.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyTyped(java.awt.event.KeyEvent e) {
                        char c = e.getKeyChar();

                        // block non-digits
                        if (!Character.isDigit(c)) {
                            e.consume(); // ignore this key
                        }

                        // block if length exceeds 10
                        if (contactNum.getText().length() >= 10) {
                            e.consume();
                        }
                    }
                });
                
             // --- TEXT FIELDS (Already looked correct, but double checking) ---
                ageField = new JTextField();
                ageField.setBounds(370, 446, 147, 18);
                PanelList.add(ageField);
                ageField.setColumns(10);
                
                // Labels don't need to be static, so these are fine as they are
                JLabel lblName = new JLabel("Name:\r\n");
                lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblName.setBounds(10, 421, 105, 13);
                PanelList.add(lblName);
                
                JLabel lblEmailAddress = new JLabel("Email Address:");
                lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblEmailAddress.setBounds(10, 446, 105, 13);
                PanelList.add(lblEmailAddress);
                
                JLabel lblContactNo = new JLabel("Contact No.: 63+");
                lblContactNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblContactNo.setBounds(270, 421, 105, 13);
                PanelList.add(lblContactNo);
                
                // --- FIX 1: ADD-ON CHECKBOXES (Removed "JCheckBox") ---
                Addon1 = new JCheckBox("Blanket (Php 250):\r\n");
                Addon1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                Addon1.setBackground(new Color(255, 215, 0));
                Addon1.setBounds(33, 271, 136, 20);
                PanelList.add(Addon1);
                
                Addon2 = new JCheckBox("Toiletries (Php 200):");
                Addon2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                Addon2.setBackground(new Color(255, 215, 0));
                Addon2.setBounds(33, 306, 136, 20);
                PanelList.add(Addon2);
                
                Addon3 = new JCheckBox("Pillow (Php 100):");
                Addon3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                Addon3.setBackground(new Color(255, 215, 0));
                Addon3.setBounds(33, 341, 136, 20);
                PanelList.add(Addon3);
                
                JLabel childNumHeader = new JLabel("No. of Children:");
                childNumHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                childNumHeader.setBounds(652, 39, 78, 13);
                PanelList.add(childNumHeader);
                
                JLabel adultNumHeader = new JLabel("No. of Adults:");
                adultNumHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                adultNumHeader.setBounds(652, 65, 78, 13);
                PanelList.add(adultNumHeader);
                
                adultNum = new JTextField();
                adultNum.setText("1");
                adultNum.setFont(new Font("Tahoma", Font.ITALIC, 10));
                adultNum.setBounds(740, 62, 147, 18);
                PanelList.add(adultNum);
                adultNum.setColumns(10);
                
                childNum = new JTextField();
                childNum.setText("1");
                childNum.setFont(new Font("Tahoma", Font.ITALIC, 10));
                childNum.setColumns(10);
                childNum.setBounds(740, 39, 147, 18);
                PanelList.add(childNum);
                
                JLabel lblleaveBlankIf = new JLabel("(Leave blank if not applicable)");
                lblleaveBlankIf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblleaveBlankIf.setBounds(740, 106, 240, 13);
                PanelList.add(lblleaveBlankIf);
                
                // --- FIX 2: ADD-ON SPINNERS (Removed "JSpinner") ---
                blanketNum = new JSpinner();
                blanketNum.setBounds(187, 272, 116, 20);
                PanelList.add(blanketNum);
                
                toiletNum = new JSpinner();
                toiletNum.setBounds(187, 307, 116, 20);
                PanelList.add(toiletNum);
                
                pillowNum = new JSpinner();
                pillowNum.setBounds(187, 342, 116, 20);
                PanelList.add(pillowNum);
                
                // --- FIX 3: SERVICE CHECKBOXES (Removed "JCheckBox") ---
                Service1 = new JCheckBox("Gym - 500/Day");
                Service1.setFont(new Font("Tahoma", Font.BOLD, 10));
                Service1.setBackground(new Color(255, 215, 0));
                Service1.setBounds(33, 159, 116, 20);
                PanelList.add(Service1);
                
                Service2 = new JCheckBox("Pool - 300/Day");
                Service2.setFont(new Font("Tahoma", Font.BOLD, 10));
                Service2.setBackground(new Color(255, 215, 0));
                Service2.setBounds(33, 185, 116, 20);
                PanelList.add(Service2);
                
                Service3 = new JCheckBox("Spa - Prices vary");
                Service3.setFont(new Font("Tahoma", Font.BOLD, 10));
                Service3.setBackground(new Color(255, 215, 0));
                Service3.setBounds(33, 213, 116, 20);
                PanelList.add(Service3);
                
                // --- FIX 4: AMENITY SPINNERS (Removed "JSpinner") ---
                gymSpinner1 = new JSpinner();
                gymSpinner1.setFont(new Font("Tahoma", Font.ITALIC, 10));
                gymSpinner1.setBounds(187, 160, 116, 20);
                PanelList.add(gymSpinner1);
                
                poolSpinner1 = new JSpinner();
                poolSpinner1.setFont(new Font("Tahoma", Font.ITALIC, 10));
                poolSpinner1.setBounds(187, 186, 116, 20);
                PanelList.add(poolSpinner1);
                
                spaSpinner1 = new JSpinner();
                spaSpinner1.setFont(new Font("Tahoma", Font.ITALIC, 10));
                spaSpinner1.setBounds(187, 214, 116, 20);
                PanelList.add(spaSpinner1);
                
                gymSpinner2 = new JSpinner();
                gymSpinner2.setFont(new Font("Tahoma", Font.ITALIC, 10));
                gymSpinner2.setBounds(369, 160, 116, 20);
                PanelList.add(gymSpinner2);
                
                poolSpinner2 = new JSpinner();
                poolSpinner2.setFont(new Font("Tahoma", Font.ITALIC, 10));
                poolSpinner2.setBounds(369, 186, 116, 20);
                PanelList.add(poolSpinner2);
                
                spaSpinner2 = new JSpinner();
                spaSpinner2.setFont(new Font("Tahoma", Font.ITALIC, 10));
                spaSpinner2.setBounds(369, 214, 116, 20);
                PanelList.add(spaSpinner2);
                
                gymSpinner3 = new JSpinner();
                gymSpinner3.setFont(new Font("Tahoma", Font.ITALIC, 10));
                gymSpinner3.setBounds(546, 160, 116, 20);
                PanelList.add(gymSpinner3);
                
                poolSpinner3 = new JSpinner();
                poolSpinner3.setFont(new Font("Tahoma", Font.ITALIC, 10));
                poolSpinner3.setBounds(546, 186, 116, 20);
                PanelList.add(poolSpinner3);
                
                // --- FIX 5: SPA COMBO BOX (Removed "JComboBox") ---
                spaSpinner3 = new JComboBox();
                spaSpinner3.setFont(new Font("Tahoma", Font.ITALIC, 10));
                String[] spaTypes = {"Foot (" + SPA_FOOT + ")", "Aroma (" + SPA_AROMA + ")", "Thai (" + SPA_THAI + ")"};
                spaSpinner3.setModel(new DefaultComboBoxModel(spaTypes));
                spaSpinner3.setBounds(740, 213, 117, 20);
                PanelList.add(spaSpinner3);
                
                JLabel spaHeader = new JLabel("No. of Adults:");
                spaHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
                spaHeader.setBounds(546, 217, 116, 13);
                PanelList.add(spaHeader);
                
                JLabel lblAge = new JLabel("Age:       ");
                lblAge.setHorizontalAlignment(SwingConstants.TRAILING);
                lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblAge.setBounds(270, 449, 105, 13);
                PanelList.add(lblAge);
                
                JLabel lblNoOfPersons = new JLabel("No. of Persons");
                lblNoOfPersons.setHorizontalAlignment(SwingConstants.LEFT);
                lblNoOfPersons.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblNoOfPersons.setBounds(187, 137, 177, 16);
                PanelList.add(lblNoOfPersons);
                
                JLabel lblPwdseniorCitizens = new JLabel("PWD/Senior Citizens");
                lblPwdseniorCitizens.setHorizontalAlignment(SwingConstants.LEFT);
                lblPwdseniorCitizens.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblPwdseniorCitizens.setBounds(370, 137, 177, 16);
                PanelList.add(lblPwdseniorCitizens);
                
                JLabel lblDays = new JLabel("Days");
                lblDays.setHorizontalAlignment(SwingConstants.LEFT);
                lblDays.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblDays.setBounds(546, 137, 177, 16);
                PanelList.add(lblDays);
                
                JLabel lblService = new JLabel("Service");
                lblService.setHorizontalAlignment(SwingConstants.LEFT);
                lblService.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblService.setBounds(35, 137, 177, 16);
                PanelList.add(lblService);
                
                JLabel lblTypespaOnly = new JLabel("Type (Spa Only)");
                lblTypespaOnly.setHorizontalAlignment(SwingConstants.LEFT);
                lblTypespaOnly.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblTypespaOnly.setBounds(740, 137, 177, 16);
                PanelList.add(lblTypespaOnly);

        // --- Background Image ---
        // Note: Ensure /project_CaseStudy/BG1.jpg exists in your src folder!
        try {
            
            JLabel lblNewLabel = new JLabel("We Care, We Love and We Serve");
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
            lblNewLabel.setForeground(new Color(255, 215, 0));
            lblNewLabel.setBounds(20, 621, 990, 38);
            contentPane.add(lblNewLabel);
            LStar_background = new JLabel(new ImageIcon(LStar_UserExp.class.getResource("/project_CaseStudy/BG1.jpg")));
            LStar_background.setBounds(10, 10, 990, 663);
            LStar_background.setHorizontalAlignment(SwingConstants.LEFT);
            contentPane.add(LStar_background);
        } catch (Exception e) {
            System.out.println("Image not found. Background will be blank.");
        }
    }

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> infoBox());
    }
}