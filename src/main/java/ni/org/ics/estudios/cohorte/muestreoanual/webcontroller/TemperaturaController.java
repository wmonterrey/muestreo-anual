package ni.org.ics.estudios.cohorte.muestreoanual.webcontroller;

import java.text.ParseException;
import java.util.List;
import javax.annotation.Resource;

import ni.org.ics.estudios.cohorte.muestreoanual.domain.TempPbmc;
import ni.org.ics.estudios.cohorte.muestreoanual.domain.TempRojoBhc;
import ni.org.ics.estudios.cohorte.muestreoanual.service.LabBHCService;
import ni.org.ics.estudios.cohorte.muestreoanual.service.LabPbmcService;
import ni.org.ics.estudios.cohorte.muestreoanual.service.LabSeroService;
import ni.org.ics.estudios.cohorte.muestreoanual.service.ReporteService;
import ni.org.ics.estudios.cohorte.muestreoanual.service.TempPbmcService;
import ni.org.ics.estudios.cohorte.muestreoanual.service.TempRojoBhcService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Maneja las solicitudes que van a la aplicacion relacionadas a json
 * 
 * @author Brenda Lopez
 */
@Controller
@RequestMapping("/temperaturas/*")
public class TemperaturaController {
	@Resource(name="tPbmcService")
	private TempPbmcService tPbmcService;
	@Resource(name="tRojoBhcService")
	private TempRojoBhcService tRojoBhcService;
	@Resource(name="labBhcService")
	private LabBHCService labBhcService;
	@Resource(name="labSeroService")
	private LabSeroService labSeroService;
	@Resource(name="labPbmcService")
	private LabPbmcService labPbmcService;
	@Resource(name="reporteService")
	private ReporteService reporteService;
	private static final Logger logger = LoggerFactory.getLogger(TemperaturaController.class);

	@RequestMapping(value = "/temperaturas", method = RequestMethod.GET)
    public String fetchPBMCsJSP(Model model) throws ParseException { 	
    	logger.debug("Mostrando Temps en JSP");
    	List<TempPbmc> tPbmc = tPbmcService.getTempPbmcs();
    	List<TempRojoBhc> tRojo = tRojoBhcService.getTempRojoBhcs();
    	List<TempPbmc> tFueraPbmc = tPbmcService.getTempPbmcsFueradeRango();
    	List<TempRojoBhc> tFueraRojo = tRojoBhcService.getTempRojoBhcsFueradeRango();
    	model.addAttribute("tPbmc", tPbmc);
    	model.addAttribute("tRojo", tRojo);
    	model.addAttribute("pbmcsfuera", tFueraPbmc);
    	model.addAttribute("rojosfuera", tFueraRojo);
    	List<Object> muestrasTotal = reporteService.getMuestrasTotal();
        model.addAttribute("muestrasTotal", muestrasTotal);
        List<Object> muestrasxEstudio = reporteService.getMuestrasxEstudio();
        model.addAttribute("muestrasEstudio", muestrasxEstudio);
        List<Object> muestrasxTubo = reporteService.getMuestrasxTubo();
        model.addAttribute("muestrasTubo", muestrasxTubo);
    	return "temperaturas/temp";
	}
    
}
