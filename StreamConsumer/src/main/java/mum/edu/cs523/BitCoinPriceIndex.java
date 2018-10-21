package mum.edu.cs523;

public class BitCoinPriceIndex {

	Currency USD;
	Currency GBP;
	Currency EUR;
	
	public BitCoinPriceIndex(){
		
	}

	public Currency getUSD() {
		return USD;
	}

	public void setUSD(Currency uSD) {
		USD = uSD;
	}

	public Currency getGBP() {
		return GBP;
	}

	public void setGBP(Currency gBP) {
		GBP = gBP;
	}

	public Currency getEUR() {
		return EUR;
	}

	public void setEUR(Currency eUR) {
		EUR = eUR;
	}

	@Override
	public String toString() {
		return "BitCoinPriceIndex [USD=" + USD + ", GBP=" + GBP + ", EUR="
				+ EUR + "]";
	}
	
	
}
