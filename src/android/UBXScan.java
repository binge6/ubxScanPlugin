package cn.bg2it.pda.ubxScan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.util.Log;

/**
 * 优博讯扫码
 * @author yons
 * @date 2020/05/24
 */
public class UBXScan {
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;
    private ScanManager scanManager;
    private Context context;
    private OnScanListener onScanListener;

    public UBXScan(){
        initScan();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodeLen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG,0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG,(byte) 0);
            String barcodeStr = new String(barcode,0,barcodeLen);
            Log.i("debug","--codeType--"+temp);
            if (onScanListener!=null){
                onScanListener.getCode(barcodeStr);
            }
        }
    };

    private void initScan(){
        scanManager = new ScanManager();
        scanManager.openScanner();
        scanManager.switchOutputMode(0);
    }

    /**
     * 监听
     * @param context
     * @param listener
     */
    public void setOnScanListener(Context context,OnScanListener listener){
        this.onScanListener = listener;
        this.context = context;

        IntentFilter filter = new IntentFilter();
        int[] idBuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME,PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] valueBuf = scanManager.getParameterString(idBuf);
        if (valueBuf != null && valueBuf[0]!=null && !"".equals(valueBuf[0])){
            filter.addAction(valueBuf[0]);
        }else {
            filter.addAction(SCAN_ACTION);
        }
        this.context.registerReceiver(broadcastReceiver,filter);
    }

    /**
     * 销毁
     */
    public void destroy(){
        if (scanManager!=null){
            scanManager.stopDecode();
        }
        if (broadcastReceiver != null){
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    /**
     * 扫码
     */
    public void startScan(){
        scanManager.startDecode();
    }

    public interface OnScanListener {
        void getCode(String code);
    }
}
