drop database if exists investimentos;
create database investimentos  CHARACTER SET utf8 COLLATE utf8_general_ci;
use investimentos;
create table Carteira(
    idCliente integer primary key auto_increment not null,
    nome varchar(40) not null,
    lucroEsperado decimal(8,2) not null,
    prejuizoMaximo decimal(8,2) not null,
    perfilDeInvestimento varchar(40) not null
);
create table Investimento(
    idCliente integer not null,
    idAcao integer primary key auto_increment not null,
    acao varchar(6) not null,
    dataCompra Date not null,
    dataVenda Date,
    compradoPor decimal(6,2) not null,
    quantidade integer not null,
    valorAtual decimal(6,2) not null,
    vendidoPor decimal(6,2),  
    constraint fkCarteiraInvestimento
    foreign key (idCliente)
    references Carteira(idCliente)
    on delete cascade 
    on update cascade
);

insert into Carteira values
(default,"Maria José",15000.00,5000.00,"Conservador"),
(default,"Jose Maria",15000.00,8000.00,"Agressivo"),
(default,"Marta Silva",150000.00,5000.00,"Moderado"),
(default,"Zelia Ferreira",20000.00,10000.00,"Moderado");

insert into Investimento values 
(1,default,"PSD3","2020/01/22","2020/06/25",13.44,20,12.83,12.10),
(1,default,"LZT4","2020/02/10",null,18.77,15,12.82,0.00),	
(1,default,"VALE1","2020/02/29","2020/07/28",14.32,30,15.01,12.74), 
(1,default,"PETRO4","2020/03/19","2020/05/13",19.56,25,14.03,14.13), 
(1,default,"LASA4","2020/04/07","2020/06/14",84.75,10,95.00,94.75),
(2,default,"PETRO4","2020-02-10","2020/06/25",12.34,20,12.83,15.40),
(2,default,"LASA2","2020-02-29",null,13.43,15,12.82,0.00),
(2,default,"RBT5","2020-03-19","2020/07/28",12.45,100,15.01,12.74),
(2,default,"RBT8","2020-04-07","2020/05/13",15.34,25,14.03,16.84), 
(2,default,"PSD3","2019-12-05","2020/06/14",73.50,50,95.00,94.75), 
(2,default,"LZT4","2020-01-08",null,110.30,10,105.23,0.00),
(2,default,"VALE1","2020-04-03",null,14.87,15,14.79,0.00),
(2,default,"VALE3","2020-01-22","2020/06/03",17.50,20,13.49,16.10),
(3,default,"PETRO4","2019-12-05","2020/06/25",12.74,20,12.83,15.40), 
(3,default,"LZT4","2020-01-08",null,13.45,15,12.82,0.00), 
(3,default,"VALE1","2020-02-29",null,74.00,10,75.00,0.00), 
(3,default,"VAL3","2020-03-19",null,14.87,15,14.79,0.00), 
(3,default,"RBT4","2020-04-07","2020/06/03",13.45,20,13.49,12.11),
(4,default,"PETRO4","2019/12/05","2020/06/25",12.74,20,12.83,15.40), 
(4,default,"LASA2","2020/01/08",null,13.45,15,12.82,0.00), 
(4,default,"PSD3","2020/04/03","2020/07/28",14.16,100,15.01,12.74), 
(4,default,"LZT4","2020/01/22","2020/05/13",14.87,25,14.03,16.84), 
(4,default,"VALE1","2020/02/10","2020/06/14",92.34,50,95.00,94.75), 
(4,default,"VALE3","2020/02/29",null,104.16,10,105.23,0.00), 
(4,default,"RBT5","2020/03/19",null,14.87,15,14.79,0.00), 
(4,default,"RBT8","2020/04/07","2020/06/03",13.45,20,13.49,12.11); 

--TABELA [TOTAL INVESTIDO, TOTAL VENDIDO E RETORNO] 
CREATE VIEW ViewInvestimentos AS
SELECT * ,(compradoPor*quantidade) AS totalInvestido,  (vendidoPor*quantidade) AS totalVendido,
CASE WHEN (compradoPor*quantidade) > (vendidoPor*quantidade) THEN 'Prejuizo' ELSE 'Lucro' END as Retorno
FROM Investimento;
--TABELA [TOTAL INVESTIDO, TOTAL VENDIDO E RETORNO POR CLIENTE]
CREATE VIEW ViewResultados AS
SELECT idCliente, SUM(compradoPor*quantidade) AS totalInvestido, SUM(vendidoPor*quantidade) AS totalVendido
FROM investimento
GROUP BY idCliente;

insert into carteira values
(default,"Cuca Marvado",1000,500,"Agressivo");

insert into investimento values
(5,default,"LAME4",curdate(),null,30.15,100,30.15,null);

select * from Carteira;
select * from Investimento;
Select * from ViewInvestimentos;
Select * from ViewResultados;
show tables;

