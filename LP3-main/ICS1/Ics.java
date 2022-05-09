class Ics{
    public static int key[] = {1,0,1,0,0,0,0,0,1,0};
    public static int P10[] = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
    public static int P8[] = { 6, 3, 7, 4, 8, 5, 10, 9 };

    public static int key1[] = new int[8];
    public static int key2[] = new int[8];

    public static int[] IP = { 2, 6, 3, 1, 4, 8, 5, 7 };
    public static int[] EP = { 4, 1, 2, 3, 2, 3, 4, 1 };
    public static int[] P4 = { 2, 4, 3, 1 };
    public static int[] IP_inv = { 4, 1, 3, 5, 7, 2, 8, 6 };

    public static int[][] S0 = { { 1, 0, 3, 2 },
                { 3, 2, 1, 0 },
                { 0, 2, 1, 3 },
                { 3, 1, 3, 2 } };
    public static int[][] S1 = { { 0, 1, 2, 3 },
                { 2, 0, 1, 3 },
                { 3, 0, 1, 0 },
                { 2, 1, 0, 3 } };

    public static void key_generation()
    {
        int key_[] = new int[10];
        for(int i=0;i<10;i++)
        {
            key_[i] = key[P10[i]-1];
        }
        int l[] = new int[5];
        int r[] = new int[5];
        for(int i=0;i<5;i++)
        {
            l[i]=key_[i];
            r[i]=key_[i+5];
        }
        l = shift(l,1);
        r = shift(r,1);
        for(int i=0;i<5;i++)
        {
            key_[i]=l[i];
            key_[i+5]=r[i];
        }
        for(int i=0;i<8;i++)
        {
            key1[i]=key_[P8[i]-1];
        }
        l=shift(l,2);
        r=shift(l,2);
        for(int i=0;i<5;i++)
        {
            key_[i]=l[i];
            key_[i+5]=r[i];
        }
        for(int i=0;i<8;i++)
        {
            key2[i]=key_[P8[i]-1];
        }
        System.out.print("Your first key is : ");
        for(int i=0;i<8;i++)
        {
            System.out.print(key1[i]);
        }
        System.out.println();
        System.out.print("Your second key is : ");
        for(int i=0;i<8;i++)
        {
            System.out.print(key2[i]);
        }
        System.out.println();

    }

    public static int[] shift(int[] key, int n)
    {
        int newKey[] = new int[5];
        for(int i=0;i<5-n;i++)
        {
            newKey[i]=key[i+n];
        }
        int temp = 5-n;
        for(int i=5-n;i<5;i++)
        {
            newKey[i]=key[i-temp];
        }
        return newKey;
    }

    public static String calc(int val)
    {
        if(val == 0)return "00";
        else if(val == 1)return "01";
        else if(val == 2)return "10";
        else return "11";
    }

    public static int[] functionK(int[] text,int[] key)
    {
        int l[] = new int[4];
        int r[] = new int[4];
        for(int i=0;i<4;i++)
        {
            l[i]=text[i];
            r[i]=text[i+4];
        }
        int ep[] = new int[8];
        for(int i=0;i<8;i++)
        {
            ep[i] = r[EP[i]-1];
        }
        for(int i=0;i<8;i++)
        {
            text[i] = ep[i]^key[i];
        }
        int l_1[] = new int[4];
        int r_1[] = new int[4];
        for(int i=0;i<4;i++)
        {
            l_1[i]=text[i];
            r_1[i]=text[i+4];
        }
        int row,col,val;
        row=Integer.parseInt(""+l_1[0]+l_1[3],2);
        col=Integer.parseInt(""+l_1[1]+l_1[2],2);
        val=S0[row][col];
        String l_s = calc(val);
    
        row=Integer.parseInt(""+r_1[0]+r_1[3],2);
        col=Integer.parseInt(""+r_1[1]+r_1[2],2);
        val=S1[row][col];
        String r_s = calc(val);
        int r_[] = new int[4];
        r_[0]= Character.getNumericValue(l_s.charAt(0));
        r_[1]= Character.getNumericValue(l_s.charAt(1));
        r_[2]= Character.getNumericValue(r_s.charAt(0));
        r_[3]= Character.getNumericValue(r_s.charAt(1));

        for(int i=0;i<4;i++)
        {
            r_[i]=r_[P4[i]-1];
        }
        for(int i=0;i<4;i++)
        {
            l[i] = l[i] ^ r_[i];
        }
        int newText[] = new int[8];
        for(int i=0;i<4;i++)
        {
            newText[i]=l[i];
            newText[i+4]=r[i];
        }
        return newText;
    }

    public static int[] swapping(int[] text)
    {
        for(int i=0;i<4;i++)
        {
            int temp = text[i];
            text[i] = text[i+4];
            text[i]=temp;
        }
        return text;
    } 


    public static int[] encryption(int []plainText)
    {
        int permute[] = new int[8];
        for(int i=0;i<8;i++)
        {
            permute[i]=plainText[IP[i]-1];
        }
        permute = functionK(permute,key1); 
        int after_swap[] = swapping(permute);
        permute = functionK(after_swap, key2);
        int cipherText[] = new int[8];
        for(int i=0;i<8;i++)
        {
            cipherText[i] = permute[IP_inv[i]-1];
        }
        return cipherText;
    }

    public static int[] decryption(int[] text)
    {
        int permute[] = new int[8];
        for(int i=0;i<8;i++)
        {
            permute[i]=text[IP[i]-1];
        }
        permute=functionK(permute, key2);
        permute=swapping(permute);
        permute=functionK(permute, key1);
        int plainText[] = new int[8];
        for(int i=0;i<8;i++)
        {
            plainText[i]=permute[IP_inv[i]-1];
        }
        return plainText;
    }
    
    public static void main(String[] args) {
        key_generation();
        int plainText[] = {1, 0, 0, 1, 0, 1, 1, 1};
        int cipherText[] = encryption(plainText);
        System.out.print("Cipher Text : ");
        for(int i=0;i<8;i++)
        {
            System.out.print(cipherText[i]);
        }
        System.out.println();
        plainText = decryption(cipherText);
        System.out.print("Plain Text : ");
        for(int i=0;i<8;i++)
        {
            System.out.print(plainText[i]);
        }

    }        
}