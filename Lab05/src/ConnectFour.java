import java.util.Scanner;

public class ConnectFour
{
   // global scanner declaration
   public static Scanner scnr = new Scanner(System.in);

   public static void printBoard(char[][] array)
   {
      // prints board line by line setting row 0 to the bottom
      for (int i = array.length - 1; i >= 0; i--)
      {
         for (int j = 0; j < array[i].length; j++)
         {
            System.out.print(array[i][j]);
         }
         System.out.println();
      }
   }
    
   public static void initializeBoard(char[][] array)
   {
      // assigns a '-' to each value in the board array
      for (int i = 0; i < array.length; i++)
      {
         for (int j = 0; j < array[i].length; j++)
         {
            array[i][j] = '-';
         }
      }
   }

   public static int insertChip(char[][] array, int col, char chipType)
   {
      int row;
      // places applicable token in next row containing a '-'
      for (row = 0; row < array.length; row++)
      {
         if (array[row][col] == '-')
         {
            // inserts 'x' for player 1
            if (chipType == 'x')
            {
               array[row][col] = 'x';
            }
            // inserts 'o' for player 2
            else
            {
               array[row][col] = 'o';
            }
			 return row;
         }
      }
   return row;
   }

   public static boolean checkIfWinner(char[][] array, int col, int row, char chipType)
   {
      boolean winner = false;
      int foundColumn = 0;
      int foundRow = 0;
      // counts consecutive characters down a column and adds to the count for each
      for(int i = row; i > 0; i--)
      {
         if(array[i][col] == chipType && array[i - 1][col] == chipType)
         {
            ++foundColumn;
            // if four match each other, a winner is declared
            if(foundColumn >= 3)
            {
               winner = true;
            }
         }
      }
      // counts consecutive characters in a row, adds to the count for each
      for(int i = 1; i < array[0].length; i++)
      {
         if(array[row][i] == chipType && array[row][i - 1] == chipType)
         {
            ++foundRow;
            if (foundRow >= 3)
            {
               winner = true;
            }
         }
         // resets count to 0 once consecutive characters mismatch
         else
         {
            foundRow = 0;
         }
      }
      return winner;
   }
   
   public static void main(String[] args)
   {
      int height;
      int length;
      int column;
      int row;
      int gameTurn = 0; // initial game count for a draw scenario

      System.out.print("What would you like the height of the board to be? ");
      height = scnr.nextInt();

      System.out.print("What would you like the length of the board to be? ");
      length = scnr.nextInt();

      // board dimension build, initialization, and printout
      char[][] board = new char[height][length];

      initializeBoard(board);
      printBoard(board);

      System.out.println("Player 1: x");
      System.out.println("Player 2: o");

      // loop repeats as long as there are open spots left in the board
      while(gameTurn < height * length)
      {
         // player 1's turn
         System.out.print("Player 1: Which column would you like to choose? ");
         column = scnr.nextInt();

         row = insertChip(board, column, 'x');
         printBoard(board);

         // statement to check for winner after each turn
         if(checkIfWinner(board, column, row, 'x'))
         {
            System.out.println("Player 1 won the game!");
            break;
         }
         ++gameTurn; // count added for draw scenario

         // player 2's turn
         System.out.print("Player 2: Which column would you like to choose? ");
         column = scnr.nextInt();

         row = insertChip(board, column, 'o');
         printBoard(board);

         // statement to check for winner after each turn
         if(checkIfWinner(board, column, row, 'o'))
         {
            System.out.println("Player 2 won the game!");
            break;
         }
         ++gameTurn; // count added for draw scenario
      }
      //  draw statement for when number of turns taken equal number of spots
      if(gameTurn == height * length)
      {
         System.out.print("Draw. Nobody wins.");
      }
   }
}
