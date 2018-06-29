package com.example.Game;

import com.example.gameframework.AppManager;
import com.example.gameframework.R;
import com.example.gameframework.SpriteAnimation;

//게임이 폭발하는 이미지 클래스다.
//애니메이션이니 SpriteAnimation을 상속받는다.
public class EffectExplosion extends SpriteAnimation{
    public EffectExplosion (int x, int y){
        super(AppManager.getInstance().getBitmap(R.drawable.explosion)); //폭발을 그린다.
        this.initSpriteData(getBitmapWidth()/6, getBitmapHeight(), 3, 6); //이미지를 나눈다.
        m_x = x;
        m_y = y;

        mbReplay = false; //이 애니메이션은 반복되지 않는다!
    }
}
