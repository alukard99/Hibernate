CREATE USER HIBERNATE_FINAL IDENTIFIED BY HIBERNATE_FINAL;
GRANT CONNECT, RESOURCE,DBA TO HIBERNATE_FINAL;



CREATE TABLE articulos (
referencia VARCHAR2(13) NOT NULL,
descripcion VARCHAR2(255) NOT NULL,
precio NUMBER(12, 3) NOT NULL,
porcIva NUMBER(5, 2), -- 4, 10, 21 Es el porcentaje de Iva del Producto
Stock NUMBER(12, 3) NOT NULL
);
CREATE UNIQUE INDEX articulos_idx1 ON articulos (descripcion ASC );
ALTER TABLE articulos ADD CONSTRAINT pk_articulos PRIMARY KEY ( referencia );
CREATE TABLE clientes (
dnicif VARCHAR2(13) NOT NULL,
nombrecli VARCHAR2(100) NOT NULL
);
ALTER TABLE clientes ADD CONSTRAINT pk_clientes PRIMARY KEY ( dnicif );
CREATE TABLE facturas_cab (
numfac NUMBER(10) NOT NULL,
fechafac DATE,
dnicif VARCHAR2(13) NOT NULL
);
ALTER TABLE facturas_cab ADD CONSTRAINT pk_facturas_cab PRIMARY KEY ( numfac );
ALTER TABLE facturas_cab ADD CONSTRAINT fk_facturas_cab_clientes
FOREIGN KEY ( dnicif ) REFERENCES clientes ( dnicif );
CREATE TABLE facturas_lin (
numfac NUMBER(10) NOT NULL,
lineafac NUMBER(10) NOT NULL,
referencia VARCHAR2(13) NOT NULL,
cantidad NUMBER(12, 3) NOT NULL,
precio NUMBER(12, 3) NOT NULL,
dtolinea NUMBER(5, 2) NOT NULL, -- Es el porcentaje de Descuento
Ivalinea NUMBER(5, 2) NOT NULL -- Es el Iva de la linea
);
ALTER TABLE facturas_lin ADD CONSTRAINT pk_facturas_lin PRIMARY KEY ( numfac,lineafac
);
ALTER TABLE facturas_lin ADD CONSTRAINT fk_facturas_lin_articulos
FOREIGN KEY ( referencia ) REFERENCES articulos ( referencia );
ALTER TABLE facturas_lin ADD CONSTRAINT fk_facturas_lin_facturas_cab
FOREIGN KEY ( numfac ) REFERENCES facturas_cab ( numfac );
CREATE TABLE facturas_tot (
numfac NUMBER(10) NOT NULL,
baseimp NUMBER(12, 2),
imp_dto NUMBER(12, 2),
imp_iva NUMBER(12, 2),
totalfac NUMBER(12, 2)
);
ALTER TABLE facturas_tot ADD CONSTRAINT pk_facturas_tot PRIMARY KEY ( numfac );
ALTER TABLE facturas_tot ADD CONSTRAINT fk_facturas_tot_facturas_cab
FOREIGN KEY ( numfac ) REFERENCES facturas_cab ( numfac ) ON DELETE CASCADE;

CREATE TABLE estadisticas_clientes (
anio NUMBER(10) NOT NULL, -- año
mes_num NUMBER(10) NOT NULL, -- mes en numero
mes_nom VARCHAR2(13) NOT NULL, -- mes en nombre ( Enero, Febrero, ...)
dnicif VARCHAR2(13) NOT NULL,
nombrecli VARCHAR2(100) NOT NULL,
suma_base NUMBER(12, 2),
suma_dtos NUMBER(12, 2),
suma_iva NUMBER(12, 2),
suma_totales NUMBER(12, 2)
);
ALTER TABLE estadisticas_clientes ADD CONSTRAINT pk_estadisticas_clientes
PRIMARY KEY ( anio, mes_num, dnicif );
ALTER TABLE estadisticas_clientes ADD CONSTRAINT fk_estadisticas_clientes
FOREIGN KEY ( dnicif ) REFERENCES clientes ( dnicif );
COMMIT;
INSERT INTO ARTICULOS VALUES ('P01', 'Product 1', 300, 21, 19);
INSERT INTO ARTICULOS VALUES ('P02', 'Product 2', 500, 21, 14);
INSERT INTO ARTICULOS VALUES ('P03', 'Product 3', 600, 21, 50 );
INSERT INTO ARTICULOS VALUES ('P04', 'Warranty', 50, 21, 100000);
INSERT INTO ARTICULOS VALUES ('P05', 'Instalation', 150, 10, 100000);
INSERT INTO ARTICULOS VALUES ('P06', 'Transport', 20, 4, 100000);
COMMIT;
INSERT INTO CLIENTES VALUES ('C01','Client 1');
INSERT INTO CLIENTES VALUES ('C02','Client 2');
COMMIT;


create or replace PROCEDURE sp_crearEstadistica(dniI VARCHAR2,dniF VARCHAR2, fechaI DATE, fechaF DATE) AS
    numFact NUMBER;
    xMes NUMBER;
    xAnio NUMBER;
    xClientes CLIENTES%ROWTYPE;
    xFacturas FACTURAS_LIN%ROWTYPE;
    xEstadisticas ESTADISTICAS_CLIENTES%ROWTYPE;
    xFacturasTot FACTURAS_TOT%ROWTYPE;
    xFecha DATE;
    xExiste NUMBER;

    CURSOR CR_CLIENTE IS SELECT * 
    FROM CLIENTES 
    WHERE DNICIF BETWEEN dniI AND dniF;

    CURSOR CR_FACTURA IS SELECT TO_NUMBER(TO_CHAR(FECHAFAC, 'YYYY')) AS Anio, TO_NUMBER(TO_CHAR(FECHAFAC, 'MM')) AS Mes, NUMFAC, FECHAFAC
    FROM FACTURAS_CAB 
    WHERE DNICIF = xClientes.DNICIF 
    AND FECHAFAC BETWEEN fechaI AND fechaF;

    CURSOR CR_LINFACTURA IS SELECT * FROM FACTURAS_LIN WHERE NUMFAC = numFact;

BEGIN
    DELETE ESTADISTICAS_CLIENTES 
    WHERE ANIO 
    BETWEEN TO_NUMBER(TO_CHAR(fechaI, 'YYYY')) AND TO_NUMBER(TO_CHAR(fechaF, 'YYYY')) 
    AND MES_NUM BETWEEN TO_NUMBER(TO_CHAR(fechaI, 'MM')) AND TO_NUMBER(TO_CHAR(fechaF, 'MM')) 
    AND DNICIF BETWEEN dniI AND dniF;

    OPEN CR_CLIENTE;
        LOOP
            FETCH CR_CLIENTE INTO xClientes;
        EXIT WHEN CR_CLIENTE%NOTFOUND;

        OPEN CR_FACTURA;
            LOOP
                FETCH CR_FACTURA INTO xAnio, xMes, numFact, xFecha;
                EXIT WHEN CR_FACTURA%NOTFOUND;  

                OPEN CR_LINFACTURA;
                    LOOP
                        FETCH CR_LINFACTURA INTO xFacturas;
                    EXIT WHEN CR_LINFACTURA%NOTFOUND;

                    SELECT COUNT(*) INTO xExiste FROM ESTADISTICAS_CLIENTES WHERE DNICIF = xClientes.dnicif
                                                                            AND ANIO = xAnio 
                                                                            AND MES_NUM = xMes;                                                
                    SELECT * INTO xFacturasTot FROM FACTURAS_TOT WHERE NUMFAC = xFacturas.NUMFAC;

                    IF(xExiste <> 0) THEN
                      SELECT * INTO xEstadisticas FROM ESTADISTICAS_CLIENTES WHERE ANIO = xAnio AND MES_NUM = xMes AND DNICIF = xClientes.DNICIF;
                      DELETE ESTADISTICAS_CLIENTES WHERE ANIO = xAnio AND MES_NUM = xMes AND DNICIF = xClientes.DNICIF;
                      INSERT INTO ESTADISTICAS_CLIENTES VALUES(xAnio, 
                                                                xMes,
                                                                TO_CHAR(xFecha,'MONTH'),
                                                                xClientes.DNICIF,
                                                                xClientes.NOMBRECLI,
                                                              (xEstadisticas.SUMA_BASE + xFacturas.PRECIO),
                                                              (xEstadisticas.SUMA_DTOS + xFacturas.DTOLINEA),
                                                              (xEstadisticas.SUMA_IVA + xFacturas.IVALINEA),
                                                              (xEstadisticas.SUMA_TOTALES + xFacturasTot.TOTALFAC));
                    END IF;

                    IF(xExiste = 0) THEN
                        INSERT INTO ESTADISTICAS_CLIENTES VALUES(xAnio, 
                                                                xMes, 
                                                                TO_CHAR(xFecha,'MONTH'), 
                                                                xClientes.DNICIF, 
                                                                xClientes.NOMBRECLI, 
                                                                xFacturas.PRECIO, 
                                                                xFacturas.DTOLINEA, 
                                                                xFacturas.IVALINEA, 
                                                                xFacturasTot.TOTALFAC);
                    END IF;
                END LOOP;
            CLOSE CR_LINFACTURA;   
            END LOOP;
        CLOSE CR_FACTURA;
        END LOOP;
    CLOSE CR_CLIENTE;
    COMMIT;
END;