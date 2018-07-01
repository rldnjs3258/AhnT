package com.example.user.final2.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

//그림을 나타내는 클래스
//캔버스를 통해 설정되어 있는 비트맵, 좌표값을 이용해서 그림을 그려줌
public class GraphicObject {
    public Bitmap m_bitmap; //비트맵
    public int m_x; //x좌표
    public int m_y; //y좌표

    //그림을 받아서 0,0의 위치에 그린다.
    public GraphicObject(Bitmap bitmap){
        m_bitmap = bitmap;
        m_x = 0;
        m_y = 0;
    }

    //setPosition으로 좌표값을 바꿔준다. (파라미터로 좌표값을 받는다.)
    public void setPosition(int x, int y){
        m_x = x;
        m_y = y;
    }

    //이미지 그림
    public void Draw(Canvas canvas){
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }

    //X, Y 각 좌표 반환
    public int getX(){return m_x;}
    public int getY(){return m_y;}
}
