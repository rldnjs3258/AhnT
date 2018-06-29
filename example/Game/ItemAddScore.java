package com.example.Game;

import com.example.gameframework.AppManager;
import com.example.gameframework.R;

public class ItemAddScore extends Item {
    public ItemAddScore (int x, int y) {
        super (AppManager.getInstance( ).getBitmap(R.drawable. item1));
        this .initSpriteData(this.getBitmapWidth()/4, this.getBitmapHeight(), 3, 4);
        m_x = x;
        m_y = y;
    }

    @Override
    void GetItem( ) {
        m_score += 100;
    }
}
