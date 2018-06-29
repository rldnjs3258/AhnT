package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.gameframework.SpriteAnimation;


//생명, 폭탄 등의 아이템을 위한 클래스
public class Item extends SpriteAnimation {
    public int m_score = 0;
    Rect m_BoundBox = new Rect();
    public boolean bOut = false;
    public Item(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        m_y += 2;
        if (m_y > 350) bOut = true;
        m_BoundBox .set ( m_x, m_y, m_x + 51, m_y + 51 );
    }

    void GetItem(){

    }

}
