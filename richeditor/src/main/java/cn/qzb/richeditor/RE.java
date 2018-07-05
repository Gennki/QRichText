package cn.qzb.richeditor;

import android.content.Context;

public class RE {

    private static RichEditor mEditor = null;

    public static void init(Context context, RichEditor mEditor) {
        RE.mEditor = mEditor;
    }

    public static RichEditor getRichEditor() {
        return mEditor;
    }

    public static void setPlaceHolder(String placeHolder) {
        mEditor.setPlaceholder(placeHolder);
    }

    public static void setBold() {
        mEditor.setBold();

    }

    public static void setItalic() {
        mEditor.setItalic();
    }
}
