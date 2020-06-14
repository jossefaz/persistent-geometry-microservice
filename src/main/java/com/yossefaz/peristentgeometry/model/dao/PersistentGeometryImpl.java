package com.yossefaz.peristentgeometry.model.dao;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;
import com.yossefaz.peristentgeometry.utils.wktParser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PersistentGeometryImpl implements PersistentGeometryService{

    public static List<PersistentGeometry> persistentGeometries = new ArrayList<>();

    static {
persistentGeometries.add(PersistentGeometry.builder().id(UUID.randomUUID()).geometry(wktParser.wktToGeometry("POLYGON((220376.84588623047 634704.92137146,217165.4490966797 634368.2426757812,217269.0425415039 630198.6065216064,220091.96391296387 630431.6917724609,220376.84588623047 634704.92137146))")).build());
persistentGeometries.add(PersistentGeometry.builder().id(UUID.randomUUID()).geometry(wktParser.wktToGeometry("POLYGON((215507.9539794922 632374.068862915,211778.5899658203 631959.6950836182,211182.92765808105 629499.350769043,213021.71130371094 628981.3835449219,215507.9539794922 632374.068862915))")).build());
persistentGeometries.add(PersistentGeometry.builder().id(UUID.randomUUID()).geometry(wktParser.wktToGeometry("POLYGON((215300.76708984375 629059.07862854,218693.4524078369 629577.0458526611,219185.52127075195 627608.770401001,215300.76708984375 629059.07862854))")).build());
    }


    @Override
    public List<PersistentGeometry> findAll() {
        return persistentGeometries;
    }

    @Override
    public PersistentGeometry findById(UUID id) {
        return null;
    }

    @Override
    public PersistentGeometry save(PersistentGeometryService persistentGeometryService) {
        return null;
    }
}
