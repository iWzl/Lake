package com.upuphub.lake.skylake.bytes;

public class ByteOneSizeCount {
    public static void main(String[] args) {
        System.out.println(getByteOneSizeCount(new Long(0b100010001000111)));
        System.out.println(getByteOneSizeCount(new Long(0b100010001100111)));
    }

    private static int getByteOneSizeCount(long num){
        long val = num;
        int ans = 0;
        while (val != 0) {
            ++ans;
            val = val & (val-1);
        }
        return ans;
    }


}
