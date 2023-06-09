package MilkStgo.example.demo.controllers;

import MilkStgo.example.demo.services.SubirDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SubirDataController {

    @Autowired
    private SubirDataService subirData;

    @GetMapping("/fileUpload")
    public String main() { return "fileUpload";}

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        subirData.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo Acopio.csv cargado correctamente!");
        subirData.vaciarBD();
        subirData.leerCsv("Acopio.csv");
        return "redirect:/fileUpload";
    }
}
