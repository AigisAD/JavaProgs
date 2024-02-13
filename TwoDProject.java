/**
 * Author: Jacques Gueye
 * Assignment 1: ProjectOnTwoD 
 * Date: 03/05/21
 * Course: CS56 Adv Java (1791)
 * 
 * Project Description: Function "isConsecutiveFour" tests whether a 
 * two-dimensional list has four consecutive numbers of the same value, 
 * either horizontally, vertically, or diagonally, and displays true or false;
 * main is used for testing and allows user to enter # of rows and columns in
 * list.
 */
import java.util.Scanner;
import java.util.Random;

public class TwoDProject {


    public static void main(String[] args) {
        char restart;
        do{
            Scanner input = new Scanner(System.in);
            System.out.print("Enter number of rows: ");
            int rows = input.nextInt();
            System.out.print("Enter number of columns: ");
            int columns = input.nextInt();
            int[][] twoDArray = new int[rows][columns];
            Random rand = new Random();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    twoDArray[i][j] = rand.nextInt(10);
                    //twoDArray[i][j]= input.nextInt(); // for manual testing
                }
            }
            System.out.println();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(twoDArray[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(isConsecutiveFour(twoDArray));
            System.out.print("Restart? 'Y' or 'N': ");// for easy re-testing
            restart = input.next().charAt(0);
        } while (restart=='Y'||restart=='y');
        
        
    }

    /***
     * Takes in a 2 dimensional array and outputs T/F for if it
     * contains 4 consecutive numbers in any direction.
     */
    public static boolean isConsecutiveFour(int[][] values) {
        //row check
        for (int i = 0; i < values.length; i++) {
            int counter = 1;
            for (int j = 0; j < values[0].length - 1; j++) {
                if (values[i][j] == values[i][j+1]) {
                    counter++;
                    if (counter == 4)  return true; //4 consecutive
                }
                else
                   counter=1; //reset counter
               
            }
        }
        //column check
        for (int i = 0; i < values[0].length; i++) {
            int counter = 1;
            for (int j = 0; j < values.length-1; j++) {
                if (values[j][i] == values[j+1][i]) {
                    counter++;
                    if (counter == 4) return true;
                }
                else
                   counter=1;
            }
        }
        /********
        *Diagonal checks: Checks "main" diagonals, then diagonals
        *above and below those. Probably not the most efficient (or pretty)
        *way, but does the job with little sacrifice to function's Big-O.
        ********/
        // \ diagonal and below
        for (int j=0; values.length-j>=4;j++){//for "shifting" the diagonal down
            int counter=1;
            //for going through elements in diagonal
            for (int i=0;i<values.length-j-1&&i<values[0].length-1;i++){
                if (values[i+j][i] == values[i+j+1][i+1]) {
                    counter++;
                    if (counter == 4) return true;
                }
                else
                    counter=1;
            }
        }
        // \ diagonal and above
        for (int j=0; values[0].length-j>=4;j++){
            int counter=1;
            for (int i=0;i<values.length-1&&i<values[0].length-j-1;i++){
                if (values[i][i+j] == values[i+1][i+j+1]) {
                    counter++;
                    if (counter == 4) return true;
                }
                else
                    counter=1;
            }
        }
        // / diagonal and below
        for (int j=0; values[0].length-j>=4;j++){
            int counter=1;
            for (int i=0;i<values.length-1&&i<values[0].length-j-1;i++){
                if (values[values.length-1-i][i+j] == values[values.length-1-i-1][i+j+1]) {
                    counter++;
                    if (counter == 4) return true;
                }
                else
                   counter=1;
            }
        }
        // /diagonal and above
        for (int j=0; values.length-j>=4;j++){
            int counter=1;
            for (int i=0;i<values.length-j-1&&i<values[0].length-1;i++){
                if (values[values.length-1-j-i][i] == values[values.length-1-j-i-1][i+1]) {
                    counter++;
                    if (counter == 4) return true;
                }
                else
                   counter=1;
            }
        }        
        
        return false;//no 4 consecutives
    
    }
}
