
/* 
2) Student Grade System:
Requirements
• Get the name of the student
• Get the number of subjects
• Get the marks in each subject
• Get the Attendance percentage
• Take the average of marks and convert it to percentage
• If attendance is below 75% deduct 5% from total percentage
• Calculate and Print the Grade
*/
import java.util.Scanner;

public class grade {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name of student: ");
        String nm = sc.nextLine();
        System.out.print("Enter number of subjects: ");
        int ns = sc.nextInt();
        double total = 0;
        double count = 0;
        for (int i = 0; i < ns; i++) {
            System.out.print("Enter marks in Subject" + (i + 1) + " :");
            double ms = sc.nextInt();
            total = total + ms;
        }
        for (int i = 0; i < ns; i++) {
            System.out.print("Enter attendance in Subject" + (i + 1) + " :");
            double att = sc.nextInt();
            if (att < 75) {
                count++;
            }
        }

        double avgmarks = total / ns;
        avgmarks = avgmarks - (5 * count);
        System.out.println("Name: " + nm);
        System.out.println("Marks: " + avgmarks);
        System.out.print("Grade: ");
        if (avgmarks > 90) {
            System.out.print("O");
        } else if (avgmarks > 80) {
            System.out.print("A+");
        } else if (avgmarks > 70) {
            System.out.print("A");
        } else if (avgmarks > 60) {
            System.out.println("B+");
        } else if (avgmarks > 50) {
            System.out.print("C");
        } else {
            System.out.print("Failed");
        }
        sc.close();
    }
}
