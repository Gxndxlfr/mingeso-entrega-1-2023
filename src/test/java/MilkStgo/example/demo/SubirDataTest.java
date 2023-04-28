package MilkStgo.example.demo;

import MilkStgo.example.demo.entities.SubirDataEntity;
import MilkStgo.example.demo.repositories.PlanillaRepository;
import MilkStgo.example.demo.services.SubirDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
public class SubirDataTest {

    @Autowired
    SubirDataService subirDataService;
    @Autowired
    private PlanillaRepository planillaRepository;

    @Test
    void testObtenerFechaPorCodigo(){
        subirDataService.guardarDataDB("6/11/2023","M","1006","10");
        String fecha = subirDataService.obtenerFechaPorCodigo("1006");
        assertEquals("6/11/2023",fecha);

        subirDataService.eliminarData("1006");

    }
    @Test
    void testObtenerFechaPorCodigoNull(){
        String fecha = subirDataService.obtenerFechaPorCodigo("1");
        assertEquals(null,fecha);

    }

    @Test
    void testLeerCsv(){
        String response = subirDataService.leerCsv("Acopio.csv");
        assertEquals("Archivo leido exitosamente",response);
    }

    @Test
    void testGuardarDataDB(){
        String response = subirDataService.guardarDataDB("01/11/2023", "M","1003","10");
        assertEquals("nuevo entrega registrada",response);

        subirDataService.eliminarData("1003");
    }

    @Test
    void testObtenerAcopioPorCodigo(){
        ArrayList<SubirDataEntity> acopioAux = new ArrayList<>();
        SubirDataEntity data = new SubirDataEntity();
        data.setID(93);
        data.setTurno("M");
        data.setFecha("01/11/2023");
        data.setProveedor("1004");
        data.setKls_leche("10");
        acopioAux.add(data);

        subirDataService.guardarDataDB("01/11/2023","M","1004","10");
        ArrayList<SubirDataEntity> acopio = subirDataService.obtenerAcopioPorCodigo("1004");
        assertArrayEquals(new ArrayList[]{acopioAux}, new ArrayList[]{acopio});

        subirDataService.eliminarData("1004");
    }

    @Test
    void testObtenerAcopioPorTurnoAndCodigo(){

        subirDataService.guardarDataDB("04/11/2023","M","1005","10");
        ArrayList<SubirDataEntity> acopio = subirDataService.obtenerAcopioPorTurnoAndCodigo("M","1005");
        String turno = acopio.get(0).getTurno();
        assertEquals("M",turno);

        subirDataService.eliminarData("1005");
    }







    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}

