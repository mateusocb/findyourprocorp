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

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Autonomo entity.
 *
 * @author Mateus Oliveira
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, of = "cpf")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Autonomo extends PrestadorDeServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, nullable = false, name = "cpf")
	private String cpf;

	@Builder
	public Autonomo(Long id, Usuario usuario, Set<Servico> servicos, String cpf) {
		super(id, usuario, servicos);
		this.cpf = cpf;
	}
}
