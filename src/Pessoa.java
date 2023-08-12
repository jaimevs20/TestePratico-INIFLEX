import java.util.Date;

public class Pessoa implements Comparable<Pessoa>{
    String nome;
    String dtNascimento;

    public Pessoa(String nome, String dtNascimento) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public int compareTo(Pessoa o) {
        return this.nome.compareTo(o.nome);
    }
}
