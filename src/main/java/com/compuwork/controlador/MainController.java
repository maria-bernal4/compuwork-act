package com.compuwork.controlador;

import com.compuwork.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<Departamento> departamentos = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    
    @FXML private ComboBox<Departamento> comboDepartamentos;
    @FXML private ListView<Empleado> listaEmpleados;
    @FXML private TextArea areaReporte;
    @FXML private TextField txtNombre, txtApellido, txtDni;
    @FXML private DatePicker fechaContratacion;
    @FXML private TextField txtSalarioBase;
    @FXML private RadioButton rbPermanente, rbTemporal;
    @FXML private VBox panelPermanente, panelTemporal;
    @FXML private TextField txtBonificacionAntiguedad, txtBonificacionProyecto;
    @FXML private DatePicker fechaFinContrato;

    @FXML
    public void initialize() {
        // Configurar los RadioButtons
        ToggleGroup grupo = new ToggleGroup();
        rbPermanente.setToggleGroup(grupo);
        rbTemporal.setToggleGroup(grupo);
        
        // Listener para mostrar/ocultar paneles según tipo de empleado
        rbPermanente.selectedProperty().addListener((obs, oldVal, newVal) -> {
            panelPermanente.setVisible(newVal);
            panelTemporal.setVisible(!newVal);
        });

        // Inicializar algunos datos de ejemplo
        inicializarDatosEjemplo();
        
        // Configurar los ComboBox y ListView
        actualizarListasDepartamentosYEmpleados();
    }

    private void inicializarDatosEjemplo() {
        // Crear departamentos de ejemplo
        Departamento rrhh = new Departamento(1, "Recursos Humanos");
        Departamento ti = new Departamento(2, "Tecnología de la Información");
        departamentos.add(rrhh);
        departamentos.add(ti);

        // Crear algunos empleados de ejemplo
        EmpleadoPermanente emp1 = new EmpleadoPermanente(1, "Juan", "Pérez", "12345678", 
            LocalDate.of(2020, 1, 1), 50000, 5000);
        EmpleadoTemporal emp2 = new EmpleadoTemporal(2, "María", "García", "87654321",
            LocalDate.of(2023, 6, 1), 45000, LocalDate.of(2024, 6, 1), 3000);

        rrhh.agregarEmpleado(emp1);
        ti.agregarEmpleado(emp2);
        empleados.add(emp1);
        empleados.add(emp2);
    }

    private void actualizarListasDepartamentosYEmpleados() {
        comboDepartamentos.setItems(FXCollections.observableArrayList(departamentos));
        listaEmpleados.setItems(FXCollections.observableArrayList(empleados));
    }

    @FXML
    private void agregarEmpleado() {
        try {
            // Validar campos obligatorios
            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || 
                txtDni.getText().isEmpty() || fechaContratacion.getValue() == null || 
                txtSalarioBase.getText().isEmpty() || comboDepartamentos.getValue() == null) {
                mostrarError("Por favor, complete todos los campos obligatorios.");
                return;
            }

            // Crear nuevo empleado
            int id = empleados.size() + 1;
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String dni = txtDni.getText();
            LocalDate fechaContr = fechaContratacion.getValue();
            double salarioBase = Double.parseDouble(txtSalarioBase.getText());
            
            Empleado nuevoEmpleado;
            if (rbPermanente.isSelected()) {
                double bonificacion = Double.parseDouble(txtBonificacionAntiguedad.getText());
                nuevoEmpleado = new EmpleadoPermanente(id, nombre, apellido, dni, 
                    fechaContr, salarioBase, bonificacion);
            } else {
                double bonificacion = Double.parseDouble(txtBonificacionProyecto.getText());
                LocalDate fechaFin = fechaFinContrato.getValue();
                nuevoEmpleado = new EmpleadoTemporal(id, nombre, apellido, dni, 
                    fechaContr, salarioBase, fechaFin, bonificacion);
            }

            // Agregar al departamento seleccionado
            Departamento departamento = comboDepartamentos.getValue();
            departamento.agregarEmpleado(nuevoEmpleado);
            empleados.add(nuevoEmpleado);
            
            // Actualizar la interfaz
            actualizarListasDepartamentosYEmpleados();
            limpiarFormulario();
            
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese valores numéricos válidos para los campos de salario y bonificación.");
        } catch (Exception e) {
            mostrarError("Error al agregar empleado: " + e.getMessage());
        }
    }

    @FXML
    private void generarReporte() {
        if (listaEmpleados.getSelectionModel().getSelectedItem() != null) {
            Empleado empleado = listaEmpleados.getSelectionModel().getSelectedItem();
            areaReporte.setText(empleado.generarReporteDesempeno());
        } else if (comboDepartamentos.getValue() != null) {
            Departamento departamento = comboDepartamentos.getValue();
            areaReporte.setText(departamento.generarReporteDepartamento());
        } else {
            mostrarError("Por favor, seleccione un empleado o un departamento para generar el reporte.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        txtDni.clear();
        fechaContratacion.setValue(null);
        txtSalarioBase.clear();
        txtBonificacionAntiguedad.clear();
        txtBonificacionProyecto.clear();
        fechaFinContrato.setValue(null);
    }
}
