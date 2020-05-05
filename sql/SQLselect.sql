SELECT *
FROM LASKU;

SELECT *
FROM MAKSAJA;

SELECT L.id, L.kuvaus, L.hinta, L.pvm, M.nimi
FROM LASKU L
JOIN MAKSAJA M ON M.id = L.maksaja;