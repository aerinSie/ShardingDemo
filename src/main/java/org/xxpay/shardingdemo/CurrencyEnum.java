package org.xxpay.shardingdemo;

import java.util.stream.Stream;

/**
 * 
 * 币种
 * 
 * @author eden
 *
 */
public enum CurrencyEnum {
	
	VND("vnd", "越南盾"),
	INR("inr", "印度卢比"),
	BRL("brl", "巴西里奥"),
	THB("thb", "泰国泰铢"),
	PKR("pkr","巴基斯坦卢比"),
	MYR("myr", "馬來西亞令吉"),
	IDR("idr", "印尼盾"),
	PHP("php", "菲律賓披索"),
	USD("usd", "美元"),
	NGN("ngn", "尼日利亚奈拉"),
	MXN("mxn", "墨西哥比索"),
	ZAR("zar", "南非兰特"),
	TRY("try", "土耳其里拉"),
	KES("kes", "肯尼亚先令"),
	COP("cop", "哥伦比亚比索"),
	BDT("bdt", "孟加拉塔卡"),
	EGP("egp", "埃及镑"),
	PEN("pen", "秘鲁新索尔"),
	EUR("eur", "欧元"),

	RMB("rmb", "人民币"),
	JPY("jpy", "日元"),
	KRW("krw", "韩国元"),
	TWD("twd", "台湾"),
	CLP("clp", "智利比索"),
	RUB("rub", "俄羅斯盧布"),
	SGD("sgd", "新加坡元"),
	RPL("rpl", "数字货币RPL"),
	OKKG("okkg", "数字货币OKKG"),
	EB("eb", "数字货币EB"),
	USDT("usdt", "usdt"),
	USDT_ERC20("usdt_erc20", "usdt_erc20"),
	ETH("eth", "eth"),
	TRX("trx", "trx")
	;

	
	private String code;
	
	private String desc;

	CurrencyEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static CurrencyEnum getCurrency(String code) {
		return Stream.of(CurrencyEnum.values()).filter(r -> r.getCode().equals(code)).findFirst().orElse(null);
	}

}
