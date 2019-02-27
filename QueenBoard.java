public class QueenBoard{
  // TESTING
  public static void main(String[] args){
    QueenBoard board1 = new QueenBoard(5);
    System.out.println(board1.countSolutions());
    for (int x = 0; x < 6; x++){
      board1.runTest(x);
    }
    /*
    QueenBoard board1 = new QueenBoard(8);

    // should print out empty board
    System.out.println(board1);

    board1.addQueen(0,0);

    // should print out one queen on upper left
    System.out.println(board1);

    board1.addQueen(0,1);
    board1.addQueen(4,3);
    board1.addQueen(1,1); // testing diagonal

    System.out.println(board1);

    board1.removeQueen(2,1);
    board1.removeQueen(0,0);
    board1.removeQueen(0,1);
    board1.removeQueen(4,3);
    board1.removeQueen(1,1);

    // should have no queens
    System.out.println(board1);
    */

    /*
    // DRIVER THAT VISHWAA GAVE ME
    QueenBoard  board = new QueenBoard(0); // testing with size 0
    System.out.println(board.countSolutions());
    System.out.println("0 \n"); // actual result to match with program's result
    QueenBoard  board1 = new QueenBoard(1); // testing with size 1
    System.out.println(board1.countSolutions());
    System.out.println("1 \n"); // actual result to match with program's result
    QueenBoard  board2 = new QueenBoard(2); // testing with size 2
    System.out.println(board.countSolutions());
    System.out.println("0 \n"); // actual result to match with program's result
    QueenBoard  board3 = new QueenBoard(3); // testing with size 3
    System.out.println(board3.countSolutions());
    System.out.println("0 \n"); // actual result to match with program's result
    QueenBoard  board4 = new QueenBoard(4); // testing with size 4
    System.out.println(board4.countSolutions());
    System.out.println("2 \n"); // actual result to match with program's result
    QueenBoard  board5 = new QueenBoard(5); // testing with size 5
    System.out.println(board5.countSolutions());
    System.out.println("10 \n"); // actual result to match with program's result
    QueenBoard  board6 = new QueenBoard(6); // testing with size 6
    System.out.println(board6.countSolutions());
    System.out.println("4 \n"); // actual result to match with program's result
    QueenBoard  board7 = new QueenBoard(7); // testing with size 7
    System.out.println(board7.countSolutions());
    System.out.println("40 \n"); // actual result to match with program's result
    QueenBoard  board8 = new QueenBoard(8); // testing with size 8
    System.out.println(board8.countSolutions());
    System.out.println("90 \n"); // actual result to match with program's result
    QueenBoard  board9 = new QueenBoard(9); // testing with size 9
    System.out.println(board9.countSolutions());
    System.out.println("352 \n"); // actual result to match with program's result
    QueenBoard  board10 = new QueenBoard(10); // testing with size 10
    System.out.println(board10.countSolutions());
    System.out.println("724 \n"); // actual result to match with program's result
    QueenBoard  board11 = new QueenBoard(11); // testing with size 11
    System.out.println(board11.countSolutions());
    System.out.println("2680 \n"); // actual result to match with program's result
    QueenBoard  board12 = new QueenBoard(12); // testing with size 12
    System.out.println(board12.countSolutions());
    System.out.println("14200 \n"); // actual result to match with program's result
    QueenBoard  board13 = new QueenBoard(13); // testing with size 13
    System.out.println(board13.countSolutions());
    System.out.println("73712 \n"); // actual result to match with program's result
    QueenBoard  board14 = new QueenBoard(14); // testing with size 14
    System.out.println(board14.countSolutions());
    System.out.println("365596	\n"); // actual result to match with program's result
    QueenBoard  board15 = new QueenBoard(16); // testing with size 15
    System.out.println(board.countSolutions());
    System.out.println("2279184 \n"); // actual result to match with program's result
    */
  }

  // instance variables
  private int[][]board;

  // constructor
  public QueenBoard(int size){
    // if the size is negative
    if (size < 0){
      throw new IllegalArgumentException("The board size cannot be negative!");
    }
    board = new int[size][size];
    for (int x = 0; x < size; x++){
      for (int y = 0; y < size; y++){
        board[x][y] = 0;
      }
    }
  }

  // private methods (suggested)
  private boolean onBoard(int r, int c){
    if (board[r][c] != 0 || r >= board.length || c >= board.length){
      return false;
    }
    return true;
  }

  private boolean addQueen(int r, int c){
    // added an extra thingy here
    if (onBoard(r, c)){
      // horizontal
      for (int i = 0; i < board[r].length; i++){
        board[r][i]++;
      }

      // vertical
      for (int i = 0; i < board.length; i++){
        board[i][c]++;;
      }

      // diagonal

      // decrease first value
      int x = r - 1;
      int y = c + 1;
      while (y < board.length && x >= 0){
        board[x][y]++;
        x--;
        y++;
      }

      // decrease second value
      x = r + 1;
      y = c - 1;
      while (y >= 0 && x < board.length){
        board[x][y]++;
        x++;
        y--;
      }

      // decrease both values
      x = r - 1;
      y = c - 1;
      while (x >= 0 && y >= 0){
        board[x][y]++;
        x--;
        y--;
      }

      // increase both values
      x = r + 1;
      y = c + 1;
      while (x < board.length && y < board.length){
        board[x][y]++;
        x++;
        y++;
      }

      // making the space have a queen
      board[r][c] = -1;
      return true;
    }
    // else, don't add anything and return false
    return false;
  }

  private boolean removeQueen(int r, int c){
    if (board[r][c] == -1){
      // horizontal
      for (int i = 0; i < board[r].length; i++){
        board[r][i]--;
      }

      // vertical
      for (int i = 0; i < board.length; i++){
        board[i][c]--;;
      }

      // diagonal

      // decrease first value
      int x = r - 1;
      int y = c + 1;
      while (y < board.length && x >= 0 && board[x][y] - 1 >= 0){
        board[x][y]--;
        x--;
        y++;
      }

      // decrease second value
      x = r + 1;
      y = c - 1;
      while (y >= 0 && x < board.length && board[x][y] - 1 >= 0){
        board[x][y]--;
        x++;
        y--;
      }

      // decrease both values
      x = r - 1;
      y = c - 1;
      while (x >= 0 && y >= 0 && board[x][y] - 1 >= 0){
        board[x][y]--;
        x--;
        y--;
      }

      // increase both values
      x = r + 1;
      y = c + 1;
      while (x < board.length && y < board.length && board[x][y] - 1 >= 0){
        board[x][y]--;
        x++;
        y++;
      }

      // making the space not have a queen
      board[r][c] = 0;
      return true;

    }
    return false;
  }

  // public methods
  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _

  *_ _ _ Q

  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String output = "";
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board.length; y++){
        if (board[x][y] == -1){
          output = output + "Q";
        }
        else{
          output = output + board[x][y];
        }
      }
      output = output + "\n";
    }
    return output;
  }



  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;

  *        true when the board is solveable, and leaves the board in a solved state

  *@throws IllegalStateException when the board starts with any non-zero value

  */
  public boolean solve(){
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException();
        }
      }
    }
    return solveHelper(0);
  }

  public boolean solveHelper(int col){
    if (col == board.length){
      return true;
    }
    for (int row = 0; row < board.length; row++){
      if (addQueen(row, col)){
        if (solveHelper(col + 1)){
          return true;
        }
        removeQueen(row, col);
      }
    }
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    if (board.length == 0){
      return 0;
    }
    if (board[0][0] != 0){
      throw new IllegalStateException("Negative value!");
    }
    return countSolutionsHelper(0);
  }

  public int countSolutionsHelper(int col){
    int numSolutions = 0;
    if (col == board.length){
      return 1;
    }
    if (col < 0){
      throw new IllegalStateException("Negative value!");
    }
    for (int row = 0; row < board.length; row++){
      if (addQueen(row, col)){
        numSolutions = numSolutions + countSolutionsHelper(col + 1);
        removeQueen(row, col);
      }
    }
    return numSolutions;
  }

  // FROM MR. K
  //testcase must be a valid index of your input/output array
  public static void runTest(int i){
    QueenBoard b;
    int[]tests =   {1,2,3,4,5,8};
    int[]answers = {1,0,0,2,10,92};
    if(i >= 0 && i < tests.length ){
      int size = tests[i];
      int correct = answers[i];
      b = new QueenBoard(size);
      int ans  = b.countSolutions();

      if(correct==ans){
        System.out.println("PASS board size: "+tests[i]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
      }
    }
  }
}
