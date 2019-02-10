public class QueenBoard{

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
  private boolean addQueen(int r, int c){
    int big = board.length;
    // if it is an empty, nonthreatening space, place a queen there and return true
    if (board[r][c] == 0){
      // making the space have a queen
      board[r][c] = -1;

      // changing values across the board
      for (int across = r; across < big && across > 0; across++){
        board[across][c] = board[across][c] + 1;
      }
      for (int across = r; across < big && across > 0; across--){
        board[across][c] = board[across][c] + 1;
      }

      // changing values down the board
      for (int down = c; down < big && down > 0; down++){
        board[r][down] = board[r][down] + 1;
      }
      for (int down = c; down < big && down > 0; down--){
        board[r][down] = board[r][down] + 1;
      }

      // changing values diagonal the board
      for (int across = r; across < big && across > 0; across++){
        for (int down = c; down < big && down > 0; down--){
          board[across][down] = board[across][down] + 1;
        }
      }
      for (int across = r; across < big && across > 0; across--){
        for (int down = c; down < big && down > 0; down++){
          board[across][down] = board[across][down] + 1;
        }
      }

      return true;
    }
    // else, don't add anything and return false
    return false;
  }
  private boolean removeQueen(int r, int c){
    int big = board.length;
    // if there is a queen in the specified location, remove the queen there and return true
    if (board[r][c] == -1){
      board[r][c] = 0;

      // changing values across the board
      for (int across = r; across < big && across > 0; across++){
        if (board[across][c] > 0){
          board[across][c] = board[across][c] - 1;
        }
      }
      for (int across = r; across < big && across > 0; across--){
        if (board[across][c] > 0){
          board[across][c] = board[across][c] - 1;
        }
      }

      // changing values down the board
      for (int down = c; down < big && down > 0; down++){
        if (board[r][down] > 0){
          board[r][down] = board[r][down] - 1;
        }
      }
      for (int down = c; down < big && down > 0; down--){
        if (board[r][down] > 0){
          board[r][down] = board[r][down] - 1;
        }
      }

      // changing values diagonal the board
      for (int across = r; across < big && across > 0; across++){
        for (int down = c; down < big && down > 0; down--){
          if (board[across][down] > 0){
            board[across][down] = board[across][down] - 1;
          }
        }
      }
      for (int across = r; across < big && across > 0; across--){
        for (int down = c; down < big && down > 0; down++){
          if (board[across][down] > 0){
            board[across][down] = board[across][down] - 1;
          }
        }
      }

      return true;
    }
    // else, don't remove anything and return false
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
      String line = "";
      for (int y = 0; y < board[x].length; y++){
        if (board[x][y] == -1){
          line = line + "Q";
        }
        else{
          line = line + "_";
        }
      }
      output = line + "\n";
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
      for (int c = 0; c < board.length; c++){
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
  public int countSolutions(){}
}
