package ui;

import java.util.*;

import pessoas.*;
import cadastros.*;
import fachada.*;
import produtos.*;
import franquias.*;

public class main {

	public static void main(String[] args) {
		String cardapio = "";
		long tempoInicio = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		Loja loja = new Loja(false);
		//tela inicial
		System.out.println("-------SEJA BEM VINDO AO ACAICIN-------");
		System.out.println("-------GELADOS E VARIEDADES------------");
		// so pode rodar com um funcionario usando
		System.out.println("-------PARA INICIALIZAR O PROGAMA------");
		System.out.println("-------CADASTRE-SE NO SISTEMA----------");
		boolean parouInicio = false;
		while (!parouInicio) {
			try {
				System.out.println("CPF: ");
				long cpf = in.nextLong();
				in.nextLine();
				System.out.println("NOME: ");
				String nome = in.nextLine();
				System.out.println("IDADE: ");
				int idade = in.nextInt();
				in.nextLine();
				ContaFuncionario funcionario = new ContaFuncionario(cpf, nome, idade);
				try {
					loja.cadastroPessoas(funcionario);
					parouInicio = true;
				} catch (CpfCadastradoException | NumeroCadastroExcedidoException e2) {
				}
			} catch (InputMismatchException e) {
				System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF E DA IDADE***\n");
				in.nextLine();
			}
		}
		boolean parou = false;
		// inicio interface do funcionario
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
					System.out.println("-----INTERFACE DA OPCAO PESSOA-----");
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
						try {
							System.out.println("CPF: ");
							long cpf = in.nextLong();
							in.nextLine();
							System.out.println("NOME: ");
							String nome = in.nextLine();
							System.out.println("IDADE: ");
							int idade = in.nextInt();
							in.nextLine();
							try {
								loja.cadastroPessoas(loja.criarContaCliente(cpf, nome, idade, escolhaPessoa));
								System.out.println("****CADASTRADO COM SUCESSO****\n");
							} catch (CpfCadastradoException e) {
								System.out.println("****Cpf ja cadastrado****\n");
							} catch (NumeroCadastroExcedidoException e) {
								System.out.println("****Numero de Cadastros Excedido****\n");
							}
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF E DA IDADE***\n");
							in.nextLine();
						}
						// interface cadastro funcionario
					} else if (escolhaPessoa == 2) {
						try {
							System.out.println("CPF: ");
							long cpf = in.nextLong();
							in.nextLine();
							System.out.println("NOME: ");
							String nome = in.nextLine();
							System.out.println("IDADE: ");
							int idade = in.nextInt();
							in.nextLine();
							try {
								loja.cadastroPessoas(loja.criarContaCliente(cpf, nome, idade, escolhaPessoa));
								System.out.println("****CADASTRADO COM SUCESSO****\n");
							} catch (CpfCadastradoException e) {
								System.out.println("****Cpf ja cadastrado****\n");
							} catch (NumeroCadastroExcedidoException e) {
								System.out.println("****Numero de Cadastros Excedido****\n");
							}
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF E DA IDADE***\n");
							in.nextLine();
						}
					} else if (escolhaPessoa == 3) {
						//remover cliente
						System.out.println("DIGITE APENAS O CPF PARA REMOVER : ");
						try {
							long cpf = in.nextLong();
							in.nextLine();
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
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
							in.nextLine();
						}
					} else if (escolhaPessoa == 4) {
						//remover funcionario
						System.out.println("DIGITE APENAS O CPF PARA REMOVER : ");
						try {
							long cpf = in.nextLong();
							in.nextLine();
							try {
								if (loja.procurarPessoa(cpf).getCliente()) {
									try {
										throw new TipoContaErradaFuncionarioException();
									} catch (TipoContaErradaFuncionarioException e) {
										System.out.println(
												"\n****Voce se referiu a uma conta de cliente, por favor insira uma de FUNCIONARIO****\n");
									}
								} else {
									loja.removerPessoa(cpf);
									System.out.println("****CONTA DE FUNCIONARIO REMOVIDA****\n");
								}
							} catch (CpfNaoCadastradoException e) {
								System.out.println("****Cpf nao cadastrado****\n");
							}
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
							in.nextLine();
						}
						// interface pessoa procurar
					} else if (escolhaPessoa == 5) {
						System.out.println("DIGITE APENAS O CPF PARA PROCURAR : ");
						try {
							long cpf = in.nextLong();
							in.nextLine();
							try {
								ContaAbstrata conta = loja.procurarPessoa(cpf);
								if (conta.getCliente()) {
									String nome = conta.getNome();
									cpf = conta.getCpf();
									int idade = conta.getIdade();
									double saldo = conta.getSaldo();
									conta.creditar(125);
									System.out.printf(
											"\n--CONTA CLIENTE--\nNome: %s\nCpf: %s\nIdade: %d\nSaldo: R$%.2f\n\n",
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
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
							in.nextLine();
						}
					} else if (escolhaPessoa == 6) {
						System.out.println("DIGITE APENAS O CPF PARA PROCURAR : ");
						try {
							long cpf = in.nextLong();
							in.nextLine();
							try {
								ContaAbstrata conta = loja.procurarPessoa(cpf);
								if (!conta.getCliente()) {
									String nome = conta.getNome();
									cpf = conta.getCpf();
									int idade = conta.getIdade();
									double saldo = conta.getSaldo();
									System.out.printf(
											"\n--CONTA FUNCIONARIO--\nNome: %s\nCpf: %s\nIdade: %d\nSaldo: R$%.2f\n\n",
											nome, cpf, idade, saldo);
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
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
							in.nextLine();
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
				// interface produtos
			} else if (escolha == 2) {
				boolean parouProduto = false;
				while (!parouProduto) {
					System.out.println("-----INTERFACE DA OPCAO PRODUTO-----");
					System.out.println("---> 1: CADASTRAR PRODUTO");
					System.out.println("---> 2: REMOVER PRODUTO");
					System.out.println("---> 3: PROCURAR PRODUTO");
					System.out.println("---> 4: LISTAR PRODUTOS");
					System.out.println("---> 0: VOLTAR A PAGINA ANTERIOR");
					int escolhaProduto = in.nextInt();
					in.nextLine();
					// interface de retorno
					if (escolhaProduto == 0) {
						parouProduto = true;
						// interface cadastrar produto
					} else if (escolhaProduto == 1) {
						System.out.println("NOME: ");
						String nomeProduto = in.nextLine();
						System.out.println("PRECO: ");
						double preco = in.nextDouble();
						System.out.println("TAMANHO: ");
						int tamanho = in.nextInt();
						try {
							loja.cadastrarProduto(loja.criarContaProduto(escolhaProduto, nomeProduto, preco, tamanho));
							System.out.println("****CADASTRADO COM SUCESSO****\n");
							cardapio += (nomeProduto + " | " + tamanho + "ml" + " | " + "R$" + (String.valueOf(preco))
									+ "\n");
						} catch (ProdutoJaCadastrado e) {
							System.out.println("****PRODUTO JA CADASTRADO****\n");
						} catch (NumeroLimiteExcedido e) {
							System.out.println("****NUMERO DE CADASTROS EXCEDIDOS****\n");
						}
						// interface remover produto
					} else if (escolhaProduto == 2) {
						System.out.println("DIGITE O NOME DO PRODUTO PARA REMOVER: ");
						String nomeProduto = in.nextLine();
						try {
							ClasseProduto produtoEncontrado = loja.procurarProduto(nomeProduto);
							double preco = produtoEncontrado.getPreco();
							int tamanho = produtoEncontrado.getTamanho();
							loja.removerProduto(nomeProduto);
							System.out.println("****PRODUTO REMOVIDO COM SUCESSO****\n");
							cardapio = cardapio.replace((nomeProduto + " | " + tamanho + "ml" + " | " + "R$"
									+ (String.valueOf(preco)) + "\n"), "");
						} catch (ProdutoNaoCadastrado e) {
							System.out.println("****PRODUTO NAO CADASTRADO****\n");
						} catch (ProdutoNaoEncontrado e) {
							System.out.println("****PRODUTO NAO ENCONTRADO****\n");
						}
						// interface procurar produto
					} else if (escolhaProduto == 3) {
						System.out.println("DIGITE O NOME DO PRODUTO PARA PROCURAR: ");
						try {
							String nomeProduto = in.nextLine();
							ClasseProduto produtoEncontrado = loja.procurarProduto(nomeProduto);
							String nome = produtoEncontrado.getNome();
							double preco = produtoEncontrado.getPreco();
							int tamanho = produtoEncontrado.getTamanho();
							System.out.printf("\n--PRODUTO--\nNome: %s\nPRECO: R$%.2f\nTAMANHO: %d\n\n", nome, preco,
									tamanho);
						} catch (ProdutoNaoEncontrado e) {
							System.out.println("****PRODUTO NAO ENCONTRADO****\n");
						}
					} else if (escolhaProduto == 4) {
						if (cardapio.equals("")) {
							System.out.println("****NAO HA PRODUTOS CADASTRADOS****\n");
						} else {
							System.out.println(cardapio);
						}
					}
				}
			} else if (escolha == 3) {
				boolean parouLoja = false;
				while (!parouLoja) {
					System.out.println("-----INTERFACE DA OPCAO FRANQUIA-----");
					System.out.println("---> 1: CADASTRAR FRANQUIA");
					System.out.println("---> 2: REMOVER FRANQUIA");
					System.out.println("---> 3: PROCURAR FRANQUIA");
					System.out.println("---> 0: VOLTAR A PAGINA ANTERIOR");
					int escolhaLoja = in.nextInt();
					in.nextLine();
					// interface de retorno
					if (escolhaLoja == 0) {
						parouLoja = true;
						// interface cadastrar Loja
					} else if (escolhaLoja == 1) {
						System.out.println("CODIGO: ");
						try {
							int codeLoja = in.nextInt();
							in.nextLine();
							System.out.println("ENDERECO: ");
							String endereco = in.nextLine();
							System.out.println("CNPJ: ");
							long cnpj = in.nextInt();
							in.nextLine();
							try {
								loja.cadastrarFranquia(loja.criarContaFranquia(codeLoja, endereco, cnpj));
								System.out.println("****CADASTRADA COM SUCESSO****\n");
							} catch (CodigoCadastradoException e) {
								System.out.println("****LOJA JA CADASTRADA****\n");
							} catch (LimiteLojasAtingidoException e) {
								System.out.println("****NUMERO LIMITE DE LOJAS ATINGIDO****\n");
							}
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CODIGO***\n");
						}
						// interface remover loja
					} else if (escolhaLoja == 2) {
						System.out.println("DIGITE O CODIGO DA LOJA PARA REMOVER: ");
						int codeLoja = in.nextInt();
						try {
							loja.removerFranquia(codeLoja);
							System.out.println("****LOJA REMOVIDA COM SUCESSO****\n");
						} catch (CodigoInexistenteException e) {
							System.out.println("****LOJA NAO CADASTRADA****\n");
						}
						// interface procurar loja
					} else if (escolhaLoja == 3) {
						System.out.println("DIGITE O CODIGO DA LOJA PARA PROCURAR: ");
						try {
							int codeLoja = in.nextInt();
							Franquia LojaEncontrada = loja.procurarFranquia(codeLoja);
							int code = LojaEncontrada.getCodigo();
							String endereco = LojaEncontrada.getEndereco();
							long cnpj = LojaEncontrada.getCnpj();
							System.out.printf("\n--LOJA--\nCODIGO: %d\nENDERECO: %s\nCNPJ: %d\n\n", code, endereco,
									cnpj);
						} catch (CodigoInexistenteException e) {
							System.out.println("****LOJA NAO ENCONTRADA****\n");
						}
					}
				}

			} else if (escolha == 4) {
				boolean parouNegocios = false;
				while (!parouNegocios) {
					System.out.println("-----INTERFACE DA OPCAO NEGOCIOS-----");
					System.out.println("---> 1: CREDITAR NA CONTA");
					System.out.println("---> 2: FAZER PEDIDO");
					System.out.println("---> 0: VOLTAR A PAGINA ANTERIOR");
					int escolhaNegocios = in.nextInt();
					in.nextLine();
					if (escolhaNegocios == 1) {
						try {
							System.out.println("DIGITE O CPF DO CLIENTE : ");
							long cpf = in.nextLong();
							in.nextLine();
							try {
								ContaAbstrata conta = loja.procurarPessoa(cpf);
								if (conta.getCliente()) {
									System.out.println("DIGITE QUANTO VOCE QUER CREDITAR NA CONTA : ");
									try {
										double dinheiro = in.nextDouble();
										in.nextLine();
										double saldo = conta.getSaldo();
										ContaAbstrata conta2 = loja.criarContaCliente(cpf, conta.getNome(),
												conta.getIdade(), 1);
										loja.setSaldo(saldo, conta2); // temos que criar uma conta nova pra creditar
										loja.creditar(dinheiro, conta2); // loja credita
										loja.atualizarPessoa(conta2); // loja atualiza por cima
										System.out.println("***CREDITADO NA CONTA COM SUCESSO***\n");
									} catch (InputMismatchException e) {
										System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
										in.nextLine();
									}
								} else {
									try {
										throw new TipoContaErradaFuncionarioException();
									} catch (TipoContaErradaFuncionarioException e) {
										System.out.println(
												"\n****Voce se referiu a uma conta de FUNCIONARIO, por favor insira uma de CLIENTE****\n");
									}
								}
							} catch (CpfNaoCadastradoException e) {
								System.out.println("****Cpf nao cadastrado****\n");
							}
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
							in.nextLine();
						}

					} else if (escolhaNegocios == 2) {
						System.out.println("DIGITE APENAS O CODIGO DA LOJA: ");
						try {
							int code = in.nextInt();
							in.nextLine();
							try {
								loja.procurarFranquia(code);
								System.out.println("DIGITE APENAS O CPF DO FUNCIONARIO QUE ESTA VENDENDO O PRODUTO : ");
								try {
									long cpf = in.nextLong();
									in.nextLine();
									try {
										ContaAbstrata conta = loja.procurarPessoa(cpf);
										if (conta.getCliente()) {
											try {
												throw new TipoContaErradaFuncionarioException();
											} catch (TipoContaErradaFuncionarioException e) {
												System.out.println(
														"\n****Voce se referiu a uma conta de cliente, por favor insira uma de FUNCIONARIO****\n");
											}

										} else {
											System.out.println("DIGITE APENAS O CPF DO CLIENTE : ");
											cpf = in.nextLong();
											in.nextLine();
											ContaAbstrata conta2 = loja.procurarPessoa(cpf);
											ContaCliente conta3 = (ContaCliente) conta2;
											if (!conta2.getCliente()) {
												try {
													throw new TipoContaErradaClienteException();
												} catch (TipoContaErradaClienteException e) {
													System.out.println(
															"\n****Voce se referiu a uma conta de funcionario, por favor insira uma de CLIENTE****\n");
												}
											} else {
												System.out.println("QUAL PRODUTO O CLIENTE DESEJA CONSUMIR : ");
												System.out.println(cardapio);
												try {
													String nomeProduto = in.nextLine();
													ClasseProduto produtoEncontrado = loja.procurarProduto(nomeProduto);
													double preco = produtoEncontrado.getPreco();
													try {
														conta3.debitar(preco);
														System.out.println(
																"*****COMPRE EFETUADA ---- VOLTE SEMPRE!*****");
													} catch (SaldoInsuficiente e) {
														double resultado = e.getSaldo();
														System.out.printf(
																"\n*****SALDO INSUFICIENTE*****\n*****DEPOSITE R$%.2f*****\n\n",
																preco - resultado);
													}
												} catch (ProdutoNaoEncontrado e) {
													System.out.println("****PRODUTO NAO ENCONTRADO****\n");
												}
											}

										}
									} catch (CpfNaoCadastradoException e) {
										System.out.println("****Cpf nao cadastrado****\n");
									}
								} catch (InputMismatchException e) {
									System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CPF***\n");
									in.nextLine();
								}
							} catch (CodigoInexistenteException e) {
								System.out.println("***CODIGO NAO CADASTRADO***\n");
							}
						} catch (InputMismatchException e) {
							System.out.println("***SO DIGITE NUMEROS NA SESSAO DO CODIGO***\n");
						}
					} else if (escolhaNegocios == 0) {
						parouNegocios = true;
					} else {
						try {
							throw new NumeroInvalidoException();
						} catch (NumeroInvalidoException e) {
							System.out.println(
									"*****Numero Invalido, por favor digite apenas o numero que tem opcao.*****\n");
						}
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
		System.out.println("---SESSÃO ENCERRADA----");
		System.out.println("Tempo da Sessão : " + ((System.currentTimeMillis() - tempoInicio) / 1000) + " segundos");
	}
}
