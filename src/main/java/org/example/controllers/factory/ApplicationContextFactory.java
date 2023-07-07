package org.example.controllers.factory;

public class ApplicationContextFactory {
    private static ApplicationContextBean applicationContext = null;

    private ApplicationContextFactory() {
    }

    public static ApplicationContextBean getInstance(){
        if(applicationContext == null){
            synchronized (ApplicationContextFactory.class){
                if(applicationContext == null){
                    applicationContext = new ApplicationContextBean();
                }
            }
        }
        return applicationContext;
    }
}
