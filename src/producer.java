import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class producer {
    public static void main(String[] args) {
        KafkaProducer producer;
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"producer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        producer=new KafkaProducer(props);
        Scanner input =new Scanner(System.in);
        while (true) {
            System.out.println("enter temperature ");
            String temperature = input.next();
            System.out.println(temperature);
            System.out.println("enter humidity ");
            String humidity = input.next();
            System.out.println(humidity);
            String sendval=String.format("{'temp':"+temperature+",'humidity':"+humidity+"}") ;
            producer.send(new ProducerRecord("TempMonitoring", sendval));

//            producer.close();
        }
    }
}
