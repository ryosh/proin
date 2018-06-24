
package pi.negocios;

import java.util.List;

public interface IAprovadorBO<T> {
    
    void validar(T entidade) throws NegocioException;
    
    void inserir(T entidade) throws NegocioException;

    void alterar(T entidade) throws NegocioException;

    void excluir(T entidade) throws NegocioException;

    T consultar(int id) throws NegocioException;

    List<T> listar() throws NegocioException;
}
