package org.example.services.classes;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.example.core.dto.ArtistDTO;
import org.example.core.dto.GenreDTO;
import org.example.core.dto.StatisticDTO;
import org.example.core.dto.VoteDTO;
import org.example.services.api.IArtistService;
import org.example.services.api.IGenreService;
import org.example.services.api.IVoteService;
import org.example.services.api.IVoteStatisticService;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VoteStatisticService implements IVoteStatisticService {
    IVoteService voteService;
    IGenreService genreService;
    IArtistService artistService;

    private final Cache<Object, Map<ArtistDTO, Long>> artistTopCache;
    private final Cache<Object, Map<GenreDTO, Long>> genreTopCache;
    private final Cache<Object, List<VoteDTO>> aboutTopCache;
    private final Object artistTopCacheKey = new Object();
    private final Object genreTopCacheKey = new Object();
    private final Object aboutTopCacheKey = new Object();

    public VoteStatisticService(IVoteService voteService, IGenreService genreService, IArtistService artistService) {
        this.voteService = voteService;
        this.genreService = genreService;
        this.artistService = artistService;

        artistTopCache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();

        genreTopCache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();

        aboutTopCache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();
    }
    @Override
    public StatisticDTO getTop() {
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setArtistsTop(this.getArtistsTop());
        statisticDTO.setGenreTop(this.getGenreTop());
        statisticDTO.setAboutTop(this.getAboutTop());
        return statisticDTO;
    }
    @Override
    public Map<GenreDTO, Long> getGenreTop() {
        Map<GenreDTO, Long> genreTop;
        synchronized (Caffeine.class) {
            genreTop = genreTopCache.getIfPresent(genreTopCacheKey);
        }
        if(genreTop != null){
            return genreTop;
        }

        List<VoteDTO> voteDTOList = voteService.get();
        genreTop = voteDTOList.stream()
                .flatMap(v -> v.getGenres().stream())
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(entity->genreService.get(entity.getKey()), Map.Entry::getValue, (e1,e2) ->e1, LinkedHashMap::new));

        synchronized (Caffeine.class) {
            genreTopCache.put(genreTopCacheKey, genreTop);
        }
        return genreTop;
    }
    @Override
    public Map<ArtistDTO, Long> getArtistsTop() {
        Map<ArtistDTO, Long> artistTop;
        synchronized (Caffeine.class) {
            artistTop = artistTopCache.getIfPresent(artistTopCacheKey);
        }
        if(artistTop != null){
            return artistTop;
        }

        List<VoteDTO> voteDTOList = voteService.get();

        artistTop = voteDTOList.stream()
                .map(VoteDTO::getArtist)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(entity -> artistService.get(entity.getKey()), Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        synchronized (Caffeine.class) {
            artistTopCache.put(artistTopCacheKey, artistTop);
        }
        return artistTop;
    }
    @Override
    public List<VoteDTO> getAboutTop() {
        List<VoteDTO> aboutTop;
        synchronized (Caffeine.class) {
            aboutTop = aboutTopCache.getIfPresent(aboutTopCacheKey);
        }
        if(aboutTop != null){
            return aboutTop;
        }
        List<VoteDTO> voteDTOList = voteService.get();
        voteDTOList.sort(Comparator.comparing(VoteDTO::getDate));
        synchronized (Caffeine.class) {
            aboutTopCache.put(aboutTopCacheKey, voteDTOList);
        }
        return voteDTOList;
    }
}
