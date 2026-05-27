/**
 * VIEW / TELAS = Lucas de Lima.
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.AlunoController;

public class TelaAluno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtId, txtNome, txtSobrenome, txtMae, txtPai, txtCpf, txtDataNasc, txtEndereco, txtCep;
    private JButton btnNovo, btnSalvar, btnExcluir, btnLimpar;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;
    private AlunoController controller;

    public TelaAluno() {
        setTitle("Secretaria Etec - Cadastro de Alunos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        criarComponentes();
        controller = new AlunoController(this);
        configurarEventos();
        controller.carregarTabela();
    }

    private void criarComponentes() {
        JPanel painelFormulario = new JPanel(new GridLayout(9, 2, 5, 5));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));

        painelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField(); txtId.setEditable(false); painelFormulario.add(txtId);

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField(); painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Sobrenome:"));
        txtSobrenome = new JTextField(); painelFormulario.add(txtSobrenome);

        painelFormulario.add(new JLabel("Nome da Mãe:"));
        txtMae = new JTextField(); painelFormulario.add(txtMae);

        painelFormulario.add(new JLabel("Nome do Pai (Obrigatório):"));
        txtPai = new JTextField(); painelFormulario.add(txtPai);

        painelFormulario.add(new JLabel("CPF:"));
        txtCpf = new JTextField(); painelFormulario.add(txtCpf);

        painelFormulario.add(new JLabel("Data Nasc (DD/MM/AAAA):"));
        txtDataNasc = new JTextField(); painelFormulario.add(txtDataNasc);

        painelFormulario.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField(); painelFormulario.add(txtEndereco);

        painelFormulario.add(new JLabel("CEP:"));
        txtCep = new JTextField(); painelFormulario.add(txtCep);

        add(painelFormulario, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "Sobrenome", "Mãe", "Pai", "CPF", "Nasc", "Endereço", "CEP" }, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        tabelaClientes = new JTable(modeloTabela);
        add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnNovo = new JButton("Novo"); btnSalvar = new JButton("Salvar"); btnExcluir = new JButton("Excluir"); btnLimpar = new JButton("Limpar");
        painelBotoes.add(btnNovo); painelBotoes.add(btnSalvar); painelBotoes.add(btnExcluir); painelBotoes.add(btnLimpar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnNovo.addActionListener(e -> controller.limpar());
        btnSalvar.addActionListener(e -> controller.salvar());
        btnExcluir.addActionListener(e -> controller.excluir());
        btnLimpar.addActionListener(e -> controller.limpar());
        tabelaClientes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { controller.preencherFormulario(); }
        });
    }

    // Getters
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNome() { return txtNome; }
    public JTextField getTxtSobrenome() { return txtSobrenome; }
    public JTextField getTxtMae() { return txtMae; }
    public JTextField getTxtPai() { return txtPai; }
    public JTextField getTxtCpf() { return txtCpf; }
    public JTextField getTxtDataNasc() { return txtDataNasc; }
    public JTextField getTxtEndereco() { return txtEndereco; }
    public JTextField getTxtCep() { return txtCep; }
    public JTable getTabelaClientes() { return tabelaClientes; }
}
