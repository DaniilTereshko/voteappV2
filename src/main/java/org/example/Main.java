package org.example;

import org.example.controllers.factory.ApplicationContextBean;
import org.example.services.api.IGenreService;

public class Main {
    public static void main(String[] args) {
        ApplicationContextBean applicationContextBean = new ApplicationContextBean();
        IGenreService genreService = applicationContextBean.getGenreService();
    }
}
