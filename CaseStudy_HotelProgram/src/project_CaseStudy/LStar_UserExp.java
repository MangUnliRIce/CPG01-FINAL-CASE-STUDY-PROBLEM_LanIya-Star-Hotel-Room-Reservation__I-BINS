package project_CaseStudy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    String[] roomTypes = {"Standard", "Deluxe", "Quadruple", "Family", "Suite"};
    int[] capacities = {1, 2, 4, 6, 4};
    int[] availableLocal = {5, 4, 5, 3, 2};
    int[] availableIntl = {5, 4, 5, 3, 2};

    // Rates
    int[] localLean = {2000, 3000, 4000, 5000, 6000};
    int[] localHigh = {4000, 5000, 7000, 9000, 11000};
    int[] localPeak = {6000, 8000, 10000, 12000, 14000};
    int[] localSuper = {9000, 12000, 15000, 18000, 21000};

    int[] intlLean = {2500, 5000, 7500, 10000, 12500};
    int[] intlHigh = {4500, 7000, 9500, 12000, 14500};
    int[] intlPeak = {6500, 9000, 11500, 14000, 16500};
    int[] intlSuper = {10000, 13000, 16000, 19000, 22000};

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel LStar_background;

    // --- CLASS LEVEL VARIABLES (Accessible by the whole class) ---
    private JComboBox<String> DestinationList;
    private JComboBox<String> RoomtypeList;
    private JSpinner CheckinDate;
    private JSpinner CheckoutDate;
    private JTextField roomNum;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField adultNum;
    private JTextField childNum;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LStar_UserExp frame = new LStar_UserExp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
        JPanel numRooms = new JPanel();
        numRooms.setBackground(new Color(255, 215, 0));
        numRooms.setBounds(10, 111, 990, 500);
        numRooms.setLayout(null);
        contentPane.add(numRooms);

        JLabel Header = new JLabel("----HOTEL RESERVATION BOOKING DETAILS ------------------------------------------------------------------------------------------------------------------------------------");
        Header.setForeground(SystemColor.textHighlight);
        Header.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header.setBounds(10, 10, 970, 19);
        numRooms.add(Header);

        // --- Destination ---
        JLabel DestinationHeader = new JLabel("Destination:");
        DestinationHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        DestinationHeader.setBounds(20, 39, 105, 13);
        numRooms.add(DestinationHeader);

        DestinationList = new JComboBox<>(new String[]{"Baguio", "Manila", "Cebu", "Tondo", "Makati", "Macau", "Hong Kong"});
        DestinationList.setFont(new Font("Tahoma", Font.PLAIN, 10));
        DestinationList.setBounds(85, 36, 147, 19);
        numRooms.add(DestinationList);

        // --- Check In Date ---
        JLabel CheckInHeader = new JLabel("Check-in-Date: (YYYY-MM-DD)");
        CheckInHeader.setHorizontalAlignment(SwingConstants.LEFT);
        CheckInHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        CheckInHeader.setBounds(290, 39, 177, 13);
        numRooms.add(CheckInHeader);

        CheckinDate = new JSpinner(new SpinnerDateModel());
        CheckinDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
        JSpinner.DateEditor de_CheckinDate = new JSpinner.DateEditor(CheckinDate, "yyyy-MM-dd");
        CheckinDate.setEditor(de_CheckinDate);
        CheckinDate.setBounds(477, 36, 147, 20);
        CheckinDate.setValue(new Date()); // Set to today
        numRooms.add(CheckinDate);

        // --- Check Out Date ---
        JLabel CheckOutHeader = new JLabel("Check-out-Date: (YYYY-MM-DD)");
        CheckOutHeader.setHorizontalAlignment(SwingConstants.LEFT);
        CheckOutHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        CheckOutHeader.setBounds(290, 65, 177, 13);
        numRooms.add(CheckOutHeader);

        CheckoutDate = new JSpinner(new SpinnerDateModel());
        CheckoutDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
        JSpinner.DateEditor de_CheckoutDate = new JSpinner.DateEditor(CheckoutDate, "yyyy-MM-dd");
        CheckoutDate.setEditor(de_CheckoutDate);
        CheckoutDate.setBounds(477, 61, 147, 21);
        // Default checkout to tomorrow
        CheckoutDate.setValue(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24))); 
        numRooms.add(CheckoutDate);

        // --- Room Type (MOVED OUTSIDE THE LISTENER) ---
        JLabel RoomTypeHeader = new JLabel("Room Type:");
        RoomTypeHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        RoomTypeHeader.setBounds(20, 65, 105, 13);
        numRooms.add(RoomTypeHeader);

        RoomtypeList = new JComboBox<>(new String[] {"Standard", "Deluxe", "Quadruple", "Family", "Suite"});
        RoomtypeList.setFont(new Font("Tahoma", Font.PLAIN, 10));
        RoomtypeList.setBounds(85, 61, 147, 20);
        numRooms.add(RoomtypeList);

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
        numRooms.add(NumberRooms);
        
        roomNum = new JTextField();
        roomNum.setText("1");
        roomNum.setBounds(85, 88, 147, 18);
        numRooms.add(roomNum);
        roomNum.setColumns(10);
        
        JLabel Header2 = new JLabel("---- ADD-ONS -----------------------------------------------------------------------------------------------------------------------------------------------");
        Header2.setForeground(SystemColor.textHighlight);
        Header2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header2.setBounds(10, 220, 970, 19);
        numRooms.add(Header2);
        
        JLabel Header3 = new JLabel("---- AMENITIES (GYM / POOL / SPA)\r\n------------------------------------------------------------------------------------------------------------------------------------");
        Header3.setForeground(SystemColor.textHighlight);
        Header3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header3.setBounds(10, 115, 970, 19);
        numRooms.add(Header3);
        
        JLabel Header4 = new JLabel("---- BOOKER INFORMATION\r\n------------------------------------------------------------------------------------------------------------------------------------");
        Header4.setForeground(SystemColor.textHighlight);
        Header4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        Header4.setBounds(10, 370, 970, 19);
        numRooms.add(Header4);
        
                // --- Header ---
                JLabel LStar_header = new JLabel("Welcome, to Lanlya Hotel Reservation System!");
                LStar_header.setBounds(-13, 461, 1020, 113);
                numRooms.add(LStar_header);
                LStar_header.setForeground(new Color(255, 215, 0));
                LStar_header.setFont(new Font("Tahoma", Font.BOLD, 35));
                LStar_header.setHorizontalAlignment(SwingConstants.CENTER);
                
                JButton submitBtn = new JButton("Submit Booking\r\n");
                submitBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
                submitBtn.setForeground(new Color(255, 255, 255));
                submitBtn.setBackground(new Color(100, 149, 237));
                submitBtn.setBounds(10, 470, 970, 20);
                numRooms.add(submitBtn);
                
                textField_1 = new JTextField();
                textField_1.setBounds(95, 399, 147, 18);
                numRooms.add(textField_1);
                textField_1.setColumns(10);
                
                textField_2 = new JTextField();
                textField_2.setBounds(95, 427, 147, 18);
                numRooms.add(textField_2);
                textField_2.setColumns(10);
                
                textField_3 = new JTextField();
                textField_3.setBounds(338, 399, 147, 18);
                numRooms.add(textField_3);
                textField_3.setColumns(10);
                
                textField_4 = new JTextField();
                textField_4.setBounds(338, 427, 147, 18);
                numRooms.add(textField_4);
                textField_4.setColumns(10);
                
                JLabel lblName = new JLabel("Name:\r\n");
                lblName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblName.setBounds(10, 402, 105, 13);
                numRooms.add(lblName);
                
                JLabel lblEmailAddress = new JLabel("Email Address:");
                lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblEmailAddress.setBounds(10, 427, 105, 13);
                numRooms.add(lblEmailAddress);
                
                JLabel lblContactNo = new JLabel("Contact No.:");
                lblContactNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                lblContactNo.setBounds(270, 402, 105, 13);
                numRooms.add(lblContactNo);
                
                JCheckBox Addon1 = new JCheckBox("Blanket\r\n");
                Addon1.setBackground(new Color(255, 215, 0));
                Addon1.setBounds(33, 245, 92, 20);
                numRooms.add(Addon1);
                
                JCheckBox Addon2 = new JCheckBox("Toilettries");
                Addon2.setBackground(new Color(255, 215, 0));
                Addon2.setBounds(33, 280, 92, 20);
                numRooms.add(Addon2);
                
                JCheckBox Addon3 = new JCheckBox("Pillow");
                Addon3.setBackground(new Color(255, 215, 0));
                Addon3.setBounds(33, 315, 92, 20);
                numRooms.add(Addon3);
                
                JLabel childNumHeader = new JLabel("No. of Children:");
                childNumHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                childNumHeader.setBounds(652, 39, 78, 13);
                numRooms.add(childNumHeader);
                
                JLabel adultNumHeader = new JLabel("No. of Adults:");
                adultNumHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                adultNumHeader.setBounds(652, 65, 78, 13);
                numRooms.add(adultNumHeader);
                
                adultNum = new JTextField();
                adultNum.setBounds(740, 62, 147, 18);
                numRooms.add(adultNum);
                adultNum.setColumns(10);
                
                childNum = new JTextField();
                childNum.setColumns(10);
                childNum.setBounds(740, 39, 147, 18);
                numRooms.add(childNum);
                
                JLabel lblleaveBlankIf = new JLabel("(Leave blank if not applicable)");
                lblleaveBlankIf.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
                lblleaveBlankIf.setBounds(740, 106, 240, 13);
                numRooms.add(lblleaveBlankIf);
                
                JSpinner spinner = new JSpinner();
                spinner.setBounds(126, 246, 116, 20);
                numRooms.add(spinner);
                
                JSpinner spinner_1 = new JSpinner();
                spinner_1.setBounds(126, 281, 116, 20);
                numRooms.add(spinner_1);
                
                JSpinner spinner_2 = new JSpinner();
                spinner_2.setBounds(126, 316, 116, 20);
                numRooms.add(spinner_2);
                
                JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
                chckbxNewCheckBox.setBounds(33, 140, 116, 20);
                numRooms.add(chckbxNewCheckBox);
                
                JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
                chckbxNewCheckBox_1.setBounds(33, 166, 116, 20);
                numRooms.add(chckbxNewCheckBox_1);
                
                JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
                chckbxNewCheckBox_2.setBounds(33, 194, 116, 20);
                numRooms.add(chckbxNewCheckBox_2);
                
                JSpinner spinner_3 = new JSpinner();
                spinner_3.setBounds(187, 141, 116, 20);
                numRooms.add(spinner_3);
                
                JSpinner spinner_4 = new JSpinner();
                spinner_4.setBounds(187, 167, 116, 20);
                numRooms.add(spinner_4);
                
                JSpinner spinner_5 = new JSpinner();
                spinner_5.setBounds(187, 195, 116, 20);
                numRooms.add(spinner_5);
                
                JSpinner spinner_3_1 = new JSpinner();
                spinner_3_1.setBounds(369, 141, 116, 20);
                numRooms.add(spinner_3_1);
                
                JSpinner spinner_3_2 = new JSpinner();
                spinner_3_2.setBounds(369, 167, 116, 20);
                numRooms.add(spinner_3_2);
                
                JSpinner spinner_3_3 = new JSpinner();
                spinner_3_3.setBounds(369, 195, 116, 20);
                numRooms.add(spinner_3_3);
                
                JSpinner spinner_3_4 = new JSpinner();
                spinner_3_4.setBounds(546, 141, 116, 20);
                numRooms.add(spinner_3_4);
                
                JSpinner spinner_3_5 = new JSpinner();
                spinner_3_5.setBounds(546, 167, 116, 20);
                numRooms.add(spinner_3_5);
                
                JComboBox comboBox = new JComboBox();
                comboBox.setBounds(740, 194, 117, 20);
                numRooms.add(comboBox);
                
                JLabel Headerr11 = new JLabel("No. of Adults:");
                Headerr11.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
                Headerr11.setBounds(546, 197, 78, 13);
                numRooms.add(Headerr11);

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
}