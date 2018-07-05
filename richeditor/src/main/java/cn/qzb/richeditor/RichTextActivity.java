package cn.qzb.richeditor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class RichTextActivity extends AppCompatActivity implements RichEditor.OnDecorationStateListener, RichEditor.OnTextChangeListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onTextChange(String text) {

    }

    @Override
    public void onStateChangeListener(String text, List<RichEditor.Type> types) {

    }


}
