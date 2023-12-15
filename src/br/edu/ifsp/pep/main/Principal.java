package br.edu.ifsp.pep.main;

import br.edu.ifsp.pep.dao.ClienteDAO;
import br.edu.ifsp.pep.dao.LocacaoDAO;
import br.edu.ifsp.pep.dao.VeiculoDAO;
import br.edu.ifsp.pep.model.Cliente;
import br.edu.ifsp.pep.model.Locacao;
import br.edu.ifsp.pep.model.Veiculo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        criarClientes();
        criarVeiculos();

        int op = 0;

        do {
            op = menu();

            switch (op) {
                case 1:
                    realizarLocacao();
                    break;
                case 2:
                    exibirVeiculosDisponiveisParaLocacao();
                    break;
                case 3:
                    realizarDevolucao();
                    break;
            }

        } while (op != 0);
    }

    public static void realizarLocacao() {
        ClienteDAO clienteDAO = new ClienteDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        Scanner scan = new Scanner(System.in);

        List<Cliente> clientes = clienteDAO.buscarTodosClientes();
        List<Veiculo> veiculos = null;

        String cpf = "";
        Integer codigo = 0;
        Integer dias = 0;

        System.out.println("----- Cliente\n");

        for (Cliente c : clientes) {
            System.out.println(c);
        }

        veiculos = exibirVeiculosDisponiveisParaLocacao();

        System.out.println("Digite o CPF do cliente que estará fazendo a locação:\n");
        cpf = scan.nextLine();

        System.out.println("Digite o código do veículo a ser alugado.");
        codigo = scan.nextInt();

        Cliente cliente = null;
        Veiculo veiculo = null;

        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
            }
        }

        for (Veiculo v : veiculos) {
            if (v.getCodigo() == codigo) {
                veiculo = v;
            }
        }

        System.out.println("Quantidade de dias da locacao?");
        do {
            dias = scan.nextInt();
        } while (dias <= 0);

        Date dataInicio = new Date();

        Locacao locacao = new Locacao(cliente, veiculo, dataInicio, veiculo.getValorDiaria() * dias, dias, null, cliente, veiculo);

        LocacaoDAO locacaoDAO = new LocacaoDAO();

        try {
            locacaoDAO.inserir(locacao);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        cliente.getLocacaoList().add(locacao);

        clienteDAO.alterar(cliente);

        veiculo.setIsLocado(true);

        veiculoDAO.alterar(veiculo);
    }

    public static List<Veiculo> exibirVeiculosDisponiveisParaLocacao() {
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        List<Veiculo> veiculos = veiculoDAO.buscarVeiculosDisponiveis();

        System.out.println("----- Veiculos Disponiveis\n");

        for (Veiculo v : veiculos) {
            System.out.println(v);
        }

        return veiculos;
    }

    public static void realizarDevolucao() {
        ClienteDAO clienteDAO = new ClienteDAO();
        LocacaoDAO locacaoDAO = new LocacaoDAO();
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        Scanner scan = new Scanner(System.in);

        List<Cliente> clientes = clienteDAO.buscarTodosClientes();
        List<Veiculo> veiculos = null;

        String cpf = "";
        Integer numero = 0;

        System.out.println("----- Cliente\n");

        for (Cliente c : clientes) {
            System.out.println(c);
        }

        System.out.println("Digite o CPF do cliente que estará fazendo a devolucação:\n");
        cpf = scan.nextLine();

        Cliente cliente = null;
        Veiculo veiculo = null;

        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
            }
        }

        System.out.println("Locações dele:");

        int contador = 0;

        for (Locacao locacao : cliente.getLocacaoList()) {
            System.out.println("Numero: " + contador + " - Locacao: " + locacao);
            contador++;
        }

        System.out.println("Escolha qual é a sua locação a ser devolvida digitando o numero dela.:");

        numero = scan.nextInt();

        Locacao devolucao = cliente.getLocacaoList().get(numero);

        Date inicio = devolucao.getDataInicio();

        Integer diasAteOFim = devolucao.getDias();

        Date dataAtual = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicio);
        calendar.add(Calendar.DAY_OF_MONTH, diasAteOFim);

        if (dataAtual.after(calendar.getTime())) {
            System.out.println("Já passou da data estipulada - Multa de R$ 50.");
            System.out.println("Total a ser pago: R$ " + devolucao.getTotal() + 50);
        } else {
            System.out.println("Total a ser pago: R$ " + devolucao.getTotal());
        }
        Veiculo veiculoDaDevolucao = devolucao.getVeiculo();

        veiculoDaDevolucao.setIsLocado(false);
        veiculoDAO.alterar(veiculoDaDevolucao);

        devolucao.setDataFim(new Date());
        locacaoDAO.alterar(devolucao);
    }

    public static int menu() {
        int op = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("----------------- Menu:\n"
                + "1. Realizar Locação de veiculos.\n"
                + "2. Exibir veiculos disponíveis para locação.\n"
                + "3. Realizar Devolução.\n"
                + "0. Finaliza.\n");
        op = scan.nextInt();
        return op;
    }

    public static void criarClientes() {
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-01");
        Cliente cliente2 = new Cliente("Maria Oliveira", "987.654.321-00");
        Cliente cliente3 = new Cliente("Carlos Santos", "111.222.333-44");

        ClienteDAO clienteDAO = new ClienteDAO();

        try {
            clienteDAO.inserir(cliente1);
            clienteDAO.inserir(cliente2);
            clienteDAO.inserir(cliente3);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void criarVeiculos() {
        double moto = 50.0;
        double carro = 100.0;
        double van = 150.0;

        Veiculo veiculo1 = new Veiculo("Carro", "ABC123", "São Paulo", "Carro Esportivo", 2022, carro, false);
        Veiculo veiculo2 = new Veiculo("Moto", "ABC12", "São Paul", "Moto Veloz", 2021, moto, false);
        Veiculo veiculo3 = new Veiculo("Van", "DEF789", "Belo Horizonte", "Van de Passeio", 2020, van, false);
        Veiculo veiculo4 = new Veiculo("Carro", "JKL321", "Porto Alegre", "Carro Sedan", 2023, carro, false);
        Veiculo veiculo5 = new Veiculo("Moto", "MNO654", "Curitiba", "Moto Custom", 2019, moto, false);
        Veiculo veiculo6 = new Veiculo("Van", "HDONK4", "Belo Horizonte", "Van de Passeio", 2020, van, false);

        VeiculoDAO veiculoDAO = new VeiculoDAO();

        try {
            veiculoDAO.inserir(veiculo1);
            veiculoDAO.inserir(veiculo2);
            veiculoDAO.inserir(veiculo3);
            veiculoDAO.inserir(veiculo4);
            veiculoDAO.inserir(veiculo5);
            veiculoDAO.inserir(veiculo6);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
