package com.example.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.gameframework.AppManager;
import com.example.gameframework.GraphicObject;
import com.example.gameframework.IState;
import com.example.gameframework.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

//GameState를 설정한다.
//IState의 클래스를 상속 받는다.
public class GameState implements IState{
    Random randEnem = new Random();
    private BackGround m_background;
    private Player m_player;
    //private Enemy_1 enem = new Enemy_1();
    ArrayList<Enemy> m_enemylist = new ArrayList<Enemy>(); //적
    ArrayList<Missile_Player> m_pmslist = new ArrayList<Missile_Player>(); //플레이어의 미사일
    ArrayList<Missile> m_enemmslist = new ArrayList<Missile>(); //적의 미사일
    ArrayList<EffectExplosion> m_explist = new ArrayList<EffectExplosion>(); //폭발 이미지 배열
    long LastShoot = System.currentTimeMillis();

    long LastRegenEnemy = System.currentTimeMillis();

    public void MakeEnemy(){
        if (System.currentTimeMillis() - LastRegenEnemy >= 1000){ //마지막 생성된 적 이후 딜레이 후 생성되게 한다.
            LastRegenEnemy = System.currentTimeMillis();

            int enemtype = randEnem.nextInt(3); //적의 타입을 랜덤으로 생성되게 한다.
            Enemy enem = null;
            if (enemtype == 0)      enem = new Enemy_1();
            else if (enemtype == 1) enem = new Enemy_2();
            else if (enemtype == 2) enem = new Enemy_3();

            enem.setPosition(randEnem.nextInt(280), -60); //랜덤 포지션에서 나오게 한다.
            enem.movetype = randEnem.nextInt(3); //랜덤하게 이동되게 한다.

            m_enemylist.add(enem); //리스트에 적을 추가한다.
        }

    }

    @Override
    public void Destroy(){

    }

    //초기상태. 그림을 그린다.
    @Override
    public void Init() {
        m_player = new Player(AppManager.getInstance().getBitmap(R.drawable.player));
        //m_keypad = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.keypad));
        m_background = new BackGround(0);
        //m_keypad.setPosition(0, 460-120);

    }

    //Render는 그리는 것이다.
    @Override
    public void Render(Canvas canvas) { //그리는 순서 조심하기.
        m_background.Draw(canvas); //배경 먼저 그리기
        for (Missile pms:m_pmslist){ //리스트의 방법을 이용해서 배열을 모두 꺼낸다. 리스트의 좌표만을 이용해 나중에 충돌처리 할 수 있다.
            pms.Draw(canvas);
        }
        for (Missile enemms : m_enemmslist){
            enemms.Draw(canvas);
        }
        for (Enemy enem : m_enemylist){
            enem.Draw(canvas);
        }
        for (EffectExplosion exp : m_explist){ //폭발 배열을 그린다.
            exp.Draw(canvas);
        }
        m_player.Draw(canvas); //플레이어 그리기
        //m_keypad.Draw(canvas);

        //글씨를 '그려'준다.
        //페인트를 생성하는 이유는 캔버스 위에 이미지가 있는데, 글씨는 캔버스 위에 그려져서 안보일 수 있기 때문에 새로 생성한다.
        Paint p = new Paint();
        p.setTextSize(100); //글씨 크기
        p.setColor(Color.BLACK); //글씨 색
        canvas.drawText("남은 목숨:"+String.valueOf(m_player.getLife()), 0, 100, p);
    }

    //Update는 프레임마다 업데이트해서 그림에 움직임을 주는 것이다.
    @Override
    public void Update() {
        long GameTime = System.currentTimeMillis();
        m_player.Update(GameTime);
        m_background.Update(GameTime); //배경이 계속 움직이게 update 해준다.
        for (int i = m_pmslist.size()-1; i>=0; i--){ //배열을 포문으로 돌릴 때 맨 뒤 부터 없앤다. 스택 개념.
            Missile_Player pms = m_pmslist.get(i); //프레임마다 플레이어 미사일 리스트의 모든 미사일이 나오게 함
            pms.Update();
            if (pms.state == Missile.STATE_OUT) m_pmslist.remove(i);
        }
        for (int i = m_enemylist.size()-1; i>=0; i--){
            Enemy enem = m_enemylist.get(i);
            enem.Update(GameTime) ;
            if (enem.state == Enemy.STATE_OUT)  m_enemylist.remove(i);
        }
        for (int i = m_enemmslist.size()-1; i>=0; i--){
            Missile enemms  = m_enemmslist.get(i);
            enemms.Update();
            if (enemms.state == Missile.STATE_OUT) {m_enemmslist.remove(i);}
        }
        for (int i = m_explist.size() -1 ; i>=0; i--){ //리스트 안의 모든 폭발을 업데이트 한다.
            EffectExplosion exp = m_explist.get(i);
            exp.Update(GameTime);
            if (exp.getAnimationEnd()) m_explist.remove(i);
        }
        MakeEnemy(); //미사일, 적군의 위치가 매번 바뀌므로 매번 체크한다.
        CheckCollision(); //Update 할 때마다 CheckCollision을 해서 매번 체크한다.
    }

    //키 입력에 따른 플레이어 이동
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int x = m_player.getX();
        int y = m_player.getY();

        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) //왼쪽
            m_player.setPosition(x-1, y);
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) //오른쪽
            m_player.setPosition(x+1, y);
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) //위
            m_player.setPosition(x, y-1);
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) //아래
            m_player.setPosition(x, y+1);
        if (keyCode == KeyEvent.KEYCODE_SPACE) //스페이스 바를 눌렀을 때 미사일이 나오게 구현
            m_pmslist.add(new Missile_Player(x+10,y));
        return false; //일단 false로 두자. 어플 게임이므로
    }

    //마우스 입력에 따른 플레이어 이동
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = m_player.getX();
        int y = m_player.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE){ //마우스의 움직임이 있으면
            x = (int) event.getX(); //마우스 위치 x 받기
            y = (int) event.getY(); //마우스 위치 y 받기

            m_player.setPosition(x-100, y-100); //마우스의 위치를 마우스위치로 한다.

            if (System.currentTimeMillis() - LastShoot >= 1000){ //시스템 시간 밀리초 마다 실행되게 하기
                LastShoot = System.currentTimeMillis();
                //미사일 발사 로직
                m_pmslist.add(new Missile_Player(x-10,y-100));
            }
        }
        return true;
    }

    public void CheckCollision(){ //박스끼리 충돌처리를 체크한다.

        //플레이어와 적의 미사일을 체크한다.
        for (int i = m_enemmslist.size()-1; i>=0; i--){ //적의 미사일 리스트를
            if (CollisionManager.CheckBoxToBox(// 트루/false 체크한다.
                    m_player.m_BoundBox, //플레이어
                    m_enemmslist.get(i).m_BoundBox)){ //적의 미사일
                m_explist.add(new EffectExplosion(m_player.getX(), m_player.getY())); //플레이어의 위치에 폭발 리스트 추가.
                m_enemmslist.remove(i); //트루면 미사일을 지운다.
                m_player.destroyPlayer(); //트루면 플레이어를 destroy 한다. (생명 -1)
                if (m_player.getLife() <= 0) System.exit(0); //생명 0이면 종료 -> 스테이트 변화 등으로 바꿀 수 있음
            }
        }

        //적과 플레이어의 미사일을 체크한다.
        for (int i = m_pmslist.size()-1; i>=0; i--){ //플레이어의 미사일 리스트
            for (int j = m_enemylist.size()-1; j>=0; j--){ //적 리스트
                if(CollisionManager.CheckBoxToBox(m_pmslist.get(i).m_BoundBox, //플레이어 미사일 리스트와
                        m_enemylist.get(j).m_BoundBox)){ //적 리스트를 비교한다.
                    m_explist.add(new EffectExplosion(m_enemylist.get(j).getX(), m_enemylist.get(j).getY())); //적의 위치에 폭발 리스트를 추가.
                    m_pmslist.remove(i); //트루면 미사일을 지운다.
                    m_enemylist.remove(j); //트루면 적을 지운다.
                    return;
                }
            }
        }
    }

    public GameState(){
        AppManager.getInstance().m_gameState = this; //관리하고 있는 AppManager에서 게임 상황을 현재 상황으로 하라.
    }
}
