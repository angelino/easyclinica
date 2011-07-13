
create table Anamnese (
    id integer not null auto_increment,
    clinicExam longtext,
    complaintAndDuration longtext,
    conduct longtext,
    date datetime,
    hf longtext,
    hpma longtext,
    hsda longtext,
    hypothesis longtext,
    supplementaryExam longtext,
    cid_id integer,
    doctor_id integer,
    patient_id integer,
    primary key (id)
) type=InnoDB;

create table Appointment (
    id integer not null auto_increment,
    appointmentAmount decimal(19,2),
    appointmentDate date,
    assistantAmount decimal(19,2),
    date datetime,
    isReturn bit not null,
    materialAmount decimal(19,2),
    medicineAmount decimal(19,2),
    observations longtext,
    procedureAmount decimal(19,2),
    roomRateAmount decimal(19,2),
    totalAmount decimal(19,2),
    doctor_id integer,
    employee_id integer,
    healthCarePlan_id integer,
    patient_id integer,
    specialty_id integer,
    primary key (id)
) type=InnoDB;

create table AppointmentAssistant (
    id integer not null auto_increment,
    amount decimal(19,2),
    ch integer not null,
    name varchar(255),
    type varchar(255),
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table AppointmentMaterial (
    id integer not null auto_increment,
    qty decimal(19,2),
    unitAmount decimal(19,2),
    material_id integer,
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table AppointmentMedicine (
    id integer not null auto_increment,
    qty decimal(19,2),
    unitAmount decimal(19,2),
    medicine_id integer,
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table AppointmentProcedure (
    id integer not null auto_increment,
    amount decimal(19,2),
    assistantAmount decimal(19,2),
    ch integer not null,
    materialAmount decimal(19,2),
    medicineAmount decimal(19,2),
    appointment_id integer,
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table CID (
    id integer not null auto_increment,
    code varchar(255),
    name varchar(255),
    primary key (id)
) type=InnoDB;

create table ChatMessage (
    id integer not null auto_increment,
    date datetime,
    message varchar(255),
    from_id integer,
    to_id integer,
    primary key (id)
) type=InnoDB;

create table Clinic (
    id integer not null auto_increment,
    active bit not null,
    city varchar(255),
    neighborhood varchar(255),
    postalCode varchar(255),
    state varchar(255),
    street varchar(255),
    billingEmail varchar(255),
    cnpj varchar(255),
    contactEmail varchar(255),
    domain varchar(255),
    endOperation time,
    fax varchar(255),
    name varchar(255),
    startOperation time,
    telephone varchar(255),
    telephone2 varchar(255),
    website varchar(255),
    privatePlan_id integer,
    primary key (id)
) type=InnoDB;

create table Doctor (
    id integer not null auto_increment,
    active bit not null,
    crm varchar(255),
    email varchar(255),
    name varchar(255),
    observations longtext,
    telephone varchar(255),
    specialty_id integer,
    primary key (id)
) type=InnoDB;

create table Employee (
    id integer not null auto_increment,
    active bit not null,
    cellphone varchar(255),
    email varchar(255),
    login varchar(255) unique,
    name varchar(255),
    observations varchar(255),
    password varchar(255),
    position varchar(255),
    doctor_id integer,
    primary key (id)
) type=InnoDB;

create table GeneralObservations (
    id integer not null auto_increment,
    date datetime,
    text longtext,
    patient_id integer,
    primary key (id)
) type=InnoDB;

create table HealthCarePlan (
    id integer not null auto_increment,
    active bit not null,
    city varchar(255),
    neighborhood varchar(255),
    postalCode varchar(255),
    state varchar(255),
    street varchar(255),
    ch decimal(19,2),
    contact varchar(255),
    email varchar(255),
    name varchar(255),
    observations longtext,
    payForRoomRate bit not null,
    periodToReturn integer not null,
    roomRateDefaultAmount decimal(19,2),
    telephone varchar(255),
    website varchar(255),
    primary key (id)
) type=InnoDB;

create table Material (
    id integer not null auto_increment,
    name varchar(255),
    unit varchar(255),
    primary key (id)
) type=InnoDB;

create table MaterialInProcedure (
    id integer not null auto_increment,
    qty decimal(19,2),
    material_id integer,
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table Medicine (
    id integer not null auto_increment,
    code varchar(255),
    name varchar(255),
    primary key (id)
) type=InnoDB;

create table MedicineInProcedure (
    id integer not null auto_increment,
    qty decimal(19,2),
    medicine_id integer,
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table Message (
    id integer not null auto_increment,
    date datetime,
    text longtext,
    employee_id integer,
    primary key (id)
) type=InnoDB;

create table Patient (
    id integer not null auto_increment,
    city varchar(255),
    neighborhood varchar(255),
    postalCode varchar(255),
    state varchar(255),
    street varchar(255),
    birthDate datetime,
    cellphone varchar(255),
    commercialPhone varchar(255),
    cpf varchar(255),
    email varchar(255),
    healthCarePlanCode varchar(255),
    maritalStatus varchar(255),
    name varchar(255),
    observations longtext,
    profession varchar(255),
    rg varchar(255),
    telephone varchar(255),
    healthCarePlan_id integer,
    primary key (id)
) type=InnoDB;

create table PrecifiedMaterial (
    id integer not null auto_increment,
    amount decimal(19,2),
    healthCarePlan_id integer,
    material_id integer,
    primary key (id)
) type=InnoDB;

create table PrecifiedMedicine (
    id integer not null auto_increment,
    amount decimal(19,2),
    healthCarePlan_id integer,
    medicine_id integer,
    primary key (id)
) type=InnoDB;

create table PrecifiedProcedure (
    id integer not null auto_increment,
    ch integer not null,
    fixedAmount decimal(19,2),
    roomTaxAmount decimal(19,2),
    healthCarePlan_id integer,
    procedure_id integer,
    primary key (id)
) type=InnoDB;

create table PrecifiedSpecialty (
    id integer not null auto_increment,
    amount decimal(19,2),
    healthCarePlan_id integer,
    specialty_id integer,
    primary key (id)
) type=InnoDB;

create table Receipt (
    id integer not null auto_increment,
    amount decimal(19,2),
    birthDate date,
    cpf varchar(255),
    date date,
    inNameOf varchar(255),
    kinship varchar(255),
    observations longtext,
    employee_id integer,
    patient_id integer,
    primary key (id)
) type=InnoDB;

create table Reply (
    id integer not null auto_increment,
    date datetime,
    text longtext,
    employee_id integer,
    message_id integer,
    primary key (id)
) type=InnoDB;

create table Schedule (
    id integer not null auto_increment,
    arrivalTime datetime,
    description longtext,
    isTreated bit not null,
    startTime datetime,
    subject varchar(255),
    doctor_id integer,
    primary key (id)
) type=InnoDB;

create table Specialty (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
) type=InnoDB;

create table medical_procedures (
    id integer not null auto_increment,
    ambCode varchar(255),
    cbhpmCode varchar(255),
    lpmCode varchar(255),
    name varchar(255),
    tussCode varchar(255),
    primary key (id)
) type=InnoDB;

create index anamnesePatientIndex on Anamnese (patient_id);

alter table Anamnese 
    add index FKC6D37B02397A30D5 (cid_id), 
    add constraint FKC6D37B02397A30D5 
    foreign key (cid_id) 
    references CID (id);

alter table Anamnese 
    add index FKC6D37B02C6AED5F5 (patient_id), 
    add constraint FKC6D37B02C6AED5F5 
    foreign key (patient_id) 
    references Patient (id);

alter table Anamnese 
    add index FKC6D37B02A92C339F (doctor_id), 
    add constraint FKC6D37B02A92C339F 
    foreign key (doctor_id) 
    references Doctor (id);

create index appointmentDateIndex on Appointment (appointmentDate);

create index appointmentDoctorIndex on Appointment (doctor_id);

create index appointmentPlanIndex on Appointment (healthCarePlan_id);

create index appointmentPatientIndex on Appointment (patient_id);

alter table Appointment 
    add index FKB7F037FAB1FFF7F (healthCarePlan_id), 
    add constraint FKB7F037FAB1FFF7F 
    foreign key (healthCarePlan_id) 
    references HealthCarePlan (id);

alter table Appointment 
    add index FKB7F037FC6AED5F5 (patient_id), 
    add constraint FKB7F037FC6AED5F5 
    foreign key (patient_id) 
    references Patient (id);

alter table Appointment 
    add index FKB7F037FA92C339F (doctor_id), 
    add constraint FKB7F037FA92C339F 
    foreign key (doctor_id) 
    references Doctor (id);

alter table Appointment 
    add index FKB7F037F6FB73C95 (specialty_id), 
    add constraint FKB7F037F6FB73C95 
    foreign key (specialty_id) 
    references Specialty (id);

alter table Appointment 
    add index FKB7F037F23C797FF (employee_id), 
    add constraint FKB7F037F23C797FF 
    foreign key (employee_id) 
    references Employee (id);

create index PAProcedureIndex on AppointmentAssistant (procedure_id);

alter table AppointmentAssistant 
    add index FK4CCF0E1F4B36F180 (procedure_id), 
    add constraint FK4CCF0E1F4B36F180 
    foreign key (procedure_id) 
    references AppointmentProcedure (id);

create index appMaterialProcedureIndex on AppointmentMaterial (procedure_id);

alter table AppointmentMaterial 
    add index FK8D6CDDC64B36F180 (procedure_id), 
    add constraint FK8D6CDDC64B36F180 
    foreign key (procedure_id) 
    references AppointmentProcedure (id);

alter table AppointmentMaterial 
    add index FK8D6CDDC61F45D95F (material_id), 
    add constraint FK8D6CDDC61F45D95F 
    foreign key (material_id) 
    references Material (id);

create index appMedicineProcedureIndex on AppointmentMedicine (procedure_id);

alter table AppointmentMedicine 
    add index FK45E9CED94B36F180 (procedure_id), 
    add constraint FK45E9CED94B36F180 
    foreign key (procedure_id) 
    references AppointmentProcedure (id);

alter table AppointmentMedicine 
    add index FK45E9CED9EB5CDC7F (medicine_id), 
    add constraint FK45E9CED9EB5CDC7F 
    foreign key (medicine_id) 
    references Medicine (id);

create index appProcedureIndex on AppointmentProcedure (appointment_id);

alter table AppointmentProcedure 
    add index FKB64E1E143F6AF075 (procedure_id), 
    add constraint FKB64E1E143F6AF075 
    foreign key (procedure_id) 
    references medical_procedures (id);

alter table AppointmentProcedure 
    add index FKB64E1E14DB923235 (appointment_id), 
    add constraint FKB64E1E14DB923235 
    foreign key (appointment_id) 
    references Appointment (id);

create index chatmessage_from on ChatMessage (from_id);

create index chatmessage_to on ChatMessage (to_id);

alter table ChatMessage 
    add index FK11CAB96FF3E24112 (to_id), 
    add constraint FK11CAB96FF3E24112 
    foreign key (to_id) 
    references Employee (id);

alter table ChatMessage 
    add index FK11CAB96FC9DEF583 (from_id), 
    add constraint FK11CAB96FC9DEF583 
    foreign key (from_id) 
    references Employee (id);

alter table Clinic 
    add index FK78780108C312E4A9 (privatePlan_id), 
    add constraint FK78780108C312E4A9 
    foreign key (privatePlan_id) 
    references HealthCarePlan (id);

alter table Doctor 
    add index FK7A547D3F6FB73C95 (specialty_id), 
    add constraint FK7A547D3F6FB73C95 
    foreign key (specialty_id) 
    references Specialty (id);

alter table Employee 
    add index FK4AFD4ACEA92C339F (doctor_id), 
    add constraint FK4AFD4ACEA92C339F 
    foreign key (doctor_id) 
    references Doctor (id);

create index generalObservationsPatientIndex on GeneralObservations (patient_id);

alter table GeneralObservations 
    add index FK633A36AFC6AED5F5 (patient_id), 
    add constraint FK633A36AFC6AED5F5 
    foreign key (patient_id) 
    references Patient (id);

create index materialInProcedureIndex on MaterialInProcedure (procedure_id);

alter table MaterialInProcedure 
    add index FKCC3FAB673F6AF075 (procedure_id), 
    add constraint FKCC3FAB673F6AF075 
    foreign key (procedure_id) 
    references medical_procedures (id);

alter table MaterialInProcedure 
    add index FKCC3FAB671F45D95F (material_id), 
    add constraint FKCC3FAB671F45D95F 
    foreign key (material_id) 
    references Material (id);

create index MedicineInProcedureIndex on MedicineInProcedure (procedure_id);

alter table MedicineInProcedure 
    add index FKFD2160743F6AF075 (procedure_id), 
    add constraint FKFD2160743F6AF075 
    foreign key (procedure_id) 
    references medical_procedures (id);

alter table MedicineInProcedure 
    add index FKFD216074EB5CDC7F (medicine_id), 
    add constraint FKFD216074EB5CDC7F 
    foreign key (medicine_id) 
    references Medicine (id);

alter table Message 
    add index FK9C2397E723C797FF (employee_id), 
    add constraint FK9C2397E723C797FF 
    foreign key (employee_id) 
    references Employee (id);

alter table Patient 
    add index FK340C82E5AB1FFF7F (healthCarePlan_id), 
    add constraint FK340C82E5AB1FFF7F 
    foreign key (healthCarePlan_id) 
    references HealthCarePlan (id);

create index PrecifiedMaterialPlanIndex on PrecifiedMaterial (healthCarePlan_id);

alter table PrecifiedMaterial 
    add index FKCC15B892AB1FFF7F (healthCarePlan_id), 
    add constraint FKCC15B892AB1FFF7F 
    foreign key (healthCarePlan_id) 
    references HealthCarePlan (id);

alter table PrecifiedMaterial 
    add index FKCC15B8921F45D95F (material_id), 
    add constraint FKCC15B8921F45D95F 
    foreign key (material_id) 
    references Material (id);

create index PrecifiedMedicinePlanIndex on PrecifiedMedicine (healthCarePlan_id);

alter table PrecifiedMedicine 
    add index FK8492A9A5AB1FFF7F (healthCarePlan_id), 
    add constraint FK8492A9A5AB1FFF7F 
    foreign key (healthCarePlan_id) 
    references HealthCarePlan (id);

alter table PrecifiedMedicine 
    add index FK8492A9A5EB5CDC7F (medicine_id), 
    add constraint FK8492A9A5EB5CDC7F 
    foreign key (medicine_id) 
    references Medicine (id);

create index PrecifiedProcedurePlanIndex on PrecifiedProcedure (healthCarePlan_id);

alter table PrecifiedProcedure 
    add index FK4CC09CC83F6AF075 (procedure_id), 
    add constraint FK4CC09CC83F6AF075 
    foreign key (procedure_id) 
    references medical_procedures (id);

alter table PrecifiedProcedure 
    add index FK4CC09CC8AB1FFF7F (healthCarePlan_id), 
    add constraint FK4CC09CC8AB1FFF7F 
    foreign key (healthCarePlan_id) 
    references HealthCarePlan (id);

create index PrecifiedSpecialtyPlanIndex on PrecifiedSpecialty (healthCarePlan_id);

alter table PrecifiedSpecialty 
    add index FK29041EF3AB1FFF7F (healthCarePlan_id), 
    add constraint FK29041EF3AB1FFF7F 
    foreign key (healthCarePlan_id) 
    references HealthCarePlan (id);

alter table PrecifiedSpecialty 
    add index FK29041EF36FB73C95 (specialty_id), 
    add constraint FK29041EF36FB73C95 
    foreign key (specialty_id) 
    references Specialty (id);

create index receiptDateIndex on Receipt (date);

create index receiptPatientIndex on Receipt (patient_id);

alter table Receipt 
    add index FKA3BB0A18C6AED5F5 (patient_id), 
    add constraint FKA3BB0A18C6AED5F5 
    foreign key (patient_id) 
    references Patient (id);

alter table Receipt 
    add index FKA3BB0A1823C797FF (employee_id), 
    add constraint FKA3BB0A1823C797FF 
    foreign key (employee_id) 
    references Employee (id);

alter table Reply 
    add index FK4B322CA44DB9EB5 (message_id), 
    add constraint FK4B322CA44DB9EB5 
    foreign key (message_id) 
    references Message (id);

alter table Reply 
    add index FK4B322CA23C797FF (employee_id), 
    add constraint FK4B322CA23C797FF 
    foreign key (employee_id) 
    references Employee (id);

create index ScheduleDoctorIndex on Schedule (doctor_id);

alter table Schedule 
    add index FKDA40F6B7A92C339F (doctor_id), 
    add constraint FKDA40F6B7A92C339F 
    foreign key (doctor_id) 
    references Doctor (id);

create index idx_procedure_lpmcode on medical_procedures (lpmCode);

create index idx_procedure_ambcode on medical_procedures (ambCode);

create index idx_procedure_tusscode on medical_procedures (tussCode);

create index idx_procedure_cbhpmcode on medical_procedures (cbhpmCode);