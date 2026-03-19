package com.zivertholm.kiosk;

import com.getcapacitor.BridgeActivity;
import android.os.Bundle;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(SwishPlugin.class);
        super.onCreate(savedInstanceState);
    }
}
