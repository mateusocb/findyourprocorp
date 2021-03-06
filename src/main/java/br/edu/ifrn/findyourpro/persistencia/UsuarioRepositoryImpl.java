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

package br.edu.ifrn.findyourpro.persistencia;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifrn.findyourpro.dominio.QUsuario;
import br.edu.ifrn.findyourpro.dominio.Usuario;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * CrudRepository com definicao de metodo.
 *
 * @author Johann Guerra
 */
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	private final EntityManager entityManager;

	@Inject
	public UsuarioRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Usuario findByLogin(String login) {

		QUsuario qUsuario = QUsuario.usuario;
		JPQLQueryFactory factory = new JPAQueryFactory(this.entityManager);

		// soma todos os lancamentos de credito do dono na conta patrimonio
		Usuario result = factory
				.from(qUsuario)
				.where(qUsuario.login.eq(login))
				.select(qUsuario)
				.fetchOne();

		return result;
	}
}
