package org.example.services.classes;

import org.example.core.dto.ArtistCreateDTO;
import org.example.core.dto.ArtistDTO;
import org.example.dao.repositories.IArtistRepository;
import org.example.dao.entities.Artist;
import org.example.services.api.IArtistService;

import java.util.ArrayList;
import java.util.List;

public class ArtistService implements IArtistService {
    private final IArtistRepository artistDao;

    public ArtistService(IArtistRepository artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public List<ArtistDTO> get() {
        List<Artist> artistEntities = artistDao.findAll();
        List<ArtistDTO> artists = new ArrayList<>();
        for(Artist a:artistEntities){
            artists.add(entityToDto(a));
        }
        return artists;
    }

    @Override
    public ArtistDTO get(long id) {
        Artist artist = artistDao.findById(id).get();
        return entityToDto(artist);
    }

    @Override
    public ArtistDTO save(ArtistCreateDTO item) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName(item.getName());
        Artist artist = dtoToEntity(artistDTO);

        Artist savedEntity = artistDao.save(artist);
        return entityToDto(savedEntity);
    }
    private Artist dtoToEntity(ArtistDTO artistDTO){
        return new Artist(artistDTO.getId(), artistDTO.getName());
    }
    private ArtistDTO entityToDto(Artist artist) {
        return new ArtistDTO(artist.getId(), artist.getName());
    }
}
