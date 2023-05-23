package MilkStgo.example.demo;

import MilkStgo.example.demo.repositories.RegistroQuincenaRepository;
import MilkStgo.example.demo.services.ProveedorService;
import MilkStgo.example.demo.services.RegistroQuincenaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RegistroQuincenaTest {

    @Autowired
    RegistroQuincenaService registroQuincenaService;

    @Autowired
    ProveedorService proveedorService;
    @Autowired
    private RegistroQuincenaRepository registroQuincenaRepository;

    @Test
    void testGuardarRegistroQuincena(){
        String response = registroQuincenaService.guardarRegistroQuincena("0007","30","31","100");
        assertEquals("nueva quincena", response);

        registroQuincenaService.eliminarQuincena("0007");
    }

    @Test
    void testGetKilosByCodigo(){
        proveedorService.guardarProveedor("8","name 8","B");
        registroQuincenaService.guardarRegistroQuincena("8","30","31","100");

        int response = registroQuincenaService.getKilosByCodigo("8");
        assertEquals(100,response);

        proveedorService.eliminarProveedor("8");

        registroQuincenaService.eliminarQuincena("8");

    }
    @Test
    void testObtenerGrasaAntigua(){
        proveedorService.guardarProveedor("9","name 9","B");
        registroQuincenaService.guardarRegistroQuincena("9","30","31","100");

        int response = registroQuincenaService.obtenerGrasaAntigua("9");
        assertEquals(30,response);

        proveedorService.eliminarProveedor("9");
        registroQuincenaService.eliminarQuincena("9");
    }
    @Test
    void testObtenerStAntigua(){
        proveedorService.guardarProveedor("10","name 10","B");
        registroQuincenaService.guardarRegistroQuincena("10","30","31","100");

        int response = registroQuincenaService.obtenerStAntigua("10");
        assertEquals(31,response);

        proveedorService.eliminarProveedor("10");
        registroQuincenaService.eliminarQuincena("10");
    }

    @Test
    void testActualizarDatos(){
        proveedorService.guardarProveedor("11","name 11","B");
        registroQuincenaService.guardarRegistroQuincena("11","30","31","100");

        String response = registroQuincenaService.actualizarDatos("11",101,32,31);

        assertEquals("registro quincena actualizado",response);

        proveedorService.eliminarProveedor("11");
        registroQuincenaService.eliminarQuincena("11");

    }

    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}
