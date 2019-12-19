package pe.com.bcp.api.servicedto.response;

public class CambioMontoResponse {

	private String monto;
	private String montoTipoCambio;
	private String monedaOrigen;
	private String monedaDestino;
	private String tipoCambio;

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getMontoTipoCambio() {
		return montoTipoCambio;
	}

	public void setMontoTipoCambio(String montoTipoCambio) {
		this.montoTipoCambio = montoTipoCambio;
	}

	public String getMonedaOrigen() {
		return monedaOrigen;
	}

	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}

	public String getMonedaDestino() {
		return monedaDestino;
	}

	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
}
