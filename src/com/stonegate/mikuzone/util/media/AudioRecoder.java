package com.stonegate.mikuzone.util.media;
import com.stonegate.mikuzone.component.audio.AudioVolumeInterface;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class AudioRecoder implements AudioVolumeInterface
{
    protected AudioRecord recorder ; 
    protected int         bufSize ;
    protected byte []     byteBuffer ;
    private static int SAMPLE_RATE_IN_HZ = 8000;
    public AudioRecoder()
    {
    	
    	bufSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
    	recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufSize);
    	byteBuffer = new byte [bufSize] ;
		Log.i("RECORDER","start "+recorder.getState());
    }
    public float getVolume()
    {
    	if(recorder.getRecordingState()!=AudioRecord.RECORDSTATE_STOPPED)
    	{
    		    int sum=0; 
                int len=recorder.read(byteBuffer, 0, bufSize) ;
                for(int i=0;i<len;i++)
                {
                	sum+=byteBuffer[i]*byteBuffer[i];
                }
          return (sum/(float)len);
    	}
    	return 0;
    }
   public void start()
   {
	   recorder.startRecording();
   }
   
   public void stop()
   {
	   if (null == recorder) {
           Log.w("RECORD", "Recorder has not start yet");
           return;
       }
	   recorder.stop();
   }
   void dealNoise(byte[] lin,int len) {
	   int i;
	   byte[] data=new byte[len*2];
	   short tmp;
	  for (i = 0; i < len; i+=2) {
	  tmp=lin[i];
	   tmp<<=8;
	   tmp+=lin[i+1];
	   tmp>>=2;
	   lin[i]=(byte)((tmp&0xff00)>>8);
	   data[i+1]=(byte)(tmp&0xff);
	   }
   }
    public void release()
    {
    	recorder.release();
    	recorder=null;
    }

}

