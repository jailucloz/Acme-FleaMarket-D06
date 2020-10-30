
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `banner` 
       drop 
       foreign key `FKjoxwdnjr54soq3j89kt3fgrtj`;

    alter table `buyer` 
       drop 
       foreign key FK_630a954if6nal5afofvjy73ob;

    alter table `credit_card` 
       drop 
       foreign key `FKa4pbn9v8sv66p46fsrke8ow89`;

    alter table `item` 
       drop 
       foreign key `FK7r7pmef5wvaepffbi0xfrso2c`;

    alter table `request_item` 
       drop 
       foreign key `FKl95xyup7566n80564w7082s`;

    alter table `request_item` 
       drop 
       foreign key `FKj6yooir0deg6w70lmjtf4pa1k`;

    alter table `request_item` 
       drop 
       foreign key `FKc2csi1fre6mjxwc8g5492iqcv`;

    alter table `sheet` 
       drop 
       foreign key `FKot1q9gh25o69kcn1fxi26xi2l`;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `supplier` 
       drop 
       foreign key FK_1h83guf8tf3di74bk4uhuo1ia;

    drop table if exists `administrator`;

    drop table if exists `advertisement`;

    drop table if exists `anonymous`;

    drop table if exists `authenticated`;

    drop table if exists `banner`;

    drop table if exists `buyer`;

    drop table if exists `credit_card`;

    drop table if exists `customisation`;

    drop table if exists `figment`;

    drop table if exists `item`;

    drop table if exists `lucas_bulletin`;

    drop table if exists `material_sheet`;

    drop table if exists `news`;

    drop table if exists `request_item`;

    drop table if exists `sheet`;

    drop table if exists `sponsor`;

    drop table if exists `suggestion`;

    drop table if exists `supplier`;

    drop table if exists `tool_sheet`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;
