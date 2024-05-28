
/**
 * Write a description of class NQueen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NQueen
{
    //private static int s = 0;
    static int N = 8;  
    static int k = 1; 
    
    public NQueen()
    {
        
    }
    
    public NQueen(int n)
    {
        N = n;
    }
    
    static void print(int board[][])  
    {  
        System.out.printf("%d-\n(", k++);  
        for (int i = 0; i < N; i++)  
        {  
            for (int j = 0; j < N; j++)  {
                if(board[i][j] == 1){
                    System.out.printf(j+1+"");
                    if(i!=N-1){
                        System.out.printf(",");
                    }
                }
            }
        }  
        System.out.printf(")\n");  
    }  
    
    static void printByCol(int board[][])  
    {  
        System.out.printf("%d-\n(", k++);  
        for (int j = 0; j < N; j++)  
        {  
            for (int i = 0; i < N; i++)  {
                if(board[i][j] == 1){
                    System.out.printf(i+1+"");
                    if(j!=N-1){
                        System.out.printf(",");
                    }
                }
            }
        }  
        System.out.printf(")\n");  
    }  
      
    /* A utility function to print solution */
    static void printSolution(int board[][])  
    {  
        print(board);
    }  
      
    /* A utility function to check if a queen can  
    be placed on board[row][col]. Note that this  
    function is called when "col" queens are  
    already placed in columns from 0 to col -1.  
    So we need to check only left side for  
    attacking queens */
    static boolean isSafe(int board[][], int row, int col)  
    {  
        int i, j;  
      
        /* Check this row on left side */
        for (i = 0; i < col; i++)  
            if (board[row][i] == 1)  
                return false;  
      
        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)  
            if (board[i][j] == 1)  
                return false;  
      
        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)  
            if (board[i][j] == 1)  
                return false;  
      
        return true;  
    }  
    
    // nang cap isSafe
    /* ld is an array where its indices indicate row-col+N-1 
    (N-1) is for shifting the difference to store negative  
    indices */
    //static int []ld = new int[30]; 
      
    /* rd is an array where its indices indicate row+col 
    and used to check whether a queen can be placed on  
    right diagonal or not*/
    //static int []rd = new int[30]; 
      
    /*column array where its indices indicates column and  
    used to check whether a queen can be placed in that 
        row or not*/
    //static int []cl = new int[30];

      
    /* A recursive utility function   
    to solve N Queen problem */
    static boolean solveNQUtil(int board[][], int col)  
    {  
        /* base case: If all queens are placed  
        then return true */
        if (col == N)  
        {  
            printSolution(board);
            return true;  
        }  
      
        /* Consider this column and try placing  
        this queen in all rows one by one */
        boolean res = false;  
        for (int i = 0; i < N; i++)  
        {  
            /* Check if queen can be placed on  
            board[i][col] */
            if ( isSafe(board, i, col) )  
            {  
                /* Place this queen in board[i][col] */
                board[i][col] = 1;  
      
                // Make result true if any placement  
                // is possible  
                res = solveNQUtil(board,col+1) || res;  
            
           // Tinh menh de res o day xem co loi giai khong de quay lui ..... 
             
                /* If placing queen in board[i][col]  
                doesn't lead to a solution, then  
                remove queen from board[i][col] */
                board[i][col] = 0; // BACKTRACK  
            }  
        }  
      
        /* If queen can not be place in any row in  
            this column col then return false */
        return res;  
    }  
      
    /* This function solves the N Queen problem using  
    Backtracking. It mainly uses solveNQUtil() to  
    solve the problem. It returns false if queens  
    cannot be placed, otherwise return true and  
    prints placement of queens in the form of 1s.  
    Please note that there may be more than one  
    solutions, this function prints one of the  
    feasible solutions.*/
    static void inKetQua()  
    {   
        System.out.println("Ban co co "+N+" x "+N);
        int board[][] = new int[N][N];  
      
        if (solveNQUtil(board, 0) == false)  
        {  
            System.out.printf("Solution does not exist");  
            return ;  
        }   
    }  
    
    
    static void inSoLuongKQ()  
    {  
        System.out.println("So luong ket qua la:"+(k-1));
    }  
      
    // Driver code  
    public static void main(String[] args) 
    {   
        NQueen nqueen = null;
        try{
            N = Integer.parseInt(args[0]);
            nqueen = new NQueen(N);
            nqueen.inKetQua();
            nqueen.inSoLuongKQ();
        }catch(Exception ex){
            nqueen = new NQueen();
            nqueen.inKetQua();
            nqueen.inSoLuongKQ();
        }
    } 

}
