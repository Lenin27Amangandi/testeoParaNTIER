-- database: ../DataBase/prjDataBase.sqlite

DROP TABLE IF EXISTS Categoria;

CREATE TABLE Categoria (
    idCategoria         INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre             TEXT NOT NULL

    ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCrea          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica      DATETIME
);
INSERT INTO Categoria (Nombre)
    VALUES 
    ('Pintura')
    ,('Escultura')
    ,('Fotografia');