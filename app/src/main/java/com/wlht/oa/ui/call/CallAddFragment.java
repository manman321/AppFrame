package com.wlht.oa.ui.call;/**
 * Created by hr on 16/7/6.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.wlht.oa.R;
import com.wlht.oa.base.BaseTitleFragment;
import com.wlht.oa.util.StringUtils;
import com.wlht.oa.util.SynchronizeController;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import cn.easydone.messagesendview.AudioRecorder;
import cn.easydone.messagesendview.Constans;
import cn.easydone.messagesendview.KeyboardStateLayout;
import cn.easydone.messagesendview.MediaUtil;
import cn.easydone.messagesendview.MessageSendView;
import cn.easydone.messagesendview.RecordVoiceView;
import cn.easydone.messagesendview.StorageUtils;

/**
 * 没有输入任何的语音和文本的时候
 * 1.最下面的按钮状态为录制语音按钮
 * 2.当输入语音或者文字之后  最下面的按钮状态变为发送按钮
 * 3.删除语音或者文本之后  最下面的按钮状态又变成发送按钮
 *
 */
public class CallAddFragment extends BaseTitleFragment {

    int MIN_CHAT_VOICE_SECONDS = 1;
    int MAX_CHAT_VOICE_SECONDS = 60;
    int MAX_CHAT_VOICE_LENGTH = MAX_CHAT_VOICE_SECONDS * 1000 + 500;

    RecordVoiceView mRecordVoiceView;
    TextView mTextView;
    EditText mSendContentTv;
    private long voiceLength;
    private SpeechSynthesizer mTts;


    private TextView mRecordVoiceButton;
    private boolean mSupportVoice;
    private boolean mIsVoiceMode = false;
    private boolean mIsVoiceDown = false;
    private boolean mIsVoiceStopNotify = false;
    private int mVoiceStopDelayTimes = 0;
    /* Record voice */
    private AudioRecorder mAudioRecorder = AudioRecorder.getInstance();
    private String mVoiceFilePath;
    private Handler handler = new Handler();


    private ImageView madintain_info_detele,item_maintain_info_image;
    private TextView item_maintain_infoTV,item_voice_time;
    private View voiceFly,sendContentFly;
    private MediaPlayer mp = new MediaPlayer();


    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_call, null, false);
    }

    @Override
    public void initNavBar() {
        setHeaderTitle("语音呼叫");
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        SpeechUtility.createUtility(getContext(), "appid=577b782a");
        mSendContentTv = (EditText)view.findViewById(R.id.sendContentTv);
        mTts = SpeechSynthesizer.createSynthesizer(getContext(), null);
        mVoiceFilePath = StorageUtils.getTempDir() + File.separator + "voice_temp_" + System.currentTimeMillis() + ".wav";
        mRecordVoiceButton = (TextView) view.findViewById(R.id.voice_record_button);

        voiceFly = view.findViewById(R.id.voiceFly);
        sendContentFly = view.findViewById(R.id.sendContentFly);
        view.findViewById(R.id.item_maintain_infoED).setVisibility(View.GONE);
        item_maintain_infoTV = (TextView)view.findViewById(R.id.item_maintain_infoTV);
        item_maintain_infoTV.setVisibility(View.VISIBLE);
        item_voice_time = (TextView)view.findViewById(R.id.item_voice_time);
        madintain_info_detele = (ImageView)view.findViewById(R.id.madintain_info_detele);
        item_maintain_info_image = (ImageView)view.findViewById(R.id.item_maintain_info_image);

        setVoiceVisibility(false);

//        KeyboardStateLayout rootView = (KeyboardStateLayout) view.findViewById(R.id.rootView);
//        controlKeyboardLayout(rootView,rootView);
        mTextView = (TextView) view.findViewById(R.id.text);
        mRecordVoiceView = (RecordVoiceView) view.findViewById(R.id.record_state_view);


        mp.setOnCompletionListener(mp->{
            isPlaying = false;
        });
    }


    private void setMessageSendView() {
        //判断是语音还是文本,如果是文本,将文本转换为语音然后在发送。
        //语音转文本
        String text = mSendContentTv.getText().toString();
        TTS_AUDIO_PATH = Environment.getExternalStorageDirectory() + "/tts.wav";
        setParam();
        int code = mTts.synthesizeToUri(text, TTS_AUDIO_PATH, mTtsListener);
        if (code != ErrorCode.SUCCESS) {
            showToast("语音合成失败,错误码: " + code);
        }
    }



    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }

    private String TTS_AUDIO_PATH = null;

    private void setParam(){
        mTts.setParameter(SpeechConstant.PARAMS, null);
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, TTS_AUDIO_PATH);

//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.pcm");
    }

    private String mVoicePath = "";

    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {}
        @Override
        public void onSpeakPaused() {}
        @Override
        public void onSpeakResumed() {}
        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
            if (percent == 100)
            {
                long amrDuration = MediaUtil.getAmrDuration(TTS_AUDIO_PATH);
                voiceLength = amrDuration / 1000;
                if (voiceLength < Constans.MIN_CHAT_VOICE_SECONDS)
                    return;
                mVoicePath = TTS_AUDIO_PATH;
                mSendContentTv.setText("");
                setVoiceVisibility(true);
                sendRecord();
            }
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
//            mPercentForPlaying = percent;
//            showToast(String.format("缓冲进度为%d，播放进度为%d", mPercentForBuffering, mPercentForPlaying));
        }

        @Override
        public void onCompleted(SpeechError error) {
            if(error != null) {
                showToast(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {}
    };


    private void play()
    {
        if (!StringUtils.isEmpty(mVoicePath))
        {
            try {
                mp.reset();
                mp.setDataSource(mVoicePath);
                mp.prepare();
                mp.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop()
    {
        mp.stop();
    }

    private void setVoiceVisibility(boolean visibility)
    {
        if (visibility)
        {
            voiceFly.setVisibility(View.VISIBLE);
            item_voice_time.setText(String.format("%d", voiceLength));
            sendContentFly.setVisibility(View.GONE);
            mSendContentTv.clearFocus();
            mRecordVoiceButton.setOnTouchListener(null);
            mRecordVoiceButton.setOnClickListener(onClickListener);
            mRecordVoiceButton.setText("发起呼叫");

        }else
        {
            voiceFly.setVisibility(View.GONE);
            sendContentFly.setVisibility(View.VISIBLE);

            mRecordVoiceButton.setOnTouchListener(onTouchListener);
            mRecordVoiceButton.setOnClickListener(null);
            mRecordVoiceButton.setText("按住说话");
        }

    }

    private boolean isPlaying = false;
    @Override
    public void initEvent(View view) {

        view.findViewById(R.id.clearTv).setOnClickListener(v->{
            mSendContentTv.setText("");
            setVoiceVisibility(false);
        });

        view.findViewById(R.id.presetIv).setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            final String[] values = new String[]{"过来我办公室商议重要事宜","测试","谈业务"};
            builder.setItems(values, (dialog, which) -> {
                setVoiceVisibility(false);
                mSendContentTv.setText(values[which]);
            });
            builder.show();
        });

        madintain_info_detele.setOnClickListener(v->{
            setVoiceVisibility(false);
        });

        item_maintain_info_image.setOnClickListener(v->{
            if (isPlaying)
            {
                stop();
            }else
            {
                play();
            }
            //播放、暂停
        });

        mRecordVoiceButton.setOnTouchListener(onTouchListener);


        mSendContentTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    setVoiceVisibility(false);
                } else {
                    mRecordVoiceButton.setOnTouchListener(null);
                    mRecordVoiceButton.setOnClickListener(onClickListener);
                    mRecordVoiceButton.setText("发起呼叫");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void sendRecord()
    {




    }

    private View.OnClickListener onClickListener = v -> {
        //check
        if (sendContentFly.getVisibility() == View.GONE)
        {
            sendRecord();
        }else
        {
            setMessageSendView();
        }
    };

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        private float downY;
        private static final float CANCEL_Y_OFFSET = 100;
        private Timer mTimer;
        private int remainSeconds;
        private boolean finished = false;

        public void onStart() {
            mRecordVoiceView.setVisibility(View.VISIBLE);
            mRecordVoiceView.setState(false);
        }

        public void onInfo(int remainSeconds) {
            mRecordVoiceView.setRemainIcons(remainSeconds);
        }

        public void onFinish(String fileName, boolean cancelled) {
            {
                mRecordVoiceView.setVisibility(View.GONE);
                if (cancelled) {
                    setVoiceVisibility(false);
                    return;
                }
                long amrDuration = MediaUtil.getAmrDuration(fileName);
                voiceLength = amrDuration / 1000;
                if (voiceLength < Constans.MIN_CHAT_VOICE_SECONDS)
                    return;
                mVoicePath = fileName;
                setVoiceVisibility(true);
            }
        }

        public void willCancel(boolean willCancel) {
            mRecordVoiceView.setState(willCancel);
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.d("mRecordVoiceButton", String.format("type: %d y: %d", event.getAction(), px2dp(v.getContext(), event.getY())));
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (mIsVoiceDown)
                        return true;
                    mIsVoiceDown = true;
                    mIsVoiceStopNotify = false;
                    mVoiceStopDelayTimes = 0;
                    mRecordVoiceButton.setText(getResources().getString(cn.easydone.messagesendview.R.string.chat_message_release_to_send));
                    downY = px2dp(v.getContext(), event.getY());

                    if (mRecordVoiceView != null) onStart();


                    mAudioRecorder.startRecording(mVoiceFilePath, MAX_CHAT_VOICE_LENGTH);
                    remainSeconds = MAX_CHAT_VOICE_SECONDS;
                    mTimer = new Timer();
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mVoiceStopDelayTimes++;
                                    if (mIsVoiceStopNotify && mVoiceStopDelayTimes >= 1) {
                                        mAudioRecorder.stopRecording();
                                        mIsVoiceDown = false;
                                        mTimer.cancel();
                                        if (mVoiceStopDelayTimes < 2) {
                                            return;
                                        }
                                    }

                                    remainSeconds--;
                                    if (remainSeconds == 0) {
                                        mAudioRecorder.stopRecording();
                                    }
                                    if (mRecordVoiceView != null && !finished) {
                                        if (remainSeconds == 0) {
                                            onFinish(mVoiceFilePath, false);
                                        } else {
                                            onInfo(remainSeconds);
                                        }
                                    }
                                    if (remainSeconds == 0) {
                                        mTimer.cancel();
                                        finished = true;
                                    }
                                }
                            }, 500);
                        }
                    }, 1000, 1000);
                    finished = false;

                    if (mRecordVoiceView != null) {
                        onInfo(remainSeconds);
                    }
                    break;
                case MotionEvent.ACTION_MOVE: {
                    float y = px2dp(v.getContext(), event.getY());
                    if (mRecordVoiceView != null) willCancel(downY - y > CANCEL_Y_OFFSET);
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (!mIsVoiceDown)
                        return true;

                    mIsVoiceStopNotify = true;
                    mRecordVoiceButton.setText(getResources().getString(cn.easydone.messagesendview.R.string.chat_message_pressed_to_talk));
                    float y = px2dp(v.getContext(), event.getY());
                    if (mRecordVoiceView != null && !finished) {
                        onFinish(mVoiceFilePath, downY - y > CANCEL_Y_OFFSET);
                    }
                    finished = true;
                    break;
                }
            }
            return true;
        }
    };



    @Override
    public void initData() {

    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            default:
                break;
        }
    }

}
