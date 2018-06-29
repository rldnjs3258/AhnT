package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.R;
import com.example.gameframework.AppManager;
import com.example.gameframework.GraphicObject;

public class BackGround extends GraphicObject{
    //시차 스크롤링 이용
    //첫번째 레이어
    static final float SCROLL_SPEED = 0.2f;
    private float m_scroll = -2000 + 480;

    Bitmap m_layer2;

    //두번째 레이어
    static final float SCROLL_SPEED_2 = 0.2f;
    private float m_scroll_2 = -2000 + 480;

    //backtype에 따라 배경 변하게 해줌.
    public BackGround(int backtype){
        super(null);
        if (backtype == 0)
            m_bitmap = AppManager.getInstance().getBitmap(R.drawable.background1);
        else if (backtype == 1)
            m_bitmap = AppManager.getInstance().getBitmap(R.drawable. background2);
        m_layer2 = AppManager.getInstance().getBitmap(R.drawable. background_2); //시차 레이어
        setPosition(0, (int) m_scroll); //지속적으로 Y 값을 증가시킨다.
    }

    void Update(long GameTime){ //업데이트에서의 순서는 상관 없다.
        m_scroll = m_scroll + SCROLL_SPEED; //지속적으로 Y 값을 증가시켜서 아래로 내려가게 한다.
        if (m_scroll >= 0) m_scroll = 0;
        setPosition(0, (int)m_scroll); //배경 이미지의 이동은 y값이 증가하게 한다. 이미지가 올라가는 것처럼 보이기 위함이다.
        m_scroll_2 = m_scroll_2 + SCROLL_SPEED_2;
        if (m_scroll_2>=0) m_scroll_2 = 0;
    }

    @Override //부모로 부터 물려 받은 것을 다르게 쓴다.
    public void Draw(Canvas canvas){
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
        canvas.drawBitmap(m_layer2, m_x, m_scroll_2, null);
    }
}
