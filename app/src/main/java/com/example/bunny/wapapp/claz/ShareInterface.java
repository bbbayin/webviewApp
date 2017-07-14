package com.example.bunny.wapapp.claz;

/**
 * Created by Administrator on 2017/7/12.
 */

public interface ShareInterface {
    void executeShareAction(String paramString);

    void executeShareAction(String shareTarget, String message);

    void cancelShareAction(String shareTarget);
}
