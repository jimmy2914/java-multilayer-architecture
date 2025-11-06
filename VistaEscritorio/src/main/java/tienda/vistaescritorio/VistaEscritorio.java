/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.vistaescritorio;

/**
 *
 * @author kevin
 */
import tienda.fachada.IFachada;
import tienda.fachada.ImplFachada;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.table.DefaultTableModel;
import tienda.controlador.FachadaControlador;
import tienda.sistemaclientes.ClienteDuplicadoException;
import tienda.gestordocumentos.FormatoNoSoportadoException;
import tienda.controlador.ProductoControlador;
import tienda.dto.ProductoDTO;
import java.util.List;

public class VistaEscritorio extends JFrame {
    private final ProductoControlador productoControlador;
    private final FachadaControlador fachadaControlador; 
    private JTable tablaProductos;
    private DefaultTableModel modeloProductos;
    private JTable tablaClientes;
    private DefaultTableModel modeloClientes;
    
    private JTextArea Formatos;
    private JTextArea areaContenido;
    private JEditorPane visualizadorDocumento;
    
    public VistaEscritorio() {
        productoControlador = new ProductoControlador();
        
        // --- Se inicializa la fachada y su controlador ---
        IFachada fachada = new ImplFachada();
        fachadaControlador = new FachadaControlador(fachada);
        
        setTitle("Tienda - Gestión de Productos y Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Productos", crearPanelProductos());
        tabbedPane.add("Clientes", crearPanelClientes());
        tabbedPane.add("Documentos", crearPanelDocumentos());

        add(tabbedPane);
    }
    
    private JPanel crearPanelProductos() {
        JPanel panel = new JPanel(new BorderLayout());
        modeloProductos = new DefaultTableModel(new String[]{"ID","Nombre", "Descripción", "Precio", "Stock"}, 0);
        tablaProductos = new JTable(modeloProductos);
        actualizarTablaProductos();
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(this::agregarProducto);
        
        JButton btnEliminar = new JButton("Eliminar Producto Seleccionado");
        btnEliminar.addActionListener(this::eliminarProducto);
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        
        panel.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        return panel;
    }

    // --- CAMBIO 2: Se implementa completamente el panel de clientes ---
    private JPanel crearPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        modeloClientes = new DefaultTableModel(new String[]{"ID","Nombre", "Teléfono", "Email"}, 0);
        tablaClientes = new JTable(modeloClientes);
        actualizarTablaClientes();

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.addActionListener(this::agregarCliente);

        JButton btnEliminar = new JButton("Eliminar Cliente Seleccionado");
        btnEliminar.addActionListener(this::eliminarCliente);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        panel.add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        return panel;
    }
    private JPanel crearPanelDocumentos() {
        // Panel principal con un layout dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.4); // Proporción del espacio

        // --- Panel de arriba: Controles para crear el documento ---
        JPanel panelControles = new JPanel(new BorderLayout(10, 10));
        panelControles.setBorder(BorderFactory.createTitledBorder("Crear Documento"));

        // Formulario de entrada
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Formato:"), gbc);

        gbc.gridx = 1;
        Formatos = new JTextArea(1,20);
        formPanel.add(Formatos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        formPanel.add(new JLabel("Contenido:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        areaContenido = new JTextArea(5, 20);
        formPanel.add(new JScrollPane(areaContenido), gbc);
        
        panelControles.add(formPanel, BorderLayout.CENTER);

        JButton btnGenerar = new JButton("Generar y Visualizar Documento");
        btnGenerar.addActionListener(this::generarDoc);
        panelControles.add(btnGenerar, BorderLayout.SOUTH);

        // --- Panel de abajo: Visualizador del documento generado ---
        JPanel panelVisualizador = new JPanel(new BorderLayout());
        panelVisualizador.setBorder(BorderFactory.createTitledBorder("Visualizador"));
        
        visualizadorDocumento = new JEditorPane();
        visualizadorDocumento.setEditable(false);
        panelVisualizador.add(new JScrollPane(visualizadorDocumento), BorderLayout.CENTER);

        splitPane.setTopComponent(panelControles);
        splitPane.setBottomComponent(panelVisualizador);

        JPanel panelFinal = new JPanel(new BorderLayout());
        panelFinal.add(splitPane, BorderLayout.CENTER);
        return panelFinal;
    }
    private void agregarProducto(ActionEvent e) {
        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField stockField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Descripción:"));
        panel.add(descripcionField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Stock:"));
        panel.add(stockField);
        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                productoControlador.agregarProducto(nombreField.getText(), descripcionField.getText(), Double.parseDouble(precioField.getText()), Integer.parseInt(stockField.getText()));
                actualizarTablaProductos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void eliminarProducto(ActionEvent e) {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la tabla para eliminar.", "Ningún producto seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloProductos.getValueAt(filaSeleccionada, 0);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el producto '" + id + "'?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                productoControlador.eliminarProducto(id);
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTablaProductos(); // Refrescar la tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    private void agregarCliente(ActionEvent e) {
        JTextField nombreField = new JTextField();
        JTextField telefonoField = new JTextField();
        JTextField emailField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                fachadaControlador.registrarCliente(nombreField.getText(), telefonoField.getText(), emailField.getText());
                actualizarTablaClientes(); // Solo se actualiza si todo sale bien
            
            } catch (ClienteDuplicadoException ex) {
                // Catch para el error específico de duplicado
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Cliente Duplicado", JOptionPane.ERROR_MESSAGE);
            
            } catch (RuntimeException ex) {
                // --- CAMBIO: Se añade un catch para cualquier otro error inesperado ---
                // Esto mostrará una ventana emergente para problemas de conexión, etc.
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado.\nConsulte la consola para más detalles.", "Error General", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Imprime los detalles técnicos en la consola para el desarrollador
            }
        }
    }
    private void eliminarCliente(ActionEvent e) {
        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente de la tabla para eliminar.", "Ningún cliente seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // El ID está en la primera columna (índice 0)
        int idCliente = Integer.parseInt(modeloClientes.getValueAt(filaSeleccionada, 0).toString());
        String nombreCliente = (String) modeloClientes.getValueAt(filaSeleccionada, 1);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar al cliente '" + nombreCliente + "' (ID: " + idCliente + ")?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                fachadaControlador.eliminarCliente(idCliente);
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTablaClientes(); // Refrescar la tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al eliminar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    private void generarDoc(ActionEvent e) {
        String formato = Formatos.getText();
        String contenido = areaContenido.getText();

        if (contenido.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El contenido no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        System.setOut(newOut); // Redirigir la salida estándar

        try {
            // Llamar al método que imprime en la consola
            fachadaControlador.generarDoc(formato, contenido);

            // Forzar que cualquier buffer se escriba en el stream
            System.out.flush();

            // Restaurar la salida estándar INMEDIATAMENTE
            System.setOut(originalOut);

            // Obtener el texto capturado
            String documentoGenerado = baos.toString();

            // Visualizar el resultado
            if ("HTML".equalsIgnoreCase(formato)) {
                visualizadorDocumento.setContentType("text/html");
            } else {
                visualizadorDocumento.setContentType("text/plain");
            }
            visualizadorDocumento.setText(documentoGenerado);
            visualizadorDocumento.setCaretPosition(0); // Mover el scroll al inicio
            
            JOptionPane.showMessageDialog(this, "Documento generado y visualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (FormatoNoSoportadoException ex) {
            System.setOut(originalOut); // Asegurarse de restaurar la salida en caso de error
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error: Formato No Soportado", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void actualizarTablaProductos() {
        modeloProductos.setRowCount(0);
        List<ProductoDTO> productos = productoControlador.obtenerProductos();
        for (ProductoDTO p : productos) {
            modeloProductos.addRow(new Object[]{p.getId(),p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock()});
        }
    }

    // --- CAMBIO 4: Se añade el método para refrescar la tabla de clientes ---
    private void actualizarTablaClientes() {
        modeloClientes.setRowCount(0);
        List<String> clientes = fachadaControlador.obtenerClientes();
        for (String c : clientes) {
            // Se parsea el string para obtener los datos por separado
            String[] parts = c.split(", ");
            String id = parts[0].split(": ")[1];
            String nombre = parts[1].split(": ")[1];
            String tel = parts[2].split(": ")[1];
            String email = parts[3].split(": ")[1];
            modeloClientes.addRow(new Object[]{id,nombre, tel, email});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaEscritorio().setVisible(true));
    }
}