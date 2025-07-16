DROP DATABASE IF EXISTS MLR20;

CREATE DATABASE IF NOT EXISTS MLR20;
USE MLR20;
# -----------------------------------------------------------------------------
#       TABLE : EMPLOYES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS EMPLOYES
 (
   ID_EMPLOYES BIGINT(4) NOT NULL  ,
   ID_STUDIO BIGINT(4) NOT NULL  ,
   POSTE_EMPLOYES CHAR(255) NOT NULL  
   , PRIMARY KEY (ID_EMPLOYES) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE EMPLOYES
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_EMPLOYES_STUDIOS
     ON EMPLOYES (ID_STUDIO ASC);

# -----------------------------------------------------------------------------
#       TABLE : EQUIPEMENTS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS EQUIPEMENTS
 (
   ID_EQUIPEMENT BIGINT(4) NOT NULL  ,
   NOM_EQUIPEMENT VARCHAR(255) NOT NULL  ,
   TYPE_EQUIPEMENT VARCHAR(255) NOT NULL  ,
   MARQUE_EQUIPEMENT VARCHAR(255) NOT NULL  ,
   ETAT_EQUIPEMENT CHAR(255) NOT NULL  
   , PRIMARY KEY (ID_EQUIPEMENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : IMPRESSIONS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS IMPRESSIONS
 (
   ID_IMPRESSION BIGINT(4) NOT NULL  ,
   ID_CLIENT BIGINT(4) NOT NULL  ,
   ID_MONTAGEVIDEO BIGINT(4) NOT NULL  ,
   ID_PHOTO BIGINT(4) NOT NULL  ,
   FORMAT_IMPRESSION VARCHAR(255) NOT NULL  ,
   TYPE_PAPIER VARCHAR(255) NOT NULL  ,
   QUANTITE BIGINT(4) NOT NULL  ,
   ALBUM_PHYSIQUE CHAR(255) NOT NULL  ,
   CLES_USB CHAR(255) NOT NULL  ,
   CAPACITE_CLES_USB VARCHAR(255) NOT NULL  
   , PRIMARY KEY (ID_IMPRESSION) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE IMPRESSIONS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_IMPRESSIONS_CLIENTS
     ON IMPRESSIONS (ID_CLIENT ASC);

CREATE  INDEX I_FK_IMPRESSIONS_MONTAGE_VIDEOS
     ON IMPRESSIONS (ID_MONTAGEVIDEO ASC);

CREATE  INDEX I_FK_IMPRESSIONS_PHOTOS
     ON IMPRESSIONS (ID_PHOTO ASC);

# -----------------------------------------------------------------------------
#       TABLE : RESERVATIONS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS RESERVATIONS
 (
   ID_RESERVATION BIGINT(4) NOT NULL  ,
   ID_STUDIO BIGINT(4) NOT NULL  ,
   ID_CLIENT BIGINT(4) NOT NULL  ,
   TYPE_SEANCERESERVATION CHAR(255) NOT NULL  ,
   DUREE_RESERVATION TIME NOT NULL  ,
   DATE_HEURERESERVATION DATETIME NOT NULL  ,
   STATUT_RESERVATION CHAR(255) NOT NULL  ,
   PRIX_RESERVATION DECIMAL(13,2) NOT NULL  
   , PRIMARY KEY (ID_RESERVATION) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE RESERVATIONS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_RESERVATIONS_STUDIOS
     ON RESERVATIONS (ID_STUDIO ASC);

CREATE  INDEX I_FK_RESERVATIONS_CLIENTS
     ON RESERVATIONS (ID_CLIENT ASC);

# -----------------------------------------------------------------------------
#       TABLE : VIDEOS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS VIDEOS
 (
   ID_VIDEO BIGINT(4) NOT NULL  ,
   ID_VIDEOGRAPHE BIGINT(4) NOT NULL  ,
   NOMFICHIER_VIDEO VARCHAR(255) NOT NULL  ,
   FORMAT_VIDEO CHAR(255) NOT NULL  ,
   RESOLUTION_VIDEO VARCHAR(255) NOT NULL  ,
   DATE_HEURECREATION_VIDEO DATETIME NOT NULL  ,
   NOMBRE_VIDEO BIGINT(4) NOT NULL  ,
   DUREE_VIDEO TIME NOT NULL  
   , PRIMARY KEY (ID_VIDEO) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE VIDEOS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_VIDEOS_VIDEOGRAPHES
     ON VIDEOS (ID_VIDEOGRAPHE ASC);

# -----------------------------------------------------------------------------
#       TABLE : PHOTOGRAPHES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PHOTOGRAPHES
 (
   ID_PHOTOGRAPHE BIGINT(4) NOT NULL  ,
   SPECIALISATION_PHOTOGRAPHE CHAR(255) NOT NULL  ,
   EXPERIENCE_PHOTOGRAPHE VARCHAR(255) NOT NULL  
   , PRIMARY KEY (ID_PHOTOGRAPHE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : MONTAGE_VIDEOS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS MONTAGE_VIDEOS
 (
   ID_MONTAGEVIDEO BIGINT(4) NOT NULL  ,
   ID_VIDEO BIGINT(4) NOT NULL  ,
   EFFET_SPECIAUX_MONTAGEVIDEO VARCHAR(255) NOT NULL  ,
   AJOUT_MUSIQUE CHAR(255) NOT NULL  ,
   AJOUT_TEXTE CHAR(255) NOT NULL  ,
   DUREEFINALE_MONTAGEVIDEO TIME NOT NULL  
   , PRIMARY KEY (ID_MONTAGEVIDEO) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE MONTAGE_VIDEOS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_MONTAGE_VIDEOS_VIDEOS
     ON MONTAGE_VIDEOS (ID_VIDEO ASC);

# -----------------------------------------------------------------------------
#       TABLE : VIDEOGRAPHES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS VIDEOGRAPHES
 (
   ID_VIDEOGRAPHE BIGINT(4) NOT NULL  ,
   ID_EQUIPEMENT BIGINT(4) NOT NULL  ,
   ID_PHOTOGRAPHE BIGINT(4) NOT NULL  ,
   SPECIALISATION_VIDEOGRAPHE VARCHAR(255) NOT NULL  ,
   EXPERIENCE_VIDEOGRAPHE VARCHAR(255) NOT NULL  
   , PRIMARY KEY (ID_VIDEOGRAPHE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE VIDEOGRAPHES
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_VIDEOGRAPHES_EQUIPEMENTS
     ON VIDEOGRAPHES (ID_EQUIPEMENT ASC);

CREATE  INDEX I_FK_VIDEOGRAPHES_PHOTOGRAPHES
     ON VIDEOGRAPHES (ID_PHOTOGRAPHE ASC);

# -----------------------------------------------------------------------------
#       TABLE : STUDIOS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS STUDIOS
 (
   ID_STUDIO BIGINT(4) NOT NULL  ,
   ID_EQUIPEMENT BIGINT(4) NOT NULL  ,
   NOM_STUDIO CHAR(255) NOT NULL  ,
   ADRESSE_STUDIO VARCHAR(255) NOT NULL  ,
   TELEPHONE_STUDIO BIGINT(4) NOT NULL  ,
   EMAIL_STUDIO VARCHAR(255) NOT NULL  
   , PRIMARY KEY (ID_STUDIO) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE STUDIOS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_STUDIOS_EQUIPEMENTS
     ON STUDIOS (ID_EQUIPEMENT ASC);

# -----------------------------------------------------------------------------
#       TABLE : CLIENTS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CLIENTS
 (
   ID_CLIENT BIGINT(4) NOT NULL  ,
   ADRESSE_CLIENT VARCHAR(255) NOT NULL  
   , PRIMARY KEY (ID_CLIENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : PERSONNES
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PERSONNES
 (
   ID_PERSONNE BIGINT(4) NOT NULL  ,
   ID_VIDEOGRAPHE BIGINT(4) NOT NULL  ,
   ID_PHOTOGRAPHE BIGINT(4) NOT NULL  ,
   ID_CLIENT BIGINT(4) NOT NULL  ,
   ID_EMPLOYES BIGINT(4) NOT NULL  ,
   NOM_PERSONNE CHAR(255) NOT NULL  ,
   PRENOM_PERSONNE CHAR(255) NOT NULL  ,
   TELEPHONE_PERSONNE BIGINT(4) NOT NULL  ,
   EMAIL_PERSONNE VARCHAR(255) NOT NULL  
   , PRIMARY KEY (ID_PERSONNE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PERSONNES
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PERSONNES_VIDEOGRAPHES
     ON PERSONNES (ID_VIDEOGRAPHE ASC);

CREATE  INDEX I_FK_PERSONNES_PHOTOGRAPHES
     ON PERSONNES (ID_PHOTOGRAPHE ASC);

CREATE  INDEX I_FK_PERSONNES_CLIENTS
     ON PERSONNES (ID_CLIENT ASC);

CREATE  INDEX I_FK_PERSONNES_EMPLOYES
     ON PERSONNES (ID_EMPLOYES ASC);

# -----------------------------------------------------------------------------
#       TABLE : PHOTOS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PHOTOS
 (
   ID_PHOTO BIGINT(4) NOT NULL  ,
   ID_PHOTOGRAPHE BIGINT(4) NOT NULL  ,
   NOMFICHIER_PHOTO VARCHAR(255) NOT NULL  ,
   FORMAT_PHOTO CHAR(255) NOT NULL  ,
   DATE_HEURECREATION_PHOTO DATETIME NOT NULL  ,
   NOMBRETOTAL_PHOTO BIGINT(4) NOT NULL  
   , PRIMARY KEY (ID_PHOTO) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PHOTOS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PHOTOS_PHOTOGRAPHES
     ON PHOTOS (ID_PHOTOGRAPHE ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE EMPLOYES 
  ADD FOREIGN KEY FK_EMPLOYES_STUDIOS (ID_STUDIO)
      REFERENCES STUDIOS (ID_STUDIO) ;


ALTER TABLE IMPRESSIONS 
  ADD FOREIGN KEY FK_IMPRESSIONS_CLIENTS (ID_CLIENT)
      REFERENCES CLIENTS (ID_CLIENT) ;


ALTER TABLE IMPRESSIONS 
  ADD FOREIGN KEY FK_IMPRESSIONS_MONTAGE_VIDEOS (ID_MONTAGEVIDEO)
      REFERENCES MONTAGE_VIDEOS (ID_MONTAGEVIDEO) ;


ALTER TABLE IMPRESSIONS 
  ADD FOREIGN KEY FK_IMPRESSIONS_PHOTOS (ID_PHOTO)
      REFERENCES PHOTOS (ID_PHOTO) ;


ALTER TABLE RESERVATIONS 
  ADD FOREIGN KEY FK_RESERVATIONS_STUDIOS (ID_STUDIO)
      REFERENCES STUDIOS (ID_STUDIO) ;


ALTER TABLE RESERVATIONS 
  ADD FOREIGN KEY FK_RESERVATIONS_CLIENTS (ID_CLIENT)
      REFERENCES CLIENTS (ID_CLIENT) ;


ALTER TABLE VIDEOS 
  ADD FOREIGN KEY FK_VIDEOS_VIDEOGRAPHES (ID_VIDEOGRAPHE)
      REFERENCES VIDEOGRAPHES (ID_VIDEOGRAPHE) ;


ALTER TABLE MONTAGE_VIDEOS 
  ADD FOREIGN KEY FK_MONTAGE_VIDEOS_VIDEOS (ID_VIDEO)
      REFERENCES VIDEOS (ID_VIDEO) ;


ALTER TABLE VIDEOGRAPHES 
  ADD FOREIGN KEY FK_VIDEOGRAPHES_EQUIPEMENTS (ID_EQUIPEMENT)
      REFERENCES EQUIPEMENTS (ID_EQUIPEMENT) ;


ALTER TABLE VIDEOGRAPHES 
  ADD FOREIGN KEY FK_VIDEOGRAPHES_PHOTOGRAPHES (ID_PHOTOGRAPHE)
      REFERENCES PHOTOGRAPHES (ID_PHOTOGRAPHE) ;


ALTER TABLE STUDIOS 
  ADD FOREIGN KEY FK_STUDIOS_EQUIPEMENTS (ID_EQUIPEMENT)
      REFERENCES EQUIPEMENTS (ID_EQUIPEMENT) ;


ALTER TABLE PERSONNES 
  ADD FOREIGN KEY FK_PERSONNES_VIDEOGRAPHES (ID_VIDEOGRAPHE)
      REFERENCES VIDEOGRAPHES (ID_VIDEOGRAPHE) ;


ALTER TABLE PERSONNES 
  ADD FOREIGN KEY FK_PERSONNES_PHOTOGRAPHES (ID_PHOTOGRAPHE)
      REFERENCES PHOTOGRAPHES (ID_PHOTOGRAPHE) ;


ALTER TABLE PERSONNES 
  ADD FOREIGN KEY FK_PERSONNES_CLIENTS (ID_CLIENT)
      REFERENCES CLIENTS (ID_CLIENT) ;


ALTER TABLE PERSONNES 
  ADD FOREIGN KEY FK_PERSONNES_EMPLOYES (ID_EMPLOYES)
      REFERENCES EMPLOYES (ID_EMPLOYES) ;


ALTER TABLE PHOTOS 
  ADD FOREIGN KEY FK_PHOTOS_PHOTOGRAPHES (ID_PHOTOGRAPHE)
      REFERENCES PHOTOGRAPHES (ID_PHOTOGRAPHE) ;

