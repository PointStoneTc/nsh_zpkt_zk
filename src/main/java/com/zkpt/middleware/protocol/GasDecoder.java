package com.zkpt.middleware.protocol;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.ProtocolTxTUtil;
import com.zkpt.gas.entity.GasCommand;
import com.zkpt.gas.entity.GasPacketHeadEntity;
import com.zkpt.gas.entity.GasProtocalPackage;

/**
 * 解码器
 * 
 * @author sunpei
 *
 */
public class GasDecoder extends CumulativeProtocolDecoder {
    private final static Logger logger = LoggerFactory.getLogger(GasDecoder.class);
    public final static int PACKAGE_HEAD_LENGTH = 51;
    private Charset charset;

    public GasDecoder() {}

    public GasDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        in.order(ByteOrder.LITTLE_ENDIAN);
        if (in.remaining() < 4) { // 这里很关键，是用来当拆包时候剩余长度小于4的时候的保护，不加就出错咯
            return false;
        }

        if (in.remaining() > 1) {
            int length = in.remaining();
            if (length < PACKAGE_HEAD_LENGTH) { // 包头没有读取完毕
                return false;
            } else {
                in.mark();
                byte[] messageBytes = new byte[length];
                in.get(messageBytes, 0, length); // 读取整个报文
                byte[] messageBodyLengthBtyes = new byte[] {messageBytes[45], messageBytes[46], messageBytes[47], messageBytes[48], messageBytes[49]}; // 包体长度
                String slength = ProtocolTxTUtil.byteArrayToStr(messageBodyLengthBtyes, charset).replaceFirst("^0*", "");
                int messageBodyLength = 0;
                if (!"".equals(slength))
                    messageBodyLength = Integer.parseInt(slength);
                if (length < PACKAGE_HEAD_LENGTH + messageBodyLength) { // 数据没有接受完毕
                    in.reset();
                    return false;
                } else { // 接受完毕了数据

                    String mesage = ProtocolTxTUtil.byteArrayToStr(messageBytes, charset);
                    String head = mesage.substring(0, PACKAGE_HEAD_LENGTH);
                    String body = mesage.substring(PACKAGE_HEAD_LENGTH);

                    GasProtocalPackage gasProtocalPackage = new GasProtocalPackage();
                    GasPacketHeadEntity gasPacketHead = gasProtocalPackage.getPacketHead();

                    gasProtocalPackage.setEchoTimeMillis(System.currentTimeMillis());
                    // 封装包头
                    gasPacketHead.setRequestId_s(head.substring(0, 4)); // 请求标识，命令字（主命令+子命令字），响应方需原样返回
                    gasPacketHead.setBankNo_s(head.substring(4, 7)); // 银行编号，只有银行端需要填写 000 – 255
                    gasPacketHead.setBankDeviceNo_s(head.substring(7, 17)); // 银行设备编号，最长 10 位 不足前补 0
                    gasPacketHead.setBankBizSn_s(head.substring(17, 27)); // 银行业务流水号，最长 10 位 ，不足前补 0
                    gasPacketHead.setTimeStamp_s(head.substring(27, 41)); // 请求方或者响应方本地时间戳” yyyymmddhhnnss”
                    gasPacketHead.setRespondState_s(head.substring(41, 45)); // 响应字响应方回馈， 0000 代表成功 其它为错误码
                    gasPacketHead.setDataLength_s(head.substring(45, 50)); // 包体长度字节数
                    gasPacketHead.setIsNextPacket_s(head.substring(50, 51)); // 包头结束符

                    // 重组gasProtocalPackage
                    gasProtocalPackage.setPacketHead_s(head);
                    gasProtocalPackage.setPacketBody(body);
                    gasProtocalPackage.setEchoTime(new Date());
                    GasCommand type = GasCommand.getEnumByKey(gasPacketHead.getRequestId_s());
                    switch (type) {
                    case YHQFQK_COMM:
                        gasProtocalPackage.setGasCommand(GasCommand.YHQFQK_COMM);
                        break;
                    case QFJN_COMM:
                        gasProtocalPackage.setGasCommand(GasCommand.QFJN_COMM);
                        break;
                    case QXJF_COMM:
                        gasProtocalPackage.setGasCommand(GasCommand.QXJF_COMM);
                        break;
                    case DZRZ_COMM:
                        gasProtocalPackage.setGasCommand(GasCommand.DZRZ_COMM);
                        break;
                    default:
                        break;
                    }
                    out.write(gasProtocalPackage);
                    if (in.remaining() > 0) {// 如果读取内容后还粘了包，就让父类再重读 一次，进行下一次解析
                        return true;
                    }
                    return false;
                }
            }
        }

        return false;
    }

}
