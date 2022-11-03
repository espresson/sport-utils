package com.example.utils;

/**
 * 作者：hzh on 2022/1/8 20:00
 * 邮箱：565150953qq.com
 * 备注：
 */
public class CmdCheckUtils {
    /**
     * 校验和
     * @return
     * AA 0C FFFFFFFF FFFFFFFF 89 37
     */
    public static byte cmdChecksum(byte[] bytes){
        int check=0;
        for(int i=0;i<bytes.length;i++){
            check= bytes[i]+check;
        }

        return (byte)(check);
    }
    /**
     * 校验和是否正确
     * @return
     * AA 0C FFFFFFFF FFFFFFFF 89 37 ba
     */
    public static boolean cmdChecksum1(byte[] bytes){
        if(bytes==null||bytes.length<12){
            return false;
        }
        byte[] b=new byte[bytes.length-1];
        System.arraycopy(bytes,0,b,0,bytes.length-1);
        int check=0;
        for(int i=0;i<b.length;i++){
            check= b[i]+check;
        }

        if(bytes[bytes.length-1]==(byte) check){
            return true;
        }else {
            return false;
        }
    }

    public static byte[]  getChecked(String result){
        byte[] sss={CmdCheckUtils.cmdChecksum(ByteUtil.hexStr2bytes(result))};
        byte[] send=new byte[ByteUtil.hexStr2bytes(result).length+1];
        System.arraycopy(ByteUtil.hexStr2bytes(result),0,send,0,ByteUtil.hexStr2bytes(result).length);
        send[send.length-1]= CmdCheckUtils.cmdChecksum(sss);
        return send;
    }

}
