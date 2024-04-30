package pl.hibernate.example.infrastructure.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextInitializer implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }


    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextInitializer.applicationContext = applicationContext;
    }


}

