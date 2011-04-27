-- especialidades	
INSERT INTO `specialty` (`id`,`name`)
VALUES
	(1, 'Ortopedia'),
	(2, 'Pediatria');
	
-- medicos
INSERT INTO `doctor` (`id`,`active`,`crm`,`email`,`name`,`observations`,`telephone`,`specialty_id`)
VALUES
	(1, 1, '54.132', 'aniche@uol.com.br', 'Dr. Roberto Aniche', '', '(11) 9175-9067', 1);

-- usuarios
INSERT INTO `employee` (`id`,`active`,`cellphone`,`email`,`login`,`name`,`observations`,`password`,`position`,`doctor_id`)
VALUES
	(1, 1, NULL, 'mauricioaniche@gmail.com', 'homolog', 'Homolog', NULL, '123', 'OWNER', NULL);

-- procedimentos
INSERT INTO `medical_procedures` (`id`,`ambCode`,`name`,`tussCode`,`cbhpmCode`,`lpmCode`)
VALUES
	(1, '1111', 'procedimento 1', '1111', '1111', '1111'),
	(2, '2222', 'procedimento 2', '2222', '2222', '2222'),
	(3, '3333', 'procedimento 3', '3333', '3333', '3333'),
	(4, '4444', 'procedimento 4', '4444', '4444', '4444'),
	(5, '5555', 'procedimento 5', '5555', '5555', '5555');

-- materiais
INSERT INTO `material` (`id`,`name`,`unit`)
VALUES
	(1, 'material 1', ''),
	(2, 'material 2', ''),
	(3, 'material 3', ''),
	(4, 'material 4', ''),
	(5, 'material 5', '');

INSERT INTO `materialinprocedure` (`id`,`qty`,`material_id`,`procedure_id`)
VALUES
	(1, 1.00, 1, 1);
	
INSERT INTO `medicineinprocedure` (`id`,`qty`,`medicine_id`,`procedure_id`)
VALUES
	(1, 1.00, 1, 1);


-- medicamentos
INSERT INTO `medicine` (`id`,`name`,`code`)
VALUES
	(1, 'remedio 1', '1'),
	(2, 'remedio 2', '2'),
	(3, 'remedio 3', '3'),
	(4, 'remedio 4', '4');

-- convenios
-- PARTICULAR
INSERT INTO `healthcareplan` (`id`,`active`,`city`,`neighborhood`,`postalCode`,`state`,`street`,`ch`,`contact`,`email`,`name`,`observations`,`payForRoomRate`,`periodToReturn`,`roomRateDefaultAmount`,`telephone`,`website`)
VALUES
	(1, 1, 'SP', 'SP', '12121-123', 'SP', 'SP', 0.70, 'John Doe', 'mauricioaniche@gmail.com', 'Particular', NULL, 0, 0, NULL, NULL, NULL);

-- precos
INSERT INTO `precifiedmaterial` (`id`,`amount`,`healthCarePlan_id`,`material_id`)
VALUES
	(1, 100.00, 1, 1),
	(2, 4.00, 1, 5),
	(3, 15.40, 1, 3),
	(4, 47.06, 1, 4),
	(5, 12.14, 1, 2);

INSERT INTO `precifiedmedicine` (`id`,`amount`,`healthCarePlan_id`,`medicine_id`)
VALUES
	(1, 200.00, 1, 1),
	(2, 50.00, 1, 3),
	(3, 40.30, 1, 2),
	(4, 15.25, 1, 4);

INSERT INTO `precifiedprocedure` (`id`,`fixedAmount`,`healthCarePlan_id`,`procedure_id`,`ch`)
VALUES
	(1, 0.00, 1, 1, 55),
	(2, 0.00, 1, 4, 12),
	(3, 0.00, 1, 2, 20),
	(4, 0.00, 1, 5, 30),
	(5, 0.00, 1, 3, 60);

INSERT INTO `precifiedspecialty` (`id`,`amount`,`healthCarePlan_id`,`specialty_id`)
VALUES
	(1, 100.00, 1, 2),
	(2, 250.00, 1, 1);

-- convenios
-- Amil
INSERT INTO `healthcareplan` (id, `active`,`city`,`neighborhood`,`postalCode`,`state`,`street`,`ch`,`contact`,`email`,`name`,`observations`,`payForRoomRate`,`periodToReturn`,`roomRateDefaultAmount`,`telephone`,`website`)
VALUES
	(2, 1, 'SP', 'SP', '12121-123', 'SP', 'SP', 0.70, 'John Doe', 'mauricioaniche@gmail.com', 'Amil', NULL, 0, 0, NULL, NULL, NULL);

-- precos
INSERT INTO `precifiedmaterial` (`amount`,`healthCarePlan_id`,`material_id`)
VALUES
	(120.00, 2, 1),
	(6.00, 2, 5),
	(17.40, 2, 3),
	(41.06, 2, 4),
	(13.14, 2, 2);

INSERT INTO `precifiedmedicine` (`amount`,`healthCarePlan_id`,`medicine_id`)
VALUES
	(220.00, 2, 1),
	(53.00, 2, 3),
	(41.70, 2, 2),
	(15.00, 2, 4);

INSERT INTO `precifiedprocedure` (`fixedAmount`,`healthCarePlan_id`,`procedure_id`,`ch`)
VALUES
	(0.00, 2, 1, 55),
	(0.00, 2, 4, 12),
	(0.00, 2, 2, 20),
	(0.00, 2, 5, 30),
	(120.00, 2, 3, 0);

INSERT INTO `precifiedspecialty` (`amount`,`healthCarePlan_id`,`specialty_id`)
VALUES
	(120.00, 2, 2),
	(200.00, 2, 1);
	
-- clinica
INSERT INTO `clinic` (`id`,`active`,`city`,`neighborhood`,`postalCode`,`state`,`street`,`billingEmail`,`cnpj`,`contactEmail`,`domain`,`fax`,`name`,`telephone`,`telephone2`,`website`,`privatePlan_id`)
VALUES
	(1, 1, 'SP', 'bla', '12345-678', 'SP', 'sp', 'mauricioaniche@gmail.com', NULL, 'mauricioaniche@gmail.com', 'homologacao', NULL, 'Clinica de Homolog', NULL, NULL, NULL, 1);
