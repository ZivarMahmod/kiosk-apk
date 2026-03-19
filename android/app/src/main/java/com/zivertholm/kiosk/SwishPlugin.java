package com.zivertholm.kiosk;

import android.content.Intent;
import android.net.Uri;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Swish")
public class SwishPlugin extends Plugin {

    @PluginMethod
    public void openPayment(PluginCall call) {
        String url = call.getString("url");
        if (url == null) {
            call.reject("URL saknas");
            return;
        }
        try {
            // Försök öppna Swish direkt med full URL inkl. betaldata
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setPackage("se.bankgirot.swish");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().startActivity(intent);
            call.resolve();
        } catch (Exception e) {
            try {
                // Fallback utan package-begränsning
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                call.resolve();
            } catch (Exception e2) {
                call.reject("Kunde inte öppna Swish: " + e2.getMessage());
            }
        }
    }
}
