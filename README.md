# Controle de Academia

Em Java, este programa foi desenvolvido para o controle de uma academia, atendendo aos seguintes requisitos mínimos:

## Funcionalidades

### Cadastro de Alunos

O programa permite o cadastro de alunos com as seguintes funcionalidades:

- Incluir
- Alterar
- Excluir
- Listar
- Buscar pelo CPF
- Buscar pelo nome

Cada aluno possui os seguintes dados:

| CPF              | Nome    | Data de Nascimento |
|------------------|---------|---------------------|
| 123.456.789-01   | Fulano  | 20/01/1976          |
| 234.567.890-12   | Ciclano | 01/01/2000          |

### Cadastro de Planos

O programa também permite o cadastro de planos, onde cada plano possui:

| Código | Nome     | Valor Mensal |
|--------|----------|--------------|
| 1      | Simples  | 79,90       |
| 2      | Gold     | 89,90       |
| 3      | Premium  | 99,90       |

### Cadastro de Exercícios

O programa realiza o cadastro de exercícios, onde cada exercício contém:

| Número | Nome                     | Músculos Ativados                    |
|--------|--------------------------|--------------------------------------|
| 01     | Leg Press                | Quadríceps, Glúteos                 |
| 05     | Cadeira Adutora          | Adutores                             |
| 20     | Supino Máquina           | Peitorais, Tríceps                  |
| 26     | Crucifixo Máquina        | Peitorais                            |
| 40     | Abdominal Máquina        | Abdominais                           |
| 50     | Desenvolvimento Máquina   | Deltoides, Trapézio, Tríceps       |

### Opções de Treino

Para alunos cadastrados, o instrutor pode:

- Cadastrar um plano, contendo: data de início do plano, dados do cartão de crédito.
- Cadastrar uma ou mais opções de treino, onde cada opção contém uma lista de exercícios.

Para cada exercício, o programa informa:

- Número de séries
- Número mínimo e máximo de repetições
- Carga utilizada (em kg)
- Tempo de descanso (em minutos)

### Início de Treino

O aluno pode, em determinada data, iniciar um treino:

- Escolher um treino dentre as opções disponíveis.
- Consultar os exercícios a serem feitos, mostrando os dados cadastrados.
- Marcar exercícios do treino que foram concluídos.
- Alterar a carga de um determinado exercício.
- Encerrar um treino.

## Relatórios

- O programa mostra as datas em que o aluno compareceu à academia em um intervalo de datas.
- Para um determinado exercício, o programa mostra a evolução de carga ao longo do tempo.

## Requisitos

O programa foi realizado com banco de dados Postgres. Para funcionar corretamente, utilize o banco Postgres e troque o ID, senha e nome do banco de dados nos métodos `Main` e `Utils`.

O programa foi feito para gerar arquivos na pasta do diretório `outputDir`. É bom para quando o programa for usado em um caso real. Para teste, `outputDir` está direcionando para a pasta do programa.

Para exemplo, deixamos um banco de dados para ser testado.
