package pe.com.bcp.api.service.cambio;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import pe.com.bcp.api.repository.MonedaRepository;
import pe.com.bcp.api.servicedto.response.CambioMontoResponse;
import pe.com.bcp.api.webdto.request.GetCambioMontoWebRequest;

@Service
public class CambioMontoServiceImpl implements CambioMontoService {

	@Autowired
	private MonedaRepository monedaRepository;

	@Override
	public Single<CambioMontoResponse> getCambioMonto(GetCambioMontoWebRequest getCambioMontoRequest) {
		return cambiarMontoRepository(getCambioMontoRequest);
	}

	private Single<CambioMontoResponse> cambiarMontoRepository(
			GetCambioMontoWebRequest getCambioMontoRequest) {

		return Single.create(singleSubscriber -> {
			String tipoCambioOrigen = monedaRepository.findByNombre(getCambioMontoRequest.getMonedaOrigen());

			if (tipoCambioOrigen == null || tipoCambioOrigen.isEmpty())
				singleSubscriber.onError(new EntityNotFoundException());
			else {

				if ("dolares".equalsIgnoreCase(getCambioMontoRequest.getMonedaOrigen())) {
					
					String tipoCambioDestino = monedaRepository.findByNombre(getCambioMontoRequest.getMonedaDestino());
					
					CambioMontoResponse cambioMontoResponse = new CambioMontoResponse();
					cambioMontoResponse.setMonedaDestino(getCambioMontoRequest.getMonedaDestino());
					cambioMontoResponse.setMonedaOrigen(getCambioMontoRequest.getMonedaOrigen());
					cambioMontoResponse.setMonto(getCambioMontoRequest.getMonto());
					//tipo cambio
					Double tipoCambio=Double.parseDouble(tipoCambioDestino);
					cambioMontoResponse.setMontoTipoCambio(String.valueOf(tipoCambio*Double.parseDouble(getCambioMontoRequest.getMonto())));
					cambioMontoResponse.setTipoCambio(tipoCambioDestino);
					
					singleSubscriber.onSuccess(cambioMontoResponse);

				} else if ("dolares".equalsIgnoreCase(getCambioMontoRequest.getMonedaDestino())) {
					
					CambioMontoResponse cambioMontoResponse = new CambioMontoResponse();
					cambioMontoResponse.setMonedaDestino(getCambioMontoRequest.getMonedaDestino());
					cambioMontoResponse.setMonedaOrigen(getCambioMontoRequest.getMonedaOrigen());
					cambioMontoResponse.setMonto(getCambioMontoRequest.getMonto());
					cambioMontoResponse.setMontoTipoCambio(String.valueOf(Double.parseDouble(tipoCambioOrigen)*Double.parseDouble(getCambioMontoRequest.getMonto())));
					cambioMontoResponse.setTipoCambio(tipoCambioOrigen);
					
					singleSubscriber.onSuccess(cambioMontoResponse);
					
				} else {
					
					String tipoCambioDestino = monedaRepository.findByNombre(getCambioMontoRequest.getMonedaDestino());
					
					CambioMontoResponse cambioMontoResponse = new CambioMontoResponse();
					cambioMontoResponse.setMonedaDestino(getCambioMontoRequest.getMonedaDestino());
					cambioMontoResponse.setMonedaOrigen(getCambioMontoRequest.getMonedaOrigen());
					cambioMontoResponse.setMonto(getCambioMontoRequest.getMonto());
					
					Double tipoCambio= Double.parseDouble(tipoCambioOrigen)/Double.parseDouble(tipoCambioDestino);
					cambioMontoResponse.setTipoCambio(String.valueOf(tipoCambio));
					cambioMontoResponse.setMontoTipoCambio(String.valueOf(tipoCambio*Double.parseDouble(getCambioMontoRequest.getMonto())));
					
					
					singleSubscriber.onSuccess(cambioMontoResponse);
					
				}

				
			}
		});
	}

}
