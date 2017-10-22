USE 25678166_0000001
/
DROP TRIGGER IF EXISTS PCJ_DTWO;
/
CREATE TRIGGER PCJ_DTWO
BEFORE INSERT ON PCJ FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS BAD_DTWO;
/
CREATE TRIGGER BAD_DTWO
BEFORE INSERT ON BAD FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS DRG_DTWO;
/
CREATE TRIGGER DRG_DTWO
BEFORE INSERT ON DRG FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS GRF_DTWO;
/
CREATE TRIGGER GRF_DTWO
BEFORE INSERT ON GRF FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS LEK_DTWO;
/
CREATE TRIGGER LEK_DTWO
BEFORE INSERT ON LEK FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS PRZ_DTWO;
/
CREATE TRIGGER PRZ_DTWO
BEFORE INSERT ON PRZ FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS REC_DTWO;
/
CREATE TRIGGER REC_DTWO
BEFORE INSERT ON REC FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS SKI_DTWO;
/
CREATE TRIGGER SKI_DTWO
BEFORE INSERT ON SKI FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
DROP TRIGGER IF EXISTS SPC_DTWO;
/
CREATE TRIGGER SPC_DTWO
BEFORE INSERT ON SPC FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END
/
CREATE TRIGGER MST_DTWO
BEFORE INSERT ON MST FOR EACH ROW
BEGIN
    IF (NEW.DTWO IS NULL) THEN -- change the isnull check for the default used
        SET NEW.DTWO = now();
    END IF;
END