package com.youyu.cardequity.payment.biz.dal.entity;

import com.youyu.cardequity.payment.biz.component.status.paytraderefund.PayTradeRefundStatus;
import com.youyu.cardequity.payment.biz.component.status.paytraderefund.PayTradeRefundStatus4NonRefund;
import com.youyu.cardequity.payment.biz.enums.AlipayDayCutEnum;
import com.youyu.cardequity.payment.dto.PayTradeRefundDto;
import com.youyu.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static com.youyu.cardequity.common.base.bean.CustomHandler.getBeanByClass;
import static com.youyu.cardequity.common.base.util.StatusAndStrategyNumUtil.getNumber;
import static com.youyu.cardequity.common.base.util.UuidUtil.uuid4NoRail;
import static com.youyu.cardequity.payment.biz.enums.AlipayDayCutEnum.getAlipayDayCutEnumByTime;

/**
 * @author panqingqing
 * @version v1.0
 * @date 2018年12月6日 下午10:00:00
 * @work PayTradeRefund退款
 */
@Getter
@Setter
@Table(name = "TB_PAY_TRADE_REFUND")
public class PayTradeRefund extends BaseEntity<String> {

    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    protected String id;

    /**
     * 支付日志id
     */
    @Column(name = "PAY_LOG_ID")
    protected String payLogId;

    /**
     * 申请单号:订单编号：业务系统传入
     */
    @Column(name = "APP_SHEET_SERIAL_NO")
    protected String appSheetSerialNo;

    /**
     * 退款申请金额
     */
    @Column(name = "REFUND_APPLY_AMOUNT")
    protected BigDecimal refundApplyAmount;

    /**
     * 退款实际金额
     */
    @Column(name = "REFUND_AMOUNT")
    protected BigDecimal refundAmount;

    /**
     * 退款编号
     */
    @Column(name = "REFUND_NO")
    protected String refundNo;

    /**
     * 退款原因
     */
    @Column(name = "REFUND_REASON")
    protected String refundReason;

    /**
     * 鉴别类型
     */
    @Column(name = "TYPE")
    protected String type;

    /**
     * 退款状态
     */
    @Column(name = "STATUS")
    protected PayTradeRefundStatus status;

    /**
     * 描述
     */
    @Column(name = "REMARK")
    protected String remark;

    /**
     * 客户号:
     */
    @Column(name = "CLIENT_ID")
    protected String clientId;

    /**
     * 客户姓名:
     */
    @Column(name = "CLIENT_NAME")
    protected String clientName;

    /**
     * 渠道号:
     */
    @Column(name = "CHANNEL_NO")
    protected String channelNo;

    /**
     * 业务代码:
     */
    @Column(name = "BUSIN_CODE")
    protected String businCode;

    public PayTradeRefund() {
        this.id = uuid4NoRail();
    }

    public PayTradeRefund(PayTradeRefundDto tradeRefundApplyDto, PayLog payLog) {
        this();
        this.payLogId = payLog.getId();
        this.appSheetSerialNo = tradeRefundApplyDto.getAppSheetSerialNo();
        this.refundApplyAmount = tradeRefundApplyDto.getRefundAmount();
        this.refundNo = tradeRefundApplyDto.getRefundNo();
        this.refundReason = tradeRefundApplyDto.getRefundReason();
        this.status = getBeanByClass(PayTradeRefundStatus4NonRefund.class);
        this.clientId = payLog.getClientId();
        this.clientName = payLog.getClientName();
        this.channelNo = payLog.getPayChannelNo();
        this.businCode = tradeRefundApplyDto.getBusinCode();
    }

    public void getTradeRefund(PayLog payLog) {
        throw new RuntimeException("该交易不支持退款!");
    }

    public void callRefund() {
        this.status = status.refunding();
    }

    public boolean isRefundSucc() {
        return status.isRefundSucc();
    }

    public void refundAfterBill2TradeRefund() {
        this.status = status.refundSucc();
    }

    public AlipayDayCutEnum getAlipayDayCutEnum() {
        return getAlipayDayCutEnumByTime(getCreateTime());
    }

    public void refundFail() {
        this.status = status.refundFail();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRefundStatus() {
        return getNumber(status);
    }
}
