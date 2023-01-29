package com.predictor;

import com.predictor.config.kafka.KafkaProducerConfig;
import com.predictor.fetcher.adapters.BinancePriceClient;
import com.predictor.fetcher.domain.ports.PriceDto;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { KafkaProducerConfig.class, KafkaTestConsumer.class })
@DirtiesContext*/

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
//@SpringJUnitConfig(ScheduledConfig.class)
class ActualPriceSenderToProcessSchedulerIT {
    @InjectMocks
    private KafkaProducerConfig producerConfig;

    @InjectMocks
    private BinancePriceClient client;

    @Autowired
    private KafkaTestConsumer testConsumer;

    @Autowired
    private ActualPriceSenderToProcessScheduler tested;

    @ClassRule
    public static KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    @Test
    public void shouldSendActualPriceToProcess() throws InterruptedException {
        //when
        //tested.sendActualPriceToProcess();

        //then
        /*await().atMost(Duration.ofSeconds(20))
                .untilAsserted(() -> {
                    //boolean messageConsumed = testConsumer.getLatch().await(10, TimeUnit.SECONDS);
                    // assertTrue(messageConsumed);
                    PriceDto priceDto = testConsumer.getPriceDto();
                    System.out.println();
                });*/

        Thread.sleep(20000L);
        //PriceDto priceDto = testConsumer.receive();
        System.out.println("Elo");

    }
}