package com.example.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：hzh on 2021/12/28 15:17
 * 邮箱：565150953qq.com
 * 备注：安卓跳转到新活动时加载视图，再加载数据。预防崩
 */
public class ProgressDialogUtils {
    /**
     * 定义线程池，异步操作加载点
     */
    private int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private int KEEP_ALIVE_TIME = 1;
    private TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingDeque<Runnable>(128));
    ProgressDialog mProgressDialog;
    Context context;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //若工作完成，取消动画，初始化界面
            if (msg.what == 1)
                mProgressDialog.cancel();
            /*//开始初始化界面
            initView();*/
        }
    };

    public ProgressDialogUtils(Context context) {
        this.context=context;
        //创建ProgressDialog
        createProgressDialog();
        //启动线程
        executorService.execute(mRunnable);
    }

    /**
     * 线程
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            /*
                需要耗时的工作
            */
            initData();
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            mHandler.sendMessage(msg);
        }

    };

    private void initData() {

    }

    /**
     * 创建ProrgressDialog
     */
    private void createProgressDialog() {
      //  context = this;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("加载数据中,请稍等...");
        //设置点击区域外的屏幕不关闭
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }




}
