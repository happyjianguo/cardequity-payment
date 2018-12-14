package com.youyu.cardequity.payment.biz.component.command;

import com.youyu.cardequity.common.base.annotation.StatusAndStrategyNum;
import com.youyu.cardequity.payment.biz.dal.entity.PayLog;
import com.youyu.cardequity.payment.biz.dal.entity.PayLog4Alipay;
import com.youyu.cardequity.payment.dto.alipay.AlipaySyncMessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author panqingqing
 * @version v1.0
 * @date 2018年12月6日 下午10:00:00
 * @work Alipay Pay同步支付命令
 */
@StatusAndStrategyNum(superClass = PayLogCommond.class, number = "1", describe = "支付宝同步接收参数")
@Component
public class PayLogCommond4AlipaySyncMessage extends PayLogCommond {

    @Value("${alipay.sellerId:}")
    private String sellerId;
    @Value("${alipay.appId:}")
    private String appId;

    /**
     * @param payLog
     * @param t      支付宝同步参数:AlipaySyncMessageDto
     * @param <T>
     * @param <R>
     * @return
     */
    @Override
    public <T, R> R executeCmd(PayLog payLog, T t) {
        AlipaySyncMessageDto alipaySyncMessageDto = (AlipaySyncMessageDto) t;
//      verifySignatur(alipaySyncMessageDto);
        PayLog4Alipay payLog4Alipay = (PayLog4Alipay) payLog;
        payLog4Alipay.analysisAlipaySycnMessage(alipaySyncMessageDto, sellerId, appId);
        return null;
    }

    private void verifySignatur(AlipaySyncMessageDto alipaySyncMessageDto) {
        // TODO: 2018/12/12  验证签名,暂时不验证(支付宝提示忽略)
    }
}