public class QueenBoard{
  // TESTING
  public static void main(String[] args){
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
    if (board[r][c] == -1 || r < board.length || c < board.length){
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
      while (y < board.length && x >= 0){
        board[x][y]--;
        x--;
        y++;
      }

      // decrease second value
      x = r + 1;
      y = c - 1;
      while (y >= 0 && x < board.length){
        board[x][y]--;
        x++;
        y--;
      }

      // decrease both values
      x = r - 1;
      y = c - 1;
      while (x >= 0 && y >= 0){
        board[x][y]--;
        x--;
        y--;
      }

      // increase both values
      x = r + 1;
      y = c + 1;
      while (x < board.length && y < board.length){
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
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; r++){
        if (board[r][c] != 0){
          throw new IllegalStateException();
        }
      }
    }
    return countSolutionsHelper(0);
  }

  public int countSolutionsHelper(int col){
    int numSolutions = 0;
    if (col == board.length){
      return 1;
    }
    for (int row = 0; row < board.length; row++){
      if (addQueen(row, col)){
        numSolutions = numSolutions + countSolutionsHelper(col + 1);
        removeQueen(row, col);
      }
    }
    return numSolutions;
  }
}
