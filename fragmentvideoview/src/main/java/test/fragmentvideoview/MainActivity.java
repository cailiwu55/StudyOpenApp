package test.fragmentvideoview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MainActivity extends FragmentActivity {
    private View mRootView;
    private FrameLayout mFrameLayout;
    private Button mFullScreen;
    private boolean isFullScreen;
    private VideoFragment mVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRootView = findViewById(R.id.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.container);
        mFullScreen = (Button) findViewById(R.id.fullscreen_btn);
        mFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFullScreen) {
                    mFrameLayout.setLayoutParams(new RelativeLayout.LayoutParams(300, 200));
                } else {
                    mFrameLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                }
                mVideoFragment.updateSize();
                isFullScreen = !isFullScreen;
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mVideoFragment = new VideoFragment();
        transaction.replace(R.id.container, mVideoFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (isFullScreen) {
            isFullScreen = false;
            mFrameLayout.setLayoutParams(new RelativeLayout.LayoutParams(300, 200));
            mVideoFragment.updateSize();
            return;
        }
        super.onBackPressed();
    }
}

@SuppressLint("ValidFragment")
class VideoFragment extends Fragment {
    private VideoView mVideoView;

    public VideoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_video, container, false);
        mVideoView = (VideoView) v.findViewById(R.id.videoview);
        mVideoView.setVideoPath("http://192.168.10.213:8080/10.mp4");

        mVideoView.start();

        return v;
    }

    public void updateSize() {
        mVideoView.getHolder().setSizeFromLayout();
    }
}