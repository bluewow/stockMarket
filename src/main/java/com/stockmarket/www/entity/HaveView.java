package com.stockmarket.www.entity;

/* 크롤링 데이터와 db데이터를 엮을 entity */
public class HaveView extends HaveStockView {
	
	private String price; // 현재가
	private int income; // 수익금 by 명훈
	private String gain; // 상승하락
	private String percent; //%
	private float ratio; // 자산비중율
	private long assetValue; // 현재 보유자산
	
	/* 기본 생성자 */
	public HaveView() {
	}
	
	/* haveStockDao용 생성자 */
	public HaveView(String price, String gain, String percent) {
		this.price = price;
		this.gain = gain;
		this.percent = percent;
	}
	
	/* distrService용 생성자 */
	public HaveView(String price, String gain, String percent, float ratio, long assetValue) {
		this.price = price;
		this.gain = gain;
		this.percent = percent;
		this.ratio = ratio;
		this.assetValue = assetValue;
	}

	public HaveView(int memberId, String stockCode, int quantity, int sum
			, String stockName, String price, String gain, String percent) {
		super(memberId, stockCode, quantity, sum, stockName);
		this.price = price;
		this.gain = gain;
		this.percent = percent;
	}
	
	public HaveView(int memberId, String stockCode, int quantity, int sum
			, String stockName, String price, String gain, String percent
			, float ratio, long assetValue) {
		super(memberId, stockCode, quantity, sum, stockName);
		this.price = price;
		this.gain = gain;
		this.percent = percent;
		this.ratio = ratio;
		this.assetValue = assetValue;
	}


	public HaveView(int income) {
		this.income = income;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getGain() {
		return gain;
	}
	public void setGain(String gain) {
		this.gain = gain;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	
	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}
	
	public long getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(long assetValue) {
		this.assetValue = assetValue;
	}

	@Override
	public String toString() {
		return "HaveView [price=" + price + ", income=" + income + ", gain=" + gain + ", percent=" + percent
				+ ", ratio=" + ratio + ", assetValue=" + assetValue + ", toString()=" + super.toString() + "]";
	}

	

}
