
INSERT INTO questions (id, description, technology) VALUES
('1f9b2c22-8f9d-45af-8333-e8b24adf3032', 'Como criar uma classe em Java','JAVA'),
('b39b2aa0-14c0-4c54-ac2a-846f72ce791a','Explique o conceito de polimorfismo em Java.','JAVA'),
('b71947a4-36bc-4688-aad9-bbc74f4dcc68','Como lidar com exceções em Java','JAVA');

INSERT INTO alternatives (id,question_id, is_correct, description) VALUES
('038552a9-e529-4335-93cb-c420f84061a4' , '1f9b2c22-8f9d-45af-8333-e8b24adf3032', true,'Usando a palavra-chave "class"'),
('3e7655df-f355-465d-8d19-9f8036983736' , '1f9b2c22-8f9d-45af-8333-e8b24adf3032', false, 'Definindo uma interface em Java'),
('9f9ba7a8-ab5d-46af-b00f-63f57994f7a7' , '1f9b2c22-8f9d-45af-8333-e8b24adf3032', false, 'Utilizando Métodos estáticos'),
('4c1a7d35-9d4e-4da7-9e39-be288fd8048a' , '1f9b2c22-8f9d-45af-8333-e8b24adf3032', false, 'Criando um construtor padão');

INSERT INTO alternatives (id,question_id, is_correct, description) VALUES
('d23fb296-1ad3-44e6-859a-bbca1062e7ef' , 'b39b2aa0-14c0-4c54-ac2a-846f72ce791a', false,'Herança simples'),
('4645bf53-db87-446c-a336-193f1f53e3ab' , 'b39b2aa0-14c0-4c54-ac2a-846f72ce791a', false, 'Encapsulamento em java'),
('7334300f-3d3e-494a-b40c-4ec41164ed26' , 'b39b2aa0-14c0-4c54-ac2a-846f72ce791a', false, 'Sobrecarga de métodos'),
('d273db91-84d3-4102-a0df-620698976049' , 'b39b2aa0-14c0-4c54-ac2a-846f72ce791a', true, 'Capacidade de um objeto de assumir várias formas');

INSERT INTO alternatives (id,question_id, is_correct, description) VALUES
('51acdf2e-0e3b-4364-b38f-b974858a1445' , 'b71947a4-36bc-4688-aad9-bbc74f4dcc68', false,'Ignorando a exceção'),
('2d5a23fa-0b05-4202-accb-0e0341a7df84' , 'b71947a4-36bc-4688-aad9-bbc74f4dcc68', true, 'Utilizando blocos try-catch'),
('03fb4b1f-3766-41b3-9f68-973af3982435' , 'b71947a4-36bc-4688-aad9-bbc74f4dcc68', false, 'Declarando uma exceção sem tratamento'),
('fa98ec37-a633-4c52-a883-39195e081447' , 'b71947a4-36bc-4688-aad9-bbc74f4dcc68', false, 'Usando a palavra-chave "finally"');