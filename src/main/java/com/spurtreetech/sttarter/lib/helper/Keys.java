package com.spurtreetech.sttarter.lib.helper;

/**
 * Created by RahulT on 16-06-2015.
 */
public class Keys {

    public static String USER_ID = "USER_ID";
    public static String USER_TOKEN = "USER_TOKEN";
    public static String APP_KEY = "APP_KEY";
    public static String APP_SECRET = "APP_SECRET";

    /*public static String USER_PHONE = "user_phone";
    public static String USER_TOKEN = "user_token";
    public static String IE_TOKEN = "ie_token";
    public static String STTARTER_TOKEN = "sttarter_token";
    public static String STTARTER_APP_KEY = "sttarter_app_key";
    public static String STTARTER_APP_SECRET = "sttarter_secret";*/


    private static String HOST = "http://sttarter.com:3000/";  //"http://10.1.3.194:3000/"  //http://10.1.3.33:3000/  // http://sttarter.com/
    public static String BASE_URL = HOST + "app/mqtt/";

    public static String AUTH = HOST + "auth";
    public static String GET_TOPICS = BASE_URL + "gettopics";
    public static String ALL_TOPICS = BASE_URL + "alltopics";
    public static String ALL_USERS = HOST + "app/user";
    public static String REGISTER = HOST + "app/client/register";
    public static String UNREGISTER = HOST + "app/client/unregister";
    public static String MY_TOPICS = BASE_URL + "mytopics";
    public static String SUB = BASE_URL + "topic/join";
    public static String UNSUB = BASE_URL + "topic/leave";
    public static String PUBLISH = BASE_URL + "pub";
    public static String INITCHAT = BASE_URL + "initchat";
    public static String INITGROUPCHAT = BASE_URL + "initgroupchat";
    public static String CHAT_UNSUBSCRIBE = BASE_URL + "chat/unsubscribe";
    public static String GROUPCHAT_UNSUBSCRIBE = BASE_URL + "groupchat/unsubscribe";
    public static String STTARTER_PREFERENCES = "com.spurtreetech.sttarter.lib";
    public static String SUBSCRIBED_TOPICS = "SUBSCRIBED_TOPICS";
    public static String ALL_TOPICS_LIST = "ALL_TOPICS_LIST";

    /* chat server url */
    public static String SERVER_URL = "sttarter.com";  //"10.1.3.54";
    public static String PORT = "1883";
    public static boolean CLEAN_SESSION = false;
    public static String NOTIFICATION_TOPICS = "NOTIFICATION_TOPICS";

    /* expirable token */
    public static String AUTH_TOKEN = "STT_TOKEN";

    /* group name passed to initiate chat */
    public static String CHAT_TOPIC = "CHAT_TOPIC";
    public static String TOPIC_META = "TOPIC_META";

    public static String CLIENT_HANDLE = "CLIENT_HANDLE";

    public static String AUTO_ACCEPT = "AUTO_ACCEPT";

}