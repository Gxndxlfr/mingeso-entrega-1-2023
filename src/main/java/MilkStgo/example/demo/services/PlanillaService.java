package MilkStgo.example.demo.services;

import MilkStgo.example.demo.entities.PlanillaEntity;
import MilkStgo.example.demo.entities.ProveedorEntity;
import MilkStgo.example.demo.entities.SubirDataEntity;
import MilkStgo.example.demo.entities.SubirPorcentajeEntity;
import MilkStgo.example.demo.repositories.PlanillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanillaService {


    @Autowired
    ProveedorService proveedorService;

    @Autowired
    SubirPorcentajeService subirPorcentajeService;

    @Autowired
    SubirDataService subirDataService;

    @Autowired
    RegistroQuincenaService registroQuincenaService;

    @Autowired
    PlanillaRepository planillaRepository;


    public List<PlanillaEntity> calcularPagos() {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();

        //recorrer proveedores
        for(ProveedorEntity proveedor:proveedores){
            //Pago de kilo leche según categoria
            int multiplicadorCategoria = calcularPagoCategoria(proveedor.getCategoria());
            System.out.println("multiplicadorCategoria = "+ multiplicadorCategoria);
            //%grasa asociado al proveedor


            int multiplicadorGrasa = obtenerPagoPorcentajeGrasa(proveedor.getCodigo());
            System.out.println("multiplicadorGrasa = "+multiplicadorGrasa);

            //%solidos Totales asociados al proveedor
            int multiplicadorST = obtenerPagoPorcentajeST(proveedor.getCodigo());
            System.out.println("multiplicadorST = "+ multiplicadorST);

            //kilos de leche entregados
            int kilosLeche = calcularCantidadKilosLeche(proveedor.getCodigo());
            System.out.println("kilosLeche: "+kilosLeche);
            //calcular bonificación por frecuencia de entrega
            int multiplicadorFrecuencia = obtenerBonificacionFrecuencia(proveedor.getCodigo());
            System.out.println("MultiplicadorFrecuencia = "+ multiplicadorFrecuencia);

            int pagoLeche = kilosLeche*multiplicadorCategoria;
            int pagoGrasa = kilosLeche*multiplicadorGrasa;
            int pagoST = kilosLeche*multiplicadorST;

            double porcentajeBonificacion = multiplicadorFrecuencia/100.0;
            int bonificacionPago = (int) Math.floor(pagoLeche*porcentajeBonificacion);

            int pagoAcopioLeche = pagoLeche + pagoGrasa + pagoST + bonificacionPago;

            System.out.println("pagoAcopioLeche: "+pagoLeche+"+"+pagoGrasa+"+"+pagoST+"+"+bonificacionPago+"="+pagoAcopioLeche);
            //calcular descuento variación según quincena anterior

            //Descuento variacion leche
            int multiplicadorDescuentoLeche = obtenerDescuentoLeche(proveedor.getCodigo());
            double porcentajeDescuentoLeche = multiplicadorDescuentoLeche/100.0;
            System.out.println("multiplicadorDescuentoLeche: "+multiplicadorDescuentoLeche);
            //Descuento variación Grasa
            int multiplicadorDescuentoGrasa = obtenerDescuentoGrasa(proveedor.getCodigo());
            double porcentajeDescuentoGrasa = multiplicadorDescuentoGrasa/100.0;
            System.out.println("multiplicadorDescuentoGrasa: "+multiplicadorDescuentoGrasa);
            //Descuento variación ST
            int multiplicadorDescuentoSt = obtenerDescuentoSt(proveedor.getCodigo());
            double porcentajeDescuentoSt = multiplicadorDescuentoSt/100.0;
            System.out.println("multiplicadorDescuentoSt: "+multiplicadorDescuentoSt);

            //aplicar descuentos
            int descuento_1 = (int) Math.floor(pagoAcopioLeche*porcentajeDescuentoLeche);
            int descuento_2 = (int) Math.floor(pagoAcopioLeche*porcentajeDescuentoGrasa);
            int descuento_3 = (int) Math.floor(pagoAcopioLeche*porcentajeDescuentoSt);

            System.out.println("descuentos ->"+descuento_1+" | "+descuento_2+" | "+descuento_3);

            int descuento = descuento_1+descuento_2+descuento_3;
            System.out.println(descuento);

            int pagoTotal = pagoAcopioLeche - descuento;
            System.out.println("pagoTotal= "+pagoTotal);

            //retencion
            int retencion = obtenerRetencion(pagoTotal);
            System.out.println("retencion 2 = "+retencion);
            //pagoFinal
            int pagoFinal = pagoTotal-retencion;
            System.out.println("pagoFinal= "+pagoFinal);
            //actualizar valores anterior quincena
            actualizarPorcentajes(proveedor.getCodigo(),kilosLeche);

            //nueva entidad planilla
            String codigo = proveedor.getCodigo();
            System.out.println("codigo= "+codigo);
            String nombre = proveedor.getNombre();
            System.out.println("nombre= "+nombre);


            double promedioKlsLeche = kilosLeche/15.0;
            String quincena = subirDataService.obtenerFechaPorCodigo(codigo);
            System.out.println("quincena= "+quincena);
            int cantDias = contarDias(codigo);
            System.out.println("cantDias 2= "+cantDias);
            double varLeche = calcularVariacionLeche(codigo);
            System.out.println("varLeche 2= "+varLeche);
            double varGrasa = calcularVariacionGrasa(codigo);
            System.out.println("varGrasa 2= "+varGrasa);
            double varSt = calcularVariacionSt(codigo);
            System.out.println("varSt 2= "+varSt);

            int grasaActual = subirPorcentajeService.obtenerGrasaActual(codigo); ;
            int stActual = subirPorcentajeService.obtenerStActual(codigo);
            guardarInfoPago(quincena,codigo,nombre,kilosLeche,cantDias,promedioKlsLeche,varLeche,grasaActual, varGrasa,stActual, varSt,pagoLeche,pagoGrasa,pagoST,bonificacionPago,descuento_1,descuento_2,descuento_3,pagoTotal,retencion,pagoFinal);

        }



        return  planillaRepository.findAll();

    }

    public String guardarInfoPago(String quincena, String codigo, String nombre, int kilosLeche, int cantDias, double promedioKlsLeche, double varLeche, int grasaActual, double varGrasa,int stActual, double varSt, int pagoLeche, int pagoGrasa, int pagoST, int bonificacionPago, int descuento_1, int descuento_2, int descuento_3, int pagoTotal, int retencion, int pagoFinal) {
        PlanillaEntity newPlanilla = new PlanillaEntity();
        newPlanilla.setQuincena(quincena);
        newPlanilla.setCodigo(codigo);
        newPlanilla.setNombre(nombre);
        newPlanilla.setKlsLeche(kilosLeche);
        newPlanilla.setCantDias(cantDias);
        newPlanilla.setPromedioKlsLeche(promedioKlsLeche);
        newPlanilla.setVarLeche(varLeche);
        newPlanilla.setGrasa(grasaActual);
        newPlanilla.setVarGrasa(varGrasa);
        newPlanilla.setSt(stActual);
        newPlanilla.setVarSt(varSt);
        newPlanilla.setPagoLeche(pagoLeche);
        newPlanilla.setPagoGrasa(pagoGrasa);
        newPlanilla.setPagoSt(pagoST);
        newPlanilla.setBonificacionFrecuencia(bonificacionPago);
        newPlanilla.setDescuentoLeche(descuento_1);
        newPlanilla.setDescuentoGrasa(descuento_2);
        newPlanilla.setDescuentoSt(descuento_3);
        newPlanilla.setPagoTotal(pagoTotal);
        newPlanilla.setRetencion(retencion);
        newPlanilla.setPagoFinal(pagoFinal);

        System.out.println(newPlanilla);
        planillaRepository.save(newPlanilla);

        return "nuevo pago calculado";

    }

    public int contarDias(String codigo) {

        ArrayList<SubirDataEntity> acopio = subirDataService.obtenerAcopioPorCodigo(codigo);

        String fechaAux = "";
        int dias = 0;
        for (SubirDataEntity a:acopio){
            if(!a.getFecha().equals(fechaAux)){
                dias = dias+1;
                fechaAux = a.getFecha();
            }
        }System.out.println("días 1= "+dias);
        return dias;
    }

    public String actualizarPorcentajes(String codigo,int kilos) {
        System.out.println("------------------");
        int stActual = subirPorcentajeService.obtenerStActual(codigo);
        System.out.println("st Actual = "+stActual);
        int grasaActual = subirPorcentajeService.obtenerGrasaActual(codigo);
        System.out.println("grasa Actual = "+grasaActual);
        registroQuincenaService.actualizarDatos(codigo,kilos,stActual,grasaActual);
        System.out.println("------------------");

        return "porcentajes Actualizados";
    }

    public int obtenerRetencion(int pagoTotal) {
        int retencion = 0;
        if(pagoTotal > 950000){
            retencion = (int) Math.floor(pagoTotal*0.13);

        }System.out.println("retencion 1= "+retencion);
        return retencion;
    }

    public int obtenerDescuentoSt(String codigo ) {

        //calcular variación
        double variacionPorcentual = calcularVariacionSt(codigo);

        if(variacionPorcentual <= 0 && variacionPorcentual >= -6 ){
            return 0;
        }else if(variacionPorcentual <= -7 && variacionPorcentual >= -12 ){
            return 18;
        }else if(variacionPorcentual <= -13 && variacionPorcentual >= -35){
            return 27;
        }else if(variacionPorcentual <= -36){
            return 45;
        }

        return 0;
    }
    public double calcularVariacionSt(String codigo){
        //obtener Porcentajes actuales
        int stActual = subirPorcentajeService.obtenerStActual(codigo);
        //obtener Porcentajes antiguos
        int stAntigua = registroQuincenaService.obtenerStAntigua(codigo);
        //calcular variación
        double variacionPorcentual = calcularVariacionPorcentual(stAntigua,stActual);
        System.out.println("variacionSt 1= "+variacionPorcentual);
        return variacionPorcentual;
    }

    public int obtenerDescuentoGrasa(String codigo ) {

        //calcular variación
        double variacionPorcentual = calcularVariacionGrasa(codigo);

        if(variacionPorcentual <= 0 && variacionPorcentual >= -15 ){
            return 0;
        }else if(variacionPorcentual <= -16 && variacionPorcentual >= -25 ){
            return 12;
        }else if(variacionPorcentual <= -26 && variacionPorcentual >= -40){
            return 20;
        }else if(variacionPorcentual <= -41){
            return 30;
        }

        return 0;

    }

    private double calcularVariacionGrasa(String codigo){
        //obtener Porcentajes actuales
        int grasaActual = subirPorcentajeService.obtenerGrasaActual(codigo);
        //obtener Porcentajes antiguos
        int grasaAntigua = registroQuincenaService.obtenerGrasaAntigua(codigo);
        //calcular variación
        double variacionPorcentual = calcularVariacionPorcentual(grasaAntigua,grasaActual);

        System.out.println("variacionGrasa 1= "+variacionPorcentual);
        return variacionPorcentual;
    }
    public double calcularVariacionPorcentual(int valor1, int valor2){

        if(valor1 == 0){
            return 0.0;
        }else{

            double valor_2= (double)(valor2);

            return ((valor_2 - (double) valor1)/ (double) valor1)*100.0;
        }
    }
    public int obtenerDescuentoLeche(String codigo) {
        System.out.println("--------------------------------");
        System.out.println("################################");
        //calcular variación
        double variacionPorcentual = calcularVariacionLeche(codigo);

        variacionPorcentual= variacionPorcentual*(-1);


        System.out.println("variacionPorcentual:" + variacionPorcentual);
        if(variacionPorcentual <= 0.0 && variacionPorcentual >= -8.0 ){
            return 0;
        }else if(variacionPorcentual <= -9.0 && variacionPorcentual >= -25.0 ){
            return 7;
        }else if(variacionPorcentual <= -26.0 && variacionPorcentual >= -45.0){
            return 15;
        }else if(variacionPorcentual <= -46.0){
            return 30;
        }
        System.out.println("################################");
        System.out.println("--------------------------------");
        return 0;
    }
    public double calcularVariacionLeche(String codigo){

        //obtener kilos actuales
        int kilosActuales = calcularCantidadKilosLeche(codigo);
        //obtener kilos antiguos
        int kilos_antiguos = registroQuincenaService.getKilosByCodigo(codigo);
        //calcular variación
        double variacionPorcentual = calcularVariacionPorcentual(kilos_antiguos,kilosActuales);
        System.out.println("variacionKilos 1= "+variacionPorcentual);
        return variacionPorcentual;
    }

    public int obtenerBonificacionFrecuencia(String codigo) {
        ArrayList<SubirDataEntity> acopioM = subirDataService.obtenerAcopioPorTurnoAndCodigo("M",codigo);
        ArrayList<SubirDataEntity> acopioT = subirDataService.obtenerAcopioPorTurnoAndCodigo("T",codigo);


        int sizeM = acopioM.size();
        int sizeT = acopioT.size();
        System.out.println(acopioM);
        System.out.println(sizeM);
        System.out.println(acopioT);
        System.out.println(sizeT);

        int sizeTotal = sizeM + sizeT;
        if(sizeTotal > 10 && sizeM > 0 && sizeT > 0){
            return 20;
        }else if(sizeM > 10){
            return 12;
        }else if(sizeT > 10){
            return 8;
        }

        return 0;
    }

    public int calcularCantidadKilosLeche(String codigo) {

        ArrayList<SubirDataEntity> acopio = subirDataService.obtenerAcopioPorCodigo(codigo);
        System.out.println(acopio);
        int cant = 0;
        int kilos;
        for(SubirDataEntity a:acopio){
            kilos = Integer.parseInt(a.getKls_leche());

            cant = cant + kilos;
        }
        return cant;
    }

    public int obtenerPagoPorcentajeST( String codigo) {

        SubirPorcentajeEntity porcentajes = subirPorcentajeService.obtenerPorcentajesPorCodigo(codigo);

        System.out.println("------ PORCENTAJE ST ------");
        System.out.println("proveedor: " + codigo);
        System.out.println("porcentaje de solidos totales: " + porcentajes.getSolidoTotal());

        int porcentajeST = Integer.parseInt(porcentajes.getSolidoTotal());

        if(porcentajeST >= 0 && porcentajeST <= 7){
            return -130;
        }else if(porcentajeST >= 8 && porcentajeST <= 18){
            return -90;
        }else if(porcentajeST >= 19 && porcentajeST <= 35){
            return 95;
        }else if(porcentajeST >= 36){
            return 150;
        }

        return 0;
    }

    public int obtenerPagoPorcentajeGrasa(String codigo) {

        SubirPorcentajeEntity porcentajes = subirPorcentajeService.obtenerPorcentajesPorCodigo(codigo);

        System.out.println("------ PORCENTAJE GRASA ------");
        System.out.println("proveedor: " + codigo);
        System.out.println("porcentaje de grasa: " + porcentajes.getGrasa());

        int porcentajeGrasa = Integer.parseInt(porcentajes.getGrasa());

        if(porcentajeGrasa >= 0 && porcentajeGrasa <= 20){
            return 30;
        }else if(porcentajeGrasa >= 21 && porcentajeGrasa <= 45){
            return 80;
        }else if(porcentajeGrasa >= 46){
            return 120;
        }

        return 0;
    }

    public int calcularPagoCategoria(String categoria) {

        return switch (categoria) {
            case "A" -> 700;
            case "B" -> 550;
            case "C" -> 400;
            case "D" -> 250;
            default -> 0;
        };
    }

    public void eliminarPago(String codigo) {

        planillaRepository.deleteByCodigo(codigo);
    }
}
