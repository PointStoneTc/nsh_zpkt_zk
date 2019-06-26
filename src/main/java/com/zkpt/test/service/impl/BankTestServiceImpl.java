package com.zkpt.test.service.impl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.common.util.DateUtils;
import com.zkpt.middleware.entity.MwClient;
import com.zkpt.test.service.IBankTestService;

@Service("bankTestService")
// @RabbitListener(queues = "spring-boot-queue")
public class BankTestServiceImpl implements IBankTestService {
    private final static Logger logger = LoggerFactory.getLogger(BankTestServiceImpl.class);

    @Autowired
    private Environment env;

    private List<String> list = new ArrayList<>();

    @Override
    public boolean queryArrears(String bankNo, String user, String month) throws Exception {
        IoSession ioSession = createClient();
        ioSession.write(queryArrearsTrans(bankNo, user, month));
        Thread.sleep(5000);
        return true;
    }

    @Override
    public boolean payment(String bankNo, String user, double money, String month) throws Exception {
        IoSession ioSession = createClient();
        ioSession.write(paymentTrans(bankNo, user, money, month));
        Thread.sleep(5000);
        return true;
    }


    @Override
    public boolean userCharge(String bankNo, String user, String serNo) throws Exception {
        IoSession ioSession = createClient();
        ioSession.write(userChargeTrans(bankNo, user, serNo));
        Thread.sleep(5000);
        return true;
    }

    @Override
    public boolean generalLedger(String bankNo, String ledgerDate) throws Exception {
        IoSession ioSession = createClient();
        ioSession.write(generalLedgerTrans(bankNo, ledgerDate));
        Thread.sleep(5000);
        return true;
    }

    @Override
    @RabbitHandler
    public void consumeMes(String msg) {
        list.add(msg);
    }

    private IoSession createClient() throws IOException {
        MwClient mwClient = new MwClient();
        mwClient.setInetSocketAddress(env.getProperty("middleware.server.bank.ip"));
        mwClient.setPort(Integer.parseInt(env.getProperty("middleware.server.bank.port")));
        mwClient.setConnectTimeoutMillis(Integer.parseInt(env.getProperty("test.client.bank.connectTimeoutMillis")));
        mwClient.setProtocolCodecFilterName(env.getProperty("test.client.bank.protocolCodecFilterName"));
        mwClient.setCharSetName(env.getProperty("test.client.bank.charSetName"));

        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(mwClient.getConnectTimeoutMillis());
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName(mwClient.getCharSetName()), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
        connector.setHandler(new BankTestHandler());
        ConnectFuture connectionFuture = connector.connect(new InetSocketAddress(mwClient.getInetSocketAddress(), mwClient.getPort()));
        // 等待是否连接成功，相当于是转异步执行为同步执行。
        connectionFuture.awaitUninterruptibly();
        IoSession ioSession = connectionFuture.getSession();
        return ioSession;
    }

    private String queryArrearsTrans(String bankNo, String user, String month) {
        return "01" + bankNo + "      0163978511      0026|811|" + user + "|" + month + "|1|";
    }

    private String paymentTrans(String bankNo, String user, double money, String month) {
        return "01" + bankNo + "      0163978512      0061|821|" + user + "|" + money + "|" + money + "|" + DateUtils.date2Str(DateUtils.yyyymmddhhmmss) + "|00100080|" + month
                + "|1||";
    }

    private String userChargeTrans(String bankNo, String user, String serNo) {
        return "01" + bankNo + "      016397851       0025|850|" + user + "|" + serNo + "|";
    }

    private String generalLedgerTrans(String bankNo, String ledgerDate) {
        return "01" + bankNo + "      016402849       0021|600|" + ledgerDate + "|1|10.00|";
    }

}
