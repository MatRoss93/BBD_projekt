USE 25678166_0000001
/
CREATE TABLE PCJ(NPCJ BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            IMIE VARCHAR(20) NOT NULL COMMENT 'IMIE PACJENTA',
                            NAZW VARCHAR(40) NOT NULL COMMENT 'NAZWISKO PACJENTA',
                            NMST INT NOT NULL COMMENT 'Klucz obcy miasta',
							NWOJ INT NOT NULL COMMENT 'Klucz obcy województwa',
                            ULIC VARCHAR(40) NOT NULL COMMENT 'ULICA',
                            NUMT INT(12) COMMENT 'NUMER TELEFONU PACJENTA',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NPCJ)
							FOREIGN KEY (NMST) REFERENCES MST(NMST)
							FOREIGN KEY (NWOJ) REFERENCES WOJ(NWOJ))

CREATE TABLE SPC(NSPC BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            SPEC VARCHAR(20) UNIQUE NOT NULL COMMENT 'Specjalność',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NSPC))

CREATE TABLE LEK(NLEK BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            NSPC BIGINT NOT NULL COMMENT 'Klucz obcy specjalnosci',
                            IMIE VARCHAR(20) NOT NULL COMMENT 'IMIE LEKARZA',
                            NAZW VARCHAR(40) NOT NULL COMMENT 'NAZWISKO LEKARZA',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NLEK),
                            FOREIGN KEY (NSPC) REFERENCES SPC(NSPC))
/
CREATE TABLE PRZ(NPRZ BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            NMST INT NOT NULL COMMENT 'Klucz obcy miasta',
							NWOJ INT NOT NULL COMMENT 'Klucz obcy województwa',
                            ADRS VARCHAR(40) NOT NULL COMMENT 'Adres przychodni',
                            NUMT INT(12) NOT NULL COMMENT 'Numer telefonu przychodzni',
                            PONO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            POND INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            WTOO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            WTOD INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            SROO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            SROD INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            CZWO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            CZWD INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            PIOO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            PIOD INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            SOBO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            SOBD INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            NIEO INT(4) COMMENT 'GODZINA OTWARCIA W MINUTACH OD POLNOCY',
                            NIED INT(4) COMMENT 'GODZINA ZAMKNIECIA W MINUTACH OD POLNOCY',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NPRZ)
							FOREIGN KEY (NMST) REFERENCES MST(NMST)
							FOREIGN KEY (NWOJ) REFERENCES WOJ(NWOJ))
/
CREATE TABLE GRF(NGRF BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            NPCJ BIGINT NOT NULL COMMENT 'Klucz obcy pacjętów',
                            NLEK BIGINT NOT NULL COMMENT 'Klucz obcy lekarzy',
                            NPRZ BIGINT NOT NULL COMMENT 'Klucz przychodni',
                            DTOD DATETIME NOT NULL COMMENT 'DATA WIZYTY od',
                            DTDO DATETIME NOT NULL COMMENT 'DATA WIZYTY do',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NGRF),
                            FOREIGN KEY (NPCJ) REFERENCES PCJ(NPCJ),
                            FOREIGN KEY (NLEK) REFERENCES LEK(NLEK),
                            FOREIGN KEY (NPRZ) REFERENCES PRZ(NPRZ))
/
CREATE TABLE DRG(NDRG BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            DRGS VARCHAR(20) UNIQUE NOT NULL COMMENT 'Nazwa leku',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NDRG))
/
CREATE TABLE REC(NREC BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            NDRG BIGINT NOT NULL COMMENT 'Klucz obcy leków',
                            NGRF BIGINT NOT NULL COMMENT 'Klucz obcy grafiku',
                            REFU SMALLINT NOT NULL COMMENT '0-100 % refundacji',
                            DWYS DATETIME NOT NULL COMMENT 'Data wystawienia recepty',
                            DWAZ DATETIME NOT NULL COMMENT 'Data ważności recepty',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NREC),
                            FOREIGN KEY (NDRG) REFERENCES DRG(NDRG),
                            FOREIGN KEY (NGRF) REFERENCES GRF(NGRF))
/
CREATE TABLE BAD(NBAD BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            BADA VARCHAR(20) UNIQUE NOT NULL COMMENT 'Nazwa badania',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NBAD))
/
CREATE TABLE SKI(NSKI BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            NBAD BIGINT NOT NULL COMMENT 'Klucz obcy badania',
                            NSPC BIGINT NOT NULL COMMENT 'Klucz obcy specjalności',
                            NGRF BIGINT NOT NULL COMMENT 'Klucz obcy grafiku',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NSKI),
                            FOREIGN KEY (NSPC) REFERENCES SPC(NSPC),
                            FOREIGN KEY (NBAD) REFERENCES BAD(NBAD),
                            FOREIGN KEY (NGRF) REFERENCES GRF(NGRF))
/
CREATE TABLE URZ(NURZ BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
                            LOGN VARCHAR(20) UNIQUE NOT NULL COMMENT 'Login',
                            HASL VARCHAR(20) NOT NULL COMMENT 'Hasło',
                            NPCJ BIGINT UNIQUE COMMENT 'Klucz obcy pacjenta',
                            NLEK BIGINT UNIQUE COMMENT 'Klucz obcy lekarza',
                            UPRW VARCHAR(1) NOT NULL DEFAULT 'P' COMMENT 'Uprawnienia P - pacjent, A - admin, L - lekarz, R - recepcja',
                            AKTW VARCHAR(1) NOT NULL DEFAULT 'T' COMMENT 'Pozycja aktywna, T - tak N - nie', 
                            DTWO DATETIME COMMENT 'Data utworzenia',
                            DAKT TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT 'Data aktualizacji',
                            PRIMARY KEY (NURZ),
                            FOREIGN KEY (NPCJ) REFERENCES PCJ(NPCJ),
                            FOREIGN KEY (NLEK) REFERENCES LEK(NLEK))
/
CREATE TABLE WOJ
(
	NWOJ INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
	WOJW VARCHAR(40) NOT NULL COMMENT 'Nazwa województwa',
	PRIMARY KEY(NWOJ)  
);
/
CREATE TABLE MST
(
	NMST INT NOT NULL AUTO_INCREMENT COMMENT 'Klucz główny',
	MSTO VARCHAR(40) NOT NULL COMMENT 'Nazwa miejscowości',
	PRIMARY KEY(NMST)
);
/
ALTER TABLE PCJ COMMENT 'Pacjenci'
/
ALTER TABLE LEK COMMENT 'Lekarze'
/
ALTER TABLE PRZ COMMENT 'Przychodnie'
/
ALTER TABLE GRF COMMENT 'Grafik wizyt'
/
ALTER TABLE REC COMMENT 'Repety'
/
ALTER TABLE DRG COMMENT 'Leki'
/
ALTER TABLE SPC COMMENT 'Specjalności'
/
ALTER TABLE BAD COMMENT 'Badania'
/
ALTER TABLE SKI COMMENT 'Skierowania'
/
ALTER TABLE URZ COMMENT 'Urzytkownicy'
/
ALTER TABLE MST COMMENT 'Miasta'
/
ALTER TABLE WOJ COMMENT 'Województwa'