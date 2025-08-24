create table animes (
                        id bigserial primary key,
                        title varchar(120) not null,
                        status varchar(20) not null,
                        episodes_watched int,
                        score int,
                        started_at date,
                        finished_at date,
                        synopsis varchar(1000)
);

create table anime_genres (
                              anime_id bigint not null,
                              genre varchar(50) not null,
                              constraint fk_anime_genres_anime foreign key (anime_id) references animes(id) on delete cascade
);