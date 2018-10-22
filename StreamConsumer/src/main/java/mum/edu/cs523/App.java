package mum.edu.cs523;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.web.client.RestTemplate;

import kafka.serializer.StringDecoder;

import com.google.gson.Gson;
import mum.edu.cs523.BtcRate;


public class App {

	public static void main(String[] args) throws InterruptedException {

		// Create a Java Streaming Context with a Batch Interval of 5 seconds
		SparkConf conf = new SparkConf().setAppName("KafkaSpark").setMaster("local");
		JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(60));

		// Specify the Kafka Broker Options and set of Topics
		String broker = "localhost:9092";
		Map<String, String> kafkaParameters = new HashMap<String, String>();
		kafkaParameters.put("metadata.broker.list", broker);
		Set<String> topics = Collections.singleton("btc_stream");

		// Create an input DStream using KafkaUtils and simple plain-text
		// message processing
		JavaPairInputDStream<String, String> kafkaDirectStream = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class,
						kafkaParameters, topics);

		// kafkaDirectStream.
		kafkaDirectStream.foreachRDD(rdd -> {
			rdd.foreach(record -> {
				Gson gson = new Gson(); 
				BtcRate btcRate = gson.fromJson(record._2, BtcRate.class); // deserializes
				System.out.println("received:" + record);
				if (btcRate != null) {
					App.sendToElasticSearch(btcRate);
				}
			}
			);
		});

		// Start the computation
		jssc.start();

		// Wait for the computation to terminate
		jssc.awaitTermination();

	}

	/**
	 * Send data to elastic search
	 * @param btcRate
	 * @return
	 */
	static private boolean sendToElasticSearch(BtcRate btcRate) {
		String url = "http://localhost:9200/cs523/bitcoin_rate";
		
		RestTemplate restTemplate = new RestTemplate();
		Object result = restTemplate.postForObject(url, btcRate, Object.class);

		System.out.println(result);
		
		
		return true;
	}
	


}
