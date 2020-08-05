package cn.bg2it.pda.ubxScan;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * This class echoes a string called from JavaScript.
 */
public class ubxScanPlugin extends CordovaPlugin {

    private UBXScan ubxscan;
    private Context context;
    private CallbackContext scanCallbackContext;

    @Override
    protected void pluginInitialize() {
        context = this.cordova.getActivity().getApplicationContext();
        ubxscan = new UBXScan();
        // 扫码事件
        ubxscan.setOnScanListener(context, new UBXScan.OnScanListener() {
            @Override
            public void getCode(final String code) {
                if (null != scanCallbackContext) {
                    PluginResult result = new PluginResult(PluginResult.Status.OK, code);
                    result.setKeepCallback(true);
                    scanCallbackContext.sendPluginResult(result);
                }
            }
        });
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("doScan")) {
            if (null != ubxscan) {
                ubxscan.startScan();
            }
            callbackContext.success();
            return true;
        } else if (action.equals("onBarcodeScanned")) {
            this.scanCallbackContext = callbackContext;
            return true;
        } else if("isUBX".equals(action)){
            if(null!=ubxscan){
                callbackContext.success("1");
            }else{
                callbackContext.success("0");
            }
            return true;
        }
        return false;
    }
}
