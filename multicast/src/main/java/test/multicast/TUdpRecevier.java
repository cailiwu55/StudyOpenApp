package test.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class TUdpRecevier implements Runnable {
    private static final String BROADCAST_IP = "230.0.0.1"; // 组播地址
    public static final int BROADCAST_PORT = 8800; // 组播端口
    private static final int DATA_LEN = 4096; // 最大数据包大小
    protected static final String TAG = "TUdpRecevier";
    private MulticastSocket socket = null; // MulticastSocket实例
    private InetAddress broadcastAddress = null; // Internet地址
    byte[] inBuff = new byte[DATA_LEN]; // 接收消息的数组
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length); // 准备接收数据的DatagramPacket对象

    public Boolean IsActived = false; // 接收状态
    // public Handler RecMsgHabndler ; //接收到消息后的通知句柄

    private void DoInit() throws IOException {
        Log.i(TAG, "DoInit-->init");
        socket = new MulticastSocket(BROADCAST_PORT);
        broadcastAddress = InetAddress.getByName(BROADCAST_IP);
        socket.joinGroup(broadcastAddress);
        socket.setLoopbackMode(false);
    }

    public void DoStart() {
        if (!IsActived) {
            try {
                DoInit();
                IsActived = true;
                new Thread(this).start();
            } catch (Exception er) {
                Log.e(TAG, "DoStart: ", er);
            }
        }
    }

    public void DoStop() {
        Log.i(TAG, "DoStop-->stop");
        if (IsActived) {
            try {
                IsActived = false;
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (Exception er) {
                Log.e(TAG, "DoStop: ", er);
            }
        }
    }

    public void run() {
        // TODO Auto-generated method stub
        while (IsActived) {
            try {
                inBuff = new byte[DATA_LEN];
                inPacket = new DatagramPacket(inBuff, inBuff.length);
                Log.i(TAG, "run-->run  C ");
                // 读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里
                socket.receive(inPacket);
                Log.i(TAG, "run-->run  D ");
                String ARecTxt = new String(inBuff, 0, inPacket.getLength());
                try {
                    DoOnReceiveMsg(ARecTxt);
                } catch (Exception er) {
                    String Err = er.getMessage();
                    int ALen = Err.length();
                    Log.e(TAG, "DoOnReceiveMsg: ", er);
                }
            } catch (Exception er) {
                Log.e(TAG, "run: ", er);
                break;
            }
        }
        DoStop();
    }

    public void DoOnReceiveMsg(String ARecMsg) {
        Message msg = new Message();
        Bundle b = new Bundle();// 存放数据
        b.putString("RecMsg", ARecMsg);
        msg.setData(b);
        this.RecMsgHabndler.sendMessage(msg);
    }

    private Handler RecMsgHabndler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "HandleMessage-->handleMessage");
            Log.i(TAG, msg.getData().getString("RecMsg"));
        }
    };
}