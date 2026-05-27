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
    private JLabel lblId;
    private JLabel lblNome;
    private JLabel lblEmail;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtEmail;

    private JButton btnNovo;
    private JButton btnSalvar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;

    private AlunoController controller;

    public TelaAluno() {
        setTitle("Cadastro de Alunos - Secretaria Etec");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        criarComponentes();

        controller = new AlunoController(this);
        configurarEventos();
        controller.carregarTabela();
    }

    private void criarComponentes() {
        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));

        lblId = new JLabel("ID:");
        txtId = new JTextField();
        txtId.setEditable(false);

        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        painelFormulario.add(lblId);
        painelFormulario.add(txtId);
        painelFormulario.add(lblNome);
        painelFormulario.add(txtNome);
        painelFormulario.add(lblEmail);
        painelFormulario.add(txtEmail);

        add(painelFormulario, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "Email" }, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Alunos"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnNovo = new JButton("Novo");
        btnSalvar = new JButton("Salvar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.limpar();
            }
        });

        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.salvar();
            }
        });

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.excluir();
            }
        });

        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.limpar();
            }
        });

        tabelaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.preencherFormulario();
            }
        });
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JTable getTabelaClientes() {
        return tabelaClientes;
    }
}
