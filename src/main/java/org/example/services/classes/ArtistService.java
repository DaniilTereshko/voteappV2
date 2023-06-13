package org.example.services.classes;

import org.example.core.dto.ArtistCreateDTO;
import org.example.core.dto.ArtistDTO;
import org.example.dao.api.IArtistHibernateDao;
import org.example.dao.classes.db.hibernate.entities.ArtistEntity;
import org.example.services.api.IArtistService;

import java.util.ArrayList;
import java.util.List;

public class ArtistService implements IArtistService {
    private final IArtistHibernateDao artistDao;

    public ArtistService(IArtistHibernateDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public List<ArtistDTO> get() {
        List<ArtistEntity> artistEntities = artistDao.get();
        List<ArtistDTO> artists = new ArrayList<>();
        for(ArtistEntity a:artistEntities){
            artists.add(entityToDto(a));
        }
        return artists;
    }

    @Override
    public ArtistDTO get(long id) {
        ArtistEntity artistEntity = artistDao.get(id);
        return entityToDto(artistEntity);
    }

    @Override
    public ArtistDTO save(ArtistCreateDTO item) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName(item.getName());
        ArtistEntity artistEntity = dtoToEntity(artistDTO);

        ArtistEntity savedEntity = artistDao.save(artistEntity);
        return entityToDto(savedEntity);
    }
    private ArtistEntity dtoToEntity(ArtistDTO artistDTO){
        return new ArtistEntity(artistDTO.getId(), artistDTO.getName());
    }
    private ArtistDTO entityToDto(ArtistEntity artistEntity) {
        return new ArtistDTO(artistEntity.getId(), artistEntity.getName());
    }
}
