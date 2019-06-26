package com.zkpt.gas.entity;

import com.common.util.ProtocolTxTUtil;

/**
 * 缴纳气费信息(响应包)
 * 
 * @author 赵琦
 *
 */
public class GasPaymentRespEntity implements java.io.Serializable {
    private static final long serialVersionUID = 8254592523158665729L;

    private char[] msg;
    private String msg_s;

    public char[] getMsg() {
        return msg;
    }

    public void setMsg(char[] msg) {
        this.msg = msg;
    }

    public String getMsg_s() {
        return msg_s;
    }

    public void setMsg_s(String msg_s) {
        this.msg_s = msg_s;
        this.msg = ProtocolTxTUtil.strToCharArray(msg_s);
    }
}
