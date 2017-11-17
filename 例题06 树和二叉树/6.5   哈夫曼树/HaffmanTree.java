//���������������ù���������

class HaffmanNode                                //���������Ľ����
{
    int weight;                                  //Ȩֵ
    int parent,left,right;                       //��ĸ�������Һ����±�
    
    public HaffmanNode(int weight)
    {                    
        this.weight = weight;
        this.parent=-1;
        this.left=-1;
        this.right=-1;
    }
    public HaffmanNode()
    {                    
        this(0);
    }
    public String toString()
    {
        return this.weight+", "+this.parent+", "+this.left+", "+this.right;
    }
}

public class HaffmanTree                         //��������
{
    private int leafNum;                         //Ҷ�ӽ�����
    private HaffmanNode[] hnodes;                //���������Ľ������
        
    public HaffmanTree(int[] weight)             //����ָ��Ȩֵ���ϵĹ�������
    {
        int n = weight.length;                   //n��Ҷ�ӽ��
        this.leafNum = n;
        this.hnodes = new HaffmanNode[2*n-1];    //n��Ҷ�ӽ��Ĺ�����������2n-1�����
        for(int i=0; i<n; i++)                   //��������ʼ����n��Ҷ�ӽ��
            this.hnodes[i] = new HaffmanNode(weight[i]);


        for(int i=0; i<n-1; i++)                 //����n-1��2�Ƚ�㣬ÿѭ��һ�Σ�����һ��2�Ƚ��
        {
            int min1, min2, x1, x2;
            min1 = min2 = Integer.MAX_VALUE;     //ѡ����С�ʹ���СȨֵ����ֵΪ���Ȩֵ
            x1 = x2 = -1;                        //��¼�����޸�ĸ����СȨֵ����±�
            for(int j=0; j<n+i; j++)             //���������޸�ĸ����СȨֵ���
            {
                if(hnodes[j].weight<min1 && hnodes[j].parent==-1)
                {
                    min2 = min1;
                    x2 = x1;
                    min1 = hnodes[j].weight;     //min1������СȨֵ
                    x1 = j;                      //x1������СȨֵ�����±�
                }
                else if(hnodes[j].weight<min2 && hnodes[j].parent==-1)
                {
                    min2 = hnodes[j].weight;
                    x2 = j;                      //x2���´���СȨֵ�����±�
                }
            }

            hnodes[x1].parent  = n+i;            //���ҳ�������Ȩֵ��С�������ϲ�Ϊһ������
            hnodes[x2].parent  = n+i;
            this.hnodes[n+i] = new HaffmanNode();
            hnodes[n+i].weight = hnodes[x1].weight+hnodes[x2].weight;
            hnodes[n+i].left = x1;
            hnodes[n+i].right = x2;
        }
    }

    public String toString()
    {
        String str="";
        for (int i=0; i<this.hnodes.length && hnodes[i]!=null; i++)
            str += i+", "+this.hnodes[i].toString()+"\n";
        return str;
    }

    public String[] haffmanCode()                //���ص�ǰ���������Ĺ���������
    {
        String[] code = new String[this.leafNum];
        for(int i=0; i<this.leafNum; i++)        //��n��Ҷ�ӽ��Ĺ���������
        {
            code[i]="";
            int child = i;
            int parent = hnodes[child].parent;
            while (parent!=-1)                   //��Ҷ�������ֱ�������ѭ��
            {
                if(hnodes[parent].left==child)
        	        code[i]="0"+code[i];         //���ӽ�����Ϊ0
                else
                    code[i]="1"+code[i];         //�Һ��ӽ�����Ϊ1
                child = parent;
                parent = hnodes[child].parent;        
            }
        }
        return code;
    }

    public static void main(String[] args)
    {
        int[] weight={5,29,7,8,14,23,3,11};      //ָ��Ȩֵ����
        HaffmanTree htree = new HaffmanTree(weight);
        System.out.println("���������Ľ������:\n"+htree.toString());
        String[] code = htree.haffmanCode();
        System.out.println("����������:");
        for (int i=0; i<code.length; i++)
             System.out.println(code[i]);
    }
}
/*

        int[] weight={5,29,7,8,14,23,3,11};//����
���������Ľ������:
0, 5, 8, -1, -1
1, 29, 13, -1, -1
2, 7, 9, -1, -1
3, 8, 9, -1, -1
4, 14, 11, -1, -1
5, 23, 12, -1, -1
6, 3, 8, -1, -1
7, 11, 10, -1, -1
8, 8, 10, 6, 0
9, 15, 11, 2, 3
10, 19, 12, 8, 7
11, 29, 13, 4, 9
12, 42, 14, 10, 5
13, 58, 14, 1, 11
14, 100, -1, 12, 13

����������:
0001
10
1110
1111
110
01
0000
001

        int[] weight = {2,4,5,7};                //ָ��Ȩֵ����
���������Ľ������:
0, 2, 4, -1, -1
1, 4, 4, -1, -1
2, 5, 5, -1, -1
3, 7, 6, -1, -1
4, 6, 5, 0, 1
5, 11, 6, 2, 4
6, 18, -1, 3, 5

����������:
110
111
10
0


        int[] weight = {7,5,2,4};//����
���������Ľ������:
0, 7, 6, -1, -1
1, 5, 5, -1, -1
2, 2, 4, -1, -1
3, 4, 4, -1, -1
4, 6, 5, 2, 3
5, 11, 6, 1, 4
6, 18, -1, 0, 5

����������:
0
10
110
111

        int[] weight = {1, 3, 5, 7};
���������Ľ������:
0, 1, 4, -1, -1
1, 3, 4, -1, -1
2, 5, 5, -1, -1
3, 7, 6, -1, -1
4, 4, 5, 0, 1
5, 9, 6, 4, 2
6, 16, -1, 3, 5

����������:
100
101
11
0

        int[] weight = {2,3,5,7,11,13,17,19,23,29,31,37,41};//����Т
���������Ľ������:
0, 2, 13, -1, -1
1, 3, 13, -1, -1
2, 5, 14, -1, -1
3, 7, 15, -1, -1
4, 11, 16, -1, -1
5, 13, 16, -1, -1
6, 17, 17, -1, -1
7, 19, 18, -1, -1
8, 23, 18, -1, -1
9, 29, 19, -1, -1
10, 31, 20, -1, -1
11, 37, 21, -1, -1
12, 41, 21, -1, -1
13, 5, 14, 0, 1
14, 10, 15, 2, 13
15, 17, 17, 3, 14
16, 24, 19, 4, 5
17, 34, 20, 6, 15
18, 42, 22, 7, 8
19, 53, 22, 16, 9
20, 65, 23, 10, 17
21, 78, 23, 11, 12
22, 95, 24, 18, 19
23, 143, 24, 20, 21
24, 238, -1, 22, 23

����������:
1011110
1011111
101110
10110
0100
0101
1010
000
001
011
100
110
111

*/