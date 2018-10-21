package kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@Component
public class ScheduledTasks {
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
    @Autowired
    private Sender sender;
	
	@Scheduled(fixedRate = 60000, initialDelay = 0) // 1 minute
	public void scheduleTaskWithFixedRate() {
	    System.out.println(String.format("Sending BTC rate :: Execution Time - {%s}", dateTimeFormatter.format(LocalDateTime.now())));
	    sender.send(this.getBtcData());
	}
	
	private String getBtcData() {

		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		StringBuffer response = new StringBuffer();
		try {
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
			connection.setRequestMethod("GET");

			// read all the lines of the response into response StringBuffer
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			

			while ((inputLine = bufferedReader.readLine()) != null) {
				response.append(inputLine);
			}
			bufferedReader.close();
		} catch (Exception ex) {
			
		}
		
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.toString());
		return je.toString();
	}
}
