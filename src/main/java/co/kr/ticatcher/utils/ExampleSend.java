package co.kr.ticatcher.utils;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */
public class ExampleSend {
    public static void main(String[] args) {
        String api_key = "NCSPY00K2BTHQXVE";
        String api_secret = "ZVWGH7KUB98Z5K9MUZEADFRPCKT4NPTG";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", "${tonum}");
        params.put("from", "010");
        params.put("type", "SMS");
        params.put("text", "[Ticatcher] 인증번호 \"+randomNumber+\" 를 입력하세요.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
