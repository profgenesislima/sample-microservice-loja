INSERT INTO CLIENTE(nome,email,senha) VALUES ('genesis','genesis@email.com','123123');
INSERT INTO CLIENTE(nome,email,senha) VALUES ('lima','lima@email.com','123123');
INSERT INTO CLIENTE(nome,email,senha) VALUES ('maria','maria@email.com','123123');

INSERT INTO PRODUTO(nome,descricao,preco) VALUES ('LEGO','Blocos Criativos.',400.00);
INSERT INTO PRODUTO(nome,descricao,preco) VALUES ('Funko Pop! Jason Voohees','Personagens do filme sexta-feira 13.',100.00);
INSERT INTO PRODUTO(nome,descricao,preco) VALUES ('Action Figure Chucky','Personagem realista do filme Brinquedo Assassino.',350.00);


INSERT INTO PEDIDO(cliente_id,produto_id,quantidade,valor_total) VALUES (1,1,10,4000.00);
INSERT INTO PEDIDO(cliente_id,produto_id,quantidade,valor_total) VALUES (1,2,5,500.00);

