package com.leevinapp.monitor;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ksyun.media.player.IMediaPlayer;
import com.ksyun.media.player.KSYMediaPlayer;
import com.leevinapp.monitor.base.BaseActivity;

import java.io.IOException;

public class VideoPlayerActivity extends BaseActivity {

    // 播放器的对象
    private KSYMediaPlayer ksyMediaPlayer;
    // 播放SDK提供的监听器
    private IMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangeListener;
    private IMediaPlayer.OnSeekCompleteListener mOnSeekCompletedListener;
    // SurfaceView需在Layout中定义，此处不在赘述
    private SurfaceView mVideoSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    // 播放完成时会发出onCompletion回调
    private IMediaPlayer.OnCompletionListener mOnCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            // 播放完成，用户可选择释放播放器
            if(ksyMediaPlayer != null) {
                ksyMediaPlayer.stop();
                ksyMediaPlayer.release();
            }
        }
    };

    // 播放器在准备完成，可以开播时会发出onPrepared回调
    private IMediaPlayer.OnPreparedListener mOnPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if(ksyMediaPlayer != null) {
                // 设置视频伸缩模式，此模式为裁剪模式
                ksyMediaPlayer.setVideoScalingMode(KSYMediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                // 开始播放视频
                ksyMediaPlayer.start();
            }
        }
    };


    // 播放器遇到错误时会发出onError回调
    private IMediaPlayer.OnErrorListener mOnErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            Toast.makeText(VideoPlayerActivity.this, "播放器遇到错误，播放已退出，错误码:"+i, Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    private IMediaPlayer.OnInfoListener mOnInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            switch (i) {
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START:

                    break;
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END:

                    break;
            }
            return false;
        }
    };


    private final String mVideoUrl = "https://c1d15d1c67ef400f814c731f98e7024c.apigw.cn-north-1.huaweicloud.com/live/work001?app=vis&stream=channel_57881367261320000017_wz_10";
    String url = "rtmp://58.200.131.2:1935/livetv/hunantv";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        mVideoSurfaceView = findViewById(R.id.surfaceView);
        ksyMediaPlayer = new KSYMediaPlayer.Builder(this.getApplicationContext()).build();
        mSurfaceHolder = mVideoSurfaceView.getHolder();
        mSurfaceHolder.addCallback(mSurfaceCallback);

        ksyMediaPlayer.setOnCompletionListener(mOnCompletionListener);
        ksyMediaPlayer.setOnPreparedListener(mOnPreparedListener);
        ksyMediaPlayer.setOnInfoListener(mOnInfoListener);
        ksyMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangeListener);
        ksyMediaPlayer.setOnErrorListener(mOnErrorListener);
        ksyMediaPlayer.setOnSeekCompleteListener(mOnSeekCompletedListener);

        try {
            ksyMediaPlayer.setDataSource(mVideoUrl);
            ksyMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ksyMediaPlayer != null) {
            ksyMediaPlayer.release();
            ksyMediaPlayer = null;
        }

    }


    private final SurfaceHolder.Callback mSurfaceCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if(ksyMediaPlayer != null) {
                ksyMediaPlayer.setDisplay(holder);
                ksyMediaPlayer.setScreenOnWhilePlaying(true);
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // 此处非常重要，必须调用!!!
            if(ksyMediaPlayer != null) {
                ksyMediaPlayer.setDisplay(null);
            }
        }
    };
}
