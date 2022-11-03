package com.example.utils;


import static com.example.utils.CmdCheckUtils.cmdChecksum1;

/**
 * 作者：hzh on 2022/1/10 19:46
 * 邮箱：565150953qq.com
 * 备注：通过evenbus获取TCP通信结果数据
 */
public class TcpDataBean {
    private byte[] data;
    private byte FH;//帧头
    private byte FHLength;//帧头长度
    private byte[] sourceAddress;//源地址
    private byte[] targetAddress;//目标地址
    private byte order;//指令
    private byte[] info;//返回数据信息
    private byte checksum;//校验和

    public TcpDataBean(byte[] result) {
        setData(result);
        if(result[0]==(byte)0xAA && cmdChecksum1(result)){
           //打印 ByteUtil.bytes2HexStr(new byte[]{result[0]})
            setFH(result[0]);//帧头
            setFHLength(result[1]);//帧头长度

            byte[] source=new byte[4];//源地址
            System.arraycopy(result,2,source,0,4);
            setSourceAddress(source);

            byte[] target=new byte[4];//目标地址
            System.arraycopy(result,6,target,0,4);
            setTargetAddress(target);

           setOrder(result[10]); //指令
          //  AA0CFFFFFFFF0A0000008A46
          //  返回数据信息

            if(Integer.parseInt(ByteUtil.byteToString(FHLength),16)>12){
                byte[] infoData=new byte[Integer.parseInt(ByteUtil.byteToString(FHLength),16)-12];
                System.arraycopy(result,11,infoData,0,Integer.parseInt(ByteUtil.byteToString(FHLength),16)-12);
                setInfo(infoData);
            }

          //  校验和
            setChecksum(result[Integer.parseInt(ByteUtil.byteToString(FHLength),16)-12+11]);
        }

    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte getFH() {
        return FH;
    }

    public void setFH(byte FH) {
        this.FH = FH;
    }

    public byte getFHLength() {
        return FHLength;
    }

    public void setFHLength(byte FHLength) {
        this.FHLength = FHLength;
    }

    public byte[] getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(byte[] sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public byte[] getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(byte[] targetAddress) {
        this.targetAddress = targetAddress;
    }

    public byte getOrder() {
        return order;
    }

    public void setOrder(byte order) {
        this.order = order;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }

    public byte getChecksum() {
        return checksum;
    }

    public void setChecksum(byte checksum) {
        this.checksum = checksum;
    }
}
