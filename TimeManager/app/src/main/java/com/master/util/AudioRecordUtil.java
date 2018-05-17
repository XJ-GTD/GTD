package com.master.util;

import android.app.AlertDialog;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Bundle;
import android.widget.Toast;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import org.json.JSONObject;

import java.io.*;

public class AudioRecordUtil {
    // 缓冲区字节大小
    private int bufferSizeInBytes = 0;

    //AudioName裸音频数据文件 ，麦克风
    private String AudioName = "";

    //NewAudioName可播放的音频文件
    private String NewAudioName = "";

    private AudioRecord audioRecord;
    private boolean isRecord = false;// 设置正在录制的状态


    private static AudioRecordUtil mInstance;

    private AudioRecordUtil(){

    }

    public synchronized static AudioRecordUtil getInstance()
    {
        if(mInstance == null)
            mInstance = new AudioRecordUtil();
        return mInstance;
    }

    public int startRecordAndFile() {
        //判断是否有外部存储设备sdcard
        if(AudioFileFunc.isSdcardExit())
        {
            if(isRecord)
            {
                return ErrorCode.E_STATE_RECODING;
            }
            else
            {
                if(audioRecord == null)
                    creatAudioRecord();

                audioRecord.startRecording();
                // 让录制状态为true
                isRecord = true;
                // 开启音频文件写入线程
                new Thread(new AudioRecordThread()).start();

                return ErrorCode.SUCCESS;
            }

        }
        else
        {
            return ErrorCode.E_NOSDCARD;
        }

    }

    //停止录音并上传解析
    public boolean stopRecordAndFile(Context context) {
        return close(context);
    }


    public long getRecordFileSize(){
        return AudioFileFunc.getFileSize(NewAudioName);
    }


    private boolean close(Context context) {
        if (audioRecord != null) {
            System.out.println("stopRecord");
            isRecord = false;//停止文件写入
            audioRecord.stop();
            audioRecord.release();//释放资源
            audioRecord = null;
            uploadFile(context);
            return true;
        }
        return false;
    }

    public void uploadFile(final Context context)
    {
        //服务器端地址
        String url = "http://192.168.99.39:8080/gtd/readAudio/read";
        //手机端要上传的文件，首先要保存你手机上存在该文件
        String filePath = AudioFileFunc.getWavFilePath();

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.setResponseTimeout(120000);
        RequestParams param = new RequestParams();
        try
        {
            param.put("file", new File(filePath));
            param.put("content", "上传音频");

            httpClient.post(url, param, new AsyncHttpResponseHandler()
            {
                @Override
                public void onStart() {
                    // Initiated the request
                }

                @Override
                public void onRetry(int retryNo) {
                    // Request was retried
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    // Progress notification
                }

                @Override
                public void onFinish() {
                    // Completed the request (either success or failure)
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    // Successfully got a response
                    //statusCode:状态码    headers：头信息  responseBody：返回的内容，返回的实体
                    byte[] ans = responseBody;
                    //判断状态码
                    if(statusCode == 200){
                        //获取结果
                        try {
                            String result = new String(responseBody,"utf-8");
                            JSONObject json =new JSONObject(result);
                            String[] strs =new String[2];
//                            strs[0] = json.getString("result");
                            JSONObject res=json.getJSONObject("data");
                            if(res.getString("scheduleName")!=""||res.getString("scheduleName")!=null){
                                strs[0] = res.getString("scheduleName");
                            }
                            if(res.getString("scheduleFinshDateString")!=""||res.getString("scheduleFinshDateString")!=null){
                                strs[1] = "日期:" +res.getString("scheduleFinshDateString");
                            }
//                            if(res.getString("address")!=""||res.getString("address")!=null){
//                                strs[2] = "地址:" +res.getString("address");
//                            }
//                            if(res.getString("name")!=""||res.getString("name")!=null){
//                                strs[3] = "参与人:" +res.getString("name");
//                            }
//                            strs[1] ="日期:" +res.getString("scheduleFinshDateString");
//
//                            strs[2] ="地址:" +res.getString("address");
//
//                            strs[3] = "参与人:" +res.getString("name");
                            showExitDialog(strs,context);
                            String speektext="";
                            if(res.getString("result")!=""||res.getString("result")!=null) {
                                speektext = "您的日程为"+res.getString("result");
                            }
                            speekText(speektext,context);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    // Response failed :(
                    String err = error.toString();
                    Toast.makeText(context, "上传失败！", Toast.LENGTH_SHORT).show();
                }


            });

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "上传文件不存在！", Toast.LENGTH_LONG).show();
        }
    }



    private void creatAudioRecord() {
        // 获取音频文件路径
        AudioName = AudioFileFunc.getRawFilePath();
        NewAudioName = AudioFileFunc.getWavFilePath();

        // 获得缓冲区字节大小
        bufferSizeInBytes = AudioRecord.getMinBufferSize(AudioFileFunc.AUDIO_SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT);

        // 创建AudioRecord对象
        audioRecord = new AudioRecord(AudioFileFunc.AUDIO_INPUT, AudioFileFunc.AUDIO_SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes);
    }


    class AudioRecordThread implements Runnable {
        @Override
        public void run() {
            writeDateTOFile();//往文件中写入裸数据
            copyWaveFile(AudioName, NewAudioName);//给裸数据加上头文件
        }
    }

    /**
     * 这里将数据写入文件，但是并不能播放，因为AudioRecord获得的音频是原始的裸音频，
     * 如果需要播放就必须加入一些格式或者编码的头信息。但是这样的好处就是你可以对音频的 裸数据进行处理，比如你要做一个爱说话的TOM
     * 猫在这里就进行音频的处理，然后重新封装 所以说这样得到的音频比较容易做一些音频的处理。
     */
    private void writeDateTOFile() {
        // new一个byte数组用来存一些字节数据，大小为缓冲区大小
        byte[] audiodata = new byte[bufferSizeInBytes];
        FileOutputStream fos = null;
        int readsize = 0;
        try {
            File file = new File(AudioName);
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);// 建立一个可存取字节的文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (isRecord == true) {
            readsize = audioRecord.read(audiodata, 0, bufferSizeInBytes);
            if (AudioRecord.ERROR_INVALID_OPERATION != readsize && fos!=null) {
                try {
                    fos.write(audiodata);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if(fos != null)
                fos.close();// 关闭写入流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 这里得到可播放的音频文件
    private void copyWaveFile(String inFilename, String outFilename) {
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen + 36;
        long longSampleRate = AudioFileFunc.AUDIO_SAMPLE_RATE;
        int channels = 2;
        long byteRate = 16 * AudioFileFunc.AUDIO_SAMPLE_RATE * channels / 8;
        byte[] data = new byte[bufferSizeInBytes];
        try {
            in = new FileInputStream(inFilename);
            out = new FileOutputStream(outFilename);
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
//            WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
//                    longSampleRate, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里提供一个头信息。插入这些信息就可以得到可以播放的文件。
     * 为我为啥插入这44个字节，这个还真没深入研究，不过你随便打开一个wav
     * 音频的文件，可以发现前面的头文件可以说基本一样哦。每种格式的文件都有
     * 自己特有的头文件。
     */
    private void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen,
                                     long totalDataLen, long longSampleRate, int channels, long byteRate)
            throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 1
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (2 * 16 / 8); // block align
        header[33] = 0;
        header[34] = 16; // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }

    // 列表对话框
    private void showExitDialog(String[] strs,Context context){
        new AlertDialog.Builder(context)
                .setTitle("日程")
                .setItems(strs, null)
                .setPositiveButton("确定", null)
                .show();
    }

    private void speekText(String str,Context context) {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer( context, null);
        //2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录 13.2
        mTts.setParameter(SpeechConstant. VOICE_NAME, "xiaoyan" ); // 设置发音人
        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
        mTts.setParameter(SpeechConstant. VOLUME, "80" );// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant. TYPE_CLOUD); //设置云端shuo
        //设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
        //3.开始合成
//        mTts.startSpeaking( et_input.getText().toString(), new MySynthesizerListener()) ;
        mTts.startSpeaking( str, new MySynthesizerListener()) ;
    }

    class MySynthesizerListener implements SynthesizerListener {

        @Override
        public void onSpeakBegin() {
//            showTip(" 开始播放 ");
        }

        @Override
        public void onSpeakPaused() {
//            showTip(" 暂停播放 ");
        }

        @Override
        public void onSpeakResumed() {
//            showTip(" 继续播放 ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos ,
                                     String info) {
            // 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
//                showTip("播放完成 ");
            } else if (error != null ) {
//                showTip(error.getPlainDescription( true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话 id为null
            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //     Log.d(TAG, "session id =" + sid);
            //}
        }
    }

}
