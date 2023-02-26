package com.example.myrtspplayer;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myrtspplayer.databinding.ActivityMainBinding;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    LibVLC libVLC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        libVLC = new LibVLC(this);
        String url = getString(R.string.rtspUrl);
        Media media = new Media(libVLC, Uri.parse(url));
        media.addOption("--aout=opensles");
        media.addOption("--audio-time-stretch");
        media.addOption("-vvv"); // verbosity
        org.videolan.libvlc.MediaPlayer mediaPlayer = new org.videolan.libvlc.MediaPlayer(libVLC);
        mediaPlayer.setMedia(media);
        mediaPlayer.getVLCVout().setVideoSurface(binding.contentMain.videoView.getHolder().getSurface(), binding.contentMain.videoView.getHolder());
        mediaPlayer.getVLCVout().setWindowSize(binding.contentMain.videoView.getWidth(), binding.contentMain.videoView.getHeight());
        mediaPlayer.getVLCVout().attachViews();
        mediaPlayer.play();

    }
}