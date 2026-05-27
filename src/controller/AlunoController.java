/**
 * INTEGRAÇÃO / MAIN / TESTES = Pietro Bruneli.
 */
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
        String sobrenome = view.getTxtSobrenome().getText();
        String mae = view.getTxtMae().getText();
        String pai = view.getTxtPai().getText();
        String cpf = view.getTxtCpf().getText();
        String nasc = view.getTxtDataNasc().getText();
        String endereco = view.getTxtEndereco().getText();
        String cep = view.getTxtCep().getText();

        // Validação: Tudo obrigatório, menos o Pai
        if (nome.trim().isEmpty() || sobrenome.trim().isEmpty() || mae.trim().isEmpty() || 
            cpf.trim().isEmpty() || nasc.trim().isEmpty() || endereco.trim().isEmpty() || cep.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos obrigatórios (Nome, Sobrenome, Mãe, CPF, Data, Endereço e CEP)!");
            return;
        }

        Aluno aluno = new Aluno(nome, sobrenome, mae, pai, cpf, nasc, endereco, cep);

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
        view.getTxtSobrenome().setText("");
        view.getTxtMae().setText("");
        view.getTxtPai().setText("");
        view.getTxtCpf().setText("");
        view.getTxtDataNasc().setText("");
        view.getTxtEndereco().setText("");
        view.getTxtCep().setText("");
        view.getTabelaClientes().clearSelection();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaClientes().getModel();
        modelo.setRowCount(0);

        try {
            List<Aluno> lista = dao.listar();
            for (Aluno aluno : lista) {
                modelo.addRow(new Object[] { 
                    aluno.getId(), aluno.getNome(), aluno.getSobrenome(), 
                    aluno.getNome_mae(), aluno.getNome_pai(), aluno.getCpf(), 
                    aluno.getData_nasc(), aluno.getEndereco(), aluno.getCep() 
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao carregar tabela: " + e.getMessage());
        }
    }

    public void preencherFormulario() {
        int linhaSelecionada = view.getTabelaClientes().getSelectedRow();

        if (linhaSelecionada != -1) {
            try {
                view.getTxtId().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 0)));
                view.getTxtNome().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 1)));
                view.getTxtSobrenome().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 2)));
                view.getTxtMae().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 3)));
                view.getTxtPai().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 4)));
                view.getTxtCpf().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 5)));
                view.getTxtDataNasc().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 6)));
                view.getTxtEndereco().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 7)));
                view.getTxtCep().setText(String.valueOf(view.getTabelaClientes().getValueAt(linhaSelecionada, 8)));
            } catch (Exception e) {
                System.err.println("Erro ao preencher formulário: " + e.getMessage());
            }
        }
    }
}
