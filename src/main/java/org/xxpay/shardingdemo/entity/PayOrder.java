package org.xxpay.shardingdemo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayOrder {
    public static final int STATUS_CREATED = 0; // 订单生成
    public static final int STATUS_NOT_YET_OPEN_PAGE = 1; // 未支付（已經發送請求給Alipy，但还没打开支付页面）
    public static final int STATUS_OPENED_PAGE = 2; // 待支付（已经打开支付页面）
    public static final int STATUS_SCANNED_QR_CODE = 3; // 支付中（已经扫码）
    public static final int STATUS_PAID = 4; // 支付完成
    public static final int STATUS_COMPLETED = 5; // 处理完成
    public static final int STATUS_CLOSED_ON_NOT_YET_OPEN_PAGE = 6; // 订单关闭（未支付）
    public static final int STATUS_CLOSED_ON_OPENED_PAGE = 7; // 订单关闭（待支付）
    public static final int STATUS_CLOSED_ON_SCANNED_QR_CODE = 8; // 订单关闭（支付中）
    public static final int STATUS_CLOSED_ON_CREATE = 9; // 訂單關閉（訂單生成）
    public static final int STATUS_CLOSED_ON_BANNED_IP = 10; // 订单关闭（IP BANNED）
    public static final int STATUS_CLOSED_ON_IP_DIFFERENT = 11; // 订单关闭（IP比对不一致）
    public static final int STATUS_CLOSED_ON_USERID_BANNED = 12; // 订单关闭（用户被BAN）
    public static final int STATUS_CREATE_ON_FAIL = 13; // 三方订单建立 Response 失败

    public static final int CHECK_PAID_FROM_NOTIFY = 0; // 由支付寶回調時，再去支付寶撈訂單，再將訂單狀態設為PAID
    public static final int CHECK_PAID_FROM_PULL = 1; // 由按下刷新，去支付寶撈訂單，再將訂單狀態設為PAID

    public static final int EXECUTE_STATUS_PROCESSED = 0; // 已處理/不需處理
    public static final int EXECUTE_STATUS_NOT_PROCESSED = 1; // 未處理




    /**
     * 支付订单狀態对应中文信息(for 后台)
     *
     * @param status
     * @return
     */
    public static String getStatusDesc(Integer status){
        // 支付状态 1:订单处理中, 2:支付完成, -1:支付失败, -2:订单关闭
        switch (status){
            case STATUS_CREATED: // 订单生成
                //翻译 中:创建中 英:Creating
                return "Creating";
            case STATUS_NOT_YET_OPEN_PAGE: // 未支付（已經發送請求給Alipy，但还没打开支付页面）
                //翻译 中:发起订单 英:Initiate an order
                return "Initiate an order";
            case STATUS_OPENED_PAGE: // 待支付（已经打开支付页面）
                //翻译 中:待支 英:To be paid
                return "To be paid";
            case STATUS_SCANNED_QR_CODE: // 支付中（已经扫码）
                //翻译 中:支付中 英:Payment ongoing
                return "Payment ongoing";
            case STATUS_PAID: // 支付完成
                //翻译 中:支付完成 英:Payment completed
                return "Payment completed";
            case STATUS_COMPLETED: // 处理完成
                //翻译 中:处理完成 英:Processing complete
                return "Processing complete";
            case STATUS_CLOSED_ON_NOT_YET_OPEN_PAGE: // 订单关闭（未支付）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CLOSED_ON_OPENED_PAGE: // 订单关闭（待支付）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CLOSED_ON_SCANNED_QR_CODE: // 订单关闭（支付中）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CLOSED_ON_CREATE: // 訂單關閉（訂單生成）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CLOSED_ON_BANNED_IP: // 订单关闭（IP BANNED）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CLOSED_ON_IP_DIFFERENT: // 订单关闭（IP比对不一致）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CLOSED_ON_USERID_BANNED: // 订单关闭（用户被BAN）
                //翻译 中:订单关闭 英:Order closed
                return "Order closed";
            case STATUS_CREATE_ON_FAIL: // 三方订单建立 Response 失败
                //翻译 中:创建失败 英:Creation failed
                return "Creation failed";
            default:
                return String.valueOf(status);
        }
    }



    public String getStatusDesc(){
        return getStatusDesc(this.status);
    }

    private String payOrderId; // 订单号

    private String merchantId; // 商户ID

    private String merchantUserId; //商户的用戶ID

    private String merchantOrderNo; // 商户方的订单号

    private Long channelId; // 渠道ID

    private String channelCode; // 渠道支付编码

    private String thirdChannelCode; // 金流商对应的支付渠道代号

    private String channelType; // 渠道類型

    private String accountGroupCode; // 帳號組代碼

    private String channelOrderNo; // 渠道方的订单号

    private String channelRemark; // 渠道参数 - 备注信息

    private Long accountId; // 账号ID

    private String catchId; // 渠道編碼

    private BigDecimal amount; // 支付金额，单位分

    private BigDecimal actualAmount; // 实际支付金额，单位分

    private BigDecimal confirmedActualAmount; // 确认实际支付金额，单位分

    private BigDecimal discountAmount; // 优惠金额，单位分

    private Double rate; // 订单手续费费率

    private BigDecimal rateFixedAmount; // 手續費，固定金額，單位分

    private BigDecimal fee; // 手續費，單位分

    private BigDecimal serviceFee; // 服務費，單位分

    private String currency; // 货币

    private Integer status; // 状态

    private String notifyUrl; // 通知地址

    private String returnUrl; // 同步跳转地址

    private String quitUrl; // 支付取消返回URL

    private String subject; // 訂單標題

    private String body; // 訂單描述

    private Date successTime; // 订单支付成功时间

    private String rateDifferences; // 整个代理阶层的费率差列表（JSON格式）

    private String clientIp; // 客户端IP

    private String clientDevice; // 客户端设备

    private String clientExtra; // 客户端发起时额外参数

    private Integer checkPaidProcess; // 指出，去支付寶撈訂單，再將訂單狀態設為PAID，是走哪個流程

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private Integer isBelong;

    private Long accountChannelId; // 記錄使用哪個支付渠道賬號

    private String channelNotifyUrl; // 渠道方回調網址

    private String realName; // 客戶真實姓名

    private BigDecimal currencyRate;

    private BigDecimal usdt;

    private String otherParams; //各自需要的额外参数，以json格式储存

    private Integer executeStatus;//建單流程狀態-NoSubmit用

    private String email; //付款帳號的email

    private String phoneNum; //付款資訊的手機號

    private String payAccountNum; //付款帳號

    private Integer merchantNotifyStatus; // 商戶回調通知狀態

    private Date lastNotifyTime; // 最後一次通知時間
}
