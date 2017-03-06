package base;

public class ErrorRespMsg extends RespMsg {
    private int code;

    public ErrorRespMsg() {
    }

    public ErrorRespMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
