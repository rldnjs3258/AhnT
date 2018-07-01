package com.example.user.final2.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.final2.R;
import com.example.user.final2.framework.GameActivity;
import com.example.user.final2.game.DBHelper;


public class EndActivity extends AppCompatActivity {
    DBHelper m_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        m_helper = new DBHelper(getApplicationContext(), "RankBoard.db", null, 1);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.endstate);

        final TextView rankboard = (TextView) findViewById(R.id.rankboard);

        final EditText name = (EditText) findViewById(R.id.name);
        final TextView score = (TextView) findViewById(R.id.score);
        rankboard.setText(m_helper.getResult());

        Button replay=(Button) findViewById(R.id.replay);
        Button insert = (Button) findViewById(R.id.insert);
        //데이터 삽입.
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = name.getText().toString();
                int data2 = Integer.parseInt(score.getText().toString());
                Log.e("1", "" + data1 + "\n");
                Log.e("2", "" + data2 + "\n");
                m_helper.insert(data1, data2);
                //데이터 출력
                rankboard.setText(m_helper.getResult());
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),GameActivity.class));
            }
        });

    }

}
