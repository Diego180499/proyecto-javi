
import org.junit.jupiter.api.Test;
import programacion.finanzas.javi.exception.AppException;
import programacion.finanzas.javi.service.CalculateService;
import static org.junit.jupiter.api.Assertions.assertThrows;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author german.estacuy
 */
public class CalculateServiceTest {

    @Test
    public void calculateTransactions_whenFileNotExists() {
        CalculateService calculateService = new CalculateService();
        assertThrows(AppException.class, () -> calculateService.calculateTransactions("C:\\Users\\HP\\Proyectos Programacion\\Java\\Proyecto Finanzas Javi\\docs\\Movements.xls"));

    }
    
   @Test
    public void calculateTransactions_whenFileExists() {
        CalculateService calculateService = new CalculateService();
        calculateService.calculateTransactions("C:\\Users\\HP\\Proyectos Programacion\\Java\\Proyecto Finanzas Javi\\docs\\Movements.xls");
    }

}
