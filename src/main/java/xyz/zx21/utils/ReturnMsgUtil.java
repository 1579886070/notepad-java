package xyz.zx21.utils;

import com.alibaba.fastjson.annotation.JSONField;
import xyz.zx21.common.Constants;

import java.io.Serializable;

/**
 * 返回数据处理
 *
 * @author Administrator
 * @date 2020/3/2 14:38
 */
public class ReturnMsgUtil implements Serializable {
    private static final long serialVersionUID = -6601457211348976672L;

    public final static int CODE_SUCCESS = Constants.CODE_SUCCESS;//正常
    public final static int CODE_FAIL = Constants.CODE_FAIL;//失败
    public final static String MSG_SUCCESS = "操作成功！";
    public final static String MSG_FAIL = "操作失败！";


    @JSONField(name = "code")
    private int code;//请求状态值

    @JSONField(name = "msg")
    private String msg;//请求状态描述

    @JSONField(name = "data")
    private Object data = null;//消息体

    public ReturnMsgUtil() {
    }

    public ReturnMsgUtil(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static ReturnMsgUtil success(Object data) {
        ReturnMsgUtil returnMsgUtil = new ReturnMsgUtil();
        returnMsgUtil.code = ReturnMsgUtil.CODE_SUCCESS;
        returnMsgUtil.msg = ReturnMsgUtil.MSG_SUCCESS;
        returnMsgUtil.data = data;
        return returnMsgUtil;
    }

    public static ReturnMsgUtil fail(Object data) {
        ReturnMsgUtil returnMsgUtil = new ReturnMsgUtil();
        returnMsgUtil.code = ReturnMsgUtil.CODE_FAIL;
        returnMsgUtil.msg = ReturnMsgUtil.MSG_FAIL;
        returnMsgUtil.data = data;
        return returnMsgUtil;
    }
    public static ReturnMsgUtil fail(int code,String msg) {
        ReturnMsgUtil returnMsgUtil = new ReturnMsgUtil();
        returnMsgUtil.code = code;
        returnMsgUtil.msg = msg;
        return returnMsgUtil;
    }
}
