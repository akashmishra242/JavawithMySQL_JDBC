import java.sql.*;
import java.util.Scanner;

//donr-6;recipeint-6;doctor-3;blood bank-5;;
class BloodB_DB {

    static String UserQuery() {
        String query = "";
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Press 1 if you wants to get some information aboutDonor");
            System.out.println("Press 2 if you wants to get some information aboutreceipient");
            System.out.println("Press 3 if you wants to get some information about BloodBank");
            int val = scan.nextInt();
            if (val == 1) {
                System.out.println("press 1 to see the list of donor who are older than oryounger than a specific age");
                System.out.println("press 2 to see the list of donor with a specific bloodgroup");
                int var = scan.nextInt();
                if (var == 1) {
                    System.out.println("for older than a specific age enter 1");
                    System.out.println("for younger than a specific age enter 2");
                    int temp = scan.nextInt();
                    if (temp == 1) {
                        System.out.println("enter the age");
                        int age = scan.nextInt();
                        query = "select * from donor where age > " + age;
                    }
                    if (temp == 2) {
                        System.out.println("enter the age");
                        int age = scan.nextInt();
                        query = "select * from donor where age < " + age;
                    }
                }
                if (var == 2) {
                    System.out.println("enter the blood group (example :- O+,O-,B-,B+) : ");
                    scan.nextLine();
                    String temp = scan.nextLine();
                    if (temp.charAt(0) == 'N') {
                        query = "select * from donor";
                    } else
                        query = "select * from donor where blood_group = \"" + temp + "\"";
                }
            }
            if (val == 2) {
                System.out
                        .println(
                                "press 1 to see the list of recipient who are older than or younger than a specific age");
                System.out.println("press 2 to see the list of recipient with a specific blood group");
                int var = scan.nextInt();
                if (var == 1) {
                    System.out.println("for older than a specific age enter 1");
                    System.out.println("for younger than a specific age enter 2");
                    int temp = scan.nextInt();
                    if (temp == 1) {
                        System.out.println("enter the age");
                        int age = scan.nextInt();
                        query = "select * from recipient where age > " + age;
                    }
                    if (temp == 2) {
                        System.out.println("enter the age");
                        int age = scan.nextInt();
                        query = "select * from recipient where age < " + age;
                    }
                }
                if (var == 2) {
                    System.out.println("enter the blood group (example :- O+,O-,B-,B+) : ");
                    scan.nextLine();
                    String temp = scan.nextLine();
                    if (temp.charAt(0) == 'N') {
                        query = "select * from recipient";
                    } else
                        query = "select * from recipient where blood_group = \"" + temp + "\"";
                }
            }
            if (val == 3) {

                System.out.println(
                        "press 1 if you wanna know that how much units blood is there in blood bank for respective blood group");

                System.out.println("press 2 if you wanna wanna see all the entries for a blood bank");

                int var = scan.nextInt();

                if (var == 1) {

                    query = "select blood_group,count(Blood_group) from blood_bank group by blood_group;";

                }

                if (var == 2) {

                    System.out.println("enter the blood bank id for the respective blood bank");
                    scan.nextLine();

                    int temp = scan.nextInt();
                    if (temp == 0) {
                        query = "select * from blood_bank";
                    } else

                        query = "select * from blood_bank where blood_donation_id = "
                                + temp + ";";
                }
            }
        }
        System.out.println(query);
        return query;
    }

    public static void main(String args[]) {

        String Url = "jdbc:mysql://localhost:3306/blood_donation_management_db";
        String UserName = "root";
        String Password = "Sunita@09";
        String Query = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    Url, UserName, Password);
            Statement stmt = con.createStatement();
            Query = UserQuery();
            ResultSet rs = stmt.executeQuery(Query);
            // ResultSet rs = stmt.executeQuery("select * from donor where
            // blood_group='B+'");
            while (rs.next())
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  "
                        + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
            con.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}