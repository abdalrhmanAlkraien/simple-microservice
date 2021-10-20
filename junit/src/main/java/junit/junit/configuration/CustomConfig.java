package junit.junit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class CustomConfig {

    private static final AtomicLong TS = new AtomicLong();

    @Bean
    public void getUUID() {
        Long id = getUniqueTimestamp();
        System.out.println("id = " + id);
        id = getUniqueTimestamp();
        System.out.println("id = " + id);

    }

    public static long getUniqueTimestamp() {
        return TS.incrementAndGet();
    }
}
