/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app;

import com.mycompany.facade.AlquilerFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author nquin
 */
public class MainGUI {

    private static final AlquilerFacade facade = new AlquilerFacade();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::crearVentana);
    }

    private static void crearVentana() {
        JFrame frame = new JFrame("Prenda Facil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 550);
        frame.setLocationRelativeTo(null);

        // Panel principal 
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 250, 255));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridLayout(0, 1, 10, 20));
        // Título principal
        JLabel titulo = new JLabel("Alquiler de prendas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(30, 60, 120));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(titulo);

        // Opciones del menu
        agregarBoton(panel, "Registrar Cliente", () -> mostrarFormularioCliente());
        agregarBoton(panel, "Registrar Empleado", () -> mostrarFormularioEmpleado());
        agregarBoton(panel, "Registrar Prenda", () -> mostrarFormularioPrenda());
        agregarBoton(panel, "Registrar Alquiler", () -> mostrarFormularioAlquiler());
        agregarBoton(panel, "Consultar Alquiler", () -> mostrarFormularioConsultaAlquiler());
        agregarBoton(panel, "Registrar Lavandería", () -> mostrarFormularioLavanderia());
        agregarBoton(panel, "Ver Cola Lavandería", () -> mostrarListaLavanderia());
        agregarBoton(panel, "Salir", () -> System.exit(0));
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void agregarBoton(JPanel panel, String texto, Runnable accion) {
        JButton btn = new JButton(texto);
        btn.addActionListener(e -> accion.run());
        panel.add(btn);
    }

    private static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Acción", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarFormularioCliente() {
        // Crear la ventana
        JFrame frame = new JFrame("Registrar Cliente");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 5, 5));

        // Campos de registro
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField direccionField = new JTextField();
        JTextField telefonoField = new JTextField();
        JTextField emailField = new JTextField();
        JButton registrarBtn = new JButton("Registrar");

        // componentes de registtro
        frame.add(new JLabel("ID:"));
        frame.add(idField);
        frame.add(new JLabel("Nombre:"));
        frame.add(nombreField);
        frame.add(new JLabel("Dirección:"));
        frame.add(direccionField);
        frame.add(new JLabel("Teléfono:"));
        frame.add(telefonoField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);
        frame.add(new JLabel(""));
        frame.add(registrarBtn);

        // Accin de "Registrar"
        registrarBtn.addActionListener(e -> {
            String id = idField.getText();
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();
            String telefono = telefonoField.getText();
            String email = emailField.getText();

            boolean ok = facade.registrarCliente(new com.mycompany.model.Cliente(id, nombre, direccion, telefono, email));
            JOptionPane.showMessageDialog(frame,
                    ok ? "Cliente registrado correctamente" : "Error registrando cliente",
                    "Resultado",
                    ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

            if (ok) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarFormularioEmpleado() {
        JFrame frame = new JFrame("Registrar Empleado");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 5, 5));
        //campos de registro
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField direccionField = new JTextField();
        JTextField telefonoField = new JTextField();
        JTextField cargoField = new JTextField();
        JButton registrarBtn = new JButton("Registrar");
         //componentes registro   
        frame.add(new JLabel("ID:"));
        frame.add(idField);
        frame.add(new JLabel("Nombre:"));
        frame.add(nombreField);
        frame.add(new JLabel("Dirección:"));
        frame.add(direccionField);
        frame.add(new JLabel("Teléfono:"));
        frame.add(telefonoField);
        frame.add(new JLabel("Cargo:"));
        frame.add(cargoField);
        frame.add(new JLabel(""));
        frame.add(registrarBtn);

        registrarBtn.addActionListener(e -> {
            String id = idField.getText();
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();
            String telefono = telefonoField.getText();
            String cargo = cargoField.getText();

            boolean ok = facade.registrarEmpleado(
                    new com.mycompany.model.Empleado(id, nombre, direccion, telefono, cargo)
            );

            JOptionPane.showMessageDialog(frame,
                    ok ? "Empleado registrado correctamente" : "Error registrando empleado",
                    "Resultado",
                    ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

            if (ok) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarFormularioPrenda() {
        JFrame frame = new JFrame("Registrar Prenda Individual");
        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(7, 2, 5, 5));
        //campos de registro
        JTextField refField = new JTextField();
        JTextField tipoField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField tallaField = new JTextField();
        JTextField valorField = new JTextField();
        JButton registrarBtn = new JButton("Registrar");
        //componentes de registro
        frame.add(new JLabel("Referencia:"));
        frame.add(refField);
        frame.add(new JLabel("Tipo:"));
        frame.add(tipoField);
        frame.add(new JLabel("Color:"));
        frame.add(colorField);
        frame.add(new JLabel("Marca:"));
        frame.add(marcaField);
        frame.add(new JLabel("Talla:"));
        frame.add(tallaField);
        frame.add(new JLabel("Valor Alquiler:"));
        frame.add(valorField);
        frame.add(new JLabel(""));
        frame.add(registrarBtn);

        registrarBtn.addActionListener(e -> {
            try {
                String ref = refField.getText();
                String tipo = tipoField.getText();
                String color = colorField.getText();
                String marca = marcaField.getText();
                String talla = tallaField.getText();
                double valor = Double.parseDouble(valorField.getText());

                var prenda = new com.mycompany.model.PrendaIndividual(ref, tipo, color, marca, talla, valor);

                boolean ok = facade.registrarPrenda(prenda);

                JOptionPane.showMessageDialog(frame,
                        ok ? "Prenda registrada correctamente" : "Error registrando prenda",
                        "Resultado",
                        ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

                if (ok) {
                    frame.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Valor de alquiler inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarFormularioAlquiler() {
        JFrame frame = new JFrame("Registrar Alquiler");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(0, 1, 5, 5));

        // Campos
        JTextField clienteField = new JTextField();
        JTextField empleadoField = new JTextField();
        JTextField fechaField = new JTextField(LocalDate.now().toString()); // fecha que se quiere alquilar

        // Lista de prendas
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String ref : facade.listarReferenciasPrenda()) {
            model.addElement(ref);
        }
        JList<String> listaPrendas = new JList<>(model);
        listaPrendas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaPrendas);
        JButton registrarBtn = new JButton("Registrar Alquiler");

        // componentes de busqueda y registro
        frame.add(new JLabel("ID Cliente:"));
        frame.add(clienteField);
        frame.add(new JLabel("ID Empleado:"));
        frame.add(empleadoField);
        frame.add(new JLabel("Fecha Alquiler (YYYY-MM-DD):"));
        frame.add(fechaField);
        frame.add(new JLabel("Seleccione Prendas:"));
        frame.add(scrollPane);
        frame.add(new JLabel(""));
        frame.add(registrarBtn);

        // Acción de busqueda
        registrarBtn.addActionListener(e -> {
            String clienteId = clienteField.getText().trim();
            String empleadoId = empleadoField.getText().trim();
            String fechaStr = fechaField.getText().trim();
            List<String> refs = listaPrendas.getSelectedValuesList();

            if (clienteId.isEmpty() || empleadoId.isEmpty() || fechaStr.isEmpty() || refs.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Debe completar todos los campos y seleccionar al menos una prenda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDate fecha = LocalDate.parse(fechaStr);
                long id = facade.registrarServicioAlquiler(clienteId, empleadoId, refs, fecha);
                JOptionPane.showMessageDialog(frame, "Alquiler registrado con ID: " + id, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Fallo", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarFormularioConsultaAlquiler() {
        JFrame frame = new JFrame("Consultar Alquiler");
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel panelInput = new JPanel(new FlowLayout());
        panelInput.add(new JLabel("ID Alquiler:"));
        JTextField idField = new JTextField(15);
        panelInput.add(idField);

        JButton btnConsultar = new JButton("Consultar");
        panelInput.add(btnConsultar);

        JTextArea txtResultado = new JTextArea(8, 30);
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);

        frame.add(panelInput, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        btnConsultar.addActionListener(e -> {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ingrese el ID del alquiler", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                long id = Long.parseLong(idText);
                var servicio = facade.consultarPorId(id);
                if (servicio == null) {
                    JOptionPane.showMessageDialog(frame, "No se encontró el alquiler con ID: " + id, "No encontrado", JOptionPane.INFORMATION_MESSAGE);
                    txtResultado.setText("");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ID: ").append(servicio.getId()).append("\n");
                    sb.append("Fecha solicitud: ").append(servicio.getFechaSolicitud()).append("\n");
                    sb.append("Fecha alquiler: ").append(servicio.getFechaAlquiler()).append("\n");
                    sb.append("Empleado ID: ").append(servicio.getEmpleadoId()).append("\n");
                    sb.append("Cliente ID: ").append(servicio.getClienteId()).append("\n");
                    sb.append("Prendas: ").append(String.join(", ", servicio.getPrendaRefs())).append("\n");
                    txtResultado.setText(sb.toString());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarFormularioLavanderia() {
        JFrame frame = new JFrame("Registrar Prenda a Lavandería");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        JTextField refField = new JTextField();

        JCheckBox prioridadCheck = new JCheckBox("Prioridad");

        JButton registrarBtn = new JButton("Registrar en Lavandería");

        frame.add(new JLabel("Referencia Prenda:"));
        frame.add(refField);
        frame.add(new JLabel("")); 
        frame.add(prioridadCheck);
        frame.add(new JLabel("")); 
        frame.add(registrarBtn);

        registrarBtn.addActionListener(e -> {
            String ref = refField.getText().trim();
            boolean prioridad = prioridadCheck.isSelected();

            if (ref.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Debe ingresar la referencia de la prenda.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean ok = facade.registrarPrendaLavanderia(ref, prioridad);

            JOptionPane.showMessageDialog(frame,
                    ok ? "Prenda registrada en lavandería" : "Error al registrar prenda en lavandería",
                    "Resultado",
                    ok ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE
            );

            if (ok) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private static void mostrarListaLavanderia() {
        JFrame frame = new JFrame("Cola de Lavandería");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // listad de prendas en espera
        List<String> cola = facade.verListaLavanderia();

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String ref : cola) {
            model.addElement(ref);
        }
        JList<String> lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(lista);

        frame.add(scrollPane, BorderLayout.CENTER);
        JButton cerrarBtn = new JButton("Cerrar");
        cerrarBtn.addActionListener(e -> frame.dispose());
        frame.add(cerrarBtn, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
