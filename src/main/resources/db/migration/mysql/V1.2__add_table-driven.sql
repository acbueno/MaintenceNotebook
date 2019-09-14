ALTER TABLE model
	ADD id_distance_driven bigint;
	
	CREATE TABLE IF NOT EXISTS distance_driven (
       id bigint not null auto_increment,
       distance_driven bigint DEFAULT 0,
       measure_type varchar(255) not null,
       primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
	
    ALTER TABLE model ADD CONSTRAINT id_fk_distance_driven
    FOREIGN KEY(id_distance_driven) REFERENCES distance_driven (id);
