create table if not exists persistent_line
(
    id       uuid,
    geometry geometry
);

alter table persistent_line
    owner to postgres;

grant select on persistent_line to replicator;

create table if not exists persistent_polygon
(
    id       uuid,
    geometry geometry(Polygon, 2039)
);

alter table persistent_polygon
    owner to postgres;

grant select on persistent_polygon to replicator;

