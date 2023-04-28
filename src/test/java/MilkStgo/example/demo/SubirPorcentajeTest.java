package MilkStgo.example.demo;

import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import MilkStgo.example.demo.services.SubirPorcentajeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SubirPorcentajeTest {

    @Autowired
    SubirPorcentajeService subirPorcentajeService;

    @Test
    void testLeerCsv(){
        String response = subirPorcentajeService.leerCsv("porcentajes.csv");
        assertEquals("Archivo leido exitosamente 2",response);
    }

    @Test
    void testGuardarDataDB(){
        String codigo = "0002";
        String grasa = "12";
        String st= "22";

        String response = subirPorcentajeService.guardarDataDB(codigo,grasa,st);

        assertEquals("Nuevo porcentaje",response);

        subirPorcentajeService.eliminarPorcentaje("0002");
    }

    @Test
    void testObtenerPorcentajesPorCodigo() {
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setGrasa("10");
        sP.setSolidoTotal("12");
        sP.setCodigo("0003");

        subirPorcentajeService.guardarData(sP);

        SubirPorcentajeEntity porcentaje = subirPorcentajeService.obtenerPorcentajesPorCodigo("0003");

        assertEquals("0003", porcentaje.getCodigo());

        subirPorcentajeService.eliminarPorcentaje("0003");
    }

    @Test
    void testObtenerGrasaActual(){

        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setGrasa("10");
        sP.setSolidoTotal("12");
        sP.setCodigo("0004");

        subirPorcentajeService.guardarData(sP);

        int grasa = subirPorcentajeService.obtenerGrasaActual("0004");
        assertEquals(10,grasa);

        subirPorcentajeService.eliminarPorcentaje("0004");
    }

    @Test
    void testObtenerStActual(){
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setGrasa("10");
        sP.setSolidoTotal("12");
        sP.setCodigo("0005");

        subirPorcentajeService.guardarData(sP);
        int st = subirPorcentajeService.obtenerStActual("0005");
        assertEquals(12,st);

        subirPorcentajeService.eliminarPorcentaje("0005");
    }
    /*@Test
    void testSueldoCategoria1(){
        int sueldo = administracionService.sueldoCategoria("A");
        assertEquals(20000, sueldo, 0.0);
    }*/
}
