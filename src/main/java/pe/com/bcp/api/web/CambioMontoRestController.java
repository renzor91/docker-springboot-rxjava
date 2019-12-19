package pe.com.bcp.api.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.com.bcp.api.service.cambio.CambioMontoService;
import pe.com.bcp.api.servicedto.response.CambioMontoResponse;
import pe.com.bcp.api.webdto.request.GetCambioMontoWebRequest;
import pe.com.bcp.api.webdto.response.BaseWebResponse;
import pe.com.bcp.api.webdto.response.CambioMontoWebResponse;

@RestController
@RequestMapping(value = "/api/cambiomonto")
public class CambioMontoRestController {

	@Autowired
	private CambioMontoService cambioMontoService;

	private CambioMontoWebResponse toCambioMontoWebResponse(CambioMontoResponse cambioMontoResponse) {
		CambioMontoWebResponse cambioMontoWebResponse = new CambioMontoWebResponse();
		BeanUtils.copyProperties(cambioMontoResponse, cambioMontoWebResponse);
		return cambioMontoWebResponse;
	}

    @GetMapping(
            value = "/{monedaOrigen}/{monedaDestino}/{monto}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public Single<ResponseEntity<BaseWebResponse<CambioMontoWebResponse>>> getCambioMonto(
			@PathVariable(value = "monto") String monto, @PathVariable(value = "monedaOrigen") String monedaOrigen,
			@PathVariable(value = "monedaDestino") String monedaDestino) {

		GetCambioMontoWebRequest getCambioMontoRequest = new GetCambioMontoWebRequest();

		getCambioMontoRequest.setMonedaDestino(monedaDestino);
		getCambioMontoRequest.setMonedaOrigen(monedaOrigen);
		getCambioMontoRequest.setMonto(monto);

		return cambioMontoService.getCambioMonto(getCambioMontoRequest).subscribeOn(Schedulers.io())
				.map(cambioMontoResponse -> ResponseEntity
						.ok(BaseWebResponse.successWithData(toCambioMontoWebResponse(cambioMontoResponse))));
	}
}
