CREATE SCHEMA `brokerportal` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_ci ;

use brokerportal;

CREATE TABLE `quote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accounting_month` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `insured_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `bus_address` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `bus_city` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `bus_postal_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `bus_state` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `product` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `class_code` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `effective_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `expiration_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `indication_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `indication_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `liability_limit` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `premium` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `quote_number` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `state_cd` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `submission_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `indication_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `submission_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `last_update_date` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `indication_data` longtext CHARACTER SET latin1 COLLATE latin1_general_ci,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `lossnotice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reported_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `loss_notice_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `loss_notice_data_json` LONGTEXT COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
ALTER TABLE lossnotice
ADD user_name varchar(255);

CREATE TABLE `content_management` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `creation_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `file_obj` MEDIUMBLOB DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `contact_details` (
  `contact_details_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `agency_name` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `case_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `severity` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `closed_by` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `closed_at` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`contact_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE contact_details CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE contact_details MODIFY COLUMN description longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `document_container` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `container_name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `file_name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `creation_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `file_obj` MEDIUMBLOB DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `user_role` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `creation_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `policydetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `policy_number` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL,
  `carrier_mode` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `created_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `creation_status` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `premium` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `quote_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `policy_data` longtext COLLATE latin1_general_ci,
  `username` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `insured_name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `product` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `state` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `indication_data` longtext CHARACTER SET latin1 COLLATE latin1_general_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `claimdetails` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `assigned_adjustor` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `claim_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `loss_date` date COLLATE latin1_general_ci DEFAULT NULL,
  `phone_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `policy_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `short_description` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `status_code` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- create table for recent list
create table recentlist (current_owner VARCHAR(50), status VARCHAR(50), policy_effective_date Date,	
						 insured_name VARCHAR(50),	line_of_business VARCHAR(50), reference_no VARCHAR(50));
						 
insert into recentlist (`current_owner`, `status`, `policy_effective_date`, `insured_name`, `line_of_business`, `reference_no`)
values ('admin','In Progress','2020-08-09','Jackson Samuel','Commercial Excess','AP-12331122');	

insert into recentlist (`current_owner`, `status`, `policy_effective_date`, `insured_name`, `line_of_business`, `reference_no`)
values ('admin','Active','2022-03-04','Williamson','Commercial Product','QT-354232452');	

insert into recentlist (`current_owner`, `status`, `policy_effective_date`, `insured_name`, `line_of_business`, `reference_no`)
values ('admin','Renewal','2021-07-03','Bennet Bruce','Excess Liability','CLM-987653222');	

insert into recentlist (`current_owner`, `status`, `policy_effective_date`, `insured_name`, `line_of_business`, `reference_no`)
values ('admin','Transaction','2019-01-02','Liam Neeson','Workers Comp','TX-12331122');				

		 
-- create table for openwork						 
create table openwork (application_no VARCHAR(50), customer_no int, effective_date Date,	
						 state VARCHAR(50) ,insured_name VARCHAR(50),	transaction_type VARCHAR(50), product_type VARCHAR(50), days_in_status int);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`, `insured_name`, `transaction_type`, `product_type`,
    `openwork`.`days_in_status`)
values ('AP-00000122',1111,'2020-08-09','California','George','In Progress','Commercial Excess',40);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`, `insured_name`, `transaction_type`, `product_type`,
    `openwork`.`days_in_status`)
values ('QT-12345566',12345,'2022-01-01','Arizona','Maxwell','Active','Commercial Auto',10);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`, `insured_name`, `transaction_type`, `product_type`,
    `openwork`.`days_in_status`)
values ('AP-00022111',87654,'2021-08-09','Washington D.C','John Samuel','Active','Commercial Package',13);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`, `insured_name`, `transaction_type`, `product_type`,
    `openwork`.`days_in_status`)
values ('AP-002120122',13,'2019-03-04','New York','Warner','In Progress','Home Owners',22);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('QT-00008023',2211,'2021-08-09','Michigan','Marcus Stonius','Cancellation','Commercial Package Policy',35);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('XL00200246',3214,'2022-03-04','Madison','Clive Lloyd','Endorsement', 'Commercial Package Policy',97);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('COM00200849',0976,'2020-01-01','New York','Mike Lowrey','Application', 'Commercial Excess Liability',86);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('COM00200850',3650 ,'2020-02-04','New Jersey','Samuel Jackson','Application','Commercial Auto', 75);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('QT-00000051',4545,'2022-04-09','New Hampshire','Shaun Pollock','Reinstatement','Commercial Auto',43);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('CPP00000054',5825,'2020-02-02','South Dakota','Steve Waugh','Cancellation', 'Commercial Package Policy', 52);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('TX00000190',4105,'2022-07-02','North Dakota','Brian Lara','Policy','Commercial Auto', 24);

insert into openwork (`application_no`, `customer_no`, `effective_date`, `state`,`insured_name`, `transaction_type`, `product_type`,`days_in_status`)
values ('CLM00200051',5610,'2022-05-04','Washington D.C','Mark Taylor','Claim', 'Commercial Excess Liability',45);

-- insert scripts for policy details

insert into policydetails (`policy_number`, `business_address`, `effective_date`, `expiration_date`,`insured_name`, `mailing_address`, `policy_term`)
values ('QT-00008023','260-C North El Camino Real, Encinitas, CA, 92024-2852','2020-08-09','2021-08-09','Portal Agent','260-C North El Camino Real, Encinitas, CA, 92024-2852','1 Year');	

insert into policydetails (`policy_number`, `business_address`, `effective_date`, `expiration_date`,`insured_name`, `mailing_address`, `policy_term`)
values ('XL00200246','591 Grand Avenue, Suite G102, San Marcos, CA, 92069','2022-03-04','2023-03-04','William Webber','591 Grand Avenue, Suite G102, San Marcos, CA, 92069', '1 Year');	

insert into policydetails (`policy_number`, `business_address`, `effective_date`, `expiration_date`,`insured_name`, `mailing_address`, `policy_term`)
values ('admin','Renewal','2021-07-03','Bennet Bruce','Excess Liability','CLM-987653222');	

insert into policydetails (`policy_number`, `business_address`, `effective_date`, `expiration_date`,`insured_name`, `mailing_address`, `policy_term`)
values ('admin','Transaction','2019-01-02','Liam Neeson','Workers Comp','TX-12331122');	

-- insert scripts for claim details
insert into claimdetails(id, assigned_adjustor, claim_number, loss_date, phone_number, policy_number, short_description, status_code)
values('1', 'William', 'TX-00000123', '2022-01-01', '901-987-0987', 'COM00123012', 'Loss ouccurred during Collision', 'In Progress');

insert into claimdetails(id, assigned_adjustor, claim_number, loss_date, phone_number, policy_number, short_description, status_code)
values('2', 'Samuel', 'CLM12345678', '2021-08-09', '123-456-0987', 'COM00123012', 'Fire Incident', 'Active');

-- user table
insert into user(user_name,password,user_role,creation_date)
values('admin','9999','admin','2020-07-01');

-- creat prdocuer_info table
CREATE TABLE `producer_info` (
  `producer_info_id` int NOT NULL AUTO_INCREMENT,
  `producer_code` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `agency_code` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `group_code` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `name` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `email` varchar(45) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`producer_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `login_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(225) COLLATE latin1_general_ci NOT NULL,
  `email` varchar(225) COLLATE latin1_general_ci DEFAULT NULL,
  `last_used` varchar(225) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;


INSERT INTO `brokerportal`.`producer_info`
(`producer_code`,`agency_code`,`group_code`,`name`,`email`)
VALUES
('test101','test102','test103','Test','test@gmail.com');

CREATE TABLE `app_config` (
  `id` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `keytype` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `filecontent` longblob,
  `prop_carrier_mode` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;


CREATE TABLE `agent_feedback` (
  `agent_feedback_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(300) COLLATE latin1_general_ci DEFAULT NULL,
  `feedback` longtext COLLATE latin1_general_ci,
  `user_name` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `feedback_date` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`agent_feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;


CREATE TABLE `agent_suggestion` (
  `agent_suggestion_id` int NOT NULL AUTO_INCREMENT,
  `suggestion` longtext COLLATE latin1_general_ci,
  `user_name` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `suggestion_date` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`agent_suggestion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;


CREATE TABLE `contentmanagement` (
  `id` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `classification` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `description` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `filecontent` longblob,
  `name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;


CREATE TABLE `documentcontainer` (
  `id` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `description` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `filecontent` longblob,
  `name` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `policy_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `type` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;


CREATE TABLE `task` (
  `id` int NOT NULL,
  `agent_email` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `agent_id` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `creation_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `quote_number` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `reminder_date` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `task_description` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `task_id` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
