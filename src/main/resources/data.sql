INSERT INTO livros (titulo, autor, editora, genero, sinopse, ano_publicacao) VALUES
    ('O Hobbit', 'J.R.R. Tolkien', 'HarperCollins', 'FANTASIA', 'Bilbo Bolseiro é um hobbit pacato que tem sua vida transformada quando o mago Gandalf e treze anões o recrutam para uma aventura épica em busca de um tesouro guardado por um dragão.', 1937),
    ('Duna', 'Frank Herbert', 'Chilton Books', 'FICCAO_CIENTIFICA', 'No planeta desértico Arrakis, o jovem Paul Atreides enfrenta traições, guerras e seu próprio destino enquanto tenta controlar a especiaria mais valiosa do universo.', 1965),
    ('1984', 'George Orwell', 'Secker & Warburg', 'DISTOPIA', 'Em um futuro totalitário, Winston Smith trabalha para o Partido reescrevendo a história, até que começa a questionar o regime e se apaixonar por Julia.', 1949),
    ('It: A Coisa', 'Stephen King', 'Viking Press', 'TERROR', 'Em Derry, um grupo de crianças enfrenta uma entidade maligna que assume a forma dos maiores medos de suas vítimas, geralmente aparecendo como o palhaço Pennywise.', 1986),
    ('Dom Casmurro', 'Machado de Assis', 'Garnier', 'ROMANCE', 'Bentinho narra sua vida e seu amor por Capitu, levantando a famosa questão: ela o traiu ou foi tudo imaginação de um narrador ciumento e não confiável?', 1899),
    ('O Nome do Vento', 'Patrick Rothfuss', 'DAW Books', 'AVENTURA', 'Kvothe, um lendário herói misterioso, narra sua própria história: de orfão nas ruas até estudante prodígio na universidade de magia e músico incomparável.', 2007),
    ('Sapiens', 'Yuval Noah Harari', 'Companhia das Letras', 'NAO_FICCAO', 'Uma viagem pela história da humanidade, desde os primeiros Homo sapiens na África até as revoluções científica e industrial que moldaram o mundo moderno.', 2011),
    ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'Gallimard', 'INFANTIL', 'Um aviador perdido no deserto encontra um pequeno príncipe vindo de outro planeta, que lhe conta sobre suas viagens e sobre o que realmente importa na vida.', 1943),
    ('Sherlock Holmes: Um Estudo em Vermelho', 'Arthur Conan Doyle', 'Ward Lock & Co', 'MISTERIO', 'O primeiro encontro entre Sherlock Holmes e Dr. Watson, que juntos investigam um assassinato misterioso em Londres usando dedução e lógica brilhantes.', 1887),
    ('O Poder do Hábito', 'Charles Duhigg', 'Random House', 'DESENVOLVIMENTO_PESSOAL', 'Baseado em pesquisas científicas, o livro explica como os hábitos funcionam e como podemos transformá-los para melhorar nossa vida pessoal e profissional.', 2012),
    ('Cem Anos de Solidão', 'Gabriel García Márquez', 'Harper & Row', 'DRAMA', 'A saga da família Buendía ao longo de sete gerações na fictícia Macondo, entrelaçando realidade e fantasia em uma das obras mais importantes da literatura mundial.', 1967),
    ('O Guia do Mochileiro das Galáxias', 'Douglas Adams', 'Pan Books', 'COMEDIA', 'Arthur Dent é o único sobrevivente da destruição da Terra para dar lugar a uma rodovia intergaláctica, e parte em uma viagem absurda e hilária pelo universo.', 1979),
    ('Steve Jobs', 'Walter Isaacson', 'Simon & Schuster', 'BIOGRAFIA', 'A história definitiva do cofundador da Apple, baseada em entrevistas exclusivas com Jobs e pessoas próximas, revelando sua genialidade, perfeccionismo e contradições.', 2011),
    ('O Cortiço', 'Aluísio Azevedo', 'Garnier', 'DRAMA', 'A vida coletiva e as relações de poder em um cortiço no Rio de Janeiro do século XIX, retratando com realismo a luta pela sobrevivência e a degradação humana.', 1890),
    ('Jogador Número Um', 'Ernest Cline', 'Crown Publishers', 'FICCAO_CIENTIFICA', 'Em 2045, o mundo vive no OASIS, uma realidade virtual. Quando seu criador morre e esconde um tesouro no jogo, uma corrida global e perigosa tem início.', 2011);

INSERT INTO usuarios (email, nick, senha, role) VALUES
    ('admin@email.com', 'admin', '$2a$12$JGFg.SNxR.DXUjgGC1D.C.kQDydF/CPw1U2It5qbFiOJGGIKScD9S', 'ADMIN'), -- senha: admin123
    ('joao@email.com', 'joaozinho', '$2a$12$yiF3YoqpP8Mhmgv0HF530.USRIXexaCG4GT0CK9RKBX5aFB7tVdXy', 'USER'), -- senha: 123456
    ('maria@email.com', 'mariazinha', '$2a$12$yiF3YoqpP8Mhmgv0HF530.USRIXexaCG4GT0CK9RKBX5aFB7tVdXy', 'USER'),
    ('carlos@email.com', 'carlao', '$2a$12$yiF3YoqpP8Mhmgv0HF530.USRIXexaCG4GT0CK9RKBX5aFB7tVdXy', 'USER'),
    ('ana@email.com', 'aninha', '$2a$12$yiF3YoqpP8Mhmgv0HF530.USRIXexaCG4GT0CK9RKBX5aFB7tVdXy', 'USER');