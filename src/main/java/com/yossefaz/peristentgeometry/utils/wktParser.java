package com.yossefaz.peristentgeometry.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class wktParser {
    public static Polygon wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Polygon geom;
        try {
            geom = (Polygon) fromText.read(wktPoint);
            geom.setSRID(2039);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }
}
