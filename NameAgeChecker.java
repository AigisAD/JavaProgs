
/**
 * Author: Jacques Gueye
 * Assignment 1: ProjectOnTwoD 
 * Date: 03/05/21
 * Course: CS56 Adv Java (1791)
 * Description: The given program reads a list of single-word first names 
 * and ages (ending with -1), and outputs that list with the age incremented. 
 * If the second input on a line is a String rather than an integer, it outputs
 * 0 for the age.
 * 
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class NameAgeChecker {

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      String inputName;
      int age;
      inputName = scnr.next();
      while (!inputName.equals("-1")) {
         // FIXME: The following line will throw an InputMismatchException.
         //        Insert a try/catch statement to catch the exception.
         try {
            age = scnr.nextInt();
         } 
         catch (InputMismatchException ex){
             age = -1;
             scnr.nextLine();// "Clear buffer"
         }
         System.out.println(inputName + " " + (age + 1)); 
         inputName = scnr.next();
      }
   }
}

