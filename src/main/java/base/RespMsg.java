package base;

import java.util.ArrayList;
import java.util.List;

public class RespMsg {
    protected String msg;
    protected List<Object> data;

    public RespMsg(){}
    public RespMsg(String message){
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void addMsg(String msg){
        if(this.msg == null) this.msg = "";
        this.msg += " || \n" + msg;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public void addData(Object data) {
        if(this.data == null) this.data = new ArrayList<>();
        this.data.add(data);
    }
}

