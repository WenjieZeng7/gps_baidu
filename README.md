# 实现功能
打开app，点击【基础定位功能】->【开始定位】，会将当前手机的位置信息上传到阿里云物联网平台。
通信采用mqtt-paho。
同时在阿里云物联网平台中，设置了信息流转，将这些经纬度地理位置信息，转存到阿里云的mysql数据库中。

# 实现方式
依托百度地图SDK进行开发。
加入了阿里云物联网平台的mqtt-paho。

# 实例
![Screenshot_20220222_161004](https://user-images.githubusercontent.com/47874610/155093794-25439739-2c75-4263-9380-74309cc87fb6.jpg)
![Screenshot_20220222_161011](https://user-images.githubusercontent.com/47874610/155093834-fc9672e8-8085-4cdc-9720-15a8a782558f.jpg)
