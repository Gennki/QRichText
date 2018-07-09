package cn.qzb.richeditor;

import android.graphics.Color;
import android.text.TextUtils;

public class RE {

    private static RichEditor mEditor = null;
    public static int fontColor = Color.BLACK;
    public static int fontBackGroundColor = Color.WHITE;
    public static boolean isBold = false;
    public static boolean isItalic = false;
    public static boolean isUnderline = false;
    public static int fontSize = 3;
    public static boolean isFocus = false;// 是否获取到焦点


    public static void init(RichEditor mEditor) {
        RE.mEditor = mEditor;
    }

    public static RichEditor getEditor() {
        return mEditor;
    }

    public static void setPlaceHolder(String placeHolder) {
        mEditor.setPlaceholder(placeHolder);
    }

    public static void setBold() {
        isBold = !isBold;
        mEditor.setBold();
    }

    public static void setItalic() {
        isItalic = !isItalic;
        mEditor.setItalic();
    }

    public static void setTextColor(int color) {
        fontColor = color;
        mEditor.setTextColor(color);
        reFreshState();
    }

    public static void setTextBackgroundColor(int color) {
        fontBackGroundColor = color;
        mEditor.setTextBackgroundColor(color);
        reFreshState();
    }

    /**
     * The size value must be between 1 and 7,
     * default value is 3
     *
     * @param fontSize
     */
    public static void setTextSize(int fontSize) {
        if (fontSize < 0 || fontSize > 7) {
            throw new IllegalStateException("The size value must be between 1 and 7");
        }
        RE.fontSize = fontSize;
        mEditor.setFontSize(fontSize);
        reFreshState();
    }

    public static String getHtml() {
        if (mEditor.getHtml() == null || (TextUtils.isEmpty(mEditor.getText()) && !mEditor.getHtml().contains("<img") && mEditor.getHtml().startsWith("<") && !mEditor.getHtml().contains("&nbsp"))) {
            return "";
        } else {
            return mEditor.getHtml();
        }
    }

    public static void setPadding(int left, int top, int right, int bottom) {
        mEditor.setPadding(left, top, right, bottom);
    }

    public static void insertImage(String url, String alt) {
        insertImage(url, alt, 100);
    }

    public static void insertImage(String url, String alt, int imageWidthPercent) {
        if (!isFocus) {
            mEditor.focusEditor();
        }
        mEditor.insertImage(url, alt, imageWidthPercent);
    }

    public static void setUnderLine() {
        isUnderline = !isUnderline;
        mEditor.setUnderline();
    }

    public static void reFreshState() {
        mEditor.refreshState();
    }
}
