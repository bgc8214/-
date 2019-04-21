package com.effective.apackage.packageeffective;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.effective.apackage.packageeffective.bean.Game;
import com.effective.apackage.packageeffective.domain.PackageValue;
import com.effective.apackage.packageeffective.utilities.JsonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameMainActivity extends AppCompatActivity {

    private static final String TAG = GameMainActivity.class.getSimpleName();

    private ImageView mBNSImage;
    private ImageView mBrownDustImage;
    private ImageView mEpicSevenImage;
    private ImageView mSummonersWarImage;
    private ImageView mBNSUpdate;
    private ImageView mBrounDustUpdate;
    private ImageView mSummonersWarUpdate;
    private ImageView mEpicSevenUpdate;
    private android.support.v7.widget.Toolbar toolbar;
    private final int DISABLE_UPDATE_DAY = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        //TODO 이미지 패스를 통한 동적 변경
        mBNSImage = (ImageView) findViewById(R.id.blade_and_soul);
        mBrownDustImage = (ImageView) findViewById(R.id.brown_dust);
        mEpicSevenImage = (ImageView) findViewById(R.id.epic_seven);
        mSummonersWarImage = (ImageView) findViewById(R.id.summoners_war);
        mBNSUpdate = (ImageView) findViewById(R.id.bns_update);
        mEpicSevenUpdate = (ImageView)findViewById(R.id.epic_update);
        mBrounDustUpdate = (ImageView)findViewById(R.id.brown_update);
        mSummonersWarUpdate = (ImageView)findViewById(R.id.summoners_update);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        List<Game> gameList = JsonUtil.getGameList();
        Date nowDate = new Date();
        for (Game game : gameList) {
            long dateDiff = (nowDate.getTime() - game.getUpdateAt().getTime()) / (1000 * 60 * 60 * 24);
            Log.d("GameMain",game.getName());

            Log.d("GameMain",dateDiff+"");
            switch (game.getName()) {
                case "블레이드앤소울":
                    if (dateDiff > DISABLE_UPDATE_DAY) {
                        mBNSUpdate.setVisibility(View.INVISIBLE);
                    }
                    setGameListener(mBNSImage, game.getName());
                    break;
                case "브라운더스트":
                    if (dateDiff > DISABLE_UPDATE_DAY) {
                        mBrounDustUpdate.setVisibility(View.INVISIBLE);
                    }
                    setGameListener(mBrownDustImage, game.getName());
                    break;
                case "에픽세븐":
                    if (dateDiff > DISABLE_UPDATE_DAY) {
                        mEpicSevenUpdate.setVisibility(View.INVISIBLE);
                    }
                    setGameListener(mEpicSevenImage, game.getName());
                    break;
                case "서머너즈워":
                    if (dateDiff > DISABLE_UPDATE_DAY) {
                        mSummonersWarUpdate.setVisibility(View.INVISIBLE);
                    }
                    setGameListener(mSummonersWarImage, game.getName());
                    break;

            }
        }
    }

    public void setGameListener(ImageView imageView, String gameName) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GameMain","ㄱㅔ임 클릭"+gameName);
                Context context = GameMainActivity.this;

                Class packageActivity = PackageListActivity.class;

                Intent intent = new Intent(context, packageActivity);

                intent.putExtra(Intent.EXTRA_TEXT, gameName);

                startActivity(intent);
            }
        });
    }

}
