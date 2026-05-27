package controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.AlunoDAO;
import model.Aluno;
import view.TelaAluno;

public class AlunoController {

    private TelaAluno view;
    private AlunoDAO dao;

    public AlunoController(TelaAluno view) {
        this.view = view;
        this.dao = new AlunoDAO();
    }

    public void salvar() {
        String nome = view.getTxtNome().getText();
        String email = view.getTxtEmail().getText();

        if (nome == null || nome.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!");
            return;
        }

        Aluno aluno = new Aluno(nome, email);

        String idStr = view.getTxtId().getText();
        try {
            if (idStr != null && !idStr.isEmpty()) {
                aluno.setId(Integer.parseInt(idStr));
                dao.atualizar(aluno);
                JOptionPane.showMessageDialog(view, "Aluno atualizado com sucesso!");
            } else {
                dao.salvar(aluno);
                JOptionPane.showMessageDialog(view, "Aluno salvo com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao processar aluno: " + e.getMessage());
        }

        limpar();
        carregarTabela();
    }

    public void excluir() {
        int linhaSelecionada = view.getTabelaClientes().getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(view, "Selecione um aluno na tabela!");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(view, "Deseja realmente excluir este aluno?", "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                int id = (int) view.getTabelaClientes().getValueAt(linhaSelecionada, 0);
                dao.excluir(id);
                JOptionPane.showMessageDialog(view, "Aluno excluído com sucesso!");
                limpar();
                carregarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Erro ao excluir aluno: " + e.getMessage());
            }
        }
    }

    public void limpar() {
        view.getTxtId().setText("");
        view.getTxtNome().setText("");
        view.getTxtEmail().setText("");
        view.getTabelaClientes().clearSelection();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaClientes().getModel();
        modelo.setRowCount(0);

        try {
            List<Aluno> lista = dao.listar();
            for (Aluno aluno : lista) {
                modelo.addRow(new Object[] { aluno.getId(), aluno.getNome(), aluno.getEmail() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao carregar tabela: " + e.getMessage());
        }
    }

    public void preencherFormulario() {
        int linhaSelecionada = view.getTabelaClientes().getSelectedRow();

        if (linhaSelecionada != -1) {
            try {
                Object idObj = view.getTabelaClientes().getValueAt(linhaSelecionada, 0);
                Object nomeObj = view.getTabelaClientes().getValueAt(linhaSelecionada, 1);
                Object emailObj = view.getTabelaClientes().getValueAt(linhaSelecionada, 2);

                view.getTxtId().setText(String.valueOf(idObj));
                view.getTxtNome().setText(String.valueOf(nomeObj));
                view.getTxtEmail().setText(String.valueOf(emailObj));
            } catch (Exception e) {
                System.err.println("Erro ao preencher formulário: " + e.getMessage());
            }
        }
    }
}
