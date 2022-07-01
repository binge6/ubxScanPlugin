# cordova PDA扫描插件

插件根据优博讯提供的API开发，目前仅测试过优博讯手持终端

### 一、主要方法说明

**onBarcodeScanned()**  

设置扫描成功后的回调方法（建议初始页面时就设置回调）

```js
cordova.plugins.ubxScanPlugin.onBarcodeScanned((code) => {
        console.log('扫描结果：'+code);
})
```

**doScan()**

开始扫描（调用扫描探头扫描）

```js
cordova.plugins.ubxScanPlugin.doScan()
```

**isUBX()**

是否初始化成功（可以用来判断是否支持扫描）

```js
cordova.plugins.ubxScanPlugin.isUBX((res) => {
        console.log(res)//返回值： 1 已成功初始 0 未初始化
        if (res == '1') {
            // 已成功初始
        } else {
           // 未初始化
        }
});
```

#### 二、相关推荐

[android原生 PDA扫描开发](https://blog.binge430.cn/2020/08/19/android%E4%BC%98%E5%8D%9A%E8%AE%AFpda%E6%89%AB%E6%8F%8F%E5%BC%80%E5%8F%91.html)

[react native PDA扫描插件](https://github.com/Txiaomo/react-native-scancode)

[uni-app PDA扫描开发](https://ask.dcloud.net.cn/article/37294)
