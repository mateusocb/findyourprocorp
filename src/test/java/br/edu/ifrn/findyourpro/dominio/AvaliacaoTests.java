/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.edu.ifrn.findyourpro.dominio;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AvaliacaoTests {

	private static final String TIPO_SERVICO1 = "eletricista";
	private static final String TIPO_SERVICO2 = "encanador";
	private static final String DESCRICAO_SERVICO1 = "servico muito bom";
	private static final String DESCRICAO_SERVICO2 = "servico muito ruim";
	private static final String LOGIN1 = "johann";
	private static final String LOGIN2 = "mateus";
	private static final int NOTA1 = 10;
	private static final int NOTA2 = 5;
	private static final String DESCRICAO1 = "ele fez bem";
	private static final String DESCRICAO2 = "ele fez mal";
	private static final String NOME3 = "Mateus";
	private static final String LOGIN3 = "mateusocb";
	private static final String SENHA3 = "mesa";
	private static final String CPF1 = "400";

	private Usuario getUsuario() {
		return Usuario.builder().nome(NOME3).login(LOGIN3).senha(SENHA3).build();
	}

	private Autonomo autonomo() {
		return Autonomo.builder().usuario(this.getUsuario()).cpf(CPF1).build();
	}

	private Avaliacao avaliacao(String tipoServico, String descricaoServico, String login, int nota, String descricao, Date data, PrestadorDeServico prestador) {
		return Avaliacao.builder()
				.servico(Servico.builder().tipo(tipoServico).descricao(descricaoServico).prestador(prestador).build())
				.cliente(Usuario.builder().login(login).build())
				.nota(nota)
				.descricao(descricao)
				.data(data)
				.build();
	}

	@Test
	public void avalicaoServicoUsuarioDataIguaisDescricaoNotaDiferentes() {
		Date hoje = new Date();

		Avaliacao avaliacao1 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA1, DESCRICAO1, hoje, this.autonomo());
		Avaliacao avaliacao2 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA2, DESCRICAO2, hoje, this.autonomo());

		assertThat(avaliacao1).isEqualTo(avaliacao2);
	}

	@Test
	public void avalicaoServicoUsuarioDataDiferentesDescricaoNotaIguais() {
		Avaliacao avaliacao1 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA1, DESCRICAO1, new Date(), this.autonomo());
		Avaliacao avaliacao2 = avaliacao(TIPO_SERVICO2, DESCRICAO_SERVICO2, LOGIN2, NOTA1, DESCRICAO1, new Date(), this.autonomo());

		assertThat(avaliacao1).isNotEqualTo(avaliacao2);
	}

	@Test
	public void compareToComDatasDiferentes() {
		Set<Avaliacao> avaliacoes = new TreeSet<>();

		Avaliacao avaliacao1 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA1, DESCRICAO1, new Date(), this.autonomo());
		Avaliacao avaliacao2 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA1, DESCRICAO1, new Date(), this.autonomo());
		avaliacoes.add(avaliacao2);
		avaliacoes.add(avaliacao1);

		assertThat(avaliacoes.iterator().next()).isEqualTo(avaliacao1);
	}

	@Test
	public void compareToComServicosDiferentes() {
		Date hoje = new Date();

		Set<Avaliacao> avaliacoes = new TreeSet<>();

		Avaliacao avaliacao1 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA1, DESCRICAO1, hoje, this.autonomo());
		Avaliacao avaliacao2 = avaliacao(TIPO_SERVICO2, DESCRICAO_SERVICO2, LOGIN1, NOTA1, DESCRICAO1, hoje, this.autonomo());
		avaliacoes.add(avaliacao2);
		avaliacoes.add(avaliacao1);

		assertThat(avaliacoes.iterator().next()).isEqualTo(avaliacao1);
	}

	@Test
	public void compareToComUsuariosDiferentes() {
		Date hoje = new Date();

		Set<Avaliacao> avaliacoes = new TreeSet<>();

		Avaliacao avaliacao1 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN1, NOTA1, DESCRICAO1, hoje, this.autonomo());
		Avaliacao avaliacao2 = avaliacao(TIPO_SERVICO1, DESCRICAO_SERVICO1, LOGIN2, NOTA1, DESCRICAO1, hoje, this.autonomo());
		avaliacoes.add(avaliacao2);
		avaliacoes.add(avaliacao1);

		assertThat(avaliacoes.iterator().next()).isEqualTo(avaliacao1);
	}
}
