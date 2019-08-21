   
    create table `brand` (
        `id` bigint not null auto_increment,
        `brand_name` varchar(255) not null,
        `origin` varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;

    
    create table `maintence` (
        `id` bigint not null auto_increment,
        `date_maintence` datetime not null,
        `description` varchar(255) not null,
        `price_maintence` double precision not null,
        `type_maintence` varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;

    
    create table `model` (
        `id` bigint not null auto_increment,
        `model_name` varchar(255) not null,
        `model_year` datetime,
        `year` datetime,
        `brand_id` bigint,
        `user_id` bigint,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;

    
    create table `part` (
        `id` bigint not null auto_increment,
        `geunine` bit not null,
        `name` varchar(255) not null,
        `price` double precision not null,
        `maintence_id` bigint,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;
    
    create table `user` (
        `id` bigint not null auto_increment,
        `email` varchar(255) not null,
        `login` varchar(255) not null,
        `name` varchar(255) not null,
        `password` varchar(255) not null,
        primary key (id)
    ) engine=InnoDB DEFAULT CHARSET=utf8;

    
    alter table model 
       add constraint FKhbgv4j3vpt308sepyq9q79mhu 
       foreign key (brand_id) 
       references brand (id);
    
    alter table model 
       add constraint FKrmdvh2mj4uuwrlpjnwgev2lf  
       foreign key (user_id) 
       references user (id) ;
    
    alter table part 
       add constraint FKewaftm3q30ofkq1lr4gne9i1d 
       foreign key (maintence_id) 
       references maintence (id);