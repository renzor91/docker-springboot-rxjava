package pe.com.bcp.api.service.cambio;

import io.reactivex.Single;
import pe.com.bcp.api.servicedto.response.CambioMontoResponse;
import pe.com.bcp.api.webdto.request.GetCambioMontoWebRequest;

public interface CambioMontoService {
    Single<CambioMontoResponse> getCambioMonto(GetCambioMontoWebRequest getCambioMontoRequest);
}
