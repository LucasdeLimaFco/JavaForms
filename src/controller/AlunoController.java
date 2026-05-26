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

        if (nome.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!");
            return;
        }

        Aluno aluno = new Aluno(nome, email);

        String idStr = view.getTxtId().getText();
        if (idStr != null && !idStr.isEmpty()) {
            aluno.setId(Integer.parseInt(idStr));
            dao.atualizar(aluno);
            JOptionPane.showMessageDialog(view, "Aluno atualizado com sucesso!");
        } else {
            dao.salvar(aluno);
            JOptionPane.showMessageDialog(view, "Aluno salvo com sucesso!");
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
            int id = (int) view.getTabelaClientes().getValueAt(linhaSelecionada, 0);
            dao.excluir(id);
            JOptionPane.showMessageDialog(view, "Aluno excluído com sucesso!");
            limpar();
            carregarTabela();
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

        List<Aluno> lista = dao.listar();

        for (Aluno aluno : lista) {
            modelo.addRow(new Object[] { aluno.getId(), aluno.getNome(), aluno.getEmail() });
        }
    }

    public void preencherFormulario() {
        int linhaSelecionada = view.getTabelaClientes().getSelectedRow();

        if (linhaSelecionada != -1) {
            int id = (int) view.getTabelaClientes().getValueAt(linhaSelecionada, 0);
            String nome = (String) view.getTabelaClientes().getValueAt(linhaSelecionada, 1);
            String email = (String) view.getTabelaClientes().getValueAt(linhaSelecionada, 2);

            view.getTxtId().setText(String.valueOf(id));
            view.getTxtNome().setText(nome);
            view.getTxtEmail().setText(email);
        }
    }
}
