import java.math.BigDecimal;
import java.util.Date;

public class Funcionario extends Pessoa{
    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, String dtNascimento, BigDecimal salario, String funcao) {
        super(nome, dtNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return nome+"\n" +
                "Data de Nascimento = " + dtNascimento +
                "\nSalário = " + salario +
                "\nFunção = " + funcao + "\n";
    }
}
