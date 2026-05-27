/**
 * BACK-END / BANCO = Matheus Godoy.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import util.Conexao;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO aluno (nome, sobrenome, nome_mae, nome_pai, cpf, data_nasc, endereco, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSobrenome());
            stmt.setString(3, aluno.getNome_mae());
            stmt.setString(4, aluno.getNome_pai());
            stmt.setString(5, aluno.getCpf());
            stmt.setString(6, aluno.getData_nasc());
            stmt.setString(7, aluno.getEndereco());
            stmt.setString(8, aluno.getCep());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage());
        } finally {
            fecharConexao(conn, stmt, null);
        }
    }

    public void atualizar(Aluno aluno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE aluno SET nome=?, sobrenome=?, nome_mae=?, nome_pai=?, cpf=?, data_nasc=?, endereco=?, cep=? WHERE id=?";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getSobrenome());
            stmt.setString(3, aluno.getNome_mae());
            stmt.setString(4, aluno.getNome_pai());
            stmt.setString(5, aluno.getCpf());
            stmt.setString(6, aluno.getData_nasc());
            stmt.setString(7, aluno.getEndereco());
            stmt.setString(8, aluno.getCep());
            stmt.setInt(9, aluno.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            fecharConexao(conn, stmt, null);
        }
    }

    public void excluir(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM aluno WHERE id = ?";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage());
        } finally {
            fecharConexao(conn, stmt, null);
        }
    }

    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM aluno ORDER BY id DESC";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSobrenome(rs.getString("sobrenome"));
                aluno.setNome_mae(rs.getString("nome_mae"));
                aluno.setNome_pai(rs.getString("nome_pai"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setData_nasc(rs.getString("data_nasc"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setCep(rs.getString("cep"));
                lista.add(aluno);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage());
        } finally {
            fecharConexao(conn, stmt, rs);
        }
        return lista;
    }

    private void fecharConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
