
package pi.dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.entidades.Documento;

public class DocumentoDAO implements DAO<Documento>{

    @Override
    public void inserir(Documento entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();
        String sql = "INSERT INTO DOCUMENTO(TITULO, ACESSO, "
                + "LOCAL, DATA, HORÁRIO, ASSUNTO, CLASSIFICAÇÃO, ENCAMINHAMENTO) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);;
            comando.setString(1, entidade.getTitulo());
            comando.setInt(2, entidade.getAcesso());
            comando.setString(3, entidade.getLocal());
            comando.setString(4, entidade.getData());
            comando.setString(5, entidade.getHorario());
            comando.setString(6, entidade.getAssunto());
            comando.setInt(7, entidade.getClassificacao());
            comando.setString(8, entidade.getEncaminhamento());
            comando.execute();
            conexao.close();
        } catch (SQLDataException ex){
            throw new DadosException("Erro ao inserir", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void alterar(Documento entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();
        String sql = "UPDATE DOCUMENTO SET TITULO=?, ACESSO=?,"
                + "LOCAL=?, DATA=?, HORÁRIO=?, ASSUNTO=?, CLASSIFICAÇÃO=?, ENCAMINHAMENTO=? "
                + "WHERE ID_DOCUMENTO=?";
         try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, entidade.getTitulo());
            comando.setInt(2, entidade.getAcesso());
            comando.setString(3, entidade.getLocal());
            comando.setString(4, entidade.getData());
            comando.setString(5, entidade.getHorario());
            comando.setString(6, entidade.getAssunto());
            comando.setInt(7, entidade.getClassificacao());
            comando.setString(8, entidade.getEncaminhamento());
            comando.setInt(9, entidade.getId_documento());
            comando.execute();
            conexao.close();
        } catch (SQLDataException ex){
            throw new DadosException("Erro ao alterar", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void excluir(Documento entidade) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();
        String sql = "DELETE FROM DOCUMENTO WHERE ID_DOCUMENTO=?";
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, entidade.getId_documento());
            comando.execute();
            conexao.close();
        } catch (SQLException ex) {
            throw new DadosException("Erro ao excluir", ex);
        }
    }

    @Override
    public Documento consultar(int id) throws DadosException {
        Connection conexao = ConexaoBD.getConexao();
        String sql = "SELECT * FROM DOCUMENTO WHERE ID_DOCUMENTO=?";
        Documento documento = new Documento();
        try {
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                documento.setId_documento(resultado.getInt(1));
                documento.setTitulo(resultado.getString(2));
                documento.setAcesso(resultado.getInt(3));
                documento.setLocal(resultado.getString(4));
                documento.setData(resultado.getString(5));
                documento.setHorario(resultado.getString(6));
                documento.setAssunto(resultado.getString(7));
                documento.setClassificacao(resultado.getInt(8));
                documento.setEncaminhamento(resultado.getString(9));
            }

            conexao.close();
            return documento;
        } catch (SQLException ex) {
            throw new DadosException("Erro ao consultar", ex);
        }
    }

    @Override
    public List<Documento> listar() throws DadosException {
        List<Documento> lista = new ArrayList<Documento>();
        Connection conexao = ConexaoBD.getConexao();
        String sql = "SELECT * FROM DOCUMENTO";
        try {
            Statement comando = conexao.createStatement();
            ResultSet resultado = comando.executeQuery(sql);

            while (resultado.next()) {
                Documento documento = new Documento();
                documento.setId_documento(resultado.getInt(1));
                documento.setTitulo(resultado.getString(2));
                documento.setAcesso(resultado.getInt(3));
                documento.setLocal(resultado.getString(4));
                documento.setData(resultado.getString(5));
                documento.setHorario(resultado.getString(6));
                documento.setAssunto(resultado.getString(7));
                documento.setClassificacao(resultado.getInt(8));
                documento.setEncaminhamento(resultado.getString(9));
                lista.add(documento);
            }

            conexao.close();
            return lista;
        } catch (SQLException ex) {
            throw new DadosException("Erro ao listar", ex);
        }
    }    
}
