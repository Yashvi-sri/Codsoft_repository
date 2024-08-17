import java.util.*;
public class GradeCalculator{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of subjects");
        int sub = sc.nextInt();
        int sum = 0;
        String Grade;

        // Take marks obtained (out of 100) in each subject.

        System.out.println("Enter the marks of " +sub+ " subjects out of 100");
        for(int i = 1; i<=sub; i++){
            int marks =sc.nextInt(); 
            sum = sum + marks;  // Calculate Total Marks
        }
        double AvgPer = sum/sub;  // Calculate Average Percentage

        //Assign grades based on the average percentage achieved.

        if(AvgPer >= 90)
            Grade = "A+" ;
        else if(AvgPer >= 80 && AvgPer < 90)
            Grade = "A" ;
        else if(AvgPer >= 70 && AvgPer < 80)
            Grade = "B+" ;
        else if(AvgPer >= 60 && AvgPer < 70)
            Grade = "B" ;
        else if(AvgPer >= 50 && AvgPer < 60)
            Grade = "C" ;
        else if(AvgPer >= 45 && AvgPer < 50)
            Grade = "D" ;
        else 
            Grade = "Fail" ;

        // Display Results

        System.out.println("Student scored: "+  '\n' + "Total marks = " + sum +  '\n' + "Average Percentage = "+ AvgPer + "%"+  '\n' + "Grade = " +Grade ); 
        

    
    
    }
}

