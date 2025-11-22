package project_CaseStudy;

public class UserData {
    private static String[][] users = {
        {"john", "1234"},
        {"admin", "adminpass"},
        {"guest", "guest123"},
        {"Javadingan", "gayass"}
    };

    public static boolean validate(String user, String pass) {
        for (String[] u : users) {
            if (u[0].equals(user) && u[1].equals(pass)) {
                return true;
            }
        }
        return false;
    }
}
