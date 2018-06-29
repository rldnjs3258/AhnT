package com.example.Game;

import com.example.gameframework.AppManager;
import com.example.gameframework.R;

//시간 약
public class ItemAddTime extends Item {
    public ItemAddTime (int x, int y){
        super (AppManager.getInstance().getBitmap(R.drawable.item2));
        this.initSpriteData(this.getBitmapWidth()/4, this.getBitmapHeight(), 3, 4);
        m_x = x;
        m_y = y;
    }

    @Override
    void GetItem(){
        AppManager.getInstance().m_gameState.CurrentTime += 3000; //시간 약 먹으면 3초 추가
    }
}
