package jp.wasabeef.richeditor;

import android.content.Context;
import android.view.View;

public class RE {

    private static int viewId;

    public static void init(int viewId) {
        RE.viewId = viewId;
    }

    public static RichEditor getEditor(Context context) {
        return (RichEditor) View.inflate(context, RE.viewId, null);
    }
}
