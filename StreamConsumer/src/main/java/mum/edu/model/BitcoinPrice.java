package mum.edu.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "bitcoin_price_index", type = "article", shards = 1, replicas = 0, refreshInterval = "-1")
public class BitcoinPrice {
	
	@Id
	private String _id = UUID.randomUUID().toString();

	Date date;
	Date dateISO;
	Date dateUK;
	String disclaimer;
	String chartName;
	
	String usdCode;
	String usdSymbol;
	Double rateInUSD;
	String usdDescription;
	
	String euroCode;
	String euroSymbol;
	Double rateInEURO;
	String euroDescription;
	
	String gbpCode;
	String gbpSymbol;
	Double rateInGBP;
	String gbpDescription;
	public BitcoinPrice(Date date, Date dateISO, Date dateUK,
			String disclaimer, String chartName, String usdCode,
			String usdSymbol, Double rateInUSD, String usdDescription,
			String euroCode, String euroSymbol, Double rateInEURO,
			String euroDescription, String gbpCode, String gbpSymbol,
			Double rateInGBP, String gbpDescription) {
		super();
		this.date = date;
		this.dateISO = dateISO;
		this.dateUK = dateUK;
		this.disclaimer = disclaimer;
		this.chartName = chartName;
		this.usdCode = usdCode;
		this.usdSymbol = usdSymbol;
		this.rateInUSD = rateInUSD;
		this.usdDescription = usdDescription;
		this.euroCode = euroCode;
		this.euroSymbol = euroSymbol;
		this.rateInEURO = rateInEURO;
		this.euroDescription = euroDescription;
		this.gbpCode = gbpCode;
		this.gbpSymbol = gbpSymbol;
		this.rateInGBP = rateInGBP;
		this.gbpDescription = gbpDescription;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateISO() {
		return dateISO;
	}
	public void setDateISO(Date dateISO) {
		this.dateISO = dateISO;
	}
	public Date getDateUK() {
		return dateUK;
	}
	public void setDateUK(Date dateUK) {
		this.dateUK = dateUK;
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
	public String getUsdCode() {
		return usdCode;
	}
	public void setUsdCode(String usdCode) {
		this.usdCode = usdCode;
	}
	public String getUsdSymbol() {
		return usdSymbol;
	}
	public void setUsdSymbol(String usdSymbol) {
		this.usdSymbol = usdSymbol;
	}
	public Double getRateInUSD() {
		return rateInUSD;
	}
	public void setRateInUSD(Double rateInUSD) {
		this.rateInUSD = rateInUSD;
	}
	public String getUsdDescription() {
		return usdDescription;
	}
	public void setUsdDescription(String usdDescription) {
		this.usdDescription = usdDescription;
	}
	public String getEuroCode() {
		return euroCode;
	}
	public void setEuroCode(String euroCode) {
		this.euroCode = euroCode;
	}
	public String getEuroSymbol() {
		return euroSymbol;
	}
	public void setEuroSymbol(String euroSymbol) {
		this.euroSymbol = euroSymbol;
	}
	public Double getRateInEURO() {
		return rateInEURO;
	}
	public void setRateInEURO(Double rateInEURO) {
		this.rateInEURO = rateInEURO;
	}
	public String getEuroDescription() {
		return euroDescription;
	}
	public void setEuroDescription(String euroDescription) {
		this.euroDescription = euroDescription;
	}
	public String getGbpCode() {
		return gbpCode;
	}
	public void setGbpCode(String gbpCode) {
		this.gbpCode = gbpCode;
	}
	public String getGbpSymbol() {
		return gbpSymbol;
	}
	public void setGbpSymbol(String gbpSymbol) {
		this.gbpSymbol = gbpSymbol;
	}
	public Double getRateInGBP() {
		return rateInGBP;
	}
	public void setRateInGBP(Double rateInGBP) {
		this.rateInGBP = rateInGBP;
	}
	public String getGbpDescription() {
		return gbpDescription;
	}
	public void setGbpDescription(String gbpDescription) {
		this.gbpDescription = gbpDescription;
	}
	
	
	
}
