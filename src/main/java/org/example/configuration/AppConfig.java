package org.example.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.example.controllers.web.servlets.ArtistController;
import org.example.controllers.web.servlets.GenreController;
import org.example.core.mappers.VoteMapperUtil;
import org.example.dao.repositories.IArtistRepository;
import org.example.dao.repositories.IGenreRepository;
import org.example.dao.repositories.IVoteRepository;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;
import org.example.services.api.IVoteStatisticService;
import org.example.services.classes.ArtistService;
import org.example.services.classes.GenreService;
import org.example.services.classes.VoteService;
import org.example.services.classes.VoteStatisticService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableJpaRepositories("org.example.dao.repositories")
/*@EnableWebMvc*/
public class AppConfig {
    @Bean
    public ViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jspx");
        return resolver;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("org.example.dao.entities");
        HibernateJpaVendorAdapter hibernate = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(hibernate);
        entityManagerFactory.setPersistenceUnitName("voteapp");

        return entityManagerFactory;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return mapper;
    }

    @Bean
    public VoteMapperUtil voteMapperUtil(ModelMapper modelMapper, IArtistRepository artistRepository, IGenreRepository genreRepository) {
        return new VoteMapperUtil(modelMapper, artistRepository, genreRepository);
    }

    @Bean
    public IGenreService genreService(IGenreRepository genreRepository, ModelMapper modelMapper){
        return new GenreService(genreRepository, modelMapper);
    }
    @Bean
    public IArtistService artistService(IArtistRepository artistRepository, ModelMapper modelMapper){
        return new ArtistService(artistRepository, modelMapper);
    }
    @Bean
    public IVoteService voteService(IVoteRepository voteRepository, IArtistService artistService, IGenreService genreService, VoteMapperUtil voteMapperUtil){
        return new VoteService(voteRepository, artistService, genreService, voteMapperUtil);
    }
    @Bean
    public IVoteStatisticService voteStatisticService(IVoteService voteService, IGenreService genreService, IArtistService artistService, VoteMapperUtil voteMapperUtil){
        return new VoteStatisticService(voteService, genreService, artistService, voteMapperUtil);
    }
    @Bean
    public ArtistController artistController(IArtistService artistService, ModelMapper modelMapper){
        return new ArtistController(artistService, modelMapper);
    }
    @Bean
    public GenreController genreController(IGenreService genreService, ModelMapper modelMapper){
        return new GenreController(genreService, modelMapper);
    }
/*    @Bean
    public VoteController voteController(IArtistService artistService, IGenreService genreService, IVoteService voteService){
        return new VoteController(artistService, genreService, voteService);
    }
    @Bean
    public VoteResultsController voteResultsController(IVoteStatisticService voteStatisticService){
        return new VoteResultsController(voteStatisticService);
    }*/
}
