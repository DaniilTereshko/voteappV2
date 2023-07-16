package org.example.services.api;

import org.example.core.dto.ArtistCreateDTO;
import org.example.core.dto.ArtistDTO;
import org.example.dao.entities.Artist;

public interface IArtistService extends ICRUDService<Artist, ArtistCreateDTO>{

}
