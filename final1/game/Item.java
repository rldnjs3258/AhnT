package com.example.user.final2.game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.user.final2.framework.SpriteAnimation;


//생명, 폭탄 등의 아이템을 위한 클래스
public class Item extends SpriteAnimation {

    Rect m_BoundBox = new Rect();
    long m_itemTime = 0;
    public boolean bOut = false;

    public Item(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        m_itemTime +=5;
        if(m_itemTime>800)bOut=true;//약 5초
    }

    public void GetItem(){
    }
}
