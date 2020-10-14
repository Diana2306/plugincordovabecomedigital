package com.become.digital.iv;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeCallBackManager;
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeInterfaseCallback;
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeResponseManager;
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.LoginError;
import com.becomedigital.sdk.identity.becomedigitalsdk.models.BDIVConfig;
import com.becomedigital.sdk.identity.becomedigitalsdk.models.ResponseIV;


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static androidx.navigation.Navigation.findNavController;

/**
 * This class echoes a string called from JavaScript.
 */
public class plugin_iv_become_digital extends CordovaPlugin {
    private BecomeCallBackManager mCallbackManager = BecomeCallBackManager.createNew();
    public static final String INIT = "init";
    public static final String COOL_METHOD = "coolMethod";
    public static final String CANCEL = "Cancel";
    public static final String SET_BECOME_CALLBACK = "setBecomeCallback";
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        switch (action) {
            case COOL_METHOD: {
                String message = args.getString(0);
                this.coolMethod(message, callbackContext);
                return true;
            }
            case INIT: {
                JSONObject data = args.getJSONObject(0);
                this.init(data, callbackContext);
                return true;
            }
            case SET_BECOME_CALLBACK: {
                this.setBecomeCallback(callbackContext);
                return true;
            }
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void init(final JSONObject data, CallbackContext callbackContext) {
        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                BecomeResponseManager.getInstance().startAutentication(cordova.getActivity(),
                        new BDIVConfig(data.getString ("clientId"),
                        data.getString ("clientSecret"),
                        data.getString ("contractId"),
                        data.getString ("validatiopnTypes") ,
                        data.getBoolean ("useGallery") ,
                        null
                        ));
                    }catch(JSONException e){
                       
                     }
            }
        });
    }


    private void setBecomeCallback(CallbackContext callbackContext) {
        BecomeResponseManager.getInstance().registerCallback(mCallbackManager, new BecomeInterfaseCallback() {
            @Override
            public void onSuccess(final ResponseIV responseIV) {
                String id = responseIV.getId();
                String created_at = responseIV.getCreated_at();
                String company = responseIV.getCompany();
                String fullname = responseIV.getFullname();
                String birth = responseIV.getBirth();
                String document_type = responseIV.getDocument_type();
                String document_number = responseIV.getDocument_number();
                Boolean face_match = responseIV.getFace_match();
                Boolean template = responseIV.getTemplate();
                Boolean alteration = responseIV.getAlteration();
                Boolean watch_list = responseIV.getWatch_list();
                String comply_advantage_result = responseIV.getComply_advantage_result();
                String comply_advantage_url = responseIV.getComply_advantage_url();
                String verification_status = responseIV.getVerification_status();
                String message = responseIV.getMessage();
                Integer responseStatus = responseIV.getResponseStatus();
               
                        JSONObject data = new JSONObject();

                        try {
                            data.put("id", id);
                            data.put("created_at", created_at);
                            data.put("company", company);
                            data.put("fullname", fullname);
                            data.put("birth", birth);
                            data.put("document_type", document_type);
                            data.put("document_number", document_number);
                            data.put("face_match", face_match);
                            data.put("template", template);
                            data.put("alteration", alteration);
                            data.put("watch_list", watch_list);
                            data.put("comply_advantage_result", comply_advantage_result);
                            data.put("comply_advantage_url", comply_advantage_url);
                            data.put("verification_status", verification_status);
                            data.put("message", message);
                            data.put("responseStatus:", responseStatus);
                            PluginResult result = new PluginResult(PluginResult.Status.OK, data.toString());
                            result.setKeepCallback(true);
                             callbackContext.sendPluginResult(result);
                        
                        } catch (JSONException e) {
                            PluginResult result = new PluginResult(PluginResult.Status.ERROR, CANCEL);
                            result.setKeepCallback(true);
                            callbackContext.sendPluginResult(result);
                        }
                
            }

            @Override
            public void onCancel() {
                PluginResult result = new PluginResult(PluginResult.Status.ERROR, CANCEL);
                result.setKeepCallback(true);
                callbackContext.sendPluginResult(result);
            }

            @Override
            public void onError(LoginError pLoginError) {
                callbackContext.error(pLoginError.getMessage());
            }
        });
    }


}
