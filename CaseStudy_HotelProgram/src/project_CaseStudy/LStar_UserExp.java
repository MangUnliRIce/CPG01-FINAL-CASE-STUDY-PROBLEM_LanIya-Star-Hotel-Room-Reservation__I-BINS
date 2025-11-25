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
    private JTextField textField;

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
    	setBackground(new Color(255, 255, 255));
        setTitle("Lanlya Hotel Reservation");
        setResizable(false);
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
        JLabel CheckInHeader = new JLabel("Check-in-Date:");
        CheckInHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        CheckInHeader.setBounds(242, 39, 78, 13);
        numRooms.add(CheckInHeader);

        CheckinDate = new JSpinner(new SpinnerDateModel());
        CheckinDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
        JSpinner.DateEditor de_CheckinDate = new JSpinner.DateEditor(CheckinDate, "yyyy-MM-dd");
        CheckinDate.setEditor(de_CheckinDate);
        CheckinDate.setBounds(338, 36, 147, 20);
        CheckinDate.setValue(new Date()); // Set to today
        numRooms.add(CheckinDate);

        // --- Check Out Date ---
        JLabel CheckOutHeader = new JLabel("Check-out-Date:");
        CheckOutHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
        CheckOutHeader.setBounds(242, 65, 93, 13);
        numRooms.add(CheckOutHeader);

        CheckoutDate = new JSpinner(new SpinnerDateModel());
        CheckoutDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
        JSpinner.DateEditor de_CheckoutDate = new JSpinner.DateEditor(CheckoutDate, "yyyy-MM-dd");
        CheckoutDate.setEditor(de_CheckoutDate);
        CheckoutDate.setBounds(338, 61, 147, 21);
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
        
        textField = new JTextField();
        textField.setText("1");
        textField.setBounds(85, 88, 147, 18);
        numRooms.add(textField);
        textField.setColumns(10);
        
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