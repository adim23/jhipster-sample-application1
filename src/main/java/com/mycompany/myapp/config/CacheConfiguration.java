package com.mycompany.myapp.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries())
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build()
        );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.Destination.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Destination.class.getName() + ".tours");
            createCache(cm, com.mycompany.myapp.domain.Destination.class.getName() + ".places");
            createCache(cm, com.mycompany.myapp.domain.Destination.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.Destination.class.getName() + ".menus");
            createCache(cm, com.mycompany.myapp.domain.Destination.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.PlaceCategory.class.getName());
            createCache(cm, com.mycompany.myapp.domain.PlaceCategory.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.PlaceCategory.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.PlaceCategory.class.getName() + ".places");
            createCache(cm, com.mycompany.myapp.domain.Place.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Place.class.getName() + ".tourSteps");
            createCache(cm, com.mycompany.myapp.domain.Place.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.Place.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.Place.class.getName() + ".tags");
            createCache(cm, com.mycompany.myapp.domain.Place.class.getName() + ".categories");
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName() + ".defaultTours");
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName() + ".menus");
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.TourCategory.class.getName() + ".tours");
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName() + ".steps");
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName() + ".tourExtras");
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName() + ".tags");
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName() + ".promotions");
            createCache(cm, com.mycompany.myapp.domain.Tour.class.getName() + ".categories");
            createCache(cm, com.mycompany.myapp.domain.TourStep.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TourStep.class.getName() + ".infos");
            createCache(cm, com.mycompany.myapp.domain.Promotion.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Promotion.class.getName() + ".titles");
            createCache(cm, com.mycompany.myapp.domain.Promotion.class.getName() + ".tours");
            createCache(cm, com.mycompany.myapp.domain.TourSchedule.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TourSchedule.class.getName() + ".bookings");
            createCache(cm, com.mycompany.myapp.domain.TourExtraCategory.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TourExtraCategory.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.TourExtraCategory.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.TourExtraCategory.class.getName() + ".extras");
            createCache(cm, com.mycompany.myapp.domain.TourExtra.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TourExtra.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.TourExtra.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.TourExtra.class.getName() + ".tags");
            createCache(cm, com.mycompany.myapp.domain.TourExtra.class.getName() + ".categories");
            createCache(cm, com.mycompany.myapp.domain.TourExtra.class.getName() + ".tours");
            createCache(cm, com.mycompany.myapp.domain.Driver.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Driver.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.Vehicle.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Vehicle.class.getName() + ".images");
            createCache(cm, com.mycompany.myapp.domain.Booking.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Passenger.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Passenger.class.getName() + ".bookings");
            createCache(cm, com.mycompany.myapp.domain.ImageFile.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ImageFile.class.getName() + ".captions");
            createCache(cm, com.mycompany.myapp.domain.WebPage.class.getName());
            createCache(cm, com.mycompany.myapp.domain.WebPage.class.getName() + ".menus");
            createCache(cm, com.mycompany.myapp.domain.WebPage.class.getName() + ".contents");
            createCache(cm, com.mycompany.myapp.domain.WebPage.class.getName() + ".tags");
            createCache(cm, com.mycompany.myapp.domain.Menu.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Menu.class.getName() + ".children");
            createCache(cm, com.mycompany.myapp.domain.Menu.class.getName() + ".names");
            createCache(cm, com.mycompany.myapp.domain.Language.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Content.class.getName());
            createCache(cm, com.mycompany.myapp.domain.TourContent.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Prompt.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName() + ".names");
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName() + ".places");
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName() + ".tours");
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName() + ".tourExtras");
            createCache(cm, com.mycompany.myapp.domain.Tag.class.getName() + ".webPages");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
