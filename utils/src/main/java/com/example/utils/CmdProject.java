package com.example.utils;

/**
 * 作者：hzh on 2022/1/20 21:03
 * 邮箱：565150953qq.com
 * 备注：设备类型定义
 */
public interface CmdProject {
    byte wireless_module=0x00;//无线模块
    byte rope_skipping=0x01;//跳绳
    byte mi50run=0x02;//50米跑
    byte mi50x8=0x03;//50*8
    byte standing_broad_jump=0x04;//立定跳远
    byte height_and_weight=0x05;//身高体重
    byte lungs_capacity=0x06;//肺活量
    byte throw_solid_sphere=0x07;//掷实心球
    byte long_distance_race=0x08;//长跑
    byte grip=0x09;//握力
    byte pull_up=0x0A;//引体向上
    byte sit_and_reach=0x0B;//坐位体前屈
    byte sit_up=0x0C;//仰卧起坐
    byte basketball=0x0D;//篮球运球
    byte soccer_dribbling=0x0E;//足球运球
    byte volleyball_mat_ball=0x0F;//排球垫球
    byte bench_test=0x10;//台阶测试
    byte snake_run=0x21;//蛇形跑




}
