package com.example.utils;

/**
 * 指令：无线模块0开头，设备A开头  例如：查询无线模块0x01,查询设备信息0xA1
 */
public interface Cmd {
    //AA 0C FFFFFFFF FFFFFFFF 89 37
    //设计保留
    byte info=(byte)0x00;
    //查询主模块
    byte MODULE=(byte)0x01;
    //配置主模块
    byte CONFIGURING_MASTER_MODULE=(byte)0x02;
    //查询无线模块id
    byte SELECT_WIFI_ID=(byte)0x08;
    //查询设备信息
    byte DeviceInfo=(byte)0xA1;
    //配置设备
    byte CONFIGURING_MASTER_SHEBEI=(byte)0x82;
    //设备心跳
    byte EQUIPMENT_HEARTBEAT=(byte)0x84;
    //升级固件
    byte TO_UPGRADE_FIRMWARE=(byte)0x83;
    //设备校时
    byte WHEN_SCHOOL_EQUIPMENT=(byte)0x85;
    //设备自检
    byte DEVICE_SELF_CHECKING=(byte)0xA6;
    //设备校准
    byte Device_Calibration=(byte)0xA7;
    //查询ID
    byte GETTHREADID=(byte)0x81;
    //单独查询设备ID
    byte GETSHEBEIID=(byte)0x88;
    //查询设备ID
    byte GETID=(byte)0xC8;
    //查询电量
    byte BATTERY_STATUS=(byte)0x89;
    //查询电量
    byte BATTERY_STATUSC=(byte)0x89;
    //查询成绩
    byte check_results=(byte)0x8A;
    //广播查询成绩
    byte check_results4A=(byte)0x4A;
    //测量开始
    byte start_measurement=(byte)0x8B;
    //测量结束
    byte End_Measurement=(byte)0x8C;
    //测量取消
    byte Measurement_cancel=(byte)0x8D;
    //显示/音频
    byte Display_Audio=(byte)0x8E;
    //0F ~ 1F 保留












}
