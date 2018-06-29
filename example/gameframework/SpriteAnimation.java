package com.example.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

//하나의 이미지에 프레임별로 사진이 있을 때, 사진을 잘라서 차례대로 보여준다.
public class SpriteAnimation extends GraphicObject{
    private Rect m_rect; //Rect 정보를 받아서 네모로 잘라준다.
    private int m_fps; //초당 프레임
    private int m_iFrames; //프레임 개수
    private int m_currentFrame; //최근 프레임
    private int m_spriteWidth;
    private int m_spriteHeight;
    private long m_frameTimer;
    protected boolean mbReplay = true;
    protected boolean mbEnd = false;

    public int getBitmapWidth() {
        return (int)m_bitmap.getWidth();
    } //사진을 프레임에 맞게 자른다.
    public int getBitmapHeight() {
        return (int)m_bitmap.getHeight();
    }
    //이미지를 네모로 잘라준다.
    public SpriteAnimation(Bitmap bitmap){
        super(bitmap);
        m_rect = new Rect(0, 0, 0, 0); //네모의 멤버 변수 초기화
        m_currentFrame = 0; //프레임 갯수 (이미지 안에 자를 프레임이 3개면 3이다.)
        m_frameTimer = 0; //초당 프레임
    }

    //데이터의 기본 정보를 설정한다.
    //기본 정보를 설정한 후 GameView에서 좌표값, 프레임만 바꿔서 이용할 수 있다.
    public void initSpriteData(int _width, int _height, int _fps, int iFrame){
        m_spriteWidth = _width;
        m_spriteHeight = _height;
        m_rect.top = 0;     m_rect.bottom = m_spriteHeight;
        m_rect.left = 0;    m_rect.right = m_spriteWidth;
        m_fps = 1000/_fps; //밀리초 단위 프레임
        m_iFrames = iFrame;
    }

    @Override
    public void Draw(Canvas canvas){
        Rect dest = new Rect(m_x, m_y, m_x+m_spriteWidth, m_y+m_spriteHeight);
        canvas.drawBitmap(m_bitmap, m_rect, dest, null);
    }

    //프레임의 너비만큼을 계속 순환하게 업데이트 해준다.
    public void Update(long gameTime){
        if (!mbEnd) {
            if (gameTime > m_frameTimer + m_fps) { //gameTime은 밀리세컨드로 GameView에서 설정했다.
                m_frameTimer = gameTime;
                m_currentFrame += 1; //프레임의 수가 일정 시간마다 증가한다.
                if (m_currentFrame >= m_iFrames) {
                    if (mbReplay) //반복되는 애니메이션
                        m_currentFrame = 0;//프레임이 계속 순환하게 일정 프레임이 되면 다시 0이 되게 했다. (반복)
                    else //반복 안되고 종료되는 애니메이션
                        mbEnd = true;
                }
            }
        }
        m_rect.left = m_currentFrame * m_spriteWidth;
        m_rect.right = m_rect.left + m_spriteWidth;
    }

    public boolean getAnimationEnd(){ //순환하지 않는 애니메이션일 경우 메모리/리스트에서도 이미지가 없어지게 함.
        return mbEnd;
    }

}
