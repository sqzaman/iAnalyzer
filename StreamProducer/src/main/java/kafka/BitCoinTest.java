package kafka;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


@SpringBootApplication
@EnableScheduling
public class BitCoinTest { 

	
	public static void main(String[] args) {
		SpringApplication.run(BitCoinTest.class, args);
	}
	/*
	public static void main(String[] args) throws IOException,
			NoSuchAlgorithmException, InvalidKeyException {
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                // your code here
		            	System.out.println("Time" + new Date().toString());
		            }
		        }, 
		        100 
		);
		
		 
/*
		
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		URL urlObj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
		connection.setRequestMethod("GET");

		// read all the lines of the response into response StringBuffer
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = bufferedReader.readLine()) != null) {
			response.append(inputLine);
		}
		bufferedReader.close();

		// if you don't want to use Gson, you can just print the plain response
		//System.out.println(response.toString());
		
		// print result in nice format using the Gson library
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(response.toString());
		System.out.println(je);
		String prettyJsonResponse = gson.toJson(je);
		System.out.println(prettyJsonResponse);
		
	
		
	}

	/*
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Time" + new Date().toString());
	}
	*/
}
