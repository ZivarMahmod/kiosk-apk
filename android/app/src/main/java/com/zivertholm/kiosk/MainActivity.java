package com.zivertholm.kiosk;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.Bridge;
import android.os.Bundle;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hantera swish:// och andra custom URL-scheman i WebView
        this.bridge.getWebView().setWebViewClient(new com.getcapacitor.BridgeWebViewClient(this.bridge) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                String scheme = request.getUrl().getScheme();

                // Öppna Swish (och andra appar) via Android Intent
                if (scheme != null && !scheme.equals("https") && !scheme.equals("http") && !scheme.equals("capacitor")) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (Exception e) {
                        // Appen inte installerad
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }
}
