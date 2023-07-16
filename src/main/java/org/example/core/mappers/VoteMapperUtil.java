package org.example.core.mappers;

import jakarta.annotation.PostConstruct;
import org.example.core.dto.VoteDTO;
import org.example.dao.entities.Genre;
import org.example.dao.entities.Vote;
import org.example.dao.repositories.IArtistRepository;
import org.example.dao.repositories.IGenreRepository;
import org.modelmapper.ModelMapper;

import java.util.Objects;
import java.util.stream.Collectors;

public class VoteMapperUtil extends AbstractMapper<VoteDTO, Vote> {
    private final ModelMapper modelMapper;
    private final IArtistRepository artistRepository;
    private final IGenreRepository genreRepository;

    public VoteMapperUtil(ModelMapper modelMapper, IArtistRepository artistRepository, IGenreRepository genreRepository) {
        super(Vote.class, VoteDTO.class);
        this.modelMapper = modelMapper;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    private void setupMapper() {
        modelMapper.createTypeMap(Vote.class, VoteDTO.class)
                .addMappings(m -> m.skip(VoteDTO::setArtistId))
                .addMappings(m -> m.skip(VoteDTO::setGenreId))
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(VoteDTO.class, Vote.class)
                .addMappings(m -> m.skip(Vote::setArtist))
                .addMappings(m -> m.skip(Vote::setGenres))
                .setPostConverter(toEntityConverter());
    }
    private void mapSpecificFields(Vote source, VoteDTO destination) {
        if(Objects.isNull(source)) {
            destination.setArtistId(Objects.isNull(source.getId()) ? null : source.getArtist().getId());
            destination.setGenreId(Objects.isNull(source.getGenres()) ? null : source.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        }
    }

    private void mapSpecificFields(VoteDTO source, Vote destination) {
        destination.setArtist(artistRepository.findById(source.getArtistId()).orElse(null));
        destination.setGenres(genreRepository.findByIdIn(source.getGenreId()));
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
