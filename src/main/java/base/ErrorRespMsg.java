package base;

public class ErrorRespMsg extends RespMsg {

    public ErrorRespMsg() {
    }

    public ErrorRespMsg(int code, String msg) {
        super(code,msg);
    }

}
