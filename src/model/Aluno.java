/**
 * BACK-END / BANCO = Matheus Godoy.
 */
package model;

public class Aluno {
    private int id;
    private String nome;
    private String sobrenome;
    private String nome_mae;
    private String nome_pai;
    private String cpf;
    private String data_nasc;
    private String endereco;
    private String cep;

    public Aluno() {
    }

    public Aluno(String nome, String sobrenome, String nome_mae, String nome_pai, String cpf, String data_nasc, String endereco, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nome_mae = nome_mae;
        this.nome_pai = nome_pai;
        this.cpf = cpf;
        this.data_nasc = data_nasc;
        this.endereco = endereco;
        this.cep = cep;
    }

    public Aluno(int id, String nome, String sobrenome, String nome_mae, String nome_pai, String cpf, String data_nasc, String endereco, String cep) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nome_mae = nome_mae;
        this.nome_pai = nome_pai;
        this.cpf = cpf;
        this.data_nasc = data_nasc;
        this.endereco = endereco;
        this.cep = cep;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }
    public String getNome_mae() { return nome_mae; }
    public void setNome_mae(String nome_mae) { this.nome_mae = nome_mae; }
    public String getNome_pai() { return nome_pai; }
    public void setNome_pai(String nome_pai) { this.nome_pai = nome_pai; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getData_nasc() { return data_nasc; }
    public void setData_nasc(String data_nasc) { this.data_nasc = data_nasc; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
}
