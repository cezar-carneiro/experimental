package com.oproom.service;

import com.oproom.model.Clipboard;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public interface IClipboardService {

    Clipboard getClipboard();
    void setClipboard(Clipboard clipboard);
}
