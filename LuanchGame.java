import java.util.Random;
import java.util.Scanner;

class TicTacToe{
    static char [][] Board;
     public TicTacToe(){
         Board= new char[3][3];
         initBoard();
     }
     void initBoard() {
         for (int i = 0; i < Board.length; i++) {
             for (int j=0;j<Board[i].length;j++){
                Board[i][j]=' ';
             }
         }
     }
     static void disBoard(){
         System.out.println("----------");
         for (int i = 0; i < Board.length; i++) {
             System.out.print("|");
             for (int j=0;j<Board[i].length;j++){
                 System.out.print(Board[i][j]+" |");
             }
             System.out.println();
             System.out.println("----------");
         }

     }


     static void placeMark(int row,int col,char mark){
         if(row>=0 && row<=2 && col>=0 &&col<=2) {
             Board[row][col] = mark;
         }
         else{
             System.out.println("Invalid Position");
         }
     }
    static boolean checkColWin(){
         for(int j=0; j<=2;j++) {
            if(Board[0][j]!=' '&& Board[0][j]==Board[1][j] && Board[1][j]==Board[2][j] ){
              return true;
         }
         }
         return false;
     }
     static boolean checkRowWin(){
         for( int i=0;i<=2;i++){
             if(Board[i][0]!=' ' && Board[i][0]==Board[i][1] && Board[i][1]==Board[i][2] ){
                 return true;
             }
         }
         return false;
     }

     static boolean checkDigWin(){
         if(Board[0][0]!=' ' && Board[0][0]==Board[1][1] && Board[1][1]==Board[2][2] || Board[0][2]!=' ' && Board[0][2]==Board[1][1] && Board[1][1]==Board[2][0]){
             return true;
         }
          return false;
     }

     static boolean isDraw(){
         for (int i = 0; i <= 2; i++) {
             for (int j = 0; j <=2 ; j++) {
                 if(Board[i][j]==' '){
                     return false;
                 };
             }
         }
         return true;
     }
}

abstract class player{
    String Name;
    char Mark;
    abstract void makeMove();

    boolean isValidMove(int row,int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(TicTacToe.Board[row][col]==' '){
                return true;
            }
        }
        return false;
    }


}
class HumanPlayer extends player{
    HumanPlayer( String Name,char Mark){
        this.Name=Name;
        this.Mark=Mark;

    }
    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter the Row and Column");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row,col,Mark);
    }

}

class AIPlayer extends player{

    AIPlayer( String Name,char Mark){
        this.Name=Name;
        this.Mark=Mark;
    }
    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
          Random r= new Random();
          row=r.nextInt(3);
          col=r.nextInt(3);
        } while (!isValidMove(row, col));
        TicTacToe.placeMark(row,col,Mark);
    }

}




public class LuanchGame {
    public static void main(String[] args) {
        TicTacToe t=new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("Bob", 'X');
        AIPlayer p2 = new AIPlayer("AI", 'O');
        player cp;
        cp=p1;
        while (true){
            System.out.println(cp.Name+" turn");
            cp.makeMove();
            TicTacToe.disBoard();
            if(TicTacToe.checkColWin()||TicTacToe.checkRowWin()||TicTacToe.checkDigWin()){
                System.out.println(cp.Name+" has Won");
                break;
            } else if (TicTacToe.isDraw()) {
                System.out.println("Game is Draw");
                break;
            } else {
                if(cp==p1){
                    cp=p2;
                }
                else {
                    cp=p1;
                }
            }
        }

    }
}
