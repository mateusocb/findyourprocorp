package br.edu.ifrn.findyourpro.dominio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*import lombok.NonNull;*/
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="login")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Usuario implements Comparable<Usuario> {

    private Localizacao endereco;
    private String telefone;
    private String login;
    private String nome;
    private String senha;

    @Override
    public int compareTo(Usuario o) {
        return this.login.compareTo(o.login);
    }
}