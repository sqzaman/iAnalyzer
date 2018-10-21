package mum.edu;


import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

import twitter4j.*;
import twitter4j.conf.*;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import kafka.producer.KeyedMessage;

public class KafkaTwitterProducer {
	public static void main(String[] args) throws Exception {
		final LinkedBlockingQueue<Status> queue = new LinkedBlockingQueue<Status>(1000);
/*
		if (args.length < 4) {
			System.out.println(
					"Usage: KafkaTwitterProducer <twitter-consumer-key> <twitter-consumer-secret> <twitter-access-token> <twitter-access-token-secret> <topic-name> <twitter-search-keywords>");
			return;
		}
		*/
		/*
		 * 
		 * 
		 * twitterAgent.sources.twitter-data.consumerKey = JRRZTnth2G4lzh5xiGt9UuciX
twitterAgent.sources.twitter-data.consumerSecret = KDKlAS7FAw1EjEwu0VOgA5w1TrxV0wWu778lnAOwzPIRWrrgJJ
twitterAgent.sources.twitter-data.accessToken = 119006816-araGZEwpfbIzF7PZZuZB68OG8ITs4frLxjywo4Al
twitterAgent.sources.twitter-data.accessTokenSecret = rWJ0EtfrloB8aQ9I7wHrVLVorWNWnkmOE2L4mcYxKUH7e
		 */

		String consumerKey = "JRRZTnth2G4lzh5xiGt9UuciX"; //args[0].toString();
		String consumerSecret = "KDKlAS7FAw1EjEwu0VOgA5w1TrxV0wWu778lnAOwzPIRWrrgJJ"; //args[1].toString();
		String accessToken = "119006816-araGZEwpfbIzF7PZZuZB68OG8ITs4frLxjywo4Al";// args[2].toString();
		String accessTokenSecret = "rWJ0EtfrloB8aQ9I7wHrVLVorWNWnkmOE2L4mcYxKUH7e";// args[3].toString();
		String topicName = "greetingtopic_latest";//args[4].toString();
		String[] arguments = {};// args.clone();
		String[] keyWords = {"java", "hadoop"};//Arrays.copyOfRange(arguments, 5, arguments.length);

		// Set twitter oAuth tokens in the configuration
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);

		// Create twitterstream using the configuration
		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {

			@Override
			public void onStatus(Status status) {
				queue.offer(status);
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				System.out.println("Got scrub_geo event userId:" + userId + "upToStatusId:" + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		};
		twitterStream.addListener(listener);

		// Filter keywords
		FilterQuery query = new FilterQuery().track(keyWords);
		twitterStream.filter(query);

		// Thread.sleep(5000);

		// Add Kafka producer config settings
		Properties props = new Properties();
		props.put("metadata.broker.list", "localhost:9092");
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");//org.apache.kafka.common.serialization.StringSerializer");

		//Producer<String, String> producer = new KafkaProducer<String, String>(props);
		Producer<String, Status> producer = new KafkaProducer<String, Status>(props);
		int i = 0;
		int j = 0;

		// poll for new tweets in the queue. If new tweets are added, send them
		// to the topic
		while (true) {
			Status ret = queue.poll();

			if (ret == null) {
				Thread.sleep(100);
				// i++;
			} else {
				//System.out.println("Tweet:" + ret);
				for (HashtagEntity hashtage : ret.getHashtagEntities()) {
					//System.out.println("Tweet:" + ret);
					//System.out.println("Hashtag: " + hashtage.getText());
					//System.out.println("Text: " + hashtage.);
					// producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(j++), hashtage.getText()));
					//producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(j++), ret.getText()));
					producer.send(new ProducerRecord<String, Status>(topicName, Integer.toString(j++), ret));
				}
			}
		}
		// producer.close();
		// Thread.sleep(500);
		// twitterStream.shutdown();
	}
}
