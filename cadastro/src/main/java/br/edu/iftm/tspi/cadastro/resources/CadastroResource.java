package br.edu.iftm.tspi.cadastro.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import br.edu.iftm.tspi.cadastro.dto.CadastroDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroResource {

    private List<CadastroDTO> cadastros = new ArrayList<>();

    @PostMapping("cadastroPost")
    public String doPost(CadastroDTO dto, Model model) {
        boolean nomeExistente = false;
        for (CadastroDTO cadastro : cadastros) {
            if (cadastro.getName().equalsIgnoreCase(dto.getName())) {
                cadastro.setEmail(dto.getEmail());
                cadastro.setPhone(dto.getPhone());
                nomeExistente = true;
                break;
            }
        }

        if (!nomeExistente)
            cadastros.add(dto);

        return doGet(model);

    }

    @RequestMapping("cadastroGet")
    public String doGet(Model model) {
        model.addAttribute("cadastro", new CadastroDTO());
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("name") String name, Model model) {
        for (CadastroDTO cadastro : cadastros) {
            if (cadastro.getName().equals(name)) {
                cadastros.remove(cadastro);
                break;
            }
        }
        return doGet(model);
    }

    @RequestMapping("editar")
    public String editar(CadastroDTO dto, Model model) {
        CadastroDTO cadastroDTO = new CadastroDTO();
        for (CadastroDTO cadastro : cadastros) {
            if (cadastro.getName().equalsIgnoreCase(dto.getName())) {
                cadastroDTO = cadastro;
                break;
            }
        }
        model.addAttribute("cadastro", cadastroDTO);
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }
}
