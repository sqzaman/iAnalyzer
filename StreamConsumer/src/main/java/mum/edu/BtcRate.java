package mum.edu;

public class BtcRate {
	
	BtcRateDate time;
	String disclaimer;
	String chartName;
	BitCoinPriceIndex bpi;
	
	public BtcRate(){
		
	}

	public BtcRateDate getTime() {
		return time;
	}

	public void setTime(BtcRateDate time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public BitCoinPriceIndex getBpi() {
		return bpi;
	}

	public void setBpi(BitCoinPriceIndex bpi) {
		this.bpi = bpi;
	}

	@Override
	public String toString() {
		return "BtcRate [time=" + time + ", disclaimer=" + disclaimer
				+ ", chartName=" + chartName + ", bpi=" + bpi + "]";
	}

	
}
