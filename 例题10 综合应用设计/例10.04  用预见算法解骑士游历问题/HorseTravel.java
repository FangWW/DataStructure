//【例10.4】  用预见算法解骑士游历问题。

public class HorseTravel
{
    private int chessboard[][];                  //二维数组表示棋盘
    private boolean show;                        //是否显示中间结果

    public HorseTravel(int n, int x, int y, boolean show) throws Exception
    {                                            //控制棋盘大小为5～8
        if (n<5)
            throw new Exception("棋盘太小");       
        if (n>8)
            throw new Exception("棋盘太大");       
        chessboard = new int[n][n];
        this.show = show;
        start(x, y);
    }
    public HorseTravel(int n, int x, int y) throws Exception
    {
        this(n, x, y, false);
    }
    public HorseTravel(int n) throws Exception
    {
        this(n, 0, 0, false);
    }
    public HorseTravel() throws Exception
    {
        this(8, 0, 0, false);
    }

    private class Position                       //棋盘一格坐标，内部类
    {
        int x,y;                                 //行、列坐标
        Position(int x, int y)                   //内部类的构造方法
        {
            if (x>=0 && x<chessboard.length && y>=0 && y<chessboard.length)
            {
                this.x=x;
                this.y=y;
            }
            else                                 //若(x,y)超出棋盘，则抛出异常
                throw new IndexOutOfBoundsException("chessboard");
        }
        Position()
        {
            this(0, 0);
        }
        Position(Position p)
        {
            this(p.x, p.y);
        }
        public String toString()
        {
            return "("+this.x+","+this.y+")";
        }        
    }//Position内部类结束

    private void start(int x, int y)             //从(x,y)格开始游历
    {
        Position current = new Position(x, y);   //当前位置
        int count=1;                             //统计第几步，count记录走过的格数
        int direction=1;                         //diretion表示8个方向
        int n=chessboard.length;                 //棋盘大小
        while (count<=n*n && direction!=0)
        {
           chessboard[current.x][current.y]=count;    //设置current格的值为count
           if (this.show) 
               System.out.print("第"+count+"步  ");
           direction = select(current);               //试探，选择一个方向
           if (direction==0 && count<n*n)
               System.out.println("第"+count+"步无路可通!");
           else
           {
               count++;                               //步数加1
               current = goaStep(current, direction); //向前走一步 
           }
        }
        if (!this.show)
            this.print();
    }

    private int select(Position p)               //为p位置选择应走的方向
    {
        if (this.show)
        {
            System.out.println("当前位置: "+p.toString());
            this.print();                        //输出棋盘所有元素
            System.out.println("方向   下一位置   可通方向     可通路数");
        }
        int direction=0, minroad=8;
        for (int i=1; i<=8; i++)                 //试探位置p(x,y)的8个方向的位置next1
        {
            int road=0;
            Position next1=goaStep(p,i);
            if (next1!=null)                     //next1在棋盘内且未被访问过
            {
                 if (this.show)
                     System.out.print("  "+i+"\t"+next1.toString()+"\t");
                 for (int j=1; j<=8; j++)        //统计next1(x,y)的可通路数road
                 {
                     Position next2=goaStep(next1,j); //next2是next1按j方向的下一位置
                     if (next2!=null)            //next2在棋盘内且未被访问过
                     {
                         road++;
                         if(this.show)
                             System.out.print(j+",");
                     }
                 }    
                 if (road<minroad)
                 {
                     minroad=road;               //minroad记载road的最小值
                     direction=i;                //direction记载road最小值的方向
                 }
                 if (this.show)
                     System.out.println("\t"+road);
             }
        }
        if (this.show)
            System.out.println("选定下一步方向 direction="+direction+"\r\n");
        return direction;
    }

    private Position goaStep(Position p, int direction)//返回p位置按direction方向的下一位置
    {
        int x=p.x, y=p.y;
        switch (direction)
        {
            case 1: x=x-2; y=y+1; break; 
            case 2: x=x-1; y=y+2; break; 
            case 3: x=x+1; y=y+2; break; 
            case 4: x=x+2; y=y+1; break; 
            case 5: x=x+2; y=y-1; break; 
            case 6: x=x+1; y=y-2; break; 
            case 7: x=x-1; y=y-2; break; 
            case 8: x=x-2; y=y-1; break; 
        }
        int n=chessboard.length;
        if (x>=0 && x<n && y>=0 && y<n && chessboard[x][y]==0)   //若(x,y)在棋盘内且未被访问过
            return new Position(x,y);
        else
            return null; 
    } 

    private void print()                         //输出棋盘所有元素
    {
        for (int i=0; i<this.chessboard.length; i++)
        {
            for (int j=0; j<this.chessboard.length; j++)
            {
                if(chessboard[i][j]>=0 && chessboard[i][j]<10)
                    System.out.print(" ");
                System.out.print("  "+chessboard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    } 
    
    public static void main(String args[]) throws Exception
    {
//        new HorseTravel();
//        new HorseTravel(5,0,1);                  //不通
//        new HorseTravel(8,4,3,true);             //显示中间结果
        new HorseTravel(8,0,0,true);             //显示中间结果
    }
}

/*
        new HorseTravel(8,0,0);
程序运行结果如下：
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25  62  21  48  55  58
  24  35  30  45  60  63  20   5
  29  14  61  34  49  44  59  54
  36  31  38  41  64  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

 */
 
 
/*
        new HorseTravel(5,1,2);                  //不通
程序运行结果如下：
第24步无路可通!
  18   1  14   7  16
  11   6  17   2  13
  22  19  12  15   8
   5  10  23  20   3
   0  21   4   9  24

 */ 
 	
/*
        new HorseTravel(8,4,3,true);
程序运行结果如下：
第1步  当前位置: (4,3)
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   1   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  1 (2,4)   1,2,3,4,6,7,8,  7
  2 (3,5)   1,2,3,4,5,7,8,  7
  3 (5,5)   1,2,3,4,5,6,8,  7
  4 (6,4)   1,2,3,6,7,  5
  5 (6,2)   2,3,6,7,8,  5
  6 (5,1)   1,3,4,5,8,  5
  7 (3,1)   1,2,4,5,8,  5
  8 (2,2)   1,2,3,5,6,7,8,  7
选定下一步方向 direction=4

……
第64步  当前位置: (4,2)
  12  25  14  31  10  27  36  61
  15  32  11  26  35  62   9  28
  24  13  34  63  30  47  60  37
  33  16  23  56  59  38  29   8
  22  55  64   1  46  57  48  39
  17  52  19  58  49  42   7   4
  54  21  50  45   2   5  40  43
  51  18  53  20  41  44   3   6

方向   下一位置   可通方向     可通路数
选定下一步方向 direction=0

 */ 
 	
 
/*
程序运行结果如下：
第1步  当前位置: (0,0)
   1   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  3 (1,2)   2,3,4,5,6,  5
  4 (2,1)   1,2,3,4,5,  5
选定下一步方向 direction=3

第2步  当前位置: (1,2)
   1   0   0   0   0   0   0   0
   0   0   2   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  2 (0,4)   3,4,5,  3
  3 (2,4)   1,2,3,4,5,6,8,  7
  4 (3,3)   1,2,3,4,5,6,7,  7
  5 (3,1)   2,3,4,5,8,  5
  6 (2,0)   1,3,4,  3
选定下一步方向 direction=2

第3步  当前位置: (0,4)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  3 (1,6)   4,5,6,  3
  4 (2,5)   1,2,3,4,5,6,7,  7
  5 (2,3)   2,3,4,5,6,7,8,  7
选定下一步方向 direction=3

第4步  当前位置: (1,6)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  4 (3,7)   5,6,7,  3
  5 (3,5)   2,3,4,5,6,7,8,  7
  6 (2,4)   1,3,4,5,6,8,    6
选定下一步方向 direction=4

第5步  当前位置: (3,7)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  5 (5,6)   4,5,6,7,8,  5
  6 (4,5)   1,3,4,5,6,7,8,  7
  7 (2,5)   1,2,4,5,6,7,    6
选定下一步方向 direction=5

第6步  当前位置: (5,6)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   0

方向   下一位置   可通方向     可通路数
  4 (7,7)   7,  1
  5 (7,5)   2,7,8,  3
  6 (6,4)   1,3,6,7,8,  5
  7 (4,4)   1,2,4,5,6,7,8,  7
  8 (3,5)   2,3,5,6,7,8,    6
选定下一步方向 direction=4

第7步  当前位置: (7,7)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   7

方向   下一位置   可通方向     可通路数
  7 (6,5)   1,2,6,7,8,  5
选定下一步方向 direction=7

第8步  当前位置: (6,5)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   0
   0   0   0   0   0   8   0   0
   0   0   0   0   0   0   0   7

方向   下一位置   可通方向     可通路数
  1 (4,6)   1,4,6,7,8,  5
  2 (5,7)   5,7,8,  3
  6 (7,3)   1,7,8,  3
  7 (5,3)   1,2,4,5,6,7,8,  7
  8 (4,4)   1,2,5,6,7,8,    6
选定下一步方向 direction=2

第9步  当前位置: (5,7)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
   0   0   0   0   0   8   0   0
   0   0   0   0   0   0   0   7

方向   下一位置   可通方向     可通路数
  5 (7,6)   7,8,    2
  7 (4,5)   1,4,5,6,7,8,    6
  8 (3,6)   1,5,6,7,8,  5
选定下一步方向 direction=5

第10步  当前位置: (7,6)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
   0   0   0   0   0   8   0   0
   0   0   0   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  7 (6,4)   1,6,7,8,    4
  8 (5,5)   1,2,3,5,6,7,8,  7
选定下一步方向 direction=7

第11步  当前位置: (6,4)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5

   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
   0   0   0   0  11   8   0   0
   0   0   0   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (4,5)   1,4,6,7,8,  5
  6 (7,2)   1,7,8,  3
  7 (5,2)   1,2,4,5,6,7,8,  7
  8 (4,3)   1,2,3,5,6,7,8,  7
选定下一步方向 direction=6

第12步  当前位置: (7,2)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
   0   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (5,3)   1,2,4,6,7,8,    6
  7 (6,0)   1,2,    2
  8 (5,1)   1,2,3,5,8,  5
选定下一步方向 direction=7

第13步  当前位置: (6,0)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (4,1)   1,2,3,4,8,  5
  2 (5,2)   1,2,4,5,7,8,    6
选定下一步方向 direction=1

第14步  当前位置: (4,1)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
   0   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (2,2)   1,2,3,4,6,7,8,  7
  2 (3,3)   1,2,3,4,5,7,    6
  3 (5,3)   1,2,4,6,8,  5
  4 (6,2)   1,2,3,6,7,  5
  8 (2,0)   1,3,    2
选定下一步方向 direction=8

第15步  当前位置: (2,0)
   1   0   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
  15   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (0,1)   3,4,    2
  3 (3,2)   1,2,3,4,5,6,8,  7
选定下一步方向 direction=1

第16步  当前位置: (0,1)
   1  16   0   0   3   0   0   0
   0   0   2   0   0   0   4   0
  15   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (1,3)   2,3,4,5,6,  5
  4 (2,2)   1,2,3,4,6,7,    6
选定下一步方向 direction=3

第17步  当前位置: (1,3)
   1  16   0   0   3   0   0   0
   0   0   2  17   0   0   4   0
  15   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (0,5)   3,4,5,  3
  3 (2,5)   1,2,4,5,6,  5
  4 (3,4)   1,2,3,4,5,6,7,  7
  5 (3,2)   2,3,4,5,6,8,    6
  6 (2,1)   1,3,4,5,    4
选定下一步方向 direction=2

第18步  当前位置: (0,5)
   1  16   0   0   3  18   0   0
   0   0   2  17   0   0   4   0
  15   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (1,7)   5,6,    2
  4 (2,6)   1,4,5,6,7,  5
  5 (2,4)   3,4,5,6,8,  5
选定下一步方向 direction=3

第19步  当前位置: (1,7)
   1  16   0   0   3  18   0   0
   0   0   2  17   0   0   4  19
  15   0   0   0   0   0   0   0
   0   0   0   0   0   0   0   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  5 (3,6)   5,6,7,8,    4
  6 (2,5)   1,4,5,6,    4
选定下一步方向 direction=5

第20步  当前位置: (3,6)
   1  16   0   0   3  18   0   0
   0   0   2  17   0   0   4  19
  15   0   0   0   0   0   0   0
   0   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  5 (5,5)   2,3,5,6,7,8,    6
  6 (4,4)   1,5,6,7,8,  5
  7 (2,4)   4,5,6,8,    4
  8 (1,5)   2,3,5,6,7,  5
选定下一步方向 direction=7

第21步  当前位置: (2,4)
   1  16   0   0   3  18   0   0
   0   0   2  17   0   0   4  19
  15   0   0   0  21   0   0   0
   0   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  4 (4,5)   1,4,6,7,    4
  5 (4,3)   2,3,5,6,7,8,    6
  6 (3,2)   3,4,5,6,8,  5
  8 (0,3)   3,5,6,  3
选定下一步方向 direction=8

第22步  当前位置: (0,3)
   1  16   0  22   3  18   0   0
   0   0   2  17   0   0   4  19
  15   0   0   0  21   0   0   0
   0   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (1,5)   2,3,5,6,    4
  5 (2,2)   2,3,4,6,7,  5
  6 (1,1)   3,4,5,  3
选定下一步方向 direction=6

第23步  当前位置: (1,1)
   1  16   0  22   3  18   0   0
   0  23   2  17   0   0   4  19
  15   0   0   0  21   0   0   0
   0   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (2,3)   2,3,4,5,6,8,    6
  4 (3,2)   3,4,5,6,    4
  5 (3,0)   2,3,4,  3
选定下一步方向 direction=5

第24步  当前位置: (3,0)
   1  16   0  22   3  18   0   0
   0  23   2  17   0   0   4  19
  15   0   0   0  21   0   0   0
  24   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (2,2)   2,3,4,7,    4
  3 (4,2)   1,2,3,4,5,6,8,  7
  4 (5,1)   1,2,3,5,    4
选定下一步方向 direction=2

第25步  当前位置: (2,2)
   1  16   0  22   3  18   0   0
   0  23   2  17   0   0   4  19
  15   0  25   0  21   0   0   0
  24   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (1,4)   2,3,4,5,7,  5
  3 (3,4)   1,2,3,4,5,6,    6
  4 (4,3)   2,3,5,6,7,  5
  7 (1,0)   2,4,    2
选定下一步方向 direction=7

第26步  当前位置: (1,0)
   1  16   0  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15   0  25   0  21   0   0   0
  24   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (0,2)   3,4,5,  3
  4 (3,1)   2,3,4,5,    4
选定下一步方向 direction=2

第27步  当前位置: (0,2)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15   0  25   0  21   0   0   0
  24   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (1,4)   2,3,4,5,    4
  4 (2,3)   2,3,4,5,6,  5
  5 (2,1)   3,4,5,  3
选定下一步方向 direction=5

第28步  当前位置: (2,1)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0   0   0   0   0  20   5
   0  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (3,3)   1,2,3,4,5,  5
  4 (4,2)   1,2,3,4,5,6,    6
  5 (4,0)   2,3,4,  3
选定下一步方向 direction=5

第29步  当前位置: (4,0)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0   0   0   0   0  20   5
  29  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (3,2)   3,4,5,  3
  3 (5,2)   1,2,4,5,8,  5

  4 (6,1)   1,2,3,  3
选定下一步方向 direction=2

第30步  当前位置: (3,2)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0  30   0   0   0  20   5
  29  14   0   0   0   0   0   0
   0   0   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  3 (4,4)   1,5,6,8,    4
  4 (5,3)   1,2,4,6,    4
  5 (5,1)   2,3,5,  3
选定下一步方向 direction=5

第31步  当前位置: (5,1)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0  30   0   0   0  20   5
  29  14   0   0   0   0   0   0
   0  31   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
   0   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (4,3)   2,3,5,7,    4
  3 (6,3)   1,2,3,6,8,  5
  5 (7,0)   2,  1
选定下一步方向 direction=5

第32步  当前位置: (7,0)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0  30   0   0   0  20   5
  29  14   0   0   0   0   0   0
   0  31   0   0   0   0   6   9
  13   0   0   0  11   8   0   0
  32   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (6,2)   1,2,3,7,    4
选定下一步方向 direction=2

第33步  当前位置: (6,2)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0  30   0   0   0  20   5
  29  14   0   0   0   0   0   0
   0  31   0   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (4,3)   2,3,7,  3
  2 (5,4)   1,2,3,4,5,7,8,  7
  3 (7,4)   1,2,8,  3
  7 (5,0)   1,2,4,  3
选定下一步方向 direction=1

第34步  当前位置: (4,3)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24   0  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
   0  31   0   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (3,5)   2,3,5,7,8,  5
  3 (5,5)   2,3,5,6,8,  5
  7 (3,1)   2,4,5,  3
选定下一步方向 direction=7

第35步  当前位置: (3,1)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
   0  31   0   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (2,3)   2,3,4,5,    4
  4 (5,2)   1,2,4,5,    4
  5 (5,0)   2,4,    2
选定下一步方向 direction=5

第36步  当前位置: (5,0)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31   0   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32   0  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  2 (4,2)   1,2,3,4,5,  5
  4 (7,1)   1,2,    2
选定下一步方向 direction=4

第37步  当前位置: (7,1)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31   0   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32  37  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (5,2)   1,2,4,  3
  2 (6,3)   1,2,3,8,    4
选定下一步方向 direction=1

第38步  当前位置: (5,2)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31  38   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32  37  12   0   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (3,3)   1,2,3,4,    4
  2 (4,4)   1,5,8,  3
  4 (7,3)   1,7,    2
选定下一步方向 direction=4

第39步  当前位置: (7,3)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31  38   0   0   0   6   9
  13   0  33   0  11   8   0   0
  32  37  12  39   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (5,4)   1,2,3,4,7,8,    6
  7 (6,1)   1,2,    2
选定下一步方向 direction=7

第40步  当前位置: (6,1)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31  38   0   0   0   6   9
  13  40  33   0  11   8   0   0
  32  37  12  39   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (4,2)   1,2,3,4,    4
  2 (5,3)   1,2,4,  3
选定下一步方向 direction=2

第41步  当前位置: (5,3)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8   0   0
  32  37  12  39   0   0  10   7

方向   下一位置   可通方向     可通路数
  1 (3,4)   1,2,3,4,6,  5
  2 (4,5)   1,4,7,  3
  4 (7,4)   1,2,    2
选定下一步方向 direction=4

第42步  当前位置: (7,4)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8   0   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  1 (5,5)   2,3,6,8,    4
  2 (6,6)   1,7,8,  3
选定下一步方向 direction=2

第43步  当前位置: (6,6)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0   0   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  1 (4,7)   6,7,8,  3
  7 (5,4)   1,2,4,7,8,  5
  8 (4,5)   1,7,    2
选定下一步方向 direction=8

第44步  当前位置: (4,5)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30   0   0   0  20   5
  29  14   0  34   0  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  1 (2,6)   1,4,6,7,    4
  7 (3,3)   1,2,4,  3
选定下一步方向 direction=7

第45步  当前位置: (3,3)
   1  16  27  22   3  18   0   0
  26  23   2  17   0   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34   0  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  1 (1,4)   2,3,4,  3
  2 (2,5)   1,4,5,  3
  4 (5,4)   1,2,4,7,    4
选定下一步方向 direction=1

第46步  当前位置: (1,4)
   1  16  27  22   3  18   0   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34   0  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  2 (0,6)   4,5,    2
  3 (2,6)   1,4,6,  3
  4 (3,5)   2,3,5,7,    4
选定下一步方向 direction=2

第47步  当前位置: (0,6)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21   0   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34   0  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  4 (2,7)   5,6,7,  3
  5 (2,5)   4,5,    2
选定下一步方向 direction=5

第48步  当前位置: (2,5)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34   0  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  4 (4,6)   1,4,6,7,    4
  5 (4,4)   5,8,    2
选定下一步方向 direction=5

第49步  当前位置: (4,4)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33   0  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  5 (6,3)   2,3,8,  3
  8 (2,3)   2,3,5,  3
选定下一步方向 direction=5

第50步  当前位置: (6,3)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33  50  11   8  43   0
  32  37  12  39  42   0  10   7

方向   下一位置   可通方向     可通路数
  2 (5,5)   2,3,8,  3
  3 (7,5)   2,8,    2
  8 (4,2)   1,2,3,  3
选定下一步方向 direction=3

第51步  当前位置: (7,5)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33  50  11   8  43   0
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  2 (6,7)   7,8,    2
  8 (5,4)   1,2,7,  3
选定下一步方向 direction=2

第52步  当前位置: (6,7)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0   0
  36  31  38  41   0   0   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  7 (5,5)   2,8,    2
  8 (4,6)   1,6,7,  3
选定下一步方向 direction=7

第53步  当前位置: (5,5)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0   0
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  2 (4,7)   7,8,    2
  8 (3,4)   1,2,3,6,    4
选定下一步方向 direction=2

第54步  当前位置: (4,7)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48   0   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  7 (3,5)   2,5,7,  3
  8 (2,6)   1,6,    2
选定下一步方向 direction=8

第55步  当前位置: (2,6)
   1  16  27  22   3  18  47   0
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48  55   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  1 (0,7)   6,  1
  6 (3,4)   1,3,6,  3
选定下一步方向 direction=1

第56步  当前位置: (0,7)
   1  16  27  22   3  18  47  56
  26  23   2  17  46   0   4  19
  15  28  25   0  21  48  55   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  6 (1,5)   3,5,6,  3
选定下一步方向 direction=6

第57步  当前位置: (1,5)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25   0  21  48  55   0
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  3 (2,7)   5,6,    2
  5 (3,4)   3,6,    2
  6 (2,3)   3,5,    2
选定下一步方向 direction=3

第58步  当前位置: (2,7)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25   0  21  48  55  58
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44   0  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  5 (4,6)   6,7,    2
  6 (3,5)   5,7,    2
选定下一步方向 direction=5

第59步  当前位置: (4,6)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25   0  21  48  55  58
  24  35  30  45   0   0  20   5
  29  14   0  34  49  44  59  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  6 (5,4)   1,7,    2
  7 (3,4)   6,  1
选定下一步方向 direction=7

第60步  当前位置: (3,4)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25   0  21  48  55  58
  24  35  30  45  60   0  20   5
  29  14   0  34  49  44  59  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7


方向   下一位置   可通方向     可通路数
  6 (4,2)   1,3,    2
选定下一步方向 direction=6

第61步  当前位置: (4,2)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25   0  21  48  55  58
  24  35  30  45  60   0  20   5
  29  14  61  34  49  44  59  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  1 (2,3)   3,  1
  3 (5,4)   1,  1
选定下一步方向 direction=1

第62步  当前位置: (2,3)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25  62  21  48  55  58
  24  35  30  45  60   0  20   5
  29  14  61  34  49  44  59  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  3 (3,5)   5,  1
选定下一步方向 direction=3

第63步  当前位置: (3,5)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25  62  21  48  55  58
  24  35  30  45  60  63  20   5
  29  14  61  34  49  44  59  54
  36  31  38  41   0  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
  5 (5,4)       0
选定下一步方向 direction=5

第64步  当前位置: (5,4)
   1  16  27  22   3  18  47  56
  26  23   2  17  46  57   4  19
  15  28  25  62  21  48  55  58
  24  35  30  45  60  63  20   5
  29  14  61  34  49  44  59  54
  36  31  38  41  64  53   6   9
  13  40  33  50  11   8  43  52
  32  37  12  39  42  51  10   7

方向   下一位置   可通方向     可通路数
选定下一步方向 direction=0

 */  
 	 		