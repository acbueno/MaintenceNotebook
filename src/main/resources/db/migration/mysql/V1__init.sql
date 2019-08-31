    create table brand (
       id bigint not null auto_increment,
        i18n_brand_name varchar(255) not null,
        brand_name varchar(255) not null,
        origin varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table fuel (
       id bigint not null auto_increment,
        i18n_key varchar(255) not null,
        type varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table maintence (
       id bigint not null auto_increment,
        date_maintence datetime not null,
        description varchar(255) not null,
        price_maintence double precision not null,
        type_maintence varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table model (
       id bigint not null auto_increment,
        name varchar(255) not null,
        version varchar(255) not null,
        brand_id bigint,
        fuel_id bigint,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table part (
       id bigint not null auto_increment,
        geunine bit not null,
        name varchar(255) not null,
        price double precision not null,
        maintence_id bigint,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table user (
       id bigint not null auto_increment,
        email varchar(255) not null,
        login varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table user_models (
       id bigint not null auto_increment,
        model_year datetime not null,
        year datetime not null,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    create table user_models_model (
       user_models_id bigint not null,
        model_id bigint not null
    ) engine=InnoDB DEFAULT CHARSET=utf8;
 
    
    alter table model 
       add constraint FKhbgv4j3vpt308sepyq9q79mhu 
       foreign key (brand_id) 
       references brand (id);
 
    
    alter table model 
       add constraint FKj0hah1kdhe1mqqw7q7vbt3vlv 
       foreign key (fuel_id) 
       references fuel (id);
 
    
    alter table part 
       add constraint FKewaftm3q30ofkq1lr4gne9i1d 
       foreign key (maintence_id) 
       references maintence (id);
 
    
    alter table user_models 
       add constraint FKn8y22owf8tflfa4hxfwfi0usd 
       foreign key (user_id) 
       references user (id);
 
    
    alter table user_models_model 
       add constraint FKtnwugsddrcm7ktne21m6ore2g 
       foreign key (model_id) 
       references model (id);
 
    
    alter table user_models_model 
       add constraint FK7x82ox11fdftrrh9jgvb2qhfk 
       foreign key (user_models_id) 
       references user_models (id);