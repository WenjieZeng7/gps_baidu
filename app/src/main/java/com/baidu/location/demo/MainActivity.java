package com.baidu.location.demo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.baidu.baidulocationdemo.R;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

//mqtt
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/***
 * 本类代码同定位业务本身无关，负责实现列表
 *
 * @author baidu
 *
 */
public class MainActivity extends Activity {
//    //MQTT
//    private final String TAG = "AiotMqtt";
//    /* 设备三元组信息 */
//    final private String PRODUCTKEY = "gmll0B3WENe";
//    final private String DEVICENAME = "test";
//    final private String DEVICESECRET = "81690e1ee1a86b12ca73b42378329ddf";
//
//    /* 自动Topic, 用于上报消息 */
//    final private String PUB_TOPIC = "/" + PRODUCTKEY + "/" + DEVICENAME + "/user/update";
//    /* 自动Topic, 用于接受消息 */
//    final private String SUB_TOPIC = "/" + PRODUCTKEY + "/" + DEVICENAME + "/user/get";
//
//    /* 阿里云Mqtt服务器域名 */
//    final String host = "tcp://" + PRODUCTKEY + ".iot-as-mqtt.cn-shanghai.aliyuncs.com:443";
//    //    final String host = "iot-06z00e2ppeme0ap.mqtt.iothub.aliyuncs.com";
//    private String clientId;
//    private String userName;
//    private String passWord;
//
//    MqttAndroidClient mqttAndroidClient;
//    ///MQTT


    private final int SDK_PERMISSION_REQUEST = 127;
    private ListView FunctionList;
    private String permissionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_list);
        FunctionList = (ListView) findViewById(R.id.functionList);
        FunctionList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        // after andrioid m,must request Permiision on runtime
        getPersimmions();


//        //MQTT
//        /* 获取Mqtt建连信息clientId, username, password */
//        AiotMqttOption aiotMqttOption = new AiotMqttOption().getMqttOption(PRODUCTKEY, DEVICENAME, DEVICESECRET);
//        if (aiotMqttOption == null) {
//            Log.e(TAG, "device info error");
//        } else {
//            clientId = aiotMqttOption.getClientId();
//            userName = aiotMqttOption.getUsername();
//            passWord = aiotMqttOption.getPassword();
//        }
//
//        /* 创建MqttConnectOptions对象并配置username和password */
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setUserName(userName);
//        mqttConnectOptions.setPassword(passWord.toCharArray());
//
//
//        /* 创建MqttAndroidClient对象, 并设置回调接口 */
//        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), host, clientId);
//        mqttAndroidClient.setCallback(new MqttCallback() {
//            @Override
//            public void connectionLost(Throwable cause) {
//                Log.i(TAG, "connection lost");
//            }
//
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception {
//                Log.i(TAG, "topic: " + topic + ", msg: " + new String(message.getPayload()));
//            }
//
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//                Log.i(TAG, "msg delivered");
//            }
//        });
//
//        /* Mqtt建连 */
//        try {
//            mqttAndroidClient.connect(mqttConnectOptions,null, new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Log.i(TAG, "connect succeed");
//
//                    subscribeTopic(SUB_TOPIC);
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Log.i(TAG, "connect failed");
//                }
//            });
//
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//        ///MQTT
    }

//    /**
//     * 订阅特定的主题
//     * @param topic mqtt主题
//     */
//    public void subscribeTopic(String topic) {
//        try {
//            mqttAndroidClient.subscribe(topic, 0, null, new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Log.i(TAG, "subscribed succeed");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Log.i(TAG, "subscribed failed");
//                }
//            });
//
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 向默认的主题/user/update发布消息
//     * @param payload 消息载荷
//     */
//    public void publishMessage(String payload) {
//        try {
//            if (mqttAndroidClient.isConnected() == false) {
//                mqttAndroidClient.connect();
//            }
//
//            MqttMessage message = new MqttMessage();
//            message.setPayload(payload.getBytes());
//            message.setQos(0);
//            mqttAndroidClient.publish(PUB_TOPIC, message,null, new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Log.i(TAG, "publish succeed!");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Log.i(TAG, "publish failed!");
//                }
//            });
//        } catch (MqttException e) {
//            Log.e(TAG, e.toString());
//            e.printStackTrace();
//        }
//    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
             */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }
        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        FunctionList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Class<?> TargetClass = null;
                switch (arg2) {
                    case 0:
                        TargetClass = LocationActivity.class;
                        break;
                    case 1:
                        TargetClass = LocationOption.class;
                        break;
                    case 2:
                        TargetClass = QuestActivity.class;
                        break;
                    default:
                        break;
                }
                if (TargetClass != null) {
                    Intent intent = new Intent(MainActivity.this, TargetClass);
                    intent.putExtra("from", 0);
                    startActivity(intent);
                }
            }
        });
    }

//    /**
//     * MQTT建连选项类，输入设备三元组productKey, deviceName和deviceSecret, 生成Mqtt建连参数clientId，username和password.
//     */
//    class AiotMqttOption {
//        private String username = "";
//        private String password = "";
//        private String clientId = "";
//
//        public String getUsername() { return this.username;}
//        public String getPassword() { return this.password;}
//        public String getClientId() { return this.clientId;}
//
//        /**
//         * 获取Mqtt建连选项对象
//         * @param productKey 产品秘钥
//         * @param deviceName 设备名称
//         * @param deviceSecret 设备机密
//         * @return AiotMqttOption对象或者NULL
//         */
//        public AiotMqttOption getMqttOption(String productKey, String deviceName, String deviceSecret) {
//            if (productKey == null || deviceName == null || deviceSecret == null) {
//                return null;
//            }
//
//            try {
//                String timestamp = Long.toString(System.currentTimeMillis());
//
//                // clientId
//                this.clientId = productKey + "." + deviceName + "|timestamp=" + timestamp +
//                        ",_v=paho-android-1.0.0,securemode=2,signmethod=hmacsha256|";
//
//                // userName
//                this.username = deviceName + "&" + productKey;
//
//                // password
//                String macSrc = "clientId" + productKey + "." + deviceName + "deviceName" +
//                        deviceName + "productKey" + productKey + "timestamp" + timestamp;
//                String algorithm = "HmacSHA256";
//                Mac mac = Mac.getInstance(algorithm);
//                SecretKeySpec secretKeySpec = new SecretKeySpec(deviceSecret.getBytes(), algorithm);
//                mac.init(secretKeySpec);
//                byte[] macRes = mac.doFinal(macSrc.getBytes());
//                password = String.format("%064x", new BigInteger(1, macRes));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//
//            return this;
//        }
//    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("基础定位功能");
        data.add("配置定位参数");
        data.add("常见问题说明");
        return data;
    }
}
