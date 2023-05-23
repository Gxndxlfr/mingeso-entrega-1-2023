package MilkStgo.example.demo;

import MilkStgo.example.demo.entities.PlanillaEntity;
import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import MilkStgo.example.demo.repositories.ProveedorRepository;
import MilkStgo.example.demo.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class PlanillaTest {

    @Autowired
    PlanillaService planillaService;

    @Autowired
    SubirPorcentajeService subirPorcentajeService;

    @Autowired
    ProveedorService proveedorService;

    @Autowired
    SubirDataService subirDataService;

    @Autowired
    RegistroQuincenaService registroQuincenaService;
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    void testCalcularPagoCategoriaA(){
        int pagoCategoria = planillaService.calcularPagoCategoria("A");
        assertEquals(700, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaB(){
        int pagoCategoria = planillaService.calcularPagoCategoria("B");
        assertEquals(550, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaC(){
        int pagoCategoria = planillaService.calcularPagoCategoria("C");
        assertEquals(400, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaD(){
        int pagoCategoria = planillaService.calcularPagoCategoria("D");
        assertEquals(250, pagoCategoria);
    }
    @Test
    void testCalcularPagoCategoriaE(){
        int pagoCategoria = planillaService.calcularPagoCategoria("E");
        assertEquals(0, pagoCategoria);
    }

    @Test
    void testObtenerPagoPorcentajeGrasa30(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("10");
        sP.setCodigo("0001");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0001","prov 0001","B");
        proveedorService.guardarProveedor("0001","prov 0001","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0001");

        //comparar
        assertEquals(30,response);

        subirPorcentajeService.eliminarPorcentaje("0001");
        proveedorService.eliminarProveedor("0001");

    }

    @Test
    void testObtenerPagoPorcentajeGrasa80(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("25");
        sP.setCodigo("0002");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0002","prov 0002","B");
        proveedorService.guardarProveedor("0002","prov 0002","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0002");

        //comparar
        assertEquals(80,response);

        subirPorcentajeService.eliminarPorcentaje("0002");
        proveedorService.eliminarProveedor("0002");

    }
    @Test
    void testObtenerPagoPorcentajeGrasa120(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("60");
        sP.setCodigo("0003");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0003","prov 0003","B");
        proveedorService.guardarProveedor("0003","prov 0003","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0003");

        //comparar
        assertEquals(120,response);


        subirPorcentajeService.eliminarPorcentaje("0003");
        proveedorService.eliminarProveedor("0003");

    }
    @Test
    void testObtenerPagoPorcentajeGrasa0(){

        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setGrasa("-60");
        sP.setCodigo("0004");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0004","prov 0004","B");
        proveedorService.guardarProveedor("0004","prov 0004","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeGrasa("0004");

        //comparar
        assertEquals(0,response);

        subirPorcentajeService.eliminarPorcentaje("0004");
        proveedorService.eliminarProveedor("0004");

    }

    @Test
    void testObtenerPagoPorcentajeST_130(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("5");
        sP.setCodigo("0005");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0005","prov 0005","B");
        proveedorService.guardarProveedor("0005","prov 0005","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0005");

        //comparar
        assertEquals(-130,response);

        subirPorcentajeService.eliminarPorcentaje("0005");
        proveedorService.eliminarProveedor("0005");
    }

    @Test
    void testObtenerPagoPorcentajeST_90(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("15");
        sP.setCodigo("0006");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0006","prov 0006","B");
        proveedorService.guardarProveedor("0006","prov 0006","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0006");

        //comparar
        assertEquals(-90,response);


        subirPorcentajeService.eliminarPorcentaje("0006");
        proveedorService.eliminarProveedor("0006");
    }

    @Test
    void testObtenerPagoPorcentajeST_95(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("30");
        sP.setCodigo("0007");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0007","prov 0007","B");
        proveedorService.guardarProveedor("0007","prov 0007","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0007");

        //comparar
        assertEquals(95,response);


        subirPorcentajeService.eliminarPorcentaje("0007");
        proveedorService.eliminarProveedor("0007");
    }

    @Test
    void testObtenerPagoPorcentajeST_150(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("60");
        sP.setCodigo("0008");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0008","prov 0008","B");
        proveedorService.guardarProveedor("0008","prov 0008","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0008");

        //comparar
        assertEquals(150,response);


        subirPorcentajeService.eliminarPorcentaje("0008");
        proveedorService.eliminarProveedor("0008");
    }

    @Test
    void testObtenerPagoPorcentajeST_0(){
        //ingresas un subir porcentaje entity
        SubirPorcentajeEntity sP = new SubirPorcentajeEntity();
        sP.setID(1);
        sP.setSolidoTotal("-10");
        sP.setCodigo("0009");
        subirPorcentajeService.guardarData(sP);

        //ingresar proveedor
        ProveedorEntity prov = new ProveedorEntity(1L,"0009","prov 0009","B");
        proveedorService.guardarProveedor("0009","prov 0009","B");

        //asociar codigos
        int response = planillaService.obtenerPagoPorcentajeST("0009");

        //comparar
        assertEquals(0,response);


        subirPorcentajeService.eliminarPorcentaje("0009");
        proveedorService.eliminarProveedor("0009");
    }

    @Test
    void testCalcularCantidadKilosLeche(){

        subirDataService.guardarDataDB("01/04/2023","M","0010","5");
        subirDataService.guardarDataDB("01/04/2023","T","0010","10");

        int response = planillaService.calcularCantidadKilosLeche("0010");

        assertEquals(15,response);

        subirDataService.eliminarData("0010");
    }

    @Test
    void testObtenerBonificacionFrecuenciaM(){
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");
        subirDataService.guardarDataDB("04/11/2023","M","1006","10");

        int response = planillaService.obtenerBonificacionFrecuencia("1006");

        assertEquals(12,response);

        subirDataService.eliminarData("1006");
    }

    @Test
    void testObtenerBonificacionFrecuenciaT(){
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");
        subirDataService.guardarDataDB("04/11/2023","T","1007","10");

        int response = planillaService.obtenerBonificacionFrecuencia("1007");

        assertEquals(8,response);

        subirDataService.eliminarData("1007");
    }

    @Test
    void testObtenerBonificacionFrecuencia(){
        subirDataService.guardarDataDB("04/11/2023","T","1008","10");
        subirDataService.guardarDataDB("04/11/2023","T","1008","10");
        subirDataService.guardarDataDB("04/11/2023","T","1008","10");
        subirDataService.guardarDataDB("04/11/2023","T","1008","10");
        subirDataService.guardarDataDB("04/11/2023","T","1008","10");
        subirDataService.guardarDataDB("04/11/2023","M","1008","10");
        subirDataService.guardarDataDB("04/11/2023","M","1008","10");
        subirDataService.guardarDataDB("04/11/2023","M","1008","10");
        subirDataService.guardarDataDB("04/11/2023","M","1008","10");
        subirDataService.guardarDataDB("04/11/2023","M","1008","10");
        subirDataService.guardarDataDB("04/11/2023","M","1008","10");

        int response = planillaService.obtenerBonificacionFrecuencia("1008");

        assertEquals(20,response);

        subirDataService.eliminarData("1008");
    }

    @Test
    void testObtenerBonificacionFrecuencia_0(){

        int response = planillaService.obtenerBonificacionFrecuencia("1009");

        assertEquals(0,response);
    }

    @Test
    void testObtenerDescuentoLeche_0(){

        //cargar acopio
        subirDataService.guardarDataDB("04/11/2023","T","1010","50");
        subirDataService.guardarDataDB("04/11/2023","T","1010","50");
        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1010","10","10","95");


        int response = planillaService.obtenerDescuentoLeche("1010");
        assertEquals(0,response);

        subirDataService.eliminarData("1010");
    }

    @Test
    void testObtenerDescuentoLeche_7(){

        //cargar acopio
        subirDataService.guardarDataDB("04/11/2023","T","1011","50");
        subirDataService.guardarDataDB("04/11/2023","T","1011","50");
        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1011","10","10","85");



        int response = planillaService.obtenerDescuentoLeche("1011");
        assertEquals(7,response);

        subirDataService.eliminarData("1011");
        registroQuincenaService.eliminarQuincena("1011");
    }

    @Test
    void testObtenerDescuentoLeche_15(){

        //cargar acopio
        subirDataService.guardarDataDB("04/11/2023","T","1012","50");
        subirDataService.guardarDataDB("04/11/2023","T","1012","50");
        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1012","10","10","70");


        int response = planillaService.obtenerDescuentoLeche("1012");
        assertEquals(15,response);

        subirDataService.eliminarData("1012");
        registroQuincenaService.eliminarQuincena("1012");
    }
   @Test
    void testObtenerDescuentoLeche_30(){

        //cargar acopio
        subirDataService.guardarDataDB("04/11/2023","T","1013","50");
        subirDataService.guardarDataDB("04/11/2023","T","1013","50");
        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1013","10","10","50");


        int response = planillaService.obtenerDescuentoLeche("1013");
        assertEquals(30,response);

       subirDataService.eliminarData("1013");
       registroQuincenaService.eliminarQuincena("1013");
    }

    @Test
    void testObtenerDescuentoLeche_00(){
//cargar acopio
        subirDataService.guardarDataDB("04/11/2023","T","1014","50");
        subirDataService.guardarDataDB("04/11/2023","T","1014","50");
        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1014","10","10","200");
        int response = planillaService.obtenerDescuentoLeche("1014");
        assertEquals(0,response);

        subirDataService.eliminarData("1014");
        registroQuincenaService.eliminarQuincena("1014");

    }

    @Test
    void testCalcularVariacionPorcentual(){

        double response = planillaService.calcularVariacionPorcentual(0,10);

        assertEquals(0.0,response,0.0);

    }

    @Test
    void testObtenerDescuentoGrasa_0() {

        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1015", "90","10");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1015","100","10","200");
        int response = planillaService.obtenerDescuentoGrasa("1015");
        assertEquals(0,response);

        subirPorcentajeService.eliminarPorcentaje("1015");
        registroQuincenaService.eliminarQuincena("1015");
    }

    @Test
    void testObtenerDescuentoGrasa_12(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1016", "80","10");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1016","100","10","200");
        int response = planillaService.obtenerDescuentoGrasa("1016");
        assertEquals(12,response);

        subirPorcentajeService.eliminarPorcentaje("1016");
        registroQuincenaService.eliminarQuincena("1016");
    }

    @Test
    void testObtenerDescuentoGrasa_20(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1017", "70","10");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1017","100","10","200");
        int response = planillaService.obtenerDescuentoGrasa("1017");
        assertEquals(20,response);

        subirPorcentajeService.eliminarPorcentaje("1017");
        registroQuincenaService.eliminarQuincena("1017");
    }

    @Test
    void testObtenerDescuentoGrasa_30(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1018", "50","10");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1018","100","10","200");
        int response = planillaService.obtenerDescuentoGrasa("1018");
        assertEquals(30,response);

        subirPorcentajeService.eliminarPorcentaje("1018");
        registroQuincenaService.eliminarQuincena("1018");
    }

    @Test
    void testObtenerDescuentoGrasa_00(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1019", "110","10");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1019","100","10","200");
        int response = planillaService.obtenerDescuentoGrasa("1019");
        assertEquals(0,response);

        subirPorcentajeService.eliminarPorcentaje("1019");
        registroQuincenaService.eliminarQuincena("1019");
    }

    @Test
    void testObtenerDescuentoSt_0(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1020", "10","98");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1020","10","100","10");
        int response = planillaService.obtenerDescuentoSt("1020");
        assertEquals(0,response);

        subirPorcentajeService.eliminarPorcentaje("1020");
        registroQuincenaService.eliminarQuincena("1020");
    }

    @Test
    void testObtenerDescuentoSt_18(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1021", "10","90");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1021","10","100","10");
        int response = planillaService.obtenerDescuentoSt("1021");
        assertEquals(18,response);

        subirPorcentajeService.eliminarPorcentaje("1021");
        registroQuincenaService.eliminarQuincena("1021");
    }

    @Test
    void testObtenerDescuentoSt_27(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1022", "10","80");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1022","10","100","10");
        int response = planillaService.obtenerDescuentoSt("1022");
        assertEquals(27,response);

        subirPorcentajeService.eliminarPorcentaje("1022");
        registroQuincenaService.eliminarQuincena("1022");
    }

    @Test
    void testObtenerDescuentoSt_45(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1023", "10","60");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1023","10","100","10");
        int response = planillaService.obtenerDescuentoSt("1023");
        assertEquals(45,response);

        subirPorcentajeService.eliminarPorcentaje("1023");
        registroQuincenaService.eliminarQuincena("1023");
    }

    @Test
    void testObtenerDescuentoSt_00(){
        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1024", "10","110");
        subirPorcentajeService.guardarData(p);

        //cargar quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1024","10","100","10");
        int response = planillaService.obtenerDescuentoSt("1024");
        assertEquals(0,response);

        subirPorcentajeService.eliminarPorcentaje("1024");
        registroQuincenaService.eliminarQuincena("1024");
    }

    @Test
    void testObtenerRetencion_sin(){
        int pagoTotal = 1000000;
        int response = planillaService.obtenerRetencion(pagoTotal);

        int expected = (int) Math.floor(pagoTotal*0.13);
        assertEquals(expected,response);
    }

    @Test
    void testObtenerRetencion_con(){
        int pagoTotal = 900000;
        int response = planillaService.obtenerRetencion(pagoTotal);

        assertEquals(0,response);
    }

    @Test
    void testActualizarPorcentajes(){

        SubirPorcentajeEntity p = new SubirPorcentajeEntity(1, "1025", "10","10");
        subirPorcentajeService.guardarData(p);

        registroQuincenaService.guardarRegistroQuincena("1025","20","20","20");

        String response = planillaService.actualizarPorcentajes("1025", 10);

        assertEquals("porcentajes Actualizados",response);

        subirPorcentajeService.eliminarPorcentaje("1025");
        registroQuincenaService.eliminarQuincena("1025");
    }

    @Test
    void testContarDias(){


        subirDataService.guardarDataDB("04/11/2023","T","1026","10");
        subirDataService.guardarDataDB("05/11/2023","T","1026","10");
        subirDataService.guardarDataDB("06/11/2023","T","1026","10");
        subirDataService.guardarDataDB("07/11/2023","T","1026","10");
        subirDataService.guardarDataDB("08/11/2023","T","1026","10");

        int response = planillaService.contarDias("1026");
        assertEquals(5,response);
    }

    @Test
    void testGuardarInfoPago(){

        String response = planillaService.guardarInfoPago("08/11/2023","1111","prov 1111",10,10,10.0,10.0,10,10.0,10,10.0,100,100,100,100,50,50,50,150,0,150);
        assertEquals("nuevo pago calculado",response);

        planillaService.eliminarPago("1111");
    }

    @Test
    void testCalcularPagos(){

        //-----agregar proveedores

        proveedorService.guardarProveedor("1212","prov 1212", "A");
        proveedorService.guardarProveedor("1313","prov 1313", "B");
        proveedorService.guardarProveedor("1414", "prov 1414", "C");

        //-----Cargar Acopio

        //acopio 1
        subirDataService.guardarDataDB("01/02/03","M","1212","10");
        subirDataService.guardarDataDB("02/02/03","M","1212","10");
        subirDataService.guardarDataDB("03/02/03","M","1212","10");
        subirDataService.guardarDataDB("04/02/03","M","1212","10");
        subirDataService.guardarDataDB("05/02/03","M","1212","10");
        subirDataService.guardarDataDB("06/02/03","M","1212","10");
        subirDataService.guardarDataDB("07/02/03","M","1212","10");
        subirDataService.guardarDataDB("08/02/03","M","1212","10");
        subirDataService.guardarDataDB("09/02/03","M","1212","10");
        subirDataService.guardarDataDB("10/02/03","M","1212","10");
        subirDataService.guardarDataDB("11/02/03","M","1212","10");

        //acopio 2
        subirDataService.guardarDataDB("01/02/03","T","1313","10");
        subirDataService.guardarDataDB("02/02/03","T","1313","10");
        subirDataService.guardarDataDB("03/02/03","T","1313","10");
        subirDataService.guardarDataDB("04/02/03","T","1313","10");
        subirDataService.guardarDataDB("05/02/03","T","1313","10");
        subirDataService.guardarDataDB("06/02/03","T","1313","10");
        subirDataService.guardarDataDB("07/02/03","T","1313","10");
        subirDataService.guardarDataDB("08/02/03","T","1313","10");
        subirDataService.guardarDataDB("09/02/03","T","1313","10");
        subirDataService.guardarDataDB("10/02/03","T","1313","10");
        subirDataService.guardarDataDB("11/02/03","T","1313","10");

        //acopio 3
        subirDataService.guardarDataDB("01/02/03","T","1414","10");
        subirDataService.guardarDataDB("02/02/03","T","1414","10");
        subirDataService.guardarDataDB("03/02/03","T","1414","10");
        subirDataService.guardarDataDB("04/02/03","T","1414","10");
        subirDataService.guardarDataDB("05/02/03","T","1414","10");
        subirDataService.guardarDataDB("05/02/03","M","1414","10");
        subirDataService.guardarDataDB("07/02/03","M","1414","10");
        subirDataService.guardarDataDB("08/02/03","M","1414","10");
        subirDataService.guardarDataDB("09/02/03","M","1414","10");
        subirDataService.guardarDataDB("10/02/03","M","1414","10");
        subirDataService.guardarDataDB("11/02/03","M","1414","10");


        //-----Cargar Porcentajes
        SubirPorcentajeEntity p1 = new SubirPorcentajeEntity(1,"1212","10","5");
        SubirPorcentajeEntity p2 = new SubirPorcentajeEntity(2,"1313","25","10");
        SubirPorcentajeEntity p3 = new SubirPorcentajeEntity(3,"1414","50","30");
        subirPorcentajeService.guardarData(p1);
        subirPorcentajeService.guardarData(p2);
        subirPorcentajeService.guardarData(p3);

        //-----Cargar Quincena anterior
        registroQuincenaService.guardarRegistroQuincena("1212","0","0","0");
        registroQuincenaService.guardarRegistroQuincena("1313","0","0","0");
        registroQuincenaService.guardarRegistroQuincena("1414","0","0","0");

        //expected planilla
        List<PlanillaEntity> planilla = new ArrayList<>();

        PlanillaEntity pl_1 = new PlanillaEntity(2, "11/02/03", "1212", "prov 1212",
                                                   110, 11, 10.0,
                                                   0.0, 10, 0.0,5,0.0,
                                                77000, 3300, -14300,9240,
                                                0 , 0,0,75240,
                                                        0,75240);
        planilla.add(pl_1);

        PlanillaEntity pl_2 = new PlanillaEntity(3, "11/02/03","1313","prov 1313",
                                                110, 11, 10.0, 0.0,
                                                25, 0.0, 10, 0.0, 60500,
                                            8800, -9900, 4840,0,
                                            0, 0, 64240, 0,
                                            64240);
        planilla.add(pl_2);

        PlanillaEntity pl_3 = new PlanillaEntity(4,"11/02/03","1414", "prov 1414",
                                            110, 10, 11.0,
                                                0.0, 50, 0.0, 30,0.0,
                                            44000, 13200, 10450, 8800,
                                            0, 0, 0, 76450, 0,
                                            76450);
        planilla.add(pl_3);

        //Llamar al método
        List<PlanillaEntity> response = planillaService.calcularPagos();

        assertArrayEquals(new List[]{planilla}, new List[]{response});

        proveedorService.eliminarProveedor("1212");
        proveedorService.eliminarProveedor("1313");
        proveedorService.eliminarProveedor("1414");

        subirDataService.eliminarData("1212");
        subirDataService.eliminarData("1313");
        subirDataService.eliminarData("1414");

        subirPorcentajeService.eliminarPorcentaje("1212");
        subirPorcentajeService.eliminarPorcentaje("1313");
        subirPorcentajeService.eliminarPorcentaje("1414");

        registroQuincenaService.eliminarQuincena("1212");
        registroQuincenaService.eliminarQuincena("1313");
        registroQuincenaService.eliminarQuincena("1414");
    }

}
