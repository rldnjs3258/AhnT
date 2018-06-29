package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.gameframework.SpriteAnimation;


//생명, 폭탄 등의 아이템을 위한 클래스
public class Item extends SpriteAnimation {

    Rect m_BoundBox = new Rect();

    public Item(Bitmap bitmap) {
        super(bitmap);
    }
}
