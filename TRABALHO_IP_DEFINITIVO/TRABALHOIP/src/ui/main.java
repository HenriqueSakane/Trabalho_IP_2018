package ui;

import java.util.*;
import pessoas.*;
import excecoes.*;
import cadastros.*;
import fachada.*;

public class main {

	public static void main(String[] args) {
		long tempoInicio = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		Loja loja = new Loja(true);
		System.out.println("-------SEJA BEM VINDO AO ACAICIN-------");
		System.out.println("-------GELADOS E VARIEDADES------------");
		// so pode rodar com um funcionario usando
		System.out.println("-------PARA INICIALIZAR O PROGAMA------");
		System.out.println("-------CADASTRE-SE NO SISTEMA----------");
		System.out.println("CPF: ");
		String cpf = in.nextLine();
		System.out.println("NOME: ");
		String nome = in.nextLine();
		System.out.println("IDADE: ");
		String idadeS = in.nextLine();
		int idade = Integer.parseInt(idadeS);
		ContaFuncionario funcionario = new ContaFuncionario(cpf, nome, idade);
		try {
			loja.cadastroPessoas(funcionario);
		} catch (CpfCadastradoException | NumeroCadastroExcedidoException e2) {
			System.out.println("" + e2);
		}
		boolean parou = false;
		// inicio interface
		while (!parou) {
			System.out.println("----BEM VINDO A INTERFACE DO FUNCIONARIO----");
			System.out.println("-----SELECIONE UMA DAS OPCOES A SEGUIR------");
			System.out.println("---> 1: PESSOAS");
			System.out.println("---> 2: PRODUTOS");
			System.out.println("---> 3: FRANQUIAS");
			System.out.println("---> 4: NEGOCIOS");
			System.out.println("---> 0: FINALIZAR SESSAO\n");
			int escolha = in.nextInt();
			if (escolha == 0) {
				parou = true;
			} else if (escolha == 1) {
				boolean parouCliente = false;
				// interface Pessoa
				while (!parouCliente) {
					System.out.println("---> 1: CADASTRO DE CLIENTE");
					System.out.println("---> 2: CADASTRO DE FUNCIONARIO");
					System.out.println("---> 3: REMOVER CLIENTE");
					System.out.println("---> 4: REMOVER FUNCIONARIO");
					System.out.println("---> 5: PROCURAR CLIENTE");
					System.out.println("---> 6: PROCURAR FUNCIONARIO");
					System.out.println("---> 0: VOLTAR A PAGINA ANTERIOR");
					int escolhaPessoa = in.nextInt();
					in.nextLine();
					if (escolhaPessoa == 0) {
						parouCliente = true;
						// interface Cadastro Cliente
					} else if (escolhaPessoa == 1) {
						System.out.println("CPF: ");
						cpf = in.nextLine();
						System.out.println("NOME: ");
						nome = in.nextLine();
						System.out.println("IDADE: ");
						idadeS = in.nextLine();
						idade = Integer.parseInt(idadeS);
						ContaCliente cliente = new ContaCliente(cpf, nome, idade);
						try {
							loja.cadastroPessoas(cliente);
							System.out.println("****CADASTRADO COM SUCESSO****\n");
						} catch (CpfCadastradoException e) {
							System.out.println("****Cpf ja cadastrado****\n");
						} catch (NumeroCadastroExcedidoException e) {
							System.out.println("****Numero de Cadastros Excedido****\n");
						}
						// interface cadastro funcionario
					} else if (escolhaPessoa == 2) {
						System.out.println("CPF: ");
						cpf = in.nextLine();
						System.out.println("NOME: ");
						nome = in.nextLine();
						System.out.println("IDADE: ");
						idadeS = in.nextLine();
						idade = Integer.parseInt(idadeS);
						funcionario = new ContaFuncionario(cpf, nome, idade);
						try {
							loja.cadastroPessoas(funcionario);
							System.out.println("****CADASTRADO COM SUCESSO****\n");
						} catch (CpfCadastradoException e) {
							System.out.println("****Cpf ja cadastrado****\n");
						} catch (NumeroCadastroExcedidoException e) {
							System.out.println("****Numero de Cadastros Excedido****\n");
						}
					} else if (escolhaPessoa == 3) {
						System.out.println("DIGITE APENAS O CPF PARA REMOVER : ");
						cpf = in.nextLine();
						try {
							if (!loja.procurarPessoa(cpf).getCliente()) {
								try {
									throw new TipoContaErradaClienteException();
								} catch (TipoContaErradaClienteException e) {
									System.out.println(
											"\n****Voce se referiu a uma conta de funcionario, por favor insira uma de CLIENTE****\n");
								}
							} else {
								loja.removerPessoa(cpf);
								System.out.println("****CONTA DE CLIENTE REMOVIDA****\n");
							}
						} catch (CpfNaoCadastradoException e) {
							System.out.println("****Cpf nao cadastrado****\n");
						}
					} else if (escolhaPessoa == 4) {
						System.out.println("DIGITE APENAS O CPF PARA REMOVER : ");
						cpf = in.nextLine();
						try {
							if (loja.procurarPessoa(cpf).getCliente()) {
								try {
									throw new TipoContaErradaFuncionarioException();
								} catch (TipoContaErradaFuncionarioException e) {
									System.out.println(
											"\n****Voce se referiu a uma conta de cliente, por favor insira uma de FUNCIONARIO9****\n");
								}
							} else {
								loja.removerPessoa(cpf);
								System.out.println("****CONTA DE FUNCIONARIO REMOVIDA****\n");
							}
						} catch (CpfNaoCadastradoException e) {
							System.out.println("****Cpf nao cadastrado****\n");
						}
					} else if (escolhaPessoa == 5) {
						System.out.println("DIGITE APENAS O CPF PARA PROCURAR : ");
						cpf = in.nextLine();
						try {
							ContaAbstrata conta = loja.procurarPessoa(cpf);
							if (conta.getCliente()) {
								nome = conta.getNome();
								cpf = conta.getCpf();
								idade = conta.getIdade();
								double saldo = conta.getSaldo();
								conta.creditar(125);
								System.out.printf("\n--CONTA CLIENTE--\nNome: %s\nCpf: %s\nIdade: %d\nSaldo: %.2f$\n\n",
										nome, cpf, idade, saldo);
							} else {
								try {
									throw new TipoContaErradaClienteException();
								} catch (TipoContaErradaClienteException e) {
									System.out.println(
											"\n****Voce se referiu a uma conta de funcionario, por favor insira uma de CLIENTE****\n");
								}
							}
						} catch (CpfNaoCadastradoException e) {
							System.out.println("****Cpf nao cadastrado****\n");
						}
					} else if (escolhaPessoa == 6) {
						System.out.println("DIGITE APENAS O CPF PARA PROCURAR : ");
						cpf = in.nextLine();
						try {
							ContaAbstrata conta = loja.procurarPessoa(cpf);
							if (!conta.getCliente()) {
								nome = conta.getNome();
								cpf = conta.getCpf();
								idade = conta.getIdade();
								double saldo = conta.getSaldo();
								System.out.printf(
										"\n--CONTA FUNCIONARIO--\nNome: %s\nCpf: %s\nIdade: %d\nSaldo: %.2f$\n\n", nome,
										cpf, idade, saldo);
							} else {
								try {
									throw new TipoContaErradaFuncionarioException();
								} catch (TipoContaErradaFuncionarioException e) {
									System.out.println(
											"\n****Voce se referiu a uma conta de cliente, por favor insira uma de FUNCIONARIO****\n");
								}
							}
						} catch (CpfNaoCadastradoException e) {
							System.out.println("****Cpf nao cadastrado****\n");
						}
					} else {
						try {
							throw new NumeroInvalidoException();
						} catch (NumeroInvalidoException e) {
							System.out.println(
									"*****Numero Invalido, por favor digite apenas o numero que tem opcao.*****\n");
						}
					}
				}
			} else if (escolha == 2) {

			} else if (escolha == 3) {

			} else if (escolha == 4) {
				boolean parouNegocios = false;
				while (!parouNegocios) {
					System.out.println("---> 1: CREDITAR NA CONTA");
					System.out.println("---> 2: CREDITAR NA FRANQUIA");
					System.out.println("---> 3: FAZER PEDIDO");
					System.out.println("---> 0: VOLTAR A PAGINA ANTERIOR");
					int escolhaNegocios = in.nextInt();
					in.nextLine();
					if (escolhaNegocios == 1) {
						System.out.println("DIGITE APENAS O CPF PARA PROCURAR CONTA DO CLIENTE : ");
					} else if (escolhaNegocios == 2) {

					} else if (escolhaNegocios == 3) {
						System.out.println("DIGITE APENAS O CPF DO FUNCIONARIO QUE ESTA VENDENDO O PRODUTO : ");
						cpf = in.nextLine();
						try {
							ContaAbstrata conta = loja.procurarPessoa(cpf);
						} catch (CpfNaoCadastradoException e) {
							System.out.println("****Cpf nao cadastrado****\n");
						}
					} else if (escolhaNegocios == 0) {
						parouNegocios = true;
					} else {

					}
				}
			} else {
				try {
					throw new NumeroInvalidoException();
				} catch (NumeroInvalidoException e) {
					System.out.println("*****Numero Invalido, por favor digite apenas o numero que tem opcao.*****\n");
				}
			}
		}
		System.out.println("---SESS�O ENCERRADA----");
		System.out.println("Tempo da Sess�o : " + ((System.currentTimeMillis() - tempoInicio) / 1000) + " segundos");
	}
}
