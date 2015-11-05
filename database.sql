
DROP TABLE zespol_has_koncert;
DROP TABLE koncert;
DROP TABLE klub;
DROP TABLE zespol;

GO

CREATE TABLE [zespol] (
  [zespol_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [zespol_nazwa_zespolu] VARCHAR(50) NOT NULL,
  [zespol_kraj] VARCHAR(30) NOT NULL
)
GO

select * from zespol;


CREATE TABLE [klub] (
  [klub_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [klub_miasto] VARCHAR(50)  NOT NULL,
  [klub_nazwa_klubu] VARCHAR(30) NOT NULL,
  [klub_ilosc_miejsc] VARCHAR(5) NOT NULL
)
GO

select * from klub;


CREATE TABLE [koncert] (
  [koncert_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [koncert_klub_id] INTEGER  NOT NULL REFERENCES klub(klub_id) ,
  [nazwa_koncertu] VARCHAR(50) NOT NULL,
  [ceny_biletow] DECIMAL,
  [data] DATE NOT NULL
)
GO

select * from koncert;


CREATE TABLE [zespol_has_koncert] (
  [zespol_has_koncert_id] INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [zespol_has_koncert_zespol_id] INTEGER  NOT NULL  REFERENCES zespol(zespol_id),
  [zespol_has_koncert_koncert_id] INTEGER  NOT NULL REFERENCES koncert(koncert_id)

)
GO

select * from zespol_has_koncert;





