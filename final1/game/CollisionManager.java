package com.example.user.final2.game;

import android.graphics.Rect;


//좌표 충돌을 체크한다.
public class CollisionManager{
    public static boolean CheckBoxToBox(Rect _rt1, Rect _rt2){ //좌표 충돌에 대한 값을 boolean으로 처리한다.
        if (_rt1.right > _rt2.left //right, left, top, bottom 좌표가 박스끼리 만나나 체크한다.
                && _rt1.left < _rt2.right
                && _rt1.top < _rt2.bottom
                && _rt1.bottom >_rt2.top){
            return true;
        }
        return false;
    }
}
