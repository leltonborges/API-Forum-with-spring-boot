INSERT INTO USUARIO(nome, email, senha)
VALUES
       ('Aluno', 'aluno@email.com', '$2a$10$jcrw8hc1UiQTeY4hBbHrdObygtaATFmRGANJ4JV8f6H2jrID44mkq'),
       ('Moderador', 'moderador@email.com', '$2a$10$jcrw8hc1UiQTeY4hBbHrdObygtaATFmRGANJ4JV8f6H2jrID44mkq');

INSERT INTO PERFIL(nome)
VALUES
       ('ROLE_ALUNO'),
       ('ROLE_MODERADOR');

INSERT INTO USUARIO_PERFIS
VALUES
       (1,1),
       (2,2),
       (2,1);

INSERT INTO CURSO(nome, categoria)
VALUES
       ('Spring Boot', 'Programação'),
       ('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id)
VALUES
       ('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1),
       ('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1),
       ('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2),
       ('Dúvida 4', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2),
       ('Dúvida 5', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);

INSERT INTO RESPOSTA(mensagem, topico_id, data_criacao, autor_id, solucao)
    VALUES
        ('Test 1', 1, current_date, 1, false ),
        ('Test 2', 2, current_date, 1, true ),
        ('Test 3', 3, current_date, 1, true ),
        ('Test 4', 2, current_date, 1, false ),
        ('Test 5', 3, current_date, 1, false ),
        ('Test 6', 1, current_date, 1, true );