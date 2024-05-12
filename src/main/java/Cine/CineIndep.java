package Cine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CineIndep extends JFrame {
    private Map<String, Cliente> mapaClientes = new HashMap<>();
    private List<SalaCine> listaSalas = new ArrayList<>();

    public CineIndep() {
        setTitle("CineIndep");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear salas de cine de ejemplo
        SalaCine sala1 = new SalaCine(100);
        SalaCine sala2 = new SalaCine(150);
        listaSalas.add(sala1);
        listaSalas.add(sala2);

        // Componentes de la interfaz
        JButton btnAgregarCliente = new JButton("Agregar Cliente");
        JButton btnVenderEntrada = new JButton("Vender Entrada");
        JButton btnReversarVenta = new JButton("Reversar Venta");
        JButton btnVerAsientos = new JButton("Ver Asientos");
        JButton btnVerEntradasCliente = new JButton("Ver Entradas Cliente");
        JButton btnIngresarSala = new JButton("Ingresar Sala");

        // Paneles
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(btnAgregarCliente);
        panel.add(btnVenderEntrada);
        panel.add(btnReversarVenta);
        panel.add(btnVerAsientos);
        panel.add(btnVerEntradasCliente);
        panel.add(btnIngresarSala);

        // Agregar componentes al contenedor
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Eventos
        btnAgregarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        btnVenderEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                venderEntrada();
            }
        });

        btnReversarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reversarVenta();
            }
        });

        btnVerAsientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verAsientos();
            }
        });

        btnVerEntradasCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verEntradasCliente();
            }
        });

        btnIngresarSala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarSala();
            }
        });
    }

    // Método para agregar un cliente
    private void agregarCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String rut = JOptionPane.showInputDialog("Ingrese el rut del cliente:");
        String correoElectronico = JOptionPane.showInputDialog("Ingrese el correo electrónico del cliente:");
        Cliente cliente = new Cliente(nombre, rut, correoElectronico);
        mapaClientes.put(rut, cliente);
        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
    }

    // Método para vender una entrada a un cliente
    private void venderEntrada() {
        String rut = JOptionPane.showInputDialog("Ingrese el rut del cliente:");
        Cliente cliente = mapaClientes.get(rut);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        SalaCine sala = listaSalas.get(0); // Se vende la entrada para la primera sala en la lista
        Entrada.TipoEntrada tipoEntrada = (Entrada.TipoEntrada) JOptionPane.showInputDialog(null, "Seleccione el tipo de entrada:", "Tipo de Entrada", JOptionPane.QUESTION_MESSAGE, null, Entrada.TipoEntrada.values(), Entrada.TipoEntrada.values()[0]);
        Entrada entrada = new Entrada(tipoEntrada, sala);
        cliente.agregarEntrada(entrada);
        sala.venderEntrada();
        JOptionPane.showMessageDialog(null, "Entrada vendida correctamente.");
    }

    // Método para revertir la venta de una entrada
    // Método para revertir la venta de una entrada
    private void reversarVenta() {
        String rut = JOptionPane.showInputDialog("Ingrese el rut del cliente:");
        Cliente cliente = mapaClientes.get(rut);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        SalaCine sala = listaSalas.get(0); // Se asume que se revierte la venta de la primera sala en la lista
        List<Entrada> entradas = cliente.getEntradas();
        if (entradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El cliente no tiene entradas.");
            return;
        }
        Entrada.TipoEntrada[] opciones = new Entrada.TipoEntrada[entradas.size()];
        for (int i = 0; i < entradas.size(); i++) {
            opciones[i] = entradas.get(i).getTipo();
        }
        Entrada.TipoEntrada tipoEntrada = (Entrada.TipoEntrada) JOptionPane.showInputDialog(null, "Seleccione el tipo de entrada a revertir:", "Tipo de Entrada", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        for (Entrada entrada : entradas) {
            if (entrada.getTipo() == tipoEntrada) {
                sala.reversarVenta(); // Se decrementa la cantidad de asientos vendidos
                cliente.eliminarEntrada(entrada); // Se elimina la asociación de la entrada con el cliente
                JOptionPane.showMessageDialog(null, "Venta de entrada revertida correctamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró la entrada para revertir.");
    }

    // Método para ver el número de asientos de una sala y la cantidad vendida
    private void verAsientos() {
        SalaCine sala = listaSalas.get(0); // Se muestra información de la primera sala en la lista
        int asientosDisponibles = sala.getNumeroAsientos() - sala.getAsientosVendidos();
        JOptionPane.showMessageDialog(null, "Asientos disponibles: " + asientosDisponibles + "\nAsientos vendidos: " + sala.getAsientosVendidos());
    }

    // Método para ver el número de entradas que tiene un cliente
    private void verEntradasCliente() {
        String rut = JOptionPane.showInputDialog("Ingrese el rut del cliente:");
        Cliente cliente = mapaClientes.get(rut);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        int numEntradas = cliente.contarEntradas();
        JOptionPane.showMessageDialog(null, "El cliente tiene " + numEntradas + " entradas.");
    }

    // Método para permitir el ingreso de un cliente a una sala si tiene entrada
    private void ingresarSala() {
        String rut = JOptionPane.showInputDialog("Ingrese el rut del cliente:");
        Cliente cliente = mapaClientes.get(rut);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }
        SalaCine sala = listaSalas.get(0); // Se asume que se intenta ingresar a la primera sala en la lista
        List<Entrada> entradas = cliente.getEntradas();
        for (Entrada entrada : entradas) {
            if (entrada.getSala() == sala) {
                JOptionPane.showMessageDialog(null, "Cliente ingresado a la sala correctamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "El cliente no tiene una entrada para esta sala.");
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CineIndep().setVisible(true);
            }
        });
    }
}