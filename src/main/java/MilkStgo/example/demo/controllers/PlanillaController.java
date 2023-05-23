package MilkStgo.example.demo.controllers;

import MilkStgo.example.demo.entities.PlanillaEntity;
import MilkStgo.example.demo.services.PlanillaService;
import MilkStgo.example.demo.services.RegistroQuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class PlanillaController {

    @Autowired
    private PlanillaService planillaService;

    @Autowired
    private RegistroQuincenaService registroQuincenaService;

    @GetMapping("/planilla")
    public String main(Model model){

        planillaService.vaciarBD();
        List<PlanillaEntity> planilla = planillaService.calcularPagos();
        model.addAttribute("planilla", planilla);
        return "planilla";}


}
