package com.example.gameframework;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

//SingleTone을 이용하여 프로젝트 어디서나 접근가능하게 만듦
public class SoundManager {
    //멤버 변수
    private SoundPool m_SoundPool; //안드로이드에서 지원하는 사운드풀
    private HashMap m_SoundPoolMap; //불러온 사운드의 아이디 값을 지정
    private AudioManager m_AudioManager; //사운드 관리를 위한 오디오 매니저
    private Context m_Activity; //애플리케이션의 컨텍스트 값

    public void init(Context _context){
        //멤버 변수 생성과 초기화
        m_SoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        m_SoundPoolMap = new HashMap();
        m_AudioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
        m_Activity = _context;
    }

    public void addSound(int _index, int _soundID){
        int id = m_SoundPool.load(m_Activity, _soundID, 1); //사운드를 로드
        m_SoundPoolMap.put(_index, id); //해시맵에 아이디 값을 받아온 인덱스 저장
    }

    public void play(int _index){
        //사운드 재생
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, 0, 1f);
    }

    public void playLooped(int _index){
        //사운드 반복 재생
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer) m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, -1, 1f);
    }

    private static SoundManager s_instance;

    public static SoundManager getinstance(){
        if(s_instance == null) s_instance = new SoundManager();
        return s_instance;
    }
}