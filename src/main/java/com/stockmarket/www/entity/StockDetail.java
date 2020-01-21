package com.stockmarket.www.entity;

public class StockDetail {
	int change_val;				//전일비
	double	frgn_stock; 			//??
	int	acc_quant;				//거래량 -
	String bizdate;				//날짜 -
	String risefall;			//??
	String itemcode;			//종목코드 - 
	int	frgn_pure_buy_quant;	//외국인 -
	int	close_val;				//종가 - 
	String sosok;				//??
	double frgn_hold_ratio;		//외국인 보유율 
	int	organ_pure_buy_quant;	//기관 -
	int indi_pure_buy_quant;	//개인 -

	public StockDetail(int acc_quant, String bizdate, String itemcode, int frgn_pure_buy_quant, int close_val,
			int organ_pure_buy_quant, int indi_pure_buy_quant) {
		super();
		this.acc_quant = acc_quant;
		this.bizdate = bizdate;
		this.itemcode = itemcode;
		this.frgn_pure_buy_quant = frgn_pure_buy_quant;
		this.close_val = close_val;
		this.organ_pure_buy_quant = organ_pure_buy_quant;
		this.indi_pure_buy_quant = indi_pure_buy_quant;
	}


	public int getChange_val() {
		return change_val;
	}


	public double getFrgn_stock() {
		return frgn_stock;
	}


	public int getAcc_quant() {
		return acc_quant;
	}


	public String getBizdate() {
		return bizdate;
	}


	public String getRisefall() {
		return risefall;
	}


	public String getItemcode() {
		return itemcode;
	}


	public int getFrgn_pure_buy_quant() {
		return frgn_pure_buy_quant;
	}


	public int getClose_val() {
		return close_val;
	}


	public String getSosok() {
		return sosok;
	}


	public double getFrgn_hold_ratio() {
		return frgn_hold_ratio;
	}


	public int getOrgan_pure_buy_quant() {
		return organ_pure_buy_quant;
	}


	public int getIndi_pure_buy_quant() {
		return indi_pure_buy_quant;
	}


	@Override
	public String toString() {
		return "StockDetail [change_val=" + change_val + ", frgn_stock=" + frgn_stock + ", acc_quant=" + acc_quant
				+ ", bizdate=" + bizdate + ", risefall=" + risefall + ", itemcode=" + itemcode
				+ ", frgn_pure_buy_quant=" + frgn_pure_buy_quant + ", close_val=" + close_val + ", sosok=" + sosok
				+ ", frgn_hold_ratio=" + frgn_hold_ratio + ", organ_pure_buy_quant=" + organ_pure_buy_quant
				+ ", indi_pure_buy_quant=" + indi_pure_buy_quant + "]";
	}
}