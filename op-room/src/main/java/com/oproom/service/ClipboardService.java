package com.oproom.service;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Looper;

import com.oproom.model.Clipboard;

/**
 * Created by Cezar Carneiro on 23/1/2018.
 */

public class ClipboardService extends BaseService {

    public ClipboardService(Context context) {
        super(context);
    }

    public Clipboard getClipboard() {
        ClipData clip = getClipboardManager().getPrimaryClip();
        if(clip.getItemCount() > 0){
            return new Clipboard(clip.getItemAt(0)
                    .coerceToText(mContext).toString());
        }
        return new Clipboard("");
    }

    public void setClipboard(Clipboard clipboard) {
        ClipData clip = ClipData.newPlainText("text", clipboard.getText());
        getClipboardManager().setPrimaryClip(clip);
    }

    private ClipboardManager getClipboardManager() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        return (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    }
}
