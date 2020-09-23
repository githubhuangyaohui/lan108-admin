package com.persistenthuang.lan108admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.persistenthuang.lan108admin.config.ApplicationContextUtil;
import com.persistenthuang.lan108admin.pojo.CheatMassage;
import com.persistenthuang.lan108admin.pojo.User;
import com.persistenthuang.lan108admin.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Controller
@ServerEndpoint("/groupChat/{sid}/{userId}")
public class WebSocketServerController {

    /**
     * 房间号 -> 组成员信息
     */
    private static final ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();
    /**
     * 房间号 -> 在线人数
     */
    private static final ConcurrentHashMap<String, Set<Integer>> onlineUserMap = new ConcurrentHashMap<>();

    /**
     * 收到消息调用的方法，群成员发送消息
     *
     * @param sid:房间号
     * @param userId：用户id
     * @param message：发送消息
     */
    @OnMessage
    public void onMessage(@PathParam("sid") String sid, @PathParam("userId") Integer userId, String message) {
        log.info("发送了信息");
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        Set<Integer> onlineUserList = onlineUserMap.get(sid);
        // 先一个群组内的成员发送消息
        sessionList.forEach(item -> {
            try {
                // json字符串转对象
                CheatMassage msg = JSONObject.parseObject(message, CheatMassage.class);
                msg.setCount(onlineUserList.size());
                // json对象转字符串
                String text = JSONObject.toJSONString(msg);
                item.getBasicRemote().sendText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 建立连接调用的方法，群成员加入
     *
     * @param session
     * @param sid
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid, @PathParam("userId") Integer userId) {
        List<Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid, k -> new ArrayList<>());
        Set<Integer> onlineUserList = onlineUserMap.computeIfAbsent(sid, k -> new HashSet<>());
        onlineUserList.add(userId);
        sessionList.add(session);
        // 发送上线通知
        log.info("上线了" + sid + userId);
        sendInfo(sid, userId, onlineUserList.size(), "上线了~");
    }


    public void sendInfo(String sid, Integer userId, Integer onlineSum, String info) {
        log.info("调用了sendInfo");
        // 获取该连接用户信息
        User currentUser = ApplicationContextUtil.getApplicationContext().getBean(UserServiceImpl.class).getUserById(userId);
        log.info("获取用户信息" + currentUser.toString());
        // 发送通知
        CheatMassage msg = new CheatMassage();
        msg.setCount(onlineSum);
        msg.setUserId(userId);
        msg.setMsg(currentUser.getUserName() + info);
        // json对象转字符串
        String text = JSONObject.toJSONString(msg);
        log.info("返回数据");
        onMessage(sid, userId, text);
    }

    /**
     * 关闭连接调用的方法，群成员退出
     *
     * @param session
     * @param sid
     */
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid, @PathParam("userId") Integer userId) {
        log.info("离线了");
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        sessionList.remove(session);
        Set<Integer> onlineUserList = onlineUserMap.get(sid);
        onlineUserList.remove(userId);
        // 发送离线通知
        sendInfo(sid, userId, onlineUserList.size(), "下线了~");
    }

    /**
     * 传输消息错误调用的方法
     *
     * @param error
     */
    @OnError
    public void OnError(Throwable error) {
        log.info("连接错误");
    }
}
