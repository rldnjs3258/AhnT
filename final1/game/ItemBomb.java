package com.example.user.final2.game;

import android.graphics.Rect;

import com.example.user.final2.R;
import com.example.user.final2.framework.AppManager;

import java.util.ArrayList;

public class ItemBomb extends Item {

    Rect m_ExplosionBox = new Rect();

    public ItemBomb(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.item4));
        this.initSpriteData(this.getBitmapWidth()/4,this.getBitmapHeight(),3,4);
        m_x=x;
        m_y=y;
        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight());
        m_ExplosionBox.set(m_x - 50,m_y - 50,m_x + 150, m_y + 150);//폭발 범위 설정
    }

    @Override
    public void GetItem() {
        ArrayList<Enemy> enemylist = AppManager.getInstance().m_gameState.m_enemylist;

        for (int i = enemylist.size()-1; i>=0; i--){
            if (CollisionManager.CheckBoxToBox( m_ExplosionBox, enemylist.get(i). m_BoundBox)) { //폭발 범위 내의 적 모두 제거
                AppManager.getInstance().m_gameState.m_explist.add(new EffectExplosion(enemylist.get(i).getX(), enemylist.get(i).getY()));
                enemylist.remove(i);
            }
        }
    }
}
