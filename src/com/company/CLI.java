package com.company;

import java.util.Scanner;

public class CLI {
    Scanner scanner;
    CandidatoDAO candidatoDAO;
    EmpresaDAO empresaDAO;

    CLI() {
        scanner = new Scanner(System.in);
        candidatoDAO = new CandidatoDAO();
        empresaDAO = new EmpresaDAO();

        initialize();
    }

    private void initialize() {
        System.out.println("Digite o nome do arquivo de candidatos: ");
        candidatoDAO.lerDados(scanner.nextLine());
        System.out.println("Digite o nome do arquivo de empresas: ");
        empresaDAO.lerDados(scanner.nextLine());

        do {
            exibirOpcoes();
            System.out.println("> Sua opção:");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 0:
                    candidatoDAO.salvarDados();
                    empresaDAO.salvarDados();
                    return;
                case 1:
                    cadastrarCandidato();
                    break;
                case 2:
                    buscarCandidato();
                    break;
                case 3:
                    removerCandidato();
                    break;
                case 4:
                    atualizarCandidato();
                    break;
                case 5:
                    cadastrarVaga();
                    break;
                case 6:
                    buscarVaga();
                    break;
                case 7:
                    removerVaga();
                    break;
                case 8:
                    atualizarVaga();
                    break;
            }

            scanner.nextLine();
        } while (true);
    }

    private void exibirOpcoes() {
        System.out.println("Escolha uma opção: ");

        System.out.println("- 0. Sair do sistema e salvar alterações");

        System.out.println("- Candidatos:");
        System.out.println("-- 1. Inserir");
        System.out.println("-- 2. Buscar");
        System.out.println("-- 3. Remover");
        System.out.println("-- 4. Editar");

        System.out.println("- Empresa:");
        System.out.println("-- 5. Inserir");
        System.out.println("-- 6. Buscar");
        System.out.println("-- 7. Remover");
        System.out.println("-- 8. Editar");

        System.out.println("- Outras opções:");
        System.out.println("-- 9. Buscar vagas por área de atuação e escolaridade máxima");
        System.out.println("-- 10. Exibir relatórios do sistema");
        System.out.println("-- 11. Alocar candidatos a uma vaga");
        System.out.println("-- 12. Alocar candidatos a todas as vagas");

    }

    private void cadastrarCandidato() {
        String identidade;
        String nome;
        String idade;
        String sexo;
        String escolaridade;
        String areaDeAtuacao;
        String pretensaoSalarial;

        System.out.println("Identidade: ");
        identidade = scanner.nextLine();
        System.out.println("Nome: ");
        nome = scanner.nextLine();
        System.out.println("Idade: ");
        idade = scanner.nextLine();
        System.out.println("Sexo: ");
        sexo = scanner.nextLine();
        System.out.println("Escolaridade: ");
        escolaridade = scanner.nextLine();
        System.out.println("Area de Atuação: ");
        areaDeAtuacao = scanner.nextLine();
        System.out.println("Pretensão Salarial: ");
        pretensaoSalarial = scanner.nextLine();

        Candidato c = new Candidato(
                identidade,
                nome,
                idade,
                sexo,
                escolaridade,
                areaDeAtuacao,
                pretensaoSalarial
        );

        candidatoDAO.cadastrar(c);
    }

    private void buscarCandidato() {
        System.out.println("Digite a identidade do candidato");
        Candidato c = candidatoDAO.buscar(Integer.parseInt(scanner.nextLine()));

        if (c == null) {
            System.out.println("O candidato não existe.");
            return;
        }

        System.out.println(c.toPrettyString());
    }

    private void atualizarCandidato() {
        System.out.println("Digite a identidade do candidato");
        Candidato c = candidatoDAO.buscar(Integer.parseInt(scanner.nextLine()));

        if (c == null) {
            System.out.println("O candidato não existe.");
            return;
        }

        System.out.printf("Identidade (%s): \n", c.getIdentidade());
        c.setIdentidade(Integer.parseInt(scanner.nextLine()));
        System.out.printf("Nome (%s): \n", c.getNome());
        c.setNome(scanner.nextLine());
        System.out.printf("Idade (%s): \n", c.getIdade());
        c.setIdade(Integer.parseInt(scanner.nextLine()));
        System.out.printf("Sexo (%s): \n", c.getSexo());
        c.setSexo(scanner.nextLine().charAt(0));
        System.out.printf("Escolaridade (%s): \n", c.getEscolaridade());
        c.setEscolaridade(scanner.nextLine());
        System.out.printf("Area de Atuação (%s): \n", c.getAreaDeAtuacao());
        c.setAreaDeAtuacao(scanner.nextLine());
        System.out.printf("Pretensão Salarial (%s): \n", c.getPretensaoSalarial());
        c.setPretensaoSalarial(Float.parseFloat(scanner.nextLine()));

        candidatoDAO.atualizar(c);
    }

    private void removerCandidato() {
        System.out.println("Digite a identidade do candidato a ser removido:");
        int identidade = Integer.parseInt(scanner.nextLine());

        candidatoDAO.excluir(identidade);
    }

    private void cadastrarVaga() {
        String nome;
        String areaAtuacao;
        String quantVagas;
        String escolaridadeMin;
        String salarioMax;

        System.out.println("Nome: ");
        nome = scanner.nextLine();
        System.out.println("Area de Atuação: ");
        areaAtuacao = scanner.nextLine();
        System.out.println("Quantidade de Vagas: ");
        quantVagas = scanner.nextLine();
        System.out.println("Escolaridade Mínima: ");
        escolaridadeMin = scanner.nextLine();
        System.out.println("Salário Máximo: ");
        salarioMax = scanner.nextLine();

        Empresa e = new Empresa(
                nome,
                areaAtuacao,
                quantVagas,
                escolaridadeMin,
                salarioMax
        );

        empresaDAO.cadastrar(e);
    }

    private void buscarVaga() {
        System.out.println("Digite o nome da empresa: ");
        Empresa e = empresaDAO.buscar(scanner.nextLine());

        if (e == null) {
            System.out.println("A vaga não existe.");
            return;
        }

        System.out.println(e.toPrettyString());
    }

    private void atualizarVaga() {
        System.out.println("Digite o nome da empresa: ");
        Empresa e = empresaDAO.buscar(scanner.nextLine());

        if (e == null) {
            System.out.println("A vaga não existe.");
            return;
        }

        System.out.println("Nome: ");
        e.setNome(scanner.nextLine());
        System.out.println("Area de Atuação: ");
        e.setAreaAtuacao(scanner.nextLine());
        System.out.println("Quantidade de Vagas: ");
        e.setQuantVagas(Integer.parseInt(scanner.nextLine()));
        System.out.println("Escolaridade Mínima: ");
        e.setEscolaridadeMin(scanner.nextLine());
        System.out.println("Salário Máximo: ");
        e.setSalarioMax(Float.parseFloat(scanner.nextLine()));

        empresaDAO.atualizar(e);
    }

    private void removerVaga() {
        System.out.println("Digite o nome da empresa que possui a vaga a ser removida:");
        String nomeEmpresa = scanner.nextLine();

        empresaDAO.excluir(nomeEmpresa);
    }
}
