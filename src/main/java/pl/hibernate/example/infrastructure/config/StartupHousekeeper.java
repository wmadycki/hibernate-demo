package pl.hibernate.example.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.hibernate.example.modules.examples.ModelExecutor;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ModelExecutor modelExecutor;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        modelExecutor.createModel();
    }
}
