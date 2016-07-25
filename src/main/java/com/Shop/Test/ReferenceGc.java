package com.Shop.Test;

/**
 * Created by Administrator on 2016/7/25.
 */
public class ReferenceGc {
    public Object instance = null;
    private static final int mv = 1024*1024;
    private byte[] bigsize = new byte[2*mv];
    public static void main(String[] args){
        ReferenceGc gcA = new ReferenceGc();
        ReferenceGc gcB = new ReferenceGc();
        gcA.instance=gcB;
        gcB.instance=gcA;
        gcA=null;
        gcB=null;
        System.gc();
    }
}
