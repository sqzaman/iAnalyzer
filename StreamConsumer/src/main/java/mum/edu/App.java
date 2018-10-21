package mum.edu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import kafka.serializer.StringDecoder;
import scala.Function1;
import scala.Tuple2;
import twitter4j.Status;

import com.google.gson.Gson;

public class App 
{ 
	
	public static void main(String[] args) throws InterruptedException {
		


    // Create a Java Streaming Context with a Batch Interval of 5 seconds
    SparkConf conf = new SparkConf().setAppName("KafkaSparkr").setMaster("local");
    JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

    // Specify the Kafka Broker Options and set of Topics
    String broker = "localhost:9092";
    Map<String, String> kafkaParameters = new HashMap<String, String>();
    kafkaParameters.put("metadata.broker.list", broker);
    Set<String> topics = Collections.singleton("greetingtopic_latest");

    // Create an input DStream using KafkaUtils and simple plain-text message processing
    JavaPairInputDStream<String, String> kafkaDirectStream = KafkaUtils.createDirectStream(jssc,
            String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParameters, topics);
    
    JavaDStream<String> lines = kafkaDirectStream.map(new Function<Tuple2<String, String>, String>() { @Override public String call(Tuple2<String, String> tuple2) { return tuple2._2(); } });

    lines.dstream().saveAsTextFiles("output-latest", "abc");
    
    //lines.dstream().saveAsTextFiles("output", ".csv");
    //lines.

    //JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(jssc, String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);
    //JavaDStream<String> lines = messages.map(new Function<Tuple2<String, String>, String>() { @Override public String call(Tuple2<String, String> tuple2) { return tuple2._2(); } });
    //lines.dstream().saveAsTextFiles(pathtohdfs);
 
    
    //kafkaDirectStream.
    lines.foreachRDD(rdd -> {
        rdd.foreach(
        		record -> {
        			//System.out.println("received:" +record._2);
        	        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        	        BtcRate btcRate = gson.fromJson(record, BtcRate.class); // deserializes json into BtcRate
        	        System.out.println("received:" + record);
        	        if(btcRate != null) {
        	        	  App.sendToElasticSearch(btcRate);
        	        }
        	      
        	        //record.s
        		}
        		
        		);

    });

    // Start the computation
    jssc.start();

    // Wait for the computation to terminate
    jssc.awaitTermination();
    
    
}

	static private boolean sendToElasticSearch(BtcRate btcRate) {
		String url = "http://localhost:9200/cs523/bitcoin_rate";
		 RestTemplate restTemplate = new RestTemplate();
		    Object result = restTemplate.postForObject( url, btcRate, Object.class);
		 
		    System.out.println(result);
		return true;
	}
	


}
