package com.kaishengit.util;

/**
 * @author Tonglin
 */
public class AjaxResult {

    private String state;
    private String message;
    private Object data;


    public static AjaxResult success() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setState("success");
        return ajaxResult;
    }

    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setData(data);
        ajaxResult.setState("success");
        return ajaxResult;
    }

    public static AjaxResult error(String message) {
        AjaxResult ajaxResult = new AjaxResult();

        ajaxResult.setState("error");
        ajaxResult.setMessage(message);
        return ajaxResult;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
