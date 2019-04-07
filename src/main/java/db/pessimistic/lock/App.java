package db.pessimistic.lock;

import db.pessimistic.lock.model.Request;
import db.pessimistic.lock.repository.RequestRepository;
import db.pessimistic.lock.service.RequestService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication(scanBasePackages = "db.pessimistic.lock")
@EnableJpaRepositories(basePackages = "db.pessimistic.lock")
@EntityScan(basePackages = "db.pessimistic.lock")
@EnableTransactionManagement
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(App.class)
                .properties("spring.config.name: sql-db")
                .build()
                .run(args);
        RequestService requestService = ctx.getBean(RequestService.class);
        RequestRepository requestRepository = ctx.getBean(RequestRepository.class);
        ExecutorService service = Executors.newFixedThreadPool(15);
        Request request = new Request().setName("Concurrency SLA Update").setCode(1).setSla(0);
        requestRepository.save(request);
        for (int i = 0; i < 1000; i++) {
            service.submit(() -> requestService.incrementSla(1));
            service.submit(() -> requestService.incrementSla(1));
        }
        System.out.println("Test finished");
        service.shutdown();
    }

}
