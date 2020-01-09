package com.acelost.demo.balloon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.acelost.balloon.Balloon;
import com.acelost.balloon.icon.BalloonIcon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.add_balloon_btn).setOnClickListener(new View.OnClickListener() {
            private int i = 0;
            @Override
            public void onClick(final View v) {
                Balloon.make("#" + i + " balloon")
                        .duration(2000)
                        .id("1")
                        .show();
                i++;
            }
        });
        findViewById(R.id.remove_balloon_btn).setOnClickListener(new View.OnClickListener() {
            private int i = 0;
            @Override
            public void onClick(final View v) {
                Balloon.dismiss(i%5 + "");
                i++;
            }
        });
        initSamples();
    }

    private void initSamples() {
        Balloon.make("ShortText #1")
                //.duration(2000)
                .show();
        Balloon.make("ShortText #2")
                .icon(BalloonIcon.notification())
                .show();
        Balloon.make("ShortText #3")
                .icon(BalloonIcon.info())
                .show();
        Balloon.make("ShortText #4")
                .icon(BalloonIcon.warning())
                .show();
        Balloon.make("ShortText #5")
                .cancellable(false)
                .show();
        Balloon.make("ShortTitle", "ShortText #6")
                .icon(BalloonIcon.notification())
                .show();
        Balloon.make("Looooooooooooooooooooooong title", "Looooooooooo ooooo ooooooooo oooooooo ng texxxxxxxxxxxxxt!")
                .show();
        Balloon.make("Crash this shit!")
                .action("Crash", () -> {
                    //throw new RuntimeException();
                })
                .show();
    }
}
