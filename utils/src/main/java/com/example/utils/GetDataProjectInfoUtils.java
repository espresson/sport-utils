package com.example.utils;

/**
 * 作者：hzh on 2022/1/17 16:33
 * 邮箱：565150953qq.com
 * 备注：解析设备数据
 */
public class GetDataProjectInfoUtils {
    public static String projectName;//项目名
    public static int networkGroup;//网络组
    public static int  route;//信道
    public static int  transmittedPowe;//发射功率
    public static int datarate;//数据率
    public static int serial_port_baud_rate;//串口波特率
    public static int netWorkID;//网络id
    public static int theReservedDesign;//预留设计

//#define PROT_SETUP_DEVICE_TYPE(data)     ((uint8_t)((data[0] >> 0) & 0xFF))
//#define PROT_SETUP_NET_GROUP(data)       ((uint8_t)((data[1] >> 4) & 0x0F))
//#define PROT_SETUP_SIGNAL_CHANNEL(data)  ((uint8_t)(((data[1] << 2) & 0x3C) | (data[2] >> 6) & 0x03))
//#define PROT_SETUP_DATA_RATE(data)       ((uint8_t)((data[2] >> 4) & 0x03))
//#define PROT_SETUP_DATA_POWER(data)      ((uint8_t)((data[2] >> 1) & 0x07))
//#define PROT_SETUP_NET_ID(data)          ((uint8_t)(((data[2] << 7) & 0x80) | ((data[3] >> 1) & 0x7F)))
//#define PROT_SETUP_SERIAL_RATE(data)     ((uint8_t)(((data[3] << 2) & 0x04) | ((data[4] >> 6) & 0x03)))

    public static void init(byte[] data){//02461E1C000000
        switch (data[0]){
            case 0x00:
                projectName="无线模块";
                break;
            case 0x01:
                projectName="跳绳";
                break;
            case 0x02:
                projectName="50米跑";
                break;
            case 0x03:
                projectName="50米*8";
                break;
            case 0x04:
                projectName="立定跳远";
                break;
            case 0x05:
                projectName="身高体重";
                break;
            case 0x06:
                projectName="肺活量";
                break;
            case 0x07:
                projectName="掷实心球";
                break;
            case 0x08:
                projectName="长跑";
                break;
            case 0x09:
                projectName="握力";
                break;
            case 0x0A:
                projectName="引体向上";
                break;
            case 0x0B:
                projectName="坐位体前屈";
                break;
            case 0x0C:
                projectName="仰卧起坐";
                break;
            case 0x0D:
                projectName="篮球运球";
                break;
            case 0x0E:
                projectName="足球运球";
                break;
            case 0x0F:
                projectName="排球垫球";
                break;
            case 0x10:
                projectName="台阶测试";
                break;
            case 0x21:
                projectName="蛇形跑";
                break;
        }
       // 02  461E1C000000   0110  00011110
        networkGroup=data[1] >> 4 & 0x0f;//网络组
        route=((data[1] << 2) & 0x3C) | (data[2] >> 6) & 0x03;//信道
        datarate=(data[2] >> 4) & 0x03;//数据率
        transmittedPowe=(data[2] >> 1) & 0x07;//发射功率
        netWorkID=((data[2] << 7) & 0x80) | ((data[3] >> 1) & 0x7F);//网络ID
        serial_port_baud_rate=(((data[3] << 2) & 0x04) | ((data[4] >> 6) & 0x03));//波特率


    }
}
