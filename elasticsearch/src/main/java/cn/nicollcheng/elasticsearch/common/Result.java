package cn.nicollcheng.elasticsearch.common;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Result {

  public static final int NO_ACCESS_TOKEN      = 404;
  public static final int ACCESS_TOKEN_INVALID = 50;

  protected boolean   success;
  protected String    msg;
  protected int       code;
  protected Object    data;
  protected String    logMsg;
  protected Exception e;

  public Result() {
  }

  public Result(boolean success, String msg) {
    this(success);
    this.msg = msg;
  }

  public Result(boolean success, String msg, int code) {
    this(success, msg);
    this.code = code;
  }

  public Result(boolean success) {
    this();
    this.success = success;
  }

  public Result(boolean success, String msg, Object data) {
    this(success, msg);
    this.data = data;
  }

  public boolean getSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Exception getE() {
    return e;
  }

  public void setE(Exception e) {
    this.e = e;
  }

  public String toString() {
    return JSONObject.toJSONString(this);
  }

  public Result put(String key, Object value) {
    if (data == null) {
      data = new HashMap<>();
    }
    ((HashMap) data).put(key, value);
    return this;
  }

  public Result putAll(Map<String, Object> m) {
    if (data == null) {
      data = new HashMap<>();
    }
    ((HashMap) data).putAll(m);
    return this;
  }


  public String getLogMsg() {
    return logMsg;
  }

  public void setLogMsg(String logMsg) {
    this.logMsg = logMsg;
  }


}