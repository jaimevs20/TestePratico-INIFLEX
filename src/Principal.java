import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Principal {
    public static void main(String[] args) throws ParseException {
        /*
            NÃO TRABALHEI COM A FORMATAÇÃO DAS DATAS E NEM DOS SALÁRIOS DA FORMA DESEJADA
         */

        List<Funcionario> funcionariosList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        funcionariosList.add(new Funcionario("MARIA", formatter.format(new Date("10/18/2000")), new BigDecimal("2009.44"), "Operador"));
        funcionariosList.add(new Funcionario("JOAO", formatter.format(new Date("05/12/1990")), new BigDecimal("2284.38"), "Operador"));
        funcionariosList.add(new Funcionario("CAIO", formatter.format(new Date("05/02/1961")), new BigDecimal("9836.14"), "Coordenador"));
        funcionariosList.add(new Funcionario("MIGUEL", formatter.format(new Date("10/14/1988")), new BigDecimal("19119.88"), "Diretor"));
        funcionariosList.add(new Funcionario("ALICE", formatter.format(new Date("01/05/1995")), new BigDecimal("2234.68"), "Recepcionista"));
        funcionariosList.add(new Funcionario("HEITOR", formatter.format(new Date("11/19/1999")), new BigDecimal("1582.72"), "Operador"));
        funcionariosList.add(new Funcionario("ARTHUR",formatter.format(new Date("03/31/1993")), new BigDecimal("4071.84"), "Contador"));
        funcionariosList.add(new Funcionario("LAURA", formatter.format(new Date("07/08/1994")), new BigDecimal("3017.45"), "Gerente"));
        funcionariosList.add(new Funcionario("HELOISA", formatter.format(new Date("05/24/2003")), new BigDecimal("1606.85"), "Eletricista"));
        funcionariosList.add(new Funcionario("HELENA", formatter.format(new Date("09/02/1996")), new BigDecimal("2799.93"), "Gerente"));


        System.out.println("-----LISTA COMPLETA: -----\n"+listaCompleta(funcionariosList)+"\n");

        removerJoao(funcionariosList);

        System.out.println("-----LISTA COM AUMENTO SALARIAL: -----\n"+ aumentoSalarial(funcionariosList));

        System.out.println("-----LISTA AGRUPADA PELAS FUNÇÕES: -----\n"+ listaEmMap(funcionariosList));

        System.out.println("-----ANIVERSARIANTES DE OUTUBRO E DEZEMBRO: -----\n"+ aniversarios(funcionariosList));

        System.out.println("-----FUNCIONÁRIO MAIS VELHO: -----\n"+ funcionarioMaisVelho(funcionariosList));

        System.out.println("-----FUNCIONÁRIOS LISTADOS EM ORDEM ALFABÉTICA: -----\n"+funcionariosOrdenadosAlfabeticamente(funcionariosList));

        System.out.println("-----TOTAL DE SALÁRIOS: -----\n"+totalSalarios(funcionariosList));

        System.out.println("-----SALÁRIOS MÍNIMOS: -----\n"+salariosMinimos(funcionariosList));

    }

    public static List<Funcionario> listaCompleta(List<Funcionario> funcionarios) throws ParseException {
        return funcionarios;
    }

    public static List<Funcionario> removerJoao(List<Funcionario> funcionarios){
        funcionarios.remove(funcionarios.get(1));
        return funcionarios;
    }

    public static List<Funcionario> aumentoSalarial(List<Funcionario> funcionarios){
        BigDecimal salarioAtual;
        BigDecimal aumento = new BigDecimal(10);
        NumberFormat z = NumberFormat.getCurrencyInstance();

        for (Funcionario funcionario: funcionarios){
            salarioAtual = funcionario.getSalario();

            funcionario.setSalario(
                    salarioAtual.add(
                    salarioAtual.multiply(aumento).divide(new BigDecimal(100),2, RoundingMode.HALF_UP)
                    )
            );
            z.format(funcionario.getSalario());
        }
        return funcionarios;
    }

    public static Map<String, List<Funcionario>> listaEmMap(List<Funcionario> funcionarios){
        Map<String, List<Funcionario>> mapList = new HashMap<>();

        for (Funcionario funcionario: funcionarios){
            String funcao = funcionario.getFuncao();
            List<Funcionario> funcoesList = mapList.get(funcao);

            if (funcoesList == null){
                funcoesList = new ArrayList<>();
                funcoesList.add(funcionario);

                mapList.put(funcao,funcoesList);
            }
            funcoesList.add(funcionario);
        }

        return mapList;
    }

    public static List<Funcionario> aniversarios(List<Funcionario> funcionarios){
        List<Funcionario> funcionariosAniversariantes = new ArrayList<>();

        for (Funcionario funcionario: funcionarios){
            if(funcionario.getDtNascimento().contains("/"+"10"+"/") ||
                    funcionario.getDtNascimento().contains("/"+"12"+"/")){

                funcionariosAniversariantes.add(funcionario);
            }
        }
        return funcionariosAniversariantes;
    }

    public static String funcionarioMaisVelho(List<Funcionario> funcionarios){
        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        int menorAno = year;
        int idade = 0;
        Funcionario maisVelho = null;

        for (Funcionario funcionario: funcionarios){
            String[] anoNascimento = funcionario.getDtNascimento().split("/", 4);

            if (Integer.parseInt(anoNascimento[2]) <= menorAno){
                menorAno = Integer.parseInt(anoNascimento[2]);
                maisVelho = funcionario;
                idade = year - menorAno;
            }
        }
        return maisVelho.getNome() + " idade: "+ idade;
    }

    public static List<Funcionario> funcionariosOrdenadosAlfabeticamente(List<Funcionario> funcionarios){
        List list = new ArrayList();

        for (Funcionario funcionario: funcionarios){
            list.add(funcionario);
        }

        Collections.sort(list);

        return list;
    }
    
    public static BigDecimal totalSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = new BigDecimal(0);
        for (Funcionario funcionario : funcionarios) {
            total = total.add(funcionario.getSalario());
        }
        return total;
    }

    public static Map<String, BigDecimal> salariosMinimos(List<Funcionario> funcionarios){
        Map<String, BigDecimal> mapList = new HashMap<>();
        BigDecimal salarioMinimo;

        for (Funcionario funcionario: funcionarios){
            salarioMinimo = funcionario.getSalario().divide(new BigDecimal("1212.00"),2, RoundingMode.HALF_UP);

            mapList.put(funcionario.getNome(), salarioMinimo);

        }

        return mapList;
    }

}
